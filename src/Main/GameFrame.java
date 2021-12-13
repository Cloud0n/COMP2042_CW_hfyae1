
package Main;

import GUI.HomeMenu;
import GUI.InfoPage;
import HighScore.HighScorePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;



public class GameFrame extends JFrame implements WindowFocusListener {

    private Image icon;

    private static final String DEF_TITLE = "Brick Destroy";

    private GameBoard gameBoard;
    private HomeMenu homeMenu;
    private InfoPage infoPage;
    private HighScorePage highScorePage;

    private boolean gaming;

    /**
     * sets all the menus and pages
     */
    public GameFrame(){
        super();

        gaming = false;

        this.setLayout(new BorderLayout());

        gameBoard = new GameBoard(this);

        homeMenu = new HomeMenu(this,new Dimension(1200,621));

        infoPage = new InfoPage(this,new Dimension(1200,621));

        highScorePage = new HighScorePage(this,new Dimension(1200,621));

        this.add(homeMenu,BorderLayout.CENTER);

        this.setUndecorated(true);


    }


    /**
     * initialises exit
     */
    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
    }

    /**
     * enables game to open
     */
    public void enableGameBoard(){
        this.dispose();
        this.remove(homeMenu);
        this.add(gameBoard,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        this.addWindowFocusListener(this);

    }

    /**
     * enables info to open
     */
    public void enableInfoPage(){
        this.dispose();
        this.remove(homeMenu);
        this.add(infoPage,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        this.addWindowFocusListener(this);

    }

    public void enableHighScore(){
        this.dispose();
        this.remove(homeMenu);
        this.add(highScorePage,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        this.addWindowFocusListener(this);

    }

    /**
     * enables home menu to open
     */
    public void enableHomeMenu(){
        this.dispose();
        this.remove(infoPage);
        this.remove(highScorePage);
        this.add(homeMenu,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        this.addWindowFocusListener(this);

    }

    /**
     * locates position and sets location of window
     */
    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }


    /**
     * if window is in focus, the game will run
     * @param windowEvent check if window is in focus
     */
    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {

        gaming = true;
    }

    /**
     * if window isnt in focus game will be paused until its in focus again
     * @param windowEvent check if wondow isnt in focus
     */
    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(gaming)
            gameBoard.onLostFocus();

    }
}
