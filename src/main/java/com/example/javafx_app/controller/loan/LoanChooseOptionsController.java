package com.example.javafx_app.controller.loan;

import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.util.Pair;

public class LoanChooseOptionsController {
    /*
    Khác với gửi tiền, cái phần trăm tiền lãi của vay nó tùy thuộc vào mục đích vay và số tiền vay của người vay
    Đây là scene đầu tiên khi bấm nút vay tiền, khi này thì nó sẽ hiện 6 options tương ứng với 6 mục đích vay tiền
    Với 5 option đầu khi bấm vào nút "Đăng ký ngay" thì nó sẽ chuyển sang scene loan_choose_categories.fxml
    Còn option cuối (Khoản vay mới) thì đi thẳng đến scene loan_choose_method.fxml
    Đấy thế thôi:)
     */
    @FXML
    void DangKyNgay1(ActionEvent event){
        DangKyNgay(event, 1);
    }
    @FXML
    void DangKyNgay2(ActionEvent event){
        DangKyNgay(event, 2);
    }
    @FXML
    void DangKyNgay3(ActionEvent event){
        DangKyNgay(event, 3);
    }
    @FXML
    void DangKyNgay4(ActionEvent event){
        DangKyNgay(event, 4);
    }
    @FXML
    void DangKyNgay5(ActionEvent event){
        DangKyNgay(event, 5);
    }
    @FXML
    void DangKyNgay6(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event), "loanScene/loan_choose_method.fxml");
    }
    void DangKyNgay(ActionEvent event, int type){
        Pair<Parent, LoanChooseCategoriesController> scene = SceneUtils.getRootAndController("loanScene/loan_choose_categories.fxml");
        scene.getValue().loadMethod(type);
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event), scene.getKey());
    }
}
