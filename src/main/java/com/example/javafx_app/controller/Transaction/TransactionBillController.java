package com.example.javafx_app.controller.Transaction;

import com.example.javafx_app.object.User.User;
import com.example.javafx_app.manager.UserManager;
import com.example.javafx_app.util.SceneUtils;
import com.example.javafx_app.object.Transaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class TransactionBillController {
    @FXML
    private Label amountLabel;
    @FXML
    private Text dateLabel;
    @FXML
    private Label toAccountFullNameLabel;
    @FXML
    private Label toAccountIDLabel;
    @FXML
    private Label toBankLabel;
    void loadTransaction(Transaction transaction){
        User user = UserManager.getInstance().findUserFromAccount(transaction.getToAccount());
        amountLabel.setText(transaction.getAmount() + " " + transaction.getCurrency());
        dateLabel.setText(String.valueOf(transaction.getDate()));
        toAccountFullNameLabel.setText(user.getFullName());
        toAccountIDLabel.setText(transaction.getToAccount().getAccountID());
        toBankLabel.setText("21stBank");
    }
    @FXML
    void TrangChu(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"HomeScenes/checking_account_home_scene.fxml");
    }
    @FXML
    void ThucHienGiaoDichKhac(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"TransactionScene/transaction_choose_account_scene.fxml");
    }
}
