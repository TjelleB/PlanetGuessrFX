package com.example.planetguessrfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class PlanetGuessr extends Application {

    private Math m = new Math();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlanetGuessr.class.getResource("mainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 480, 640);
        stage.setTitle("PlanetGuessr");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
