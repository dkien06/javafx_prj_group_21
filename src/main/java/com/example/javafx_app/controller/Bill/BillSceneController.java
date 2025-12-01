package com.example.javafx_app.controller.Bill;

import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import static com.example.javafx_app.config.Constant.mainStage;

public class BillSceneController {

    @FXML
    private Button account_btn;

    @FXML
    private Label account_icon;

    @FXML
    private GridPane gridpane2;

    @FXML
    private Button hoc_phi_button;

    @FXML
    private GridPane main_gridpane;

    @FXML
    private Button mang_button;

    @FXML
    private Label mu_cu_nhan_icon;

    @FXML
    private Label plane_icon1;

    @FXML
    private Label plane_icon11;

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
    private Label tia_set_icon;

    @FXML
    private Button tien_dien_button;

    @FXML
    private Button tien_nuoc_button;

    @FXML
    private Label water_icon;

    @FXML
    private Label wifi_icon;

    @FXML
    void QuayLai(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "/com/example/javafx_app/HomeScenes/checking_account_home_scene.fxml");
    }

    @FXML
    void XemCaiDat(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "/com/example/javafx_app/setting/setting.fxml");
    }

    @FXML
    void XemTaiKhoan(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "/com/example/javafx_app/account_scene.fxml");
    }

}
