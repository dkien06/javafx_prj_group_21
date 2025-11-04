package com.example.javafx_app.controller;

import com.example.javafx_app.Manager.AccountManager;
import com.example.javafx_app.Manager.BankManager;
import com.example.javafx_app.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class StartScene {

    @FXML
    private TextField PhoneTexrField;

    @FXML
    private TextField EmailTextField;

    @FXML
    private DatePicker DatePicker;

    // Hàm sẽ được gọi khi click nút (gán trực tiếp trên SceneBuilder)
    @FXML
    private void NextToLogin(ActionEvent event) {
        BankManager.setCurrentEmail(EmailTextField.getText());
        BankManager.setCurrentPhoneNumber(PhoneTexrField.getText());
        BankManager.setTodayDate(DatePicker.getValue());
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"login_scene.fxml");
    }
}
