package com.example.javafx_app.manager;
import com.example.javafx_app.object.Bill.Bill;
import com.example.javafx_app.object.Bill.BillType;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

public class BillManager {

    public static Map<BillType, LocalDate> getOldestDates(List<Bill> bills) {
        Map<BillType, LocalDate> result = new HashMap<>();

        for (Bill bill : bills) {
            BillType type = bill.getBillType();
            LocalDate date = bill.getDate();

            // Nếu loại này chưa có trong Map hoặc tìm thấy ngày cũ hơn (xa hơn)
            if (!result.containsKey(type) || date.isBefore(result.get(type))) {
                result.put(type, date);
            }
        }
        return result;
    }
}