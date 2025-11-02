package com.example.javafx_app.controller.SignUp;

import com.example.javafx_app.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SignUpNew2 {

    @FXML
    private PasswordField PasswordTextField;

    @FXML
    private Text PasswordErrorLog;

    @FXML
    private PasswordField PasswordAgainTextField;

    @FXML
    private Text PasswordAgainErrorLog;

    @FXML
    private TextField PINTextField;

    @FXML
    private Text PINErrorLog;

    @FXML
    private Button complete_btn;

    @FXML
    private Button btn_return;

    @FXML
    private void Complete(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"login_scene.fxml");
    }

    @FXML
    private void returnToSignUpNew1(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),
                "/com/example/javafx_app/SignUpScene/signup_new_customer1_scene.fxml");
    }
}
