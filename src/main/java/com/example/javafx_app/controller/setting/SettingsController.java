package com.example.javafx_app.controller.setting;

import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SettingsController {
    @FXML
    public void GoToUserDetailSettings(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"setting/setting_user_detail.fxml");
    }
    @FXML
    public void GoToPasswordSettings(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"setting/setting_password.fxml");
    }
    @FXML
    public void GoToPINSettings(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"setting/setting_PIN.fxml");
    }
    @FXML
    public void GoToThemeSettings(ActionEvent event){
        System.out.println("Go to Theme Settings");
    }
    @FXML
    public void returnToHomeScene(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"HomeScenes/home_scene.fxml");
    }
}
