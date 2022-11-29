import java.sql.*;

public class database {
 ResultSet result = null;           //Result der executeQuery
String pstatement = null;           //Prepared Statement
int zahl = 0;                       //Variable, damit in die Prepared statements eingesetzt werden kann
int ScoreID = 0;                    //ScoreID, um Spielernamen und Score zu verknüpfen
int saveID = 0;                     //Eine ID; die am ende dem User gegeben wird, um das gespeicherte Spiel fortzuführen
int[] Score = new int[4];           //Array für Spielerscore
String[] SNA = new String[4];       //Array für Spielernamen
int SA = 0;         //Spieleranzahl

    //String t2 = "SELECT count(*) FROM sqlite_master where type='table'"; // Test

    public void connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:PlanetGuessr.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection has been established.");


//Ausführen von Prepared Statement

            //String query = " SELECT * FROM sqlite_master where type='table'";
            PreparedStatement myStmt = conn.prepareStatement(pstatement);
            if(zahl == 1)
            {
                System.out.println(zahl);
                for(int i = 1; i < SA; i++) {    //Save score

                    myStmt.setInt(1, ScoreID);
                    myStmt.setInt(2, saveID);
                    myStmt.setString(3, SNA[i - 1]);
                    myStmt.setInt(4, Score[i - 1]);
                }
            }
            else if (zahl == 2)
            {
                System.out.println(zahl);
                myStmt.setInt(1, saveID);
                myStmt.setString(2, SNA[0]);
                myStmt.setString(3, SNA[1]);
                myStmt.setString(4, SNA[2]);
                myStmt.setString(5, SNA[3]);

            }
            else if (zahl == 3) //ScoreID
            {
                System.out.println(zahl);
            } else if (zahl == 4) {     //SaveID,
                System.out.println(zahl);
            }

            ResultSet myRs = myStmt.executeQuery();


            if (zahl ==1) {
                result = myRs;
            } else if (zahl == 2) {
                result = myRs;
            } else if (zahl == 3) { // ScoreID
                result = myRs;
                ScoreID = result.getInt(String.valueOf(myRs));

            } else if (zahl ==4){   //SaveID
                saveID = result.getInt(String.valueOf(myRs));
                result = myRs;
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



    public static void main(String[] args) {
        System.out.println("Startet");
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
    zahl =4;
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


    public void Save(int SA, String SN1, String SN2, String SN3, String SN4, int SC1, int SC2, int SC3, int SC4) throws SQLException {


       generateSaveID();
       getNewScoreID();
        createArray(SA, SN1, SN2, SN3, SN4, SC1, SC2, SC3,SC4);
        SA = SA;



//SA = Score Anzahl: Anzahl an übergebenen Scores
/*
        for(int i = 1; i < SA; i++){    //Save score

            String InsertScore = "INSERT INTO SCOREBOARD(?, ?, ?, ?)";
            PreparedStatement SIS = conn.prepareStatement(InsertScore);
            PreparedStatement.setInt(1, getNewScoreID());
            PreparedStatement.setInt(2, saveID);
            PreparedStatement.setString(3, SNA[i-1]);
            PreparedStatement.setInt(4, Score[i-1]);

            getPS2(InsertScore); //übergibt ps ohne eingaben
            zahl = 1;
       }*/
        String InsertScore = "INSERT INTO SCOREBOARD(?, ?, ?, ?)";
        getPS(InsertScore); //übergibt ps ohne eingaben
        zahl = 1;


// Zum speichern des Spiels
        String InsertSave = "INSERT INTO Saves(?, ?, ?, ?, ?)";
                               /*
                                           PreparedStatement SPP = conn.prepareStatement(InsertSave);
                                           SPP.setInt(1, saveID);
                                           SPP.setString(2, SNA[0]);
                                           SPP.setString(3, SNA[1]);
                                           SPP.setString(4, SNA[2]);
                                           SPP.setString(5, SNA[3]);
*/
        getPS(InsertSave);
        zahl = 2;



    }

    public void getNewScoreID() throws SQLException {
               String ScoreIDSQL = "SELECT MAX(ScoreID) from Scoreboard";

       // PreparedStatement ScoreSQLS = conn.PrepareStatement(ScoreIDSQL);
        zahl = 3;
        getPS(ScoreIDSQL);
        ResultSet e = getRS();

       // ScoreID1 = getInt(e) +1; //+1, um die nächst höhere Zahl zu bekommen
       // ScoreID = ScoreID1;
    }


}