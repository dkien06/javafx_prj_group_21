package com.example.javafx_app.Manager;

import com.example.javafx_app.Account.Account;
import com.example.javafx_app.BankApplication;
import com.example.javafx_app.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransactionManager {
    private final static TransactionManager instance = new TransactionManager();
    private TransactionManager(){}


    public static TransactionManager getInstance() {
        return instance;
    }
    public Transaction getCurrentTransaction() {
        return BankManager.currentTransaction;
    }
    public List<Transaction> getTransactionsList() {
        return BankManager.TRANSACTIONS;
    }

    public void newTransaction(Transaction.TransactionType type){
        BankManager.currentTransaction = new Transaction(type,0.0,"VND",null, null,"");
    }
    public void newTransaction(Transaction.TransactionType type, double amount, String currency, Account fromAccount, Account toAccount, String description){
        BankManager.currentTransaction = new Transaction(type,amount,currency,fromAccount, toAccount,description);
    }
    public void removeNewTransaction(){
        BankManager.currentTransaction = null;
    }
    public void addTransaction(Transaction transaction){
        BankManager.TRANSACTIONS.add(transaction);
    }
    public boolean removeTransaction(String transactionID){
        for(Transaction t : BankManager.TRANSACTIONS){
            if(Objects.equals(t.getTransactionID(), transactionID)){
                BankManager.TRANSACTIONS.remove(t);
                return true;
            }
        }
        return false;
    }
    public List<Transaction> getTransactionsByAccount(Account account){
        return account.getHistory();
    }
    public List<Transaction> filterByDate(Account account, LocalDate start, LocalDate end){
        return null;
    }
    public void TransactionsListLog(){
        for(Transaction t : BankManager.TRANSACTIONS){
            System.out.println(
                    "\n\tTransaction ID: " + t.getTransactionID()
                            + "\n\tDate: " + t.getDate()
                            + "\n\tType: " + t.getType()
                            + "\n\tAmount: " + t.getAmount()
                            + "\n\tCurrency: " + t.getCurrency()
                            + "\n\tFrom account: " + t.getFromAccount().getFullName()
                            + "\n\tTo account: " + t.getToAccount().getFullName()
                            + "\n\tDescription: " + t.getDescription()
            );
        }
    }
}
