package Game;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class Levels extends Wall {

    private static final int LEVELS_COUNT = 9;

    public Levels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos) {
        super(drawArea, brickCount, lineCount, brickDimensionRatio, ballPos);
    }

    private static Brick[] makeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){

        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ?  makeBrick(p,brickSize,typeA) : makeBrick(p,brickSize,typeB);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = Wall.makeBrick(p,brickSize,typeA);
        }
        return tmp;
    }
    public static Brick[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio){
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,CLAY);
        tmp[1] = makeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,CEMENT);
        tmp[2] = makeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,STEEL);
        tmp[3] = makeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,CEMENT);
        tmp[4] = makeLevel(drawArea,60,6,brickDimensionRatio,CEMENT,CEMENT);
        tmp[5] = makeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,CONCRETE);
        tmp[6] = makeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CONCRETE,CEMENT);
        tmp[7] = makeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CARBON,STEEL);
        tmp[8] = makeLevel(drawArea,200,20,brickDimensionRatio,CARBON,CARBON);
        return tmp;
    }
}
