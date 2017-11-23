package dungeonCrawler.presentationConsole;

import dungeonCrawler.aqu.IHighScore;

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
//    public void printHightScore(HighScore highScore)
//    {
////        highScore.readText();
//        for (String score : highScore.getHighScoreArray())
//        {
//            System.out.println(score);
//        }
//    }
    public void printHightScore(IHighScore highScore)
    {
        highScore.readText();
        for (String score : highScore.getHighScoreArray())
        {
            System.out.println(score);
        }
    }

}
