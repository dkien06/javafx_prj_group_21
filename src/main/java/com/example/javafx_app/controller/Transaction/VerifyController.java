package com.example.javafx_app.controller.Transaction;

import com.example.javafx_app.convert.NumberToVietnameseWord;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.object.Account.CheckingAccount;
import com.example.javafx_app.object.Transaction;
import com.example.javafx_app.object.TransactionType;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.io.IOException;

import static com.example.javafx_app.config.Constant.mainStage;
import  com.example.javafx_app.object.TransactionType;

public class VerifyController {
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
    Label amountInTextLabel;
    @FXML
    Label descriptionLabel;
    @FXML
    Label transactionTypeLabel;
    @FXML
    PasswordField PINField;
    @FXML
    Text PINErrorLog;
    Account currentAccount = AccountManager.getInstance().getCurrentAccount();
    void displayTransactionInformation(Transaction newTransaction){
        fullSendingNameLabel.setText("Họ tên: " + newTransaction.getFromAccount().getAccountName());
        sendingAccountIDLabel.setText("Mã tài khoản: " + newTransaction.getFromAccount().getAccountID());
        sendingBankLabel.setText("Ngân hàng: " + "21stBank");
        fullReceiveNameLabel.setText("Họ tên: " + newTransaction.getToAccount().getAccountName());
        receiveAccountIDLabel.setText("Mã tài khoản: " + newTransaction.getToAccount().getAccountID());
        receiveBankLabel.setText("Ngân hàng: " + "21stBank");
        amountLabel.setText("Số tiền: " + newTransaction.getAmount() + " "+newTransaction.getCurrency());
        amountInTextLabel.setText(NumberToVietnameseWord.numberToVietnameseWords(newTransaction.getAmount()));
        descriptionLabel.setText(newTransaction.getDescription());
        transactionTypeLabel.setText(
                switch (newTransaction.getType()){
                    case TRANSFER -> "Chuyển khoản";
                    case DEPOSIT -> "Gửi tiền";
                    case WITHDRAW -> "Rút tiền";
                    case LOAN -> "Vay tiền";
                    case REPAY -> "Trả nợ";
                }
        );
    }
    @FXML
    void QuayLai(ActionEvent event) {
        Pair<Parent,TransactingController> scene = SceneUtils.getRootAndController("TransactionScene/transaction_scene.fxml");
        scene.getValue().loadTransaction(AccountManager.getInstance().getCurrentAccount(), TransactionManager.getInstance().getCurrentTransaction());
        TransactionManager.getInstance().getTransactionsList().remove
                (TransactionManager.getInstance().getCurrentTransaction());// xoa transaction hien tai di
        SceneUtils.switchScene(mainStage,scene.getKey());
    }
    @FXML
    void TiepTuc(ActionEvent event) throws IOException {
       String PIN = PINField.getText();
        if(PIN.isEmpty()){
            PINErrorLog.setText("Vui lòng nhập mã pin");
            return;
        }
        if(AccountManager.getInstance().getCurrentAccount().isPinMatched(PIN)){
            Transaction currentTransaction = TransactionManager.getInstance().getCurrentTransaction();
            Account fromAccount = currentTransaction.getFromAccount();
            Account toAccount = currentTransaction.getToAccount();
            long amount = currentTransaction.getAmount();
            String description = currentTransaction.getDescription();
            switch (currentTransaction.getType()) {
                case TransactionType.TRANSFER:
                    // Thực hiện hành động cho Chuyển khoản
                    fromAccount.transfer(toAccount, amount, description);
                    break;
                case TransactionType.DEPOSIT:
                    // Thực hiện hành động cho Nạp tiền
                    fromAccount.deposit(amount);
                    break;
                case TransactionType.WITHDRAW:
                    // Thực hiện hành động cho Rút tiền
                    fromAccount.withdraw(amount);
                    break;
                case TransactionType.LOAN:
                    // Thực hiện hành động cho Vay tiền
                    System.out.println("Đây là giao dịch Vay tiền.");
                    break;
                case TransactionType.REPAY:
                    // Thực hiện hành động cho Trả nợ
                    System.out.println("Đây là giao dịch Trả nợ.");
                    break;
                default:
                    break;
            }
            Pair<Parent, BillController> scene = SceneUtils.getRootAndController("TransactionScene/transaction_bill_scene.fxml");
            scene.getValue().loadTransaction();
            TransactionManager.getInstance().removeNewTransaction();

            SceneUtils.switchScene(mainStage,scene.getKey());
        }
        else {
            PINErrorLog.setText("Mã pin của bạn không chính xác");
        }
    }

}
