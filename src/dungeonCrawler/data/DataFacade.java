package dungeonCrawler.data;

import dungeonCrawler.aqu.IDataFacade;

import dungeonCrawler.aqu.IHighScore;
import dungeonCrawler.aqu.IMap;
import dungeonCrawler.aqu.IPlayer;

import java.io.Serializable;

public class DataFacade implements IDataFacade, Serializable
{

    @Override
    public IHighScore getHighScore()
    {
        IHighScore score = (IHighScore) new HighScore("HighScore.txt");

        return score;
    }

    public GameStateDTO load(GameStateDTO gameStateDTO, String filename)
    {
        gameStateDTO = GameHandler.loadGame(gameStateDTO,filename);
        return gameStateDTO;
    }

    public void save(GameStateDTO gameStateDTO, String filename)
    {
        GameHandler.saveGame(gameStateDTO,filename);
    }






  
}
