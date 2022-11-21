package com.example.planetguessrfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    public Pane pMainBackground;

    public Label lblTitle;

    ObservableList<String> playerList = FXCollections.observableArrayList("1 Player","2 Players","3 Players","4 Players");

    public TextField txtPlayer1Name;
    public TextField txtPlayer4Name;
    public TextField txtPlayer3Name;
    public TextField txtPlayer2Name;
    public ChoiceBox cbPlayers;

    private Stage stage;
    private Scene scene;

    public String playerName1;
    public String playerName2;
    public String playerName3;
    public String playerName4 ;

    public int players = 1;

    @FXML
    private void initialize(){
        cbPlayers.setItems(playerList);
    }



    public void checkNames(){
        playerName1 = txtPlayer1Name.getText();
        if(playerName1 == ""){ playerName1 = "Player 1";}
        playerName2 = txtPlayer2Name.getText();
        if(playerName2 == ""){ playerName2 = "Player 2";}
        playerName3 = txtPlayer3Name.getText();
        if(playerName3 == ""){ playerName3 = "Player 3";}
        playerName4 = txtPlayer4Name.getText();
        if(playerName4 == ""){ playerName4 = "Player 4";}
    }

    public void startGame(ActionEvent event) throws IOException{

        checkNames();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameScreen.fxml"));
        Parent root = loader.load();
        GameScreenController controller = loader.getController();
        controller.genNewPlanet();
        controller.setPlayers(players, playerName1, playerName2, playerName3, playerName4);
        controller.chgBackground();


        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 480, 640);
        stage.setScene(scene);
        stage.show();
    }

    public void playersSelected(ActionEvent event) throws IOException{
        if(cbPlayers.getValue() == "1 Player"){
            players = 1;
            txtPlayer2Name.setVisible(false);
            txtPlayer3Name.setVisible(false);
            txtPlayer4Name.setVisible(false);
        } else if(cbPlayers.getValue() == "2 Players"){
            players = 2;
            txtPlayer2Name.setVisible(true);
            txtPlayer3Name.setVisible(false);
            txtPlayer4Name.setVisible(false);
        } else if(cbPlayers.getValue() == "3 Players"){
            players = 3;
            txtPlayer2Name.setVisible(true);
            txtPlayer3Name.setVisible(true);
            txtPlayer4Name.setVisible(false);
        } else if(cbPlayers.getValue() == "4 Players"){
            players = 4;
            txtPlayer2Name.setVisible(true);
            txtPlayer3Name.setVisible(true);
            txtPlayer4Name.setVisible(true);
        }

    }
}