module Models {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires json.simple;
    requires android.json;


    opens Models to javafx.fxml;
    exports Models;
    exports Controllers;
    opens Controllers to javafx.fxml;
//    exports Models.ClassHierarchy;
//    opens Models.ClassHierarchy to javafx.fxml;


    exports Controllers.TeachersController;
    opens Controllers.TeachersController to javafx.fxml;
    exports Models.TeachersModel;
    opens Models.TeachersModel to javafx.fxml;
}