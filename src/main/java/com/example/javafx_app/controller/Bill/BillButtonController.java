package com.example.javafx_app.controller.Bill;

import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.object.Account.CheckingAccount;
import com.example.javafx_app.object.Bill.Bill;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.time.LocalDate;

import static com.example.javafx_app.config.Constant.mainStage;

public class BillButtonController {
    @FXML
    private Label Supplier;
    @FXML
    private Label Date;
    @FXML
    private Label Amount;
    // cac biến sử dụng cho transaction
    public static boolean isBillPayment = false;
    public static Bill bill ;
    public void setData(String supplier, String date, long amount) {
        Supplier.setText(supplier);
        Date.setText(date);
        Amount.setText(amount+"");
    }
    public void GoToPayScene(ActionEvent event) {
        bill = AccountManager.getInstance().findBillFromAccount(
                (CheckingAccount) AccountManager.getInstance().getCurrentAccount()
                ,Amount.getText(),Date.getText(),Supplier.getText());
        isBillPayment = true;
        SceneUtils.switchScene(mainStage,"TransactionScene/transaction_scene.fxml");
    }
}

