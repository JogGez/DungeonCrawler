package worldofzuul.logic;

/**
 * Inherit from the Item class
 * @author 
 */
public class Potion extends Item
{
    //Int variable healthRecovery 
    private int healthRecovery;

    
    /**
     * 
     * @param name
     * @param description
     * @param ascii
     * @param healthRecovery 
     */
    public Potion(String name, String description, String ascii, int healthRecovery)
    {

        this.setName(name);
        this.setDescription(description);
        this.setAscii(ascii);
        this.healthRecovery = healthRecovery;
    }

    /**
     * Getter method for HealthRecovery
     * @return int
     */
    public int getHealthRecovery()
    {
        return healthRecovery;
    }
}
