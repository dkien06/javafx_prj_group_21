package com.example.javafx_app.controller.Transaction;

import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.javafx_app.config.Constant.mainStage;

public class TransactionHistorySceneController implements Initializable {

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
    @FXML
    private Label Name , Balance , AccountID ;
    private final Account currentAccount = AccountManager.getInstance().getCurrentAccount();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Name.setText("Họ và tên: "+currentAccount.getAccountName());
        Balance.setText("Số dư: "+currentAccount.getBalance()+ " "+currentAccount.getCurrency());
        AccountID.setText("Số tài khoản: "+currentAccount.getAccountID());
    }
    @FXML
    void QuayLai(ActionEvent event) {
        SceneUtils.switchScene(mainStage, AccountManager.getInstance().chooseHomeScene(
                AccountManager.getInstance().getCurrentAccount()));
    }

}
