package com.example.javafx_app.controller.checking;

import com.example.javafx_app.controller.block.HistoryBlockController;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.object.Account.CheckingAccount;
import com.example.javafx_app.object.Transaction;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

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
    CheckingAccount currentAccount = (CheckingAccount) AccountManager.getInstance().getCurrentAccount();
    @FXML
    public void initialize() {
        name.setText("Họ và tên: " + AccountManager.getInstance().getCurrentAccount().getAccountName());
        balance.setText("Số dư: " + TransactionManager.getInstance().
                formatCurrency(currentAccount.getBalance(),currentAccount.getCurrency()));
        accountID.setText("Số tài khoản: " + AccountManager.getInstance().getCurrentAccount().getAccountID());
        for(Transaction t : (AccountManager.getInstance().getCurrentAccount()).getHistory()){
            Pair<Parent, HistoryBlockController> block = SceneUtils.getRootAndController("TransactionScene/history_block.fxml");
            block.getValue().setData(t);
            vbox_lich_su_giao_dich.getChildren().add(1,block.getKey());
        }
    }
    @FXML
    void QuayLai(ActionEvent event) {
        SceneUtils.switchScene(mainStage, AccountManager.getInstance().chooseHomeScene(
                AccountManager.getInstance().getCurrentAccount()));
    }
}
