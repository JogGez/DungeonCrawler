package worldofzuul.logic;

public class Weapon extends Item
{

    private int power;
    private int multiplier;

    public Weapon(String name, String description, String ascii, int power, int multiplier)
    {
        this.name = name;
        this.description = description;
        this.ascii = ascii;
        this.power = power;
        this.multiplier = multiplier;
    }

    public int getPower()
    {
        return power;
    }

    public int getMultiplier()
    {
        return multiplier;
    }
}
