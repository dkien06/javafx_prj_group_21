package com.example.javafx_app.Account;

import com.example.javafx_app.config.Constant;

/**
 * Tài khoản vay vốn:)
 */
public class LoanAccount extends Account {
    private double debt;
    public LoanAccount(Account account){
        this.accountName = account.accountName;
        this.accountID = "SA" + account.accountID;
        this.password = account.password;
        this.PIN = account.PIN;
        this.citizenID = account.citizenID;
        this.currency = account.currency;
        this.debt = Constant.DEFAULT_DEBT;
    }
    public LoanAccount(Account account, double debt){
        this.accountName = account.accountName;
        this.accountID = "SA" + account.accountID;
        this.password = account.password;
        this.PIN = account.PIN;
        this.citizenID = account.citizenID;
        this.currency = account.currency;
        this.debt = debt;
    }

    public double getDebt() {
        return debt;
    }

    //Vay tiền
    public boolean loan(Account account, double amount){
        if(account.getCheckingAccount().deposit(amount)){
            debt += amount;
            return true;
        }
        else return false;
    }
    //Trả nợ
    public boolean repay(Account account, double amount){
        if(account.getCheckingAccount().withdraw(amount)){
            debt -= amount;
            return true;
        }
        else return false;
    }
    //Thêm lãi
    public void applyInterest(){
        debt += debt * Constant.LOAN_INTEREST_RATE_PER_MONTH;
    }
    //Set cảnh cáo
    public void setWarning(Account account){
        if(debt > Constant.LOAN_WARNING_2_MAX){
            account.setWarning(3);
        }
        else if(debt > Constant.LOAN_WARNING_1_MAX){
            account.setWarning(2);
        }
        else if(debt > Constant.LOAN_MAX){
            account.setWarning(1);
        }
        else account.setWarning(0);
    }
}
