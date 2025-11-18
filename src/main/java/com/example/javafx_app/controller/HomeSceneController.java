package com.example.javafx_app.controller;

import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class HomeSceneController {

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
    void ChuyenTien(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"TransactionScene/transaction_between_accounts_scene.fxml");
    }

    @FXML
    void XemCaiDat(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event), "setting/setting.fxml");
    }

    @FXML
    void XemDIchVu(ActionEvent event) {

    }

    @FXML
    void XemHoTro(ActionEvent event) {

    }

    @FXML
    void XemHoaDon(ActionEvent event) {

    }

    @FXML
    void XemLichSuGiaoDich(ActionEvent event) {

    }

    @FXML
    void XemTaiKhoan(ActionEvent event) {

    }

    @FXML
    void XemThongBao(ActionEvent event) {

    }

    @FXML
    void XemVIP(ActionEvent event) {

    }

}
