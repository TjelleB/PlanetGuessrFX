package com.example.planetguessrfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    //FXML Objects
    public Pane pMainBackground;
    public Label lblTitle;
    public Pane pResumeDialog;
    public Button btnResume;
    public TextField txtPlayer1Name;
    public TextField txtPlayer4Name;
    public TextField txtPlayer3Name;
    public TextField txtPlayer2Name;
    public ChoiceBox cbPlayers;

    private final ObservableList<String> playerList = FXCollections.observableArrayList("1 Player","2 Players","3 Players","4 Players");
    public String playerName1;
    public String playerName2;
    public String playerName3;
    public String playerName4 ;
    public int players = 1;
    public TextField txtSIDInput;

    @FXML
    private void initialize(){
        cbPlayers.setItems(playerList);
    }
    //Überprüft ob ein spieler einen Namen eigegeben hat, wenn nicht wird ein standardname verwendet
    public void checkNames(){
        String[] playerNames = new String[]{txtPlayer1Name.getText(), txtPlayer2Name.getText(), txtPlayer3Name.getText(), txtPlayer4Name.getText()};
        String[] defaultNames = new String[]{"Player 1", "Player 2", "Player 3", "Player 4"};

        for(int i = 0; i < playerNames.length; i++){
            if(playerNames[i] == ""){
                playerNames[i] = defaultNames[i];
            }
        }
        playerName1 = playerNames[0];
        playerName2 = playerNames[1];
        playerName3 = playerNames[2];
        playerName4 = playerNames[3];
    }


    //lädt den Game screen und übergibt die spielernamen
    public void startGame(ActionEvent event) throws IOException{
        checkNames();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameScreen.fxml"));
        Parent root = loader.load();
        GameScreenController controller = loader.getController();
        controller.setPlayers(players, playerName1, playerName2, playerName3, playerName4);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 480, 640);
        stage.setScene(scene);
        stage.show();
    }
    public void resumeGame(ActionEvent event) throws IOException {
        try {
            int sID = Integer.parseInt(txtSIDInput.getText());
            database db = new database();
            db.loadGame(sID);
            int playercount = db.loadSpielerAnzahl();
            String[] playernames = db.loadPlayerNameGame();
            int[] playerscore = db.loadPlayerScore();
            for (int i = 0; i < 4; i++) {
                System.out.println(playercount);
                System.out.println(playernames[i]);
                System.out.println(playerscore[i]);
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gameScreen.fxml"));
            Parent root = loader.load();
            GameScreenController controller = loader.getController();
            controller.setPlayers(playercount, playernames[0], playernames[1], playernames[2], playernames[3]);
            controller.setScores(playerscore[0], playerscore[1], playerscore[2], playerscore[3]);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 480, 640);
            stage.setScene(scene);
            stage.show();

        } catch (NumberFormatException e) {
            System.out.println("Keine SaveID");
        }
    }
    //Zeigt die option an, mit einem code ein spiel weiterzuführen
    public void showResumeDialog(){
        pResumeDialog.setVisible(true);
    }

    //zeigt/versteckt bei änderung der spieleranzahl die Namenseingabe felder
    public void playersSelected(){
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