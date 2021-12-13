package HighScore;

import java.util.Comparator;

public class ScoreCompare implements Comparator<Score> {


    /**
     * cpompares score 1 and 2 to be able to sort
     * @param score1 defined variable as "score1" to compare score 1 and score 2
     * @param score2 defined variable as "score2" to compare score 1 and score 2
     */
    public int compare(Score score1, Score score2) {

        int sc1 = score1.getScore();
        int sc2 = score2.getScore();

        if (sc1 > sc2){
            return -1;
        }else if (sc1 < sc2){
            return +1;
        }else{
            return 0;
        }
    }

}