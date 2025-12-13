package com.example.javafx_app.object.Account;

import com.example.javafx_app.manager.BankManager;
import com.example.javafx_app.object.Noti.Notification;
import com.example.javafx_app.object.Transaction;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public  abstract class Account implements Serializable {
    protected String accountName;
    protected String citizenID;
    protected String accountID;
    protected String password;
    protected String currency;
    protected String PIN;
    private List<Transaction> history;
    private List<Notification> notifications;
    private LocalDate StartDate = null;

    // ✅ Constructor đầy đủ
    public Account(String fullName, String citizenID, String accountID, String password,
                   String currency, String PIN) {
        this.accountName = fullName;
        this.citizenID = citizenID;
        this.accountID = accountID;
        this.password = password;
        this.currency = currency;
        this.PIN = PIN;
        this.history = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.StartDate = BankManager.getCurrentDate();// Lay ngay hom nay , gia lap thoi
    }

    // ✅ Constructor rỗng (cần cho JavaFX hoặc khởi tạo tạm)
    public Account() {
        this.history = new ArrayList<>();
    }

    // === Getter ===
    public String getAccountName() {
        return accountName;
    }
    public String getCitizenID() {
        return citizenID;
    }
    public String getAccountID() {
        return accountID;
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
    public LocalDate getStartDate() {
        return StartDate;
    }
    public abstract ACCOUNT_TYPE getAccountType();
    public List<Notification> getNotifications() {
        return notifications;
    }

    // === Setter ===
    public void setAccountName(String accountName) {
        this.accountName = accountName;
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
    public void addNotification(Notification notification) { this.notifications.add(notification); }
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
                ", currency='" + currency + '\'' +
                '}';
    }
}