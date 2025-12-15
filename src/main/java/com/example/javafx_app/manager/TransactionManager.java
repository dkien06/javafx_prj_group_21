package com.example.javafx_app.manager;

import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.object.Transaction;
import com.example.javafx_app.object.TransactionType;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class TransactionManager {
    private final static TransactionManager instance = new TransactionManager();
    private TransactionManager(){}

    private static Transaction currentTransaction;
    private static  List<Transaction> transactions = new ArrayList<>();

    public static TransactionManager getInstance() {
        return instance;
    }
    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }
    public List<Transaction> getTransactionsList() {
        return transactions;
    }
    // Thêm setter để lớp Persistence có thể ghi đè dữ liệu
    public void setTransactionsList(List<Transaction> newTransactionsList) {
        if (newTransactionsList != null) {
            transactions.clear();
            transactions.addAll(newTransactionsList);
        }
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
    public  String formatCurrency(long balance, String currency) {
        // 1. Khởi tạo ký hiệu định dạng cho Locale Việt Nam (vi_VN)
        Locale vietnameseLocale = new Locale("vi", "VN");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(vietnameseLocale);

        // 2. Đặt dấu phân cách hàng nghìn là dấu chấm (.), theo chuẩn Việt Nam
        symbols.setGroupingSeparator('.');

        // 3. Định nghĩa format với pattern "#,###" và symbols đã định nghĩa
        // Pattern này đảm bảo dấu phân cách hàng nghìn sẽ được áp dụng.
        DecimalFormat formatter = new DecimalFormat("#,###", symbols);

        // 4. Định dạng số tiền
        String formattedBalance = formatter.format(balance);

        // 5. Trả về chuỗi kết hợp với mã tiền tệ
        return formattedBalance + " " + currency;
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
