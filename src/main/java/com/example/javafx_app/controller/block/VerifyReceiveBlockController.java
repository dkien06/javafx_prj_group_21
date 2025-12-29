package com.example.javafx_app.controller.block;

import com.example.javafx_app.object.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class VerifyReceiveBlockController implements Initializable {
    @FXML Label icon_nhan_tien;
    @FXML Label receiveLabel;
    @FXML Label fullReceiveNameLabel;
    @FXML Label receiveAccountIDLabel;
    @FXML Label receiveBankLabel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        icon_nhan_tien.setVisible(true);
        receiveLabel.setVisible(true);
        fullReceiveNameLabel.setVisible(true);
        receiveAccountIDLabel.setVisible(true);
        receiveBankLabel.setVisible(true);
    }
    public void setData(Transaction transaction){
        icon_nhan_tien.getStyleClass().removeAll();
        icon_nhan_tien.getStyleClass().addAll("icon_nhan_tien");
        switch (transaction.getType()){
            case TRANSFER:
                receiveLabel.setText("Người nhận");
                fullReceiveNameLabel.setText("Họ tên: " + transaction.getToAccount().getAccountName());
                receiveAccountIDLabel.setText("Mã tài khoản: " + transaction.getToAccount().getAccountID());
                receiveBankLabel.setText("Ngân hàng: " + "21stBank");
                break;
            case DEPOSIT:
                receiveLabel.setText("Người gửi");
                fullReceiveNameLabel.setText("Họ tên: " + transaction.getFromAccount().getAccountName());
                receiveAccountIDLabel.setText("Mã tài khoản: " + transaction.getFromAccount().getAccountID());
                receiveBankLabel.setText("Ngân hàng: " + "21stBank");
                break;
            case LOAN:
                receiveLabel.setText("Người vay");
                fullReceiveNameLabel.setText("Họ tên: " + transaction.getFromAccount().getAccountName());
                receiveAccountIDLabel.setText("Mã tài khoản: " + transaction.getFromAccount().getAccountID());
                receiveBankLabel.setText("Ngân hàng: " + "21stBank");
                break;
            default:
                icon_nhan_tien.setVisible(false);
                receiveLabel.setVisible(false);
                fullReceiveNameLabel.setVisible(false);
                receiveAccountIDLabel.setVisible(false);
                receiveBankLabel.setVisible(false);
                break;
        }
    }
}
