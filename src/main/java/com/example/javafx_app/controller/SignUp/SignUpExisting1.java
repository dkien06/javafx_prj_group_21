package com.example.javafx_app.controller.SignUp;

import com.example.javafx_app.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpExisting1 {
    @FXML
    private TextField CCCDField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    public void NextToForm2(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),
                "/com/example/javafx_app/SignUpScene/signup_existing_customer2_scene.fxml");
    }

    @FXML
    public void backTo3SignUpOption(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),
                "/com/example/javafx_app/SignUpScene/signup_3option_scene.fxml");
    }
}
