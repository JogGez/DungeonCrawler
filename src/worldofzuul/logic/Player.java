/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.logic;

import java.awt.*;

/**
 * The Player class
 *
 * @author Jonathan
 */
public class Player {
  
    /**
    We aren't making the system print out a line, because later on it would
    result in a error. Therefore we are going to create a seperate class under game => play.
    this method under game, calls for our parser (which is our primary scanner class) that gets the users next input, and makes it a string that is used for the name.
    */
    private String name;
    private int health;
    private int score;
    private Weapon currentWeapon;
    private Inventory inventory;
    private Point location;
    private Point lastLocation;

    /**
     * To use inventory
     * Method
     * @return inventory
     */
    public Inventory getInventory()
    {
        return inventory;
    }
    
    /**
     * To use players current location
     * Method
     * @return the current location
     */
    public Point getLocation()
    {
        return location;
    }
    /**
     * To use players last location, further use for a back command
     * Method
     * @return the last location
     */
    public Point getLastLocation()
    {
        return lastLocation;
    }
    
    /**
     * Setter for location
     * Method
     * @param location 
     */
    public void setLocation(Point location)
    {
        this.lastLocation = this.location;
        this.location = location;
    }


    /**
     * Constructor for player
     * 
     * @param name 
     */
    public Player(String name)
    {
        this.name = name;
        this.health = 2000;
        this.score = 0;
        this.currentWeapon = new Weapon("Hands","Puny hands, not good for fighting :(","",1,0 );
        this.location = new Point(0,0);
        this.lastLocation = new Point(0,0);
        this.inventory = new Inventory(3);
        
    }

    /**
     * Gets name.
     * Method
     * @return the name
     */
    public String getName() 
    {
        return name;
    }
    

    /**
     * Gets health.
     * Method
     * @return the health
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * Sets health.
     * Method
     * @param health the health
     */
    public void setHealth(int health)
    {
        this.health = health;
    }


    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Sets score.
     * Method
     * @param score the score
     */
    public void setScore(int score)
    {
        this.score = score;
    }

    /**
     * Sets current weapon
     * Method
     * @param weapon 
     */
    public void setCurrentWeapon(Weapon weapon)
    {
        this.currentWeapon = weapon;
    }
    
    /**
     * Gets current weapon
     * Method
     * @return the current weapon
     */
    public Weapon getCurrentWeapon()
    {
        return this.currentWeapon;
    }
}
