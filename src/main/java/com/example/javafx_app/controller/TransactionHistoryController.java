package com.example.javafx_app.controller;

import com.example.javafx_app.Account;
import com.example.javafx_app.AccountManager;
import com.example.javafx_app.SceneUtils;
import com.example.javafx_app.Transaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.List;

public class TransactionHistoryController {
    @FXML
    TextArea transactionHistoryField;
    @FXML
    void QuayLai(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"home_scene.fxml");
    }
    void displayHistory(){
        transactionHistoryField.setWrapText(true);
        Account currentAccount = AccountManager.getInstance().getCurrentAccount();
        List<Transaction> transactionHistoryList = currentAccount.getHistory();
        for(Transaction t : transactionHistoryList){
            transactionHistoryField.setText(
                      "TK " + currentAccount.getAccountID()
                    + "|GD: " + (-t.getAmount())
                    + " " + t.getDate()
                    + "|" + t.getDescription()
                    + "\n"
            );
        }
    }
}
