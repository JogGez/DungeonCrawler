package worldofzuul.logic;

/**
 * Battle class, where battles happens
 * @author Linea Hoffmann
 */
public class Battle
{
    // Declare datafields, with no value, with private access modifieres
    // Reference datatypes of the objects. 
    private Player player;
    private Monster monster;
    private boolean isBattleOver; // Allocates memory storage, primitive datatype

    
    //Constructor: set values for player, and mosnter, and isBattleOver.
    public Battle(Player player, Monster monster)
    {
        // Initializing (gives value to) private fields (contrsuctor parameter) 
        this.player = player;
        this.monster = monster;
        this.isBattleOver = false;


    }
    
    // Getter method for isBattleOver
    public boolean getIsBattleOver()
    {
        return isBattleOver;
    }

    //TODO Kig på metoden. ifh til retunering af String.
    //Method start, returns a String
    public String start()
    {
        // (int) in pararentens because it would only be Math.random = 0, which is = 0.
        // We are getting the players current weapons power to add to our calculation, same with the monster.
        int playerHit = player.getCurrentWeapon().getPower() + ((int)(Math.random()*20+10)); // caster because Math. is always a double.
        int monsterHit = monster.getPower() + ((int)(Math.random()*10+5));

        //Computes the player and monster get.health ( how much the player/monster has, after the Hit)
        // set.Health calls from Player and Monster class
        monster.setHealth(monster.getHealth() - playerHit);
        player.setHealth(player.getHealth() - monsterHit);
        
        //If, else if that use the get.Health, from the calculation above
        // 
        if (player.getHealth() <= 0)
        {
            isBattleOver = true;// sets the value + (While loop in game, that would continue forever, if not set.)
            return "You have died...";
        }
        else if (monster.getHealth() <= 0)
        {
            isBattleOver = true;
            return "You beat the Monster ... MIGHT WARRIOR :D ...";
        }

        return "Monster lost " + playerHit + "hp and currently has " + monster.getHealth() + "hp left \n" +
                "You lost " + monsterHit + "hp and currently have " + player.getHealth() + "hp left \n";
    }
    
 
}
