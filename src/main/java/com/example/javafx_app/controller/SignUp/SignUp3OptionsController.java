package com.example.javafx_app.controller.SignUp;

import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import static com.example.javafx_app.config.Constant.mainStage;

public class SignUp3OptionsController {
    @FXML
    public void new_Customer(ActionEvent event) {
        SceneUtils.switchScene(mainStage,
                "SignUpScene/signup_new_customer1_scene.fxml");
    }
    @FXML
    public void existing_Customer(ActionEvent event) {
        SceneUtils.switchScene(mainStage,
                "SignUpScene/signup_existing_customer1_scene.fxml");
    }
    @FXML
    public void TurnBack(ActionEvent event) {
        SceneUtils.switchScene(mainStage,"login_scene.fxml");
    }
    @FXML
    public void GoToStaffForm1(ActionEvent event) {
        SceneUtils.switchScene(mainStage,"SignUpScene/signup_staff1_scene.fxml");
    }
}
