package com.example.javafx_app.controller;

import com.example.javafx_app.Account;
import com.example.javafx_app.BankManager;
import com.example.javafx_app.SceneUtils;
import javafx.application.Preloader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.regex.Pattern;

public class SignUpController2 {

    // Khai báo các biến tương ứng với các thành phần trong FXML
    @FXML
    private PasswordField PasswordTextField; // Dùng PasswordField để ẩn ký tự

    @FXML
    private PasswordField PasswordAgainTextField;

    @FXML
    private TextField PINTextField;

    @FXML
    private Text PasswordErrorLog;

    @FXML
    private Text PasswordAgainErrorLog;

    @FXML
    private Text PINErrorLog;

    @FXML
    private Button complete_btn;

    @FXML
    private Button btn_return;

    /**
     * Phương thức xử lý sự kiện khi nhấn nút "Hoàn thành".
     * Được liên kết với onAction="#HoanThanh" trong FXML.
     */
    @FXML
    void HoanThanh(ActionEvent event) {
        // Lấy dữ liệu từ các ô nhập liệu
        String password = PasswordTextField.getText();
        String passwordAgain = PasswordAgainTextField.getText();
        String pin = PINTextField.getText();

        // Xóa các thông báo lỗi cũ trước khi kiểm tra lại
        PasswordErrorLog.setText("");
        PasswordAgainErrorLog.setText("");
        PINErrorLog.setText("");

        boolean isValid = true;

        // --- Bắt đầu quá trình xác thực dữ liệu ---

        // 1. Kiểm tra độ mạnh của mật khẩu
        if (password.isEmpty()) {
            PasswordErrorLog.setText("Mật khẩu không được để trống.");
            isValid = false;
        } else if (!isPasswordStrong(password)) {
            PasswordErrorLog.setText("Mật khẩu yếu: cần ít nhất 8 ký tự, gồm chữ hoa, chữ thường, số và ký tự đặc biệt.");
            isValid = false;
        }

        // 2. Kiểm tra nhập lại mật khẩu
        if (passwordAgain.isEmpty()) {
            PasswordAgainErrorLog.setText("Vui lòng nhập lại mật khẩu.");
            isValid = false;
        } else if (!password.equals(passwordAgain)) {
            PasswordAgainErrorLog.setText("Mật khẩu nhập lại không khớp.");
            isValid = false;
        }

        // 3. Kiểm tra mã PIN
        if (pin.isEmpty()) {
            PINErrorLog.setText("Mã PIN không được để trống.");
            isValid = false;
        } else if (!pin.matches("\\d{6}")) {
            PINErrorLog.setText("Mã PIN phải là 6 chữ số.");
            isValid = false;
        }

        // --- Kết thúc quá trình xác thực ---
        // Nếu tất cả dữ liệu đều hợp lệ
        if (isValid) {
            BankManager.newAcc = new Account(BankManager.newUser.getCitizenID(),"123456789"+BankManager.ACCOUNTS.size(),
                    password,0,"VND",pin) ;
            BankManager.AddNewAccount();
            SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"login_scene.fxml");
            // tra lai gia tri cho new user va account cho lan dang nhap tiep theo
            BankManager.ResetNewUserAndAcc();
        }
    }/**
     * Phương thức phụ để kiểm tra độ mạnh của mật khẩu bằng Biểu thức chính quy (Regex).
     * @param password Mật khẩu cần kiểm tra.
     * @return true nếu mật khẩu mạnh, false nếu ngược lại.
     */
    private boolean isPasswordStrong(String password) {
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

    /**
     * Phương thức xử lý sự kiện khi nhấn nút "Quay lại"
     * Phương thức này được gọi khi thuộc tính onAction="#returnToLoginScene" được kích hoạt.
     * @param event Sự kiện nhấp chuột
     */
    @FXML
    void returnToLoginScene(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"signup_scene1.fxml");
    }
}