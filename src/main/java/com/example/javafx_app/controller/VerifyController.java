package com.example.javafx_app.controller;

import com.example.javafx_app.controller.Bill.BillButtonController;
import com.example.javafx_app.controller.Transaction.TransactingController;
import com.example.javafx_app.controller.saving.SavingController;
import com.example.javafx_app.convert.NumberToVietnameseWord;
import com.example.javafx_app.exception.CodeUnderConstruction;
import com.example.javafx_app.exception.MysteriousException;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.object.Account.CheckingAccount;
import com.example.javafx_app.object.Account.SavingAccount;
import com.example.javafx_app.object.Noti.Notification;
import com.example.javafx_app.object.Noti.NotificationType;
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
import java.text.NumberFormat;
import java.util.Locale;

import static com.example.javafx_app.config.Constant.mainStage;

public class VerifyController {
    @FXML Label icon_chuyen_tien;
    @FXML Label fullSendingNameLabel;
    @FXML Label sendingAccountIDLabel;
    @FXML Label sendingBankLabel;
    @FXML Label icon_nhan_tien;
    @FXML Label receiveLabel;
    @FXML Label fullReceiveNameLabel;
    @FXML Label receiveAccountIDLabel;
    @FXML Label receiveBankLabel;
    @FXML Label amountLabel;
    @FXML Label amountInTextLabel;
    @FXML Label descriptionLabel;
    @FXML Label transactionTypeLabel;
    @FXML PasswordField PINField;
    @FXML Text PINErrorLog;
    Account currentAccount = AccountManager.getInstance().getCurrentAccount();
    Transaction currentTransaction = TransactionManager.getInstance().getCurrentTransaction();
    public void displayTransactionInformation(Transaction newTransaction){
        switch (currentTransaction.getType()){
            case TRANSFER:
                icon_chuyen_tien.getStyleClass().removeAll();
                icon_chuyen_tien.getStyleClass().addAll("icon_container");
                fullSendingNameLabel.setText("Họ tên: " + newTransaction.getFromAccount().getAccountName());
                sendingAccountIDLabel.setText("Mã tài khoản: " + newTransaction.getFromAccount().getAccountID());
                sendingBankLabel.setText("Ngân hàng: " + "21stBank");

                icon_nhan_tien.setVisible(true);
                icon_nhan_tien.getStyleClass().removeAll();
                icon_nhan_tien.getStyleClass().addAll("icon_container");
                receiveLabel.setVisible(true);
                fullReceiveNameLabel.setVisible(true);
                fullReceiveNameLabel.setText("Họ tên: " + newTransaction.getToAccount().getAccountName());
                receiveAccountIDLabel.setVisible(true);
                receiveAccountIDLabel.setText("Mã tài khoản: " + newTransaction.getToAccount().getAccountID());
                receiveBankLabel.setVisible(true);
                receiveBankLabel.setText("Ngân hàng: " + "21stBank");

                transactionTypeLabel.setText("Chuyển khoản");
                break;
            case DEPOSIT:
                icon_chuyen_tien.getStyleClass().removeAll();
                icon_chuyen_tien.getStyleClass().addAll("icon_container");
                fullSendingNameLabel.setText("Họ tên: " + newTransaction.getFromAccount().getAccountName());
                sendingAccountIDLabel.setText("Mã tài khoản: " + newTransaction.getFromAccount().getAccountID());
                sendingBankLabel.setText("Ngân hàng: " + "21stBank");

                receiveLabel.setVisible(false);
                icon_nhan_tien.setVisible(false);
                fullReceiveNameLabel.setVisible(false);
                receiveAccountIDLabel.setVisible(false);
                receiveBankLabel.setVisible(false);

                if(currentAccount instanceof CheckingAccount){
                    transactionTypeLabel.setText("Nạp tiền");
                    break;
                }
                else if(currentAccount instanceof SavingAccount){
                    switch (((SavingAccount)currentAccount).getType()){
                        case FLEXIBLE:
                            transactionTypeLabel.setText("Gửi tiền - Linh hoạt");
                            break;
                        case FIXED:
                            transactionTypeLabel.setText("Gửi tiền - Kì hạn: " + ((SavingAccount)currentAccount).getFixedDuration() + " tháng");
                            break;
                        case ACCUMULATED:
                            transactionTypeLabel.setText("Gửi tiền - Tích góp: " + ((SavingAccount)currentAccount).getAccumulatedAmount() + currentAccount.getCurrency());
                            break;
                        default:
                            throw new MysteriousException();
                    }
                    break;
                }
            case WITHDRAW:
                icon_chuyen_tien.getStyleClass().removeAll();
                icon_chuyen_tien.getStyleClass().addAll("icon_container");
                fullSendingNameLabel.setText("Họ tên: " + newTransaction.getFromAccount().getAccountName());
                sendingAccountIDLabel.setText("Mã tài khoản: " + newTransaction.getFromAccount().getAccountID());
                sendingBankLabel.setText("Ngân hàng: " + "21stBank");

                receiveLabel.setVisible(false);
                icon_nhan_tien.setVisible(false);
                fullReceiveNameLabel.setVisible(false);
                receiveAccountIDLabel.setVisible(false);
                receiveBankLabel.setVisible(false);

                if(currentAccount instanceof CheckingAccount){
                    transactionTypeLabel.setText("Rút tiền");
                    break;
                }
                break;
            default:
                CodeUnderConstruction.throwException();
                break;
        }
        amountLabel.setText("Số tiền: " + TransactionManager.getInstance().formatCurrency(
                newTransaction.getAmount(), newTransaction.getCurrency()));
        amountInTextLabel.setText(NumberToVietnameseWord.numberToVietnameseWords(newTransaction.getAmount()));
        descriptionLabel.setText(newTransaction.getDescription());
    }
    @FXML
    void QuayLai(ActionEvent event) {
        switch (TransactionManager.getInstance().getCurrentTransaction().getType()){
            case TRANSFER:
                Pair<Parent, TransactingController> transactingScene = SceneUtils.getRootAndController("TransactionScene/transaction_scene.fxml");
                transactingScene.getValue().loadTransaction(TransactionManager.getInstance().getCurrentTransaction());
                TransactionManager.getInstance().removeNewTransaction();
                SceneUtils.switchScene(mainStage,transactingScene.getKey());
                break;
            case DEPOSIT, WITHDRAW:
                if(currentAccount instanceof CheckingAccount){
                    Pair<Parent, TransactingController> savingScene = SceneUtils.getRootAndController("TransactionScene/transaction_scene.fxml");
                    savingScene.getValue().loadTransaction(TransactionManager.getInstance().getCurrentTransaction());
                    TransactionManager.getInstance().removeNewTransaction();
                    SceneUtils.switchScene(mainStage,savingScene.getKey());
                }
                if(currentAccount instanceof SavingAccount){
                    Pair<Parent, SavingController> savingScene = SceneUtils.getRootAndController("SavingScene/saving_scene.fxml");
                    savingScene.getValue().loadSaving(TransactionManager.getInstance().getCurrentTransaction());
                    TransactionManager.getInstance().removeNewTransaction();
                    SceneUtils.switchScene(mainStage,savingScene.getKey());
                }
                break;
            default:
                CodeUnderConstruction.throwException();
                break;
        }
    }
    @FXML
    void TiepTuc(ActionEvent event) throws IOException {
        String PIN = PINField.getText();
        if(PIN.isEmpty()){
            PINErrorLog.setText("Vui lòng nhập mã pin");
            return;
        }
        System.out.println(PINField.getText());
        if(AccountManager.getInstance().getCurrentAccount().isPinMatched(PIN)){
            Transaction currentTransaction = TransactionManager.getInstance().getCurrentTransaction();
            Account fromAccount = currentTransaction.getFromAccount();
            Account toAccount = currentTransaction.getToAccount();
            long amount = currentTransaction.getAmount();
            String description = currentTransaction.getDescription();
            if(currentAccount instanceof CheckingAccount){
                switch (currentTransaction.getType()){
                    case TRANSFER:
                        // Thực hiện hành động cho Chuyển khoản
                        ((CheckingAccount)fromAccount).transfer((CheckingAccount) toAccount, amount, description);
                        break;
                    case DEPOSIT:
                        ((CheckingAccount)toAccount).deposit(amount,description);
                        break;
                    case WITHDRAW:
                        ((CheckingAccount)fromAccount).withdraw(amount,description);
                        break;
                }
            }
            else if(currentAccount instanceof SavingAccount){
                switch (currentTransaction.getType()) {
                    case TransactionType.DEPOSIT:
                        // Thực hiện hành động cho Nạp tiền
                        ((SavingAccount)fromAccount).deposit(AccountManager.getInstance().findCheckingAccount(fromAccount), amount, description);
                        break;
                    case TransactionType.WITHDRAW:
                        // Thực hiện hành động cho Rút tiền
                        ((SavingAccount)fromAccount).withdraw((CheckingAccount) toAccount, amount);
                        break;
                    default:
                        throw new MysteriousException();
                }
            }
            Pair<Parent, CompletedController> scene = SceneUtils.getRootAndController("TransactionScene/transaction_bill_scene.fxml");
            scene.getValue().loadTransaction();
            TransactionManager.getInstance().removeNewTransaction();
            // xoa bill
            if(BillButtonController.isBillPayment){
                ((CheckingAccount) AccountManager.getInstance().getCurrentAccount()).removeBill(BillButtonController.bill);
                // tra lai bien cho bill
                BillButtonController.isBillPayment=false;
                BillButtonController.bill=null ;
            }
            SceneUtils.switchScene(mainStage,scene.getKey());
        }
        else {
            PINErrorLog.setText("Mã pin của bạn không chính xác");
        }
    }


}
