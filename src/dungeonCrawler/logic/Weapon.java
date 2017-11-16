package dungeonCrawler.logic;

class Weapon extends Item implements dungeonCrawler.aqu.IWeapon
{

    private int power;
    private int multiplier;

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

    @Override
    public int getPower()
    {
        return power;
    }

    @Override
    public int getMultiplier()
    {
        return multiplier;
    }
}
