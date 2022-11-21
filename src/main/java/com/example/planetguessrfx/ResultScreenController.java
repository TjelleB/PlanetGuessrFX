package com.example.planetguessrfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ResultScreenController {
    public Label txtNr1Score;
    public Label txtNr1UpdatedScore;
    public Label txtNr2Score;
    public Label txtNr2UpdatedScore;
    public Label txtNr3Score;
    public Label txtNr3UpdatedScore;
    public Label txtNr4Score;
    public Label txtNr4UpdatedScore;
    public Button bttnNextPlanet;

    int players;
    String playerName1;
    String playerName2;
    String playerName3;
    String playerName4;

    public void setPlayers(int p, String p1, String p2, String p3, String p4) {
        players = p;
        playerName1 = p1;
        playerName2 = p2;
        playerName3 = p3;
        playerName4 = p4;
    }


    public void nextPlanet(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameScreen.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 480, 640);
        stage.setScene(scene);
        stage.show();
    }

    public void returnToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 480, 640);
        stage.setScene(scene);
        stage.show();
    }
}