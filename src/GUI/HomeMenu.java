
package GUI;

import Main.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class HomeMenu extends JLayeredPane implements MouseListener, MouseMotionListener {

    private static final Color BG_COLOR = new Color(255,102,102);
    private static final Color BORDER_COLOR = new Color(255, 255, 204);
    private static final Color TEXT_COLOR = new Color(166, 182, 241);
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = new Color(0, 0, 0);
    private static final int BORDER_SIZE = 5;

    /**private JLabel menubg = new JLabel();
    private Image menubackground = new ImageLoad(ImageLoad.menubackground).getImage();
    private ImageIcon menuIcon = new ImageIcon(menubackground);
**/
    private Rectangle menuFace;
    public Rectangle startButton;
    public Rectangle menuButton;

    private BasicStroke borderStoke_noDashes;

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;

    private GameFrame owner;

    private boolean startClicked;
    private boolean menuClicked;

    private GUI.HomeDraw HomeDraw;


    public HomeMenu(GameFrame owner,Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();
        HomeDraw=new HomeDraw();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;

        /**this.setLayout(null);
        this.setSize(1200,621);
        menubg.setBounds(0,0,1200,621);
        menubg.setIcon(menuIcon);
       this.add(menubg,DEFAULT_LAYER);
**/

        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        startButton = new Rectangle(btnDim);
        menuButton = new Rectangle(btnDim);

        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        greetingsFont = new Font("Noto Mono",Font.PLAIN,25);
        gameTitleFont = new Font("Noto Mono",Font.BOLD,40);
        creditsFont = new Font("Monospaced",Font.PLAIN,10);
        buttonFont = new Font("Monospaced",Font.PLAIN,startButton.height-2);



    }


    public void paint(Graphics g){

        HomeDraw.setClick(startClicked,menuClicked);
        HomeDraw.setmenuFace(menuFace);
        HomeDraw.setButton(startButton,menuButton);
        HomeDraw.setFont(greetingsFont,gameTitleFont,creditsFont,buttonFont);
        HomeDraw.setStroke(borderStoke_noDashes);
        HomeDraw.drawMenu((Graphics2D)g);


    }


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
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p)){
            startClicked = true;
            repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);

        }
        else if(menuButton.contains(p)){
            menuClicked = true;
            repaint(menuButton.x,menuButton.y,menuButton.width+1,menuButton.height+1);
        }
    }

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
        if(startButton.contains(p) || menuButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }

}
