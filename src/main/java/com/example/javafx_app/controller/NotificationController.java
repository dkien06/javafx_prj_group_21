package com.example.javafx_app.controller;

import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import static com.example.javafx_app.config.Constant.mainStage;

public class NotificationController {

    @FXML
    private Button account_btn;

    @FXML
    private Label account_icon;

    @FXML
    private GridPane gridpane2;

    @FXML
    private Label logo_label;

    @FXML
    private VBox myVBox;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label setting_icon;

    @FXML
    private Button settings_btn;

    @FXML
    private Button return_btn;

    @FXML
    void XemCaiDat(ActionEvent event) {

    }

    @FXML
    void XemTaiKhoan(ActionEvent event) {

    }

    @FXML
    void QuayLai(ActionEvent event) {
        SceneUtils.switchScene(mainStage,"/com/example/javafx_app/HomeScenes/checking_account_home_scene.fxml");
    }

}
