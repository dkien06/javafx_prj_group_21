package com.example.javafx_app.controller.block;

import com.example.javafx_app.object.Account.LoanStatus;
import com.example.javafx_app.object.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class LoanHistoryBlockController {
    @FXML private Label icon;
    @FXML private Label toAccountName;
    @FXML private Label description;
    @FXML private Label amount;
    @FXML private Button button;
    @FXML private Label status;
    public void loadData(Transaction transaction, LoanStatus loanStatus, String reason){
        toAccountName.setText(transaction.getFromAccount().getAccountName());
        description.setText(transaction.getDescription());
        amount.setText(transaction.getAmount() + transaction.getCurrency());
        icon.getStyleClass().addAll("icon_container","chuyen_vao");
        button.getStyleClass().addAll("nut_chua_lsgd", "nut_xanh");
        switch (loanStatus){
            case REVIEW:
                status.setText("Đang duyệt");
                status.setTextFill(Color.rgb(192,192,0));
                break;
            case ACTIVE:
                status.setText("Duyệt thành công");
                status.setTextFill(Color.rgb(0,192,0));
                break;
            case OVERDUE:
                status.setText("Nợ quá hạn");
                status.setTextFill(Color.rgb(255,128,0));
                break;
            case EXTENDED:
                status.setText("Gia hạn thành công");
                status.setTextFill(Color.rgb(0,192,192));
                break;
            case REJECTED:
                status.setText("Đã từ chối. Lý do: " + reason);
                status.setTextFill(Color.rgb(0,192,192));
                break;
        }
    }
}
