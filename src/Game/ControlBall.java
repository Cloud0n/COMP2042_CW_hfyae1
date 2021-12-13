package Game;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

public class ControlBall {

    /**
     * defines and creates ball
     */
    private Ball ball;

    ControlBall(Ball ball) {
        this.ball = ball;
        ball.setUp(new Point2D.Double());
        ball.setDown(new Point2D.Double());
        ball.setLeft(new Point2D.Double());
        ball.setRight(new Point2D.Double());

        ball.getUp().setLocation(ball.getPosition().getX(), ball.getPosition().getY() - (ball.getRadiusB() / 2));
        ball.getDown().setLocation(ball.getPosition().getX(), ball.getPosition().getY() + (ball.getRadiusB() / 2));

        ball.getLeft().setLocation(ball.getPosition().getX() - (ball.getRadiusA() / 2), ball.getPosition().getY());
        ball.getRight().setLocation(ball.getPosition().getX() + (ball.getRadiusA() / 2), ball.getPosition().getY());
    }


    /**
     * moves the ball depending on impact from player
     * @param p moves ball to position p
     */
    public void moveTo(Point p) {
        ball.getPosition().setLocation(p);

        RectangularShape tmp = (RectangularShape) ball.getBallFace();
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((ball.getPosition().getX() - (w / 2)), (ball.getPosition().getY() - (h / 2)), w, h);
        ball.setBallFace(tmp);
    }

    /**
     * sets ball shape
     * @param width width of ball
     * @param height height of ball
     */
    private void setPoints(double width, double height) {
        ball.getUp().setLocation(ball.getPosition().getX(), ball.getPosition().getY() - (height / 2));
        ball.getDown().setLocation(ball.getPosition().getX(), ball.getPosition().getY() + (height / 2));

        ball.getLeft().setLocation(ball.getPosition().getX() - (width / 2), ball.getPosition().getY());
        ball.getRight().setLocation(ball.getPosition().getX() + (width / 2), ball.getPosition().getY());
    }

    /**
     * sets ball speed
     * @param x sets speed of x
     * @param y sets speed of y
     */
    public void setSpeed(int x, int y) {
        ball.setXSpeed(x);
        ball.setYSpeed(y);
    }

    /**
     * moves the ball position in correlation to impact with player
     */
    public void move() {
        RectangularShape tmp = (RectangularShape) ball.getBallFace();
        ball.getPosition().setLocation((ball.getPosition().getX() + ball.getSpeedX()), (ball.getPosition().getY() + ball.getSpeedY()));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((ball.getPosition().getX() - (w / 2)), (ball.getPosition().getY() - (h / 2)), w, h);
        setPoints(w, h);


        ball.setBallFace(tmp);
    }

    /**
     * gets x speed
     * @return returns the x speed of the ball
     */
    public int getSpeedX(){
        return ball.getSpeedX();
    }

    /**
     * gets y speed
     * @return returns the y speed of the ball
     */
    public int getSpeedY(){
        return ball.getSpeedY();
    }

}



