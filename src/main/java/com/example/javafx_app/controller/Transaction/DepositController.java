package com.example.javafx_app.controller.Transaction;

import com.example.javafx_app.BankApplication;
import com.example.javafx_app.convert.NumberToVietnameseWord;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.object.Account.CheckingAccount;
import com.example.javafx_app.object.TransactionType;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Listener kiểm tra số tiền nhập vào
        amountTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (!newValue.isEmpty() && newValue.matches("\\d+")) {
                    long amount = Long.parseLong(newValue);
                    if (amount <= 0) {
                        amountLog.setText("Số tiền phải lớn hơn 0");
                        amountLog.setFill(Color.RED);
                        isAmountValid = false;
                    } else {
                        String amountInWords = NumberToVietnameseWord.numberToVietnameseWords(amount);
                        amountLog.setText(amountInWords);
                        amountLog.setFill(Color.BLACK);
                        isAmountValid = true;
                    }
                } else {
                    if (newValue.isEmpty()) amountLog.setText("Vui lòng nhập số tiền");
                    else amountLog.setText("Số tiền không hợp lệ");
                    amountLog.setFill(Color.RED);
                    isAmountValid = false;
                }
            } catch (NumberFormatException e) {
                amountLog.setText("Số tiền không hợp lệ");
                amountLog.setFill(Color.RED);
                isAmountValid = false;
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
                    CurrentAccount, // Từ đâu đó (ngân hàng)
                    CurrentAccount, // Tới tài khoản hiện tại
                     descriptionTextArea.getText()
            );

            try {
                FXMLLoader nextSceneLoader = new FXMLLoader(BankApplication.class.getResource("TransactionScene/verify_transaction.scene.fxml"));
                Parent nextSceneRoot = nextSceneLoader.load();

                VerifyController controller = nextSceneLoader.getController();
                controller.displayTransactionInformation(TransactionManager.getInstance().getCurrentTransaction());

                SceneUtils.switchScene(mainStage, nextSceneRoot);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}