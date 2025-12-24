package com.example.javafx_app.controller;

import com.example.javafx_app.controller.Bill.BillButtonController;
import com.example.javafx_app.controller.checking.DepositController;
import com.example.javafx_app.controller.checking.TransactingController;
import com.example.javafx_app.controller.checking.WithdrawController;
import com.example.javafx_app.controller.block.VerifyReceiveBlockController;
import com.example.javafx_app.controller.block.VerifySendingBlockController;
import com.example.javafx_app.controller.saving.SavingController;
import com.example.javafx_app.convert.NumberToVietnameseWord;
import com.example.javafx_app.exception.MysteriousException;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.object.Account.CheckingAccount;
import com.example.javafx_app.object.Account.LoanAccount;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import static com.example.javafx_app.config.Constant.mainStage;

public class VerifyController {
    @FXML VBox VBox_Thong_Tin_Xac_Nhan;
    @FXML Label amountLabel;
    @FXML Label amountInTextLabel;
    @FXML Label descriptionLabel;
    @FXML Label transactionTypeLabel;
    @FXML PasswordField PINField;
    @FXML Text PINErrorLog;
    Account currentAccount = AccountManager.getInstance().getCurrentAccount();
    Transaction currentTransaction = TransactionManager.getInstance().getCurrentTransaction();
    public void displayTransactionInformation(Transaction newTransaction){
        Pair<Parent, VerifyReceiveBlockController> receiveBlock;
        Pair<Parent, VerifySendingBlockController> sendingBlock;
        switch (currentAccount) {
            case CheckingAccount checkingAccount -> {
                switch (currentTransaction.getType()) {
                    case TRANSFER, WITHDRAW:
                        sendingBlock = SceneUtils.getRootAndController("verify/verify_sending_block.fxml");
                        sendingBlock.getValue().setData(newTransaction);
                        VBox_Thong_Tin_Xac_Nhan.getChildren().add(sendingBlock.getKey());

                        receiveBlock = SceneUtils.getRootAndController("verify/verify_receive_block.fxml");
                        receiveBlock.getValue().setData(newTransaction);
                        VBox_Thong_Tin_Xac_Nhan.getChildren().add(receiveBlock.getKey());

                        break;
                    case DEPOSIT:
                        sendingBlock = SceneUtils.getRootAndController("verify/verify_sending_block.fxml");
                        sendingBlock.getValue().setData(newTransaction);
                        VBox_Thong_Tin_Xac_Nhan.getChildren().addFirst(sendingBlock.getKey());

                        receiveBlock = SceneUtils.getRootAndController("verify/verify_receive_block.fxml");
                        receiveBlock.getValue().setData(newTransaction);
                        VBox_Thong_Tin_Xac_Nhan.getChildren().addFirst(receiveBlock.getKey());

                        break;
                    default:
                        throw new MysteriousException();
                }
                switch (currentTransaction.getType()) {
                    case TRANSFER:
                        transactionTypeLabel.setText("Chuyển khoản");
                        break;
                    case DEPOSIT:
                        transactionTypeLabel.setText("Nạp tiền");
                        break;
                    case WITHDRAW:
                        transactionTypeLabel.setText("Rút tiền");
                        break;
                    default:
                        throw new MysteriousException();
                }
            }
            case SavingAccount savingAccount -> {
                switch (currentTransaction.getType()) {
                    case DEPOSIT:
                        sendingBlock = SceneUtils.getRootAndController("verify/verify_sending_block.fxml");
                        sendingBlock.getValue().setData(newTransaction);
                        VBox_Thong_Tin_Xac_Nhan.getChildren().addFirst(sendingBlock.getKey());

                        receiveBlock = SceneUtils.getRootAndController("verify/verify_receive_block.fxml");
                        receiveBlock.getValue().setData(newTransaction);
                        VBox_Thong_Tin_Xac_Nhan.getChildren().addFirst(receiveBlock.getKey());

                        break;
                    case WITHDRAW:
                        sendingBlock = SceneUtils.getRootAndController("verify/verify_sending_block.fxml");
                        sendingBlock.getValue().setData(newTransaction);
                        VBox_Thong_Tin_Xac_Nhan.getChildren().add(sendingBlock.getKey());

                        receiveBlock = SceneUtils.getRootAndController("verify/verify_receive_block.fxml");
                        receiveBlock.getValue().setData(newTransaction);
                        VBox_Thong_Tin_Xac_Nhan.getChildren().add(receiveBlock.getKey());

                        break;
                    default:
                        throw new MysteriousException();
                }
                switch (currentTransaction.getType()) {
                    case DEPOSIT:
                        switch (savingAccount.getType()) {
                            case FLEXIBLE:
                                transactionTypeLabel.setText("Gửi tiền - Linh hoạt");
                                break;
                            case FIXED:
                                transactionTypeLabel.setText("Gửi tiền - Kì hạn: " + savingAccount.getFixedDuration() + " tháng");
                                break;
                            case ACCUMULATED:
                                transactionTypeLabel.setText("Gửi tiền - Tích góp: " + savingAccount.getAccumulatedAmount() + currentAccount.getCurrency());
                                break;
                            default:
                                throw new MysteriousException();
                        }
                        break;
                    case WITHDRAW:
                        System.out.println(currentTransaction.getAmount()+" "+savingAccount.getSaving());
                        if (currentTransaction.getAmount() == savingAccount.getSaving())
                            transactionTypeLabel.setText("Rút tiền: Tất cả");
                        else
                            transactionTypeLabel.setText("Rút tiền: Một phần");
                        break;
                    default:
                        throw new MysteriousException();
                }
            }
            case LoanAccount loanAccount -> {
                switch (currentTransaction.getType()) {
                    case LOAN:
                        break;
                    case REPAY:
                        break;
                    default:
                        throw new MysteriousException();
                }
                switch (currentTransaction.getType()) {
                    case LOAN:
                        break;
                    case REPAY:
                        break;
                    default:
                        throw new MysteriousException();
                }
            }
            case null, default -> throw new MysteriousException();
        }
        amountLabel.setText("Số tiền: " + TransactionManager.getInstance().formatCurrency(
                newTransaction.getAmount(), newTransaction.getCurrency()));
        amountInTextLabel.setText(NumberToVietnameseWord.numberToVietnameseWords(newTransaction.getAmount()));
        descriptionLabel.setText(newTransaction.getDescription());
    }
    @FXML
    void QuayLai(ActionEvent event) {
        switch (currentAccount) {
            case CheckingAccount checkingAccount -> {
                switch (currentTransaction.getType()) {
                    case TRANSFER:
                        Pair<Parent, TransactingController> transactingScene = SceneUtils.getRootAndController("TransactionScene/transaction_scene.fxml");
                        transactingScene.getValue().loadTransaction(TransactionManager.getInstance().getCurrentTransaction());
                        TransactionManager.getInstance().removeNewTransaction();
                        SceneUtils.switchScene(mainStage, transactingScene.getKey());
                        break;
                    case DEPOSIT:
                        Pair<Parent, DepositController> depositScene = SceneUtils.getRootAndController("TransactionScene/deposit_scene.fxml");
                        depositScene.getValue().loadDeposit(TransactionManager.getInstance().getCurrentTransaction());
                        TransactionManager.getInstance().removeNewTransaction();
                        SceneUtils.switchScene(mainStage, depositScene.getKey());
                        break;
                    case WITHDRAW:
                        Pair<Parent, WithdrawController> withdrawScene = SceneUtils.getRootAndController("TransactionScene/withdraw_scene.fxml");
                        withdrawScene.getValue().loadWithdraw(TransactionManager.getInstance().getCurrentTransaction());
                        TransactionManager.getInstance().removeNewTransaction();
                        SceneUtils.switchScene(mainStage, withdrawScene.getKey());
                        break;
                    default:
                        throw new MysteriousException();
                }
            }
            case SavingAccount savingAccount -> {
                ((SavingAccount) AccountManager.getInstance().getCurrentAccount()).setFixedDuration(0);
                ((SavingAccount) AccountManager.getInstance().getCurrentAccount()).setAccumulatedAmount(0);
                Pair<Parent, SavingController> savingScene = SceneUtils.getRootAndController("SavingScene/saving_scene.fxml");
                savingScene.getValue().loadSaving(TransactionManager.getInstance().getCurrentTransaction());
                TransactionManager.getInstance().removeNewTransaction();
                SceneUtils.switchScene(mainStage, savingScene.getKey());
            }
            case LoanAccount loanAccount -> {
                //Hehe
            }
            case null, default -> throw new MysteriousException();
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
                SceneUtils.switchScene(mainStage,"verify_otp_transaction.fxml");
        }
        else {
            PINErrorLog.setText("Mã pin của bạn không chính xác");
        }
    }
    public static void complete(){
        Transaction currentTransaction = TransactionManager.getInstance().getCurrentTransaction();
        Account fromAccount = currentTransaction.getFromAccount();
        Account toAccount = currentTransaction.getToAccount();
        long amount = currentTransaction.getAmount();
        String description = currentTransaction.getDescription();
        switch (AccountManager.getInstance().getCurrentAccount()) {
            case CheckingAccount checkingAccount -> {
                switch (currentTransaction.getType()) {
                    case TRANSFER:
                        // Thực hiện hành động cho Chuyển khoản
                        ((CheckingAccount) fromAccount).transfer((CheckingAccount) toAccount, amount, description);
                        break;
                    case DEPOSIT:
                        ((CheckingAccount) toAccount).deposit(amount, description);
                        break;
                    case WITHDRAW:
                        ((CheckingAccount) fromAccount).withdraw(amount, description);
                        break;
                    default:
                        throw new MysteriousException();
                }
            }
            case SavingAccount savingAccount -> {
                switch (currentTransaction.getType()) {
                    case TransactionType.DEPOSIT:
                        // Thực hiện hành động cho Nạp tiền
                        ((SavingAccount) toAccount).deposit(AccountManager.getInstance().findCheckingAccount(toAccount), amount, description);
                        break;
                    case TransactionType.WITHDRAW:
                        // Thực hiện hành động cho Rút tiền
                        ((SavingAccount) fromAccount).withdraw((CheckingAccount) toAccount, amount, description);
                        break;
                    default:
                        throw new MysteriousException();
                }
            }
            case LoanAccount loanAccount -> {
                switch ((currentTransaction.getType())) {
                    case LOAN:
                        ((LoanAccount) toAccount).loan(AccountManager.getInstance().findCheckingAccount(toAccount), amount);
                        break;
                    case REPAY:
                        ((LoanAccount) fromAccount).repay(AccountManager.getInstance().findCheckingAccount(fromAccount), amount);
                        break;
                    default:
                        throw new MysteriousException();
                }
            }
            case null, default -> throw new MysteriousException();
        }
        Pair<Parent, CompletedController> scene = SceneUtils.getRootAndController("completed_scene.fxml");
        scene.getValue().loadTransaction();
        // Thêm notification
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
