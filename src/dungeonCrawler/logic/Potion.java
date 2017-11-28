package dungeonCrawler.logic;

import dungeonCrawler.aqu.IPotion;

/**
 * Inherit from the Item class
 * @author 
 */
class Potion extends Item implements IPotion
{
    //Int variable healthRecovery 
    private int healthRecovery;
    private int timeRecovery;

    
    /**
     * 
     * @param name
     * @param description
     * @param ascii
     * @param healthRecovery 
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
     * Getter method for HealthRecovery
     * @return int
     */
    @Override
    public int getHealthRecovery()
    {
        return healthRecovery;
    }
    
    @Override
    public int getTimeRecovery()
    {
        return timeRecovery;
    }
    
}
