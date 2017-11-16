package dungeonCrawler.data;

import dungeonCrawler.aqu.IDataFacade;
import dungeonCrawler.aqu.IHighScore;

public class DataFacade implements IDataFacade
{

    @Override
    public IHighScore getHighScore()
    {
        IHighScore score = (IHighScore) new HighScore("HighScore.txt");

        return score;
    }
}
