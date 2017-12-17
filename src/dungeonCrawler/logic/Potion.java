package dungeonCrawler.logic;

import dungeonCrawler.aqu.IPotion;

import java.io.Serializable;

/**
 * Potion class
 * Inherit from Item, used to restore health points and time points for the player.
 * implements IPotion, Serializable.
 * @author Peter Jonathan, Joakim, Linea, Frederik, Simon og Brian
 */
class Potion extends Item implements IPotion, Serializable
{
    //Int variable healthRecovery 
    private int healthRecovery;
    private int timeRecovery;

    
    /**
     * Constructor for Potion
     * @param name Sets the name to use.
     * @param description Sets the discription to use.
     * @param ascii Sets the ascii to use.
     * @param healthRecovery  Sets the healthRecovery to use.
     */
    public Potion(String name, String description, String ascii, int healthRecovery, int timeRecovery)
    {

        this.setName(name);
        this.setDescription(description);
        this.setAscii(ascii);
        this.healthRecovery = healthRecovery;
        this.timeRecovery = timeRecovery;
    }
    

    /**
     * Getter method for potion HealthRecovery
     * @return int
     */
    @Override
    public int getHealthRecovery()
    {
        return healthRecovery;
    }

    /**
     * Getter method for potion TimeRecovery
     * @return int
     */
    @Override
    public int getTimeRecovery()
    {
        return timeRecovery;
    }
    
}
