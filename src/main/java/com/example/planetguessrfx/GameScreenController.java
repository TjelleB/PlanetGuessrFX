package com.example.planetguessrfx;
import      javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
public class GameScreenController {
    //FXML Objects
    public Pane pBackground;
    public Rectangle rtInfos;
    public VBox vInfos;
    public Label lblPlanetName;
    public ToggleButton btnHint1;
    public ToggleButton btnHint2;
    public ToggleButton btnHint3;
    public Button btnValues;
    public VBox vInputs;
    public HBox hRow1;
    public HBox hPlayer1;
    public TextField txtInput1;
    public ToggleButton btnConfirm1;
    public HBox hPlayer2;
    public TextField txtInput2;
    public ToggleButton btnConfirm2;
    public HBox hRow2;
    public HBox hPlayer3;
    public TextField txtInput3;
    public ToggleButton btnConfirm3;
    public HBox hPlayer4;
    public TextField txtInput4;
    public ToggleButton btnConfirm4;
    public Pane pSaveGame;
    public Button btnMenu;
    public Button btnSave;
    public Label lblYourCode;
    public Pane tblPane;
    public TableView tblView;
    public TableColumn tblColRes;
    public TableColumn tblColVal;
    public TableColumn tblColAmount;
    public ImageView ivPlanet;
    public TextArea txtBaseInfos;
    public String playerName1;
    public String playerName2;
    public String playerName3;
    public String playerName4;
    public int score1;
    public int score2;
    public int score3;
    public int score4;
    public Button btnReturnToMain;
    private int gainedScore1;
    private int gainedScore2;
    private int gainedScore3;
    private int gainedScore4;
    private final Math m = new Math();
    private Image imgCurrentPlanet;
    private int players;
    private double redMP1;
    private double redMP2;
    private double redMP3;
    private double redMP4;
    private boolean isLockedP1 = false;
    private boolean isLockedP2 = false;
    private boolean isLockedP3 = false;
    private boolean isLockedP4 = false;

    @FXML
    private void initialize() {genNewPlanet();}

    // generiert einen neuen Planeten und holt sich den namen und wert aus der math klasse, legt den hintergrund passend zum Planetentypen fest
    public void genNewPlanet() {
        this.addDataToTbl();
        m.calcFinalVal();
        lblPlanetName.setText(m.name.getName());
        chgBackground();
        setBaseHints();
    }

