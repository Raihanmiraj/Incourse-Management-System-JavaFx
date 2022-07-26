package Models;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MECCSEPORTAL extends Application {
    public void start(Stage stage) throws IOException {

//        Scene scene = new Scene(fxmlLoader.load(), 1100, 680);
//

//        stage.setScene(scene);
//        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/CSEDULOGO.png")));
//        stage.getIcons().add(image);
//        stage.show();
//        Parent pane   = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
//        Parent pane   = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
//        Parent pane   = FXMLLoader.load(getClass().getResource("TeacherView/DashBoard.fxml"));

       Parent pane   = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(pane, 1100, 680);
        stage.setTitle("MEC PROJECT");
        stage.setScene(scene);
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/meclogo.png")));
        stage.getIcons().add(image);
//      stage.initStyle(StageStyle.UNDECORATED);
//      stage.setMaximized(true);
//        stage.setFullScreen(true);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }


}