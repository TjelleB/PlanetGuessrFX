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
    public Button btnNextPlanet;
    public VBox box1;
    public VBox box2;
    public VBox box3;
    public VBox box4;
    public Pane pSaveGame;
    public Button btnMenu;
    public Button btnSave;
    public Label lblYourCode;
    public Label lblPlanetVal;
    private Stage stage;
    private int players;
    private String playerName1;
    private String playerName2;
    private String playerName3;
    private String playerName4;
    private int updatedScore1;
    private int updatedScore2;
    private int updatedScore3;
    private int updatedScore4;


    //@Tjelle >Übernimmt die Spieleranzahl und Namen von der GameScreenController Klasse
    public void setPlayers(int p, String p1, String p2, String p3, String p4) {
        players = p;
        playerName1 = p1;
        playerName2 = p2;
        playerName3 = p3;
        playerName4 = p4;
        // Bestimmt wie viele vBoxen angezeigt werden, jede vBox zeigt den Score von einem Spieler an
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
    //@Tjelle >zeigt die Speichern-Option an
    public void displaySaveOption(){
        pSaveGame.setVisible(true);
    }

    public void setPlanetVal(long p){
        lblPlanetVal.setText("Planet value: " + p);
    }

    //@Tjelle >Übernimmt den Score(s1-4) und die erhaltenden Punkte(gs1-4) der letzten Runde und zeigt diese an
    public void displayScore(int s1, int s2, int s3, int s4,int gs1, int gs2, int gs3, int gs4){
        updatedScore1 = gs1 + s1;
        updatedScore2 = gs2 + s2;
        updatedScore3 = gs3 + s3;
        updatedScore4 = gs4 + s4;
        txtNr1Score.setText("Player 1: " + playerName1 + " + " + gs1);
        txtNr1UpdatedScore.setText("Updated score: " + updatedScore1);
        txtNr2Score.setText("Player 2: " + playerName2 + " + " + gs2);
        txtNr2UpdatedScore.setText("Updated score: " + updatedScore2);
        txtNr3Score.setText("Player 3: " + playerName3 + " + " + gs3);
        txtNr3UpdatedScore.setText("Updated score: " + updatedScore3);
        txtNr4Score.setText("Player 4: " + playerName4 + " + " + gs4);
        txtNr4UpdatedScore.setText("Updated score: " + updatedScore4);
    }
    //@Tjelle >Übergibt die Spielernamen und die aktualisierten Punkte an die Database klasse
    public void saveScore() throws SQLException {
        Database db = new Database();
        db.save(players, playerName1,playerName2,playerName3,playerName4,updatedScore1,updatedScore2,updatedScore3,updatedScore4);
        lblYourCode.setText("Your code is: " + db.getSaveID());
        btnSave.setDisable(true);
    }
    //@Tjelle >Lädt den Game-Screen und übergibt Namen und Punkte
    public void nextPlanet(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameScreen.fxml"));
        Parent root = loader.load();
        GameScreenController controller = loader.getController();
        controller.setScores(updatedScore1, updatedScore2, updatedScore3, updatedScore4);
        controller.setPlayers(players, playerName1, playerName2, playerName3, playerName4);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 480, 640));
        stage.show();
    }
    //@Tjelle >Wechselt zum Hauptmenü
    public void returnToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader.load(), 480, 640));
        stage.show();
    }
}
