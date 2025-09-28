package com.example.javafx_app;

public class userdata {
    private static String username;
    private static String password="123";
    public static String getUsername() {
        return username;
    }
    public static void setUsername(String username) {userdata.username = username;}
    public static String getPassword() { return password; }
    public static void setPassword(String password) {userdata.password = password;}
}
