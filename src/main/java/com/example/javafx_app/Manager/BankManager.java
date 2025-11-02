package com.example.javafx_app.Manager;

import com.example.javafx_app.Account.Account;
import com.example.javafx_app.Transaction;
import com.example.javafx_app.User.User;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;

public class BankManager {
    // 🔹 Danh sách chứa tất cả tài khoản trong hệ thống
    protected static final List<Account> ACCOUNTS = new ArrayList<>();

    // 🔹 Khối static initializer — chạy một lần duy nhất khi class được load vào bộ nhớ
    static {
        // Tạo sẵn 100 tài khoản giả lập để test hệ thống
        for (int i = 1; i <= 100; i++) {
            Account a = new Account(
                    "123456789" + i,         // Mã căn cước công dân (giả)
                    "AC" + i,                // Mã tài khoản
                    "pwd" + i,               // Mật khẩu
                    Math.random() * 1_000_000, // Số dư ngẫu nhiên từ 0 đến 1 triệu
                    "VND",                   // Loại tiền tệ
                    "0000"                   // Mã PIN mặc định
            );
            ACCOUNTS.add(a); // Thêm vào danh sách tài khoản hệ thống
        }

        // Tạo thêm 1 tài khoản ADMIN (quản trị viên)
        ACCOUNTS.add(new Account(
                "892006",     // citizenID (CMND/CCCD)
                "AC",         // Mã tài khoản
                "892006",     // Mật khẩu
                1000,         // Số dư
                "VND",        // Loại tiền
                "0000"        // PIN
        ));
    }
    protected static Transaction currentTransaction;
    protected static List<Transaction> TRANSACTIONS = new ArrayList<>();
    // 🔹 Danh sách chứa toàn bộ người dùng trong hệ thống (có thể có nhiều tài khoản)
    protected static List<User> USERS = new ArrayList<>();

    // 🔹 Biến lưu trữ người dùng hiện đang đăng nhập vào hệ thống
    protected static User currentUser;

    // 🔹 Biến lưu trữ tài khoản (Account) hiện đang được sử dụng
    protected static Account currentAccount;
    // 🔹 Ngày hiện tại (LocalDate.now() sẽ gán khi khởi tạo)
    protected static LocalDate TodayDate;

