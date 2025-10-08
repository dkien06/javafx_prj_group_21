package com.example.javafx_app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SignInController {
    @FXML
    void returnToLoginScene(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"login_scene.fxml");
    }
}