    //Lädt das Main menu
    public void returnToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 480, 640);
        stage.setScene(scene);
        stage.show();
    }

    //Lädt den result screeen und übergibt spieler anzahl, namen und punkte
    public void showResult(ActionEvent event)throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resultScreen.fxml"));
        Parent root = loader.load();
        ResultScreenController controller = loader.getController();
        controller.setPlayers(players, playerName1, playerName2, playerName3, playerName4);
        controller.displayScore(score1, score2, score3, score4, gainedScore1, gainedScore2, gainedScore3, gainedScore4);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 480, 640);
        stage.setScene(scene);
        stage.show();
    }

    //Zeigt Speicheroption an
    public void displaySaveOption(){
        pSaveGame.setVisible(true);
    }

    //Übergibt daten an die database klasse
    public void saveScore() throws SQLException {
        database db = new database();
        db.save(playerName1,playerName2,playerName3,playerName4,score1,score2,score3,score4);
        lblYourCode.setText("Your code is: " + db.getSaveID());
        btnSave.setDisable(true);
    }

    //Setzt den aktuellen score aller Spieler
    public  void setScores(int s1, int s2, int s3, int s4){
        score1 = s1;
        score2 = s2;
        score3 = s3;
        score4 = s4;
    }

    public void checkP1() {
        txtInput1.setEditable(!btnConfirm1.isSelected()); //Das Textfield kann nicht bearbeitet werden, wenn der ToggleButton gedrückt ist
        btnConfirm1.setDisable(btnConfirm1.isSelected()); //Der ToggleButton wird deaktiviert, wenn er gedrückt ist
        if (btnConfirm1.isDisabled()) { //Wenn der Spieler seine Schätzung festgelegt hat
            if (btnHint3.isSelected()) redMP1 = 0.65; //Wird der niedrigste Multiplikator ausgewählt
            else if (btnHint2.isSelected()) redMP1 = 0.70;
            else if (btnHint1.isSelected()) redMP1 = 0.85;
            else redMP1 = 1;
            isLockedP1 = true; //If-Bedingung wird gesperrt
        }
    }
    public void checkP2() {
        txtInput2.setEditable(!btnConfirm2.isSelected());
        btnConfirm2.setDisable(btnConfirm2.isSelected());
        if (btnConfirm2.isDisabled()) {
            if (btnHint3.isSelected()) redMP2 = 0.65;
            else if (btnHint2.isSelected()) redMP2 = 0.70;
            else if (btnHint1.isSelected()) redMP2 = 0.85;
            else redMP2 = 1;
            isLockedP2 = true;
        }
    }
    public void checkP3() {
        txtInput3.setEditable(!btnConfirm3.isSelected());
        btnConfirm3.setDisable(btnConfirm3.isSelected());
        if (btnConfirm3.isDisabled()) {
            if (btnHint3.isSelected()) redMP3 = 0.65;
            else if (btnHint2.isSelected()) redMP3 = 0.70;
            else if (btnHint1.isSelected()) redMP3 = 0.85;
            else redMP3 = 1;
            isLockedP3 = true;
        }
    }
    public void checkP4() {
        txtInput4.setEditable(!btnConfirm4.isSelected());
        btnConfirm4.setDisable(btnConfirm4.isSelected());
        if (btnConfirm4.isDisabled()) {
            if (btnHint3.isSelected()) redMP4 = 0.65;
            else if (btnHint2.isSelected()) redMP4 = 0.70;
            else if (btnHint1.isSelected()) redMP4 = 0.85;
            else redMP4 = 1;
            isLockedP4 = true;
        }
    }
    public void checkConfirmation(ActionEvent event) throws IOException{
        if(!isLockedP1) checkP1();
        if(!isLockedP2) checkP2();
        if(!isLockedP3) checkP3();
        if(!isLockedP4) checkP4();
        if(btnConfirm1.isSelected()&& players == 1){
            gainedScore1 =  m.calcPts(redMP1, Integer.parseInt(txtInput1.getText()));
            showResult(event);
        } else if(players == 2 && btnConfirm2.isSelected()&& btnConfirm1.isSelected()){
            gainedScore1 =  m.calcPts(redMP1, Integer.parseInt(txtInput1.getText()));
            gainedScore2 =  m.calcPts(redMP2, Integer.parseInt(txtInput2.getText()));
            showResult(event);
        }else if( players == 3&& btnConfirm3.isSelected()&&btnConfirm2.isSelected()&& btnConfirm1.isSelected()){
            gainedScore1 =  m.calcPts(redMP1, Integer.parseInt(txtInput1.getText()));
            gainedScore2 =  m.calcPts(redMP2, Integer.parseInt(txtInput2.getText()));
            gainedScore3 =  m.calcPts(redMP3, Integer.parseInt(txtInput3.getText()));
            showResult(event);
        }else if(players == 4&&btnConfirm4.isSelected()&& btnConfirm3.isSelected()&&btnConfirm2.isSelected()&& btnConfirm1.isSelected()){
            gainedScore1 =  m.calcPts(redMP1, Integer.parseInt(txtInput1.getText()));
            gainedScore2 =  m.calcPts(redMP2, Integer.parseInt(txtInput2.getText()));
            gainedScore3 =  m.calcPts(redMP3, Integer.parseInt(txtInput3.getText()));
            gainedScore4 =  m.calcPts(redMP4, Integer.parseInt(txtInput4.getText()));
            showResult(event);
        }
    }
    //Zeigt den Spielernamen in der inputbox an und setzt sie auf sichtbar, jenachdem wie viele da sind
    public void setPlayers(int p, String p1, String p2, String p3, String p4){
        players = p;
        txtInput1.setPromptText(p1 + "'s Guess");
        txtInput2.setPromptText(p2 + "'s Guess");
        txtInput3.setPromptText(p3 + "'s Guess");
        txtInput4.setPromptText(p4 + "'s Guess");

        switch (players){
            case 1 -> {
                hPlayer2.setVisible(false);
                hPlayer3.setVisible(false);
                hPlayer4.setVisible(false);
            }
            case 2 -> {
                hPlayer3.setVisible(false);
                hPlayer4.setVisible(false);
            }
            case 3 ->
                hPlayer4.setVisible(false);
        }
    }

    //Setzt den hintergrund zum passenden planeten
    public void chgBackground() {
      ivPlanet.setImage(PlanetGuessr.getPictures(m.detImg()));
    }

    //createStringBH() = @Ivo
    public String createStringBH() { //String für Basistipps
        return "Basis Wert: 50.000.000" +
                "\nOberfläche: " + m.getBh1() +
                "\nStern: " + m.getBh2() +
                "\n";
    }

    //createStringH1() = @Ivo
    public String createStringH1() { //String für Tipp 1
        return this.createStringBH() +
                "Tipp 1:\nAtmosphäre: " + m.getH11() +
                "\nWetter: " + m.getH12() +
                "\n";
    }

    //createStringH2() = @Ivo
    public String createStringH2() { //String für Tipp 2
        return this.createStringH1() +
                "Tipp 2:\nBewohnbar: " + m.getH2() +
                "\n";
    }

    //createStringH3() = @Ivo
    public String createStringH3() { //String für Tipp 3
        return this.createStringH2() +
                "Tipp 3:\nRessourcenmenge der Wertetabelle hinzugefügt!";
    }

    //setBaseHints() = @Ivo
    public void setBaseHints() { //Basistipps werden in TextArea geschrieben, Button für Tipp 1 wird verfügbar
        txtBaseInfos.setEditable(false);
        txtBaseInfos.setText(this.createStringBH());
        btnHint1.setDisable(false);
        btnHint2.setDisable(true);
        btnHint3.setDisable(true);
    }

    //setHint1() = @Ivo
    public void setHint1() { //Tipp 1 wird in TextArea geschrieben, Button für Tipp 2 wird verfügbar
        txtBaseInfos.setText(this.createStringH1());
        btnHint1.setDisable(true);
        btnHint2.setDisable(false);
        System.out.println(m.value);
    }

    //setHint2() = @Ivo
    public void setHint2() { //Tipp 2 wird in TextArea geschrieben, Button für Tipp 3 wird verfügbar
        txtBaseInfos.setText(this.createStringH2());
        btnHint2.setDisable(true);
        btnHint3.setDisable(false);
    }

    //setHint3() = @Ivo
    public void setHint3() { //Tipp 3 werden in TextArea geschrieben, zusätzliche Tabellenspalte wird angezeigt, Tipp 3 Button wird deaktiviert
        tblColAmount.setVisible(true);
        txtBaseInfos.setText(this.createStringH3());
        btnHint3.setDisable(true);
    }

    //openTblRes() = @Ivo
    public void openTblRes() { //Ändert die Visibility der Tabelle zum derzeitigen Gegenteil
        tblPane.setVisible(!tblPane.isVisible());
    }

    //addDataToTbl() = @Ivo
    public void addDataToTbl() {
        String[][] data = new String[m.res.getArrayLength()][3]; //2D-Array mit 3 Spalten und Rows = Anzahl Ressourcen
        String[] resources = {"Salz", "Kobalt", "Silber", "Gold", "Platin", "Kohlenstoff", "Natrium",
                                "Tritium", "Eisen", "Kupfer", "Ammonium", "Uran", "Dioxid", "Phosphor",
                                "Dihydrogen"};
        //2D-Array wird mit den Ressourcen und ihren Werten/Mengen befüllt
        for (int i = 0; i < m.res.getArrayLength(); i++) {
            data[i][0] = "" + resources[i];
            data[i][1] = "" + m.res.getValues(i);
            data[i][2] = "" + m.res.getAmounts(i);
        }
        tblColRes.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                String[] x = p.getValue();
                if (x != null && x.length>0) {
                    return new SimpleStringProperty(x[0]);
                } else {
                    return new SimpleStringProperty("<no name>");
                }
            }
        });
        tblColVal.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                String[] x = p.getValue();
                if (x != null && x.length>1) {
                    return new SimpleStringProperty(x[1]);
                } else {
                    return new SimpleStringProperty("<no value>");
                }
            }
        });
        tblColAmount.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                String[] x = p.getValue();
                for (int i = 0; i < x.length; i++) {
                    System.out.println(i + " " + x[i]);
                    System.out.println(Arrays.toString(p.getValue()));
                }
                if (x.length>2) {
                    return new SimpleStringProperty(x[2]);
                } else {
                    return new SimpleStringProperty("<no value>");
                }
            }
        });
        tblView.getItems().addAll(Arrays.asList(data));
    }
}
