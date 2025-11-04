package com.example.javafx_app.object.Account;

import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.object.Transaction;
import com.example.javafx_app.object.TransactionType;

import java.util.ArrayList;
import java.util.List;

public class SavingAccount extends Account {
    private final List<FinancialProduct> savings = new ArrayList<>();
    public SavingAccount(Account account){
        this.accountName = account.accountName;
        this.accountID = account.accountID;
        this.password = account.password;
        this.PIN = account.PIN;
        this.citizenID = account.citizenID;
        this.currency = account.currency;
    }
    public List<FinancialProduct> getSavings() {
        return savings;
    }
    public boolean addSavings(FinancialProduct saving) {
        if (!savings.isEmpty() && this.isVIP) return false;
        this.savings.add(saving);
        return true;
    }
    // ✅ Nạp tiền
    public boolean deposit(Account account, FinancialProduct financialProduct, double amount, String description) {
        if (!this.citizenID.equals(account.getCitizenID())) {
            System.out.println("Không thể nạp tiền từ tài khoản khác chủ.");
            return false;
        }
        if(!account.accountID.equals(financialProduct.getAccountID())){
            System.out.println("Không thể nạp tiền từ ví tiết kiệm khác chủ.");
            return false;
        }
        if (account.getCheckingAccount().withdraw(amount)) {
            financialProduct.setPrincipal(financialProduct.getPrincipal() + amount);
            Transaction newTransaction = new Transaction(TransactionType.DEPOSIT, amount, this.currency, this,null,description);
            this.addTransaction(newTransaction);
            TransactionManager.getInstance().addTransaction(newTransaction);
            return true;
        }
        else return false;
    }
    // ✅ Rút tiền
    public boolean withdraw(Account account, FinancialProduct financialProduct, double amount, String description) {
        if (!this.citizenID.equals(account.getCitizenID())) {
            System.out.println("Không thể nạp tiền từ tài khoản khác chủ.");
            return false;
        }
        if(!account.accountID.equals(financialProduct.getAccountID())){
            System.out.println("Không thể nạp tiền từ ví tiết kiệm khác chủ.");
            return false;
        }
        if (amount <= financialProduct.getPrincipal()) {
            if (account.getCheckingAccount().deposit(amount)) {
                financialProduct.setPrincipal(financialProduct.getPrincipal() - amount);
                Transaction newTransaction = new Transaction(TransactionType.WITHDRAW, amount, this.currency, null, this, description);
                this.addTransaction(newTransaction);
                TransactionManager.getInstance().addTransaction(newTransaction);
            } else return false;
        }
        return false;
    }

    public boolean withdrawAll(Account account, FinancialProduct financialProduct, String description){
        return withdraw(account,financialProduct, financialProduct.getPrincipal(), description);
    }
    public ACCOUNT_TYPE getAccountType(){ return ACCOUNT_TYPE.SAVING ;}
}
