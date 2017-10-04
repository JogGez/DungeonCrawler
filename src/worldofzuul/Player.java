/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

/**
 *
 * @author Jonathan
 */
public class Player {
    
    // We aren't making the system print out a line, because later on it would
    // result in a error. Therefore we are going to create a seperate class under game => play.
    // this method under game, calls for our parser (which is our primary scanner class) that gets the users next input, and makes it a string that is used for the name. 
    private String name;
    private int health;
    private int score;
    private boolean isDead = false;
    // 
    public String getName() 
    {
        return name;
    }
    // Used in game class under play method to set the name, which is made in parser (our scanner)
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
        int hp = 100;
        return hp;
    }

    public int getScore() 
    {
        return score;
    }

    public void setScore(int score) 
    {
        this.score = score;
    }
    
    // Might change to none void, but atm I don't need to return it.
    // Most of the things underneath can be moved into a different class
    // all the way to after isDead part.
    public double attack(Player pc)
    {
        // Calculate damage dealt.
        //Should most likly be put under default weapons (Hands/Swords/Mace etc.)
        // Manipulation of the damage
        // Makes the damage be a bit more random
        double damage = Math.round((Math.random() * (15 - 5)));
        pc.takeDamage(damage);
        return damage;
    }
    // I thought about making it a none void, but the example i saw, did it with void.
    // Might change.
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
