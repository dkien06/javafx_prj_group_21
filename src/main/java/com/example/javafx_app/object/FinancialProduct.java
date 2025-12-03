package com.example.javafx_app.object;

import java.time.LocalDate;

/**
 * Class biểu diễn một sản phẩm tài chính:
 * có thể là khoản GỬI TIẾT KIỆM hoặc KHOẢN VAY.
 */
public class FinancialProduct {

    private final String accountID;
    private FinancialProductType type;             // Loại sản phẩm: SAVING hay LOAN
    private LocalDate startDate;// Ngày bắt đầu
    private String ProductName ;
    private String description ;
    private double term;           // Kỳ hạn (tính bằng năm)
    private double principal;      // Số tiền vốn
    private double interestRate;   // Lãi suất (%/năm)

    // Constructor
    public FinancialProduct(String accountID,FinancialProductType type, double principal, double term, double interestRate,String ProductName,String description) {
        this.accountID = accountID;
        this.type = type;
        this.principal = principal;
        this.term = term;
        this.interestRate = interestRate;
        this.startDate = LocalDate.now();
        this.ProductName = ProductName;
        this.description = description;
    }

    // Getter/Setter
    public String getAccountID() {
        return accountID;
    }

    public FinancialProductType getType() {
        return type;
    }

    public void setType(FinancialProductType type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public double getTerm() {
        return term;
    }

    public void setTerm(double term) {
        this.term = term;
    }

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    public String getProductName() { return ProductName; }
    public void setProductName(String ProductName) { this.ProductName = ProductName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    // --- Logic chung ---

    /** Tính ngày đáo hạn / ngày trả nợ */
    public LocalDate calculateMaturityDate() {
        int years = (int) term;
        double fractional = term - years;

        LocalDate date = startDate.plusYears(years);
        if (fractional > 0) {
            int months = (int) Math.round(fractional * 12);
            date = date.plusMonths(months);
        }
        return date;
    }

    /** Tính tổng số tiền sau kỳ hạn:
     *  - SAVING: số tiền nhận được (gốc + lãi)
     *  - LOAN: số tiền phải trả (gốc + lãi)
     */
    public double calculateTotalAmount() {
        return principal * Math.pow(1 + interestRate, term);
    }
    /** In thông tin */
    @Override
    public String toString() {
        String action = (type == FinancialProductType.SAVING) ? "Gửi tiết kiệm" : "Khoản vay";
        return action + " {" +
                "Ngày bắt đầu=" + startDate +
                ", Kỳ hạn=" + term + " năm" +
                ", Số tiền gốc=" + principal +
                ", Lãi suất=" + interestRate +
                ", Ngày đáo hạn=" + calculateMaturityDate() +
                ", Tổng tiền " + ((type == FinancialProductType.SAVING) ? "nhận được" : "phải trả") +
                "=" + String.format("%.2f", calculateTotalAmount()) +
                '}';
    }
}
