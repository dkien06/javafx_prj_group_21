package com.example.javafx_app;

import java.time.LocalDateTime;

public class Transaction {
    public enum TransactionType {
        DEPOSIT,
        WITHDRAW,
        TRANSFER
    }
    private String transactionID;
    private LocalDateTime date;
    private TransactionType type;
    private double amount;
    private String currency;
    private Account fromAccount;
    private Account toAccount;
    private String description;

    // Constructor
    public Transaction(TransactionType type, double amount, String currency, Account fromAccount,
                       Account toAccount, String description) {
        date = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
        this.currency = currency;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.description = description;
    }

    // Getter methods
    public String getTransactionID() {
        return transactionID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + date + "] " + type + " " + amount + " " + currency +
                " (" + description + ")";
    }
}
