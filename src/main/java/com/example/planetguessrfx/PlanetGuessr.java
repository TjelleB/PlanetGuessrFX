package com.example.planetguessrfx;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class PlanetGuessr extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        stage.setTitle("PlanetGuessr");
        stage.setScene(new Scene(loader.load(), 480, 640));
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}