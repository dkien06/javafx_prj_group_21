package com.example.javafx_app.controller.SignUp;

import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SignUp3OptionsController {
    @FXML
    public void new_costumer(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),
                "SignUpScene/signup_new_customer1_scene.fxml");
    }
    @FXML
    public void existing_costumer(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),
                "SignUpScene/signup_existing_customer1_scene.fxml");
    }
    @FXML
    public void TurnBack(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"login_scene.fxml");
    }
    @FXML
    public void GoToStaffForm1(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"SignUpScene/signup_staff1_scene.fxml");
    }
}
