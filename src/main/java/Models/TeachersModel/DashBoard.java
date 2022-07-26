package Models.TeachersModel;

import Controllers.DatabaseQuery;
import Models.DataManagement;

import java.sql.ResultSet;

public class DashBoard {
    public static ResultSet getClassRoutine(){
        String query = "SELECT * FROM `class_routine` WHERE teacherid = "+ DataManagement.getTeacherId() +" ORDER by date LIMIT 5";
         return DatabaseQuery.get(query);
    }
    public static String getTotalClassToday(){
        String query = "SELECT COUNT(*) as count  FROM class_routine WHERE DATE(date) = CURDATE()";
ResultSet rs = DatabaseQuery.get(query);
String count = null;
try{
    if(rs.next()){
        count = rs.getString("count");
    }

}catch (Exception e){

}
        return count;
    }

    public static String getAttendanceBelowAverage(){
        String query = "SELECT COUNT(*) as count FROM students LEFT JOIN attendance on attendance.student_id = students.id WHERE attendance.marks<7";
        ResultSet rs = DatabaseQuery.get(query);
        String count = null;
        try{
            if(rs.next()){
                count = rs.getString("count");
            }

        }catch (Exception e){

        }
        return count;
    }
    public static String getAttendanceAvobeAverage(){
        String query = "SELECT COUNT(*) as count FROM students LEFT JOIN attendance on attendance.student_id = students.id WHERE attendance.marks>=7";
        ResultSet rs = DatabaseQuery.get(query);
        String count = null;
        try{
            if(rs.next()){
                count = rs.getString("count");
            }

        }catch (Exception e){

        }
        return count;
    }
}
