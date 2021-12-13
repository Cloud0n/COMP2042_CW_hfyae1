
package Game;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


public class SteelBrick extends Brick {

    private static final String NAME = "Steel Brick";
    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private Random rnd;
    private Shape brickFace;

    /**
     * initialise default value for clay brick
     * @param point where the brick will be
     * @param size size of the brick
     */
    public SteelBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        rnd = new Random();
        brickFace = super.brickFace;
    }


    /**
     * makes the brick shape
     * @param pos  position of brick
     * @param size size of brick
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * gets the shape of the brick
     * @return returns referenced brickface
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     * sets impact of when broken
     * @param point point of impact
     * @param dir   direction of impact
     * @return returns if broken
     */
    public  boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        impact();
        return  super.isBroken();
    }

    /**
     * will randomly crack the brick or brwak it depending on probability
     */
    public void impact(){
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            super.impact();
        }
    }

}
