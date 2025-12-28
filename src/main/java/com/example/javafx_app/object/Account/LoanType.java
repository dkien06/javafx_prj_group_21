package com.example.javafx_app.object.Account;

import java.io.Serializable;

public enum LoanType implements Serializable {
    NONE("Không có"),
    FIXED("Vay tiền kì hạn"),
    ACCUMULATED("Vay tiền trả góp");

    private final String label;

    LoanType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
    public LoanType fromString(String label) {
        for (LoanType type : LoanType.values()) {
            if (type.toString().equals(label)) {
                return type;
            }
        }
        return NONE;
    }
}
