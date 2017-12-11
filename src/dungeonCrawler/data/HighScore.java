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
 * The type High score handler.
 */
public class HighScore implements IHighScore, Serializable
{
    private class ScoreItem implements Comparable<ScoreItem>
    {
        String name;
        int score;


        public ScoreItem(String name, int score)
        {
            this.name = name;
            this.score = score;
        }

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
     * Instantiates a new High score handler.
     *
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
     * Write text to file.
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
     * Read text from file into TreeMap.
     *
     * @return the tree map
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

    public void addHighScore(IPlayer player)
    {
        readText();
        if (highScore.get(0).score <= player.getScore())
        {
            highScore.set(0, new ScoreItem(player.getName(), player.getScore()));
        }
        writeText();
    }

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
     * Check if file exists and is a file and not a folder.
     *
     * @param filename String
     * @return boolean
     */
    private boolean fileExists(String filename)
    {
        return new File(filename).exists() && new File(filename).isFile();
    }

    /**
     * Create the file
     *
     * @param filename
     * @return
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
