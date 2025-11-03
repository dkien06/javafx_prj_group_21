package com.example.javafx_app.config;

import java.util.Map;

/**
 * Class dữ trữ mấy biến hằng số, éo thích thì vào mà chỉnh:)
 */
public class Constant {
    public static final double SAVING_INTEREST_RATE_PER_MONTH = 0.007;
    public static final double LOAN_INTEREST_RATE_PER_MONTH = 0.01;
    public static final double SAVING_INTEREST_RATE_PER_YEAR = 0.7;

    public static final int LOAN_WARNING_APPLY_MONTH_TIME = 12;
    public static final double LOAN_MAX = 200_000_000.0;
    public static final double LOAN_WARNING_1_MAX = 500_000_000.0;
    public static final double LOAN_WARNING_2_MAX = 2_000_000_000.0;

    public static final double VIP_FEE = 69_000.0;

    public static final int PHONE_NUMBER_LENGTH = 10;
    public static final int MINIUM_PASSWORD_LENGTH = 8;
    public static final int MINIUM_PIN_LENGTH = 6;

    public static final String ADMIN_CITIZEN_ID = "000000000000";
    public static final String ADMIN_ACCOUNT_ID = "00000000";
    public static final String ADMIN_PASSWORD = "000000";
    public static final double ADMIN_BALANCE = 9223372036854775807.0;
    public static final String ADMIN_CURRENCY = "VND";
    public static final String ADMIN_PIN = "000000";

    public static final long START_ACCOUNT_ID = 10000000;
    public static final double DEFAULT_BALANCE = 0.0;
    public static final double DEFAULT_DEBT = 0.0;

    public static final Map<String,String> CURRENCY = Map.ofEntries(
            Map.entry("USA","USD"),
            Map.entry("Canada","CAD"),
            Map.entry("Mexico","MXN"),
            Map.entry("Brazil","BRL"),
            Map.entry("UK","EUR"),
            Map.entry("China","CNY"),
            Map.entry("Japan","JPY"),
            Map.entry("Korea","KRW"),
            Map.entry("India","INR"),
            Map.entry("Singapore","SGD"),
            Map.entry("Laos","LAK"),
            Map.entry("Cambodia","KHR"),
            Map.entry("Vietnam","VND"),
            Map.entry("Philippine","PHP"),
            Map.entry("Thailand","THB"),
            Map.entry("Malaysia","MYR"),
            Map.entry("Indonesia","IDR"),
            Map.entry("Myanmar","MMK")
            //Rảnh thì thêm đi:)
    );

    public static final Map<String,Double> CONVERT_TO_VND = Map.ofEntries(
            Map.entry("USD",26_314.7707),
            Map.entry("CAD",18_748.2378),
            Map.entry("MXN",1_417.5805),
            Map.entry("BRL",4_890.1238),
            Map.entry("EUR",30_505.04),
            Map.entry("CNY",3_699.2756),
            Map.entry("JPY",170.8514),
            Map.entry("KRW",18.4119),
            Map.entry("IRN",296.1629),
            Map.entry("SGD",20_242.4247),
            Map.entry("LAK",1.2126),
            Map.entry("KHR",6.5492),
            Map.entry("VND",1.0000000000000000),
            Map.entry("PHP",447.9576),
            Map.entry("THB",815.52),
            Map.entry("MYR",6_279.8555),
            Map.entry("IDR",1.5819),
            Map.entry("MMK",12.5332)
            //Rảnh thì thêm đi
    );
}
