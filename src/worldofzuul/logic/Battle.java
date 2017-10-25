package worldofzuul.logic;

/**
 * Battle class, where battles happens
 * @author Linea Hoffmann
 */
public class Battle
{
    Player player;
    Monster monster;
    boolean isBattleOver;
    
    public Battle(Player player, Monster monster)
    {
        this.player = player;
        this.monster = monster;
        this.isBattleOver = false; 
        
    }

    public boolean getIsBattleOver()
    {
        return isBattleOver;
    }
    
    public String fight()
    {
        int playerHit = (int)(Math.random()*20+10);
        int monsterHit = (int)(Math.random()*20+10);

        monster.setHealth(monster.getHealth() - playerHit);
        player.setHealth(player.getHealth() - monsterHit);

        if (player.getHealth() <= 0)
        {
            isBattleOver = true;
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
