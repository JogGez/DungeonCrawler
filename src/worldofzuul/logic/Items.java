package worldofzuul.logic;


public final class Items
{



    private Items()
    {
    }

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
}
