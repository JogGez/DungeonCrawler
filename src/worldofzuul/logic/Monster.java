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
    private boolean isDead;

    public Monster(String name, String description, String ascii, int health, int power)
    {
        this.name = name;
        this.description = description;
        this.ascii = ascii;
        this.health = health;
        this.power = power;
        this.isDead = false;
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


    /**
     * Power double.
     *
     * @param pc the pc
     * @return the double
     */
// Gives the "power" to attack the player class
        public double power(Player pc)
    {
        // Calculate damage dealt.
        //Should most likly be put under default weapons (Hands/Swords/Mace etc.)
        // Manipulation of the damage
        // Makes the damage be a bit more random
        double damage = Math.round((Math.random() * (15 - 5)));
        pc.takeDamage(damage);
        return damage;
    }

    /**
     * Take damage.
     *
     * @param damage the damage
     */
    public void takeDamage(double damage)
    {
        //Checks damage taken, if 0 or less, return isDead
        if (health - damage <= 0)
        {
        health = 0;
        isDead = true;
        }
        else
        {
            // Otherwise minus health with damage taken
            health -= damage;
        }
    }

    /**
     * Is dead boolean.
     *
     * @return the boolean
     */
// State of death
    public boolean isDead()
    {
        return isDead;
    }
    
    
}
