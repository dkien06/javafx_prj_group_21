package com.example.javafx_app.controller.Staff;

import com.example.javafx_app.manager.UserManager;
import com.example.javafx_app.object.User.Customer;
import com.example.javafx_app.object.User.USER_TYPE;
import com.example.javafx_app.object.User.User;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.function.Function;

import static com.example.javafx_app.config.Constant.mainStage;

public class StaffFindCustomerController {

    @FXML private TextField searchField;
    @FXML private Text errorLog;
    @FXML private VBox resultVBox;

    // Các Label hiển thị thông tin KHÁCH HÀNG
    @FXML private Label cccdLabel;
    @FXML private Label nameLabel;
    @FXML private Label phoneLabel;
    @FXML private Label emailLabel;

    // THÊM CÁC TRƯỜNG MỚI: NGÀY SINH VÀ GIỚI TÍNH
    @FXML private Label DoBLabel;
    @FXML private Label genderLabel;

    @FXML
    public void initialize() {
        // Ẩn khu vực kết quả khi khởi động
        resultVBox.setVisible(false);
        errorLog.setText("");
    }

    @FXML
    private void TimKiemKhachHang(ActionEvent event) {
        String cccdNumber = searchField.getText().trim();

        errorLog.setText("");

        if (cccdNumber.isEmpty()) {
            errorLog.setText("Vui lòng nhập số CCCD.");
            return;
        }

        try {
            // 1. Tìm kiếm User bằng Citizen ID
            User user = UserManager.getInstance().findUserByCitizenID(cccdNumber);

            // 2. Kiểm tra nếu tìm thấy và là Customer
            if (user != null && user.getType().equals(USER_TYPE.Customer)) {

                Customer customer = (Customer) user;
                displayCustomerInfo(customer);
                resultVBox.setVisible(true); // Hiển thị khu vực kết quả

            } else {
                // 3. Không tìm thấy hoặc User không phải là Customer
                errorLog.setText("Không tìm thấy Khách hàng với số CCCD này.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorLog.setText("Lỗi: Không thể kết nối hoặc truy vấn dữ liệu.");
        }
    }

    private void displayCustomerInfo(Customer customer) {

        // Helper function để lấy giá trị an toàn (xử lý NULL)
        Function<String, String> safeStringValue = (value) ->
                (value != null && !value.isEmpty()) ? value : "N/A";

        // Helper function xử lý LocalDate
        Function<LocalDate, String> safeDateValue = (date) ->
                (date != null) ? date.toString() : "N/A";

        // Cập nhật các trường đã có
        // Lưu ý: customer.getID() và customer.getAddress() không có trong code gốc, nên tôi dùng giả định
        cccdLabel.setText("Số CCCD: " + safeStringValue.apply(customer.getCitizenID()));
        nameLabel.setText("Họ và Tên: " + safeStringValue.apply(customer.getFullName()));
        phoneLabel.setText("Số điện thoại: " + safeStringValue.apply(customer.getPhoneNumber()));
        emailLabel.setText("Email: " + safeStringValue.apply(customer.getEmail()));

        DoBLabel.setText("Ngày sinh: " + safeDateValue.apply(customer.getDateOfBirth()));
        genderLabel.setText("Giới tính: " + customer.getGender().toString());

    }


    @FXML
    private void QuayLai(ActionEvent event) throws IOException {
        SceneUtils.switchScene(mainStage, "HomeScenes/staff_home_scene.fxml");
    }
}