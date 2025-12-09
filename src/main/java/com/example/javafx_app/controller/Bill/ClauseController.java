package com.example.javafx_app.controller.Bill;

import com.example.javafx_app.util.SceneUtils;
import javafx.fxml.FXML;

import static com.example.javafx_app.config.Constant.mainStage;

public class ClauseController {
    @FXML
    void HoanThanh(){
        SceneUtils.switchScene(mainStage,"BillScene/bill_home_scene.fxml");
    }
    @FXML
    void QuayLai(){
        SceneUtils.switchScene(mainStage,"BillScene/bill_home_scene.fxml");
    }
}
