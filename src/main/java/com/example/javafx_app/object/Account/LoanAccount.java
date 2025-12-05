package com.example.javafx_app.object.Account;

public class LoanAccount extends Account {
    private long loan;
    public LoanAccount(String fullName, String citizenID, String accountID, String password, long loan,
                       String currency, String PIN){
        super(fullName, citizenID, accountID, password, currency, PIN);
        this.loan = loan;
    }
    //Vay tiền
    public boolean loan(CheckingAccount account, double amount){
        return true ;
    }
    //Trả nợ
    public boolean repay(CheckingAccount account, double amount){
        return true ;

    }
    public boolean repayAll(Account account, String description){
        return true;
    }
    @Override
    public ACCOUNT_TYPE getAccountType(){ return ACCOUNT_TYPE.LOAN ;}
}
