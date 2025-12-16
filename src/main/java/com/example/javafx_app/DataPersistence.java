package com.example.javafx_app;

import com.example.javafx_app.config.ExampleUser;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.BankManager; // Import BankManager cho currentDate
import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.manager.UserManager;
import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.object.Transaction;
import com.example.javafx_app.object.User.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate; // Import LocalDate
import java.util.HashMap; // Import HashMap
import java.util.List;
import java.util.Map;

/**
 * Lớp tiện ích để lưu và tải dữ liệu ứng dụng bằng cách sử dụng Java Serialization.
 * Dữ liệu được lưu trữ trong thư mục "data/" tại thư mục gốc của dự án.
 */
public class DataPersistence {

    private static final String DATA_DIR = "data";
    private static final String ACCOUNT_FILE = DATA_DIR + "/accounts.bin";
    private static final String USER_FILE = DATA_DIR + "/users.bin";
    private static final String TRANSACTION_FILE = DATA_DIR + "/transactions.bin";
    // Thêm hằng số cho tệp lưu trữ thông tin đăng nhập
    private static final String LOGIN_STATE_FILE = DATA_DIR + "/login_state.bin"; //
    // Các trường static công khai để mô phỏng dữ liệu đăng nhập cần lưu (thường nằm trong một LoginManager)
    public static LocalDate lastAppUsageDate = LocalDate.now(); // Giá trị mặc định
    public static String savedAccountID = ""; // Account ID được lưu
    public static String savedPassword = ""; // Mật khẩu được lưu (nếu user chọn nhớ)
    public static String accountType = "";
    private static void initializeDataDirectory() {
        Path dataPath = Paths.get(DATA_DIR);
        if (!Files.exists(dataPath)) {
            try {
                Files.createDirectories(dataPath);
                System.out.println("Đã tạo thư mục dữ liệu: " + DATA_DIR);
            } catch (IOException e) {
                System.err.println("Lỗi khi tạo thư mục dữ liệu: " + e.getMessage());
            }
        }
    }

    /**
     * Lưu trữ toàn bộ dữ liệu (Account, User, Transaction) vào các tệp binary.
     */
    @SuppressWarnings("unchecked")
    public static void saveAllData() {
        initializeDataDirectory();
        System.out.println("Đang lưu dữ liệu...");

        saveData((Serializable) AccountManager.getInstance().getAccountList(), ACCOUNT_FILE);
        saveData((Serializable) UserManager.getInstance().getUserList(), USER_FILE);
        saveData((Serializable) TransactionManager.getInstance().getTransactionsList(), TRANSACTION_FILE);

        // LƯU THÔNG TIN ĐĂNG NHẬP (Last Usage Date, Account ID, Password)
        try {
            // Sử dụng HashMap để đóng gói 3 trường dữ liệu cần lưu
            Map<String, Serializable> loginStateData = new HashMap<>(); //

            // 1. Thời gian điểm sử dụng ứng dụng gần nhất (startdate)
            // Lấy từ BankManager.getCurrentDate(), nếu không tồn tại thì dùng lastAppUsageDate
            LocalDate dateToSave = BankManager.getCurrentDate() != null ? BankManager.getCurrentDate() : lastAppUsageDate; //
            loginStateData.put("lastUsageDate", dateToSave); //

            // 2. String tài khoản (Account ID)
            loginStateData.put("savedAccountID", savedAccountID); //

            // 3. Mật khẩu (chỉ nếu người dùng yêu cầu nhớ)
            loginStateData.put("savedPassword", savedPassword);
            loginStateData.put("accountType", accountType);

            saveData((Serializable) loginStateData, LOGIN_STATE_FILE); //

        } catch (Exception e) {
            System.err.println("Lỗi khi lưu thông tin đăng nhập: " + e.getMessage());
        }

        System.out.println("Lưu dữ liệu hoàn tất.");
    }

    /**
     * Tải toàn bộ dữ liệu (Account, User, Transaction) từ các tệp binary.
     * Nếu không có tệp, sẽ khởi tạo dữ liệu giả lập.
     */
    @SuppressWarnings("unchecked")
    public static void loadAllData() {
        initializeDataDirectory();
        System.out.println("Đang tải dữ liệu...");

        try {
            // 1. Tải Account Map
            AccountManager.getInstance().setAccountMap((Map<String, Account>) loadData(ACCOUNT_FILE));

            // 2. Tải User Map
            UserManager.getInstance().setUserMap((Map<String, User>) loadData(USER_FILE));

            // 3. Tải Transaction List
            TransactionManager.getInstance().setTransactionsList((List<Transaction>) loadData(TRANSACTION_FILE));

            // TẢI THÔNG TIN ĐĂNG NHẬP
            try {
                // Đọc Map chứa 3 trường dữ liệu mới
                Map<String, Serializable> loginStateData = (Map<String, Serializable>) loadData(LOGIN_STATE_FILE); //

                // Gán lại các giá trị đã tải vào các trường static
                lastAppUsageDate = (LocalDate) loginStateData.get("lastUsageDate"); //
                savedAccountID = (String) loginStateData.get("savedAccountID"); //
                savedPassword = (String) loginStateData.get("savedPassword"); //
                accountType = (String) loginStateData.get("accountType");
                // Cập nhật ngày sử dụng ứng dụng gần nhất vào BankManager
                BankManager.setCurrentDate(lastAppUsageDate);
            } catch (FileNotFoundException e) {
                System.out.println("Không tìm thấy tệp lưu trữ thông tin đăng nhập. Bỏ qua.");
            } catch (Exception e) {
                System.err.println("Lỗi khi tải thông tin đăng nhập: " + e.getMessage());
            }

            System.out.println("Tải dữ liệu từ tệp thành công.");
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy tệp dữ liệu. Đang khởi tạo dữ liệu giả lập...");
            ExampleUser.init(); // Khởi tạo dữ liệu giả lập nếu không có tệp
            saveAllData(); // Lưu dữ liệu giả lập vừa tạo vào tệp
            System.out.println("Đã khởi tạo và lưu dữ liệu giả lập.");
        } catch (Exception e) {
            System.err.println("Lỗi khi tải dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Phương thức generic để lưu một đối tượng Serializable vào tệp.
     */
    private static void saveData(Serializable object, String filePath) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(object);
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi dữ liệu vào " + filePath + ": " + e.getMessage());
        }
    }

    /**
     * Phương thức generic để tải một đối tượng Serializable từ tệp.
     * Trả về Object để ép kiểu ngoài.
     */
    private static Object loadData(String filePath) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return ois.readObject();
        }
    }
    public static void clearAllData() {
        System.out.println("Đang xóa các tệp dữ liệu...");
        try {
            Files.deleteIfExists(Paths.get(ACCOUNT_FILE));
            Files.deleteIfExists(Paths.get(USER_FILE));
            Files.deleteIfExists(Paths.get(TRANSACTION_FILE));
            Files.deleteIfExists(Paths.get(LOGIN_STATE_FILE)); // Xóa thêm tệp lưu trữ thông tin đăng nhập
            System.out.println("Xóa dữ liệu hoàn tất. Dữ liệu sẽ được tải lại từ giả lập trong lần khởi động tới.");
        } catch (IOException e) {
            System.err.println("Lỗi khi xóa tệp dữ liệu: " + e.getMessage());
        }
    }
    public static void main(String[] args) {

    }
}