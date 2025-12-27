package com.example.javafx_app.manager;

import com.example.javafx_app.convert.NumberToVietnameseWord;
import com.example.javafx_app.object.Bill.Bill;
import com.example.javafx_app.object.Noti.Notification;
import com.example.javafx_app.object.Noti.NotificationType;
import com.example.javafx_app.object.Transaction;

import java.time.LocalDate;

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
    public static Notification getNotiForServiceCancellation(String serviceName, LocalDate oldestDate) {
        String title = NotificationType.BILL_CANCELLATION.toString();
        String message = String.format(
                "Dịch vụ %s của bạn đã bị tạm dừng do có hóa đơn từ ngày %s đã quá hạn 3 tháng. Vui lòng thanh toán để khôi phục.",
                serviceName,
                oldestDate.toString()
        );

        // Sử dụng NotificationType phù hợp (ví dụ TRANSFER_FAILED hoặc bổ sung loại mới)
        return new Notification(
                NotificationType.BILL_CANCELLATION,
                title,
                message
        );
    }
    public static Notification getNotiForSavingUpdate(String accountID, long newBalance, long totalMonths) {
        // Giả định bạn có loại thông báo SAVING_UPDATE trong enum NotificationType
        // Nếu chưa có, bạn có thể dùng một loại mặc định hoặc bổ sung vào enum
        String title = "CẬP NHẬT TIỀN TIẾT KIỆM";

        String message = String.format(
                "Tài khoản tiết kiệm %s của bạn đã được cộng lãi. Số tiền hiện tại đã tăng lên thành %d VND sau %d tháng tích lũy.",
                accountID,
                newBalance,
                totalMonths
        );

        return new Notification(
                NotificationType.SAVING_UPDATE, // Bạn cần đảm bảo loại này tồn tại trong NotificationType
                title,
                message
        );
    }public static Notification getNotiForFixedSavingMaturity(String accountID, long amount, String targetAccountID) {
        // Tiêu đề thông báo
        String title = "TẤT TOÁN TIẾT KIỆM ĐẾN HẠN";

        // Nội dung thông báo chi tiết
        String message = String.format(
                "Khoản tiết kiệm  của bạn đã đến kỳ hạn nhận. Tổng số tiền %d VND (bao gồm gốc và lãi) đã được chuyển về tài khoản thanh toán %s.",
                amount,
                targetAccountID
        );

        return new Notification(
                NotificationType.SAVING_UPDATE, // Giả định bạn có loại này trong NotificationType
                title,
                message
        );
    }
}
