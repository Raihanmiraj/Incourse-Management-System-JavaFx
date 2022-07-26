package Controllers;
import Controllers.TeachersController.LayoutController;
import Models.MECCSEPORTALUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button LoginScreenSignup;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField userPassword;

    @FXML
    private TextField userRegistrationOrUserName;

    @FXML
    private BorderPane loginPageContent;
    @FXML
    void loginHandler(ActionEvent event) {
        String RegOrUser = userRegistrationOrUserName.getText();
        String Password = userPassword.getText();
        MECCSEPORTALUtils.loginUserAsTeacher(RegOrUser,Password);
         LayoutController.changeSceneClick(event,"TeacherView/Layout.fxml");
//        LayoutController.changeSenceInCenter("TeacherView/Layout.fxml");
        System.out.println("Remove");



    }

    @FXML
    void signUpButtonHandler(ActionEvent event){
        LayoutController.changeSceneClick(event,"SignupScreen.fxml");

     }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
