import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * The Rook class is a subclass of the ChessPiece class. The objects of the Rook class are
 * used to instantiate Rook chess pieces. As inherited from the parent class ChessPiece,
 * The objects of this class are defined by a piece position, a piece type, and a game map.
 * @author Pulin Angurala
 */
public class Rook extends ChessPiece {

    /**
     * The constructor for this class is inherited from the super class and takes in arguments
     * of a piece position, a piece type and a game map to which the piece is belonging to.
     * @param piecePosition is passed a position located in the game map
     * @param pieceType is passed a piece type from the enum PieceType class to specify the type
     *                  of the chess piece.
     * @param gameMap is passed a GameMap object.
     */
    public Rook(PiecePosition piecePosition, PieceType pieceType, ChessPiece[][] gameMap)  {
        super(piecePosition, pieceType, gameMap);
    }

    /**
     * This method is an abstract method inherited from the super class and is overridden to
     * actively determine and highlight the possible moves a pawn chess piece may make, when
     * on the game map.
     * @return true, if the king is within the possible moves spectrum of this chess piece
     */
    @Override
    public boolean possibleMoves(){
        for (int i = 0; i < getGameMap().length; i++){
            for (int j = 0; j < getGameMap()[0].length; j++){


                    if (getGameMap()[i][j].equals(this) && getPieceType().equals(PieceType.ROOK)) {
                    range(i, false, true);
                    range(i, true, true);
                    range(j , true, false);
                    range(j , false, false);

                    if (range(i, false, true) || range(i, true, true) ||
                            range(j , true, false) || range(j , false, false)){
                        return true;
                    }
                }
            }
        }
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
            setIcon(new ImageIcon(dir+"/src/fancy/BR.gif"));
        }
        else{
            setIcon(new ImageIcon(dir+"/src/fancy/WR.gif"));
        }
    }

    /**
     * This method is used to return the possible moves range spectrum of the
     * Rook class. This method applies a universal take upon the Rook's pattern
     * based movement to minimize code repetition.
     * @param rowCol is passed a row or column tp set bounds for
     * @param greaterThan is passed a boolean value to specify method pathways.
     * @param row is passed a boolean variable to specific method pathways.
     * @return true if the King is within the possible moves spectrum.
     */
    public boolean range(int rowCol, boolean greaterThan, boolean row){
        boolean samePlayer = true;
        boolean bool = false;
        int a = 1;
        int x = 1;
        int y = 0;

        if(!row){
            x = 0;
            y = 1;
        }

        for (int i = 0; i < getGameMap().length; i++){
            for (int j = 0; j < getGameMap()[0].length; j++) {
                if (getGameMap()[i][j].equals(this) && getPieceType().equals(PieceType.ROOK)) {
                    if (!getGameMap()[i][j].isPlayerOne()) {
                        samePlayer = false;
                    }


                    if (greaterThan) {
                        if (rowCol > 0) {
                            while (getGameMap()[i - x][j - y].getPieceType().equals(PieceType.X) ||
                                    getGameMap()[i - x][j - y].isPlayerOne() != samePlayer) {
                                if (!row){
                                    y = a;
                                    x = 0;
                                }
                                else{
                                    x = a;
                                    y = 0;
                                }

                                if (rowCol - a < 0) {
                                    break;
                                }
                                if (!getGameMap()[i - x][j - y].getPieceType().equals(PieceType.X)) {
                                    if (getGameMap()[i - x][j - y].isPlayerOne() != samePlayer) {
                                        setAvailable(getGameMap()[i-x][j-y]);
//                                        getGameMap()[i - x][j - y].setInRange(true);
                                        if (getGameMap()[i - x][j - y].getPieceType().equals(PieceType.KING)){
                                            bool = true;
                                        }
                                    }
                                    break;
                                }
                                setAvailable(getGameMap()[i-x][j-y]);
//                                getGameMap()[i - x][j - y].setInRange(true);
                                a++;
                            }
                        }
                    }
                    else {
                        if (rowCol < 7) {
                            while (getGameMap()[i + x][j + y].getPieceType().equals(PieceType.X)
                                    || getGameMap()[i + x][j + y].isPlayerOne() != samePlayer) {
                                if (!row){
                                    y = a;
                                    x = 0;
                                }
                                else{
                                    x = a;
                                    y = 0;
                                }

                                if (rowCol + a > 7) {
                                    break;
                                }
                                if (!getGameMap()[i + x][j + y].getPieceType().equals(PieceType.X)) {
                                    if (getGameMap()[i + x][j + y].isPlayerOne() != samePlayer) {
                                        setAvailable(getGameMap()[i+x][j+y]);
                                        if (getGameMap()[i + x][j + y].getPieceType().equals(PieceType.KING)){
                                            bool = true;
                                        }
                                    }
                                    break;
                                }
                                setAvailable(getGameMap()[i+x][j+y]);
                                a++;
                            }
                        }
                    }
                }
            }
        }
        return bool;
    }

    /**
     * This method is used to indicate the certain tiles that are available for a user to
     * move a specific piece by indicating a highlighted change on the GUI.
     * @param chessPiece is passed the chess piece/tile that is available to be moved to.
     */
    public void setAvailable(ChessPiece chessPiece){
        chessPiece.setBackground(Color.decode("#faf3c0"));
        chessPiece.setBorder(new LineBorder(Color.BLACK, 2));
    }
}
