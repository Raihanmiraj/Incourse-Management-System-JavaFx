package Controllers.TeachersController;

import Models.DataManagement;
import Models.MECCSEPORTAL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LayoutController implements Initializable {


    @FXML
    private Text ProfileIcon;

    @FXML
    private Text courses;

    @FXML
    private AnchorPane coursesButton;

    @FXML
    private Text dashboard;

    @FXML
    private AnchorPane dashboardButton;

    @FXML
    private BorderPane layoutContent;

    @FXML
    private Text logout;

    @FXML
    private AnchorPane logoutButton;

    @FXML
    private Text namerender;

    @FXML
    private  Text pageTitle;

    @FXML
    private Text students;

    @FXML
    private AnchorPane studentsButton;
public  void setPageTitle(String text){
    pageTitle.setText(text);
}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        namerender.setText(DataManagement.getTeacherName());
        ProfileIcon.setText(String.valueOf(DataManagement.getTeacherName().charAt(0)));
//        try {
//            Parent pane   = FXMLLoader.load(MECCSEPORTALUtils.class.getResource("TeacherView/CoursePage.fxml"));
//            layoutContent.setCenter(pane);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        changeSenceInCenter("TeacherView/CoursePage.fxml");
        changeSenceInCenter("TeacherView/DashBoard.fxml");
        coursesButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setPageTitle("Courses");
//                MECCSEPORTAL.changeScenceforMouseEvent(mouseEvent,"ClassroomScreen.fxml","Classrooms",null);
                ButtonActiveHandle(coursesButton, studentsButton, dashboardButton, courses, students, dashboard);
                System.out.println("presentation");
                changeSenceInCenter("TeacherView/CoursePage.fxml");
            }


        });

        dashboardButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setPageTitle("DashBoard");
//                MECCSEPORTAL.changeScenceforMouseEvent(mouseEvent,"ClassroomScreen.fxml","Classrooms",null);
                ButtonActiveHandle( dashboardButton,studentsButton, coursesButton, dashboard,students, courses);
                System.out.println("home");
                changeSenceInCenter("TeacherView/DashBoard.fxml");

            }
     });

        studentsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setPageTitle("Students");
//                MECCSEPORTAL.changeScenceforMouseEvent(mouseEvent,"ClassroomScreen.fxml","Classrooms",null);
                ButtonActiveHandle( studentsButton,coursesButton,dashboardButton, students, courses,  dashboard);
                System.out.println("team");
                changeSenceInCenter("TeacherView/StudentsPage.fxml");

            }


        });

        logoutButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

//                MECCSEPORTAL.changeScenceforMouseEvent(mouseEvent,"ClassroomScreen.fxml","Classrooms",null);
                ButtonActiveHandle( logoutButton,coursesButton,dashboardButton, logout, courses,  dashboard);
                changeScene(event, "LoginPage.fxml");


            }


        });
    }

    public void ButtonActiveHandle(AnchorPane a, AnchorPane b, AnchorPane c, Text x, Text y , Text z){
        if(!a.getStyleClass().contains("DashboardScreenMenuItemActive") ){
            a.getStyleClass().add("DashboardScreenMenuItemActive");
            x.getStyleClass().remove("DashboardScreenMenuItemText");
            x.getStyleClass().add("DashboardScreenMenuItemTextActive");
        }

        if(b.getStyleClass().contains("DashboardScreenMenuItemActive")){
            b.getStyleClass().remove("DashboardScreenMenuItemActive");
            y.getStyleClass().remove("DashboardScreenMenuItemTextActive");
            y.getStyleClass().add("DashboardScreenMenuItemText");
        }

        if(c.getStyleClass().contains("DashboardScreenMenuItemActive")){
            c.getStyleClass().remove("DashboardScreenMenuItemActive");
            z.getStyleClass().remove("DashboardScreenMenuItemTextActive");
            z.getStyleClass().add("DashboardScreenMenuItemText");
        }

    }

    public void changeSenceInCenter(String fxml){
        try {
            Parent pane   = FXMLLoader.load(MECCSEPORTAL.class.getResource(fxml));
            layoutContent.setCenter(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void changeScene(MouseEvent event, String fxmlFile){
        Parent root = null;
        try{
            root = FXMLLoader.load(MECCSEPORTAL.class.getResource(fxmlFile));
        }catch (IOException e){
            System.out.println("Can't Load File");
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("MEC");
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void changeSceneClick(ActionEvent event, String fxmlFile){
        Parent root = null;
        try{
            root = FXMLLoader.load(MECCSEPORTAL.class.getResource(fxmlFile));
        }catch (IOException e){
            System.out.println("Can't Load File");
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("MEC");
        stage.setScene(new Scene(root));
        stage.show();
    }
    }

