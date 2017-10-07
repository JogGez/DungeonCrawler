package worldofzuul;

/**
 * The type Command.
 */
// Class used to hold the user input
public class Command
{
    // Declaring objects for later instantiation
    private CommandWord commandWord;
    private String secondWord;

    /**
     * Instantiates a new Command.
     *
     * @param commandWord the command word
     * @param secondWord  the second word
     */
// Constructor that sets the commandWord and the secondWord
    public Command(CommandWord commandWord, String secondWord)
    {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    /**
     * Gets command word.
     *
     * @return the command word
     */
// Getter for commandWord
    public CommandWord getCommandWord()
    {
        return commandWord;
    }

    /**
     * Gets second word.
     *
     * @return the second word
     */
// Getter for secondWord
    public String getSecondWord()
    {
        return secondWord;
    }

    /**
     * Is unknown boolean.
     *
     * @return the boolean
     */
// Returns a boolean value whether or not the command is valid
    public boolean isUnknown()
    {
        return (commandWord == CommandWord.UNKNOWN);
    }

    /**
     * Has second word boolean.
     *
     * @return the boolean
     */
// Returns a boolean value whether or not the command contains a second word
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}

