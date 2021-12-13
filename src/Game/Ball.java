package Game;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;


abstract public class Ball {

    private Shape ballFace;

    private Point2D center;

    Point2D up;
    Point2D down;
    Point2D left;
    Point2D right;

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;
    private int radiusA;
    private int radiusB;

    /**
     * Sets the values of the ball
     * @param center Position of the ball
     * @param radiusA Radius of the ball
     * @param radiusB Radius of the ball
     * @param inner inside color of the ball
     * @param border Border color of the ball
     */
    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        this.center = center;
        this.radiusA = radiusA;
        this.radiusB = radiusB;
        this.inner = inner;
        this.border = border;


        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY()-(radiusB / 2));
        down.setLocation(center.getX(),center.getY()+(radiusB / 2));

        left.setLocation(center.getX()-(radiusA /2),center.getY());
        right.setLocation(center.getX()+(radiusA /2),center.getY());


        ballFace = makeBall(center,radiusA,radiusB);
        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
    }

    /**
     * creates the ball
     * @param center Position of the ball
     * @param radiusA Radius of the ball
     * @param radiusB Radius of the ball
     * @return
     */
    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    /**
     * Sets the movement physics of the ball
     */
    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }

    /**
     * @param x sets the x speeed of the ball
     * @param y sets the y speed of the ball
     */
    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    public void setXSpeed(int s){
        speedX = s;
    }

    public void setYSpeed(int s){
        speedY = s;
    }

    public void reverseX(){
        speedX *= -1;
    }

    public void reverseY(){
        speedY *= -1;
    }

    public int getRadiusA(){return radiusA;}

    public int getRadiusB(){return radiusB;}

    public Color getBorderColor(){
        return border;
    }

    public Color getInnerColor(){
        return inner;
    }

    public Point2D getPosition(){
        return center;
    }

    public Shape getBallFace(){
        return ballFace;
    }

    public Point2D getUp(){return up;}

    public void setUp(Point2D up){this.up=up;}

    public Point2D getDown(){return down;}

    public void setDown(Point2D down){this.down=down;}

    public Point2D getLeft(){return left;}

    public void setLeft(Point2D left){this.left=left;}

    public Point2D getRight(){return right;}

    public void setRight(Point2D right){this.right=right;}

    public void setBallFace(Shape ballFace){this.ballFace=ballFace;}

    /**
     *  Forces the ball to a set position in the beginning of the game
     * @param p A specific point in the game
     */
    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    /**
     * sets location of rectangle(player)
     * @param width width of rectangle
     * @param height height of rectangle
     */
    private void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }

    public int getSpeedX(){
        return speedX;
    }

    public int getSpeedY(){
        return speedY;
    }


}
