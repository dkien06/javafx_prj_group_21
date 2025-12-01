package com.example.javafx_app.controller.homeScene;

import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import static com.example.javafx_app.config.Constant.mainStage;
public class CheckingAccountHomeSceneController implements HomeSceneController{

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
    private GridPane gridpane1;

    @FXML
    private GridPane gridpane2;

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
        SceneUtils.switchScene(mainStage, "TransactionScene/transaction_history_scene.fxml");
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

}
