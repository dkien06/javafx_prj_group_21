package com.example.javafx_app.manager;

import com.example.javafx_app.object.Account;
import com.example.javafx_app.object.Transaction;

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

    public void newTransaction(Transaction.TransactionType type){
        currentTransaction = new Transaction(type,0.0,"VND",null, null,"");
    }
    public void newTransaction(Transaction.TransactionType type, double amount, String currency, Account fromAccount, Account toAccount, String description){
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
        return account.getCheckingAccount().getHistory();
    }
    public List<Transaction> filterByDate(Account account, LocalDate start, LocalDate end){
        return null;
    }

    private static final String[] soDonVi = {"", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín"};
    private static final String[] hangChuc = {"", "mười", "hai mươi", "ba mươi", "bốn mươi", "năm mươi", "sáu mươi", "bảy mươi", "tám mươi", "chín mươi"};
    private static final String[] hangTram = {"không trăm", "một trăm", "hai trăm", "ba trăm", "bốn trăm", "năm trăm", "sáu trăm", "bảy trăm", "tám trăm", "chín trăm"};
    private String docSoBaChuSo(int baChuSo) {
        int tram = baChuSo / 100;
        int chuc = (baChuSo % 100) / 10;
        int donvi = baChuSo % 10;
        String ketQua = "";

        if (tram > 0) {
            ketQua += hangTram[tram] + " ";
        }
        if (chuc > 0) {
            if (chuc == 1) {
                ketQua += "mười ";
            } else {
                ketQua += hangChuc[chuc] + " ";
            }
        } else if (tram > 0 && donvi > 0) {
            ketQua += "linh ";
        }
        if (donvi > 0) {
            if (chuc > 1 && donvi == 1) {
                ketQua += "mốt";
            } else if (chuc >= 1 && donvi == 5) {
                ketQua += "lăm";
            } else {
                ketQua += soDonVi[donvi];
            }
        }
        return ketQua.trim();
    }
    public String numberToVietnameseWords(double amount){
        long longAmount = (long) amount;
        if (longAmount == 0) return "không";

        // ================================================================
        // SỬA LỖI 1: Thêm dòng bảo vệ
        // Giới hạn ở dưới 1 triệu tỷ (1,000,000,000,000,000)
        // Bạn có thể tăng giới hạn này nếu mở rộng thêm mảng 'hangNghin'
        if (longAmount >= 1_000_000_000_000_000L) {
            return "Số tiền quá lớn"; // Trả về thông báo lỗi, không chạy tiếp
        }
        // ================================================================

        String sAmount = String.valueOf(longAmount);
        StringBuilder ketQua = new StringBuilder();

        // ================================================================
        // SỬA LỖI 2: Mở rộng mảng 'hangNghin'
        // Mảng cũ: {"", " nghìn", " triệu", " tỷ"}; (có 4 phần tử -> GÂY LỖI)
        // Mảng mới (ĐÃ SỬA):
        String[] hangNghin = {"", " nghìn", " triệu", " tỷ", " nghìn tỷ", " triệu tỷ"};
        // Giờ đây mảng có 6 phần tử, có thể truy cập đến index 5
        // ================================================================

        int soNhom = (int) Math.ceil(sAmount.length() / 3.0);

        for (int i = 0; i < soNhom; i++) {
            int startIndex = Math.max(0, sAmount.length() - 3 * (i + 1));
            int endIndex = sAmount.length() - 3 * i;
            int nhomBaSo = Integer.parseInt(sAmount.substring(startIndex, endIndex));

            if (nhomBaSo > 0) {
                // Dòng bảo vệ thứ 2, đề phòng i vượt quá mảng
                if (i >= hangNghin.length) {
                    break; // Ngừng đọc nếu số quá lớn
                }
                String chuoiBaSo = docSoBaChuSo(nhomBaSo);
                ketQua.insert(0, chuoiBaSo + hangNghin[i] + " ");
            }
        }

        // Xử lý các trường hợp đặc biệt
        ketQua = new StringBuilder(ketQua.toString().replaceAll("một mươi", "mười"));
        ketQua = new StringBuilder(ketQua.toString().replaceAll("mười năm", "mười lăm"));
        ketQua = new StringBuilder(ketQua.toString().replaceAll("mươi năm", "mươi lăm"));
        ketQua = new StringBuilder(ketQua.toString().replaceAll("mươi một", "mươi mốt"));

        if (ketQua.toString().startsWith("một nghìn ")) {
            ketQua = new StringBuilder(ketQua.substring(4));
        }

        ketQua = new StringBuilder(ketQua.toString().trim());
        // Viết hoa chữ cái đầu
        if (ketQua.isEmpty()) return "Không đồng"; // Đề phòng trường hợp không mong muốn
        return ketQua.substring(0, 1).toUpperCase() + ketQua.substring(1);
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