    // 🔹 Thông tin email và số điện thoại của người dùng hiện tại (giả lập điện thoại)
    protected static String currentEmail;
    protected static String currentPhoneNumber;
    public static void setCurrentPhoneNumber(String currentPhoneNumber) { BankManager.currentPhoneNumber = currentPhoneNumber; }
    public static void setCurrentEmail(String currentEmail) { BankManager.currentEmail = currentEmail; }
    public static void setTodayDate(LocalDate TodayDate) { BankManager.TodayDate = TodayDate; }
    public static String getCurrentPhoneNumber() { return BankManager.currentPhoneNumber; }
    public static String getCurrentEmail() { return BankManager.currentEmail; }
    public static LocalDate getTodayDate() { return BankManager.TodayDate; }
    // Ham kiem tra mat khau
    public static boolean VerifyPassword(String citizenID, String password) {
        Account VerifyAccount = AccountManager.getInstance().findAccountFromCitizenID(citizenID);
        if(VerifyAccount==null) {return false;}
        System.out.println("Verify Password");
        return password.equals(VerifyAccount.getPassword());
    }
    /*Mấy cái hàm này cho phần đăng kí*/
    public enum SignUpInformationState {
        EMPTY,
        WRONG_FORM,
        WRONG_SIZE,
        EXISTED,
        RIGHT
    }
    //Check họ tên
    public static SignUpInformationState checkSignUpFullName(String fullName){
        if(fullName.isEmpty())
            return SignUpInformationState.EMPTY;
        return SignUpInformationState.RIGHT;
    }
    //Check ngày sinh
    public static SignUpInformationState checkSignUpDateOfBirth(LocalDate dateOfBirth){
        if(dateOfBirth == null)
            return SignUpInformationState.EMPTY;
        return SignUpInformationState.RIGHT;
    }
    //Check giới tính
    public static SignUpInformationState checkSignUpGender(String gender){
        if(gender == null)
            return SignUpInformationState.EMPTY;
        return SignUpInformationState.RIGHT;
    }
    //Check email (Phức tạp vcl:))
    //Email: [ten_nguoi_dung]@[duong_dan] VD: NguyenVanA1970@gmail.com Binh.TT2412345@sis.hust.edu.vn
    public static SignUpInformationState checkSignUpEmail(String email){
        if(email.isEmpty()) return SignUpInformationState.EMPTY;
        if(AccountManager.getInstance().findAccountFromEmail(email) != null) return SignUpInformationState.EXISTED;
        int viTriACong = email.indexOf('@');
        //Email thì phải có '@', hiển nhiên rồi còn gì:))
        if(viTriACong == -1)return SignUpInformationState.WRONG_FORM;
        //Check [ten_nguoi_dung]
        //Kí tự đầu phải bằng chữ. Các kí tự còn lại có thể có chữ cái, chữ số và kí tự '.','_','+'
        if(!Character.isLetter(email.charAt(0)))return SignUpInformationState.WRONG_FORM;
        for(int i = 1; i < viTriACong; i++){
            if(!(Character.isLetterOrDigit(email.charAt(i)) || email.charAt(i) == '.' || email.charAt(i) == '_' || email.charAt(i) == '+')){
                return SignUpInformationState.WRONG_FORM;
            }
        }
        //Check [duong_dan]
        //Tất cả các kí tự phải bằng chữ in thường, bắt buộc phải có kí tự '.'
        int dotCounter = 0;
        for(int i = viTriACong + 1; i<email.length(); i++){
            if(!(Character.isLowerCase(email.charAt(i)) || (i != viTriACong && email.charAt(i) == '.'))){
                return SignUpInformationState.WRONG_FORM;
            }
            if(email.charAt(i) == '.')dotCounter++;
        }
        if(dotCounter == 0) {
            return SignUpInformationState.WRONG_FORM;
        }
        return SignUpInformationState.RIGHT;
    }
    //Check số điện thoại
    public static SignUpInformationState checkSignUpPhoneNumber(String phoneNumber){
        if(phoneNumber.isEmpty()) return SignUpInformationState.EMPTY;
        if(AccountManager.getInstance().findAccountFromPhoneNumber(phoneNumber) == null)return SignUpInformationState.EXISTED;
        for(int i = 0; i < phoneNumber.length(); i++){
            if(!Character.isDigit(phoneNumber.charAt(i)))return SignUpInformationState.WRONG_FORM;
        }
        if(phoneNumber.length() != 10) return SignUpInformationState.WRONG_SIZE;
        return SignUpInformationState.RIGHT;
    }
    //Check số căn cước công dân
    public static SignUpInformationState checkSignUpCitizenID(String citizenID){
        if(citizenID.isEmpty()) return SignUpInformationState.EMPTY;
        for(int i = 0; i < citizenID.length(); i++){
            if(!Character.isDigit(citizenID.charAt(i)))return SignUpInformationState.WRONG_FORM;
        }
        return SignUpInformationState.RIGHT;
    }
    public static Map<String, SignUpInformationState> CheckAllSignUpInfo(String fullName, LocalDate dateOfBirth, String gender, String email, String phoneNumber, String citizenID){
        //Basically #include <unorder_map> nhưng của java:))
        //Nó khá giống mảng nhưng index thay vì là int thi nó có thể là biến tùy ý. VD: infoState["fullName"]
        //Gán giá trị thay vì trực tiếp (A["blabla"] = 1) thì phải dùng hàm Map.put("blabla", 1)
        //Còn lấy giá trị thì dễ thôi: Map.set("blabla") (=1)
        Map<String, SignUpInformationState> infoStates = new HashMap<>();

        infoStates.put("fullName",checkSignUpFullName(fullName));
        infoStates.put("dateOfBirth",checkSignUpDateOfBirth(dateOfBirth));
        infoStates.put("gender",checkSignUpGender(gender));
        infoStates.put("email", checkSignUpEmail(email));
        infoStates.put("phoneNumber", checkSignUpPhoneNumber(phoneNumber));
        infoStates.put("citizenID", checkSignUpCitizenID(citizenID));

        return infoStates;
    }
    public enum PasswordState{
        EMPTY,
        WEAK,
        NOT_MATCHED,
        RIGHT
    }
    /**
     * Phương thức phụ để kiểm tra độ mạnh của mật khẩu bằng Biểu thức chính quy (Regex).
     * @param password Mật khẩu cần kiểm tra.
     * @return true nếu mật khẩu mạnh, false nếu ngược lại.
     */
    private static boolean isPasswordStrong(String password) {
        // Định nghĩa mẫu regex cho mật khẩu mạnh
        // ^                 : Bắt đầu chuỗi
        // (?=.*[a-z])       : Phải chứa ít nhất một chữ thường
        // (?=.*[A-Z])       : Phải chứa ít nhất một chữ hoa
        // (?=.*\\d)         : Phải chứa ít nhất một chữ số
        // (?=.*[!@#$%^&*()]) : Phải chứa ít nhất một ký tự đặc biệt trong danh sách
        // . {8,}            : Phải có ít nhất 8 ký tự
        // $                 : Kết thúc chuỗi
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()]).{8,}$";

        // Kiểm tra xem mật khẩu có khớp với mẫu không
        return Pattern.matches(passwordPattern, password);
    }
    public static PasswordState checkNewPassword(String password){
        if (password.isEmpty()) return PasswordState.EMPTY;
        else if (!isPasswordStrong(password)) return PasswordState.WEAK;
        else return PasswordState.RIGHT;
    }
    public static PasswordState checkPasswordAgain(String password, String passwordAgain){
        if(passwordAgain.isEmpty())return PasswordState.EMPTY;
        else if(password.equals(passwordAgain))return PasswordState.NOT_MATCHED;
        else return PasswordState.RIGHT;
    }
    public enum PINState{
        EMPTY,
        WRONG_FORM,
        RIGHT
    }
    public static PINState checkNewPIN(String PIN){
        if(PIN.isEmpty())return PINState.EMPTY;
        else if (!PIN.matches("\\d{6}"))return PINState.WRONG_FORM;
        else return PINState.RIGHT;
    }
}
