package com.example.javafx_app.object.Account;

import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.object.FinancialProduct;
import com.example.javafx_app.object.Transaction;
import com.example.javafx_app.object.TransactionType;

import java.util.ArrayList;
import java.util.List;

public class SavingAccount extends Account {
    private final List<FinancialProduct> savings = new ArrayList<>();
    public SavingAccount(String fullName, String citizenID, String accountID, String password, long balance,
                         String currency, String PIN) {
        super(fullName, citizenID, accountID, password, balance, currency, PIN);
    }
    public List<FinancialProduct> getSavings() {
        return savings;
    }
    public boolean addSavings(FinancialProduct saving) {
        if (!savings.isEmpty() && this.isVIP) return false;
        this.savings.add(saving);
        return true;
    }
    @Override
   public boolean transfer(Account toAccount, long amount,String description) {
       if (toAccount == null || amount <= 0 || amount > balance) {
           return false;
       }
       if(!toAccount.getCitizenID().equals(citizenID)){ return false;}
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

    @Override
    public ACCOUNT_TYPE getAccountType(){ return ACCOUNT_TYPE.SAVING ;}
}
