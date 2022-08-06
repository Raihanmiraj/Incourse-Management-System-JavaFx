package Models.TeachersModel;

import Controllers.DatabaseQuery;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SingleCourseStudentsMark {
    public static ArrayList<String> getIncourseTypeId() {
        return IncourseTypeId;
    }

    public static ArrayList<String> getIncourseType() {
        return IncourseType;
    }

    private static ArrayList<String> IncourseType = new ArrayList<>();
    private static ArrayList<String> IncourseTypeId = new ArrayList<>();

    public static ResultSet getData(Integer courseId, Integer teacherId) {
        IncourseType.clear();
        IncourseTypeId.clear();
        ResultSet alldata = null;
        String query = "SELECT * FROM `incourse_type` WHERE  `course_id` =" + courseId + "  AND teacher_id = " + teacherId;
        try {
            ResultSet rs = DatabaseQuery.get(query);
            String groupMake = "SELECT students.id as StudentId, students.reg_no as \"Registration No\", students.student_name as \"Student Name\", ";
            int count = 0;
            while (rs.next()) {

                String columName = rs.getString("incoursetype_name");
                System.out.println(columName);
                IncourseType.add(columName);
                IncourseTypeId.add(rs.getString("id"));
                groupMake += "GROUP_CONCAT( if(incourse_type.incoursetype_name='" + columName + "',incourses_mark.marks,NULL) ) AS '" + columName + "' , ";
                count++;
            }
            groupMake += "  attendance.marks as \"Attendance Mark\", (FORMAT((sum(incourses_mark.marks)/3),2)+IF(attendance.marks IS NULL,0,attendance.marks)) as Average FROM `incourses_mark` INNER JOIN incourse_type on incourse_type.id = incourses_mark.incoursetype_id INNER JOIN students on students.id = incourses_mark.student_id LEFT JOIN attendance on students.id = attendance.student_id AND attendance.course_id = incourse_type.course_id WHERE incourse_type.course_id = '" + courseId + "' AND incourse_type.teacher_id ='" + teacherId + "' GROUP BY students.id ";

//   , AVG(incourses_mark.marks) as avrg      String SQL = "SELECT students.reg_no as \"Registration No\", students.student_name as \"Student Name\",  GROUP_CONCAT( if(incourse_type.incoursetype_name='Class Test 1',incourses_mark.marks,NULL) ) AS 'Class Test 1' , GROUP_CONCAT( if(incourse_type.incoursetype_name='Class Test 2',incourses_mark.marks,NULL) ) AS 'Class Test 2' ,  attendance.marks as \"Attendance Mark\" FROM `incourses_mark` INNER JOIN incourse_type on incourse_type.id = incourses_mark.incoursetype_id INNER JOIN students on students.id = incourses_mark.student_id LEFT JOIN attendance on students.id = attendance.student_id AND attendance.course_id = incourse_type.course_id WHERE incourse_type.course_id = '"+courseId+"' AND incourse_type.teacher_id ='"+teacherId+"' GROUP BY students.id ";


            System.out.println(groupMake);
            System.out.println(courseId);
//         String sql = "SELECT students.reg_no as \"Registration No\", students.student_name as \"Student Name\", GROUP_CONCAT( if(incourse_type.incoursetype_name='Class Test 1',incourses_mark.marks,NULL) ) AS 'Class Test 1' , GROUP_CONCAT( if(incourse_type.incoursetype_name='Class Test 2',incourses_mark.marks,NULL) ) AS 'Class Test 2' , GROUP_CONCAT( if(incourse_type.incoursetype_name='class test 3',incourses_mark.marks,NULL) ) AS 'class test 3', attendance.marks as \"Attendance Mark\", AVG(incourses_mark.marks) as avrg FROM `incourses_mark` INNER JOIN incourse_type on incourse_type.id = incourses_mark.incoursetype_id INNER JOIN students on students.id = incourses_mark.student_id LEFT JOIN attendance on students.id = attendance.student_id AND attendance.course_id = incourse_type.course_id WHERE incourse_type.course_id = '1' AND incourse_type.teacher_id ='1' GROUP BY students.id";
            alldata = DatabaseQuery.get(groupMake);
        } catch (Exception e) {

        }
        return alldata;
    }

    public static int UpdateIncourseMark(Double marks, Integer incourseTypeId, Integer reg_no) {
        String query = "SELECT id FROM `students` WHERE  `reg_no`  =" + reg_no;
        ResultSet rs = DatabaseQuery.get(query);
        String studentId = null;
        System.out.println(rs);
        try {

            if (rs.next()) {
                System.out.println("student id " + rs.getString("id"));
                studentId = rs.getString("id");
            }

        } catch (Exception e) {

        }

        if (studentId != null) {
            String updatequery = "UPDATE `incourses_mark` set `marks`= " + marks + "  WHERE student_id =  " + studentId + "  AND incoursetype_id = " + incourseTypeId;
            String insertQuery = "INSERT INTO `incourses_mark`( `student_id`, `incoursetype_id`, `marks`) VALUES ( '" + studentId + "','" + incourseTypeId + "', '" + marks + "')";
            return DatabaseQuery.updateOrinsert(updatequery, insertQuery);
        }
        return 0;

    }

    public static int UpdateAttendanceMark(Double marks, Integer courseId, Integer teacherId, Integer studentId) {
        String updatequery = "UPDATE `attendance` SET  `marks`= '" + marks + "' WHERE course_id = '" + courseId + "' AND teacher_id = '" + teacherId + "' AND student_id = '" + studentId + "'";
        String insertQuery = "INSERT INTO `attendance`(`student_id`, `teacher_id`, `course_id`, `marks`)   VALUES ( '" + studentId + "','" + teacherId + "', '" + courseId + "', '" + marks + "')";
        System.out.println(updatequery);
        System.out.println(insertQuery);
        return DatabaseQuery.updateOrinsert(updatequery, insertQuery);

    }
}
