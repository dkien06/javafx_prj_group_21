package com.example.javafx_app.controller;

import com.example.javafx_app.manager.BankManager;
import com.example.javafx_app.manager.UserManager;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class StartScene {

    @FXML
    private TextField PhoneTextField;

    @FXML
    private TextField EmailTextField;

    @FXML
    private DatePicker DatePicker;

    // Hàm sẽ được gọi khi click nút (gán trực tiếp trên SceneBuilder)
    // Sau nay se them khong the chọn ngày trước ngày cuối cùng tức là khôgn thể đi ngược thời gian
    @FXML
    private void NextToLogin(ActionEvent event) {
        BankManager.SignUpInformationState emailState = BankManager.checkSignUpEmail(EmailTextField.getText());
        BankManager.SignUpInformationState phoneState = BankManager.checkSignUpPhoneNumber(PhoneTextField.getText());
        System.out.println(emailState+" "+phoneState);
        if(emailState != BankManager.SignUpInformationState.EXISTED && emailState != BankManager.SignUpInformationState.RIGHT) {
            return;
        }
        if(phoneState != BankManager.SignUpInformationState.EXISTED &&
                phoneState != BankManager.SignUpInformationState.RIGHT) return;

        BankManager.setCurrentPhoneNumber(PhoneTextField.getText());
        BankManager.setCurrentDate(DatePicker.getValue());
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"login_scene.fxml");
    }
}
