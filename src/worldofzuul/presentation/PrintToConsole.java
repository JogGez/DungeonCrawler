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

//   //Under construction ?
//    public void printHightScore(HighScoreHandler highScoreHandler)
//    {
////        highScore.readText();
//        for (String score : highScoreHandler.getHighScoreArray())
//        {
//            System.out.println(score);
//        }
//    }
    public void printHightScore(HighScoreHandler highScoreHandler)
    {
//        highScore.readText();
        for (String score : highScoreHandler.readText())
        {
            System.out.println(score);
        }
    }

}
