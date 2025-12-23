package com.example.javafx_app.manager;

import com.example.javafx_app.convert.NumberToVietnameseWord;
import com.example.javafx_app.object.Bill.Bill;
import com.example.javafx_app.object.Noti.Notification;
import com.example.javafx_app.object.Noti.NotificationType;
import com.example.javafx_app.object.Transaction;

public class NotiManager {
    public static boolean isMarkedAsRead = false;
    public static Notification getNotifromTransaction(Transaction t) {
        // 1. Chuẩn bị dữ liệu
        long absoluteAmount = Math.abs(t.getAmount());
        String formattedAmount = TransactionManager.getInstance().formatCurrency(absoluteAmount, t.getCurrency());
        String amountInWords = NumberToVietnameseWord.numberToVietnameseWords(absoluteAmount );

        String title;
        String message;
        NotificationType notiType = NotificationType.BALANCE_CHANGE;

        // 2. Logic tạo thông báo
        switch (t.getType()) {
            case TRANSFER:
                if (t.getAmount() > 0) {
                    // Tiền chuyển đi (t.getAmount() dương trong log của người gửi)
                    title = "Chuyển tiền thành công";
                    message = String.format("Bạn đã chuyển %s (%s) đến tài khoản %s. Nội dung: %s.",
                            formattedAmount, amountInWords,
                            t.getToAccount().getAccountName(), t.getDescription());
                } else {
                    // Tiền nhận được (t.getAmount() âm trong log của người nhận)
                    title = "Nhận tiền thành công";
                    message = String.format("Bạn đã nhận %s (%s) từ tài khoản %s. Nội dung: %s.",
                            formattedAmount, amountInWords,
                            t.getFromAccount().getAccountName(), t.getDescription());
                }
                break;
            case DEPOSIT:
                title = "Giao dịch Nạp tiền";
                message = String.format("Bạn đã nạp %s (%s) vào tài khoản.", formattedAmount, amountInWords);
                break;
            case WITHDRAW:
                title = "Giao dịch Rút tiền";
                message = String.format("Bạn đã rút %s (%s) khỏi tài khoản.", formattedAmount, amountInWords);
                break;
            case LOAN:
                title = "Khoản Vay Mới";
                message = String.format("Khoản vay %s (%s) đã được giải ngân vào tài khoản của bạn.", formattedAmount, amountInWords);
                break;
            case REPAY:
                title = "Thanh toán khoản Vay";
                message = String.format("Bạn đã thanh toán khoản nợ %s (%s).", formattedAmount, amountInWords);
                break;
            default:
                title = "Giao dịch không xác định";
                message = String.format("Giao dịch số %s với số tiền %s.", t.getTransactionID(), formattedAmount);
                notiType = NotificationType.TRANSFER_FAILED;
                break;
        }

        // 3. Tạo và trả về Notification mới
        return new Notification(
                notiType,
                title,
                message
        );
    }
    public static Notification getNotifromBill(Bill bill) {
        String title = "Thông báo Hóa đơn đến hạn";

        // Sử dụng hàm toNotificationString() đã định nghĩa trong class Bill
        // để tạo chuỗi thông báo chi tiết: "Thông báo: Hóa đơn [Loại] trị giá [Số tiền] của [NCC] đến hạn thanh toán vào [Ngày]."
        String message = bill.toNotificationString();

        // Sử dụng NotificationType.BALANCE_CHANGE làm loại thông báo chung
        // (Có thể thay đổi thành NotificationType.BILL_DUE nếu bạn định nghĩa thêm loại này)
        NotificationType notiType = NotificationType.BALANCE_CHANGE;

        return new Notification(
                notiType,
                title,
                message
        );
    }
}
