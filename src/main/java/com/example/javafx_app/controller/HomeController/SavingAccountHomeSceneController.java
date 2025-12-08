package com.example.javafx_app.controller.HomeController;

import com.example.javafx_app.exception.CodeUnderConstruction;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import static com.example.javafx_app.config.Constant.mainStage;

public class SavingAccountHomeSceneController implements HomeSceneController{
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
    public void ChuyenTien(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event), "SavingScene/saving_choose_method_scene.fxml");
    }

    public void XemCaiDat(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "setting/setting.fxml");
    }

    public void XemHoTro(ActionEvent event) {
        CodeUnderConstruction.throwException();
    }

    public void XemLichSuGiaoDich(ActionEvent event) {
        CodeUnderConstruction.throwException();
    }

    public void XemTaiKhoan(ActionEvent event) {
        CodeUnderConstruction.throwException();
    }

    public void XemThongBao(ActionEvent event) {
        CodeUnderConstruction.throwException();
    }

    public void XemVIP(ActionEvent event) {
        CodeUnderConstruction.throwException();
    }
}
