package com.example.javafx_app.controller.Transaction;

import com.example.javafx_app.BankApplication;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.object.Transaction;
import com.example.javafx_app.util.SceneUtils;
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
        fullSendingNameLabel.setText("Họ tên: " + newTransaction.getFromAccount().getAccountName());
        sendingAccountIDLabel.setText("Mã tài khoản: " + newTransaction.getFromAccount().getAccountID());
        sendingBankLabel.setText("Ngân hàng: " + "21stBank");
        fullReceiveNameLabel.setText("Họ tên: " + newTransaction.getToAccount().getAccountName());
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
        FXMLLoader previousSceneLoader = new FXMLLoader(BankApplication.class.getResource("transacting_between_accounts.fxml"));
        Parent previousSceneRoot = previousSceneLoader.load();

        TransactingBetweenAccountsController controller = previousSceneLoader.getController();
        controller.loadTransaction(AccountManager.getInstance().getCurrentAccount(), TransactionManager.getInstance().getCurrentTransaction());

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
            AccountManager.getInstance().getCurrentAccount().getCheckingAccount().transfer(
                    TransactionManager.getInstance().getCurrentTransaction().getToAccount().getCheckingAccount(),
                    TransactionManager.getInstance().getCurrentTransaction().getAmount(),
                    TransactionManager.getInstance().getCurrentTransaction().getDescription()
            );
            FXMLLoader nextSceneLoader = new FXMLLoader(BankApplication.class.getResource("transaction_bill_scene.fxml"));
            Parent nextSceneRoot = nextSceneLoader.load();

            TransactionBillController controller = nextSceneLoader.getController();
            controller.loadTransaction(TransactionManager.getInstance().getCurrentTransaction());
            TransactionManager.getInstance().removeNewTransaction();

            SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),nextSceneRoot);
        }
        else{
            PINErrorLog.setText("Mã pin của bạn không chính xác");
        }
    }
}
