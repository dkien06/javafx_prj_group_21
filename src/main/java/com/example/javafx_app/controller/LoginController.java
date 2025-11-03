package com.example.javafx_app.controller;

import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.BankManager;
import com.example.javafx_app.util.DialogUtils;
import com.example.javafx_app.util.SceneUtils;
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
            if(!AccountManager.getInstance().logIn(CCCD,Password)){
                WrongLoginLabel.setText("Có lỗi xảy ra");
            }
            else {
                SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"home_scene.fxml");
                try {
                    String name = CCCDField.getText();
                    if (!name.isEmpty()) {
                        System.out.println(DialogUtils.dialogButton(
                                "Xác nhận đối tượng",
                                "Vui lòng chọn đối tượng",
                                "Việc này sẽ giúp cho chúng tôi giúp đỡ bạn",
                                "Khách hàng mới","Khách hàng cũ","Nhân viên","Thằng gay lọ"
                        ));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
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
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"signup_new_customer1_scene.fxml");
    }
}
