package com.example.javafx_app.config;

import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Map;

/**
 * Class dữ trữ mấy biến hằng số, éo thích thì vào mà chỉnh:)
 */
public class Constant {
    private Constant() {}

    public static final double SAVING_FLEXIBLE_INTEREST_RATE_PER_YEAR = 5;
    public static final double SAVING_FIXED_INTEREST_RATE_PER_YEAR =  15;
    public static final double SAVING_ACCUMULATE_INTEREST_RATE_PER_YEAR = 10;

    public static final int PHONE_NUMBER_LENGTH = 10;
    public static final int MINIUM_PASSWORD_LENGTH = 8;
    public static final int MINIUM_PIN_LENGTH = 6;

    public static final String ADMIN_CITIZEN_ID = "000000000000";
    public static final String ADMIN_ACCOUNT_ID = "00000000";
    public static final String ADMIN_PASSWORD = "000000";
    public static final String ADMIN_CURRENCY = "VND";
    public static final String ADMIN_PIN = "000000";

    public static final int START_ACCOUNT_ID = 10000000;
    public static final long DEFAULT_BALANCE = 0;
    public static Stage mainStage = new Stage();
    public static final Map<String, String> CURRENCY = Map.ofEntries(
            Map.entry("USA", "USD"),
            Map.entry("Canada", "CAD"),
            Map.entry("Mexico", "MXN"),
            Map.entry("Brazil", "BRL"),
            Map.entry("UK", "EUR"),
            Map.entry("China", "CNY"),
            Map.entry("Japan", "JPY"),
            Map.entry("Korea", "KRW"),
            Map.entry("India", "INR"),
            Map.entry("Singapore", "SGD"),
            Map.entry("Laos", "LAK"),
            Map.entry("Cambodia", "KHR"),
            Map.entry("Vietnam", "VND"),
            Map.entry("Philippine", "PHP"),
            Map.entry("Thailand", "THB"),
            Map.entry("Malaysia", "MYR"),
            Map.entry("Indonesia", "IDR"),
            Map.entry("Myanmar", "MMK")
            //Rảnh thì thêm đi:)
    );

    public static final Map<String, Double> CONVERT_TO_VND = Map.ofEntries(
            Map.entry("USD", 26_314.7707),
            Map.entry("CAD", 18_748.2378),
            Map.entry("MXN", 1_417.5805),
            Map.entry("BRL", 4_890.1238),
            Map.entry("EUR", 30_505.04),
            Map.entry("CNY", 3_699.2756),
            Map.entry("JPY", 170.8514),
            Map.entry("KRW", 18.4119),
            Map.entry("IRN", 296.1629),
            Map.entry("SGD", 20_242.4247),
            Map.entry("LAK", 1.2126),
            Map.entry("KHR", 6.5492),
            Map.entry("VND", 1.0000000000000000),
            Map.entry("PHP", 447.9576),
            Map.entry("THB", 815.52),
            Map.entry("MYR", 6_279.8555),
            Map.entry("IDR", 1.5819),
            Map.entry("MMK", 12.5332)
            //Rảnh thì thêm đi
    );
    public static final String[] head1 = {
            "Vay tiêu dùng",
            "Vay gia dụng",
            "Vay tài sản lớn"
    };
    public static final String[] head2 = {
            "Vay kinh doanh nhỏ",
            "Vay kinh doanh mở rộng",
            "Vay kinh doanh quy mô lớn"
    };
    public static final String[] head3 = {
            "Vay mua ô tô phổ thông",
            "Vay mua ô tô cao cấp",
    };
    public static final String[] head4 = {
            "Vay mua nhà ở",
            "Vay đầu tư bất động sản",
    };
    public static final String[] head5 = {
            "Vay đầu tư cá nhân",
            "Vay đầu tư nâng cao"
    };

    public static final String[] title1 = {
            "Dành cho việc mua những món đồ thiết yếu phục vụ nhu cầu cá nhân hằng ngày",
            "Dành cho việc mua các thiết bị gia dụng như TV, tủ lạnh, máy giặt, nội thất,...",
            "Dành cho việc mua các tài sản tiêu dùng giá trị cao"
    };
    public static final String[] title2 = {
            "Phù hợp với hộ kinh doanh cá thể, bán hàng online, vốn quay vòng nhanh",
            "Phục vụ mở rộng cửa hàng, nhập thêm hàng hóa, đầu tư thiết bị",
            "Dành cho doanh nghiệp nhỏ và vừa, đầu tư dài hạn"
    };
    public static final String[] title3 = {
            "Dành cho cá nhân, gia đình mua xe phục vụ đi lại",
            "Dành cho xe sang, xe phục vụ kinh doanh"
    };
    public static final String[] title4 = {
            "Dành cho nhu cầu an cư, mua căn hộ, nhà ở",
            "Dành cho đầu tư, mua để cho thuê hoặc sinh lời"
    };
    public static final String[] title5 = {
            "Dành cho các kế hoạch đầu tư tài chính trung và dài hạn",
            "Phù hợp với nhà đầu tư có kinh nghiệm, danh mục rõ ràng"
    };
    public static final long[] max1 = {
            75_000_000L,
            400_000_000L,
            1_600_000_000L
    };
    public static final long[] max2 = {
            100_000_000L,
            500_000_000L,
            2_000_000_000L
    };
    public static final long[] max3 = {
            1_000_000_000L,
            2_000_000_000L
    };
    public static final long[] max4 = {
            3_000_000_000L,
            5_000_000_000L
    };
    public static final long[] max5 = {
            1_000_000_000L,
            3_000_000_000L
    };
    public static final Pair[] interest1 = new Pair[] {
            new Pair<>(0.75, 1.0),
            new Pair<>(0.5, 0.75),
            new Pair<>(0.25, 0.5),
    };
    public static final Pair[] interest2 = new Pair[] {
            new Pair<>(0.8, 1.2),
            new Pair<>(0.6, 1.0),
            new Pair<>(0.45, 0.8),
    };
    public static final Pair[] interest3 = new Pair[] {
            new Pair<>(0.5, 0.8),
            new Pair<>(0.45, 0.7),
    };
    public static final Pair[] interest4 = new Pair[] {
            new Pair<>(0.4, 0.65),
            new Pair<>(0.45, 0.75),
    };
    public static final Pair[] interest5 = new Pair[] {
            new Pair<>(0.55, 0.9),
            new Pair<>(0.5, 0.85),
    };
}