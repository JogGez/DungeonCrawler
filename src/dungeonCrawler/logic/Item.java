package dungeonCrawler.logic;

import dungeonCrawler.aqu.IItem;
import java.io.Serializable;

/**
 *
 * @author Linea Hoffmann
 */
abstract class Item implements IItem, Serializable
{
    private String name;
    private String description;
    private String ascii;


    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public String getAscii()
    {
        return ascii;
    }

    @Override
    public void setAscii(String ascii)
    {
        this.ascii = ascii;
    }
    
    static Item getRandomItem()
    {
        int randomItem = (int)(Math.random()*100);

        if (randomItem < GameSettings.getChanceOfWeapon())
        {
            return WeaponEnum.getRandomWeapon();
        }
        else if (randomItem < GameSettings.getChanceOfWeapon() + GameSettings.getChanceOfHealthPotion())
        {
            return PotionEnum.getRandomPotion();
        }
        else if (randomItem < GameSettings.getChanceOfWeapon() + GameSettings.getChanceOfHealthPotion() + GameSettings.getChanceOfTimePotion())
        {
            return PotionEnum.getRandomPotion();
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
