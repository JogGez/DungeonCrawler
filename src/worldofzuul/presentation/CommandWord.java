package worldofzuul.presentation;

/**
 * Enum of all the available commands in the game.
 */
public enum CommandWord
{
    /**
     * Go command word.
     */
    GO("go"),
    /**
     * Talk command word.
     */
    TALK("talk"),
    /**
     * Open command word.
     */
    OPEN("open"),
    /**
     * Leave command word.
     */
    LEAVE("leave"),
    /**
     * Attack command word.
     */
    ATTACK("attack"),
    /**
     * Show command word.
     */
    SHOW("show"),
    /**
     * Use command word.
     */
    USE("use"),
    /**
     * Quit command word.
     */
    QUIT("quit"),
    /**
     * Exit command word.
     */
    EXIT("exit"),
    /**
     * Help command word.
     */
    HELP("help"),
    /**
     * Unknown command word.
     */
    UNKNOWN("?");
    
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
