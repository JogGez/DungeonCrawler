package worldofzuul;

/**
 * The type Command. Used to hold and evaluate user input
 */
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
    public CommandWord getCommandWord()
    {
        return commandWord;
    }

    /**
     * Gets second word.
     *
     * @return the second word
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    /**
     * Check if command is valid.
     *
     * @return the boolean
     */
    public boolean isUnknown()
    {
        return (commandWord == CommandWord.UNKNOWN);
    }

    /**
     * Check if command has a second word.
     *
     * @return the boolean
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}

