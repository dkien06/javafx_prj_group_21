package com.example.javafx_app.controller.Transaction;

import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.util.SceneUtils;
import com.example.javafx_app.object.Transaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.List;

import static com.example.javafx_app.config.Constant.mainStage;

public class TransactionHistoryController {
    @FXML
    TextArea transactionHistoryField;
    @FXML
    void QuayLai(ActionEvent event){
        SceneUtils.switchScene(mainStage,"HomeScenes/home_scene.fxml");
    }
    public void displayHistory(){
        transactionHistoryField.setWrapText(true);
        Account currentAccount = AccountManager.getInstance().getCurrentAccount();
        List<Transaction> transactionHistoryList = currentAccount.getHistory();
        for(Transaction t : transactionHistoryList){
            if(t.getFromAccount().getAccountID().equals(currentAccount.getAccountID())){
                transactionHistoryField.setText(
                        transactionHistoryField.getText()
                                + "TK " + currentAccount.getAccountID()
                                + "|GD: " + (-t.getAmount())
                                + " " + t.getDate()
                                + "|" + t.getDescription()
                                + "\n"
                );
            }
            else{
                transactionHistoryField.setText(
                        transactionHistoryField.getText()
                                + "TK " + currentAccount.getAccountID()
                                + "|GD: " + t.getAmount()
                                + " " + t.getDate()
                                + "|" + t.getDescription()
                                + "\n"
                );
            }

        }
    }
}
