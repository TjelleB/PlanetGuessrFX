package com.example.planetguessrfx;

import java.sql.*;

public class database { ResultSet result = null;           //Result der executeQuery
String pstatement = null;           //Prepared Statement
int number = 0;                       //Variable, damit in die Prepared statements eingesetzt werden kann
int scoreID = 0;                    //scoreID, um Spielernamen und score zu verknüpfen
int saveID = 0;                     //Eine ID; die am Ende dem User gegeben wird, um das gespeicherte Spiel fortzuführen
int[] score = new int[4];           //Array für Spieler score
String[] playerNameArray = new String[4];       //Array für Spielernamen
    boolean b = true;
    int SA = 0;
    int ID;

    //String t2 = "SELECT count(*) FROM sqlite_master where type='table'"; // Test

    public void connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:src/main/resources/com/example/planetguessrfx/GalaxyDB.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection has been established.");

//Ausführen von Prepared Statement
            PreparedStatement myStmt = conn.prepareStatement(pstatement);
            System.out.println("Save after set Statement");
            if(number == 1)
            {
                System.out.println(number);
                for(int i = 1; i <= 4; i++) {    //Save score
                    myStmt.setInt(1, scoreID);
                    myStmt.setInt(2, saveID);
                    myStmt.setString(3, playerNameArray[i - 1]);
                    myStmt.setInt(4, score[i - 1]);

                    myStmt.execute();
                }
                b = false;
            }
            else if (number == 2)
            {
                System.out.println(number);
                myStmt.setInt(1, saveID);
                myStmt.setString(2, playerNameArray[0]);
                myStmt.setString(3, playerNameArray[1]);
                myStmt.setString(4, playerNameArray[2]);
                myStmt.setString(5, playerNameArray[3]);
                myStmt.setInt(6, SA);
                myStmt.execute();
                b = false;
            }
            else if (number == 3) //scoreID
            {
                System.out.println("scoreID");
            } else if (number == 4) {     //SaveID,
                System.out.println("SaveID");
            } else if (number == 5) {
                myStmt.setInt(1, ID);
                System.out.println("getSpieleranzahl");
            } else if (number == 6) {
                System.out.println(number);

                    myStmt.setInt(1, ID);
                }
                //TODO
            else if (number == 7) {
                System.out.println(number);

                myStmt.setInt(1, ID);

            }


            System.out.println(myStmt);
            if (b) {
                result = myStmt.executeQuery();
            }

            if (number ==1) {

            } else if (number == 2) {

            } else if (number == 3) { // scoreID
                scoreID = result.getInt("MAX(ScoreID)") + 1;
                System.out.println(scoreID);
            } else if (number ==4){   //SaveID
                saveID = result.getInt("MAX(SaveID)") + 1;
                System.out.println(saveID);
            } else if (number == 5) {
                SA = result.getInt("SpielerAnzahl");
                System.out.println(SA);
            } else if (number == 6) {
                //Abfrage der Spielernamen
                for (int j = 0; j <=4; j++)
                {
                    playerNameArray[j-1] = result.getString(String.valueOf(result.next()));
                }
            } else if (number == 7) {
                //Abfrage der Spieler scores
                for (int j = 0; j <=4; j++)
                {
                    score[j-1] = result.getInt(String.valueOf(result.next()));
                }

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    b = true;
                    conn.close();
                    System.out.println("Conn closed");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void getPS(String t) {
        pstatement = t;
        connect();
    }

    public void generateSaveID() throws SQLException {
         String SaveIDSQL = "SELECT MAX(SaveID) from Saves";
         System.out.println("generateSaveID");
         number =4;
         getPS(SaveIDSQL);
    }

    public void createArray(String SN1, String SN2, String SN3, String SN4, int SC1, int SC2, int SC3, int SC4){
        System.out.println("createArray");
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

    public void save(int SAu, String SN1, String SN2, String SN3, String SN4, int SC1, int SC2, int SC3, int SC4) throws SQLException {
        System.out.println("Save before SaveID");
        SA = SAu;
        generateSaveID();
        System.out.println("Save after SaveID, before NewScoreID");
        getNewScoreID();
        System.out.println("Save after NewScoreID");
        createArray(SN1, SN2, SN3, SN4, SC1, SC2, SC3,SC4);

        String InsertScore = "INSERT INTO SCOREBOARD VALUES (?, ?, ?, ?)";
        number = 1;
        getPS(InsertScore); //übergibt ps, ohne eingaben


// Zum Speichern des Spiels
        String InsertSave = "INSERT INTO Saves VALUES (?, ?, ?, ?, ?, ?)";
        number = 2;
        getPS(InsertSave);

    }

    public int getSaveID(){
        return saveID;
    }
    public void getNewScoreID() throws SQLException {
               String ScoreIDSQL = "SELECT MAX(scoreID) from Scoreboard";

        number = 3;
        getPS(ScoreIDSQL);
    }


    public void loadGame(int IDn){
        ID = IDn; //Schreibt die übergebene SpielID in eine Globale Valirable rein
        String spielerAnzahl = "Select Spieleranzahl where SaveID = ?";     //Für die Spieleranzahl
        number = 5;
        getPS(spielerAnzahl);
//Playername
        String loadPlayerName = "Select SpielerID FROM Scoreboard where SaveID = ?"; //in createArray() einfügen
        number = 6;
        getPS(loadPlayerName);
        //PlayerScore
        String loadPlayerScore = "Select Score FROM scoreboard where saveID = ?";
        number = 7;
        getPS(loadPlayerScore);
        //Zurückgegeben werden SpielerNamen und Scores, sowie Spieleranzahl

    }
}