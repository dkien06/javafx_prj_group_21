package com.example.javafx_app.controller;

import com.example.javafx_app.exception.MysteriousException;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.object.Account.CheckingAccount;
import com.example.javafx_app.object.Account.LoanAccount;
import com.example.javafx_app.object.Account.SavingAccount;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import static com.example.javafx_app.config.Constant.mainStage;

public class ServiceController {

    @FXML
    private Button account_btn;

    @FXML
    private Label account_icon;

    @FXML
    private GridPane gridpane2;

    @FXML
    private GridPane main_gridpane;

    @FXML
    private Label mu_cu_nhan_icon;

    @FXML
    private Label plane_icon;

    @FXML
    private Label plane_icon1;

    @FXML
    private Label plane_icon11;

    @FXML
    private Label plane_icon112;

    @FXML
    private Label plane_icon113;

    @FXML
    private Label plane_icon114;

    @FXML
    private Label plane_icon115;

    @FXML
    private Button return_btn;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label setting_icon;

    @FXML
    private Button settings_btn;

    @FXML
    private Label tien_dien_nuoc_icon;

    @FXML
    private Label voucher_icon;

    @FXML
    void QuayLai(ActionEvent event) {
        switch (AccountManager.getInstance().getCurrentAccount()) {
            case CheckingAccount checkingAccount ->
                    SceneUtils.switchScene(mainStage, "/com/example/javafx_app/HomeScenes/checking_account_home_scene.fxml");
            case SavingAccount savingAccount ->
                    SceneUtils.switchScene(mainStage, "/com/example/javafx_app/HomeScenes/checking_account_home_scene.fxml");
            case LoanAccount loanAccount ->
                    SceneUtils.switchScene(mainStage, "/com/example/javafx_app/HomeScenes/checking_account_home_scene.fxml");
            case null, default -> throw new MysteriousException();
        }
    }

    @FXML
    void XemCaiDat(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "setting/setting.fxml");
    }

    @FXML
    void XemTaiKhoan(ActionEvent event) {

    }

}
