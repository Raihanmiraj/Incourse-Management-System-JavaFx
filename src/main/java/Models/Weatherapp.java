package Models;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Weatherapp extends Application {
    public void start(Stage stage) throws IOException {

        Parent pane   = FXMLLoader.load(getClass().getResource("weather.fxml"));
        Scene scene = new Scene(pane );
        stage.setTitle("Weather app");
        stage.setScene(scene);

        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
