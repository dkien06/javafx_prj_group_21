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
import java.time.temporal.ChronoUnit;

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
    private void setStartSavingDate(LocalDate startSavingDate) {
        this.startSavingDate = startSavingDate;
    }
    public SavingType getType() {
        return type;
    }

    public void setType(SavingType type) {
        setStartSavingDate(BankManager.getCurrentDate());
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

    public int getMonthDuration() {
        LocalDate currentDate = BankManager.getCurrentDate(); // Nên dùng ngày hệ thống của BankManager cho đồng bộ

        if (currentDate.isBefore(startSavingDate)) {
            return 0; // Hoặc xử lý lỗi tùy bạn
        }

        // Cách 1: Dùng ChronoUnit (Khuyên dùng - Ngắn gọn và chính xác)
        return (int) ChronoUnit.MONTHS.between(startSavingDate, currentDate);
    }
    public boolean isOverdue() {
        // 1. Lấy số tháng thực tế đã trôi qua kể từ ngày bắt đầu gửi
        int currentMonths = getMonthDuration();

        // 2. So sánh với thời hạn đã đăng ký (fixedDuration)
        // Nếu số tháng trôi qua lớn hơn hoặc bằng thời hạn cam kết thì trả về true
        if (currentMonths >= this.fixedDuration) {
            return true;
        }

        // Ngược lại, vẫn đang trong kỳ hạn tiết kiệm
        return false;
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

    public void applyFlexibleInterest() {
        // Formula: Balance = Balance * (1 + rate)
        double rate = Constant.SAVING_FLEXIBLE_INTEREST_RATE_PER_MONTH;
        saving = (long) (saving * (1.0 + rate));
    }

    public long applyFixedInterest() {
        CheckingAccount checkingAccount = AccountManager.getInstance().findCheckingAccount(this);

        // Formula: Balance = Balance * (1 + rate)^duration
        double rate = Constant.SAVING_FIXED_INTEREST_RATE_PER_MONTH;
        saving = (long) (saving * Math.pow(1.0 + rate, fixedDuration));
        long temp = saving;
        // Transfer final balance to checking
        withdrawAll(checkingAccount, "");
        return temp;
    }

    public void applyAccumulatedInterest() {
        CheckingAccount checkingAccount = AccountManager.getInstance().findCheckingAccount(this);

        // Calculate interest gained this period
        long interestGained = (long) (saving * Constant.SAVING_ACCUMULATE_INTEREST_RATE_PER_MONTH);
        saving += interestGained;

        // Try to move additional funds from checking to saving
        if (checkingAccount.withdraw(accumulatedAmount, "")) {
            saving += accumulatedAmount;
        } else {
           withdrawAll(checkingAccount, "");
        }
    }
    @Override
    public ACCOUNT_TYPE getAccountType(){ return ACCOUNT_TYPE.SAVING ;}
}
