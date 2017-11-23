package dungeonCrawler.aqu;

import dungeonCrawler.logic.Guide;
import dungeonCrawler.logic.Player;
import java.util.ArrayList;
import java.util.Date;

public interface ILogicFacade
{
    void injectData(IDataFacade dataLayer);

    void createPlayerInstance(String name);
    IPlayer getPlayerDTO();

    void createMapInstance();
    IMap getMapDTO();

    IBattle doBattle(IMonster monster);

    ITimeTracker getTimeTracker(Date date);

    IMonster getLucifer();

    IHighScore getHighScore();

    ArrayList<IGuide> guideList();
    
    boolean guideAndPlayerSameRoom(IGuide guide, IPlayer player);
    
    


}
