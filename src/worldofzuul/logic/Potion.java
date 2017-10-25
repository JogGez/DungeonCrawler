package worldofzuul.logic;

public class Potion extends Item
{

    private int healthRecovery;

    public Potion(String name, String description, String ascii, int healthRecovery)
    {

        this.name = name;
        this.description = description;
        this.ascii = ascii;
        this.healthRecovery = healthRecovery;
    }


    public int getHealthRecovery()
    {
        return healthRecovery;
    }
}
