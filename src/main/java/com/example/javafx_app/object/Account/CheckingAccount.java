package com.example.javafx_app.object.Account;

import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.object.Bill.Bill;
import com.example.javafx_app.object.Transaction;
import com.example.javafx_app.object.TransactionType;

import java.util.ArrayList;
import java.util.List;

/**
 * Tài khoản chuển tiền:)
 */
public class CheckingAccount extends Account {
    private List<Bill> bills;
    public CheckingAccount(String fullName, String citizenID, String accountID, String password, long balance, String currency, String PIN){
        super(fullName, citizenID, accountID, password, balance, currency,  PIN);
        bills = new ArrayList<>() ;
    }

    public long getBalance() {
        return balance;
    }
    // ✅ Chuyển tiền
    public boolean transfer(Account toAccount, long amount, String description) {
        if (toAccount == null || amount <= 0 || amount > balance) {
            return false;
        }

        // rút tiền bên gửi
        this.balance -= amount;
        // nạp tiền bên nhận
        toAccount.balance += amount;

        // thêm lịch sử giao dịch cho cả 2
        Transaction newTransfer = new Transaction(TransactionType.TRANSFER, amount, "VND", this, toAccount, description);
        this.addTransaction(newTransfer);
        TransactionManager.getInstance().addTransaction(newTransfer);

        Transaction newReceive = new Transaction(TransactionType.TRANSFER, -amount, "VND", this, toAccount, description);
        toAccount.addTransaction(newReceive);
        return true;
    }
    // ✅ Rút tiền để tiết kiệm hoặc trả nợ
    public boolean withdraw(long amount) {
        if (amount > 0 && balance > amount) {
            balance -= amount;
            return true;
        }
        else return false;
    }
    // ✅ Nạp tiền từ tiền tiết kiệm hoặc vay mượn
    public boolean deposit(long amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        } else return false;
    }
    @Override
    public ACCOUNT_TYPE getAccountType(){ return ACCOUNT_TYPE.CHECKING ;}
    public void addBill(Bill bill){
        bills.add(bill);
    }
    public List<Bill> getBills(){
        return bills;
    }
    public boolean removeBill(Bill bill) {
        // List.remove(Object o) sẽ sử dụng phương thức Bill.equals()
        return bills.remove(bill);
    }
}
