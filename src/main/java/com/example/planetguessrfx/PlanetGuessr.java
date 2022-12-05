package com.example.planetguessrfx;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class PlanetGuessr extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("PlanetGuessr");
        stage.setScene(new Scene(new FXMLLoader(getClass().getResource("mainMenu.fxml")).load(), 480, 640));
        stage.show();
    }
    public static void main(String[] args) {launch();}
}