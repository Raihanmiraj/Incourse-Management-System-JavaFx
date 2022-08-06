package Controllers.TeachersController;

import Controllers.DatabaseQuery;
import Models.DataManagement;
import Models.TeachersModel.ClassTestAndAssignmentList;
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

public class ClassTestAndAssignmentListController implements Initializable {
    private ObservableList<ClassTestAndAssignmentList> data;


    @FXML
    private TextField searchBox;

    @FXML
    private TableView<ClassTestAndAssignmentList> tableView;



    @FXML
    private TextField incourseTypeMarks;

    @FXML
    private TextField incourseTypeName;



    @FXML
    void addNewHandler(ActionEvent event) {
      String incourseTName =   incourseTypeName.getText();
       Integer incourseMark = Integer.parseInt(incourseTypeMarks.getText());
int res = ClassTestAndAssignmentList.addIncourseType(incourseTName,incourseMark,courseId, Integer.parseInt(DataManagement.getTeacherId()));
Button b = new Button();
b.setId(String.valueOf(res));
        ClassTestAndAssignmentList cl =      new ClassTestAndAssignmentList(res,incourseTName,incourseMark,b);
        tableView.getItems().add(cl);
    }

    @FXML
    void deleteButtonHandler(ActionEvent event) {
        ClassTestAndAssignmentList selectedItem = tableView.getSelectionModel().getSelectedItem();
     Integer idtoDelete =  tableView.getSelectionModel().getSelectedItem().getId();
        tableView.getItems().remove(selectedItem);
     ClassTestAndAssignmentList.DeleteIncourseType(idtoDelete);
    }



    @FXML
    void updateButtonHandler(ActionEvent event) {

    }

    private static int courseId;

    public static int getCourseId() {
        return courseId;
    }

    public static void setCourseId(int courseId) {
        ClassTestAndAssignmentListController.courseId = courseId;
    }
    public void buildData() {
        try {
String query = "SELECT * FROM `incourse_type` WHERE  `teacher_id` = "+ DataManagement.getTeacherId() +" AND course_id ="+getCourseId();
            ResultSet rs = DatabaseQuery.get(query);
            TableColumn id = new TableColumn("ID");
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn incourseTypename = new TableColumn("Incourse");
            incourseTypename.setCellValueFactory(new PropertyValueFactory("incourseTypeName"));
            TableColumn totalMarks = new TableColumn("total marks");
            totalMarks.setCellValueFactory(new PropertyValueFactory("totalMarks"));

            TableColumn View = new TableColumn("View");
            View.setCellValueFactory(new PropertyValueFactory("viewButton"));

//            ObservableList<String> list = FXCollections.observableArrayList();
//            tableView.setItems(data);
//            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            tableView.getColumns().addAll(id, incourseTypename, totalMarks);



            final ObservableList<ClassTestAndAssignmentList> data = FXCollections.observableArrayList(


//                     new ClassTestAndAssignmentList(2,"ok", 5, 1.2)
            );
            while (rs.next()){
                Button button = new Button("View");
                button.setId(String.valueOf(rs.getInt("id")));
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
//                        logger.info("OnAction {}", event);
                        System.out.println(event);

                    }
                });
                data.add(  new ClassTestAndAssignmentList(rs.getInt("id"), rs.getString("incoursetype_name"), rs.getInt("total_marks") , button));
            }

      tableView.setItems(data);
            tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {



                    String incourseName = (String) tableView.getSelectionModel().getSelectedItem().getIncourseTypeName();
                    String incourseMarks = String.valueOf(tableView.getSelectionModel().getSelectedItem().getTotalMarks());

                    incourseTypeName.setText(incourseName);
            incourseTypeMarks.setText(incourseMarks);
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buildData();
    }

    private Predicate<ClassTestAndAssignmentList> createPredicate(String searchText){
        return order -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsOrder(order, searchText);
        };
    }

    private ObservableList<ClassTestAndAssignmentList> filterList(List<ClassTestAndAssignmentList> list, String searchText){
        List<ClassTestAndAssignmentList> filteredList = new ArrayList<>();

        for (ClassTestAndAssignmentList order : list){
            if(searchFindsOrder(order, searchText)){
                filteredList.add(order);
            }
        }
        return FXCollections.observableList(filteredList);
    }

    private boolean searchFindsOrder(ClassTestAndAssignmentList data, String searchText){
        return (Integer.valueOf(data.getId()).toString().contains(searchText)) ||
                (data.getIncourseTypeName().toLowerCase().contains(searchText)) ||
                Integer.valueOf(data.getTotalMarks()).toString().equals(searchText) ;


    }

    @FXML
    private void handleExitButtonClicked(ActionEvent event) {
        Platform.exit();
        event.consume();
    }


    public void handleClearSearchText(ActionEvent event) {
        searchBox.setText("");
        event.consume();
    }
}
