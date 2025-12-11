package com.example.javafx_app.object;

import java.io.Serializable;

public enum TransactionType implements Serializable {
    DEPOSIT,
    WITHDRAW,
    TRANSFER,
    LOAN,
    REPAY
}
