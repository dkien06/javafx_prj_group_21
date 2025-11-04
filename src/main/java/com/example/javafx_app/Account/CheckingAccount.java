package com.example.javafx_app.Account;

public class CheckingAccount extends Account {
    public CheckingAccount(String citizenID, String accountID,String AccountName, String password, double balance,
                           String currency, String PIN) {
        super(citizenID,accountID,password,balance,currency,PIN);
    }
    public ACCOUNT_TYPE getAccountType(){
        return ACCOUNT_TYPE.CHECKING;
    }
}
