package Models;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.sql.*;

public class DataManagement {
    private static String databaseHost = "jdbc:mysql://localhost/incoursemanagement";
    private static String databaseUser = "root";
    private static String databasePass = "";
    private static String teacherName;
    private static String courseName;
    private static String courseId;
    private static String teacherId;
    private static String teacherUserName;

    public static String getTeacherUserName() {
        return teacherUserName;
    }

    public static void setTeacherUserName(String teacherUserName) {
        DataManagement.teacherUserName = teacherUserName;
    }

    public static String getDepartmentId() {
        return departmentId;
    }

    public static void setDepartmentId(String departmentId) {
        DataManagement.departmentId = departmentId;
    }

    public static String getDepartmentName() {
        return departmentName;
    }

    public static void setDepartmentName(String departmentName) {
        DataManagement.departmentName = departmentName;
    }

    private static String departmentId;
    private static String departmentName;

    public static String getTeacherName() {
        return teacherName;
    }

    public static void setTeacherName(String teacherName) {
        DataManagement.teacherName = teacherName;
    }

    public static String getCourseName() {
        return courseName;
    }

    public static void setCourseName(String courseName) {
        DataManagement.courseName = courseName;
    }

    public static String getCourseId() {
        return courseId;
    }

    public static void setCourseId(String courseId) {
        DataManagement.courseId = courseId;
    }

    public static String getTeacherId() {
        return teacherId;
    }

    public static void setTeacherId(String teacherId) {
        DataManagement.teacherId = teacherId;
    }

    public static ResultSet getDataFromQuery(String query){
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DriverManager.getConnection(databaseHost,databaseUser,databasePass);
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public static void successfullLoginHandler(String teacherId,String teacherName,String teacherUserName){
       setTeacherId(teacherId);
        System.out.println("TEacher id + "+teacherId);
       setTeacherName(teacherName);
       setTeacherUserName(teacherUserName);

     }




}
