package GUI;

import Main.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;


public class HomeMenu extends JComponent implements MouseListener, MouseMotionListener {

    private static final String GREETINGS = "Welcome to:";
    private static final String GAME_TITLE = "Brick Destroy";
    private static final String CREDITS = "Edited by Ali Wael";
    private static final String START_TEXT = "Start game";
    private static final String MENU_TEXT = "Exit game";
    private static final String INFO_TEXT = "Info";
    private static final String HS_TEXT = "Highscore";

    private static final Color BG_COLOR = new Color(255,102,102,0);
    private static final Color BORDER_COLOR = new Color(255, 255, 204);
    private static final Color TEXT_COLOR = new Color(86, 24, 58);
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = new Color(0, 0, 0);
    private static final int BORDER_SIZE = 5;

    private Rectangle menuFace;
    public Rectangle startButton;
    public Rectangle menuButton;
    public Rectangle infoButton;
    public Rectangle hsButton;

    private BasicStroke borderStoke_noDashes;

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;

    private GameFrame owner;

    private boolean startClicked;
    private boolean menuClicked;
    private boolean infoClicked;
    private boolean hsClicked;


    /**
     * creates home menu
     * @param owner game frame object
     * @param area area of home menu
     */
    public HomeMenu(GameFrame owner,Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();


        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;


        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        startButton = new Rectangle(btnDim);
        menuButton = new Rectangle(btnDim);
        infoButton = new Rectangle(btnDim);
        hsButton = new Rectangle(btnDim);

        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        greetingsFont = new Font("Noto Mono",Font.PLAIN,25);
        gameTitleFont = new Font("Noto Mono",Font.BOLD,40);
        creditsFont = new Font("Monospaced",Font.PLAIN,10);
        buttonFont = new Font("Monospaced",Font.PLAIN,startButton.height-2);



    }


    public void setStroke(BasicStroke borderStoke_noDashes){

        this.borderStoke_noDashes=borderStoke_noDashes;
    }

    public void setClick(boolean startClicked, boolean menuClicked, boolean infoClicked, boolean hsClicked){
        this.startClicked=startClicked;
        this.menuClicked=menuClicked;
        this.infoClicked=infoClicked;
        this.hsClicked=hsClicked;
    }

    public void setmenuFace(Rectangle Menuface){
        this.menuFace = Menuface;
    }

    public void setButton(Rectangle startButton, Rectangle menuButton, Rectangle infoButton, Rectangle hsButton){
        this.startButton = startButton;
        this.menuButton = menuButton;
        this.infoButton = infoButton;
        this.hsButton = hsButton;
    }

