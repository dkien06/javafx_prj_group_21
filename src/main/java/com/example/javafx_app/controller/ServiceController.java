package com.example.javafx_app.controller;

import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class ServiceController {

    @FXML
    private Button account_btn;

    @FXML
    private Label account_icon;

    @FXML
    private GridPane gridpane2;

    @FXML
    private GridPane main_gridpane;

    @FXML
    private Label mu_cu_nhan_icon;

    @FXML
    private Label plane_icon;

    @FXML
    private Label plane_icon1;

    @FXML
    private Label plane_icon11;

    @FXML
    private Label plane_icon112;

    @FXML
    private Label plane_icon113;

    @FXML
    private Label plane_icon114;

    @FXML
    private Label plane_icon115;

    @FXML
    private Button return_btn;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label setting_icon;

    @FXML
    private Button settings_btn;

    @FXML
    private Label tien_dien_nuoc_icon;

    @FXML
    private Label voucher_icon;

    @FXML
    void QuayLai(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event), "/com/example/javafx_app/HomeScenes/checking_account_home_scene.fxml");
    }

    @FXML
    void XemCaiDat(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event), "setting/setting.fxml");
    }

    @FXML
    void XemTaiKhoan(ActionEvent event) {

    }

}
