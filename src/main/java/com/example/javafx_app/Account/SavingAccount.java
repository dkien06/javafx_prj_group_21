package com.example.javafx_app.Account;

import com.example.javafx_app.config.Constant;

/**
 * Tài khoản tiết kiệm:)
 * Cần thêm lịch sử giao dịch đoạn này không nhở:))
 */
public class SavingAccount extends Account {
    private double balance;
    public SavingAccount(Account account){
        this.accountName = account.accountName;
        this.accountID = "SA" + account.accountID;
        this.password = account.password;
        this.PIN = account.PIN;
        this.citizenID = account.citizenID;
        this.currency = account.currency;
        this.balance = Constant.DEFAULT_BALANCE;
    }
    public SavingAccount(Account account, double balance){
        this.accountName = account.accountName;
        this.accountID = "SA" + account.accountID;
        this.password = account.password;
        this.PIN = account.PIN;
        this.citizenID = account.citizenID;
        this.currency = account.currency;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    // ✅ Nạp tiền
    public boolean deposit(Account account, double amount) {
        if (!this.citizenID.equals(account.getCitizenID())) {
            System.out.println("Không thể nạp tiền từ tài khoản khác chủ.");
            return false;
        }
        if (account.getCheckingAccount().withdraw(amount)) {
            balance += amount;
            return true;
        }
        else return false;
    }
    // ✅ Rút tiền
    public boolean withdraw(Account account,double amount) {
        if (!this.citizenID.equals(account.getCitizenID())) {
            System.out.println("Không thể nạp tiền từ tài khoản khác chủ.");
            return false;
        }
        if (amount <= balance) {
            if(account.getCheckingAccount().deposit(amount)){
                balance -= amount;
                return true;
            }
            else return false;
        } else {
            return false;
        }
    }
    public boolean withdrawAll(Account account){
        if (!this.citizenID.equals(account.getCitizenID())) {
            System.out.println("Không thể nạp tiền từ tài khoản khác chủ.");
            return false;
        }
        if(account.getCheckingAccount().deposit(balance)){
            balance = 0;
            return true;
        }
        else return false;
    }
    // ✅ Nhận lãi
    public void applyMonthlyInterest/*???*/(){
        balance += balance * Constant.SAVING_INTEREST_RATE_PER_MONTH;
    }
}
