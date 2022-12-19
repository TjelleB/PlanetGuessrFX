package com.example.planetguessrfx;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
public class PlanetGuessr extends Application {
    private static final Image stone  = new Image("com/example/planetguessrfx/Pictures 1/Planet Imgs/Stone.gif");
    private static final Image water = new Image("com/example/planetguessrfx/Pictures 1/Planet Imgs/Water.gif");
    private static final Image gas = new Image("com/example/planetguessrfx/Pictures 1/Planet Imgs/Gas.gif");
    private static final Image ice = new Image("com/example/planetguessrfx/Pictures 1/Planet Imgs/Ice.gif");
    private static final Image noAtmos = new Image("com/example/planetguessrfx/Pictures 1/Planet Imgs/No Atmos.gif");
    private static final Image background = new Image("com/example/planetguessrfx/Pictures 1/BackgroundGalaxy.gif");
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("PlanetGuessr");
        stage.setScene(new Scene(new FXMLLoader(getClass().getResource("mainMenu.fxml")).load(), 480, 640));
        stage.show();
    }
    public static Image getPictures(int i){
        return switch (i) {
            case 0 -> stone;
            case 1 -> water;
            case 2 -> gas;
            case 3 -> ice;
            case 4 -> noAtmos;
            default -> background;
        };
    }
    public static void main(String[] args) {launch();}
}