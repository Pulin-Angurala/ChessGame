import java.util.ArrayList;
/**
 * The Player class is used to represent a real life player in the chess game. The objects of this class
 * are used to instantiate a single player with a set of dedicated pieces based on the rules of
 * chess. The player class helps keep track of a players chess pieces in this game when being
 * moved, eliminated or checking the opposite player.
 */
public class Player {
    private int numChecksMade;
    private ArrayList<ChessPiece> chessPieces;
    private int row;

    /**
     * The constructor for this class is used to initialise a player's number status as well as
     * references the gameBoard that the player is to play on.
     * @param isPLayerOne initialised to true if player one, else false.
     * @param gameBoard passed a chess board map.
     */
    public Player(Boolean isPLayerOne, ChessPiece[][] gameBoard){
        chessPieces = new ArrayList<>();
        assignPieces(isPLayerOne, gameBoard);
    }

    public void assignPieces(Boolean isPLayerOne, ChessPiece[][] gameBoard){
        int pawnRow;
        
        if (isPLayerOne) {
            row = 0;
            pawnRow = 1;
        }
        else{
            row = 7;
            pawnRow = 6;
        }

        for (int i = 0; i < 8; i++){
            chessPieces.add(new Pawn(new PiecePosition(pawnRow, i), PieceType.PAWN, gameBoard));
        }

        chessPieces.add(new King(new PiecePosition(row, 4), PieceType.KING, gameBoard));
        chessPieces.add(new Queen(new PiecePosition(row, 3), PieceType.QUEEN, gameBoard));
        chessPieces.add(new Bishop(new PiecePosition(row, 2), PieceType.BISHOP, gameBoard));
        chessPieces.add(new Bishop(new PiecePosition(row, 5), PieceType.BISHOP, gameBoard));
        chessPieces.add(new Knight(new PiecePosition(row, 1), PieceType.KNIGHT, gameBoard));
        chessPieces.add(new Knight(new PiecePosition(row, 6), PieceType.KNIGHT, gameBoard));
        chessPieces.add(new Rook(new PiecePosition(row, 0), PieceType.ROOK, gameBoard));
        chessPieces.add(new Rook(new PiecePosition(row, 7), PieceType.ROOK, gameBoard));

        for (int i = 0; i <chessPieces.size(); i++){
            chessPieces.get(i).setPlayerOne(isPLayerOne);
        }
    }

    public ArrayList<ChessPiece> getChessPieces() {
        return chessPieces;
    }
    
    public int getRow(){
        return this.row;
    }
    
    public void setRow(int row){
        this.row = row;
    }

    /**
     * This method is used to add to the number of checks made by a player.
     * To keep track of the 3 checks rule.
     */
    public void addCheck(){
        numChecksMade++;
    }

    public int getNumChecksMade() {
        return numChecksMade;
    }
}
