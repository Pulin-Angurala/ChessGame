
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * The Database class is used to set up and connect an embedded database to the
 * program. This database is used to record game stats and game history over
 * time.
 */
public class Database {

    private static final String USER_NAME = "";
    private static final String PASSWORD = "";
    private String winner, staleMate;

    Statement statement = null;
    private static final String URL = "jdbc:derby:Data;create=true";
    Connection conn;

    /**
     * The constructor for this class is used to establish a connection with the
     * embedded database.
     */
    public Database() {
        conn = null;
        establishConnection();
    }

    /**
     * This method is used to establish a connection with the embedded database,
     * carrying out required SQL statements to instantiate required tables, rows
     * and columns.
     */
    public void establishConnection() {
        if (this.conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

                statement = conn.createStatement();

                if (!this.checkExistedTable("Game_Stats")) {
                    String newTable = "Game_Stats";
                    String sqlCreateTable = "CREATE TABLE " + newTable + "(Game_ID INT, GAME_AT DATE, "
                            + "GAME_WINNER VARCHAR(10), TOTAL_CHECKS INT," +
                            "TOTAL_KILLS INT, TOTAL_MOVES INT, GAME_LASTED INT)";

                    statement.executeUpdate(sqlCreateTable);
                    System.out.println("Created");
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public Connection getConnection() {
        return this.conn;
    }

    /**
     * This method is used to check for an existing table, in the case that the
     * program/game is started up for a first time and no data has been assigned
     * or organised, as well as to avoid the case that a database has been
     * established and is not override.
     *
     * @param name is passed the name of the table wanting to be queried.
     * @return true if the table exists, else false.
     */
    public boolean checkExistedTable(String name) {
        try {
            DatabaseMetaData dbmd = this.conn.getMetaData();
            String[] types = {"TABLE"};
            ResultSet rs = dbmd.getTables(null, null, null, types);
            Statement statement = this.conn.createStatement();
            while (rs.next()) {
                String table_name = rs.getString("TABLE_NAME");
                if (table_name.equalsIgnoreCase(name)) {
                    return true;

                }
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
        }
        return false;
    }

    /**
     * This method is used to update the table with Game Stats and information
     * each time a new game is played.
     *
     * @param gameID is passed a unique game ID for a game session
     * @param gameAt is passed the date and time for when session was carried
     * out
     * @param winner is passed a string to identify the winner of a game
     * @param staleMate is passed a string to indicate whether the game was won
     * by stalemate
     * @param totalChecks is passed the number of checks made during a game by
     * players
     * @param totalKills is passed the number of eliminations made by the
     * players during a game
     * @param totalMoves is passed the number of moved made during a game
     * @param gameLasted is passed the amount of time a session was carried out.
     */
    public void updateTable(int gameID, Date gameAt, String winner, int totalChecks, int totalKills,
            int totalMoves, int gameLasted) {

        System.out.println("INSERT INTO Game_Stats VALUES (" + gameID + ", "
                + gameAt + ", '" + winner + "', '" + staleMate + "', " + totalChecks + ", "
                + totalKills + ", " + totalMoves + ", " + gameLasted + ")");

        // edit this V
        try {
            statement = conn.createStatement();
            this.statement.executeUpdate("INSERT INTO Game_Stats VALUES (" + gameID + ", "
                    + gameAt + ", '" + winner + "', '" + staleMate + "', " + totalChecks + ", "
                    + totalKills + ", " + totalMoves + ", " + gameLasted + ")");

            ResultSet rs = statement.executeQuery("SELECT * FROM Game_Stats");

            System.out.println(rs.next());

            while (rs.next()) {
                System.out.println("hi");
                int gID = rs.getInt(1);
                Date gAt = rs.getDate(2);
                String gWinner = rs.getString(3);
                int gTotalChecks = rs.getInt(4);
                int gTotalKills = rs.getInt(5);
                int gTotalMoves = rs.getInt(6);
                int gLasted = rs.getInt(7);

                System.out.println(gID + ", " + gAt + ", " + gWinner + ", " +
                        gTotalChecks + ", " + gTotalKills + ", " + gTotalMoves + ", " + gLasted + ")");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
