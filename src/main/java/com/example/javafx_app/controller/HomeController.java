package com.example.javafx_app.controller;

import com.example.javafx_app.Manager.AccountManager;
import com.example.javafx_app.util.SceneUtils;
import com.example.javafx_app.controller.Transaction.TransactionHistoryController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;


public class HomeController {
    public void ChuyenTien(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"transaction_choose_method_scene.fxml");
    }
    public void LichSuGiaoDich(ActionEvent event) throws IOException {
        FXMLLoader nextSceneLoader = new FXMLLoader(SceneUtils.class.getResource("transaction_history_scene.fxml"));
        Parent nextSceneRoot = nextSceneLoader.load();

        TransactionHistoryController controller = nextSceneLoader.getController();
        controller.displayHistory();

        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),nextSceneRoot);
    }
    public void DangXuat(ActionEvent event){
        AccountManager.getInstance().logOut();
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"login_scene.fxml");
    }
    public void CaiDat(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"setting.fxml");
    }
}
