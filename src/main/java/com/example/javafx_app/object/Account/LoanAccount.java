package com.example.javafx_app.object.Account;

public class LoanAccount extends Account {
    private long debt;
    public LoanAccount(String fullName, String citizenID, String accountID, String password, long debt,
                       String currency, String PIN){
        super(fullName, citizenID, accountID, password, currency, PIN);
        this.debt = debt;
    }
    //Vay tiền
    public boolean loan(CheckingAccount account, long amount){
        if (!this.citizenID.equals(account.getCitizenID())) {
            System.out.println("Không thể nạp tiền từ tài khoản khác chủ.");
            return false;
        }
        if(account.deposit(amount)){
            debt += amount;
            return true;
        }
        else return false;
    }
    //Trả nợ
    public boolean repay(CheckingAccount account, long amount){
        if (!this.citizenID.equals(account.getCitizenID())) {
            System.out.println("Không thể nạp tiền từ tài khoản khác chủ.");
            return false;
        }
        if(account.withdraw(amount)){
            debt -= amount;
            return true;
        }
        else return false;
    }
    public boolean repayAll(CheckingAccount account){
        return repay(account, debt);
    }
    @Override
    public ACCOUNT_TYPE getAccountType(){ return ACCOUNT_TYPE.LOAN ;}
}
