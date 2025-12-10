package com.example.javafx_app.controller.checking;

import com.example.javafx_app.convert.NumberToVietnameseWord;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.object.Account.CheckingAccount;
import com.example.javafx_app.object.Transaction;
import com.example.javafx_app.object.TransactionType;
import com.example.javafx_app.util.SceneUtils;
import com.example.javafx_app.controller.VerifyController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.javafx_app.config.Constant.mainStage;

/**
 * "Nạp tiền" ở checkingAccount là chuyển tiền từ savingAccount hoặc loanAccount sang hay sao đấy?
 */
public class DepositController implements Initializable {
    @FXML
    private TextField amountTextField;
    @FXML
    private Text amountLog;
    @FXML
    private TextField descriptionTextArea;

    private final CheckingAccount CurrentAccount = (CheckingAccount) AccountManager.getInstance().getCurrentAccount();
    private boolean isAmountValid = false;

    public void loadDeposit(Transaction transaction){
        amountTextField.setText("" + transaction.getAmount());
        descriptionTextArea.setText(transaction.getDescription());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Listener kiểm tra số tiền nhập vào
        descriptionTextArea.setText(CurrentAccount.getAccountName().toUpperCase() + " nap tien");
        amountTextField.textProperty().addListener((observable, _, value) -> {
            amountLog.setText(NumberToVietnameseWord.displayError(value));
            if(!amountLog.getText().isEmpty()){
                amountLog.setFill(Color.rgb(255,0,0));
                isAmountValid = false;
            }
            else{
                amountLog.setText(NumberToVietnameseWord.numberToVietnameseWords(Long.parseLong(value)));
                amountLog.setFill(Color.rgb(255,255,255));
                isAmountValid = true;
            }
        });
    }

    @FXML
    void QuayLai(ActionEvent event) {
        // Quay về màn hình chính của tài khoản thanh toán
        SceneUtils.switchScene(mainStage, "TransactionScene/transaction_options_scene.fxml");

    }

    @FXML
    void TiepTuc(ActionEvent event) throws IOException {
        amountLog.setText("");
        if (amountTextField.getText().isEmpty()) {
            amountLog.setText("Vui lòng nhập số tiền nạp");
            amountLog.setFill(Color.RED);
            return;
        }

        if (isAmountValid) {
            // Tạo TransactionType.DEPOSIT. Sử dụng CurrentAccount cho cả from và to
            TransactionManager.getInstance().newTransaction(
                    TransactionType.DEPOSIT,
                    Long.parseLong(amountTextField.getText()),
                    "VND",
                    CurrentAccount,
                    CurrentAccount, // Tới tài khoản hiện tại
                     descriptionTextArea.getText()
            );
            Pair<Parent, VerifyController> scene = SceneUtils.getRootAndController("verify/verify_scene.fxml");
            scene.getValue().displayTransactionInformation(TransactionManager.getInstance().getCurrentTransaction());
            SceneUtils.switchScene(mainStage, scene.getKey());
        }
    }
}