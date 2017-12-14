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
import java.io.Serializable;

/**
 * The Player class
 *
 * @author Peter Jonathan, Joakim, Linea, Frederik, Simon og Brian.
 */
class Player implements IPlayer, Serializable
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
     * @param name 
     */
    public Player(String name)
    {
        this.name = name;
        this.health = GameSettings.getPlayerHealth();
        this.score = 0;
        this.weapon = new Weapon("Hands", "Puny hands, not good for fighting :(", "", GameSettings.getPlayerPower(), 0);
        this.location = new Point(0,0);
        this.lastLocation = new Point(0,0);
        this.inventory = new Inventory(GameSettings.getInventorySize());
        this.time = GameSettings.getPlayerTime();
    }
    
    /**
     * Getter method for players inventory
     * @return IInventory
     */
    @Override
    public IInventory getInventory()
    {
        return inventory;
    }
    
    /**
     * Getter method for players location
     * @return Point
     */
    @Override
    public Point getLocation()
    {
        return location;
    }
    /**
     * Getter method for player last location
     * @return Point
     */
    @Override
    public Point getLastLocation()
    {
        return lastLocation;
    }
    
    /**
     * Setter  method for player location
     * @param location 
     */
    @Override
    public void setLocation(Point location)
    {
        this.lastLocation = this.location;
        this.location = location;
    }

    /**
     * Getter method for player name
     * @return String
     */
    @Override
    public String getName()
    {
        return name;
    }

    /**
     * Getter method for player health
     * @return int
     */
    @Override
    public int getHealth()
    {
        return health;
    }

    /**
     * Setter method for player health
     * @param health
     */
    @Override
    public void setHealth(int health)
    {
        this.health = health;
    }

    /**
     * Getter method for player score
     * @return int
     */
    @Override
    public int getScore()
    {
        return score;
    }

    /**
     * Getter method for player weapon
     * @return IWeapon
     */
    @Override
    public IWeapon getWeapon()
    {
        return this.weapon;
    }

    /**
     * Setter method for player score
     * @param score
     */
    @Override
    public void setScore(int score)
    {
        this.score = score;
    }

    /**
     * Setter method for player weapon
     * @param weapon 
     */
    @Override
    public void setWeapon(IWeapon weapon)
    {
        this.weapon = (Weapon) weapon;
    }

    /**
     * Getter method for player time
     * @return int
     */
    @Override
    public int getTime()
    {
        return time;
    }

    /**
     * Setter method for player time
     * @param time
     */
    @Override
    public void setTime(int time) 
    {
        this.time = time;
    }

    /**
     * Getter method for player inventory
     * @return int
     */
    @Override
    public int getInventorySize()
    {
        return inventory.getSize();
    }

    /**
     * Setter method for player name
     * @param name
     */
    @Override
    public void setName(String name)
    {
        this.name = name;
    }
}
