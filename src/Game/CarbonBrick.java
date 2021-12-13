package Game;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

public class CarbonBrick extends  Brick{

    private static final String NAME = "Concrete Brick";
    private static final Color DEF_INNER = new Color(0, 0, 0);
    private static final Color DEF_BORDER = new Color(255, 255, 255);
    private static final int CARBON_STRENGTH = 69;

    private Crack crack;
    private Shape brickFace;


    /**
     * initialise default value for carbon brick
     * @param point where the brick will be
     * @param size size of the brick
     */
    public CarbonBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CARBON_STRENGTH);
        crack = new Crack(DEF_CRACK_DEPTH,DEF_STEPS);
        brickFace = super.brickFace;
    }

    /**
     * makes the brick shape
     * @param pos  position of brick
     * @param size size of brick
     * @return returns position of brick in a  form of a rectangle
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * setup impact and break system
     * @param point point of impact
     * @param dir   direction of impact
     */
    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        super.impact();
        if(!super.isBroken()){
            crack.makeCrack(point,dir);
            updateBrick();
            return false;
        }
        return true;
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
     * updates wether or not brick is cracked or broken and draws crack
     */
    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(super.brickFace,false);
            brickFace = gp;
        }
    }

    /**
     * repairs crack and break in brick
     */
    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.brickFace;
    }

}
