package com.example.planetguessrfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class ResultScreenController {
    //FXML objekte
    public Label txtNr1Score;
    public Label txtNr1UpdatedScore;
    public Label txtNr2Score;
    public Label txtNr2UpdatedScore;
    public Label txtNr3Score;
    public Label txtNr3UpdatedScore;
    public Label txtNr4Score;
    public Label txtNr4UpdatedScore;
    public Button bttnNextPlanet;
    public VBox box1;
    public VBox box2;
    public VBox box3;
    public VBox box4;
    public Pane pSaveGame;
    public Button btnMenu;
    public Button btnSave;
    public Label lblYourCode;

    Stage stage;
    Scene scene;
    int players;
    String playerName1;
    String playerName2;
    String playerName3;
    String playerName4;
    int updatedScore1;
    int updatedScore2;
    int updatedScore3;
    int updatedScore4;

    //Übernimmt die Spieleranzahl und Namen von der GameScreenController Klasse
    public void setPlayers(int p, String p1, String p2, String p3, String p4) {
        players = p;
        playerName1 = p1;
        playerName2 = p2;
        playerName3 = p3;
        playerName4 = p4;
        // Bestimmt wie viele vboxen angezeigt wird, jede vbox zeigt den score von einem spieler an
        switch (p) {
            case 1 -> {
                box2.setVisible(false);
                box3.setVisible(false);
                box4.setVisible(false);
            }
            case 2 -> {
                box3.setVisible(false);
                box4.setVisible(false);
            }
            case 3 -> box4.setVisible(false);
        }
    }

    //Zeigt die Spiel speichern Option an
    public void displaySaveOption(){
        pSaveGame.setVisible(true);
    }

    //Übernimmt den score(s1-4) und die erhaltenden punkte(gs1-4) der letzten runde und zeigt diese an
    public void displayScore(int s1, int s2, int s3, int s4,int gs1, int gs2, int gs3, int gs4){
        updatedScore1 = gs1 + s1;
        updatedScore2 = gs2 + s2;
        updatedScore3 = gs3 + s3;
        updatedScore4 = gs4 + s4;

        txtNr1Score.setText("Player 1: " + playerName1 + " + " + gs1);
        txtNr1UpdatedScore.setText("Updated Score: " + updatedScore1);
        txtNr2Score.setText("Player 2: " + playerName2 + " + " + gs2);
        txtNr2UpdatedScore.setText("Updated Score: " + updatedScore2);
        txtNr3Score.setText("Player 3: " + playerName3 + " + " + gs3);
        txtNr3UpdatedScore.setText("Updated Score: " + updatedScore3);
        txtNr4Score.setText("Player 4: " + playerName4 + " + " + gs4);
        txtNr4UpdatedScore.setText("Updated Score: " + updatedScore4);
    }

    //Übergibt die Spielernamen und den Updated score an die database klasse
    public void saveScore() throws SQLException {
        database db = new database();
        db.connect();
        db.save(players,playerName1,playerName2,playerName3,playerName4,updatedScore1,updatedScore2,updatedScore3,updatedScore4);
        lblYourCode.setText("Your code is: " + db.getSaveID());
        btnSave.setDisable(true);
    }

    //Lädt eine neue instanz des game screens und einen neuen Planeten, übergibt den score und spieleranzahl+namen
    public void nextPlanet(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameScreen.fxml"));
        Parent root = loader.load();
        GameScreenController controller = loader.getController();
        controller.setScores(updatedScore1, updatedScore2, updatedScore3, updatedScore4);
        controller.setPlayers(players, playerName1, playerName2, playerName3, playerName4);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 480, 640);
        stage.setScene(scene);
        stage.show();
    }

    //Wechselt zum Hauptmenü
    public void returnToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 480, 640);
        stage.setScene(scene);
        stage.show();
    }
}