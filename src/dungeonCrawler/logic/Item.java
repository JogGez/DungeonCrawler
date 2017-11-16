package dungeonCrawler.logic;

/**
 *
 * @author Linea Hoffmann
 */
abstract class Item implements dungeonCrawler.aqu.IItem
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

        if (randomItem < 60)
        {
            return (Item)WeaponEnum.getRandomWeapon();
        }
        else
        {
            return (Item)PotionEnum.getRandomPotion();
        }

    }
}
