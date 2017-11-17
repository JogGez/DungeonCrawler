package dungeonCrawler.logic;

import dungeonCrawler.aqu.*;

import java.util.ArrayList;
import java.util.Date;

public class LogicFacade implements dungeonCrawler.aqu.ILogicFacade
{
    IDataFacade data;
    Player player;
    Map map;
    IHighScore highScore;

    @Override
    public void injectData(IDataFacade dataLayer)
    {
        data = dataLayer;
    }

    @Override
    public void createPlayerInstance(String name)
    {
        this.player = new Player(name);
    }

    @Override
    public IPlayer getPlayerDTO()
    {
        return player;
    }

    @Override
    public void createMapInstance()
    {
        this.map = new Map();
    }

    @Override
    public IMap getMapDTO()
    {
        return map;
    }

    @Override
    public IBattle doBattle(IMonster monster)
    {
        IBattle battle = new Battle(player, monster);
        return battle;
    }

    @Override
    public ITimeTracker getTimeTracker(Date date)
    {
        TimeTracker time = new TimeTracker(date, player);
        return time;
    }

    @Override
    public IMonster getLucifer()
    {
        return MonsterEnum.LUCIFER.getMonster();
    }

    @Override
    public IHighScore getHighScore()
    {
        highScore = data.getHighScore();
        return highScore;
    }

    @Override
    public ArrayList<IGuide> guideList()
    {
        ArrayList<? extends IGuide> guides = map.getGuideList();
        return (ArrayList<IGuide>) guides;
    }


}
