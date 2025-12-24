package com.example.javafx_app.object.Noti;

import com.example.javafx_app.manager.BankManager;

import java.io.Serializable;
import java.time.LocalDate;

public class Notification implements Serializable {
    private NotificationType type;
    private LocalDate timestamp;
    private String title;
    private String message;
    private boolean isRead;

    // Constructor

    public Notification(NotificationType type, String title, String message) {
        this.type = type;
        this.timestamp = BankManager.getCurrentDate();
        this.title = title;
        this.message = message;
        this.isRead = false; // Mặc định là chưa đọc
    }

    // Phương thức tạo ID ngẫu nhiên (tương tự như trong Transaction.java)

    // Getters

    public NotificationType getType() {
        return type;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public boolean isRead() {
        return isRead;
    }

    // Setter
    public void setRead(boolean read) {
        isRead = read;
    }
}