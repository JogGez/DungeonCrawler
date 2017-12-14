package dungeonCrawler.logic;

import dungeonCrawler.aqu.*;
import dungeonCrawler.data.GameStateDTO;

import java.io.Serializable;
import java.util.Date;

public class LogicFacade implements dungeonCrawler.aqu.ILogicFacade, Serializable
{
    IDataFacade data;
    Player player;
    Map map;
    TimeTracker timeTracker;
    GameText gameText;
    IHighScore highScore;

    public LogicFacade()
    {

    }

    @Override
    public void injectData(IDataFacade dataLayer)
    {
        data = dataLayer;
    }

    @Override
    public IPlayer createPlayerInstance(String name)
    {
        this.player = new Player(name);
        return player;
    }

    @Override
    public IPlayer getPlayer()
    {
        return player;
    }

    @Override
    public IMap createMapInstance()
    {
        if (player != null)
        {
            this.map = new Map(player);
            return map;
        } else
        {
            return null;
        }
    }

    @Override
    public IMap getMap()
    {
        return map;
    }

    @Override
    public IBattle doBattle(int index)
    {
        return new Battle(player, (Monster) map.getCurrentRoom().getContent(index));
    }

    @Override
    public IBattle doBattle(IMonster monster)
    {
        return new Battle(player, (Monster) monster);
    }

    @Override
    public ITimeTracker getTimeTracker(Date date)
    {
        timeTracker = new TimeTracker(date, player);
        return timeTracker;
    }

    @Override
    public IMonster getLucifer()
    {
        return MonsterEnum.getLucifer();
    }

    @Override
    public IHighScore getHighScore()
    {
        highScore = data.getHighScore();
        return highScore;
    }

    public GameText getGameText()
    {
        gameText = new GameText();
        return gameText;
    }

    public void injectGameText()
    {
        gameText.injectVariables(player, map);
    }

    // Skal heller ikke være her. Kun dem du sender, modtager og instantiere referencer i Facaden. 
    public void saveItemToInventory(int inventoryIndex, int contensIndex)
    {
        Chest chest = (Chest) (map.getCurrentRoom().getContent(contensIndex));
        player.getInventory().addItem(chest.getItem(), inventoryIndex);
    }

    // Bliver ikke brugt ?? 
    @Override
    public void usePotion(int index)
    {
        player.setHealth(player.getHealth() + player.getInventory().potionArrayList().get(index).getHealthRecovery());
        player.setTime(player.getTime() + player.getInventory().potionArrayList().get(index).getTimeRecovery());
        player.getInventory().removeItem(player.getInventory().getItemIndex((IItem) player.getInventory().potionArrayList().get(index)));
    }

    //Flyttet ud til de klasser der har betydning. (i inventory klassen)
    @Override
    public int getNumberOfAvailableKeys()
    {
        return player.getInventory().keyArrayList().size();
    }

    @Override
    public void useKey(int index)
    {
        player.getInventory().removeItem(player.getInventory().getItemIndex((IItem) player.getInventory().keyArrayList().get(index)));
    }

    @Override
    public void setDifficultyLevel(int i)
    {
        switch (i)
        {
            case 1:
                GameSettings.setEasyDifficulty();
                break;
            case 2:
                GameSettings.setNormalDifficulty();
                break;
            case 3:
                GameSettings.setHardDifficulty();
                break;
        }
    }

    @Override
    public int getDifficultyLevel()
    {
        return GameSettings.getDifficultyLevel();
    }

    @Override
    public void saveGame(int index)
    {
        player.setTime(timeTracker.calculateRemainingTime());

        GameStateDTO stateDTO = new GameStateDTO(player, map);
        data.save(stateDTO, "SaveGame" + index + ".sav");

//        GameHandler.saveGame(stateDTO, "fileName.sav");
    }

    @Override
    public void loadGame(int index)
    {
        GameStateDTO stateDTO = new GameStateDTO(player, map);

        stateDTO = data.load(stateDTO,"SaveGame" + index + ".sav");

        player = (Player)stateDTO.getPlayer();
        map = (Map)stateDTO.getMap();
    }


}
