package com.example.javafx_app.object;

import com.example.javafx_app.config.Constant;
import com.example.javafx_app.manager.TransactionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Tài khoản chuển tiền:)
 */
public class CheckingAccount extends Account{
    private double balance;
    private final List<Transaction> history;
    public CheckingAccount(Account account){
        this.accountName = account.accountName;
        this.password = account.password;
        this.PIN = account.PIN;
        this.citizenID = account.citizenID;
        this.currency = account.currency;
        this.accountID = "CA" + account.accountID;
        this.balance = Constant.DEFAULT_BALANCE;
        this.history = new ArrayList<>();
    }
    public CheckingAccount(Account account, double balance){
        this.accountName = account.accountName;
        this.accountID = "CA" + account.accountID;
        this.password = account.password;
        this.PIN = account.PIN;
        this.citizenID = account.citizenID;
        this.currency = account.currency;
        this.balance = balance;
        this.history = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }
    public List<Transaction> getHistory(){
        return history;
    }

    // ✅ Thêm giao dịch
    public void addTransaction(Transaction t) {
        history.add(t);
    }
    // ✅ Chuyển tiền
    public boolean transfer(CheckingAccount toAccount, double amount, String description) {
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
    // ✅ Rút tiền để tiết kiệm hoặc trả nợ
    public boolean withdraw(double amount) {
        if (amount > 0 && balance > amount) {
            balance -= amount;
            return true;
        }
        else return false;
    }
    // ✅ Nạp tiền từ tiền tiết kiệm hoặc vay mượn
    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        } else return false;
    }
}
