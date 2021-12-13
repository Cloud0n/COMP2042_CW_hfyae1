package HighScore;

import java.io.Serializable;

public class Score implements Serializable {
    private int score;
    private String name;


    /**
     * returns score player obtained
     * @return returns score
     */
    public int getScore() {
        return score;
    }


    /**
     * returns name of player
     * @return returns name
     */
    public String getName() {
        return name;
    }


    /**
     * @param name name of player that obtained score
     * @param score gets score player has obtained
     */
    public Score(String name, int score) {
        this.score = score;
        this.name = name;
    }
}
