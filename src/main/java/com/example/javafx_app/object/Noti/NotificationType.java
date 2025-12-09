package com.example.javafx_app.object.Noti;

public enum NotificationType {
    // 1. Biến động số dư / Giao dịch
    BALANCE_CHANGE("Biến động số dư"),
    TRANSFER_SUCCESS("Chuyển khoản thành công"),
    TRANSFER_FAILED("Chuyển khoản thất bại"),

    // 2. Hóa đơn
    NEW_BILL("Hóa đơn mới"),
    BILL_PAID_SUCCESS("Thanh toán hóa đơn thành công"),
    BILL_PAID_FAILED("Thanh toán hóa đơn thất bại"),

    // 3. Tài khoản / Bảo mật
    LOGIN_ALERT("Cảnh báo đăng nhập"),
    PASSWORD_CHANGED("Thay đổi mật khẩu"),
    PIN_CHANGED("Thay đổi mã PIN"),

    // 4. Sản phẩm / Hệ thống
    PROMOTION("Khuyến mãi/Ưu đãi"),
    SYSTEM_UPDATE("Cập nhật hệ thống");

    private final String label;

    private NotificationType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
    public NotificationType fromLabel(String label) {
        for (NotificationType type : NotificationType.values()) {
            if (type.toString().equals(label)) { return type; }
        }
        return null;
    }
}