package com.example.javafx_app.controller.Noti;

import com.example.javafx_app.block.NotificationBlockController;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.object.Noti.Notification;
import com.example.javafx_app.util.SceneUtils;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.List;

import static com.example.javafx_app.config.Constant.mainStage;

public class NotificationController {
    public static Notification currentNotification ;
    @FXML
    private VBox myVBox;

    List<Notification> notifications = AccountManager.getInstance().getCurrentAccount().getNotifications();
    @FXML
    public void initialize() {
        myVBox.getChildren().clear();
        for(Notification notification : notifications) {
            Pair<Parent, NotificationBlockController> button = SceneUtils.
                    getRootAndController("NotiScene/notification_button_block.fxml");
            button.getValue().setData(notification);
            myVBox.getChildren().add(button.getKey());
        }
    }

    @FXML
    void XemCaiDat() {
        SceneUtils.switchScene(mainStage, "setting/setting.fxml");
    }

    @FXML
    void XemTaiKhoan() {
        SceneUtils.switchScene(mainStage, "account_scene.fxml");
    }

    @FXML
    void QuayLai() {
        SceneUtils.switchScene(mainStage,"/com/example/javafx_app/HomeScenes/checking_account_home_scene.fxml");
    }

}
