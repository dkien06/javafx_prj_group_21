package com.example.javafx_app.controller.Bill.Electric;

import com.example.javafx_app.controller.Bill.BillScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ElectricBillSceneController implements BillScene {

    @FXML
    private Label WrongLoginLabel;

    @FXML
    private Button continue_btn;

    @FXML
    private TextField customer_id;

    @FXML
    private Label nguon_thanh_toan;

    @FXML
    private ChoiceBox<?> nha_cung_cap;

    @FXML
    private Button return_btn;

    @FXML
    public void QuayLai(ActionEvent event) {

    }

    @FXML
    public void TiepTuc(ActionEvent event) {

    }
}
