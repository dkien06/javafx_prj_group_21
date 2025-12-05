package com.example.javafx_app.controller.Transaction;

import com.example.javafx_app.controller.Bill.BillButtonController;
import com.example.javafx_app.convert.NumberToVietnameseWord;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.object.Account.CheckingAccount;
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
    Transaction currentTransaction = TransactionManager.getInstance().getCurrentTransaction();
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
            // Them notification
            if(currentTransaction.getType().equals(TransactionType.TRANSFER)){
                toAccount.addNotification(new Notification(NotificationType.BALANCE_CHANGE,
                        NotificationType.BALANCE_CHANGE.toString(), generateInboundNotification(currentTransaction)));
            }
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
    public static String generateInboundNotification(Transaction transaction) {
        if (transaction == null) {
            return "Không có chi tiết giao dịch.";
        }

        long amount = transaction.getAmount();
        String currency = transaction.getCurrency();

        // Định dạng số tiền theo chuẩn Việt Nam (ví dụ: 100.000 VND)
        NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("vi", "VN"));
        String formattedAmount = numberFormat.format(amount) + " " + currency;

        Account fromAcc = transaction.getFromAccount();
        Account toAcc = transaction.getToAccount();
        String description = transaction.getDescription();
        String accountTypeLabel = toAcc != null ? toAcc.getAccountType().toString() : "tài khoản"; // Dùng tên hiển thị của loại tài khoản

        switch (transaction.getType()) {
            case TRANSFER:
                // Nhận tiền từ tài khoản khác (Inbound)
                String senderName = fromAcc != null ? fromAcc.getAccountName() : "Tài khoản ẩn danh";
                String senderID = fromAcc != null ? fromAcc.getAccountID() : "Không rõ";

                return String.format(
                        "Tài khoản của bạn đã nhận %s từ tài khoản %s (STK: %s).\n Nội dung: %s",
                        formattedAmount,
                        senderName,
                        senderID,
                        description.isEmpty() ? "Không có nội dung" : description
                );

            case DEPOSIT:
                // Gửi tiền vào tài khoản (từ ATM/quầy giao dịch)
                return String.format(
                        "Bạn đã nạp %s vào %s của mình (STK: %s).",
                        formattedAmount,
                        accountTypeLabel,
                        toAcc != null ? toAcc.getAccountID() : "Không rõ"
                );

            case LOAN:
                // Khoản vay được giải ngân
                return String.format(
                        "Khoản vay %s đã được giải ngân vào %s của bạn (STK: %s).",
                        formattedAmount,
                        accountTypeLabel,
                        toAcc != null ? toAcc.getAccountID() : "Không rõ"
                );

            case REPAY:
                // Giao dịch trả nợ (thông báo xác nhận)
                return String.format(
                        "Bạn đã thanh toán %s cho khoản nợ/vay thành công.",
                        formattedAmount
                );

            case WITHDRAW:
            default:
                // Giao dịch rút tiền (WITHDRAW) thường là outbound,
                // nhưng nếu nó là một loại transaction khác không xác định
                return String.format(
                        "Đã xảy ra giao dịch loại %s với số tiền %s.",
                        transaction.getType().toString(),
                        formattedAmount
                );
        }
    }

}
