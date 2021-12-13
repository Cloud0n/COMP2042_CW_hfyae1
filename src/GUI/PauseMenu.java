package GUI;

import Main.GameBoard;

import java.awt.*;
import java.awt.font.FontRenderContext;


public class PauseMenu {

    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0,255,0);

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;
    private int strLen;

    private Font menuFont;

    private Main.GameBoard GameBoard;


    /**
     * sess font for pause menu
     * @param Gameboard sets font for pause menu
     */
    public PauseMenu(GameBoard Gameboard){
        this.GameBoard=Gameboard;
        menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);

    }

    /**
     * creates a continue button that excecutes "continueButtonRect"
     */
    public Rectangle ContinueButton(){
        return continueButtonRect;

    }

    /**
     * creates an exit button that excecutes "exitButtonRect"
     */
    public Rectangle ExitButton(){
        return exitButtonRect;

    }

    /**
     * creates a restart button that excecutes "restartButtonRect"
     */
    public Rectangle RestartButton(){
        return restartButtonRect;

    }

    /**
     * @param g2d draws menu mechanics
     */
    public void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    /**
     * draws and sets colour for pause menu
     * @param g2d draws pause menu
     */
    private void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    /**
     * draws the buttons and things inside the pause menu
     * @param g2d draws the buttons and things inside the pause menu
     */
    private void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();


        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (GameBoard.getWidth() - strLen) / 2;
        int y = GameBoard.getHeight() / 10;

        g2d.drawString(PAUSE,x,y);

        x = this.GameBoard.getWidth() / 8;
        y = this.GameBoard.getHeight() / 4;


        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = menuFont.getStringBounds(CONTINUE,frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(CONTINUE,x,y);

        y *= 2;

        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(RESTART,x,y);

        y *= 3.0/2;

        if(exitButtonRect == null){
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        g2d.drawString(EXIT,x,y);



        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }
}
