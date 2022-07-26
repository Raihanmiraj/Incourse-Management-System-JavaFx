package Models.TeachersModel;

import Controllers.DatabaseQuery;

public class StudentsPage {
    private int studentId;
    private String studentName;
    private int registrationNo;
    private int year;
    private int semester;
    private String departmentName;

    public StudentsPage(int studentId, String studentName, int registrationNo, String departmentName, int year, int semester) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.registrationNo = registrationNo;
        this.year = year;
        this.semester = semester;
        this.departmentName = departmentName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(int registrationNo) {
        this.registrationNo = registrationNo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public static int insertStudentData ( String StudentName , String regNo, String departmentId, String semester, String year){
String query = "INSERT INTO `students`(  `student_name`, `s_semester`, `s_year`, `department_id`, `reg_no`, `s_password`) VALUES ('"+StudentName+"','"+semester+"','"+year+"','"+departmentId+"','"+regNo+"','123456' )";
        return DatabaseQuery.insertGetId(query);
    }
}
