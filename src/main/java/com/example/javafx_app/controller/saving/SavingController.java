package com.example.javafx_app.controller.saving;

import com.example.javafx_app.convert.NumberToVietnameseWord;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.object.Account.SavingAccount;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import static com.example.javafx_app.config.Constant.mainStage;

public class SavingController {
    @FXML private TextField savingAccountID;
    @FXML private TextField savingMethod;
    @FXML private TextField amount;
    @FXML private Text amountLog;
    @FXML private TextField duration;
    @FXML private TextArea description;
    boolean isAmountValid = false;
    void loadInfo(String type, double interestRate){
        savingAccountID.setText(AccountManager.getInstance().getCurrentAccount().getAccountID());
        savingAccountID.setEditable(false);
        savingMethod.setText(type + " - " + interestRate + "%/năm");
        savingMethod.setEditable(false);
        amount.textProperty().addListener((observable, _, value) -> {
            try {
                if (!value.isEmpty() && value.matches("\\d+")) {
                    double amount = Double.parseDouble(value);
                    if(amount > AccountManager.getInstance().findCheckingAccount((SavingAccount)(AccountManager.getInstance().getCurrentAccount())).getBalance()){
                        amountLog.setText("Số tiền bạn nhập không đủ để chuyển");
                        amountLog.setFill(Color.rgb(255, 0, 0));
                        isAmountValid = false;
                    }
                    else{
                        String amountInWords = NumberToVietnameseWord.numberToVietnameseWords(amount);
                        amountLog.setText(amountInWords);
                        amountLog.setFill(Color.rgb(0,0,0));
                        isAmountValid = true;
                    }
                } else {
                    if(value.isEmpty())amountLog.setText("Vui lòng nhập số tiền");
                    else amountLog.setText("Số tiền không hợp lệ");
                    amountLog.setFill(Color.rgb(255, 0, 0));
                    isAmountValid = false;
                }
            } catch (NumberFormatException e) {
                amountLog.setText("Số tiền không hợp lệ");
                amountLog.setFill(Color.rgb(255, 0, 0));
                isAmountValid = false;
            }
        });
    }
    @FXML
    void QuayLai(ActionEvent event){
        SceneUtils.switchScene(mainStage, "SavingScene/saving_choose_method_scene.fxml");
    }

}
