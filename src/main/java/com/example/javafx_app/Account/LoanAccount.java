package com.example.javafx_app.Account;

import java.util.List;

public class LoanAccount extends Account {
    private List<FinancialProduct> loans;
    public LoanAccount(String citizenID, String accountID,String AccountName, String password, double balance,
                       String currency, String PIN) {
        super(citizenID, accountID, password, balance, currency, PIN) ;
    }
    public List<FinancialProduct> getSavings() {
        return loans;
    }
    public boolean addLoan(FinancialProduct loan) {
        if(!loans.isEmpty()&&!this.isVIP) return false ;
        loans.add(loan);
        return true;
    }
    public ACCOUNT_TYPE getAccountType(){ return ACCOUNT_TYPE.LOAN ;}
}
