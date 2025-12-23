package com.example.javafx_app.controller.loan;

import com.example.javafx_app.controller.block.LoanCategoriesBlockController;
import com.example.javafx_app.util.SceneUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.awt.event.ActionEvent;

import static com.example.javafx_app.config.Constant.mainStage;

public class LoanChooseCategoriesController {
    @FXML private VBox v_box_contain_loan_categories_info;
    private final String[] head1 = {
            "Vay tiêu dùng",
            "Vay gia dụng",
            "Vay tài sản lớn"
    };
    private final String[] head2 = {
            "Vay kinh doanh nhỏ",
            "Vay kinh doanh mở rộng",
            "Vay kinh doanh quy mô lớn"
    };
    private final String[] head3 = {
            "Vay mua ô tô phổ thông",
            "Vay mua ô tô cao cấp",
    };
    private final String[] head4 = {
            "Vay mua nhà ở",
            "Vay đầu tư bất động sản",
    };
    private final String[] head5 = {
            "Vay đầu tư cá nhân",
            "Vay đầu tư nâng cao"
    };

    private final String[] title1 = {
            "Dành cho việc mua những món đồ thiết yếu phục vụ nhu cầu cá nhân hằng ngày",
            "Dành cho việc mua các thiết bị gia dụng như TV, tủ lạnh, máy giặt, nội thất,...",
            "Dành cho việc mua các tài sản tiêu dùng giá trị cao"
    };
    private final String[] title2 = {
            "Phù hợp với hộ kinh doanh cá thể, bán hàng online, vốn quay vòng nhanh",
            "Phục vụ mở rộng cửa hàng, nhập thêm hàng hóa, đầu tư thiết bị",
            "Dành cho doanh nghiệp nhỏ và vừa, đầu tư dài hạn"
    };
    private final String[] title3 = {
            "Dành cho cá nhân, gia đình mua xe phục vụ đi lại",
            "Dành cho xe sang, xe phục vụ kinh doanh"
    };
    private final String[] title4 = {
            "Dành cho nhu cầu an cư, mua căn hộ, nhà ở",
            "Dành cho đầu tư, mua để cho thuê hoặc sinh lời"
    };
    private final String[] title5 = {
            "Dành cho các kế hoạch đầu tư tài chính trung và dài hạn",
            "Phù hợp với nhà đầu tư có kinh nghiệm, danh mục rõ ràng"
    };

