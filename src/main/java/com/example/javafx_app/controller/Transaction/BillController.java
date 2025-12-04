package com.example.javafx_app.controller.Transaction;

import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.object.User.User;
import com.example.javafx_app.manager.UserManager;
import com.example.javafx_app.util.SceneUtils;
import com.example.javafx_app.object.Transaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import static com.example.javafx_app.config.Constant.mainStage;

public class BillController {
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
    void loadTransaction(){
        Transaction cur = TransactionManager.getInstance().getCurrentTransaction();
        User user = UserManager.getInstance().findUserByCitizenID(cur.getToAccount().getCitizenID());
        amountLabel.setText(cur.getAmount() + " " + cur.getCurrency());
        dateLabel.setText(String.valueOf(cur.getDate()));
        toAccountFullNameLabel.setText("Người nhận: "+cur.getToAccount().getAccountName());
        toAccountIDLabel.setText("Số tài khoản: "+cur.getToAccount().getAccountID());
        toBankLabel.setText("21stBank");
    }
    @FXML
    void TrangChu(ActionEvent event){
        SceneUtils.switchScene(mainStage,"HomeScenes/checking_account_home_scene.fxml");
    }
    @FXML
    void ThucHienGiaoDichKhac(ActionEvent event){
        SceneUtils.switchScene(mainStage,"TransactionScene/transaction_scene.fxml");
    }
}
