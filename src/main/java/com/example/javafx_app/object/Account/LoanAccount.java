package com.example.javafx_app.object.Account;

import com.example.javafx_app.object.FinancialProduct;

import java.util.ArrayList;
import java.util.List;

public class LoanAccount extends Account {
    private final List<FinancialProduct> loans = new ArrayList<FinancialProduct>();
    public LoanAccount(String fullName, String citizenID, String accountID, String password,double balance,
                       String currency, String PIN){
        super(fullName, citizenID, accountID, password, balance, currency, PIN);
    }
    public List<FinancialProduct> getLoans() {
        return loans;
    }
    public boolean addLoan(FinancialProduct loan) {
        if(!loans.isEmpty()&&!this.isVIP) return false ;
        loans.add(loan);
        return true;
    }
    //Vay tiền
    public boolean loan(Account account,FinancialProduct financialProduct, double amount, String description){
        return true ;
    }
    //Trả nợ
    public boolean repay(Account account, FinancialProduct financialProduct, double amount, String description){
        return true ;

    }
    public boolean repayAll(Account account, FinancialProduct financialProduct, String description){
        return repay(account,financialProduct, financialProduct.getPrincipal(), description);
    }
    @Override
    public ACCOUNT_TYPE getAccountType(){ return ACCOUNT_TYPE.LOAN ;}
}
