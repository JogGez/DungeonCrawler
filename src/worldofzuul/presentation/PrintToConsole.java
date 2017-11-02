package worldofzuul.presentation;

import worldofzuul.data.HighScoreHandler;

public class PrintToConsole
{
    /**
     * Class constructor
     * Used for instantiate TextToPrintToConsole
     */
    //No-arg Constructor
    public PrintToConsole()
    {
    }


    public void print(String print)
    {
        System.out.println(print);
    }

    public void printHightScore()
    {
        HighScoreHandler highScore = new HighScoreHandler("HighScore.txt");
        highScore.readText();
        for (String score : highScore.getHighScoreArray())
        {
            System.out.println(score);
        }
    }

}
