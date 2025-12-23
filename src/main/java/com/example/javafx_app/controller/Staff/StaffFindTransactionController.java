package com.example.javafx_app.controller.Staff;

import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.object.Transaction;
import com.example.javafx_app.object.TransactionType;
import com.example.javafx_app.util.SceneUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.javafx_app.config.Constant.mainStage;

public class StaffFindTransactionController implements Initializable {

    // FXML fields matching the provided XML structure
    @FXML private TextField minAmountField;
    @FXML private TextField maxAmountField;
    @FXML private Label messageLabel;
    @FXML private TableView<Transaction> transactionTable;
    @FXML private TableColumn<Transaction, String> idColumn;
    @FXML private TableColumn<Transaction, LocalDateTime> dateColumn;
    @FXML private TableColumn<Transaction, TransactionType> typeColumn;
    @FXML private TableColumn<TableColumn<Transaction, Long>, Long> amountColumn;
    @FXML private TableColumn<Transaction, String> currencyColumn;
    @FXML private TableColumn<Transaction, String> fromAccountColumn;
    @FXML private TableColumn<Transaction, String> toAccountColumn;

    // Nút Quay Lại (Dựa trên FXML có 1 button ngoài VBox, không cần fx:id)

    private final TransactionManager transactionManager = TransactionManager.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // GỌI HÀM RIÊNG để cấu hình TableView
        setupTableView();
    }

    /**
     * Phương thức riêng để cấu hình các cột của TableView,
     * bao gồm mapping thuộc tính và định dạng hiển thị.
     */
    private void setupTableView() {
        // 1. Map columns to simple Transaction properties
        idColumn.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        // Cần ép kiểu thủ công vì lỗi generic (JavaFX TableColumn issue)
        TableColumn<Transaction, Long> tempAmountColumn = (TableColumn<Transaction, Long>) (TableColumn<?, ?>) amountColumn;
        tempAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        currencyColumn.setCellValueFactory(new PropertyValueFactory<>("currency"));

        // 2. Custom mapping for complex nested objects (Account ID)
        // Lấy AccountID từ object Account (fromAccount.accountID)
        fromAccountColumn.setCellValueFactory(cellData -> {
            Transaction transaction = cellData.getValue();
            if (transaction.getFromAccount() != null) {
                return new javafx.beans.property.SimpleStringProperty(transaction.getFromAccount().getAccountID());
            }
            return new javafx.beans.property.SimpleStringProperty("N/A");
        });

        // Lấy AccountID từ object Account (toAccount.accountID)
        toAccountColumn.setCellValueFactory(cellData -> {
            Transaction transaction = cellData.getValue();
            if (transaction.getToAccount() != null) {
                return new javafx.beans.property.SimpleStringProperty(transaction.getToAccount().getAccountID());
            }
            return new javafx.beans.property.SimpleStringProperty("N/A");
        });

        // 3. Custom formatting for date/time (LocalDateTime)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        dateColumn.setCellFactory(column -> new javafx.scene.control.TableCell<Transaction, LocalDateTime>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(formatter.format(item));
                }
            }
        });
    }

    @FXML
    private void handleSearch(ActionEvent event) {
        messageLabel.setText("");

        // 1. Input Validation and Parsing
        String minText = minAmountField.getText().trim();
        String maxText = maxAmountField.getText().trim();

        if (minText.isEmpty() || maxText.isEmpty()) {
            messageLabel.setText("Vui lòng nhập cả Min Amount và Max Amount.");
            messageLabel.setStyle("-fx-text-fill: red;");
            transactionTable.setItems(FXCollections.emptyObservableList());
            return;
        }

        long minAmount, maxAmount;
        try {
            minAmount = Long.parseLong(minText);
            maxAmount = Long.parseLong(maxText);
        } catch (NumberFormatException e) {
            messageLabel.setText("Số tiền không hợp lệ. Vui lòng nhập số.");
            messageLabel.setStyle("-fx-text-fill: red;");
            transactionTable.setItems(FXCollections.emptyObservableList());
            return;
        }

        // 2. Business Logic: Filter transactions
        List<Transaction> allTransactions = transactionManager.getTransactionsList();

        // Sử dụng hàm lọc đã định nghĩa
        List<Transaction> filteredList = transactionManager.filterByAmountRange(
                allTransactions,
                minAmount,
                maxAmount
        );

        // 3. Display Results
        if (filteredList.isEmpty()) {
            messageLabel.setText("Không tìm thấy giao dịch nào trong khoảng [" + minAmount + " - " + maxAmount + "].");
            transactionTable.setItems(FXCollections.emptyObservableList());
            messageLabel.setStyle("-fx-text-fill: orange;");
        } else {
            ObservableList<Transaction> observableList = FXCollections.observableArrayList(filteredList);
            transactionTable.setItems(observableList);
            messageLabel.setText("Đã tìm thấy " + filteredList.size() + " giao dịch trong khoảng [" + minAmount + " - " + maxAmount + "].");
            messageLabel.setStyle("-fx-text-fill: green;");
        }
    }

    // Xử lý nút Quay lại (Dựa trên hàm QuayLai trong các controller khác)
    @FXML
    private void QuayLai(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "HomeScenes/staff_home_scene.fxml");
    }
}