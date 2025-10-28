package com.example.javafx_app.controller;

import com.example.javafx_app.AccountManager;
import com.example.javafx_app.BankManager;
import com.example.javafx_app.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField CCCDField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private Label WrongLoginLabel ;
    @FXML
    void DangNhap(ActionEvent event) {
        String CCCD = CCCDField.getText();
        String Password = PasswordField.getText();
        if(BankManager.VerifyPassword(CCCD, Password)) {
            if(AccountManager.getInstance().logIn(CCCD,Password)){
                WrongLoginLabel.setText("Có lỗi xảy ra");
            }
            SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"home_scene.fxml");
        }
        else{
            if(Password.isEmpty()){
                WrongLoginLabel.setText("Vui lòng nhập mật khẩu");
            }
            else
                WrongLoginLabel.setText("CCCD hoặc mật khẩu bị sai");
        }
    }

    @FXML
    void TaoTaiKhoanMoi(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"signup_scene1.fxml");
    }
}
