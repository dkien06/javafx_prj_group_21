package com.example.javafx_app.controller.SignUp;

import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class SignUpStaff2Controller {

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
    private void returnToSignUpStaff1(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"SignUpScene/signup_staff1_scene.fxml");
    }
}
