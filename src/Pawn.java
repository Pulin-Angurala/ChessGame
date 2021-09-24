import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Pawn extends ChessPiece{
    /**
     * The  constructor for this class takes in an argument of a piece position, piece type and a game map
     * to initialise a chess piece.
     *
     * @param piecePosition is passed piece position to store for each chess piece
     * @param pieceType     is passed a pieceType enum to define the the type of chess piece
     */
    public Pawn(PiecePosition piecePosition, PieceType pieceType, ChessPiece[][] gameMap)  {
        super(piecePosition, pieceType, gameMap);
    }


    @Override
    public boolean possibleMoves() {
        boolean opposite = true;
        int x  = 1;
        int startingRow = 0;

        if (isPlayerOne()){
            x = 1;
            startingRow = 1;
        }
        else{
            x = -1;
            startingRow = 6;
            opposite = false;
        }


        for (int i = 0; i < getGameMap().length; i++){
            for (int j = 0; j < getGameMap()[0].length; j++){

                if ((getGameMap()[i][j].equals(this)) && getPieceType().equals(PieceType.PAWN)){
                    System.out.println(getGameMap()[i][j].getPiecePosition());
                    if(i > 0 && i < 7 ) {
                        if (getGameMap()[i + (x)][j].getPieceType().equals(PieceType.X)
                                && getGameMap()[i + (2*x)][j].getPieceType().equals(PieceType.X) &&
                                getPiecePosition().getRow() == startingRow) {

                            setAvailable(getGameMap()[i + (x)][j]);
                            setAvailable(getGameMap()[i + (2*x)][j]);
                        }
                        if (getGameMap()[i + (x)][j].getPieceType().equals(PieceType.X)) {
                            setAvailable(getGameMap()[i + (x)][j]);
                        }
                        if (j < 7 && !getGameMap()[i + (x)][j + 1].getPieceType().equals(PieceType.X) &&
                                getGameMap()[i + (x)][j + 1].isPlayerOne() != opposite) {
                            setAvailable(getGameMap()[i + (x)][j + 1]);
                        }
                        if ((j > 0) && !getGameMap()[i + (x)][j - 1].getPieceType().equals(PieceType.X) &&
                                getGameMap()[i + (x)][j - 1].isPlayerOne() != opposite) {
                            setAvailable(getGameMap()[i + (x)][j - 1]);
                        }
                    }
//
//                    if (isPlayerOne()){
//                        promote(getGameMap()[i][j], 7);
//                    }
//                    else{
//                        promote(getGameMap()[i][j], 0);
//                        getGameMap()[i][j].setPlayerOne(false);
//                    }
                }
//                else if ((getGameMap()[i][j].equals(this) && getGameMap()[i][j].getPieceType().equals(PieceType.X))){
//                    getGameMap()[i][j] = new X(getGameMap()[i][j].getPiecePosition(), PieceType.X, getGameMap());
//                }
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
            setIcon(new ImageIcon(dir+"/src/fancy/BP.gif"));
        }
        else{
            setIcon(new ImageIcon(dir+"/src/fancy/WP.gif"));
        }
    }


    /**
     * This method is used to indicate the certain tiles that are available for a user to
     * move a specific piece by indicating a highlighted change on the GUI.
     * @param chessPiece is passed the chess piece/tile that is available to be moved to.
     */
    public void setAvailable(ChessPiece chessPiece){
       chessPiece.setBackground(Color.decode("#faf3c0"));
       chessPiece.setBorder(new LineBorder(Color.BLACK, 2, true));
    }
}
