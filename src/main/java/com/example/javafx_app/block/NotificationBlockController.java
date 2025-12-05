package com.example.javafx_app.block;

import com.example.javafx_app.controller.Noti.NotificationController;
import com.example.javafx_app.object.Noti.Notification;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import static com.example.javafx_app.config.Constant.mainStage;

public class NotificationBlockController {
    Notification thisNotification;
    @FXML
    private Button notificationButton;

    @FXML
    private Label iconLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Label detailLabel;

    @FXML
    private Label dateLabel;

    public void setData(Notification notification) {
        if(notification == null) {return;}
        titleLabel.setText(notification.getTitle());
        detailLabel.setText(notification.getMessage());
        dateLabel.setText(notification.getTimestamp().toString());
        this.thisNotification = notification;
    }
    @FXML
    void GoToDetails(){
        NotificationController.currentNotification  = thisNotification;
        System.out.println("Current notification is "+thisNotification);
        SceneUtils.switchScene(mainStage,"NotiScene/detail_noti.fxml");
    }
}