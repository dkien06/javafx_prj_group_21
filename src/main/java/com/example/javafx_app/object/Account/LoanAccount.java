package com.example.javafx_app.object.Account;

import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.object.Transaction;
import com.example.javafx_app.object.TransactionType;

import java.util.List;

public class LoanAccount extends Account {
    private List<FinancialProduct> loans;
    public LoanAccount(Account account){
        this.accountName = account.accountName;
        this.accountID = account.accountID;
        this.password = account.password;
        this.PIN = account.PIN;
        this.citizenID = account.citizenID;
        this.currency = account.currency;
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
        if (!this.citizenID.equals(account.getCitizenID())) {
            System.out.println("Không thể nạp tiền từ tài khoản khác chủ.");
            return false;
        }
        if(!account.accountID.equals(financialProduct.getAccountID())){
            System.out.println("Không thể nạp tiền từ ví tiết kiệm khác chủ.");
            return false;
        }
        if(account.getCheckingAccount().deposit(amount)){
            financialProduct.setPrincipal(financialProduct.getPrincipal() + amount);
            Transaction newTransaction = new Transaction(TransactionType.LOAN, amount, this.currency, null,this,description);
            this.addTransaction(newTransaction);
            TransactionManager.getInstance().addTransaction(newTransaction);
            return true;
        }
        else return false;
    }
    //Trả nợ
    public boolean repay(Account account, FinancialProduct financialProduct, double amount, String description){
        if (!this.citizenID.equals(account.getCitizenID())) {
            System.out.println("Không thể nạp tiền từ tài khoản khác chủ.");
            return false;
        }
        if(!account.accountID.equals(financialProduct.getAccountID())){
            System.out.println("Không thể nạp tiền từ ví tiết kiệm khác chủ.");
            return false;
        }
        if(account.getCheckingAccount().withdraw(amount)){
            financialProduct.setPrincipal(financialProduct.getPrincipal() - amount);
            Transaction newTransaction = new Transaction(TransactionType.REPAY, amount, this.currency, this,null,description);
            this.addTransaction(newTransaction);
            TransactionManager.getInstance().addTransaction(newTransaction);
            return true;
        }
        else return false;
    }
    public boolean repayAll(Account account, FinancialProduct financialProduct, String description){
        return repay(account,financialProduct, financialProduct.getPrincipal(), description);
    }
    public ACCOUNT_TYPE getAccountType(){ return ACCOUNT_TYPE.LOAN ;}
}
