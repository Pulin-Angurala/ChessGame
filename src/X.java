import java.io.IOException;

/**
 * The X class is a subclass of the ChessPiece class. The objects of the X class are
 * used to instantiate X chess pieces. As inherited from the parent class ChessPiece,
 * The objects of this class are defined by a piece position, a piece type, and a game map.
 * The X chess pieces are used to instantiate empty tiles on a chess board, they
 * are not a designated piece from the original chess game and are only used for tile
 * representation purposes of this program.
 * @author Pulin Angurala
 */
public class X extends ChessPiece {

    /**
     * The constructor for this class is inherited from the super class and takes in arguments
     * of a piece position, a piece type and a game map to which the piece is belonging to.
     * @param pieceType is passed a piece type from the enum PieceType class to specify the type
     *                  of the chess piece.
     * @param gameMap is passed a GameMap object.
     */
    public X(PieceType pieceType, ChessPiece[][] gameMap){
        super(pieceType, gameMap);
    }

    /**
     * No modifications have been made as the X pieces are idle and only for
     * representation purposes.
     * @return false, always.
     */
    @Override
    public boolean possibleMoves() {
        return false;
    }

    @Override
    public void appearance() {

    }
}
