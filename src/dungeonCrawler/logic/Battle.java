package dungeonCrawler.logic;

import dungeonCrawler.aqu.IBattle;

import java.io.Serializable;

/**
 * Battle class
 * The class where the methods for battles plays out.
 * Implements IBattle and Serializable. 
 * @author Peter Jonathan, Joakim, Linea, Frederik, Simon og Brian
 */
class Battle implements IBattle, Serializable
{
    // Declare datafields, with no value, with private access modifieres
    // Reference datatypes of the objects. 
    private Player player;
    private Monster monster;
    private boolean isBattleOver; // Allocates memory storage, primitive datatype

    
   
    /**
     * Constructor: set values for player, and monster, and isBattleOver.
     * @param player
     * @param monster 
     */
    public Battle(Player player, Monster monster)
    {
        // Initializing (gives value to) private fields (constructor parameter)
        this.player = player;
        this.monster = monster;
        this.isBattleOver = false;
    }
    
    
    /**
     * Getter method for isBattleOver
     * @return boolean
     */
    @Override
    public boolean getIsBattleOver()
    {
        return isBattleOver;
    }

    
    //Method start, returns a String

    /**
     * Starts a battle, sets boolean IsBattleOver when health reaches 0 for either player/monster
     * @return String
     */
    @Override
    public String start()
    {
        // (int) in pararentens because it would only be Math.random = 0, which is = 0.
        // We are getting the players current weapons power to add to our calculation, same with the monster.
        int playerHit = player.getWeapon().getPower() + ((int)(Math.random()*20+10)); // caster because Math. is always a double.
        int monsterHit = monster.getPower() + ((int)(Math.random()*10+5));

        //Computes the player and monster get.health ( how much the player/monster has, after the Hit)
        // set.Health calls from Player and Monster class
        player.setHealth(player.getHealth() - monsterHit);
        monster.setHealth(monster.getHealth() - playerHit);

        
        //If, else if that use the get.Health, from the calculation above
        if (player.getHealth() <= 0)
        {
            isBattleOver = true;// sets the value + (While loop in game, that would continue forever, if not set.)
            return "You have died...";
        }
        else if (monster.getHealth() <= 0)
        {
            isBattleOver = true;
            return "You beat the Monster ... MIGHTY WARRIOR :D ...";
        }

        return "Monster lost " + playerHit + "hp and currently has " + monster.getHealth() + "hp left \n" +
                "You lost " + monsterHit + "hp and currently have " + player.getHealth() + "hp left \n";
    }
}
