package com.example.javafx_app.object.Account;

import com.example.javafx_app.config.Constant;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.BankManager;
import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.object.Transaction;
import com.example.javafx_app.object.TransactionType;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

public class SavingAccount extends Account implements Serializable {
    private long saving;
    private SavingType type;
    private LocalDate startSavingDate;

    private int fixedDuration;
    private long accumulatedAmount;

    public SavingAccount(String fullName, String citizenID, String accountID, String password, long saving,
                         String currency, String PIN) {
        super(fullName, citizenID, accountID, password, currency, PIN);
        this.saving = saving;
        this.type = SavingType.NONE;
        this.startSavingDate = BankManager.getCurrentDate();
        this.fixedDuration = 0;
        this.accumulatedAmount = 0;
    }

    public long getSaving() {
        return saving;
    }
    public LocalDate getStartSavingDate() {
        return startSavingDate;
    }
    public void setStartSavingDate(LocalDate startSavingDate) {
        this.startSavingDate = startSavingDate;
    }
    public SavingType getType() {
        return type;
    }

    public void setType(SavingType type) {
        this.type = type;
    }
    public int getFixedDuration() {
        return fixedDuration;
    }

    public void setFixedDuration(int fixedDuration) {
        this.fixedDuration = fixedDuration;
    }
    public long getAccumulatedAmount() {
        return accumulatedAmount;
    }
    public void setAccumulatedAmount(long accumulatedAmount) {
        this.accumulatedAmount = accumulatedAmount;
    }

    public int getMonthDuration(){
        LocalDate currentDate = LocalDate.now();
        Duration duration = Duration.between(startSavingDate,currentDate);
        Period period = Period.between(startSavingDate,currentDate);
        if(duration.isNegative()) return period.getMonths() - 1;
        else return period.getMonths();
    }
    // ✅ Nạp tiền
    public boolean deposit(CheckingAccount account, long amount, String description) {
        if (!this.citizenID.equals(account.getCitizenID())) {
            System.out.println("Không thể nạp tiền từ tài khoản khác chủ.");
            return false;
        }
        if (account.withdraw(amount,"")) {
            if(saving == 0) startSavingDate = BankManager.getCurrentDate();
            saving += amount;
            Transaction newSaving = new Transaction(TransactionType.DEPOSIT, amount, account.getCurrency(), account, this, description);
            this.addTransaction(newSaving);
            TransactionManager.getInstance().addTransaction(newSaving);
            return true;
        }
        else return false;
    }
    // ✅ Rút tiền
    public boolean withdraw(CheckingAccount account, long amount, String description) {
        if (!this.citizenID.equals(account.getCitizenID())) {
            System.out.println("Không thể rút tiền đến tài khoản khác chủ.");
            return false;
        }
        if (amount <= saving) {
            if(account.deposit(amount,"")){
                saving -= amount;
                Transaction newWithdraw =
                        new Transaction(TransactionType.WITHDRAW,amount,getCurrency(),this,account,description) ;
                this.addTransaction(newWithdraw);
                if(saving == 0) this.type = SavingType.NONE;
                return true;
            }
            else return false;
        } else {
            return false;
        }
    }
    public boolean withdrawAll(CheckingAccount account,String description) {
        return withdraw(account, saving,description);
    }

    public void applyFlexibleInterest(){
        saving = (long)Math.abs(saving * Constant.SAVING_FLEXIBLE_INTEREST_RATE_PER_YEAR);
    }
    public void applyFixedInterest(int duration){
        CheckingAccount checkingAccount = AccountManager.getInstance().findCheckingAccount(this);
        saving = (long) (saving * Math.pow(Constant.SAVING_FIXED_INTEREST_RATE_PER_YEAR, duration));
        withdrawAll(checkingAccount,"");
    }
    public void applyAccumulatedInterest(){
        CheckingAccount checkingAccount = AccountManager.getInstance().findCheckingAccount(this);
        saving = (long)Math.abs(saving * Constant.SAVING_ACCUMULATE_INTEREST_RATE_PER_YEAR);
        if(checkingAccount.withdraw(accumulatedAmount,"")){
            saving += accumulatedAmount;
        }
        else withdrawAll(checkingAccount,"");
    }
    @Override
    public ACCOUNT_TYPE getAccountType(){ return ACCOUNT_TYPE.SAVING ;}
}
