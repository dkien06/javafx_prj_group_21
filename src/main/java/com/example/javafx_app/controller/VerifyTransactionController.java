package com.example.javafx_app.controller;

import com.example.javafx_app.AccountManager;
import com.example.javafx_app.SceneUtils;
import com.example.javafx_app.Transaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.io.IOException;

public class VerifyTransactionController {
    @FXML
    Label fullSendingNameLabel;
    @FXML
    Label sendingAccountIDLabel;
    @FXML
    Label sendingBankLabel;
    @FXML
    Label fullReceiveNameLabel;
    @FXML
    Label receiveAccountIDLabel;
    @FXML
    Label receiveBankLabel;
    @FXML
    Label amountLabel;
    @FXML
    Label descriptionLabel;
    @FXML
    Label transactionTypeLabel;
    void displayTransactionInformation(Transaction newTransaction){
        fullSendingNameLabel.setText("Họ tên: " + newTransaction.getFromAccount().getFullName());
        sendingAccountIDLabel.setText("Mã tài khoản: " + newTransaction.getFromAccount().getAccountID());
        sendingBankLabel.setText("Ngân hàng: " + "21stBank");
        fullReceiveNameLabel.setText("Họ tên: " + newTransaction.getToAccount().getFullName());
        receiveAccountIDLabel.setText("Mã tài khoản: " + newTransaction.getToAccount().getAccountID());
        receiveBankLabel.setText("Ngân hàng: " + "21stBank");
        amountLabel.setText(Double.toString(newTransaction.getAmount()));
        descriptionLabel.setText(newTransaction.getDescription());
        transactionTypeLabel.setText(
                switch (newTransaction.getType()){
                    case TRANSFER -> "Chuyển khoản";
                    case DEPOSIT -> "Gửi tiền";
                    case WITHDRAW -> "Rút tiền";
                }
        );
    }
    @FXML
    void QuayLai(ActionEvent event) throws IOException {
        FXMLLoader previousSceneLoader = new FXMLLoader(SceneUtils.class.getResource("transacting_between_accounts.fxml"));
        Parent previousSceneRoot = previousSceneLoader.load();

        TransactingBetweenAccountsController controller = previousSceneLoader.getController();
        controller.loadTransaction(AccountManager.getInstance().getCurrentAccount(), TransactingBetweenAccountsController.newTransaction);

        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),previousSceneRoot);
    }
}
