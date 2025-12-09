package com.example.javafx_app.convert;

import com.example.javafx_app.manager.AccountManager;

public class NumberToVietnameseWord {
    private NumberToVietnameseWord(){};
    private static final String[] soDonVi = {"", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín"};
    private static final String[] hangChuc = {"", "mười", "hai mươi", "ba mươi", "bốn mươi", "năm mươi", "sáu mươi", "bảy mươi", "tám mươi", "chín mươi"};
    private static final String[] hangTram = {"không trăm", "một trăm", "hai trăm", "ba trăm", "bốn trăm", "năm trăm", "sáu trăm", "bảy trăm", "tám trăm", "chín trăm"};
    private static String docSoBaChuSo(int baChuSo) {
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
    public static String numberToVietnameseWords(long amount){
        if (amount == 0) return "không";

        // ================================================================
        // SỬA LỖI 1: Thêm dòng bảo vệ
        // Giới hạn ở dưới 1 triệu tỷ (1,000,000,000,000,000)
        // Bạn có thể tăng giới hạn này nếu mở rộng thêm mảng 'hangNghin'
        if (amount >= 1_000_000_000_000_000L) {
            return "Số tiền quá lớn"; // Trả về thông báo lỗi, không chạy tiếp
        }
        // ================================================================

        String sAmount = String.valueOf(amount);
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
    public static String displayError(String value){
        try {
            if (!value.isEmpty() && value.matches("\\d+")) {
                long amount = Long.parseLong(value);
                if(amount > AccountManager.getInstance().findCheckingAccount(AccountManager.getInstance().getCurrentAccount()).getBalance())
                    return "Số tiền bạn nhập không đủ để chuyển";
                else return "";
            } else {
                if(value.isEmpty())return "Vui lòng nhập số tiền";
                else return  "Số tiền không hợp lệ";
            }
        } catch (NumberFormatException e) {
            return  "Số tiền không hợp lệ";
        }
    }
}
