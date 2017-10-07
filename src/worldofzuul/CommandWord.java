package worldofzuul;

/**
 * The enum Command word.
 */
// Enum of all the available commands in the game
public enum CommandWord
{
    /**
     * Go command word.
     */
    GO("go"), /**
     * Quit command word.
     */
    QUIT("quit"), /**
     * Help command word.
     */
    HELP("help"), /**
     * Unknown command word.
     */
    UNKNOWN("?"), /**
     * Health command word.
     */
    HEALTH("hp"), /**
     * Attack command word.
     */
    ATTACK("attack");
    
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
