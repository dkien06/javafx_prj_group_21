package com.example.javafx_app.controller.Bill;

import com.example.javafx_app.exception.MysteriousException;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.object.Account.CheckingAccount;
import com.example.javafx_app.object.Bill.Bill;
import com.example.javafx_app.util.SceneUtils;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.List;

import static com.example.javafx_app.config.Constant.mainStage;

public class BillListSceneController  {
    @FXML
    private VBox BillList;
    private final CheckingAccount acc = (CheckingAccount) AccountManager.getInstance().getCurrentAccount();
    @FXML
    public void initialize() {
        List<Bill> bills = acc.getBills();
        if (bills == null || bills.isEmpty()) {
            Label emptyLabel = new Label("Không có hóa đơn cần thanh toán");
            emptyLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-padding: 20;");

            // Căn giữa trong VBox
            BillList.setAlignment(javafx.geometry.Pos.CENTER);
            BillList.getChildren().clear();
            BillList.getChildren().add(emptyLabel);
            return;
        }

        for (Bill bill : bills) {
            System.out.println(bill.getSupplier());
            addBillToVbox(bill);
        }
    }
    @FXML
    void QuayLai() {
        SceneUtils.switchScene(mainStage,"BillScene/bill_home_scene.fxml");
    }
    private void addBillToVbox(Bill bill){
        String Supplier = bill.getSupplier();
        LocalDate  date = bill.getDate();
        long amount = bill.getAmount();
        String path = switch (bill.getBillType()) {
            case INTERNET -> "BillScene/BillButton/internet_btn.fxml";
            case ELECTRIC -> "BillScene/BillButton/electric_btn.fxml";
            case TUITION -> "BillScene/BillButton/schoolfee_btn.fxml";
            case WATER -> "BillScene/BillButton/water_btn.fxml";
            default -> {
                path = "";
                throw new MysteriousException();
            }
        };
        Pair<Parent, BillButtonController> button = SceneUtils.getRootAndController(path);
        button.getValue().setData(Supplier,date.toString(),amount);
        BillList.getChildren().add(button.getKey());
    }
    @FXML
    void GoToPayScene(){
        SceneUtils.switchScene(mainStage,"TransactionScene/transaction_scene.fxml");
    }
}
