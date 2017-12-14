package dungeonCrawler.data;

import dungeonCrawler.aqu.IMap;
import dungeonCrawler.aqu.IPlayer;
import java.io.Serializable;

/**
 * GameStateDTO Class
 *
 * This Class creates a Data Transfer Object of current gamestate
 *
 * Implements Serializable
 * @author Peter Jonathan, Joakim, Linea, Frederik, Simon og Brian
 */
public class GameStateDTO implements Serializable
{
    private IPlayer player;
    private IMap map;

    public GameStateDTO(IPlayer player, IMap map)
    {
        this.player = player;
        this.map = map;
    }

    public IPlayer getPlayer()
    {
        return player;
    }

    public IMap getMap()
    {
        return map;
    }
    
    
    
}
