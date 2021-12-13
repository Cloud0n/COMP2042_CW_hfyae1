package Game;

import java.awt.*;
import java.awt.Point;



public class ClayBrick extends Brick {

    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;


    /**
     * initialise default value for clay brick
     * @param point where the brick will be
     * @param size size of the brick
     */
    public ClayBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }

    /**
     * makes the brick shape
     * @param pos  position of brick
     * @param size size of brick
     * @return position of brick in a  form of a rectangle
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
        return super.brickFace;
    }


}
