import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * The View class renders and represents the contents of the Model class
 * to the user. This class is used to represent every change and game functionality
 * embedded in this program upon indication from the model class as part of the
 * MVC design pattern.
 */
public class View extends JFrame implements Observer {
    private ChessBoard chessBoardPanel;
    private StartMenu startMenu;
    private Player playerOne, playerTwo;
    private ChessPiece[][] tiles = new ChessPiece[8][8];
    private ChessPiece currentPiece = null;
    private JButton startButton;

    /**
     * The constructor for this class is used to initialise all the components at the start
     * of the GUI each time a new game or the application is started.
     */
    public View() {
        this.setSize(new Dimension(750, 750));
        chessBoardPanel = new ChessBoard();
        startMenu = new StartMenu();
        add(startMenu);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }

    /**
     * The StartMenus class is used to instantiate the menu screen that greets
     * the user upon start of the program. This class is used to simply allow users
     * to be given a structured application.
     */
    public class StartMenu extends JPanel{
        /**
         * The constructor for this class is used to set the layout and add
         * necessary components for program functionality both interactive and visual.
         * */
        public StartMenu(){
            setSize(new Dimension(750, 750));
            startButton = new JButton("Start Game");
            add(startButton, CENTER_ALIGNMENT);
        }
    }

    /**
     * The ChessBoard class is used to instantiate the chess board upon
     * which the game takes place. This class is used to set the layout and bounds for the
     * initial scene of a chess game.
     */
    public class ChessBoard extends JPanel {
        /**
         * The constructor for this class is used to set the layout and add
         * necessary components for program functionality both interactive and visual.
         * */
        public ChessBoard() {
            setLayout(new GridLayout(8, 8));
            super.setPreferredSize(new Dimension(600, 600));
        }
    }


    /**
     * The addActionListener method is used to reference a Listener object
     * from the controller class as part of the MVC design pattern. This method
     * is used to reference the dedicate listener in order to avoid code clashes
     * and complexities as defined by the MVC design pattern , to make the program
     * code more structured.
     * @param listener is passed a listener object to listen the actions of dedicated buttons
     */
    public void addActionListener(ActionListener listener) {
        startButton.addActionListener(listener);

        if (tiles != null) {
            for (int i = 0; i < tiles.length; i++) {
                for (int j = 0; j < tiles[0].length; j++) {
                    tiles[i][j].addActionListener(listener);
                }
            }
        }
    }


    public ChessBoard getChessBoardPanel() {
        return chessBoardPanel;
    }

    public void setChessBoardPanel(ChessBoard chessBoardPanel) {
        this.chessBoardPanel = chessBoardPanel;
    }

    public StartMenu getStartMenu() {
        return startMenu;
    }

    public void setStartMenu(StartMenu startMenu) {
        this.startMenu = startMenu;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public ChessPiece[][] getTiles() {
        return tiles;
    }

    public void setTiles(ChessPiece[][] tiles) {
        this.tiles = tiles;
    }

    public ChessPiece getCurrentPiece() {
        return currentPiece;
    }

    public void setCurrentPiece(ChessPiece currentPiece) {
        this.currentPiece = currentPiece;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public void setStartButton(JButton startButton) {
        this.startButton = startButton;
    }
}