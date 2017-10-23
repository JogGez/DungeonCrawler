package worldofzuul.logic;

/**
 * Battle class, where battles happens
 * @author Linea Hoffmann
 */
public class Battle
{
    Player player;
    RoomContent monster;//laves om senere
    boolean isBattleOver;
    
    public Battle(Player player, RoomContent monster)
    {
        this.player = player;
        this.monster = monster;
        this.isBattleOver = false; 
        
    }

    public boolean getIsBattleOver()
    {
        return isBattleOver;
    }
    
    public void fight()
    {
        
        
    }
    
 
}
