package com.example.javafx_app.controller.Staff;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class StaffFindTransactionController {

    @FXML
    private TableColumn<?, ?> amountColumn;

    @FXML
    private TableColumn<?, ?> currencyColumn;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private TableColumn<?, ?> fromAccountColumn;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TextField maxAmountField;

    @FXML
    private Label messageLabel;

    @FXML
    private TextField minAmountField;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableColumn<?, ?> toAccountColumn;

    @FXML
    private TableView<?> transactionTable;

    @FXML
    private TableColumn<?, ?> typeColumn;

    @FXML
    void handleSearch(ActionEvent event) {

    }

}
