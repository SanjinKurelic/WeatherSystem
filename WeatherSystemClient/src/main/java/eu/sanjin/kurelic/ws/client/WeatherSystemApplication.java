package eu.sanjin.kurelic.ws.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WeatherSystemApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        var fxmlLoader = new FXMLLoader(WeatherSystemApplication.class.getResource("ws_main_window.fxml"));
        var scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setResizable(false);
        stage.setTitle("Weather System Client");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
