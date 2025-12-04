package com.example.javafx_app.controller.Bill;

import com.example.javafx_app.config.ExampleUser;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.object.Account.CheckingAccount;
import com.example.javafx_app.object.Bill.Bill;
import com.example.javafx_app.object.Bill.BillType;
import com.example.javafx_app.util.SceneUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
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
        BillType billType = bill.getBillType();
        long amount = bill.getAmount();
        Node item = new Button();
        BillButtonController controller = new BillButtonController() ;
        if(bill.getBillType()== BillType.INTERNET){
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/example/javafx_app/BillScene/BillButton/internet_btn.fxml")
                );

                 item = loader.load();
                 controller = loader.getController();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(bill.getBillType()== BillType.ELECTRIC){
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/example/javafx_app/BillScene/BillButton/electric_btn.fxml")
                );

                 item = loader.load();
                 controller = loader.getController();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(bill.getBillType()== BillType.TUITION){
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/example/javafx_app/BillScene/BillButton/schoolfee_btn.fxml")
                );

                 item = loader.load();
                 controller = loader.getController();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/example/javafx_app/BillScene/BillButton/water_btn.fxml")
                );

                 item = loader.load();
                 controller = loader.getController();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        controller.setData(Supplier,date.toString(),amount);
        BillList.getChildren().add(item);
    }
    @FXML
    void GoToPayScene(){
        SceneUtils.switchScene(mainStage,"TransactionScene/transaction_scene.fxml");
    }
}
