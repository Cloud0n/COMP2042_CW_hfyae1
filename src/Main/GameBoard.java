package Main;

import Game.Ball;
import Game.Brick;
import Game.Player;
import Game.Wall;
import HighScore.HighScore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GameBoard extends JComponent implements KeyListener,MouseListener,MouseMotionListener {

    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0,255,0);


    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final Color BG_COLOR = Color.WHITE;

    private Timer gameTimer;

    private Wall wall;

    private String message;

    private boolean showPauseMenu;


    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;


    private DebugConsole debugConsole;

    private PauseMenu PauseMenu;


    /**
     * creates gameboard
     * @param owner game frame object
     */
    public GameBoard(JFrame owner){

        super();
        PauseMenu=new PauseMenu(this);
        showPauseMenu = false;

        HighScore hs= new HighScore();


        this.initialize();
        message = "";
        wall = new Wall(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,3,6/2,new Point(300,430));

        debugConsole = new DebugConsole(owner,wall,this);
        //initialize the first level
        wall.nextLevel();

        gameTimer = new Timer(10,e ->{
            wall.move();
            wall.findImpacts();
            message = String.format("Bricks: %d Balls %d Score %d",wall.getBrickCount(),wall.getBallCount(),wall.score);
            if(wall.isBallLost()){
                if(wall.ballEnd()){
                    wall.wallReset();
                    hs.addScore("LocalPlayer",wall.score);
                    message = "Game over";
                }
                wall.ballReset();
                gameTimer.stop();
            }
            else if(wall.isDone()){
                if(wall.hasLevel()){
                    message = "Go to Next Level";
                    gameTimer.stop();
                    wall.ballReset();
                    wall.wallReset();
                    wall.nextLevel();
                }
                else{
                    message = "ALL WALLS DESTROYED";
                    gameTimer.stop();
                }
            }

            repaint();
        });

    }


    /**
     *initialises game board
     */
    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }


    /**
     * @param g paints wall and pause menu
     */
    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(Color.BLUE);
        g2d.drawString(message,250,225);

        drawBall(wall.ball,g2d);

        for(Brick b : wall.bricks)
            if(!b.isBroken())
                drawBrick(b,g2d);

        drawPlayer(wall.player,g2d);

        if(showPauseMenu)
            PauseMenu.drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * @param g2d sets the background color
     */
    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    /**
     * draws brick
     * @param brick gets brick
     * @param g2d draws brick
     */
    private void drawBrick(Brick brick,Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(tmp);
    }

    /**
     * draws ball
     * @param ball gets ball
     * @param g2d draws ball
     */
    private void drawBall(Ball ball, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /**
     * creates player
     * @param p gets player
     * @param g2d draws player
     */
    private void drawPlayer(Player p, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(Player.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Player.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
    }


    /**
     * @param keyEvent inputs a key press
     */
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    /**
     * binds keypress to button on keyboard
     * @param keyEvent inputs keypress
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
                wall.player.moveLeft();
                break;
            case KeyEvent.VK_D:
                wall.player.movRight();
                break;
            case KeyEvent.VK_ESCAPE:
                showPauseMenu = !showPauseMenu;
                repaint();
                gameTimer.stop();
                break;
            case KeyEvent.VK_SPACE:
                if(!showPauseMenu)
                    if(gameTimer.isRunning())
                        gameTimer.stop();
                    else
                        gameTimer.start();
                break;
            case KeyEvent.VK_F1:
                    debugConsole.setVisible(true);
            default:
                wall.player.stop();
        }
    }

    /**
     * will stop mivong paddle when key is released
     * @param keyEvent check is key is released
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        wall.player.stop();
    }

    /**
     * will check if buttons are pressed and give functions
     * @param mouseEvent checks if mouse is clicked
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(!showPauseMenu)
            return;
        if(PauseMenu.ContinueButton().contains(p)){
            showPauseMenu = false;
            repaint();
        }
        else if(PauseMenu.RestartButton().contains(p)){
            message = "Restarting Game...";
            wall.ballReset();
            wall.wallReset();
            showPauseMenu = false;
            repaint();
        }
        else if(PauseMenu.ExitButton().contains(p)){
            System.exit(0);
        }

    }

    /**
     * @param mouseEvent check is mouse is pressef
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    /**
     * @param mouseEvent checks if mouse is released
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    /**
     * @param mouseEvent check if mouse is in the ui
     */
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    /**
     * @param mouseEvent check if mouse is out of the ui
     */
    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    /**
     * @param mouseEvent reads mouse movements
     */
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    /**
     * gives a hover effect for buttons
     * @param mouseEvent reads mouse hovering over button
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(PauseMenu.ExitButton() != null && showPauseMenu) {
            if (PauseMenu.ExitButton().contains(p) || PauseMenu.ContinueButton().contains(p) || PauseMenu.RestartButton().contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        }
        else{
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * will stop game when lost focus from window
     */
    public void onLostFocus(){
        gameTimer.stop();
        message = "Focus Lost";
        repaint();
    }

}
