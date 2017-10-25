/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.logic;

/**
 * The type Monster.
 *
 * @author Computer
 */
//Monster Class, nedarvning af RoomContent
public class Monster extends RoomContent
{
    private String name;
    private String description;
    private String ascii;
    private int health;
    private int power;

    public Monster(String name, String description, String ascii, int health, int power)
    {
        this.name = name;
        this.description = description;
        this.ascii = ascii;
        this.health = health;
        this.power = power;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets health.
     *
     * @return the health
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * Sets health.
     *
     * @param health the health
     */
    public void setHealth(int health)
    {
        this.health = health;
    }


    /**
     * Gets power.
     *
     * @return the power
     */
    public int getPower()
    {
        return power;
    }

    /**
     * Sets power.
     *
     * @param power the power
     */
    public void setPower(int power)
    {
        this.power = power;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }


    public String getAscii()
    {
        return this.ascii;
    }
}
