package com.example.javafx_app.object.Account;

import com.example.javafx_app.manager.TransactionManager;
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
    // ✅ Nạp tiền
    public boolean deposit(Account account, FinancialProduct financialProduct, double amount, String description) {
        return true ;
    }
    // ✅ Rút tiền
    public boolean withdraw(Account account, FinancialProduct financialProduct, double amount, String description) {
        return true ;
    }

    public boolean withdrawAll(Account account, FinancialProduct financialProduct, String description){
        return withdraw(account,financialProduct, financialProduct.getPrincipal(), description);
    }
    @Override
    public ACCOUNT_TYPE getAccountType(){ return ACCOUNT_TYPE.SAVING ;}
}
