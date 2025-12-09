package com.example.javafx_app.controller.Noti;

import com.example.javafx_app.object.Noti.Notification;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import static com.example.javafx_app.config.Constant.mainStage;

public class DetailNoticontroller {
    @FXML
    private Label messageLabel;

    @FXML
    private Button returnButton;

    @FXML
    private Label Title;

    @FXML
    public void initialize(){
        Notification cur = NotificationController.currentNotification;
        Title.setText(cur.getTitle());
        messageLabel.setText(cur.getMessage());
    }
    @FXML
    public void QuayLai(ActionEvent event) {
        SceneUtils.switchScene(mainStage,"NotiScene/noti_scene.fxml");
    }
}