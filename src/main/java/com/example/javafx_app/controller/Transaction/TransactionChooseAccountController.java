package com.example.javafx_app.controller.Transaction;

import com.example.javafx_app.BankApplication;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class TransactionChooseAccountController {
    @FXML
    public void ChuyenKhoan(ActionEvent event) throws IOException {
        FXMLLoader nextSceneLoader = new FXMLLoader(BankApplication.class.getResource("TransactionScene/transacting_scene.fxml"));
        Parent nextSceneRoot = nextSceneLoader.load();
        TransactingController controller = nextSceneLoader.getController();
        controller.displaySendingAccountIDAndMoney(AccountManager.getInstance().getCurrentAccount());
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),nextSceneRoot);
    }
    @FXML
    public void QuayLai(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"HomeScenes/home_scene.fxml");
    }
}
