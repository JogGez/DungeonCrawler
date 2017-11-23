package dungeonCrawler.logic;

import dungeonCrawler.aqu.*;

import java.io.Console;
import java.util.ArrayList;
import java.util.Date;

public class LogicFacade implements dungeonCrawler.aqu.ILogicFacade
{
    IDataFacade data;
    Player player;
    Map map;
    GameText gameText;
    Battle battle;
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
    public IPlayer getPlayer()
    {
        return player;
    }

    @Override
    public void createMapInstance()
    {
        if (player != null)
        {
            this.map = new Map(player);
        }
        else
        {

        }
    }

    @Override
    public IMap getMap()
    {
        return map;
    }

    @Override
    public void doBattle(int index)
    {
        battle = new Battle(player, (Monster) map.getCurrentRoom().getContent(index));
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

    @Override
    public boolean guideAndPlayerSameRoom(IGuide guide, IPlayer player)
    {
        return false;
    }

    //TODO 
    boolean guideAndPlayerSameRoom()
    {
        return guideAndPlayerSameRoom();

    }

    public GameText getGameText()
    {
        gameText = new GameText();
        return gameText;
    }

    public void injectGameText()
    {
        gameText.injectVariables(player,map);
    }

    public IRoom getCurrentRoom()
    {
        return map.getCurrentRoom();
    }

    public void saveItemToInventory(int inventoryIndex, int contensIndex)
    {
        Chest chest = (Chest)(map.getCurrentRoom().getContent(contensIndex));
        player.getInventory().addItem(chest.getItem(),inventoryIndex);
    }

    @Override
    public int getNumberOfAvailablePotions()
    {
        return player.getInventory().potionArrayList().size();
    }

    @Override
    public void usePotion(int index)
    {
        player.setHealth(player.getHealth() + player.getInventory().potionArrayList().get(index).getHealthRecovery());
        player.setTime(player.getTime() + player.getInventory().potionArrayList().get(index).getTimeRecovery());
        player.getInventory().removeItem(player.getInventory().getItemIndex(player.getInventory().potionArrayList().get(index)));
    }

    @Override
    public int getNumberOfAvailableKeys()
    {
        return player.getInventory().keyArrayList().size();
    }

    @Override
    public void useKey(int index)
    {
        player.getInventory().removeItem(player.getInventory().getItemIndex(player.getInventory().keyArrayList().get(index)));
    }

    @Override
    public void useItem(int index)
    {
        Item item = player.getInventory().getItem(index);


    }

}
