
package Game;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


public class Wall {


    public static final int CLAY = 1;
    public static final int STEEL = 2;
    public static final int CEMENT = 3;
    public static final int CONCRETE = 4;
    public static final int CARBON = 5;

    private Random rnd;
    private Rectangle area;

    public Brick[] bricks;
    public Ball ball;
    public Player player;

    private Brick[][] levels;
    private int level;

    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;
    public int score=0;

    /**
     * creates the wall which the ball bounces off of
     * @param drawArea draws area of wall
     * @param brickCount how many bricks in the level
     * @param lineCount how many lines of brick in a level
     * @param brickDimensionRatio whats is the dimensions of brick
     * @param ballPos what is the starting ball position
     */
    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        this.startPoint = new Point(ballPos);

        levels = Levels.makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;

        ballCount = 3;
        ballLost = false;

        rnd = new Random();

        makeBall(ballPos);
        int speedX,speedY;
        do{
            speedX = 3;
        }while(speedX == 0);
        do{
            speedY = -3;
        }while(speedY == 0);

        ball.setSpeed(speedX,speedY);

        player = new Player((Point) ballPos.clone(),150,10, drawArea);

        area = drawArea;


    }


    /**
     * makes ball
     * @param ballPos what is the starting ball position
     */
    private void makeBall(Point2D ballPos){
        ball = new RubberBall(ballPos);
    }

    /**
     *lets player paddle and ball move
     */
    public void move(){
        player.move();
        ball.move();
    }

    /**
     * will find where the ball has impact with in wall, in player or in brick
     */
    public void findImpacts(){
        if(player.impact(ball)){
            ball.reverseY();
        }
        else if(impactWall()){

            brickCount--;
            score++;
        }
        else if(impactBorder()) {
            ball.reverseX();
        }
        else if(ball.getPosition().getY() < area.getY()){
            ball.reverseY();
        }
        else if(ball.getPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            ballLost = true;
        }
    }

    /**
     * impact with wall reverses ball direction
     */
    private boolean impactWall(){
        for(Brick b : bricks){
            switch(b.findImpact(ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.down, Brick.Crack.UP);
                case Brick.DOWN_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.up,Brick.Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.right,Brick.Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.left,Brick.Crack.LEFT);
            }
        }
        return false;
    }

    /**
     * impact with border change ball direction
     */
    private boolean impactBorder(){
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    public int getBrickCount(){
        return brickCount;
    }

    public int getBallCount(){
        return ballCount;
    }

    public boolean isBallLost(){
        return ballLost;
    }

    /**
     * will reset ball position when button is pressed or when ball is lost
     */
    public void ballReset(){
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
        int speedX,speedY;
        do{
            speedX = 3;
        }while(speedX == 0);
        do{
            speedY = -3;
        }while(speedY == 0);

        ball.setSpeed(speedX,speedY);
        ballLost = false;
    }

    /**
     * will reset level
     */
    public void wallReset(){
        for(Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
    }

    /**
     * game ends when ball count is 0
     */
    public boolean ballEnd(){
        return ballCount == 0;
    }

    /**
     * when all bricks are broken, level ends
     */
    public boolean isDone(){
        return brickCount == 0;
    }

    /**
     * next level will be initiated when either skip level is pressed or all bricks are gone
     */
    public void nextLevel(){
        bricks = levels[level++];
        this.brickCount = bricks.length;
    }

    /**
     * available levels less that the max amount of levels
     */
    public boolean hasLevel(){
        return level < levels.length;
    }

    public void setBallXSpeed(int s){
        ball.setXSpeed(s);
    }

    public void setBallYSpeed(int s){
        ball.setYSpeed(s);
    }

    /**
     * resets ball count to 3
     */
    public void resetBallCount(){
        ballCount = 3;
    }

    /**
     * creates brick
     * @param point psoition of brick
     * @param size size of brick
     * @param type type of brick
     */
    static Brick makeBrick(Point point, Dimension size, int type){
        Brick out;
        switch(type){

            case CARBON:
                out = new CarbonBrick(point, size);
                break;

            case CONCRETE:
                out = new ConcreteBrick(point, size);
                break;

            case CLAY:
                out = new ClayBrick(point,size);
                break;
            case STEEL:
                out = new SteelBrick(point,size);
                break;
            case CEMENT:
                out = new CementBrick(point, size);
                break;
            default:
                throw  new IllegalArgumentException(String.format("Unknown Type:%d\n",type));
        }
        return  out;
    }

}
