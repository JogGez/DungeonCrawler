/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

/**
 *
 * @author Computer
 */
//Monster Class
public class Monster {
    private String name;
    private int health;
    private int power;
    private String description;
    private boolean isDead = false;

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public int getHealth() 
    {
        return health;
    }

    public void setHealth(int health) 
    {
        this.health = health;
    }
    
    public int health()
    {
        int hp = 150;
        return hp;
    }

    public int getPower() 
    {
        return power;
    }

    public void setPower(int power) 
    {
        this.power = power;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }
    
    
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
    
    // State of death
    public boolean isDead()
    {
        return isDead;
    }
    
    
}
