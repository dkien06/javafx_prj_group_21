package com.example.javafx_app.object;

import com.example.javafx_app.BankApplication;
import com.example.javafx_app.Manager.TransactionManager;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String fullName;
    private String citizenID;
    private String accountID;
    private String password;
    private double balance;
    private String currency;
    private String PIN;
    private List<Transaction> history;

    // ✅ Constructor đầy đủ
    public Account(String fullName, String citizenID, String accountID, String password, double balance,
                   String currency, String PIN) {
        this.fullName = fullName;
        this.citizenID = citizenID;
        this.accountID = accountID;
        this.password = password;
        this.balance = balance;
        this.currency = currency;
        this.PIN = PIN;
        this.history = new ArrayList<>();
    }

    // ✅ Constructor rỗng (cần cho JavaFX hoặc khởi tạo tạm)
    public Account() {
        this.history = new ArrayList<>();
    }

    // === Getter ===
    public String getFullName() {
        return fullName;
    }
    public String getCitizenID() {
        return citizenID;
    }
    public String getAccountID() {
        return accountID;
    }
    public double getBalance() {
        return balance;
    }
    public String getCurrency() {
        return currency;
    }
    public String getPIN() {
        return PIN;
    }
    public List<Transaction> getHistory() {
        return history;
    }
    public String getPassword() {
        return password;
    }

    // === Setter ===
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public void setPIN(String PIN) {
        this.PIN = PIN;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    // === Giao dịch cơ bản ===

    // ✅ Nạp tiền
    public void deposit(double amount, String description) {
        if (amount > 0) {
            balance += amount;
            addTransaction(new Transaction(Transaction.TransactionType.DEPOSIT, amount, "VND", this, this, description));
        }
    }

    // ✅ Rút tiền
    public boolean withdraw(double amount, String description) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            addTransaction(new Transaction(Transaction.TransactionType.WITHDRAW, amount, "VND", this, this, description));
            return true;
        } else {
            return false;
        }
    }

    // ✅ Chuyển tiền
    public boolean transfer(Account toAccount, double amount, String description) {
        if (toAccount == null || amount <= 0 || amount > balance) {
            return false;
        }

        // rút tiền bên gửi
        this.balance -= amount;
        // nạp tiền bên nhận
        toAccount.balance += amount;

        // thêm lịch sử giao dịch cho cả 2
        Transaction NewTransfer = new Transaction(Transaction.TransactionType.TRANSFER, amount, "VND", this, toAccount, description);
        this.addTransaction(NewTransfer);
        Transaction newTransfer = new Transaction(Transaction.TransactionType.TRANSFER, -amount, "VND", this, toAccount, description);
        toAccount.addTransaction(newTransfer);
        TransactionManager.getInstance().addTransaction(newTransfer);
        return true;
    }

    // ✅ Thêm giao dịch
    public void addTransaction(Transaction t) {
        history.add(t);
    }
    //Đối chiếu PIN
    public boolean isPinMatched(String pin) {
        return this.PIN != null && this.PIN.equals(pin);
    }
    public boolean isPasswordMatched(String password){
        return this.password != null && this.password.equals(password);
    }
    // ✅ In ra thông tin tài khoản (dễ debug)
    @Override
    public String toString() {
        return "Account{" +
                "citizenID='" + citizenID + '\'' +
                ", accountID='" + accountID + '\'' +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                '}';
    }
}