    private final long[] max1 = {
            75_000_000L,
            400_000_000L,
            1_600_000_000L
    };
    private final long[] max2 = {
            100_000_000L,
            500_000_000L,
            2_000_000_000L
    };
    private final long[] max3 = {
            1_000_000_000L,
            2_000_000_000L
    };
    private final long[] max4 = {
            3_000_000_000L,
            5_000_000_000L
    };
    private final long[] max5 = {
            1_000_000_000L,
            3_000_000_000L
    };
    void loadMethod(int type){
        switch (type){
            case 1:
                for(int i = 0; i < 3; i++){
                    Pair<Parent, LoanCategoriesBlockController> block = SceneUtils.getRootAndController("loanScene/loan_categories_block.fxml");
                    block.getValue().setData(head1[i], title1[i], max1[i]);
                    v_box_contain_loan_categories_info.getChildren().add(block.getKey());
                    v_box_contain_loan_categories_info.getChildren().add(new Text());
                }
                break;
            case 2:
                for(int i = 0; i < 3; i++){
                    Pair<Parent, LoanCategoriesBlockController> block = SceneUtils.getRootAndController("loanScene/loan_categories_block.fxml");
                    block.getValue().setData(head2[i], title2[i], max2[i]);
                    v_box_contain_loan_categories_info.getChildren().add(block.getKey());
                    v_box_contain_loan_categories_info.getChildren().add(new Text());
                }
                break;
            case 3:
                for(int i = 0; i < 2; i++){
                    Pair<Parent, LoanCategoriesBlockController> block = SceneUtils.getRootAndController("loanScene/loan_categories_block.fxml");
                    block.getValue().setData(head3[i], title3[i], max3[i]);
                    v_box_contain_loan_categories_info.getChildren().add(block.getKey());
                    v_box_contain_loan_categories_info.getChildren().add(new Text());
                }
                break;
            case 4:
                for(int i = 0; i < 2; i++){
                    Pair<Parent, LoanCategoriesBlockController> block = SceneUtils.getRootAndController("loanScene/loan_categories_block.fxml");
                    block.getValue().setData(head4[i], title4[i], max4[i]);
                    v_box_contain_loan_categories_info.getChildren().add(block.getKey());
                    v_box_contain_loan_categories_info.getChildren().add(new Text());
                }
                break;
            case 5:
                for(int i = 0; i < 2; i++){
                    Pair<Parent, LoanCategoriesBlockController> block = SceneUtils.getRootAndController("loanScene/loan_categories_block.fxml");
                    block.getValue().setData(head5[i], title5[i], max5[i]);
                    v_box_contain_loan_categories_info.getChildren().add(block.getKey());
                    v_box_contain_loan_categories_info.getChildren().add(new Text());
                }
                break;
            default:
                break;
        }
    }
    @FXML
    void QuayLai(ActionEvent event){
        SceneUtils.switchScene(mainStage, "loanScene/loan_choose_categories.fxml");
    }
    /*
    Scene này dùng để hiển thị các danh mục vay theo từng mục đích,
    cho người dùng lựa chọn các mức tiền vay khác nhau dựa vào nhu cầu của họ.
    (Hạn mức vay - % lãi kỳ hạn - % lãi trả góp)

    I. MUA SẮM TIÊU DÙNG
    + Vay tiêu dùng:
      Dành cho việc mua những món đồ thiết yếu phục vụ nhu cầu cá nhân hằng ngày
      (75.000.000 VND - 0.75% / tháng - 1.00% / tháng)
    + Vay gia dụng:
      Dành cho việc mua các thiết bị gia dụng như TV, tủ lạnh, máy giặt, nội thất,...
      (400.000.000 VND - 0.50% / tháng - 0.75% / tháng)
    + Vay tài sản lớn:
      Dành cho việc mua các tài sản tiêu dùng giá trị cao
      (1.600.000.000 VND - 0.25% / tháng - 0.50% / tháng)

    II. SẢN XUẤT – KINH DOANH
    + Vay kinh doanh nhỏ:
      Phù hợp với hộ kinh doanh cá thể, bán hàng online, vốn quay vòng nhanh
      (100.000.000 VND - 0.80% / tháng - 1.20% / tháng)
    + Vay kinh doanh mở rộng:
      Phục vụ mở rộng cửa hàng, nhập thêm hàng hóa, đầu tư thiết bị
      (500.000.000 VND - 0.60% / tháng - 1.00% / tháng)
    + Vay kinh doanh quy mô lớn:
      Dành cho doanh nghiệp nhỏ và vừa, đầu tư dài hạn
      (2.000.000.000 VND - 0.45% / tháng - 0.80% / tháng)

    III. MUA Ô TÔ
    + Vay mua ô tô phổ thông:
      Dành cho cá nhân, gia đình mua xe phục vụ đi lại
      (1.000.000.000 VND - 0.50% / tháng - 0.80% / tháng)
    + Vay mua ô tô cao cấp:
      Dành cho xe sang, xe phục vụ kinh doanh
      (2.000.000.000 VND - 0.45% / tháng - 0.70% / tháng)

    IV. MUA BẤT ĐỘNG SẢN
    + Vay mua nhà ở:
      Dành cho nhu cầu an cư, mua căn hộ, nhà ở
      (3.000.000.000 VND - 0.40% / tháng - 0.65% / tháng)
    + Vay đầu tư bất động sản:
      Dành cho đầu tư, mua để cho thuê hoặc sinh lời
      (5.000.000.000 VND - 0.45% / tháng - 0.75% / tháng)

    V. ĐẦU TƯ
    + Vay đầu tư cá nhân:
      Dành cho các kế hoạch đầu tư tài chính trung và dài hạn
      (1.000.000.000 VND - 0.55% / tháng - 0.90% / tháng)
    + Vay đầu tư nâng cao:
      Phù hợp với nhà đầu tư có kinh nghiệm, danh mục rõ ràng
      (3.000.000.000 VND - 0.50% / tháng - 0.85% / tháng)

    VI. KHOẢN VAY TỰ CHỌN
    + Khoản vay tự chọn:
      Người dùng chủ động nhập số tiền và thời hạn vay.
      Ngân hàng sẽ xét duyệt hạn mức và áp dụng mức lãi suất tương ứng
      dựa trên hồ sơ, thu nhập và mục đích vay.
      (Không giới hạn hạn mức - Lãi suất xác định khi xét duyệt)
    */
}
