package com.example.javafx_app.controller.Transaction;

import com.example.javafx_app.BankApplication;
import com.example.javafx_app.block.TransactionHistoryBlockController;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.object.Account.CheckingAccount;
import com.example.javafx_app.object.Transaction;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.javafx_app.config.Constant.mainStage;

public class TransactionHistorySceneController {
    @FXML
    private Label name;

    @FXML
    private Label balance;

    @FXML
    private Label accountID;

    @FXML
    private Button return_btn;

    @FXML
    private ScrollPane rootPane;

    @FXML
    private VBox vbox_lich_su_giao_dich;

    @FXML
    private VBox vbox_so_du_sotaikhoan;

    @FXML
    private VBox vbox_to;

    public void initialize() throws IOException {
        name.setText("Họ và tên: " + AccountManager.getInstance().getCurrentAccount().getAccountName());
        balance.setText("Số dư: " + ((CheckingAccount)AccountManager.getInstance().getCurrentAccount()).getBalance()
                               + AccountManager.getInstance().getCurrentAccount().getCurrency());
        accountID.setText("Số tài khoản: " + AccountManager.getInstance().getCurrentAccount().getAccountID());
        for(Transaction t : (AccountManager.getInstance().getCurrentAccount()).getHistory()){
            FXMLLoader loader = new FXMLLoader(BankApplication.class.getResource("block/transaction_history_block.fxml"));
            Parent block = loader.load();

            TransactionHistoryBlockController blockController = loader.getController();
            blockController.setData(t);

            vbox_lich_su_giao_dich.getChildren().add(block);
        }
    }
    @FXML
    void QuayLai(ActionEvent event) {
        SceneUtils.switchScene(mainStage, AccountManager.getInstance().chooseHomeScene(
                AccountManager.getInstance().getCurrentAccount()));
    }
}
