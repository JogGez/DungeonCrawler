package dungeonCrawler.data;
import dungeonCrawler.aqu.IDataFacade;
import dungeonCrawler.aqu.IHighScore;
import dungeonCrawler.aqu.IMap;
import dungeonCrawler.aqu.IPlayer;

import java.io.Serializable;


/**
 * DataFacade Class
 * This Class provides the methods, that the logic package requires from the data package
 * Implements IDataFacade, Serializable
 *
 * @author Peter Jonathan, Joakim, Linea, Frederik, Simon og Brian
 */
public class DataFacade implements IDataFacade, Serializable
{

    /**
     *
     * @return IHighScore
     */
    @Override
    public IHighScore getHighScore()
    {
        IHighScore score = (IHighScore) new HighScore("HighScore.txt");

        return score;
    }

    /**
     *
     * @param gameStateDTO
     * @param filename
     * @return GameStateDTO
     */
    public GameStateDTO load(GameStateDTO gameStateDTO, String filename)
    {
        gameStateDTO = GameHandler.loadGame(gameStateDTO,filename);
        return gameStateDTO;
    }

    /**
     *
     * @param gameStateDTO
     * @param filename
     */
    public void save(GameStateDTO gameStateDTO, String filename)
    {
        GameHandler.saveGame(gameStateDTO,filename);
    }






  
}
