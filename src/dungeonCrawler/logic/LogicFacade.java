package dungeonCrawler.logic;

import dungeonCrawler.aqu.*;
import dungeonCrawler.data.GameStateDTO;

import java.io.Serializable;
import java.util.Date;

/**
 * Used to gain access to the logic layer
 * @author Peter Jonathan, Joakim, Linea, Frederik, Simon og Brian.
 */
public class LogicFacade implements dungeonCrawler.aqu.ILogicFacade, Serializable
{
    IDataFacade data;
    Player player;
    Map map;
    TimeTracker timeTracker;
    GameText gameText;
    IHighScore highScore;

    /**
     * Constructor for LogicFacade
     */
    public LogicFacade()
    {

    }

    /**
     * Method that gains access to the data layer
     * @param dataLayer
     */
    @Override
    public void injectData(IDataFacade dataLayer)
    {
        data = dataLayer;
    }

    /**
     * Method that creates an instance of IPlayer
     * @param name
     * @return IPlayer
     */
    @Override
    public IPlayer createPlayerInstance(String name)
    {
        this.player = new Player(name);
        return player;
    }

    /**
     * Getter method for IPlayer
     * @return IPlayer
     */
    @Override
    public IPlayer getPlayer()
    {
        return player;
    }

    /**
     * Method that creates an instance of IMap
     * @return IMap
     */
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

    /**
     * Getter method for IMap
     * @return IMap
     */
    @Override
    public IMap getMap()
    {
        return map;
    }

    /**
     * Method to create an instance of a new Battle, in the current room.
     * @param index
     * @return IBattle
     */
    @Override
    public IBattle doBattle(int index)
    {
        return new Battle(player, (Monster) map.getCurrentRoom().getContent(index));
    }

    /**
     * Method to create an instance of a new Battle, in any room
     * Used for final battle only
     * @param monster
     * @return IBattle
     */
    @Override
    public IBattle doBattle(IMonster monster)
    {
        return new Battle(player, (Monster) monster);
    }

    /**
     * Getter method for TimeTracker
     * @param date
     * @return ITimeTracker
     */
    @Override
    public ITimeTracker getTimeTracker(Date date)
    {
        timeTracker = new TimeTracker(date, player);
        return timeTracker;
    }

    /**
     * Method to get Lucifer the final boss
     * @return IMonster
     */
    @Override
    public IMonster getLucifer()
    {
        return MonsterEnum.getLucifer();
    }

    /**
     * Getter method for HighScore
     * @return IHighScore
     */
    @Override
    public IHighScore getHighScore()
    {
        highScore = data.getHighScore();
        return highScore;
    }

    /**
     * Getter method for GameText
     * @return
     */
    public GameText getGameText()
    {
        gameText = new GameText();
        return gameText;
    }

    /**
     * Method to gain access to GameText
     */
    public void injectGameText()
    {
        gameText.injectVariables(player, map);
    }

    // TODO Skal heller ikke være her
    public IRoom getCurrentRoom()
    {
        return map.getCurrentRoom();
    }

    // Skal heller ikke være her. Kun dem du sender, modtager og instantiere referencer i Facaden.
    public void saveItemToInventory(int inventoryIndex, int contensIndex)
    {
        Chest chest = (Chest) (map.getCurrentRoom().getContent(contensIndex));
        player.getInventory().addItem(chest.getItem(), inventoryIndex);
    }

    //TODO Skal ikke være her
    // Kun fåes igennem vores inevntory, igen grund til metoder i facade klassen. 
    @Override
    public int getNumberOfAvailablePotions()
    {
        return player.getInventory().potionArrayList().size();
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
    public void useItem(int index)
    {
        Item item = (Item) player.getInventory().getItem(index);
    }

    /**
     * Setter method for difficulty level in GameSettings
     * @param i
     */
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

    /**
     * Getter method for difficulty level in GameSettings
     * @return
     */
    @Override
    public int getDifficultyLevel()
    {
        return GameSettings.getDifficultyLevel();
    }

    /**
     * Method to save the current state of the game
     * @param index
     */
    @Override
    public void saveGame(int index)
    {
        player.setTime(timeTracker.calculateRemainingTime());

        GameStateDTO stateDTO = new GameStateDTO(player, map);
        data.save(stateDTO, "SaveGame" + index + ".sav");

//        GameHandler.saveGame(stateDTO, "fileName.sav");
    }

    /**
     * Method to load a saved game state
     * @param index
     */
    @Override
    public void loadGame(int index)
    {
        GameStateDTO stateDTO = new GameStateDTO(player, map);

        stateDTO = data.load(stateDTO,"SaveGame" + index + ".sav");

        player = (Player)stateDTO.getPlayer();
        map = (Map)stateDTO.getMap();
    }


}
