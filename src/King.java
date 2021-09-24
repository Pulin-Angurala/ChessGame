import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * The King class is a subclass of the ChessPiece class. The objects of the King class are
 * used to instantiate King chess pieces.As inherited from the parent class ChessPiece,
 * The objects of this class are defined by a piece position, a piece type, and a game map.
 * @author Pulin Angurala
 */
public class King extends ChessPiece {

    /**
     * The constructor for this class is inherited from the super class and takes in arguments
     * of a piece position, a piece type and a game map to which the piece is belonging to.
     * @param piecePosition is passed a position located in the game map
     * @param pieceType is passed a piece type from the enum PieceType class to specify the type
     *                  of the chess piece.
     * @param gameMap is passed a GameMap object.
     */
    public King(PiecePosition piecePosition, PieceType pieceType, ChessPiece[][] gameMap)  {
        super(piecePosition, pieceType, gameMap);
    }

    /**
     * This method is an abstract method inherited from the super class and is overridden to
     * actively determine and highlight the possible moves a pawn chess piece may make, when
     * on the game map.
     * @return false, as a King cannot checkmate another King.
     */
    @Override
    public boolean possibleMoves() {
        for (int i = 0; i < getGameMap().length; i++){
            for (int j = 0; j < getGameMap()[0].length; j++) {
                    if (getGameMap()[i][j].equals(this) && getPieceType().equals(PieceType.KING)) {
                        if (j > 0) {
                            setAvailable(i, j - 1);
                        }
                        if (j < 7) {
                            setAvailable(i, j + 1);
                        }
                        if (i > 0) {
                            setAvailable(i - 1, j);
                            if (j > 0) {
                                setAvailable(i - 1, j - 1);
                            }
                            if (j < 7) {
                                setAvailable(i - 1, j + 1);
                            }
                        }
                        if (i < 7) {
                            setAvailable(i + 1, j);
                            if (j < 7) {
                                setAvailable(i + 1, j + 1);
                            }
                            if (j > 0) {
                                setAvailable(i + 1, j - 1);
                            }
                        }
                    }
                }
            }
//        }
        return false;
    }

    /**
     * This method is inherited from the super class and overriden to
     * specifically serve each sub class of a chess piece. The method
     * is used to access the image illustration for the specific type of
     * chess piece and is used to display an illustration of itself on the GUI.
     */
    @Override
    public void appearance() {
        String dir = System.getProperty("user.dir");
        if (isPlayerOne()){
            setIcon(new ImageIcon(dir+"/src/fancy/BK.gif"));
        }
        else{
            setIcon(new ImageIcon(dir+"/src/fancy/WK.gif"));
        }
    }


    /**
     * This method returns wanted chess Pieces from the game map. As the king
     * has a very specific possible moves spectrum this method is created to
     * retrieve those move locations and minimise code repetition.
     * @param a is passed a row index
     * @param b is passed a column index
     */
    public void setAvailable(int a, int b) {
        boolean samePlayer = true;

        for (int i = 0; i < getGameMap().length; i++) {
            for (int j = 0; j < getGameMap()[0].length; j++) {
                if (getGameMap()[i][j].equals(this) && getPieceType().equals(PieceType.KING)) {
                    if (!getGameMap()[i][j].isPlayerOne()){
                        samePlayer = false;
                    }
                    if (getGameMap()[a][b].getPieceType().equals(PieceType.X) ||
                            getGameMap()[a][b].isPlayerOne() != samePlayer){

                        getGameMap()[a][b].setBackground(Color.decode("#faf3c0"));
                        getGameMap()[a][b].setBorder(new LineBorder(Color.BLACK, 2));
                    }
                }
            }
        }
    }

}
