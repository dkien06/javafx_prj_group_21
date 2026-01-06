package com.example.javafx_app.controller.Noti;

import com.example.javafx_app.controller.block.NotificationBlockController;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.NotiManager;
import com.example.javafx_app.object.Noti.Notification;
import com.example.javafx_app.util.SceneUtils;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import java.util.Iterator;
import java.util.List;

import static com.example.javafx_app.config.Constant.mainStage;

public class NotificationController {
    public static Notification currentNotification ;
    @FXML
    private VBox myVBox;
    @FXML private CheckBox ReadCheckBox ;

    List<Notification> notifications = AccountManager.getInstance().getCurrentAccount().getNotifications();
    @FXML
    public void initialize() {
        myVBox.getChildren().clear();
        Iterator<Notification> iterator = notifications.iterator();
        if (ReadCheckBox != null) {
            // 2. Gắn Listener để cập nhật biến static ngay lập tức khi trạng thái thay đổi
            ReadCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                NotiManager.isMarkedAsRead = newValue;
            });
        }
        if(NotiManager.isMarkedAsRead){
            notifications.clear();
            NotiManager.isMarkedAsRead = false;
            return;
        }
        // 2. Lặp bằng while (iterator.hasNext())
        while (iterator.hasNext()) {
            Notification notification = iterator.next(); // Lấy phần tử tiếp theo

            if (notification.isRead()) {
                // 3. Xóa phần tử bằng phương thức của Iterator (AN TOÀN)
                iterator.remove();
                // Không cần 'continue' vì Iterator sẽ tự động nhảy đến phần tử tiếp theo
            }
            else{
                Pair<Parent, NotificationBlockController> button = SceneUtils.
                        getRootAndController("NotiScene/notification_button_block.fxml");
                button.getValue().setData(notification);
                myVBox.getChildren().add(0,button.getKey());
            }
        }
    }

    @FXML void GoToDetails() {

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
        SceneUtils.switchScene(mainStage,AccountManager.getInstance().chooseHomeScene(
                AccountManager.getInstance().getCurrentAccount()));
    }

}
