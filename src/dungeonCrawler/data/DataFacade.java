package dungeonCrawler.data;

import dungeonCrawler.aqu.IDataFacade;

import dungeonCrawler.aqu.IHighScore;
import dungeonCrawler.aqu.IMap;
import dungeonCrawler.aqu.IPlayer;

public class DataFacade implements IDataFacade
{
    GameState gameState;

    @Override
    public IHighScore getHighScore()
    {
        IHighScore score = (IHighScore) new HighScore("HighScore.txt");

        return score;
    }
    @Override
    public void saveGame(IPlayer player, IMap map, String filename)
    {
        gameState = new GameState(player, map);
        GameHandler.saveGame(gameState, filename);
    }
            

  
}
