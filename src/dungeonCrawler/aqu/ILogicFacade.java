package dungeonCrawler.aqu;

import dungeonCrawler.logic.GameText;

import java.io.Serializable;
import java.util.Date;

/**
 * ILogicFacade interface
 */
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

    GameText getGameText();

    void injectGameText();

    void usePotion(int index);

    int getNumberOfAvailableKeys();

    void useKey(int index);

    void setDifficultyLevel(int i);

    int getDifficultyLevel();
    
    void saveGame(int index);

    void loadGame(int index);
}
