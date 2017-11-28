/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonCrawler.logic;

import dungeonCrawler.aqu.IInventory;
import dungeonCrawler.aqu.IWeapon;
import dungeonCrawler.aqu.IPlayer;

import java.awt.*;

/**
 * The Player class
 *
 * @author Jonathan
 */
class Player implements IPlayer
{
    /**
    We aren't making the system print out a line, because later on it would
    result in a error. Therefore we are going to create a seperate class under game => play.
    this method under game, calls for our parser (which is our primary scanner class) that gets the users next input, and makes it a string that is used for the name.
    */
    private String name;
    private int health;
    private int score;
    private Weapon weapon;
    private Inventory inventory;
    private Point location;
    private Point lastLocation;
    private int time;

     /**
     * Constructor for player
     * 
     * @param name 
     */
    public Player(String name)
    {
        this.name = name;
        this.health = GameConstants.getPlayerHealth();
        this.score = 0;
        this.weapon = new Weapon("Hands", "Puny hands, not good for fighting :(", "", GameConstants.getPlayerPower(), 0 );
        this.location = new Point(0,0);
        this.lastLocation = new Point(0,0);
        this.inventory = new dungeonCrawler.logic.Inventory(GameConstants.getInventorySize());
        this.time = GameConstants.getPlayerTime();
    }
    
    /**
     * To use inventory
     * Method
     * @return inventory
     */
    @Override
    public IInventory getInventory()
    {
        return inventory;
    }
    
    /**
     * To use players current location
     * Method
     * @return the current location
     */
    @Override
    public Point getLocation()
    {
        return location;
    }
    /**
     * To use players last location, further use for a back command
     * Method
     * @return the last location
     */
    @Override
    public Point getLastLocation()
    {
        return lastLocation;
    }
    
    /**
     * Setter for location
     * Method
     * @param location 
     */
    @Override
    public void setLocation(Point location)
    {
        this.lastLocation = this.location;
        this.location = location;
    }

    /**
     * Gets name.
     * Method
     * @return the name
     */
    @Override
    public String getName()
    {
        return name;
    }

    /**
     * Gets health.
     * Method
     * @return the health
     */
    @Override
    public int getHealth()
    {
        return health;
    }

    /**
     * Sets health.
     * Method
     * @param health the health
     */
    @Override
    public void setHealth(int health)
    {
        this.health = health;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    @Override
    public int getScore()
    {
        return score;
    }

    @Override
    public IWeapon getWeapon()
    {
        return this.weapon;
    }

    /**
     * Sets score.
     * Method
     * @param score the score
     */
    @Override
    public void setScore(int score)
    {
        this.score = score;
    }

    /**
     * Sets current weapon
     * Method
     * @param weapon 
     */
    @Override
    public void setWeapon(IWeapon weapon)
    {
        this.weapon = (Weapon) weapon;
    }

    @Override
    public int getTime()
    {
        return time;
    }

    @Override
    public void setTime(int time) 
    {
        this.time = time;
    }

    @Override
    public int getInventorySize()
    {
        return inventory.getSize();
    }
}
