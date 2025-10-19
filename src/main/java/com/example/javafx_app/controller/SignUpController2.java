package com.example.javafx_app.controller;

import com.example.javafx_app.Account;
import com.example.javafx_app.BankManager;
import com.example.javafx_app.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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
        String password = PasswordTextField.getText();
        String passwordAgain = PasswordAgainTextField.getText();
        String pin = PINTextField.getText();

        // Xóa lỗi cũ
        PasswordErrorLog.setText("");
        PasswordAgainErrorLog.setText("");
        PINErrorLog.setText("");

        boolean isValid = true;

        // 1. Kiểm tra mật khẩu
        String passwordError = BankManager.VerifySignUpInformation.validatePassword(password);
        if (passwordError != null) {
            PasswordErrorLog.setText(passwordError);
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
        String pinError = BankManager.VerifySignUpInformation.validatePIN(pin);
        if (pinError != null) {
            PINErrorLog.setText(pinError);
            isValid = false;
        }

        // 4. Nếu hợp lệ → tạo tài khoản mới
        if (isValid) {
            BankManager.newAcc = new Account(
                    BankManager.newUser.getCitizenID(),
                    "123456789" + BankManager.ACCOUNTS.size(),
                    password,
                    0,
                    "VND",
                    pin
            );
            BankManager.AddNewAccount();
            SceneUtils.switchScene(SceneUtils.getStageFromEvent(event), "login_scene.fxml");
            BankManager.ResetNewUserAndAcc();
        }
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