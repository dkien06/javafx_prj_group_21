package com.example.javafx_app.controller.loan;

import com.example.javafx_app.controller.verify.VerifyController;
import com.example.javafx_app.convert.NumberToVietnameseWord;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.object.Account.LoanAccount;
import com.example.javafx_app.object.Transaction;
import com.example.javafx_app.object.TransactionType;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.javafx_app.config.Constant.mainStage;

public class RepaySceneController implements Initializable {
    @FXML private TextField receiveAccountIDTextField;
    @FXML private TextField amountTextField;
    @FXML private TextField descriptionTextArea;
    @FXML private Text amountLog;
    private boolean isAmountValid = false;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        receiveAccountIDTextField.setText(AccountManager.getInstance().getCurrentAccount().getAccountID());
        receiveAccountIDTextField.setEditable(false);
        descriptionTextArea.setText(AccountManager.getInstance().getCurrentAccount().getAccountName().toUpperCase() + " tra no");
        amountTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            String error = NumberToVietnameseWord.displayErrorForLoan(newValue, (LoanAccount) AccountManager.getInstance().getCurrentAccount());
            if (!error.isEmpty()) {
                amountLog.setText(error);
                amountLog.setFill(Color.RED);
                isAmountValid = false;
            } else {
                try {
                    long amount = Long.parseLong(newValue);
                    amountLog.setText(NumberToVietnameseWord.numberToVietnameseWords(amount));
                    amountLog.setFill(Color.BLACK);
                    isAmountValid = true;
                } catch (NumberFormatException e) {
                    amountLog.setText("Số tiền không hợp lệ");
                    amountLog.setFill(Color.RED);
                    isAmountValid = false;
                }
            }
        });
    }
    @FXML
    void QuayLai(ActionEvent event){
        SceneUtils.switchScene(mainStage, "HomeScenes/loan_account_home_scene.fxml");
    }
    @FXML
    void TiepTuc(ActionEvent event){
        if(amountTextField.getText().isEmpty()){
            amountLog.setText("Vui lòng nhập số tiền");
            amountLog.setFill(Color.RED);
            isAmountValid = false;
        }
        if(isAmountValid){
            TransactionManager.getInstance().newTransaction(
                    TransactionType.REPAY,
                    Long.parseLong(amountTextField.getText()),
                    AccountManager.getInstance().getCurrentAccount().getCurrency(),
                    AccountManager.getInstance().findCheckingAccount(AccountManager.getInstance().getCurrentAccount()),
                    AccountManager.getInstance().getCurrentAccount(),
                    descriptionTextArea.getText()
            );
            Pair<Parent, VerifyController> scene = SceneUtils.getRootAndController("verify/verify_scene.fxml");
            scene.getValue().displayTransactionInformation(TransactionManager.getInstance().getCurrentTransaction());
            SceneUtils.switchScene(mainStage, scene.getKey());
        }
    }
    @FXML
    void TraCa(ActionEvent event){
        amountTextField.setText(((LoanAccount) AccountManager.getInstance().getCurrentAccount()).getDebt() + "");
        amountLog.setText(NumberToVietnameseWord.numberToVietnameseWords(((LoanAccount) AccountManager.getInstance().getCurrentAccount()).getDebt()));
    }
}
