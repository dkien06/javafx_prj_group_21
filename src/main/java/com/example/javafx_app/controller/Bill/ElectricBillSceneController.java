package com.example.javafx_app.controller.Bill;

import com.example.javafx_app.config.ExampleUser;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.object.Bill.BillType;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static com.example.javafx_app.config.Constant.mainStage;

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
    private ChoiceBox<String> nha_cung_cap;
    @FXML
    void initialize() {
        nha_cung_cap.getItems().add(ExampleUser.ELECTRIC_PROVIDER.getAccountName()) ;
        nha_cung_cap.setValue(ExampleUser.ELECTRIC_PROVIDER.getAccountName());
        nha_cung_cap.setMouseTransparent(true);
        nguon_thanh_toan.setText(AccountManager.getInstance().getCurrentAccount().getAccountID());
    }
    @FXML
    private Button return_btn;
    @FXML
    public void QuayLai(ActionEvent event) {
        SceneUtils.switchScene(mainStage,"BillScene/bill_home_scene.fxml");
    }

    @FXML
    public void TiepTuc(ActionEvent event) {
        ClauseController.billType = BillType.ELECTRIC;
        SceneUtils.switchScene(mainStage,"BillScene/clause_scene.fxml");
    }

}
