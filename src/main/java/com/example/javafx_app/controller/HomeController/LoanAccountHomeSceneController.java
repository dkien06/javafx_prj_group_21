package com.example.javafx_app.controller.HomeController;

import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import static com.example.javafx_app.config.Constant.mainStage;

public class LoanAccountHomeSceneController {

    @FXML
    private Button account_btn;

    @FXML
    private Label account_icon;

    @FXML
    private VBox du_no_box;

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
    private Label setting_icon;

    @FXML
    private Button settings_btn;

    @FXML
    private Label so_du_no;

    @FXML
    private Label so_du_no_label;

    @FXML
    void XemCaiDat(ActionEvent event) {

    }

    @FXML
    void XemTaiKhoan(ActionEvent event) {

    }

    @FXML
    void XemThongBao(ActionEvent event) {

    }

    @FXML
    void hoTro(ActionEvent event) {

    }

    @FXML
    void thanhToanKhoanVay(ActionEvent event) {

    }

    @FXML
    void thongTinVay(ActionEvent event) {

    }

    @FXML
    void vayMoi(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "loanScene/loan_choose_option.fxml");
    }

}
