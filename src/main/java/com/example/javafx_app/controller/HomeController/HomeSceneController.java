package com.example.javafx_app.controller.HomeController;

import javafx.event.ActionEvent;

import java.io.IOException;

interface HomeSceneController {
    void ChuyenTien(ActionEvent event);
    void XemCaiDat(ActionEvent event);
    void XemHoTro(ActionEvent event);
    void XemLichSuGiaoDich(ActionEvent event) throws IOException;
    void XemTaiKhoan(ActionEvent event);
    void XemThongBao(ActionEvent event);
    void XemVIP(ActionEvent event);
}
