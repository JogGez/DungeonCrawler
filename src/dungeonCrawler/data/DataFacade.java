package dungeonCrawler.data;
import dungeonCrawler.aqu.IDataFacade;
import dungeonCrawler.aqu.IHighScore;
import dungeonCrawler.aqu.IMap;
import dungeonCrawler.aqu.IPlayer;

import java.io.Serializable;


/**
 * DataFacade Class
 * This Class provides the methods, that the logic package requires from the data packages
 * Implements IDataFacade, Serializable
 *
 * @author Gruppen, minus Morten Bindslev
 */
public class DataFacade implements IDataFacade, Serializable
{

    /**
     *
     * @return
     */
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
