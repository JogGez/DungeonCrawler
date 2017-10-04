package worldofzuul;

// Enum of all the available commands in the game
public enum CommandWord
{
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), HEALTH("hp"), ATTACK("attack");
    
    private String commandString;
    
    // Setter for the private String commandString
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    // Getter for the private String commandString
    public String toString()
    {
        return commandString;
    }
}
