package dungeonCrawler.aqu;

import dungeonCrawler.logic.GameText;

import java.io.Serializable;
import java.util.Date;

public interface ILogicFacade  extends Serializable
{
    void injectData(IDataFacade dataLayer);

    IPlayer createPlayerInstance(String name);
    IPlayer getPlayer();

    IMap createMapInstance();
    IMap getMap();

    IBattle doBattle(int index);
    IBattle doBattle(IMonster monster);

    ITimeTracker getTimeTracker(Date date);

    IMonster getLucifer();

    IHighScore getHighScore();

//    ArrayList<IMerchant> merchantList();

    GameText getGameText();

    void injectGameText();

    IRoom getCurrentRoom();

    void saveItemToInventory(int inventoryIndex, int contensIndex);

    int getNumberOfAvailablePotions();

    void usePotion(int index);

    int getNumberOfAvailableKeys();

    void useKey(int index);

    void useItem(int index);

    void setDifficultyLevel(int i);
    
    void saveGame();

    void loadGame();
}
