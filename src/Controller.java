import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Controller class is used to translate the user's actions
 * with the GUI and View CLass
 */
public class Controller implements ActionListener, WindowListener {
    public View view;
    public Model model;

    /**
     * The constructor for this classed is passed a View and Model object to
     * link the 3 classes , so that the MVC design pattern is followed where
     * the controller is manipulating the model and the view is being updated by
     * the model.
     * @param view is passed a View object to be updated and used for user interactions
     * @param model is passed a Model object to update data and game functionality.
     */
    public Controller(View view, Model model){
        this.view = view;
        this.model = model;
        this.view.setPlayerOne(model.getPlayerOne());
        this.view.setPlayerTwo(model.getPlayerTwo());
        this.view.setTiles(model.getTiles());
        this.view.setCurrentPiece(model.getCurrentPiece());
        this.view.addActionListener(this);
        this.view.addWindowListener(this);

        for (int i = 0; i < view.getTiles().length; i++){
            for (int j = 0; j < view.getTiles()[0].length; j++){
                view.getChessBoardPanel().add(view.getTiles()[i][j]);
            }
        }

        view.revalidate();
        view.repaint();
    }

    /**
     * This method is used to listen to user actions through the GUI
     * and carry out specific tasks based upon the user's interaction
     * with the program GUI. This method is used to embed chess game
     * functionality such as moves, eliminations, etc.
     * @param e is used to the sense the type of action made by the user.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == view.getStartButton()){
            view.remove(view.getStartMenu());
            view.add(view.getChessBoardPanel());
            view.revalidate();
            view.repaint();;
            this.model.setStart((int) System.currentTimeMillis());
        }

        if (model.getCurrentPiece() == null) {
            for (int i = 0; i < model.getTiles().length; i++) {
                for (int j = 0; j < model.getTiles()[0].length; j++) {
                    if (source == model.getTiles()[i][j] &&
                            model.getTiles()[i][j].isPlayerOne() == model.isPass() &&
                            !model.getTiles()[i][j].getPieceType().equals(PieceType.X)){
                        model.reset();
                        model.setCurrentPiece(model.getTiles()[i][j]);
                        if (model.getTiles()[i][j].possibleMoves()){
                            check(model.getTiles()[i][j].isPlayerOne());
                            model.setTotalChecks(model.getTotalChecks()+1);
                        }
                    }
                }
            }
        }
        else {
            for (int i = 0; i < model.getTiles().length; i++) {
                for (int j = 0; j < model.getTiles()[0].length; j++) {
                    if (source == model.getTiles()[i][j]
                            && model.getTiles()[i][j] != model.getCurrentPiece()) {
                        if (model.getTiles()[i][j].getBackground().equals(Color.decode("#faf3c0"))) {
                            remap(model.getCurrentPiece(), i, j);
                            model.setCurrentPiece(null);
                        }
                        else if (model.getTiles()[i][j].isPlayerOne() == model.isPass()) {
                            model.reset();
                            model.setCurrentPiece(model.getTiles()[i][j]);
                            if (model.getCurrentPiece().possibleMoves()) {
                                check(model.getTiles()[i][j].isPlayerOne());
                                model.setTotalChecks(model.getTotalChecks()+1);
                            }
                        }
                        else{
                            model.reset();
                            model.setCurrentPiece(null);
                        }
                    }
                }
            }
        }
    }

    /**
     * This method is used to move a piece chosen by a player, to a location requested by the player.
     * If the location is valid the piece is moved.
     * @param chessPiece is passed the chess piece requested to move by the player.
     * @row is passed the row upon which the chess piece is to be moved to.
     * @col is passed the column upon which the chess piece is to be moved to.
     */
    public void remap(ChessPiece chessPiece, int row, int col) {
        Player aPlayer = model.getPlayerOne();
        
        ChessPiece aPiece = model.getTiles()[row][col];
        
        if (aPiece.isPlayerOne() != chessPiece.isPlayerOne() && !aPiece.getPieceType().equals(PieceType.X)){
            aPiece.setPiecePosition(new PiecePosition(-1, -1));
            model.setTotalKills(model.getTotalKills()+1);
        }
        

        if (!chessPiece.isPlayerOne()){
            aPlayer = model.getPlayerTwo();
        }

        for (int i = 0; i < aPlayer.getChessPieces().size(); i++){
            if (aPlayer.getChessPieces().get(i).getPiecePosition().equals(chessPiece.getPiecePosition())){
                aPlayer.getChessPieces().get(i).setPiecePosition(new PiecePosition(row, col));
                break;
            }
        }

        for (int i = 0; i < view.getTiles().length; i++){
            for (int j = 0; j < view.getTiles()[0].length; j++){
                view.getChessBoardPanel().remove(view.getTiles()[i][j]);
            }
        }

        model.initialiseMap();

        for (int i = 0; i < view.getTiles().length; i++){
            for (int j = 0; j < view.getTiles()[0].length; j++){
                view.getChessBoardPanel().add(view.getTiles()[i][j]);
                view.addActionListener(this);
            }
        }

        model.setPass(!model.isPass());
        model.setTotalMoves(model.getTotalMoves()+1);
        view.revalidate();
        view.repaint();
    }


    /**
     * This method is used actively check for any possible checks made by any player,
     * and indicate a representation of the player in danger.
     * @param playerOne is passed a boolean to identify the specific player in check.
     */
    public void check(boolean playerOne){
        for (int i = 0; i < model.getTiles().length; i++){
            for (int j = 0; j < model.getTiles()[0].length; j++){
                if (model.getTiles()[i][j].isPlayerOne() != playerOne
                        && model.getTiles()[i][j].getPieceType().equals(PieceType.KING)){
                    model.getTiles()[i][j].setBackground(Color.RED);
                    break;
                }
            }
        }

        view.revalidate();
        view.repaint();
    }

    @Override
    public void windowClosing(WindowEvent we) {
        this.model.setEnd((int) System.currentTimeMillis());
        this.model.setGameLasted((int)TimeUnit.MILLISECONDS.toMinutes(this.model.getEnd() -
                this.model.getStart()));
//            model.calculateGameStats();
    }
    
    @Override
    public void windowOpened(WindowEvent we) {}

    @Override
    public void windowClosed(WindowEvent we) {}

    @Override
    public void windowIconified(WindowEvent we) {}

    @Override
    public void windowDeiconified(WindowEvent we) {}

    @Override
    public void windowActivated(WindowEvent we) {}

    @Override
    public void windowDeactivated(WindowEvent we) {}
}