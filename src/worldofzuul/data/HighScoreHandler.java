package worldofzuul.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * The type High score handler.
 */
public class HighScoreHandler
{
    private String filename;
    private TreeMap<Integer, String> highScore;

    private int maxNumberOfScores;

    /**
     * Instantiates a new High score handler.
     *
     * @param filename the filename
     */
    public HighScoreHandler(String filename)
    {
        this.filename = filename;
        this.highScore = new TreeMap<>();
        this.maxNumberOfScores = 10;
        //readText();
    }

    /**
     * Write text to file.
     */
    public void writeText()
    {
        if (fileExists(filename))
        {
            try
            {
                PrintWriter output = new PrintWriter(filename);

                for (Map.Entry score : highScore.entrySet())
                {
                    output.println(score.getKey() + " " + score.getValue());
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
    public TreeMap<Integer, String> readText()
    {
        try
        {
            Scanner input = new Scanner(new File(filename));

            while( input.hasNextLine() )
            {
                String[] split = input.nextLine().split("\\s");

                highScore.put( Integer.parseInt(split[0]) ,split[1] );
            }

            input.close();

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return highScore;
    }

    public String[] getHighScoreArray()
    {
        String[] highScoreArray = new String[maxNumberOfScores];

        int index = maxNumberOfScores;
        for (Map.Entry entry : highScore.entrySet())
        {
            highScoreArray[index-1] = index + ".\t\t" + entry.getValue() + "\t\t" + entry.getKey();
            index--;
        }

        return highScoreArray;

    }

    /**
     * Add score to TreeMap.
     *
     * @param score the score
     * @param name  the name
     */
    public void addScore(int score, String name)
    {
        if (highScore.size() >= maxNumberOfScores)
        {
            highScore.put(score, name);

            highScore.pollFirstEntry();
        }
        else
        {
            highScore.put(score, name);
        }
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
