package dungeonCrawler.logic;

import dungeonCrawler.aqu.IWeapon;
import java.io.Serializable;

/**
 * Weapon class
 * Used for the player to equip and enhance his power to fight monster/NPC's
 * extends Item
 * implements IWeapon, Serializable
 * @author Peter Jonathan, Joakim, Linea, Frederik, Simon og Brian.
 */
class Weapon extends Item implements IWeapon, Serializable
{
    private int power;
    private int multiplier;

    /**
     * Constructor for Weapon
     * @param name
     * @param description
     * @param ascii
     * @param power
     * @param multiplier
     */
    public Weapon(String name, String description, String ascii, int power, int multiplier)
    {
        //Weapon inheriet from Item.
        //(this.name = name; (we dont do this)because our properties inheriet from Item, that's why we don't need to allocate them.
        this.setName(name);
        this.setDescription(description);
        this.setAscii(ascii);
        this.power = power;
        this.multiplier = multiplier;
    }

    /**
     * Getter method for weapon power
     * @return int
     */
    @Override
    public int getPower()
    {
        return power;
    }

    /**
     * Getter method for weapon multiplier
     * @return int
     */
    @Override
    public int getMultiplier()
    {
        return multiplier;
    }
}
