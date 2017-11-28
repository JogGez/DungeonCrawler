package dungeonCrawler.aqu;

import dungeonCrawler.data.GameState;

public interface IDataFacade
{
    IHighScore getHighScore();
    void saveGame(IPlayer player, IMap map, String fileName);
    void loadGame(String fileName);
    IMap getMap();
    IPlayer getPlayer();
}
