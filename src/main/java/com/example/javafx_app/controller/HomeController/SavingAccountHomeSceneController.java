package com.example.javafx_app.controller.HomeController;

import com.example.javafx_app.exception.CodeUnderConstruction;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.object.Account.SavingAccount;
import com.example.javafx_app.object.Account.SavingType;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import static com.example.javafx_app.config.Constant.mainStage;

public class SavingAccountHomeSceneController implements HomeSceneController{
    // AnchorPane
    @FXML
    private AnchorPane rootPane;

    // Labels
    @FXML
    private Label logo_label;
    @FXML
    private Label account_icon;
    @FXML
    private Label setting_icon;
    @FXML
    private Label tongtiengui_label;
    @FXML
    private Label balance_label;
    @FXML
    private Label MethodLabel;

    // Buttons
    @FXML
    private Button transfer_btn;
    @FXML
    private Button service_btn;
    @FXML
    private Button account_btn;
    @FXML
    private Button settings_btn;
    @FXML
    private Button noti_btn;

    // Layout Containers
    @FXML
    private GridPane gridpane1;
    @FXML
    private GridPane gridpane2;
    @FXML
    private VBox balance_box;
    private SavingAccount savingAccount = (SavingAccount) AccountManager.getInstance().getCurrentAccount() ;
    public void initialize(){
        if(savingAccount.getType()!= SavingType.NONE)balance_label.setText(TransactionManager.getInstance().formatCurrency(savingAccount.getSaving(),"VND"));
        else balance_label.setText("");
        MethodLabel.setText(savingAccount.getType().toString());
    }
    public void ChuyenTien(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event), "SavingScene/saving_choose_method_scene.fxml");
    }

    public void XemCaiDat(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "setting/setting.fxml");
    }

    public void XemHoTro(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "SupportScene/support_home_scene.fxml");
    }

    public void XemLichSuGiaoDich(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "SavingScene/saving_history_scene.fxml");
    }

    public void XemTaiKhoan(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "account_scene.fxml");
    }

    public void XemThongBao(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "NotiScene/noti_scene.fxml");
    }
}
