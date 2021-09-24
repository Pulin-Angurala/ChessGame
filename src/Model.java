import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.Observable;
import java.util.Random;

/**
 * The Model class contains and represents data and rules that govern
 * access to and updates of the chess game being played. This class
 * constantly updates the data according to set rules and user interactions,
 * for the View class to represent to the user in the GUI as part of the MVC design pattern.
 */
public class Model extends Observable{
    private Database db;
    private final ChessPiece[][] tiles = new ChessPiece[8][8];
    private Player playerOne, playerTwo;
    private ChessPiece currentPiece;
    private int gameID, totalChecks, totalKills, totalMoves, gameLasted, start, end;
    private String winner, staleMate;
    private Date gameAt;
    private boolean pass;

    /**
     * The constructor for this class initialises the game match as a new match each
     * time with the default settings.
     */
    public Model(){
        currentPiece = null;
//        db = new Database();
        gameAt = new Date();

        playerOne = new Player(true, tiles);
        playerTwo = new Player(false, tiles);
        pass = true;
        initialiseMap();
    }

    /**
     * This method is used to initialise and mould the Chess Piece button array into a game map/visual
     * representation of a chess board where the board is a grid of 8 x 8 tiles with each
     * chess piece being placed at it’s designated initial location, row and column wise.
     */
    public void initialiseMap() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                for (int a = 0; a < playerOne.getChessPieces().size(); a++) {
                    if (playerOne.getChessPieces().get(a).getPiecePosition().getRow() == i &&
                            playerOne.getChessPieces().get(a).getPiecePosition().getCol() == j) {
                        tiles[i][j] = playerOne.getChessPieces().get(a);
                        break;
                    } else if (playerTwo.getChessPieces().get(a).getPiecePosition().getRow() == i &&
                            playerTwo.getChessPieces().get(a).getPiecePosition().getCol() == j) {
                        tiles[i][j] = playerTwo.getChessPieces().get(a);
                        break;
                    } else {
                        tiles[i][j] = new X(PieceType.X, tiles);
                    }
                }

                if ((i + j) % 2 == 0) {
                    tiles[i][j].setBackground(Color.decode("#3c777d"));
//                    tiles[i][j].setBackground(Color.decode("#654321"));

                } else {
                    tiles[i][j].setBackground(Color.WHITE);
                }

                tiles[i][j].appearance();
                tiles[i][j].setOpaque(true);
                tiles[i][j].setBorder(BorderFactory.createEmptyBorder());
            }
        }
    }

    /**
     * This method is used to clean the game map after the possible moves for a chess piece have
     * been displayed/highlighted or a check/checkmate representation has been displayed on the map. As
     * otherwise the the map would be filled with previous and inaccurate representations as
     * the game progresses.
     */
    public void reset(){
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j].getBackground().equals(Color.decode("#faf3c0"))) {

                    if ((i + j) % 2 == 0) {
                        tiles[i][j].setBackground(Color.decode("#3c777d"));
//                        tiles[i][j].setBackground(Color.decode("#654321"));
                        tiles[i][j].setBorder(new LineBorder(Color.BLACK, 2));

                    } else {
                        tiles[i][j].setBackground(Color.WHITE);
                    }
                    tiles[i][j].setBorder(null);
                }
            }
        }

        this.setChanged();
        this.notifyObservers();
    }

    /**
     * This method is used to pass information and important stats recorded during the current game ,
     * to be stored in the database as a part of games history and high scores.
     */
//    public void calculateGameStats() {
////        gameID = new Random().nextInt(1000);
////        db.updateTable(gameID, gameAt, winner, totalChecks, totalKills,
////            totalMoves, gameLasted);
//    }


    public Database getDb() {
        return db;
    }

    public ChessPiece[][] getTiles() {
        return tiles;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public ChessPiece getCurrentPiece() {
        return currentPiece;
    }

    public int getGameID() {
        return gameID;
    }

    public int getTotalChecks() {
        return totalChecks;
    }

    public int getTotalKills() {
        return totalKills;
    }

    public int getTotalMoves() {
        return totalMoves;
    }

    public int getGameLasted() {
        return gameLasted;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public String getWinner() {
        return winner;
    }

    public String getStaleMate() {
        return staleMate;
    }

    public Date getGameAt() {
        return gameAt;
    }

    public boolean isPass() {
        return pass;
    }

    public void setDb(Database db) {
        this.db = db;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public void setCurrentPiece(ChessPiece currentPiece) {
        this.currentPiece = currentPiece;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public void setTotalChecks(int totalChecks) {
        this.totalChecks = totalChecks;
    }

    public void setTotalKills(int totalKills) {
        this.totalKills = totalKills;
    }

    public void setTotalMoves(int totalMoves) {
        this.totalMoves = totalMoves;
    }

    public void setGameLasted(int gameLasted) {
        this.gameLasted = gameLasted;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setStaleMate(String staleMate) {
        this.staleMate = staleMate;
    }

    public void setGameAt(Date gameAt) {
        this.gameAt = gameAt;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }
}