package com.example.javafx_app.object.Bill;

import java.io.Serializable;

public enum BillType implements Serializable {
    // 1. Định nghĩa các hằng số với nhãn hiển thị (label)
    ELECTRIC("Điện"),
    WATER("Nước"),
    INTERNET("Internet"),
    TUITION("Học phí");

    // 2. Thuộc tính để lưu trữ nhãn hiển thị
    private final String label;

    // 3. Constructor (PHẢI là private)
    private BillType(String label) {
        this.label = label;
    }

    // --- Hàm chuyển đổi từ Enum sang String (toString) ---

    // Ghi đè phương thức toString() để trả về nhãn hiển thị
    @Override
    public String toString() {
        return this.label;
    }

    // --- Hàm chuyển đổi từ String (label) sang Enum ---

    /**
     * Tìm và trả về BillType tương ứng với nhãn (label) đã cho.
     * * @param label Nhãn hiển thị của hóa đơn (ví dụ: "Nước").
     * @return BillType tương ứng, hoặc null nếu không tìm thấy.
     */
    public static BillType fromLabel(String label) {
        // Lặp qua tất cả các hằng số trong Enum
        for (BillType type : BillType.values()) {
            // So sánh nhãn (label)
            if (type.label.equalsIgnoreCase(label)) {
                return type;
            }
        }
        // Có thể thay đổi để ném ra IllegalArgumentException thay vì trả về null
        return null;
    }
}
