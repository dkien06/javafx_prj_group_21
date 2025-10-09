package com.example.javafx_app.controller;

import com.example.javafx_app.SceneUtils;
import javafx.event.ActionEvent;


public class HomeController {
    public void ChuyenTien(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"transaction_choose_method_scene.fxml");
    }
}
