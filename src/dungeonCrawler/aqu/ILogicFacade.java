package dungeonCrawler.aqu;

import dungeonCrawler.logic.GameText;

import java.util.ArrayList;
import java.util.Date;

public interface ILogicFacade
{
    void injectData(IDataFacade dataLayer);

    void createPlayerInstance(String name);
    IPlayer getPlayer();

    void createMapInstance();
    IMap getMap();

    void doBattle(int index);

    void doBattle(IMonster monster);

    ITimeTracker getTimeTracker(Date date);

    IMonster getLucifer();

    IHighScore getHighScore();

    ArrayList<IGuide> guideList();
    
    boolean guideAndPlayerSameRoom(IGuide guide, IPlayer player);

    GameText getGameText();

    void injectGameText();

    IRoom getCurrentRoom();

    void saveItemToInventory(int inventoryIndex, int contensIndex);

    int getNumberOfAvailablePotions();

    void usePotion(int index);

    int getNumberOfAvailableKeys();

    void useKey(int index);

    void useItem(int index);
}
