package com.example.javafx_app.controller.setting;

import com.example.javafx_app.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SettingsController {
    @FXML
    public void GoToUserDetailSettings(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"setting_user_detail.fxml");
    }
    @FXML
    public void GoToPasswordSettings(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"setting_password.fxml");
    }
    @FXML
    public void GoToPINSettings(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"setting_PIN.fxml");
    }
    @FXML
    public void GoToThemeSettings(ActionEvent event){
        System.out.println("Go to Theme Settings");
    }
    @FXML
    public void returnToHomeScene(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"home_scene.fxml");
    }
}
