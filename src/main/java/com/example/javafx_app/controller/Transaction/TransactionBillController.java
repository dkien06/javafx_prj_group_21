package com.example.javafx_app.controller.Transaction;

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
        amountLabel.setText(transaction.getAmount() + " " + transaction.getCurrency());
        dateLabel.setText(String.valueOf(transaction.getDate()));
        toAccountFullNameLabel.setText(transaction.getToAccount().getFullName());
        toAccountIDLabel.setText(transaction.getToAccount().getAccountID());
        toBankLabel.setText("21stBank");
    }
    @FXML
    void TrangChu(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"home_scene.fxml");
    }
    @FXML
    void ThucHienGiaoDichKhac(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"transaction_choose_method_scene.fxml");
    }
}
