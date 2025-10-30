package com.example.javafx_app.controller.Transaction;

import com.example.javafx_app.*;
import com.example.javafx_app.Manager.AccountManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TransactingBetweenAccountsController implements Initializable {
    @FXML
    private TextField sendingAccountIDTextField;
    @FXML
    private TextField currentBalanceTextField;
    @FXML
    private RadioButton localBank;
    @FXML
    private RadioButton otherBank;
    @FXML
    private ChoiceBox<String> bankChoiceBox;
    private final String[] banks = {"MB Bank","BIDV","Techcombank","etc..."}; //Tự add đi
    @FXML
    private Text bankChoiceErrorLog;
    @FXML
    private TextField receiveAccountIDTextField;
    @FXML
    private Text receiveAccountIDErrorLog;
    @FXML
    private TextField amountTextField;
    @FXML
    private Text amountInWordsText;
    @FXML
    private Text amountErrorLog;
    @FXML
    private TextArea descriptionTextArea;
    private static final String[] soDonVi = {"", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín"};
    private static final String[] hangChuc = {"", "mười", "hai mươi", "ba mươi", "bốn mươi", "năm mươi", "sáu mươi", "bảy mươi", "tám mươi", "chín mươi"};
    private static final String[] hangTram = {"không trăm", "một trăm", "hai trăm", "ba trăm", "bốn trăm", "năm trăm", "sáu trăm", "bảy trăm", "tám trăm", "chín trăm"};
    void displaySendingAccountIDAndMoney(Account account){
        sendingAccountIDTextField.setText(account.getAccountID());
        currentBalanceTextField.setText(account.getBalance() + " " + account.getCurrency());
        descriptionTextArea.setText(AccountManager.getInstance().getCurrentAccount().getFullName() + " CHUYEN TIEN");
    }
    void loadTransaction(Account account, Transaction transaction){
        if(transaction != null){
            sendingAccountIDTextField.setText(account.getAccountID());
            currentBalanceTextField.setText(Double.toString(account.getBalance()));
            receiveAccountIDTextField.setText(transaction.getToAccount().getAccountID());
            amountTextField.setText(Integer.toString((int)transaction.getAmount()));
            descriptionTextArea.setText(transaction.getDescription());
        }
    }
    @FXML
    void allowChoosingBank(){
        bankChoiceBox.setDisable(!otherBank.isSelected());
    }
    @FXML
    void disallowChoosingBank(){
        bankChoiceBox.setValue("");
        bankChoiceBox.setDisable(localBank.isSelected());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bankChoiceBox.getItems().addAll(banks);
        amountTextField.textProperty().addListener((observable, _, value) -> {
            // Xử lý khi giá trị text thay đổi
            try {
                //newValue.matches("\\d+") -> Check xem biến newValue có viết dưới dạng số không)
                if (!value.isEmpty() && value.matches("\\d+")) {
                    long amount = Long.parseLong(value);
                    if(amount > AccountManager.getInstance().getCurrentAccount().getBalance()){
                        amountErrorLog.setText("Số tiền bạn nhập không đủ để chuyển");
                    }
                    else amountErrorLog.setText("");
                    String amountInWords = numberToVietnameseWords(amount);
                    amountInWordsText.setText(amountInWords + " đồng");
                } else {
                    amountInWordsText.setText("");
                    amountErrorLog.setText("Số tiền không hợp lệ");
                }
            } catch (NumberFormatException e) {
                amountInWordsText.setText("");
                amountErrorLog.setText("Số tiền không hợp lệ");
            }
        });
    }
    private String numberToVietnameseWords(double amount){
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
        String ketQua = "";

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
                ketQua = chuoiBaSo + hangNghin[i] + " " + ketQua;
            }
        }

        // Xử lý các trường hợp đặc biệt
        ketQua = ketQua.replaceAll("một mươi", "mười");
        ketQua = ketQua.replaceAll("mười năm", "mười lăm");
        ketQua = ketQua.replaceAll("mươi năm", "mươi lăm");
        ketQua = ketQua.replaceAll("mươi một", "mươi mốt");

        if (ketQua.startsWith("một nghìn ")) {
            ketQua = ketQua.substring(4);
        }

        ketQua = ketQua.trim();
        // Viết hoa chữ cái đầu
        if (ketQua.isEmpty()) return "Không đồng"; // Đề phòng trường hợp không mong muốn
        return ketQua.substring(0, 1).toUpperCase() + ketQua.substring(1);
    }


    @FXML
    void QuayLai(ActionEvent event){
        BankApplication.TransactionManager.getInstance().removeNewTransaction();
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"transaction_choose_method_scene.fxml");
    }
    @FXML
    void TiepTuc(ActionEvent event) throws IOException {
        boolean isBankChoiceValid = false;
        if((bankChoiceBox.getValue() == null || bankChoiceBox.getValue().isEmpty()) && otherBank.isSelected())
            bankChoiceErrorLog.setText("Vui lòng chọn ngân hang");
        else {
            bankChoiceErrorLog.setText("");
            isBankChoiceValid = true;
        }

        Account receiveAccount = new Account();
        boolean isReceiveAccountIDValid = false;
        if(receiveAccountIDTextField.getText().isEmpty())
            receiveAccountIDErrorLog.setText("Vui lòng nhập tài khoản nhận");
        else{
            receiveAccountIDErrorLog.setText("");
            if(localBank.isSelected()){
                receiveAccount = AccountManager.getInstance().findAccount(receiveAccountIDTextField.getText());
                if(receiveAccount == null){
                    receiveAccountIDErrorLog.setText("Tài khoản nhận không tồn tại");
                }
                else isReceiveAccountIDValid = true;
            }
            else{
                //Méo biết
            }
        }

        boolean isAmountValid = !amountTextField.getText().isEmpty() && amountTextField.getText().matches("\\d+");
        if(!isAmountValid)amountErrorLog.setText("Vui lòng nhập số tiền hợp lệ");

        if(isAmountValid && isReceiveAccountIDValid && isBankChoiceValid){
            BankApplication.TransactionManager.getInstance().newTransaction(
                    Transaction.TransactionType.TRANSFER,
                    Double.parseDouble(amountTextField.getText()),
                    "VND",
                    AccountManager.getInstance().getCurrentAccount(),
                    receiveAccount,
                    descriptionTextArea.getText());
            FXMLLoader nextSceneLoader = new FXMLLoader(SceneUtils.class.getResource("verify_transaction.scene.fxml"));
            Parent nextSceneRoot = nextSceneLoader.load();

            VerifyTransactionController controller = nextSceneLoader.getController();
            controller.displayTransactionInformation(BankApplication.TransactionManager.getInstance().getCurrentTransaction());

            SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),nextSceneRoot);
        }
    }
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
}
