package Controllers.TeachersController;

import Controllers.DatabaseQuery;
import Models.DataManagement;
import Models.TeachersModel.SingleCourseStudentsMark;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class SingleCourseStudentsMarkController implements Initializable {
    private ObservableList<ObservableList> data;

    ArrayList<String> IncourseType =  SingleCourseStudentsMark.getIncourseType();
    ArrayList<String> IncourseTypeId =  SingleCourseStudentsMark.getIncourseTypeId();
    ArrayList<String> TempNumberSet =  SingleCourseStudentsMark.getIncourseType();
    @FXML
    private TextField searchBox;
    private int StudentId;
    TextField  regTextField = new TextField ();

   private int studentRegistrationNo;
    public int getStudentRegistrationNo() {
        return studentRegistrationNo;
    }

    public void setStudentRegistrationNo(int studentRegistrationNo) {
        this.studentRegistrationNo = studentRegistrationNo;
    }

    public int getStudentId() {
        return StudentId;
    }
private int teacherId;
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
    public void setStudentId(int studentId) {
        this.StudentId = studentId;
    }

    @FXML
    private TableView<ObservableList> tableView;
    private static int courseId;

    public static int getCourseId() {
        return courseId;
    }
    @FXML
    private TextField  attendanceBox;

    @FXML
    private VBox editSideBar;

    @FXML
    private Button incourseMarkButton;
private int trow;
    @FXML
    private VBox sidebarvbox;
    @FXML
    private Button setAttendanceButton;




    private static void changeValueAt(int row, int col, String value, TableView<ObservableList> table) {
        ObservableList newData = table.getItems().get(row);
        newData.set(col, value);
        table.getItems().set(row, newData);
    }


    @FXML
    void incourseMarkUpdateHandler(ActionEvent event) {

        String itemName = regTextField.getText();
        boolean checkDataExist =   searchFindsOrder(data, itemName);
        System.out.println("check data " +checkDataExist);
//     addNewRowTable();

    int j = 3;
    int k = 3;
    setStudentRegistrationNo(Integer.parseInt(regTextField.getText()));

    for (int i = 0; i < IncourseType.size(); i++) {
        TextField ta = (TextField) editSideBar.getChildren().get(j);
        System.out.println(TempNumberSet.get(i).trim() + " === " + ta.getText().trim());

        if (TempNumberSet.get(i).trim().equals(ta.getText().trim())) {

        } else {
            String GetMarkFromText =ta.getText().trim();


            if(GetMarkFromText.equals("") || GetMarkFromText.equals("Absent")){
                GetMarkFromText = "0";
            }else{
                GetMarkFromText = GetMarkFromText;
                System.out.println("Get Text Mark = "+ GetMarkFromText);


            }
            TempNumberSet.set(i, GetMarkFromText);
            int x = SingleCourseStudentsMark.UpdateIncourseMark(Double.valueOf(GetMarkFromText), Integer.parseInt(ta.getId()), getStudentRegistrationNo());
            System.out.println("Registrsyipm no " + getStudentRegistrationNo());
            if(checkDataExist){
                changeValueAt(trow, k, GetMarkFromText, tableView);
            }



        }
        k++;
        j += 2;
    }
    if(!checkDataExist){
        addNewRowTable();
    }else{
        updateAverageNumber();
    }


}




    void addNewRowTable(){

            String query = "SELECT students.*, attendance.marks FROM `students` LEFT JOIN attendance on attendance.student_id = students.id WHERE students.reg_no ="+regTextField.getText();
            ResultSet rs =    DatabaseQuery.get(query);
            String studentid = null;
            System.out.println(rs);
            String marks =  null;
            String student_name =  null;

            try{

                if(rs.next()){
                    System.out.println( "student id " + rs.getString("id"));
                    studentid = rs.getString("id");
                    student_name = rs.getString("student_name");
                    marks = rs.getString("marks");
                }
                ObservableList<String> row2 = FXCollections.observableArrayList();
                row2.add(String.valueOf(studentid));
                row2.add(String.valueOf(getStudentRegistrationNo()));
                row2.add(student_name);
                int z = 3;
                System.out.println("Marks start");
                Double addnewmarksum = 0.0;
                for(int l=0;l<IncourseType.size();l++){
                    TextField  aw = (TextField ) editSideBar.getChildren().get(z);
                    System.out.println(aw);
                    String getMarksFromText = aw.getText().trim();
                    if(getMarksFromText.equals("")){
                        row2.add("Absent");
                        getMarksFromText = "0";
                    }else{
                        row2.add(getMarksFromText);
                    }

                    System.out.println("Marks is " +aw.getText().trim());
                    addnewmarksum += Double.valueOf(getMarksFromText);
                    z+=2;

                }
                String attendancemark = "0";
if(marks==null){
    row2.add("Absent");

}else{
    attendancemark = marks;
    row2.add(marks);
}


                row2.add(String.format("%.2f", (addnewmarksum/IncourseType.size())+Double.valueOf(attendancemark)));
                System.out.println(row2);
                data.add(row2);


            }catch (Exception e){

            }
    }


    @FXML
    void setAttendanceHandler(ActionEvent event) {
        System.out.println(attendanceBox.getText());
      int x =   SingleCourseStudentsMark.UpdateAttendanceMark(Double.valueOf(attendanceBox.getText()),getCourseId(), getTeacherId(),getStudentId());
//        int x =   SingleCourseStudentsMark.UpdateAttendanceMark(Double.valueOf(attendanceBox.getText()),getCourseId(), Integer.parseInt(DataManagement.getTeacherId()),getStudentId());

        System.out.println(x);
        int y = IncourseType.size()+3;
        changeValueAt(trow, y ,String.valueOf(Double.valueOf(attendanceBox.getText().trim())), tableView);
        updateAverageNumber();
    }
    void updateAverageNumber(){
double Average = 0;

int count  = IncourseType.size();
int j = 0;
        for(int i = 0 ; i<count;i++){
            j = i+3;
            String getDataFromTableView = (String) tableView.getSelectionModel().getSelectedItem().get(j);

            if(getDataFromTableView.equals("") || getDataFromTableView.equals("Absent")){

            }else{
                Average+=   Double.valueOf(getDataFromTableView) ;
            }


        }
        j+=1;
        String attendancemarkfromtable = (String) tableView.getSelectionModel().getSelectedItem().get(j);
        if(attendancemarkfromtable.equals("") || attendancemarkfromtable.equals("Absent")){
            Average = (Average/count)   ;
        }else{
            Average = (Average/count) + Double.valueOf(attendancemarkfromtable)  ;
        }

        j+=1;
        System.out.println(j);
        changeValueAt(trow, j ,String.format("%.2f", Average) , tableView);

    }
    public static void setCourseId(int courseId) {
        SingleCourseStudentsMarkController.courseId = courseId;

    }


    public void buildData() {
        Connection c;
        data = FXCollections.observableArrayList();
        try {

//             ResultSet rs = SingleCourseStudentsMark.getData(getCourseId(), Integer.valueOf(DataManagement.getTeacherId()));

            ResultSet rs = SingleCourseStudentsMark.getData(getCourseId(),getTeacherId());
//            rs.getMetaData().getColumnName();
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }

                });

                tableView.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    if(rs.getString(i)==null){
                        row.add("Absent");
                    }else{
                        row.add(rs.getString(i));
                    }

                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }
