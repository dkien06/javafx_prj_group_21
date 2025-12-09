package com.example.javafx_app.object.Account;


public enum ACCOUNT_TYPE {
    CHECKING("Tài khoản thanh toán"), // Tên hiển thị cho CHECKING
    SAVING("Tài khoản tiết kiệm"),    // Tên hiển thị cho SAVING
    LOAN("Tài khoản vay"),// Tên hiển thị cho LOAN
    STAFF("Tài khoản nhân viên") ;
    private final String label;

    // Constructor của Enum
    private ACCOUNT_TYPE(String label) {
        this.label = label;
    }

    // Ghi đè phương thức toString để trả về tên hiển thị
    @Override
    public String toString() {
        return label;
    }
    public static ACCOUNT_TYPE fromLabel(String label) {
        for (ACCOUNT_TYPE type : values()) {
            if (type.label.equalsIgnoreCase(label)) {
                return type;
            }
        }
        // Ném ngoại lệ để xử lý lỗi nếu không tìm thấy (giống như valueOf)
        throw new IllegalArgumentException("Không tìm thấy loại tài khoản với label: " + label);
    }
}