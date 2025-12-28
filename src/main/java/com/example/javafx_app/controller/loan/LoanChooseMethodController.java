package com.example.javafx_app.controller.loan;

import com.example.javafx_app.config.Constant;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.object.Account.LoanAccount;
import com.example.javafx_app.object.Account.LoanType;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.util.Pair;

import static com.example.javafx_app.config.Constant.mainStage;

public class LoanChooseMethodController {
    @FXML private Label loan_fixed_interest_label;
    @FXML private Label loan_accumulated_interest_label;
    private int type = 0;
    private int index = 0;
    private long max = 0;
    private double fixedInterest = 0;
    private double accumulatedInterest = 0;
    public void loadInfo(int type, int index){
        this.type = type;
        this.index = index;
        switch (type){
            case 1:
                this.max = Constant.max1[index];
                loan_fixed_interest_label.setText(Constant.interest1[index].getKey() + "%/tháng");
                this.fixedInterest = (Double) Constant.interest1[index].getKey();
                loan_accumulated_interest_label.setText(Constant.interest1[index].getValue() + "%/tháng");
                this.accumulatedInterest = (Double) Constant.interest1[index].getValue();
                break;
            case 2:
                this.max = Constant.max2[index];
                loan_fixed_interest_label.setText(Constant.interest2[index].getKey() + "%/tháng");
                this.fixedInterest = (Double) Constant.interest2[index].getKey();
                loan_accumulated_interest_label.setText(Constant.interest2[index].getValue() + "%/tháng");
                this.accumulatedInterest = (Double) Constant.interest2[index].getValue();
                break;
            case 3:
                this.max = Constant.max3[index];
                loan_fixed_interest_label.setText(Constant.interest3[index].getKey() + "%/tháng");
                this.fixedInterest = (Double) Constant.interest3[index].getKey();
                loan_accumulated_interest_label.setText(Constant.interest3[index].getValue() + "%/tháng");
                this.accumulatedInterest = (Double) Constant.interest3[index].getValue();
                break;
            case 4:
                this.max = Constant.max4[index];
                loan_fixed_interest_label.setText(Constant.interest4[index].getKey() + "%/tháng");
                this.fixedInterest = (Double) Constant.interest4[index].getKey();
                loan_accumulated_interest_label.setText(Constant.interest4[index].getValue() + "%/tháng");
                this.accumulatedInterest = (Double) Constant.interest4[index].getValue();
                break;
            case 5:
                this.max = Constant.max5[index];
                loan_fixed_interest_label.setText(Constant.interest5[index].getKey() + "%/tháng");
                this.fixedInterest = (Double) Constant.interest5[index].getKey();
                loan_accumulated_interest_label.setText(Constant.interest5[index].getValue() + "%/tháng");
                this.accumulatedInterest = (Double) Constant.interest5[index].getValue();
                break;
            default:
                this.max = 100000000L;
                loan_fixed_interest_label.setText("");
                this.fixedInterest = 0.0;
                loan_accumulated_interest_label.setText("");
                this.accumulatedInterest = 0.0;
                break;
        }
    }
    @FXML
    public void QuayLai(ActionEvent event){
        if(this.type != 0){
            Pair<Parent, LoanChooseCategoriesController> scene = SceneUtils.getRootAndController("loanScene/loan_choose_categories.fxml");
            scene.getValue().loadMethod(this.type);
            SceneUtils.switchScene(mainStage, scene.getKey());
        }
        else SceneUtils.switchScene(mainStage, "loanScene/loan_choose_option.fxml");
    }
    @FXML
    public void KiHan(ActionEvent event){
        ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).setType(LoanType.FIXED);
        Pair<Parent, LoanController> scene = SceneUtils.getRootAndController("loanScene/loan_scene.fxml");
        scene.getValue().loadInfo(this.type, this.index, this.max, this.fixedInterest);
        SceneUtils.switchScene(mainStage, scene.getKey());
    }
    @FXML
    public void TraGop(ActionEvent event){
        ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).setType(LoanType.ACCUMULATED);
        Pair<Parent, LoanController> scene = SceneUtils.getRootAndController("loanScene/loan_scene.fxml");
        scene.getValue().loadInfo(this.type, this.index, this.max, this.accumulatedInterest);
        SceneUtils.switchScene(mainStage, scene.getKey());
    }
    /*
    Scene này cho phép người dùng lựa chọn phương thức vay vốn.
    Hiện tại hệ thống hỗ trợ 2 phương thức: Vay kỳ hạn và Vay trả góp.

    1. VAY KỲ HẠN
    - Người dùng vay một khoản tiền trong một khoảng thời gian xác định.
    - Đến thời điểm đáo hạn:
        + Người dùng có thể trả toàn bộ cả gốc và lãi.
        + Hoặc yêu cầu gia hạn khoản vay (nếu được ngân hàng chấp thuận).
    - Trong suốt thời gian vay, người dùng chỉ cần theo dõi khoản vay,
      không phải trả tiền hàng tháng.
    - Lãi suất của vay kỳ hạn thường thấp hơn vay trả góp.
    - Phù hợp với người dùng có kế hoạch tài chính rõ ràng
      và có khả năng thanh toán toàn bộ khoản vay khi đến hạn.

    2. VAY TRẢ GÓP
    - Người dùng trả nợ theo từng tháng trong suốt thời gian vay.
    - Mỗi tháng, hệ thống sẽ tự động trích tiền từ tài khoản:
        + Một phần tiền gốc
        + Và tiền lãi đã được tính toán trước
    - Tổng số tiền phải trả được chia nhỏ giúp giảm áp lực tài chính hàng tháng.
    - Lãi suất vay trả góp thường cao hơn vay kỳ hạn.
    - Phù hợp với người dùng có thu nhập ổn định hàng tháng.

    LƯU Ý VỀ LÃI SUẤT
    - Lãi suất áp dụng phụ thuộc vào:
        + Mục đích vay (Mua sắm, Sản xuất kinh doanh, Mua ô tô, Bất động sản, Đầu tư...)
        + Danh mục vay đã được người dùng lựa chọn ở scene trước.
    - Đối với khoản vay tự chọn:
        + Lãi suất chưa được xác định tại bước này.
        + Ngân hàng sẽ xét duyệt và áp dụng mức lãi suất phù hợp
          sau khi đánh giá hồ sơ của người dùng.

    Scene này chỉ hiển thị phương thức vay,
    các thông tin chi tiết về lãi suất và lịch trả nợ
    sẽ được hiển thị ở các bước tiếp theo.
    */
}
