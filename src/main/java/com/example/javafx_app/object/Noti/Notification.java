package com.example.javafx_app.object.Noti;


import java.time.LocalDateTime;
import java.util.Random;

public class Notification {
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
    private String generateID() {
        String numbers = "0123456789";
        StringBuilder id = new StringBuilder("N"); // Bắt đầu bằng 'N'
        Random random = new Random();
        for(int i = 0; i < 9; i++){ // Tổng cộng 10 ký tự
            id.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        return String.valueOf(id);
    }

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