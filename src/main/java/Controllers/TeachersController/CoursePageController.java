package Controllers.TeachersController;

import Models.DataManagement;
import Models.MECCSEPORTAL;
import Models.TeachersModel.CoursePage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CoursePageController implements Initializable {



    @FXML
    private VBox coursePageContent;

    @FXML
    private AnchorPane coursePageMainContent;


    private static int courseSize = 0;

    void addCourseName(String buttonID , String courseName){
        BorderPane bpane = new BorderPane();
        Text txt =new Text(courseName);
        HBox title = new HBox();
        txt.getStyleClass().add("coursePageCourseName");
        title.setAlignment(Pos.CENTER_LEFT);
        title.getChildren().add(txt);

        Button btn = new Button("View");
        btn.setId(buttonID);

        Button btn2= new Button("View Result");
        btn2.setId(buttonID);

        HBox button = new HBox();

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//   event.getTarget().getId();
                System.out.println(((Control)event.getSource()).getId());
          ClassTestAndAssignmentListController.setCourseId(Integer.parseInt(((Control)event.getSource()).getId()));
                coursePageMainContent.getChildren().clear();
                Parent pane   = null;
                try {
                    pane = FXMLLoader.load(MECCSEPORTAL.class.getResource("TeacherView/ClassTestAndAssignmentList.fxml"));
                    coursePageMainContent.getChildren().add(pane) ;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        });


        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//   event.getTarget().getId();
                System.out.println(((Control)event.getSource()).getId());
                 SingleCourseStudentsMarkController.setCourseId(Integer.parseInt(((Control)event.getSource()).getId()));
                 coursePageMainContent.getChildren().clear();
                Parent pane   = null;
                try {
                    pane = FXMLLoader.load(MECCSEPORTAL.class.getResource("TeacherView/SingleCourseStudentsMark.fxml"));
                    coursePageMainContent.getChildren().add(pane) ;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
   }
        });
        button.setAlignment(Pos.CENTER_RIGHT);
        button.getChildren().addAll(btn,btn2);
        bpane.getStyleClass().add("coursePageCourseData");
        title.setPadding(new Insets(0, 0, 0, 50));
        button.setPadding(new Insets(0, 50, 0, 0));
        button.setSpacing(10);
        bpane.setLeft(title);
        bpane.setRight(button);
        bpane.setPrefWidth(880);
        bpane.setPrefHeight(150);
        coursePageContent.setPrefHeight(150*courseSize);
        coursePageContent.getChildren().add(bpane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        courseSize = 0 ;
        ResultSet rs = CoursePage.getAllCoursesData(Integer.valueOf(DataManagement.getTeacherId()));
        try {
            while (rs.next()){
                courseSize++;
                addCourseName(rs.getString("id"),rs.getString("course_name"));
            }
        }catch (Exception e) {
        }
        }



    }

