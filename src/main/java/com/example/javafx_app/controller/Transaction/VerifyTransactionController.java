package com.example.javafx_app.controller.Transaction;

import com.example.javafx_app.*;
import com.example.javafx_app.Manager.AccountManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;

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
    @FXML
    PasswordField PINField;
    @FXML
    Text PINErrorLog;
    void displayTransactionInformation(Transaction newTransaction){
        fullSendingNameLabel.setText("Họ tên: " + newTransaction.getFromAccount().getFullName());
        sendingAccountIDLabel.setText("Mã tài khoản: " + newTransaction.getFromAccount().getAccountID());
        sendingBankLabel.setText("Ngân hàng: " + "21stBank");
        fullReceiveNameLabel.setText("Họ tên: " + newTransaction.getToAccount().getFullName());
        receiveAccountIDLabel.setText("Mã tài khoản: " + newTransaction.getToAccount().getAccountID());
        receiveBankLabel.setText("Ngân hàng: " + "21stBank");
        amountLabel.setText(newTransaction.getAmount() + newTransaction.getCurrency());
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
        controller.loadTransaction(AccountManager.getInstance().getCurrentAccount(), BankApplication.TransactionManager.getInstance().getCurrentTransaction());

        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),previousSceneRoot);
    }
    @FXML
    void TiepTuc(ActionEvent event) throws IOException {
        String PIN = PINField.getText();
        if(PIN.isEmpty()){
            PINErrorLog.setText("Vui lòng nhập mã pin");
            return;
        }
        if(AccountManager.getInstance().getCurrentAccount().isPinMatched(PIN)){
            Transaction currentTransaction = BankApplication.TransactionManager.getInstance().getCurrentTransaction();
            AccountManager.getInstance().getCurrentAccount().transfer(
                    currentTransaction.getToAccount(),
                    currentTransaction.getAmount(),
                    currentTransaction.getDescription()
            );
            FXMLLoader nextSceneLoader = new FXMLLoader(SceneUtils.class.getResource("transaction_bill_scene.fxml"));
            Parent nextSceneRoot = nextSceneLoader.load();

            TransactionBillController controller = nextSceneLoader.getController();
            controller.loadTransaction(BankApplication.TransactionManager.getInstance().getCurrentTransaction());
            BankApplication.TransactionManager.getInstance().removeNewTransaction();

            SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),nextSceneRoot);
        }
        else{
            PINErrorLog.setText("Mã pin của bạn không chính xác");
        }
    }
}
