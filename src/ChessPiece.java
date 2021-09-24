import javax.swing.*;

/**
 * The abstract ChessPiece class is used to generalise the blueprint for all pieces used in the program.
 * The abstract class is used as a parent class for  all the chess pieces classes where each class has
 * Specific functionality dependent on the role of piece, but is based on the blue print created by this
 * class. Therefore, each chess Piece class consists of a piece type, piece position, a game map and a
 * playerOne , inRange and inCheck status.
 * @author Pulin Angurala
 */
public abstract class ChessPiece extends JButton{
    private PieceType pieceType;
    private PiecePosition piecePosition;
    private boolean playerOne;
    private ChessPiece[][] gameMap;


    /**
     * The  constructor for this class takes in an argument of a piece position, piece type and a game map
     * to initialise a chess piece.
     * @param piecePosition is passed piece position to store for each chess piece
     * @param pieceType is passed a pieceType enum to define the the type of chess piece
     */
    public ChessPiece(PiecePosition piecePosition, PieceType pieceType, ChessPiece[][] gameMap)  {
        this.piecePosition = piecePosition;
        this.pieceType = pieceType;
        this.gameMap = gameMap;
        setPlayerOne(true);
    }

    public ChessPiece(PieceType pieceType, ChessPiece[][] gameMap)  {
        this.pieceType = pieceType;
        this.gameMap = gameMap;
        setPlayerOne(true);
    }


    public PieceType getPieceType() {
        return pieceType;
    }

    public void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public void setPiecePosition(PiecePosition piecePosition) {
        this.piecePosition = piecePosition;
    }

    public PiecePosition getPiecePosition() {
        return piecePosition;
    }

    public boolean isPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(boolean playerOne) {
        this.playerOne = playerOne;
    }

    public ChessPiece[][] getGameMap() {
        return gameMap;
    }

    /**
     * This method is used to determine and highlight the possible moves a chess piece can
     * make, on to the game map. This method is defined as an abstract method as each chessPiece
     * based on chess piece type will have a specific spectrum/range of possible moves.
     * Hence, this method is overridden and used in each sub class of the chess piece
     * class to define the spectrum of movements for each type of piece. furthermore this
     * method is also used to indicate when the opposition King is within the range spectrum of
     * the chess piece.
     * @return true if the king is within the possible moves spectrum.
     */
    public abstract boolean possibleMoves();

    /**
     * This method is inherited from the super class and overriden to
     * specifically serve each sub class of a chess piece. The method
     * is used to access the image illustration for the specific type of
     * chess piece and is used to display an illustration of itself on the GUI.
     */
    public abstract void appearance();
}


