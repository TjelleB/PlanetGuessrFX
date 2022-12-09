package com.example.planetguessrfx;

import java.sql.*;

public class database { ResultSet result = null;           //Result der executeQuery
String pstatement = null;           //Prepared Statement
int number = 0;                       //Variable, damit in die Prepared statements eingesetzt werden kann
int ScoreID = 0;                    //ScoreID, um Spielernamen und Score zu verknüpfen
int saveID = 0;                     //Eine ID; die am ende dem User gegeben wird, um das gespeicherte Spiel fortzuführen
int[] Score = new int[4];           //Array für Spielerscore
String[] playerNameArray = new String[4];       //Array für Spielernamen
int players = 0;         //Spieleranzahl

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
            if(number == 1)
            {
                System.out.println(number);
                for(int i = 1; i < players; i++) {    //Save score
                    myStmt.setInt(1, ScoreID);
                    myStmt.setInt(2, saveID);
                    myStmt.setString(3, playerNameArray[i - 1]);
                    myStmt.setInt(4, Score[i - 1]);
                }
            }
            else if (number == 2)
            {
                System.out.println(number);
                myStmt.setInt(1, saveID);
                myStmt.setString(2, playerNameArray[0]);
                myStmt.setString(3, playerNameArray[1]);
                myStmt.setString(4, playerNameArray[2]);
                myStmt.setString(5, playerNameArray[3]);
            }
            else if (number == 3) //ScoreID
            {
                System.out.println(number);
            } else if (number == 4) {     //SaveID,
                System.out.println(number);
            }
            result = myStmt.executeQuery();
            if (number ==1) {

            } else if (number == 2) {

            } else if (number == 3) { // ScoreID
                ScoreID = result.getInt(String.valueOf(result));
            } else if (number ==4){   //SaveID
                saveID = result.getInt(String.valueOf(result));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public ResultSet getRS(){
       return result;
    }

    private void getPS(String t) {
        pstatement = t;
        connect();
    }

    public void generateSaveID() throws SQLException {
         String SaveIDSQL = "SELECT MAX(SaveID) from Saves";
         number =4;
         getPS(SaveIDSQL);
    }

    public void createArray(int SA, String SN1, String SN2, String SN3, String SN4, int SC1, int SC2, int SC3, int SC4){

        playerNameArray[0] = SN1;
        playerNameArray[1] = SN2;
        playerNameArray[2] = SN3;
        playerNameArray[3] = SN4;//Array für die Spielernamen
        Score[0] = SC1;
        Score[1] = SC2;
        Score[2] = SC3;
        Score[3] = SC4;
        //Array für den Score
    }

    public void save(int SA, String SN1, String SN2, String SN3, String SN4, int SC1, int SC2, int SC3, int SC4) throws SQLException {

        generateSaveID();
        getNewScoreID();
        createArray(SA, SN1, SN2, SN3, SN4, SC1, SC2, SC3,SC4);

        String InsertScore = "INSERT INTO SCOREBOARD(?, ?, ?, ?)";
        getPS(InsertScore); //übergibt ps ohne eingaben
        number = 1;

// Zum speichern des Spiels
        String InsertSave = "INSERT INTO Saves(?, ?, ?, ?, ?)";

        getPS(InsertSave);
        number = 2;
    }

    public int getSaveID(){
        return saveID;
    }
    public void getNewScoreID() throws SQLException {
               String ScoreIDSQL = "SELECT MAX(ScoreID) from Scoreboard";

        number = 3;
        getPS(ScoreIDSQL);
    }
}