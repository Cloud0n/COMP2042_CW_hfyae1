
package Main;

import Game.Ball;
import Game.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class DebugConsole extends JDialog implements WindowListener{

    private static final String TITLE = "Debug Console";


    private JFrame owner;
    private DebugPanel debugPanel;
    private GameBoard gameBoard;
    private Wall wall;


    /**
     * defines debug panel and border layout for it
     * @param owner game frame object
     * @param wall wall
     * @param gameBoard main game
     */
    public DebugConsole(JFrame owner,Wall wall,GameBoard gameBoard){

        this.wall = wall;
        this.owner = owner;
        this.gameBoard = gameBoard;
        initialize();

        debugPanel = new DebugPanel(wall);
        this.add(debugPanel,BorderLayout.CENTER);


        this.pack();
    }

    /**
     * initialises the debug console
     */
    private void initialize(){
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.setFocusable(true);
    }


    /**
     * sets location of console
     */
    private void setLocation(){
        int x = ((owner.getWidth() - this.getWidth()) / 2) + owner.getX();
        int y = ((owner.getHeight() - this.getHeight()) / 2) + owner.getY();
        this.setLocation(x,y);
    }


    /**
     * @param windowEvent opens window
     */
    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    /**
     * @param windowEvent closing action of window
     */
    @Override
    public void windowClosing(WindowEvent windowEvent) {
        gameBoard.repaint();
    }

    /**
     * @param windowEvent closes window
     */
    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    /**
     * @param windowEvent puts icon
     */
    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    /**
     * @param windowEvent removes icon
     */
    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    /**
     * @param windowEvent window events that can be configured
     */
    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
        Ball b = wall.ball;
        debugPanel.setValues(b.getSpeedX(),b.getSpeedY());
    }

    /**
     * @param windowEvent window is closed
     */
    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
