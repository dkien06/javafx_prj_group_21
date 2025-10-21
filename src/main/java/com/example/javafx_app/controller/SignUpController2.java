package com.example.javafx_app.controller;

import com.example.javafx_app.Account;
import com.example.javafx_app.AccountManager;
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
        switch (BankManager.checkNewPassword(password)){
            case EMPTY:
                PasswordErrorLog.setText("Mật khẩu không được để trống.");
                isValid = false;
                break;
            case WEAK:
                PasswordErrorLog.setText("Mật khẩu yếu: cần ít nhất 8 ký tự, gồm chữ hoa, chữ thường, số và ký tự đặc biệt.");
                isValid = false;
                break;
            case RIGHT:
                PasswordErrorLog.setText("");
                break;
        }

        // 2. Kiểm tra nhập lại mật khẩu (Chỉ check khi password đủ mạnh)
        if(isValid){
            switch (BankManager.checkPasswordAgain(password,passwordAgain)){
                case EMPTY:
                    PasswordAgainErrorLog.setText("Vui lòng nhập lại mật khẩu.");
                    isValid = false;
                    break;
                case NOT_MATCHED:
                    PasswordAgainErrorLog.setText("Mật khẩu nhập lại không khớp.");
                    isValid = false;
                    break;
                case RIGHT:
                    PasswordAgainErrorLog.setText("");
                    break;
            }
        }

        // 3. Kiểm tra mã PIN
        switch (BankManager.checkNewPIN(pin)){
            case EMPTY:
                PINErrorLog.setText("Mã PIN không được để trống.");
                isValid = false;
                break;
            case WRONG_FORM:
                PINErrorLog.setText("Mã PIN phải là 6 chữ số.");
                isValid = false;
                break;
            case RIGHT:
                PINErrorLog.setText("");
                break;
        }

        // --- Kết thúc quá trình xác thực ---
        // Nếu tất cả dữ liệu đều hợp lệ
        if (isValid) {
            AccountManager.getInstance().resister(BankManager.newUser,password,pin);
            SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"login_scene.fxml");
            // tra lai gia tri cho new user va account cho lan dang nhap tiep theo
            BankManager.ResetNewUser();
        }
    }
    @FXML
    void returnToLoginScene(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"signup_scene1.fxml");
    }
}