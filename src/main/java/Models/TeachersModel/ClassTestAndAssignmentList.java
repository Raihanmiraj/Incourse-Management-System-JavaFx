package Models.TeachersModel;

import Controllers.DatabaseQuery;
import javafx.scene.control.Button;

import java.sql.ResultSet;

public class ClassTestAndAssignmentList {
    private int id;



    private String incourseTypeName;
//    private String viewButton;
    private Button viewButton;

    public ClassTestAndAssignmentList(int id, String incourseTypeName, int totalMarks,   Button viewButton) {
        this.id = id;
        this.incourseTypeName = incourseTypeName;
        this.viewButton = viewButton;
        this.totalMarks = totalMarks;

    }

    public Button getViewButton() {

        return viewButton;
    }

    public void setViewButton(Button viewButton) {
        this.viewButton = viewButton;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIncourseTypeName() {
        return incourseTypeName;
    }

    public void setIncourseTypeName(String incourseTypeName) {
        this.incourseTypeName = incourseTypeName;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }



    private int totalMarks ;

    public static int addIncourseType(String iTName, Integer marks , Integer courseId, Integer TeacherId){
String query = "INSERT INTO `incourse_type`(`course_id`, `incoursetype_name`, `teacher_id`, `total_marks`) VALUES ('"+courseId+"','"+iTName+"','"+TeacherId+"','"+marks+"')";
        System.out.println(query);

   return  DatabaseQuery.insertGetId(query);
    }
public static int DeleteIncourseType(int id){
        String query = "DELETE incourse_type, incourses_mark FROM `incourse_type` INNER JOIN incourses_mark on  incourse_type.id  = incourses_mark.incoursetype_id WHERE  incourse_type.id =  "+id;
      return   DatabaseQuery.delete(query);
}

}
