package dungeonCrawler.data;

import dungeonCrawler.aqu.IDataFacade;

import dungeonCrawler.aqu.IHighScore;
import dungeonCrawler.aqu.IMap;
import dungeonCrawler.aqu.IPlayer;

import java.io.Serializable;

public class DataFacade implements IDataFacade, Serializable
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

    @Override
    public void loadGame(String fileName)
    {
        this.gameState = GameHandler.loadGame(gameState, "fileName.sav");
    }

    @Override
    public IMap getMap()
    {
        return gameState.map;
    }

    @Override
    public IPlayer getPlayer()
    {
        return gameState.player;
    }



  
}
