package Models;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.sql.*;

public class MECCSEPORTALUtils  {
    private static String StudentRegistrationNumber;
    private static String StudentEmail;
    private static String StudentRoll;
    private static String StudentYear;
    private static String StudentSemester;

    public static String getStudentYear() {
        return StudentYear;
    }

    public static void setStudentYear(String studentYear) {
        StudentYear = studentYear;
    }

    public static String getStudentSemester() {
        return StudentSemester;
    }

    public static void setStudentSemester(String studentSemester) {
        StudentSemester = studentSemester;
    }

    public static String getStudentRoll() {
        return StudentRoll;
    }

    public static void setStudentRoll(String studentRoll) {
        StudentRoll = studentRoll;
    }

    public static String getStudentEmail() {
        return StudentEmail;
    }

    public static void setStudentEmail(String studentEmail) {
        StudentEmail = studentEmail;
    }

    public static void setStudentRegistration(String Reg){
        StudentRegistrationNumber = Reg;
    }
    public static String getStudentRegistration(){
        return StudentRegistrationNumber ;
    }


    public static void loginUserAsTeacher(String RegOrUserName, String Password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost/incoursemanagement","root","");
            preparedStatement = connection.prepareStatement("SELECT *  FROM `teachers` WHERE `t_username` = ?  AND `t_password` = ?");
            preparedStatement.setString(1,RegOrUserName);
            preparedStatement.setString(2,Password);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.isBeforeFirst() || RegOrUserName ==null){
                System.out.println("User Not Found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("User Not Found");
                alert.show();
            }else{
                while(resultSet.next()) {
                    String teacherId = resultSet.getString("id");
                    String teacherName = resultSet.getString("teacher_name");
                    String tUsername = resultSet.getString("t_username");
      DataManagement.successfullLoginHandler(teacherId, teacherName, tUsername);

      }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(resultSet != null){
                try{
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try{
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }if(connection != null){
                try{
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}