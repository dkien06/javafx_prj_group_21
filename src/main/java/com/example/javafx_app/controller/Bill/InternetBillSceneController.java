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

import static com.example.javafx_app.config.Constant.mainStage;

public class InternetBillSceneController implements BillScene {

    @FXML
    private Label WrongLoginLabel;

    @FXML
    private ChoiceBox<String> accountType;

    @FXML
    private Button continue_btn;

    @FXML
    private Label nguon_thanh_toan;

    @FXML
    private Button return_btn;
    @FXML
    void initialize() {
        accountType.getItems().add(ExampleUser.INTERNET_PROVIDER.getAccountName()) ;
        accountType.setValue(ExampleUser.INTERNET_PROVIDER.getAccountName());
        accountType.setMouseTransparent(true);
        nguon_thanh_toan.setText(AccountManager.getInstance().getCurrentAccount().getAccountID());
    }
    @FXML
    public void QuayLai(ActionEvent event) {
        SceneUtils.switchScene(mainStage,"BillScene/bill_home_scene.fxml");
    }

    @FXML
    public void TiepTuc(ActionEvent event) {
        ClauseController.billType = BillType.INTERNET  ;
        SceneUtils.switchScene(mainStage,"BillScene/clause_scene.fxml");
    }

}
