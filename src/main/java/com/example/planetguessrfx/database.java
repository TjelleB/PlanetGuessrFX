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

            //String query = " SELECT * FROM sqlite_master where type='table'";
            PreparedStatement myStmt = conn.prepareStatement(pstatement);
            if(number == 0){
               // "SELECT count(*) FROM sqlite_master WHERE type = 'table'""
                System.out.println(number);
            }

            else if(number == 1)
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

            ResultSet myRs = myStmt.executeQuery();

            int rows_affected = myStmt.executeUpdate();

            if(number ==0){

            }

            else if (number ==1) {  //Spiechern ins Scoreboard (InsertInto)

                System.out.println(rows_affected);

            } else if (number == 2) {   //Speichern des Spiels in Saves (InsertInto)
                System.out.println(rows_affected);

            } else if (number == 3) { // ScoreID

                ScoreID = result.getInt(String.valueOf(result));

            } else if (number ==4){   //SaveID
                saveID = result.getInt(String.valueOf(result));

            }
            conn.close();
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

    private String getPS(String t) {
        pstatement = t;
        connect();
return null;
    }

    public void generateSaveID() throws SQLException {
         String SaveIDSQL = "SELECT MAX(SaveID) from Saves";
    number =4;
        getPS(SaveIDSQL);
       ResultSet ret = getRS();
       // saveID = getInt(ret) +1; //+1, um die nächst höhere Zahl zu bekommen
        //return saveID;
        //Wird gebraucht in Save (Saves[SaveID])
    }


    public void createArray(int SA, String SN1, String SN2, String SN3, String SN4, int SC1, int SC2, int SC3, int SC4){


        String[] SNA={SN1, SN2, SN3, SN4}; //Array für die Spielernamen
        int[] Score = {SC1, SC2, SC3, SC4}; //Array für den Score

    }


    public void save(int SA, String SN1, String SN2, String SN3, String SN4, int SC1, int SC2, int SC3, int SC4) throws SQLException {


       generateSaveID();
       getNewScoreID();
        createArray(SA, SN1, SN2, SN3, SN4, SC1, SC2, SC3,SC4);




//SA = Score Anzahl: Anzahl an übergebenen Scores

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
        ResultSet e = getRS();
    }


}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
//MySQL

 /*


import java.sql.*; //JDBC-API
import java.sql.DriverManager;

public class database {
    //Attribute
    Connection conn;       // Repräsentiert die DB-Verbindung
    PreparedStatement pst; // Statement repräsentiert SQL Statement
    ResultSet rs;          // Ergebnismenge, die vom DB-Server zurückgeliefert wird

    int number = 0;                       //Variable, damit in die Prepared statements eingesetzt werden kann
    int ScoreID = 0;                    //ScoreID, um Spielernamen und Score zu verknüpfen
    int saveID = 0;                     //Eine ID; die am ende dem User gegeben wird, um das gespeicherte Spiel fortzuführen
    int[] Score = new int[4];           //Array für Spielerscore
    String[] playerNameArray = new String[4];       //Array für Spielernamen
    int players = 0;         //Spieleranzahl

    String pstatement = null;

    ResultSet result = null;

    public void connect() throws Exception {
        //Verbindung zur Datenbank aufbauen
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/galaxydb","root","");
        System.out.println("Connection established");


        //Prepared statements
        //this.pst = conn.prepareStatement("SELECT * FROM autor;");
        PreparedStatement myStmt = conn.prepareStatement(pstatement);

       if(number == 0){
          // pstatement = "SELECT count(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'galaxyDB'";

            System.out.println(number);
        }

        else if(number == 1)
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

        rs = myStmt.executeQuery();
        int rows_affected = myStmt.executeUpdate();

        if(number ==0){

        }

        else if (number ==1) {  //Spiechern ins Scoreboard (InsertInto)

            System.out.println(rows_affected);

        } else if (number == 2) {   //Speichern des Spiels in Saves (InsertInto)
            System.out.println(rows_affected);

        } else if (number == 3) { // ScoreID

            ScoreID = result.getInt(String.valueOf(result));

        } else if (number ==4){   //SaveID
            saveID = result.getInt(String.valueOf(result));

        }

        conn.close();
    }

/*
    public static void main(String[] args) throws Exception {
        database jdbc = new database();
        jdbc.connect();
    }

*/
    //------------------------------

/*
    public ResultSet getRS(){
        return result;
    }

    private String getPS(String t) throws Exception {
        pstatement = t;
        connect();
        return null;
    }


    public void generateSaveID() throws Exception {
        String SaveIDSQL = "SELECT MAX(SaveID) from Saves";
        number =4;
        getPS(SaveIDSQL);
        ResultSet ret = getRS();
        // saveID = getInt(ret) +1; //+1, um die nächst höhere Zahl zu bekommen
        //return saveID;
        //Wird gebraucht in Save (Saves[SaveID])
    }


    public void createArray(int SA, String SN1, String SN2, String SN3, String SN4, int SC1, int SC2, int SC3, int SC4){


        String[] SNA={SN1, SN2, SN3, SN4}; //Array für die Spielernamen
        int[] Score = {SC1, SC2, SC3, SC4}; //Array für den Score

    }


    public void save(int SA, String SN1, String SN2, String SN3, String SN4, int SC1, int SC2, int SC3, int SC4) throws Exception {


        generateSaveID();
        getNewScoreID();
        createArray(SA, SN1, SN2, SN3, SN4, SC1, SC2, SC3,SC4);




//SA = Score Anzahl: Anzahl an übergebenen Scores

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
    public void getNewScoreID() throws Exception {
        String ScoreIDSQL = "SELECT MAX(ScoreID) from Scoreboard";
        number = 3;
        getPS(ScoreIDSQL);
        ResultSet e = getRS();
    }


}

*/