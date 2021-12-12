package GUI;

import Main.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class InfoPage extends JComponent implements MouseListener, MouseMotionListener {

    private static final String INFO = "Info Page:";
    private static final String SUB_INFO = "Brick Destroyer is a game that has been edited by Ali Wael.";
    private static final String SUB_INFO2 ="Controls for this game are as follows:";
    private static final String SUB_INFO3 ="A and D to move paddle left and right";
    private static final String SUB_INFO4 =" F1 to open game debugger";
    private static final String SUB_INFO5 ="ESC to pause and open escape menu.";
    private static final String SUB_INFO6 =" Thank you for playing this game.";
    private static final String EXIT_TEXT = "Exit";

    private static final Color BG_COLOR = new Color(162, 239, 226);
    private static final Color BORDER_COLOR = new Color(255, 255, 204);
    private static final Color TEXT_COLOR = new Color(217, 173, 173);
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = new Color(0, 0, 0);
    private static final int BORDER_SIZE = 5;

    private Rectangle menuFace;
    public Rectangle exitButton;


    private BasicStroke borderStoke_noDashes;

    private Font infoFont;
    private Font subinfoFont;
    private Font buttonFont;

    private GameFrame owner;

    private boolean exitClicked;

    public InfoPage(GameFrame owner, Dimension area) {
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

        infoFont = new Font("Noto Mono",Font.PLAIN,40);
        subinfoFont = new Font("Noto Mono",Font.BOLD,20);
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

    private void drawContainer(Graphics2D g2d) {





        Stroke tmp = g2d.getStroke();

        g2d.setStroke(borderStoke_noDashes);
        g2d.setColor(BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(tmp);

        /*g2d.setColor(prev);*/

    }
    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D infoRect = infoFont.getStringBounds(INFO,frc);
        Rectangle2D subinfoRect = subinfoFont.getStringBounds(SUB_INFO,frc);
        Rectangle2D subinfo2Rect = subinfoFont.getStringBounds(SUB_INFO2,frc);
        Rectangle2D subinfo3Rect = subinfoFont.getStringBounds(SUB_INFO3,frc);
        Rectangle2D subinfo4Rect = subinfoFont.getStringBounds(SUB_INFO4,frc);
        Rectangle2D subinfo5Rect = subinfoFont.getStringBounds(SUB_INFO5,frc);
        Rectangle2D subinfo6Rect = subinfoFont.getStringBounds(SUB_INFO6,frc);

        int sX,sY;

        sX = (int)(menuFace.getWidth() - infoRect.getWidth()) / 2;
        sY = (int)(menuFace.getHeight() / 4);

        g2d.setFont(infoFont);
        g2d.drawString(INFO,sX,sY);

        sX = (int)(menuFace.getWidth() - subinfoRect.getWidth()) / 2;
        sY += (int) subinfoRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(subinfoFont);
        g2d.drawString(SUB_INFO,sX,sY);

        sX = (int)(menuFace.getWidth() - subinfo2Rect.getWidth()) / 2;
        sY += (int) subinfoRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(subinfoFont);
        g2d.drawString(SUB_INFO2,sX,sY);

        sX = (int)(menuFace.getWidth() - subinfo3Rect.getWidth()) / 2;
        sY += (int) subinfoRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(subinfoFont);
        g2d.drawString(SUB_INFO3,sX,sY);

        sX = (int)(menuFace.getWidth() - subinfo4Rect.getWidth()) / 2;
        sY += (int) subinfoRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(subinfoFont);
        g2d.drawString(SUB_INFO4,sX,sY);

        sX = (int)(menuFace.getWidth() - subinfo5Rect.getWidth()) / 2;
        sY += (int) subinfoRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(subinfoFont);
        g2d.drawString(SUB_INFO5,sX,sY);

        sX = (int)(menuFace.getWidth() - subinfo6Rect.getWidth()) / 2;
        sY += (int) subinfoRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(subinfoFont);
        g2d.drawString(SUB_INFO6,sX,sY);



    }

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

    public void paint(Graphics g){

        drawMenu((Graphics2D)g);


    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(exitButton.contains(p)){
            owner.enableHomeMenu();


        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (exitButton.contains(p)) {
            exitClicked = true;
            repaint(exitButton.x, exitButton.y, exitButton.width + 1, exitButton.height + 1);

        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(exitClicked ){
            exitClicked = false;
            repaint(exitButton.x,exitButton.y,exitButton.width+1,exitButton.height+1);
        }

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(exitButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }

}

