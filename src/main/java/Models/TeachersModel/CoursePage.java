package Models.TeachersModel;

import Controllers.DatabaseQuery;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CoursePage {
    protected static ArrayList<String>CoursesName = new ArrayList<>();

      public static ResultSet getAllCoursesData(Integer teacherId){
          ResultSet rs = null;
          String query = "SELECT courses.* FROM `course_teachers` INNER JOIN teachers on course_teachers.teacher_id = teachers.id INNER JOIN courses on course_teachers.course_id = courses.id WHERE course_teachers.teacher_id = " +teacherId;
          rs = DatabaseQuery.get(query);
          return rs;
      }

}
