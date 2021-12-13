
package Game;

import java.awt.*;


public class Player {


    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = Color.GREEN;

    private static final int DEF_MOVE_AMOUNT = 5;

    private Rectangle playerFace;
    private Point ballPoint;
    private int moveAmount;
    private int min;
    private int max;


    /**
     * creates player
     * @param ballPoint where ball is
     * @param width width of player rectangle
     * @param height of player rectangle
     * @param container container of player rectangle
     */
    public Player(Point ballPoint,int width,int height,Rectangle container) {
        this.ballPoint = ballPoint;
        moveAmount = 0;
        playerFace = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;

    }

    /**
     * makes rectangle for player
     * @param width rectangle width
     * @param height rectangle height
     * @return returns rectangle
     */
    private Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }

    /**
     * impact of ball when hits the player
     * @param b ball b
     * @return returns player position
     */
    public boolean impact(Ball b){
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.down) ;
    }

    /**
     *moves the rectangle
     */
    public void move(){
        double x = ballPoint.getX() + moveAmount;
        if(x < min || x > max)
            return;
        ballPoint.setLocation(x,ballPoint.getY());
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }

    /**
     *moves paddle left
     */
    public void moveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    /**
     *moves paddle right
     */
    public void movRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }

    /**
     *when no input, no movement
     */
    public void stop(){
        moveAmount = 0;
    }

    /**
     * shape of paddle
     * @return returns shape of paddle
     */
    public Shape getPlayerFace(){
        return  playerFace;
    }

    /**
     * movement of paddle
     * @param p moves paddle to point p
     */
    public void moveTo(Point p){
        ballPoint.setLocation(p);
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }
}
