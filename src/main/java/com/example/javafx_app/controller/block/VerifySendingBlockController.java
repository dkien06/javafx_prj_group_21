package com.example.javafx_app.controller.block;

import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.object.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class VerifySendingBlockController implements Initializable {
    @FXML Label icon_chuyen_tien;
    @FXML Label sendingLabel;
    @FXML Label fullSendingNameLabel;
    @FXML Label sendingAccountIDLabel;
    @FXML Label sendingBankLabel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        icon_chuyen_tien.setVisible(true);
        sendingLabel.setVisible(true);
        fullSendingNameLabel.setVisible(true);
        sendingAccountIDLabel.setVisible(true);
        sendingBankLabel.setVisible(true);
    }
    public void setData(Transaction transaction){
        icon_chuyen_tien.getStyleClass().removeAll();
        icon_chuyen_tien.getStyleClass().addAll("icon_chuyen_tien");
        switch (transaction.getType()){
            case TRANSFER:
                sendingLabel.setText("Người chuyển");
                fullSendingNameLabel.setText("Họ tên: " + transaction.getFromAccount().getAccountName());
                sendingAccountIDLabel.setText("Mã tài khoản: " + transaction.getFromAccount().getAccountID());
                sendingBankLabel.setText("Ngân hàng: " + "21stBank");
            case WITHDRAW:
                sendingLabel.setText("Người rút");
                fullSendingNameLabel.setText("Họ tên: " + transaction.getToAccount().getAccountName());
                sendingAccountIDLabel.setText("Mã tài khoản: " + transaction.getToAccount().getAccountID());
                sendingBankLabel.setText("Ngân hàng: " + "21stBank");
            default:
                icon_chuyen_tien.setVisible(false);
                sendingLabel.setVisible(false);
                fullSendingNameLabel.setVisible(false);
                sendingAccountIDLabel.setVisible(false);
                sendingBankLabel.setVisible(false);
        }
    }
}
