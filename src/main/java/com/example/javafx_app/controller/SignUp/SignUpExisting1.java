package com.example.javafx_app.controller.SignUp;

import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.BankManager;
import com.example.javafx_app.manager.UserManager;
import com.example.javafx_app.object.Account.ACCOUNT_TYPE;
import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.object.User.Customer;
import com.example.javafx_app.object.User.USER_TYPE;
import com.example.javafx_app.object.User.User;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.List;

import static com.example.javafx_app.config.Constant.mainStage;

public class SignUpExisting1 {
    @FXML
    private TextField CCCDField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private Text PasswordLog, CCCDLog;
    private boolean checkMainAcccount(String cccd, String password){
        User user = UserManager.getInstance().findUserByCitizenID(cccd);
        if(user == null){ return  false; }
        if(user.getType() == USER_TYPE.STAFF) return  false;
        Customer customer = (Customer) user ;
        Account mainAccount = customer.getMainAccount() ;
        if(mainAccount==null){
            return  false;
        }
        if(mainAccount.getPassword().equals(password)){return  true;}
        return  false;
    }
    private boolean checkFullAccount(String cccd){
        User user = UserManager.getInstance().findUserByCitizenID(cccd);
        if(user == null){return  false;}
        // Người dùng đã có đủ 3 tài khoản
        if(AccountManager.getInstance().findAccountFromUser(user).size() == 3){return  true;}
        return  false;
    }
    @FXML
    public void NextToForm2(ActionEvent event) {
            String CCCD = CCCDField.getText();
            String Password = PasswordField.getText();
            BankManager.SignUpInformationState CCCDState = BankManager.checkSignUpCitizenID(CCCD) ;
            if(CCCDState == BankManager.SignUpInformationState.EMPTY){
                CCCDLog.setText("Vui lòng nhập CCCD");
            }
            else if(CCCDState == BankManager.SignUpInformationState.WRONG_FORM){
                CCCDLog.setText("CCCD sai định dạng");
            }
            else{
                boolean isMainAccount =checkMainAcccount(CCCD, Password);
                if(!isMainAccount) PasswordLog.setText("CCCD hoặc mật khẩu bị sai");
                else {
                    if (checkFullAccount(CCCD)) {
                        PasswordLog.setText("Bạn đã có đủ 3 tài khoản, không thể mở thêm");
                    }
                    else {
                        UserManager.getInstance().setCurrentUser(UserManager.getInstance().findUserByCitizenID(CCCD));
                        SceneUtils.switchScene(mainStage,
                                "SignUpScene/signup_existing_customer2_scene.fxml");
                    }

                }

            }

    }

    @FXML
    public void backTo3SignUpOption(ActionEvent event) {
        SceneUtils.switchScene(mainStage,
                "/com/example/javafx_app/SignUpScene/signup_3option_scene.fxml");
    }

}
