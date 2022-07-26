package Controllers.TeachersController;

import Controllers.DatabaseQuery;
import Models.DataManagement;
import Models.TeachersModel.StudentsPage;
import Models.TeachersModel.StudentsPage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class StudentsPageController implements Initializable {
    private ObservableList<StudentsPage> data;


    @FXML
    private TextField RegistrationNoF;

    @FXML
    private TextField SemesterF;

    @FXML
    private TextField YearF;

    @FXML
    private TextField departmentIdF;

    @FXML
    private TextField searchBox;

    @FXML
    private TextField studentIdF;

    @FXML
    private TextField studentNameF;

    @FXML
    private TableView<StudentsPage> tableView;


    @FXML
    void AddStudentHandler(ActionEvent event) {
  int y =  StudentsPage.insertStudentData(studentNameF.getText(), RegistrationNoF.getText(),departmentIdF.getText() ,SemesterF.getText(),YearF.getText());
        StudentsPage cl =      new StudentsPage(y ,studentNameF.getText(),Integer.parseInt(RegistrationNoF.getText()),departmentIdF.getText(),Integer.parseInt(YearF.getText()), Integer.parseInt(SemesterF.getText()));
        tableView.getItems().add(cl);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buildData();
    }

    public void buildData() {
        try {
            String query = "SELECT DISTINCT students.id , students.student_name , students.reg_no , departments.department_name , students.s_semester , students.s_year FROM students INNER JOIN departments on departments.id = students.department_id INNER JOIN courses on courses.department_id =students.department_id INNER JOIN course_teachers on course_teachers.course_id =courses.id WHERE course_teachers.teacher_id ="+ DataManagement.getTeacherId();
            ResultSet rs = DatabaseQuery.get(query);
            TableColumn id = new TableColumn("ID");
            id.setCellValueFactory(new PropertyValueFactory<>("studentId"));
            TableColumn stdname = new TableColumn("Name");
            stdname.setCellValueFactory(new PropertyValueFactory("studentName"));
            TableColumn regno = new TableColumn("Reg");
            regno.setCellValueFactory(new PropertyValueFactory("registrationNo"));
            TableColumn dptname = new TableColumn("DepartMent");
            dptname.setCellValueFactory(new PropertyValueFactory("departmentName"));

            TableColumn year = new TableColumn("Year");
            year.setCellValueFactory(new PropertyValueFactory("year"));

            TableColumn semster = new TableColumn("Semester");
            semster.setCellValueFactory(new PropertyValueFactory("semester"));
//            ObservableList<String> list = FXCollections.observableArrayList();
//            tableView.setItems(data);
//            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            tableView.getColumns().addAll(id, stdname,regno,dptname,year, semster);



            final ObservableList<StudentsPage> data = FXCollections.observableArrayList(


//                     new StudentsPage(2,"ok", 5, 1.2)
            );
            while (rs.next()){


                data.add(  new StudentsPage(rs.getInt("id"), rs.getString("student_name"), rs.getInt("reg_no") ,  rs.getString("department_name") , rs.getInt("s_semester") , rs.getInt("s_year") ));
            }

            tableView.setItems(data);
            tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {



//                 (String) tableView.getSelectionModel().getSelectedItem().getIncourseTypeName();
//                   String.valueOf(tableView.getSelectionModel().getSelectedItem().getTotalMarks());


                }
            });

            searchBox.textProperty().addListener((observable, oldValue, newValue) ->{
                        tableView.setItems(filterList(data, newValue.toLowerCase()));
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
    private Predicate<StudentsPage> createPredicate(String searchText){
        return order -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsOrder(order, searchText);
        };
    }

    private ObservableList<StudentsPage> filterList(List<StudentsPage> list, String searchText){
        List<StudentsPage> filteredList = new ArrayList<>();

        for (StudentsPage order : list){
            if(searchFindsOrder(order, searchText)){
                filteredList.add(order);
            }
        }
        return FXCollections.observableList(filteredList);
    }

    private boolean searchFindsOrder(StudentsPage data, String searchText){
//        return (Integer.valueOf(data.getStudentId()).toString().contains(searchText)) ||
//                (data.getStudentName().toLowerCase().contains(searchText)) ||
//                Integer.valueOf(data.getDepartmentName()).toString().equals(searchText) ||Integer.valueOf(data.getRegistrationNo()).toString().equals(searchText) ;

return data.getStudentName().contains(searchText) ||  String.valueOf(data.getRegistrationNo()).contains(searchText) || data.getDepartmentName().contains(searchText);
    }

    @FXML
    private void handleExitButtonClicked(ActionEvent event) {
        Platform.exit();
        event.consume();
    }

//    @FXML
//    private void handleGitButtonClicked(ActionEvent event) {
//        new Application() {
//            @Override
//            public void start(Stage stage) {
//            }
//        }.getHostServices().showDocument("https://github.com/edencoding/javafx-ui/");
//        event.consume();
//    }

    public void handleClearSearchText(ActionEvent event) {
        searchBox.setText("");
        event.consume();
    }
}
