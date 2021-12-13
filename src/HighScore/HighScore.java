package HighScore;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class HighScore{

    private ArrayList<Score> scores;

    private static final String HSFILE = "HighScore.dat";

    ObjectOutputStream output = null;
    ObjectInputStream input = null;

    /**
     * Defines score as an array to store score in
     */
    public HighScore() {
        scores = new ArrayList<Score>();
    }


    /**
     * Creates a highscore file
     */
    public void loadHsFile() {
        try {
            input = new ObjectInputStream(new FileInputStream(HSFILE));
            scores = (ArrayList<Score>) input.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("[Laad] FNF Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("[Laad] IO Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("[Laad] CNF Error: " + e.getMessage());
        } finally {
            try {
                if (output != null) {
                    output.flush();
                    output.close();
                }
            } catch (IOException e) {
                System.out.println("[Laad] IO Error: " + e.getMessage());
            }
        }
    }


    /**
     * Updates the High score file that has been created or loaded when a new score is input,
     */
    public void updateHsFile() {
        try {
            output = new ObjectOutputStream(new FileOutputStream(HSFILE));
            output.writeObject(scores);
        } catch (FileNotFoundException e) {
            System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
        } catch (IOException e) {
            System.out.println("[Update] IO Error: " + e.getMessage());
        } finally {
            try {
                if (output != null) {
                    output.flush();
                    output.close();
                }
            } catch (IOException e) {
                System.out.println("[Update] Error: " + e.getMessage());
            }
        }
    }

    /**
     * Gets the string that contains the score
     */
    public String getHsString() {
        String highScoreString = "";
        int max = 10;

        ArrayList<Score> scores;
        scores = getHsScores();

        int i = 0;
        int x = scores.size();
        if (x > max) {
            x = max;
        }
        while (i < x) {
            highScoreString += (i + 1) + ".\t" + scores.get(i).getName() + "\t\t" + scores.get(i).getScore() + "\n";
            i++;
        }
        return highScoreString;
    }

    /**
     * Gets the file that has high score stored in it with all its values
     */
    public ArrayList<Score> getHsScores() {
        loadHsFile();
        sort();
        return scores;
    }

    /**
     * sorts the scores that have been taken from the game
     */
    private void sort() {
        ScoreCompare comparator = new ScoreCompare();
        Collections.sort(scores, comparator);
    }


    /**
     * add score string into file
     * @param name name of player that got specific score
     * @param score score that player has gotten
     */
    public void addScore(String name, int score) {
        loadHsFile();
        scores.add(new Score(name, score));
        updateHsFile();
    }

}



