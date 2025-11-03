package com.example.javafx_app.object.Account;

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
    public boolean deposit(Account account, FinancialProduct financialProduct, double amount) {
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
            return true;
        }
        else return false;
    }
    // ✅ Rút tiền
    public boolean withdraw(Account account, FinancialProduct financialProduct, double amount) {
        if (!this.citizenID.equals(account.getCitizenID())) {
            System.out.println("Không thể nạp tiền từ tài khoản khác chủ.");
            return false;
        }
        if(!account.accountID.equals(financialProduct.getAccountID())){
            System.out.println("Không thể nạp tiền từ ví tiết kiệm khác chủ.");
            return false;
        }
        if (amount <= financialProduct.getPrincipal()) {
            if(account.getCheckingAccount().deposit(amount)){
                financialProduct.setPrincipal(financialProduct.getPrincipal() - amount);
                return true;
            }
            else return false;
        } else {
            return false;
        }
    }

    public boolean withdrawAll(Account account, FinancialProduct financialProduct){
        if (!this.citizenID.equals(account.getCitizenID())) {
            System.out.println("Không thể nạp tiền từ tài khoản khác chủ.");
            return false;
        }
        if(!account.accountID.equals(financialProduct.getAccountID())){
            System.out.println("Không thể nạp tiền từ ví tiết kiệm khác chủ.");
            return false;
        }
        if(account.getCheckingAccount().deposit(financialProduct.getPrincipal())){
            financialProduct.setPrincipal(0);
            return true;
        }
        else return false;
    }
    public ACCOUNT_TYPE getAccountType(){ return ACCOUNT_TYPE.SAVING ;}
}