//for(int i=0;i<5;i++){
//    ObservableList<String> row2 = FXCollections.observableArrayList();
//    row2.add("ok");
//    row2.add("12");
//    row2.add("12"); row2.add("12"); row2.add("12");
//    row2.add("work");
//    System.out.println(row2);
//    data.add(row2);
//}


            tableView.setEditable(true);

            tableView.setItems(data);

            tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    TempNumberSet.clear();
                    trow = tableView.getSelectionModel().getSelectedIndex();


                    setStudentId(Integer.parseInt((String)  tableView.getSelectionModel().getSelectedItem().get(0)));
                    setStudentRegistrationNo(Integer.parseInt((String)  tableView.getSelectionModel().getSelectedItem().get(1)));
                    regTextField.setText((String) tableView.getSelectionModel().getSelectedItem().get(1));
                    if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                            int j =3;
                        for(int i =3 ;i<editSideBar.getChildren().size();i+=2){
                             TextField  ta = (TextField ) editSideBar.getChildren().get(i);
                             String marks = String.valueOf(tableView.getSelectionModel().getSelectedItem().get(j++));
                            TempNumberSet.add(marks);
                            ta.setText(marks);

                        }
                        attendanceBox.setText((String) tableView.getSelectionModel().getSelectedItem().get(j++));
  }
//                    setStudentId((Integer) tableView.getSelectionModel().getSelectedItem().get(0));
//                    System.out.println(tableView.getSelectionModel().getSelectedItem().get(0));
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
        setTeacherId(Integer.valueOf(DataManagement.getTeacherId()));
        buildData();
        Label lbr = new Label("registration No");
        editSideBar.getChildren().add(lbr);

        regTextField.setPromptText("Registration No");
        regTextField.setId("reg_no");
        regTextField.setPrefHeight(25);
        editSideBar.getChildren().add(regTextField);
//        TextField tf = new TextField();
//        sidebarvbox.getChildren().add(tf);
int incourseTypeSize = IncourseType.size();
       for(int i = 0 ;i<incourseTypeSize;i++){
           Label lb = new Label(IncourseType.get(i));

           TextField  a = new TextField ();
           a.setPromptText(IncourseType.get(i));
           a.setId(IncourseTypeId.get(i));
            a.setPrefHeight(25);
           editSideBar.getChildren().add(lb);
//           a.textProperty().addListener((observable, oldValue, newValue) ->{
//                       System.out.println(newValue);
//           }
//                   );
           editSideBar.getChildren().add(a);
       }

        editSideBar.setPrefHeight((incourseTypeSize+1)*30 - 10);
//       Button b = new Button("Save");
//       b.setOnAction(new EventHandler<ActionEvent>() {
//                   @Override
//                   public void handle(ActionEvent event) {
//                       for(int i = 0 ;i<IncourseType.size();i++){
//                           TextField  ta = (TextField ) editSideBar.getChildren().get(i);
//                           if(IncourseType.get(i).trim().equals(ta.getText().trim()) ){
//                               System.out.println("No  change");
//
//                           }else{
//                               System.out.println("   change");
//
//                           }
//
//                       }
//
//
//   }
//               });
//       editSideBar.getChildren().add(b);


    }

    private Predicate<ObservableList> createPredicate(String searchText){
        return order -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsOrder(order, searchText);
        };
    }

    private ObservableList<ObservableList> filterList(List<ObservableList> list, String searchText){
        List<ObservableList> filteredList = new ArrayList<>();

        for (ObservableList order : list){
            if(searchFindsOrder(order, searchText)){
                filteredList.add(order);
            }
        }
        return FXCollections.observableList(filteredList);
    }

    private boolean searchFindsOrder(ObservableList data, String searchText){
        boolean x = false;
        for(int i = 0 ;i<data.size();i++){
            if( String.valueOf(data.get(i)).toLowerCase().contains(searchText)){
    x = true;
    break;
            }
        }
return x;
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

    public void newAddHandler(ActionEvent actionEvent) {
    }
}
