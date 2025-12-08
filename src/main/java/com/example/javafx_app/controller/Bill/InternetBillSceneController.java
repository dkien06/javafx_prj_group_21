package com.example.javafx_app.controller.Bill;

import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import static com.example.javafx_app.config.Constant.mainStage;

public class InternetBillSceneController implements BillScene {

    @FXML
    private Label WrongLoginLabel;

    @FXML
    private ChoiceBox<?> accountType;

    @FXML
    private Button continue_btn;

    @FXML
    private Label nguon_thanh_toan;

    @FXML
    private Button return_btn;

    @FXML
    public void QuayLai(ActionEvent event) {
        SceneUtils.switchScene(mainStage,"BillScene/bill_home_scene.fxml");
    }

    @FXML
    public void TiepTuc(ActionEvent event) {
        SceneUtils.switchScene(mainStage,"BillScene/clause_scene.fxml");
    }

}
