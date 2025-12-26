package com.example.javafx_app.object.Account;

import java.io.Serializable;

public enum SavingType implements Serializable {
    NONE("Không có"),
    FLEXIBLE("Gửi tiền linh hoạt"),
    FIXED("Gửi tiền kì hạn"),
    ACCUMULATED("Gửi tiền tích góp");

    private final String label;

    SavingType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
    public SavingType fromString(String label) {
        for (SavingType type : SavingType.values()) {
            if (type.toString().equals(label)) {
                return type;
            }
        }
        return NONE;
    }
}
