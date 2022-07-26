package Controllers.TeachersController;

import Models.TeachersModel.DashBoard;
import javafx.fxml.Initializable;
import Models.DataManagement;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DashBoardController implements Initializable {
    @FXML
    private Text AttendanceAvobeAverage;

    @FXML
    private Text attendanceBelowInAverage;



    @FXML
    private Text totalClassToday;
    @FXML
    private VBox classRoutineVbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//     namerender.setText(DataManagement.getTeacherName());


        ClassRoutineShow();
        totalClassToday.setText(DashBoard.getTotalClassToday());
        attendanceBelowInAverage.setText(DashBoard.getAttendanceBelowAverage());
        AttendanceAvobeAverage.setText(DashBoard.getAttendanceAvobeAverage());
    }

    public void ClassRoutineShow(){
        ResultSet rs = DashBoard.getClassRoutine();
        try {
            while (rs.next()){
                System.out.println(rs.getString("class_name"));
                AnchorPane an = new AnchorPane();
                an.setPrefHeight(84);
                an.setPrefWidth(573);
                an.getStyleClass().add("DashboardScreenUpcomingClassesItem");
                Text className = new Text(rs.getString("class_name"));
                className.setLayoutX(14);
                className.setLayoutY(31);
                className.getStyleClass().add("DashboardScreenUpcomingClassesItemHeader");
                Text classdate = new Text(rs.getString("day") + " "+ rs.getString("date"));
                classdate.setLayoutX(415);
                classdate.setLayoutY(29);
                classdate.getStyleClass().add("DashboardScreenUpcomingClassesItemDate");
                Text classtime = new Text(rs.getString("time"));
                classtime.setLayoutX(428);
                classtime.setLayoutY(61);
                classtime.getStyleClass().add("DashboardScreenUpcomingClassesItemTime");
                Text sc = new Text("Scheduled By ");
                sc.setLayoutX(14);
                sc.setLayoutY(58);
                sc.getStyleClass().add("DashboardScreenUpcomingClassesItemScheduledBy");
                Text addedby = new Text(rs.getString("addedby"));
                addedby.setLayoutX(109);
                addedby.setLayoutY(58);
                addedby.getStyleClass().add("DashboardScreenUpcomingClassesItemTeacher");
                an.getChildren().addAll(className,classdate,classtime,sc,addedby);
                classRoutineVbox.getChildren().add(an);
            }

        }catch (Exception e){

        }

    }


}
