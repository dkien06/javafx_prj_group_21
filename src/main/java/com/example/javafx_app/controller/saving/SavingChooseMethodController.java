package com.example.javafx_app.controller.saving;

import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SavingChooseMethodController {
    @FXML
    void MoNgay1(ActionEvent event){

    }
    @FXML
    void MoNgay2(ActionEvent event){

    }
    @FXML
    void MoNgay3(ActionEvent event){

    }
    @FXML
    void MoNgay(ActionEvent event, String type, double interestRate){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"SavingScene/saving_choose_method_scene.fxml");
    }
    @FXML
    void QuayLai(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"HomeScenes/saving_account_home_scene.fxml");
    }
}
