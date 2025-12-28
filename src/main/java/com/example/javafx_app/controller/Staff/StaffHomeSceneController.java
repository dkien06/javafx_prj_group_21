package com.example.javafx_app.controller.Staff;

import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import static com.example.javafx_app.config.Constant.mainStage;

public class StaffHomeSceneController  {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label logo_label;

    @FXML
    private GridPane gridpane1;

    // Dùng cho nút "Xem tài khoản" (GridPane 1) và nút "Tài khoản" (GridPane 2)
    @FXML
    private Button account_btn;

    @FXML
    private GridPane gridpane2;

    @FXML
    private Label account_icon;

    @FXML
    private Button settings_btn;

    @FXML
    private Label setting_icon;

    @FXML
    private Button noti_btn;


    // --- Các hàm xử lý sự kiện cho GridPane 1 ---

    @FXML
    void XemTaiKhoanKhachHang(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "StaffScene/staff_find_account_scene.fxml");
    }

    @FXML
    void XemLichSuGiaoDich(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "StaffScene/staff_find_transaction.fxml");
    }

    @FXML
    void XemThongTinKhachHang(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "StaffScene/staff_find_costumer.fxml");
    }

    // --- Các hàm xử lý sự kiện khác (Navigation) ---

    @FXML
    void XemTaiKhoan(ActionEvent event) {
        // Chuyển đến màn hình tài khoản cá nhân của nhân viên
        SceneUtils.switchScene(mainStage, "/com/example/javafx_app/account_scene.fxml");
    }

    @FXML
    void XemCaiDat(ActionEvent event) {
        // Chuyển đến màn hình cài đặt
        SceneUtils.switchScene(mainStage, "setting/setting.fxml");
    }

    @FXML
    void XemThongBao(ActionEvent event) {
        // Chuyển đến màn hình thông báo
        SceneUtils.switchScene(mainStage, "/com/example/javafx_app/noti_scene.fxml");
    }

    @FXML
    void XetDuyet(ActionEvent event) {
        // Chuyển đến màn hình thông báo
        SceneUtils.switchScene(mainStage, "StaffScene/loan_review_scene.fxml");
    }
}