package com.example.javafx_app.controller.saving;

import com.example.javafx_app.config.Constant;
import com.example.javafx_app.controller.block.HistoryBlockController;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.object.Account.SavingAccount;
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

public class SavingHistoryController {

    @FXML
    private Label accountID;

    @FXML
    private Label name;

    @FXML
    private Button return_btn;

    @FXML
    private ScrollPane rootPane;

    @FXML
    private Label so_tien_da_gui;

    @FXML
    private VBox vbox_lich_su_gui_rut;

    @FXML
    private VBox vbox_so_tien_gui;

    @FXML
    private VBox vbox_to;
    SavingAccount cur = (SavingAccount) AccountManager.getInstance().getCurrentAccount();
    @FXML
    void initialize() {
        accountID.setText("Số tài khoản : "+cur.getAccountID());
        name.setText("Họ và tên : "+cur.getAccountName());
        so_tien_da_gui.setText("Số tiền gửi : "+cur.getSaving());
        for(Transaction t : (AccountManager.getInstance().getCurrentAccount()).getHistory()){
            Pair<Parent, HistoryBlockController> block = SceneUtils.getRootAndController("TransactionScene/history_block.fxml");
            block.getValue().setData(t);
            vbox_lich_su_gui_rut.getChildren().add(1,block.getKey());
        }
    }
    @FXML
    void QuayLai(ActionEvent event) {
        SceneUtils.switchScene(Constant.mainStage,"HomeScenes/saving_account_home_scene.fxml");
    }
}
