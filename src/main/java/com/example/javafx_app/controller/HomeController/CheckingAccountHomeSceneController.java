package com.example.javafx_app.controller.HomeController;

import com.example.javafx_app.controller.Transaction.TransactionHistorySceneController;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.object.Account.CheckingAccount;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.javafx_app.config.Constant.mainStage;

public class CheckingAccountHomeSceneController implements Initializable,HomeSceneController {

    @FXML
    private Button VIP_btn;

    @FXML
    private Button account_btn;

    @FXML
    private Label account_icon;

    @FXML
    private Button balance_btn;

    @FXML
    private Button bill_btn;


    @FXML
    private Label logo_label;

    @FXML
    private Button noti_btn;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button service_btn;

    @FXML
    private Label setting_icon;

    @FXML
    private Button settings_btn;

    @FXML
    private Button transaction_btn;

    @FXML
    private Button transaction_btn1;

    @FXML
    private Button transfer_btn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CheckingAccount currentAcc = (CheckingAccount) AccountManager.getInstance().getCurrentAccount();
        balance_btn.setText("Số dư: " + currentAcc.getBalance() + currentAcc.getCurrency());
    }
    @FXML
    public void ChuyenTien(ActionEvent event) {
        SceneUtils.switchScene(mainStage,"TransactionScene/transaction_scene.fxml");
    }

    @FXML
    public void XemCaiDat(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "setting/setting.fxml");
    }

    @FXML
    public void XemDIchVu(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "ServiceScene/service_home_scene.fxml");
    }

    @FXML
    public void XemHoTro(ActionEvent event) {

    }

    @FXML
    public void XemHoaDon(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "BillScene/bill_home_scene.fxml");
    }

    @FXML
    public void XemLichSuGiaoDich(ActionEvent event) {
        Pair<Parent, TransactionHistorySceneController> scene = SceneUtils.getRootAndController("TransactionScene/transaction_history_scene.fxml");
        scene.getValue().loadTransactionHistory();
        SceneUtils.switchScene(mainStage,scene.getKey());
    }

    @FXML
    public void XemTaiKhoan(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "/com/example/javafx_app/account_scene.fxml");
    }

    @FXML
    public void XemThongBao(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "/com/example/javafx_app/noti_scene.fxml");
    }

    @FXML
    public void XemVIP(ActionEvent event) {

    }
    @FXML
    public void SoTien(){
        SceneUtils.switchScene(mainStage,"TransactionScene/transaction_history_scene.fxml");
    }
}
