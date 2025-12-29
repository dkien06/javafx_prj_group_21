package com.example.javafx_app.object.Account;

public enum LoanStatus {
    NONE("Không có"),
    REVIEW("Chờ duyệt"),
    REJECTED("Từ chối"),
    ACTIVE("Đang hoạt động"),
    REPAY("Trả nợ"),
    COMPLETED("Hoàn thành vay"),
    OVERDUE("Quá hạn"),
    EXTENDED("Gia hạn"),
    CANCELED("Hủy");

    private final String label;

    LoanStatus(String label) {
        this.label = label;
    }

    public String toString() {
        return this.label;
    }
}
