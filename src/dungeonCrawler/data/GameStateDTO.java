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

    /**
     * Constructor for GameStateDTO
     * @param player
     * @param map
     */
    public GameStateDTO(IPlayer player, IMap map)
    {
        this.player = player;
        this.map = map;
    }

    /**
     * Getter method for GameStateDTO player
     * @return IPlayer
     */
    public IPlayer getPlayer()
    {
        return player;
    }

    /**
     * Getter method for GameStateDTO map
     * @return IMap
     */
    public IMap getMap()
    {
        return map;
    }
}
