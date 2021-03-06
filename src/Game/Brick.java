package Game;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;


/**
 * defines all the impact values that allows the brick to crack
 */
abstract public class Brick  {

    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;


    /**
     * defines the different types of crack
     */
    public class Crack{

        private static final int CRACK_SECTIONS = 3;
        private static final double JUMP_PROBABILITY = 0.7;

        public static final int LEFT = 10;
        public static final int RIGHT = 20;
        public static final int UP = 30;
        public static final int DOWN = 40;
        public static final int VERTICAL = 100;
        public static final int HORIZONTAL = 200;



        private GeneralPath crack;

        private int crackDepth;
        private int steps;


        /**
         * this defines the crack depth and length
         * @param crackDepth defines the depth of the crack
         * @param steps length of the crack
         */
        public Crack(int crackDepth, int steps){

            crack = new GeneralPath();
            this.crackDepth = crackDepth;
            this.steps = steps;

        }


        /**
         * draws crack
         * @return call from crack
         */
        public GeneralPath draw(){

            return crack;
        }

        /**
         * resets the brick
         */
        public void reset(){
            crack.reset();
        }

        /**
         * creates the direction of the crack from where the point of impact is
         * @param point where the crack will be
         * @param direction which way the crack will go
         */
        protected void makeCrack(Point2D point, int direction){
            Rectangle bounds = Brick.this.brickFace.getBounds();

            Point impact = new Point((int)point.getX(),(int)point.getY());
            Point start = new Point();
            Point end = new Point();


            switch(direction){
                case LEFT:
                    start.setLocation(bounds.x + bounds.width, bounds.y);
                    end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                    Point tmp = makeRandomPoint(start,end,VERTICAL);
                    makeCrack(impact,tmp);

                    break;
                case RIGHT:
                    start.setLocation(bounds.getLocation());
                    end.setLocation(bounds.x, bounds.y + bounds.height);
                    tmp = makeRandomPoint(start,end,VERTICAL);
                    makeCrack(impact,tmp);

                    break;
                case UP:
                    start.setLocation(bounds.x, bounds.y + bounds.height);
                    end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                    tmp = makeRandomPoint(start,end,HORIZONTAL);
                    makeCrack(impact,tmp);
                    break;
                case DOWN:
                    start.setLocation(bounds.getLocation());
                    end.setLocation(bounds.x + bounds.width, bounds.y);
                    tmp = makeRandomPoint(start,end,HORIZONTAL);
                    makeCrack(impact,tmp);

                    break;

            }
        }

        /**
         * determines the crack shape and length
         * @param start the start of the crack
         * @param end the end of the crack
         */
        protected void makeCrack(Point start, Point end){

            GeneralPath path = new GeneralPath();


            path.moveTo(start.x,start.y);

            double w = (end.x - start.x) / (double)steps;
            double h = (end.y - start.y) / (double)steps;

            int bound = crackDepth;
            int jump  = bound * 5;

            double x,y;

            for(int i = 1; i < steps;i++){

                x = (i * w) + start.x;
                y = (i * h) + start.y + randomInBounds(bound);

                if(inMiddle(i,CRACK_SECTIONS,steps))
                    y += jumps(jump,JUMP_PROBABILITY);

                path.lineTo(x,y);

            }

            path.lineTo(end.x,end.y);
            crack.append(path,true);
        }

        /**
         * makes the crack appear randomly
         * @param bound bricks
         * @return returns random value
         */
        private int randomInBounds(int bound){
            int n = (bound * 2) + 1;
            return rnd.nextInt(n) - bound;
        }

        private boolean inMiddle(int i,int steps,int divisions){
            int low = (steps / divisions);
            int up = low * (divisions - 1);

            return  (i > low) && (i < up);
        }

        private int jumps(int bound,double probability){

            if(rnd.nextDouble() > probability)
                return randomInBounds(bound);
            return  0;

        }

        /**
         * Creates a random position
         * @param from starting position
         * @param to ending position
         * @param direction direction
         * @return
         */
        private Point makeRandomPoint(Point from,Point to, int direction){

            Point out = new Point();
            int pos;

            switch(direction){
                case HORIZONTAL:
                    pos = rnd.nextInt(to.x - from.x) + from.x;
                    out.setLocation(pos,to.y);
                    break;
                case VERTICAL:
                    pos = rnd.nextInt(to.y - from.y) + from.y;
                    out.setLocation(to.x,pos);
                    break;
            }
            return out;
        }

    }

    private static Random rnd;

    private String name;
    Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;


    /**
     * this constructor initialises the default brick value
     * @param name name of brick to be used
     * @param pos position of brick
     * @param size size of brick
     * @param border border color of brick
     * @param inner inner color of brick
     * @param strength strength of brick before breaking
     */
    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        rnd = new Random();
        broken = false;
        this.name = name;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    /**
     * @param pos position of brick
     * @param size size of brick
     */
    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    /**
     * sets the impact in brick
     * @param point point of impact
     * @param dir direction of impact
     * @return if brick breaks, returns nothing, if brick doesnt break, returns that its broken
     */
    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    /**
     * gets the shape of brick
     */
    public abstract Shape getBrick();



    public Color getBorderColor(){
        return  border;
    }

    public Color getInnerColor(){
        return inner;
    }


    /**
     * finds the impact of the ball
     * @param b gets the ball parameters and where it has an impact
     */
    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.right))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.left))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.up))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.down))
            out = UP_IMPACT;
        return out;
    }

    /**
     * @return if brick is broken, returns broken
     */
    public final boolean isBroken(){
        return broken;
    }

    /**
     * if brick is broken or crracked , resets the strength of brick to original
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * Defines the game mechanic where if the ball hits the brick, it will minus the strength until strength is 0 which will then break the brick
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }



}





