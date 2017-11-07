package worldofzuul.logic;

/**
 *
 * @author Linea Hoffmann
 */
public abstract class Item
{
    private String name;
    private String description;
    private String ascii;

    
    public static Item getRandomItem()
    {
        int randomItem = (int)(Math.random()*100);

        if (randomItem < 60)
        {
            return WeaponEnum.getRandomWeapon();
        }
        else
        {
            return PotionEnum.getRandomPotion();
        }

    }
    
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getAscii()
    {
        return ascii;
    }

    public void setAscii(String ascii)
    {
        this.ascii = ascii;
    }
    
    
}
