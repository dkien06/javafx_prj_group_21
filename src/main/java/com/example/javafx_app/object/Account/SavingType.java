package com.example.javafx_app.object.Account;

public enum SavingType {
    NONE("Không có"),
    FLEXIBLE("Linh hoạt"),
    FIXED("Kì hạn"),
    ACCUMULATED("Tích góp");

    private final String label;

    SavingType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
