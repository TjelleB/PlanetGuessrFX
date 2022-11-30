package com.example.planetguessrfx;

import javafx.beans.property.SimpleStringProperty;
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
    public Pane pBackground;
    public Rectangle rtInfos;
    public VBox vInfos;
    public Label lblPlanetName;
    public ToggleButton bttnHint1;
    public ToggleButton bttnHint2;
    public ToggleButton bttnHint3;
    public Button btnValues;
    public VBox vInputs;
    public HBox hRow1;
    public HBox hPlayer1;
    public TextField txtInput1;
    public ToggleButton bttnConfirm1;
    public HBox hPlayer2;
    public TextField txtInput2;
    public ToggleButton bttnConfirm2;
    public HBox hRow2;
    public HBox hPlayer3;
    public TextField txtInput3;
    public ToggleButton bttnConfirm3;
    public HBox hPlayer4;
    public TextField txtInput4;
    public ToggleButton bttnConfirm4;
    public Pane pSaveGame;
    public Button btnMenu;
    public Button btnSave;
    public Label lblYourCode;

    public String playerName1;
    public String playerName2;
    public String playerName3;
    public String playerName4;
    public int score1;
    public int score2;
    public int score3;
    public int score4;
    public Pane tblPane;
    public TableView tblView;
    public TableColumn tblColRes;
    public TableColumn tblColVal;
    public TableColumn tblColAmount;
    int gainedScore1;
    int gainedScore2;
    int gainedScore3;
    int gainedScore4;
    public ImageView ivPlanet;
    public TextArea txtBaseInfos;
    private final Math m = new Math();
    Image imgCurrentPlanet;
    int players;

    @FXML
    private void initialize() {
        lblPlanetName.setText(m.name.getName());
        this.addDataToTbl();
    }
    public void genNewPlanet(){
        m.calcFinalVal();
    }
    public void returnToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 480, 640);
        stage.setScene(scene);
        stage.show();
    }

    public void showResult(ActionEvent event)throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resultScreen.fxml"));
        Parent root = loader.load();
        ResultScreenController controller = loader.getController();
        controller.setPlayers(players, playerName1, playerName2, playerName3, playerName4);
        controller.setScore(score1, score2, score3, score4);
        controller.displayScore(gainedScore1, gainedScore2, gainedScore3, gainedScore4);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 480, 640);
        stage.setScene(scene);
        stage.show();
    }

    public void saveScore() throws SQLException {
        database db = new database();
        db.connect();
        db.save(players,playerName1,playerName2,playerName3,playerName4,score1,score2,score3,score4);
        lblYourCode.setText("Your code is: " + db.getSaveID());
        btnSave.setDisable(true);
    }

    public  void setScores(int s1, int s2, int s3, int s4){
        score1 = s1;
        score2 = s2;
        score3 = s3;
        score4 = s4;
    }

    public void checkConfirmation(ActionEvent event) throws IOException{
        if(bttnConfirm1.isSelected()&& players == 1){
            gainedScore1 =  m.calcPts(Integer.parseInt(txtInput1.getText()));
            showResult(event);
        } else if(players == 2 && bttnConfirm2.isSelected()&& bttnConfirm1.isSelected()){
            gainedScore1 =  m.calcPts(Integer.parseInt(txtInput1.getText()));
            gainedScore2 =  m.calcPts(Integer.parseInt(txtInput2.getText()));
            showResult(event);
        }else if( players == 3&& bttnConfirm3.isSelected()&&bttnConfirm2.isSelected()&& bttnConfirm1.isSelected()){
            gainedScore1 =  m.calcPts(Integer.parseInt(txtInput1.getText()));
            gainedScore2 =  m.calcPts(Integer.parseInt(txtInput2.getText()));
            gainedScore3 =  m.calcPts(Integer.parseInt(txtInput3.getText()));
            showResult(event);
        }else if(players == 4&&bttnConfirm4.isSelected()&& bttnConfirm3.isSelected()&&bttnConfirm2.isSelected()&& bttnConfirm1.isSelected()){
            gainedScore1 =  m.calcPts(Integer.parseInt(txtInput1.getText()));
            gainedScore2 =  m.calcPts(Integer.parseInt(txtInput2.getText()));
            gainedScore3 =  m.calcPts(Integer.parseInt(txtInput3.getText()));
            gainedScore4 =  m.calcPts(Integer.parseInt(txtInput4.getText()));
            showResult(event);
        }
    }

    public void setPlayers(int p, String p1, String p2, String p3, String p4){
        players = p;
        playerName1 = p1;
        txtInput1.setPromptText(playerName1 + "'s Guess");
        playerName2 = p2;
        txtInput2.setPromptText(playerName2 + "'s Guess");
        playerName3 = p3;
        txtInput3.setPromptText(playerName3 + "'s Guess");
        playerName4 = p4;
        txtInput4.setPromptText(playerName4 + "'s Guess");

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

    public void chgBackground() {

        switch (m.detImg()) {
            case 0 -> { // Stoneplanet
                imgCurrentPlanet = new Image("com/example/planetguessrfx/Pictures/Planet Imgs/Stone.gif");
                ivPlanet.setImage(imgCurrentPlanet);
            }
            case 1 -> { // Waterplanet
                imgCurrentPlanet = new Image("com/example/planetguessrfx/Pictures/Planet Imgs/Water.gif");
                ivPlanet.setImage(imgCurrentPlanet);
            }
            case 2 -> { // Gasplanet
                imgCurrentPlanet = new Image("com/example/planetguessrfx/Pictures/Planet Imgs/Gas.gif");
                ivPlanet.setImage(imgCurrentPlanet);
            }
            case 3 -> { // Iceplanet
                imgCurrentPlanet = new Image("com/example/planetguessrfx/Pictures/Planet Imgs/Ice.gif");
                ivPlanet.setImage(imgCurrentPlanet);
            }
            case 4 -> { // No Atmosphere
                imgCurrentPlanet = new Image("com/example/planetguessrfx/Pictures/Planet Imgs/No Atmos.gif");
                ivPlanet.setImage(imgCurrentPlanet);
            }
        }
    }
    public String createStringBH() {
        return "Basis Wert: 50.000.000" +
                "\nOberfläche: " + m.getBh1() +
                "\nStern: " + m.getBh2() +
                "\n";
    }
    public String createStringH1() {
        return "" + this.createStringBH() +
                "Tipp 1:\nAtmosphäre: " + m.getH11() +
                "\nWetter: " + m.getH12() +
                "\n";
    }
    public String createStringH2() {
        return "" + this.createStringH1() +
                "Tipp 2:\nBewohnbar: " + m.getH2() +
                "\n";
    }
    public String createStringH3() {
        return "" + this.createStringH2() +
                "Tipp 3:\nRessourcenmenge der Wertetabelle hinzugefügt!";
    }
    public void setBaseHints() {
        txtBaseInfos.setEditable(false);
        txtBaseInfos.setText(this.createStringBH());
        bttnHint1.setDisable(false);
        bttnHint2.setDisable(true);
        bttnHint3.setDisable(true);
    }
    public void setHint1() {
        txtBaseInfos.setText(this.createStringH1());
        bttnHint1.setDisable(true);
        bttnHint2.setDisable(false);
        System.out.println(m.value);
    }
    public void setHint2() {
        txtBaseInfos.setText(this.createStringH2());
        bttnHint2.setDisable(true);
        bttnHint3.setDisable(false);
    }
    public void setHint3() {
        tblColAmount.setVisible(true);
        txtBaseInfos.setText(this.createStringH3());
        bttnHint3.setDisable(true);
    }
    public void openTblRes() {
        tblPane.setVisible(!tblPane.isVisible());
    }
    public void addDataToTbl() {
        String[][] data = new String[m.res.getArrayLength()][3];
        String[] resources = {"Salz", "Kobalt", "Silber", "Gold", "Platin", "Kohlenstoff", "Natrium",
                                "Tritium", "Eisen", "Kupfer", "Amonium", "Uran", "Dioxid", "Phosphor",
                                "Dihydrogen"};
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
                if (x != null && x.length>2) {
                    return new SimpleStringProperty(x[2]);
                } else {
                    return new SimpleStringProperty("<no value>");
                }
            }
        });
        tblView.getItems().addAll(Arrays.asList(data));
    }
}
