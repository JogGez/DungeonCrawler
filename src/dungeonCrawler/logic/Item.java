package dungeonCrawler.logic;

import dungeonCrawler.aqu.IItem;

import java.io.Serializable;

/**
 * Item abstract class
 * Used as an abstract class to make, some objects have things incommon
 * so we can store them all up.
 * implements IItem Serializable
 * @author Peter Jonathan, Joakim, Linea, Frederik, Simon og Brian
 */
abstract class Item implements IItem, Serializable
{
    private String name;
    private String description;
    private String ascii;


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
     * Setter method for name
     * @param name
     */
    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Getter method for description
     * @return String
     */
    @Override
    public String getDescription()
    {
        return description;
    }

    /**
     * Setter method for description
     * @param description
     */
    @Override
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Getter method for ascii
     * @return
     */
    @Override
    public String getAscii()
    {
        return ascii;
    }

    /**
     * Setter method for ascii
     * @param ascii
     */
    @Override
    public void setAscii(String ascii)
    {
        this.ascii = ascii;
    }

    /**
     * Getter method for a random item
     * @return Item
     */
    static Item getRandomItem()
    {
        int randomItem = (int)(Math.random()*100);

        if (randomItem < GameSettings.getChanceOfWeapon())
        {
            return WeaponEnum.getRandomWeapon();
        }
        else if (randomItem < GameSettings.getChanceOfWeapon() + GameSettings.getChanceOfHealthPotion())
        {
            return PotionEnum.getRandomHealthPotion();
        }
        else if (randomItem < GameSettings.getChanceOfWeapon() + GameSettings.getChanceOfHealthPotion() + GameSettings.getChanceOfTimePotion())
        {
            return PotionEnum.getRandomTimePotion();
        }
        else if (randomItem < GameSettings.getChanceOfWeapon() + GameSettings.getChanceOfHealthPotion() + GameSettings.getChanceOfTimePotion() + GameSettings.getChanceOfKey())
        {
            return KeyEnum.getRandomKey();
        }
        else if (randomItem < GameSettings.getChanceOfWeapon() + GameSettings.getChanceOfHealthPotion() +
                GameSettings.getChanceOfTimePotion() + GameSettings.getChanceOfKey() + GameSettings.getChanceOfSpecial())
        {
            return SpecialEnum.getRandomSpecial();
        }
        else
        {
            return null;
        }

    }
}
