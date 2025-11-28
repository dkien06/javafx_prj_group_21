package com.example.javafx_app.controller.saving;

import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;

import static com.example.javafx_app.config.Constant.mainStage;

public class SavingChooseMethodController {
    void MoNgay1(ActionEvent event){

    }
    void MoNgay2(ActionEvent event){

    }
    void MoNgay3(ActionEvent event){

    }
    void MoNgay(ActionEvent event, String type, double interestRate){
        SceneUtils.switchScene(mainStage,"SavingScene/saving_choose_method_scene.fxml");
    }
    void QuayLai(ActionEvent event){
        SceneUtils.switchScene(mainStage,"HomeScenes/checking_account_home_scene.fxml");
    }
}
