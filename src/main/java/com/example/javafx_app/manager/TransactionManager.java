package com.example.javafx_app.manager;

import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.object.Transaction;
import com.example.javafx_app.object.TransactionType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransactionManager {
    private final static TransactionManager instance = new TransactionManager();
    private TransactionManager(){}

    private static Transaction currentTransaction;
    private static final List<Transaction> transactions = new ArrayList<>();

    public static TransactionManager getInstance() {
        return instance;
    }
    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }
    public List<Transaction> getTransactionsList() {
        return transactions;
    }

    public void newTransaction(TransactionType type, long amount, String currency, Account fromAccount, Account toAccount, String description){
        currentTransaction = new Transaction(type,amount,currency,fromAccount, toAccount,description);
    }
    public void removeNewTransaction(){
        currentTransaction = null;
    }
    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }
    public boolean removeTransaction(String transactionID){
        for(Transaction t : transactions){
            if(Objects.equals(t.getTransactionID(), transactionID)){
                transactions.remove(t);
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
        for(Transaction t : transactions){
            System.out.println(
                    "\n\tTransaction ID: " + t.getTransactionID()
                    + "\n\tDate: " + t.getDate()
                    + "\n\tType: " + t.getType()
                    + "\n\tAmount: " + t.getAmount()
                    + "\n\tCurrency: " + t.getCurrency()
                    + "\n\tFrom account: " + t.getFromAccount().getAccountName()
                    + "\n\tTo account: " + t.getToAccount().getAccountName()
                    + "\n\tDescription: " + t.getDescription()
            );
        }
    }
}
