package dungeonCrawler.aqu;

import dungeonCrawler.data.GameStateDTO;

import java.io.Serializable;

public interface IDataFacade extends Serializable
{
    IHighScore getHighScore();
    void save(GameStateDTO gameStateDTO, String filename);
    GameStateDTO load(GameStateDTO gameStateDTO, String filename);
    //    IMap getMap();
//    IPlayer getPlayer();

}
