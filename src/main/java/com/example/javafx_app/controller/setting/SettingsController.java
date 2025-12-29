package com.example.javafx_app.controller.setting;

import com.example.javafx_app.controller.LoginController;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.util.Pair;

import static com.example.javafx_app.config.Constant.mainStage;

public class SettingsController {
    @FXML
    public void GoToUserDetailSettings(ActionEvent event){
        SceneUtils.switchScene(mainStage,"setting/setting_user_detail.fxml");
    }
    @FXML
    public void GoToPasswordSettings(ActionEvent event){
        SceneUtils.switchScene(mainStage,"setting/setting_password.fxml");
    }
    @FXML
    public void GoToPINSettings(ActionEvent event){
        SceneUtils.switchScene(mainStage,"setting/setting_PIN.fxml");
    }
    @FXML
    public void GoToThemeSettings(ActionEvent event){
        System.out.println("Go to Theme Settings");
    }
    @FXML
    public void returnToHomeScene(ActionEvent event){
        SceneUtils.switchScene(mainStage,
                AccountManager.getInstance().chooseHomeScene(AccountManager.getInstance().getCurrentAccount()));
    }
    @FXML
    public void DangXuat(ActionEvent event){
        AccountManager.getInstance().logOut();
        Pair<Parent, LoginController> scene = SceneUtils.getRootAndController("login_scene.fxml");
        scene.getValue().loadCheat();
        SceneUtils.switchScene(mainStage,scene.getKey());
    }
}
