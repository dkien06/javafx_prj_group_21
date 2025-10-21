package com.example.javafx_app.controller;

import com.example.javafx_app.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class TransactionChooseMethodController {
    @FXML
    public void SoTaiKhoan(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"transacting_by_account_id.fxml");
    }
}
