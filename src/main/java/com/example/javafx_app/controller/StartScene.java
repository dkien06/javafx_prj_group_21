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
    @FXML
    private void NextToLogin(ActionEvent event) {
        BankManager.setCurrentEmail(EmailTextField.getText());
        BankManager.setCurrentPhoneNumber(PhoneTextField.getText());
        BankManager.setCurrentDate(DatePicker.getValue());
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"login_scene.fxml");
    }
}
