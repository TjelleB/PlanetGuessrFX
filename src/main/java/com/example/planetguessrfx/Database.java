package com.example.planetguessrfx;

import java.sql.*;

public class Database { // Paul >
    ResultSet result = null;           //Result der executeQuery
String pstatement = null;           //Prepared Statement
int number = 0;                       //Variable, damit in die Prepared statements eingesetzt werden kann
int scoreID = 0;                    //scoreID, um Spielernamen und score zu verknüpfen
int saveID = 0;                     //Eine ID; die am Ende dem User gegeben wird, um das gespeicherte Spiel fortzuführen
int[] score = new int[4];           //Array für Spieler score
String[] playerNameArray = new String[4];       //Array für Spielernamen
    boolean b = true;                           //Bool, damit die for-schleife nicht zweimal ausgeführt wird
    int sa = 0;                                 //Spieleranzahl
    int id;                                     //SpielID
    public void connect() {                     //Paul > Funktion zu Verbindung mit der Datenbank
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:src/main/resources/com/example/planetguessrfx/GalaxyDB.db";   //Link in der Local directory
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            //Ausführen von Prepared Statement
            PreparedStatement myStmt = conn.prepareStatement(pstatement);
            if(number == 1)
            {
                for(int i = 1; i <= 4; i++) {    //Save score
                    myStmt.setInt(1, scoreID);
                    myStmt.setInt(2, saveID);
                    myStmt.setString(3, playerNameArray[i - 1]);
                    myStmt.setInt(4, score[i - 1]);
                    myStmt.execute();
                }
                b = false;                              //damit nicht nochmal ausgeführt wird
            }
            else if (number == 2)
            {
                myStmt.setInt(1, saveID);
                myStmt.setString(2, playerNameArray[0]);
                myStmt.setString(3, playerNameArray[1]);
                myStmt.setString(4, playerNameArray[2]);
                myStmt.setString(5, playerNameArray[3]);
                myStmt.setInt(6, sa);
                myStmt.execute();
                b = false;

            } else if (number == 5) {
                myStmt.setInt(1, id);
            } else if (number == 6) {
                myStmt.setInt(1, id);
            } else if (number == 7) {
                myStmt.setInt(1, id);
            }
            if (b) {
                result = myStmt.executeQuery();
            }
            if (number == 3) { // scoreID
                scoreID = result.getInt("MAX(ScoreID)") + 1;
            } else if (number ==4){   //SaveID
                saveID = result.getInt("MAX(SaveID)") + 1;
            } else if (number == 5) {
                sa = result.getInt("SpielerAnzahl");
            } else if (number == 6) {
                //Abfrage der Spielernamen
                for (int j = 0; j <4; j++) {
                    result.next();
                    playerNameArray[j] = result.getString("SpielerID");
                }
            } else if (number == 7) {
                //Abfrage der Spieler scores
                result.next();
                for (int j = 0; j < 4; j++) {
                    score[j] = result.getInt("Score");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    b = true;
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void getPS(String t) {  //Paul > String in Prepared Statement
        pstatement = t;
        connect();
    }

    public void generateSaveID() {      //Paul > Generiert die SaveID
         String SaveIDSQL = "SELECT MAX(SaveID) from Saves";
         number =4;
         getPS(SaveIDSQL);
    }
    //Paul > Füllt die Arrays mit Namen und Scores
    public void createArray(String SN1, String SN2, String SN3, String SN4, int SC1, int SC2, int SC3, int SC4){
        playerNameArray[0] = SN1;
        playerNameArray[1] = SN2;
        playerNameArray[2] = SN3;
        playerNameArray[3] = SN4;//Array für die Spielernamen
        score[0] = SC1;
        score[1] = SC2;
        score[2] = SC3;
        score[3] = SC4;
        //Array für den score
    }
    //Paul > zum Speichern eines Spielstands
    public void save(int SAu, String SN1, String SN2, String SN3, String SN4, int SC1, int SC2, int SC3, int SC4) {
        sa = SAu;
        generateSaveID();
        getNewScoreID();
        createArray(SN1, SN2, SN3, SN4, SC1, SC2, SC3,SC4);

        String InsertScore = "INSERT INTO SCOREBOARD VALUES (?, ?, ?, ?)";
        number = 1;
        getPS(InsertScore); //übergibt ps, ohne eingaben


// Zum Speichern des Spiels
        String InsertSave = "INSERT INTO Saves VALUES (?, ?, ?, ?, ?, ?)";
        number = 2;
        getPS(InsertSave);

    }

    public int getSaveID(){                        // Paul >
        return saveID;
    }
    public void getNewScoreID() {  // Paul > Generiert eine neue ScoreID
               String ScoreIDSQL = "SELECT MAX(scoreID) from Scoreboard";
        number = 3;
        getPS(ScoreIDSQL);
    }


    public void loadGame(int IDn){  //Paul > Lädt einen vorher gespeicherten Spielstand
        id = IDn; //Schreibt die übergebene SpielID in eine globale Variable rein
        String spielerAnzahl = "Select SpielerAnzahl FROM Saves where SaveID = ?";     //Für die Spieleranzahl
        number = 5;
        getPS(spielerAnzahl);
        //Player name
        String loadPlayerName = "Select SpielerID FROM Scoreboard where SaveID = ?"; //in createArray() einfügen
        number = 6;
        getPS(loadPlayerName);
        //PlayerScore
        String loadPlayerScore = "Select Score FROM scoreboard where saveID = ?";
        number = 7;
        getPS(loadPlayerScore);
        //Zurückgegeben werden SpielerNamen und Scores, sowie Spieleranzahl

    }
    public String[] loadPlayerNameGame() {
        return playerNameArray;
    }
    public int[] loadPlayerScore() {
        return score;
    }
    public int loadSpielerAnzahl() {
        return sa;
    }
}
