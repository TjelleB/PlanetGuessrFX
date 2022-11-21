package com.example.planetguessrfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class GameScreenController {
    public Pane pBackground;
    public Rectangle rtInfos;
    public VBox vInfos;
    public Label lblPlanetName;
    public ToggleButton bttnHint1;
    public ToggleButton bttnHint2;
    public ToggleButton bttnHint3;
    public Button btValues;
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

    public String playerName1;
    public String playerName2;
    public String playerName3;
    public String playerName4;
    int player1guess;
    int player2Guess;
    int player3Guess;
    int player4Guess;
    int planetValue;
    public ImageView ivPlanet;
    public TextArea txtBaseInfos;
    private Math m = new Math();
    Image imgCurrentPlanet;
    String planetName;
    int players;

    @FXML
    private void initialize(){
        planetName = "Test Planet";
        lblPlanetName.setText(planetName);
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
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 480, 640);
        stage.setScene(scene);
        stage.show();
    }

    public void checkConfirmation(ActionEvent event) throws IOException{
        if(bttnConfirm1.isSelected()&& players == 1){
            showResult(event);
        } else if(players == 2 && bttnConfirm2.isSelected()&& bttnConfirm1.isSelected()){
            showResult(event);
        }else if( players == 3&& bttnConfirm3.isSelected()&&bttnConfirm2.isSelected()&& bttnConfirm1.isSelected()){
            showResult(event);
        }else if(players == 4&&bttnConfirm4.isSelected()&& bttnConfirm3.isSelected()&&bttnConfirm2.isSelected()&& bttnConfirm1.isSelected()){
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
        String s =   "Basis Wert: 50.000.000" +
                "\nOberfläche: " + m.getBh1() +
                "\nStern: " + m.getBh2() +
                "\n";
        return s;
    }
    public String createStringH1() {
        String s = "" + this.createStringBH() +
                "Tipp 1:\nAtmosphäre: " + m.getH11() +
                "\nWetter: " + m.getH12() +
                "\n";
        return s;
    }
    public String createStringH2() {
        String s = "" + this.createStringH1() +
                "Tipp 2:\nBewohnbar: " + m.getH2() +
                "\n";
        return s;
    }
    public String createStringH3() {
        String s = "" + this.createStringH2() +
                "Tipp 3:\nRessourcenanzahl der Wertetabelle hinzugefügt!";
        return s;
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
    }
    public void setHint2() {
        txtBaseInfos.setText(this.createStringH2());
        bttnHint2.setDisable(true);
        bttnHint3.setDisable(false);
    }
    public void setHint3() {
        txtBaseInfos.setText(this.createStringH3());
        bttnHint3.setDisable(true);
    }
}
