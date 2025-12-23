package com.example.javafx_app.controller.Bill;

import com.example.javafx_app.config.ExampleUser;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.object.Bill.BillType;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.regex.Pattern;

import static com.example.javafx_app.config.Constant.mainStage;

public class SchoolFeeSceneController implements BillScene {

    @FXML
    private TextField MSSV;

    @FXML
    private Label WrongLoginLabel;

    @FXML
    private ChoiceBox<String> co_so_dao_tao;

    @FXML
    private Button continue_btn;

    @FXML
    private Label nguon_thanh_toan;

    @FXML
    private Button return_btn;
    @FXML private Text ErrorLog ;
    @FXML
    void initialize() {
        co_so_dao_tao.getItems().addAll(ExampleUser.SCHOOL_PROVIDER.getAccountName());
        co_so_dao_tao.setValue(ExampleUser.SCHOOL_PROVIDER.getAccountName());
        co_so_dao_tao.setMouseTransparent(true);
        nguon_thanh_toan.setText(AccountManager.getInstance().getCurrentAccount().getAccountID());
    }
    @FXML
    public void QuayLai(ActionEvent event) {
        SceneUtils.switchScene(mainStage,"BillScene/bill_home_scene.fxml");
    }

    @FXML
    public void TiepTuc(ActionEvent event) {
        String mssv = MSSV.getText();
        if(mssv.isEmpty()){
            ErrorLog.setText("Mã số sinh viên không được để trống");
            return;
        }
        else if(!checkValidIdentifier(mssv)){
            ErrorLog.setText("Mã số sinh viên không đúng định dạng");
            return;
        }
        ClauseController.billType = BillType.TUITION ;
        SceneUtils.switchScene(mainStage,"BillScene/clause_scene.fxml");
    }
    // Khai báo Pattern tĩnh để tối ưu hiệu suất (chỉ biên dịch 1 lần)
    // Pattern này kiểm tra: Bắt đầu bằng 2020, 2021, 2022, 2023, 2024, hoặc 2025 (202[0-5])
    // và theo sau là 4 đến 5 chữ số bất kỳ (\\d{4,5}), tổng cộng 8 hoặc 9 chữ số.
    private static final Pattern VALID_IDENTIFIER_PATTERN =
            Pattern.compile("^(202[0-5])\\d{4,5}$");

    /**
     * Kiểm tra chuỗi số có phải là mã định danh hợp lệ theo quy tắc:
     * 1. Độ dài 8 hoặc 9 chữ số.
     * 2. 4 chữ số đầu tiên là năm trong khoảng 2020 đến 2025 (ví dụ: 2023xxxx).
     * * @param identifier Chuỗi cần kiểm tra (ví dụ: "202412345").
     * @return true nếu chuỗi hợp lệ, ngược lại là false.
     */
    private   boolean checkValidIdentifier(String identifier) {
        if (identifier == null || identifier.isEmpty()) {
            return false;
        }

        // Sử dụng Pattern đã khai báo để kiểm tra toàn bộ chuỗi
        return VALID_IDENTIFIER_PATTERN.matcher(identifier).matches();
    }
}
