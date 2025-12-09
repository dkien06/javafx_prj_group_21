package com.example.javafx_app.object.Bill;

import java.time.LocalDate;
import java.util.Objects;

public class Bill {
    long amount;
    LocalDate date;
    BillType billType;
    String Supplier ;
    public Bill(long amount, LocalDate date, BillType billType, String supplier) {
        this.amount = amount;
        this.date = date;
        this.billType = billType;
        this.Supplier = supplier;
    }
    // ======Getter=========
    public long getAmount() { return amount; }
    public LocalDate getDate() { return date; }
    public BillType getBillType() { return billType; }
    public String getSupplier() { return Supplier; }
    //======Setter=========
    public void setAmount(long amount) { this.amount = amount; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setBillType(BillType billType) { this.billType = billType; }
    public void setSupplier(String supplier) { this.Supplier = supplier; }
    @Override
    public boolean equals(Object o) {
        // Kiểm tra xem đối tượng có phải chính nó không
        if (this == o) return true;
        // Kiểm tra xem o có null không và có cùng lớp với Bill không
        if (o == null || getClass() != o.getClass()) return false;

        // Ép kiểu đối tượng thành Bill
        Bill bill = (Bill) o;

        // So sánh các trường để xác định sự bằng nhau
        return amount == bill.amount &&
                Objects.equals(date, bill.date) &&
                billType == bill.billType &&
                Objects.equals(Supplier, bill.Supplier);
    }

    // Bổ sung phương thức hashCode() để tuân thủ nguyên tắc của equals/hashCode
    @Override
    public int hashCode() {
        return Objects.hash(amount, date, billType, Supplier);
    }
}
