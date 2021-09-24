/**
 * The PiecePosition class is used to define and store the position of a chess piece.
 * @author Pulin Angurala
 */
public class PiecePosition {
    private int row;
    private int col;

    /**
     * The constructor for this class takes in arguments of a number for a row and a
     * character for a column to which the chess piece belongs to, and stores this
     * information for each specific and individual chess piece.
     * @param row is passed a number to reference the row the chess piece is located in.
     * @param col is passed a character to reference the column the chess piece is located in.
     */
    public PiecePosition(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String toString(){
        char[] columns = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

        return columns[getCol()]+""+(getRow()+1);
    }
}
