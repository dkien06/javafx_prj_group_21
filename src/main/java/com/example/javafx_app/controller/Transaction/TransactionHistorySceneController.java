package com.example.javafx_app.controller.Transaction;

import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class TransactionHistorySceneController {

    @FXML
    private Button return_btn;

    @FXML
    private ScrollPane rootPane;

    @FXML
    private VBox vbox_lich_su_giao_dich;

    @FXML
    private VBox vbox_so_du_sotaikhoan;

    @FXML
    private VBox vbox_to;

    @FXML
    void QuayLai(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event), "/com/example/javafx_app/HomeScenes/checking_account_home_scene.fxml");
    }

}
