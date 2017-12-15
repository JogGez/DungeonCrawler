package dungeonCrawler.data;

//import com.sun.xml.internal.fastinfoset.util.StringArray;

import dungeonCrawler.aqu.IHighScore;
import dungeonCrawler.aqu.IPlayer;

import java.io.*;
import java.util.*;
//// Test to XML file
//import javax.xml.parsers.*;
//import javax.xml.transform.*;
//import javax.xml.transform.dom.*;
//import javax.xml.transform.stream.*;

/**
 * HighScore Class
 *
 * implements IHighScore and Serializable
 *
 * @author Peter Jonathan, Joakim, Linea, Frederik, Simon og Brian
 */
public class HighScore implements IHighScore, Serializable
{
    /**
     * ScoreItem Next Class
     * Inner Class of HighScore
     * implements Comparable
     */
    private class ScoreItem implements Comparable<ScoreItem>
    {
        String name;
        int score;

        /**
         * Constructor for ScoreItem
         * @param name
         * @param score
         */
        public ScoreItem(String name, int score)
        {
            this.name = name;
            this.score = score;
        }

        /**
         * Method to compare context of text file
         * @param o
         * @return int
         */
        @Override
        public int compareTo(ScoreItem o)
        {
            int compare = o.score;
            return this.score-compare;
        }
    }
    private String filename;
    private ArrayList<ScoreItem>highScore;
    private int maxNumberOfScores;

    /**
     * Constructor for HighScore
     * @param filename the filename
     */
    public HighScore(String filename)
    {
        this.filename = filename;
//        this.highScore = new String[10];
        this.maxNumberOfScores = 10;
        this.highScore = new ArrayList<>();
    }

    /**
     * Method to write text to file.
     */
    public void writeText()
    {
        //checks if file exists, if file doesn't exist it creates the file in our else statement.
        if (fileExists(filename))
        {
            try
            {
                PrintWriter output = new PrintWriter(filename);

                for (ScoreItem score : highScore)
                {
                    output.println(score.score + " " + score.name);
                }

                output.flush();
                output.close();


            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            createFile(filename);

            this.writeText();
        }

    }


    /**
     * Method to read text to IDE
     */
    public void readText()
    {
        try
        {
            Scanner input = new Scanner(new File(filename));

            while( input.hasNextLine() )
            {
                String[] split = input.nextLine().split("\\s");
                highScore.add(new ScoreItem(split[1],Integer.parseInt(split[0])));
            }

            //remember to close input for garbage collection
            input.close();

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        // returns the highscore
        Collections.sort(highScore);
    }

    /**
     * Method for HighScore to add player score to text file
     * @param player
     */

    public void addHighScore(IPlayer player)
    {
        readText();
        if (highScore.get(0).score <= player.getScore())
        {
            highScore.set(0, new ScoreItem(player.getName(), player.getScore()));
        }
        writeText();
    }

    /**
     * Getter method for HighScore Array
     * @return String []
     */
    public String[] getHighScoreArray()
    {
        String[] highScoreArray = new String[maxNumberOfScores];

        int index = highScore.size();
        for (ScoreItem score : highScore)
        {
            highScoreArray[index-1] = index + ".\t\t" + score.name + "\t\t" + score.score;
            index--;
        }

        return highScoreArray;

    }

    /**
     * Method to check if file exists and is a file and not a folder.
     * @param filename String
     * @return boolean
     */
    private boolean fileExists(String filename)
    {
        return new File(filename).exists() && new File(filename).isFile();
    }

    /**
     * Method to create the file
     * @param filename
     * @return boolean
     */
    private boolean createFile(String filename)
    {
        try
        {
            File file = new File(filename);
            //file.getParentFile().mkdirs();
            return file.createNewFile();

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return false;
    }
}
