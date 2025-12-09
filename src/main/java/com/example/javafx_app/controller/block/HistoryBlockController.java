package com.example.javafx_app.controller.block;

import com.example.javafx_app.object.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HistoryBlockController {
    @FXML
    private Label icon;
    @FXML
    private Label toAccountName;
    @FXML
    private Label description;
    @FXML
    private Label amount;
    @FXML
    private Button button;

    public void setData(Transaction transaction){
        toAccountName.setText(transaction.getToAccount().getAccountName());
        description.setText(transaction.getDescription());
        switch (transaction.getType()){
            case TRANSFER:
                if(transaction.getAmount() > 0){
                    amount.setText(transaction.getAmount() + transaction.getFromAccount().getCurrency());
                    icon.getStyleClass().addAll("icon_container","chuyen_di");
                    button.getStyleClass().addAll("nut_chua_lsgd", "nut_do");
                }
                else{
                    amount.setText((-transaction.getAmount()) + transaction.getFromAccount().getCurrency());
                    icon.getStyleClass().addAll("icon_container","chuyen_vao");
                    button.getStyleClass().addAll("nut_chua_lsgd", "nut_xanh");
                }
            //Thêm icon rút tiền với nạp tiên đi
            case WITHDRAW:
                amount.setText((-transaction.getAmount()) + transaction.getFromAccount().getCurrency());
                icon.getStyleClass().addAll("icon_container","chuyen_di");
                button.getStyleClass().addAll("nut_chua_lsgd", "nut_do");
            case DEPOSIT:
                //Từ từ đã
            case LOAN:
                //Từ từ đã
            case REPAY:
                //Từ từ đã
        }

    }
}
