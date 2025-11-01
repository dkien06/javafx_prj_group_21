package com.example.javafx_app.controller.Transaction;

import com.example.javafx_app.BankApplication;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.util.SceneUtils;
import com.example.javafx_app.object.Transaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class TransactionChooseMethodController {
    @FXML
    public void ChuyenKhoan(ActionEvent event) throws IOException {
        TransactionManager.getInstance().newTransaction(Transaction.TransactionType.TRANSFER);
        FXMLLoader nextSceneLoader = new FXMLLoader(BankApplication.class.getResource("transacting_between_accounts.fxml"));
        Parent nextSceneRoot = nextSceneLoader.load();
        TransactingBetweenAccountsController controller = nextSceneLoader.getController();
        controller.displaySendingAccountIDAndMoney(AccountManager.getInstance().getCurrentAccount());
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),nextSceneRoot);
    }
    @FXML
    public void QuayLai(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"home_scene.fxml");
    }
}
