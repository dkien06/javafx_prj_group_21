package com.example.javafx_app.controller;

import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import static com.example.javafx_app.config.Constant.mainStage;

public class AccountSceneController {

    @FXML
    private Label avatar;

    @FXML
    private RadioButton checking_account_choice;

    @FXML
    private RadioButton checking_account_choice1;

    @FXML
    private RadioButton checking_account_choice2;

    @FXML
    private Label logo_label;

    @FXML
    private VBox my_vbox;

    @FXML
    private Button return_btn;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ScrollPane scroll;

    @FXML
    private VBox vbox_in_hbox;

    @FXML
    void QuayLai(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "/com/example/javafx_app/HomeScenes/checking_account_home_scene.fxml");
    }

}
