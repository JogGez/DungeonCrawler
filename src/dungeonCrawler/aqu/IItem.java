package dungeonCrawler.aqu;


import dungeonCrawler.logic.PotionEnum;
import dungeonCrawler.logic.WeaponEnum;

public interface IItem
{
    static IItem getRandomItem()
    {
        int randomItem = (int)(Math.random()*100);

        if (randomItem < 60)
        {
            return (IItem)WeaponEnum.getRandomWeapon();
        }
        else
        {
            return (IItem)PotionEnum.getRandomPotion();
        }
    }

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    String getAscii();

    void setAscii(String ascii);
}
