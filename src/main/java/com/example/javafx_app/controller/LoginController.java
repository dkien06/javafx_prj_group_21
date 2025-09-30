package com.example.javafx_app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoginController {

    @FXML
    private Button btn_dang_nhap;

    @FXML
    private Button btn_tao_tai_khoan_moi;

    @FXML
    void DangNhap(ActionEvent event) {
        System.out.println("Dang nhap");
    }

    @FXML
    void TaoTaiKhoanMoi(ActionEvent event) {
        System.out.println("Tao Tai Khoan");
    }

}
