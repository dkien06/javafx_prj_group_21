package com.example.javafx_app.Account;

import java.util.List;

public class SavingAccount extends Account {
    private List<FinancialProduct> savings;
    public SavingAccount(String citizenID, String accountID,String AccountName, String password, double balance,
                       String currency, String PIN) {
        super(citizenID, accountID, password, balance, currency, PIN) ;
    }
    public List<FinancialProduct> getSavings() {
        return savings;
    }
    public boolean addSavings(FinancialProduct saving) {
        if(!savings.isEmpty()&&!this.isVIP) return false ;
        this.savings.add(saving);
        return true;
    }
    public ACCOUNT_TYPE getAccountType(){ return ACCOUNT_TYPE.SAVING ;}
}