    /**
     * draws the menu
     * @param g2d draws all the menu objects
     */
    public void drawMenu(Graphics2D g2d){



        drawContainer(g2d);


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
     * creates a border around menu
     * @param g2d draws container of menu
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
     * @param g2d draws text inside menuu
     */
    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS,frc);
        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE,frc);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS,frc);

        int sX,sY;

        sX = (int)(menuFace.getWidth() - greetingsRect.getWidth()) / 2;
        sY = (int)(menuFace.getHeight() / 4);

        g2d.setFont(greetingsFont);
        g2d.drawString(GREETINGS,sX,sY);

        sX = (int)(menuFace.getWidth() - gameTitleRect.getWidth()) / 2;
        sY += (int) gameTitleRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(gameTitleFont);
        g2d.drawString(GAME_TITLE,sX,sY);

        sX = (int)(menuFace.getWidth() - creditsRect.getWidth()) / 2;
        sY += (int) creditsRect.getHeight() * 1.1;

        g2d.setFont(creditsFont);
        g2d.drawString(CREDITS,sX,sY);


    }

    /**
     * creates and draws buttons
     * @param g2d draws button inside menu
     */
    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = buttonFont.getStringBounds(START_TEXT,frc);
        Rectangle2D mTxtRect = buttonFont.getStringBounds(MENU_TEXT,frc);
        Rectangle2D iTxtRect = buttonFont.getStringBounds(INFO_TEXT,frc);
        Rectangle2D hsTxtRect = buttonFont.getStringBounds(HS_TEXT,frc);

        g2d.setFont(buttonFont);

        int x = (menuFace.width - startButton.width) / 2;
        int y =(int) ((menuFace.height - startButton.height) * 0.8);

        startButton.setLocation(x,y);

        x = (int)(startButton.getWidth() - txtRect.getWidth()) / 2;
        y = (int)(startButton.getHeight() - txtRect.getHeight()) / 2;

        x += startButton.x;
        y += startButton.y + (startButton.height * 0.9);




        if(startClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(startButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(START_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(startButton);
            g2d.drawString(START_TEXT,x,y);
        }

        x = startButton.x;
        y = startButton.y;

        y *= 1.2;

        menuButton.setLocation(x,y);

        x = (int)(menuButton.getWidth() - mTxtRect.getWidth()) / 2;
        y = (int)(menuButton.getHeight() - mTxtRect.getHeight()) / 2;

        x += menuButton.x;
        y += menuButton.y + (startButton.height * 0.9);

        if(menuClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(menuButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(MENU_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(menuButton);
            g2d.drawString(MENU_TEXT,x,y);
        }


        infoButton.setLocation(400,370);

        x = (int)(infoButton.getWidth() - iTxtRect.getWidth()) / 2;
        y = (int)(menuButton.getHeight() - iTxtRect.getHeight()) / 2;

        x += infoButton.x;
        y += infoButton.y + (startButton.height * 0.9);

        if(infoClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(infoButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(INFO_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(infoButton);
            g2d.drawString(INFO_TEXT,x,y);
        }

        hsButton.setLocation(400,290);

        x = (int)(hsButton.getWidth() - hsTxtRect.getWidth()) / 2;
        y = (int)(menuButton.getHeight() - hsTxtRect.getHeight()) / 2;

        x += hsButton.x;
        y += hsButton.y + (startButton.height * 0.9);

        if(hsClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(hsButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(HS_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(hsButton);
            g2d.drawString(HS_TEXT,x,y);
        }
    }

    /**
     * paints home menu
     * @param g draws home menu
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
        if(startButton.contains(p)){
            owner.enableGameBoard();

        }
        else if(menuButton.contains(p)){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }

        else if(infoButton.contains(p)){
            owner.enableInfoPage();
        }

        else if(hsButton.contains(p)){
            owner.enableHighScore();
        }

    }

    /**
     * makes an "animation" for when mouse is clicked
     * @param mouseEvent reads when the button is clicked
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (startButton.contains(p)) {
            startClicked = true;
            repaint(startButton.x, startButton.y, startButton.width + 1, startButton.height + 1);

        } else if (menuButton.contains(p)) {
            menuClicked = true;
            repaint(menuButton.x, menuButton.y, menuButton.width + 1, menuButton.height + 1);

        } else if (infoButton.contains(p)) {
            infoClicked = true;
            repaint(infoButton.x, infoButton.y, infoButton.width + 1, infoButton.height + 1);
        }
        else if (hsButton.contains(p)) {
            hsClicked = true;
            repaint(hsButton.x, hsButton.y, hsButton.width + 1, hsButton.height + 1);
        }
    }

    /**
     * when mouse is released, resets "animation"
     * @param mouseEvent reads when mouse is released
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(startClicked ){
            startClicked = false;
            repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);
        }
        else if(menuClicked){
            menuClicked = false;
            repaint(menuButton.x,menuButton.y,menuButton.width+1,menuButton.height+1);
        }

        else if(infoClicked){
            infoClicked = false;
            repaint(infoButton.x,infoButton.y,infoButton.width+1,infoButton.height+1);
        }

        else if(hsClicked){
            hsClicked = false;
            repaint(hsButton.x,hsButton.y,hsButton.width+1,hsButton.height+1);
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
        if(startButton.contains(p) || menuButton.contains(p) || infoButton.contains(p) || hsButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }

}