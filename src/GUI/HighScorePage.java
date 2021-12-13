package GUI;

import HighScore.HighScore;
import Main.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class HighScorePage extends JComponent implements MouseListener, MouseMotionListener {

    private static final String INFO = "HighScore Page:";
    JTextArea hsText=new JTextArea();
    private static final String EXIT_TEXT = "Exit";

    private static final Color BG_COLOR = new Color(162, 239, 226);
    private static final Color BORDER_COLOR = new Color(255, 255, 204);
    private static final Color TEXT_COLOR = new Color(86, 24, 58);
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = new Color(0, 0, 0);
    private static final int BORDER_SIZE = 5;

    private Rectangle menuFace;
    public Rectangle exitButton;


    private BasicStroke borderStoke_noDashes;

    private Font hsFont;
    private Font buttonFont;

    private GameFrame owner;

    private boolean exitClicked;

    /**
     * creates a page to display all the scores
     * @param owner game frame object
     * @param area area of scoreboard menu
     */
    public HighScorePage(GameFrame owner, Dimension area) {

        this.setFocusable(true);
        this.requestFocusInWindow();


        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;


        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        exitButton = new Rectangle(btnDim);

        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        hsFont = new Font("Noto Mono",Font.PLAIN,40);
        buttonFont = new Font("Monospaced",Font.PLAIN,exitButton.height-2);



    }


    public void setStroke(BasicStroke borderStoke_noDashes){

        this.borderStoke_noDashes=borderStoke_noDashes;
    }

    public void setClick(boolean startClicked, boolean menuClicked, boolean infoClicked){
        this.exitClicked=exitClicked;
    }

    public void setmenuFace(Rectangle Menuface){
        this.menuFace = Menuface;
    }

    public void setButton(Rectangle startButton, Rectangle menuButton, Rectangle infoButton){
        this.exitButton = exitButton;
    }

    /**
     * draws the Highscore board
     * @param g2d draws all the menu objects
     */
    public void drawMenu(Graphics2D g2d){



        drawContainer(g2d);

        Image Background = Toolkit.getDefaultToolkit().getImage("background.jpg");
        g2d.drawImage(Background, 0, 0, this);

        this.setLayout(null);
        this.setSize(1200,621);

        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x,y);

        //methods calls
        drawText(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    /**
     * creates a border around High Score
     * @param g2d draws container of High score
     */
    private void drawContainer(Graphics2D g2d) {





        Stroke tmp = g2d.getStroke();

        g2d.setStroke(borderStoke_noDashes);
        g2d.setColor(BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(tmp);

        /*g2d.setColor(prev);*/

    }

    /**
     * creates text and draws them
     * @param g2d draws text inside High score
     */
    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D infoRect = hsFont.getStringBounds(INFO,frc);

        int sX,sY;

        sX = (int)(menuFace.getWidth() - infoRect.getWidth()) / 2;
        sY = (int)(menuFace.getHeight() / 4);

        g2d.setFont(hsFont);
        g2d.drawString(INFO,sX,sY);

        hsText.setSize(600,200);
        hsText.setLocation(300,200);
        HighScore hs = new HighScore();
        hsText.setText(hs.getHsString());
        this.add(hsText);
    }

    /**
     * creates and draws buttons
     * @param g2d draws button inside High score
     */
    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D extRect = buttonFont.getStringBounds(EXIT_TEXT,frc);


        g2d.setFont(buttonFont);

        int x = (menuFace.width - exitButton.width) / 2;
        int y =(int) ((menuFace.height - exitButton.height) * 0.8);

        exitButton.setLocation(x,y);

        x = (int)(exitButton.getWidth() - extRect.getWidth()) / 2;
        y = (int)(exitButton.getHeight() - extRect.getHeight()) / 2;

        x += exitButton.x;
        y += exitButton.y + (exitButton.height * 0.9);




        if(exitClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(exitButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(EXIT_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(exitButton);
            g2d.drawString(EXIT_TEXT,x,y);
        }

        x = exitButton.x;
        y = exitButton.y;

        y *= 1.2;


    }

    /**
     * paints info menu
     * @param g draws info High score
     */
    public void paint(Graphics g){

        drawMenu((Graphics2D)g);


    }

    /**
     * reads if buttons are clicked
     * @param mouseEvent reads a click from the mouse
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(exitButton.contains(p)){
            owner.enableHomeMenu();


        }

    }

    /**
     * makes an "animation" for when mouse is clicked
     * @param mouseEvent reads when the button is clicked
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (exitButton.contains(p)) {
            exitClicked = true;
            repaint(exitButton.x, exitButton.y, exitButton.width + 1, exitButton.height + 1);

        }
    }

    /**
     * when mouse is released, resets "animation"
     * @param mouseEvent reads when mouse is released
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(exitClicked ){
            exitClicked = false;
            repaint(exitButton.x,exitButton.y,exitButton.width+1,exitButton.height+1);
        }

    }

    /**
     * @param mouseEvent reads if mouse is in menu
     */
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    /**
     * @param mouseEvent reads if mouse exited the menu
     */
    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    /**
     * @param mouseEvent reads movements of mouse in menu
     */
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    /**
     * when mouse hovers over button, will change shape of mouse
     * @param mouseEvent reads if mouse is hovering over button
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(exitButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }
}
