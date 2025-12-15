package com.example.javafx_app.object.Noti;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Notification implements Serializable {
    private NotificationType type;
    private LocalDateTime timestamp;
    private String title;
    private String message;
    private boolean isRead;

    // Constructor

    public Notification(NotificationType type, String title, String message) {
        this.type = type;
        this.timestamp = LocalDateTime.now();
        this.title = title;
        this.message = message;
        this.isRead = false; // Mặc định là chưa đọc
    }

    // Phương thức tạo ID ngẫu nhiên (tương tự như trong Transaction.java)

    // Getters

    public NotificationType getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
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