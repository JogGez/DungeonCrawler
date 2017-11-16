package dungeonCrawler.presentation;

/**
 * The type Command. Used to hold and evaluate user input
 */
public class Command
{
    // Declaring objects for later instantiation
    // TODO Strings to CommandWord, and add it to the Enum.
    private CommandWord commandWord;
    private String secondWord;
    private String thirdWord;

    /**
     * Instantiates a new Command.
     *
     * @param firstWord the command word
     * @param secondWord  the second word
     */
    // TODO make the strings into CommandWord.
    public Command(CommandWord firstWord, String secondWord, String thirdWord)
    {
        this.commandWord = firstWord;
        this.secondWord = secondWord;
        this.thirdWord = thirdWord;
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
     * Gets third word.
     *
     * @return the third word
     */
    public String getThirdWord()
    {
        return thirdWord;
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

    /**
     * Check if command has a third word.
     *
     * @return the boolean
     */
    public boolean hasThirdWord()
    {
        return (thirdWord != null);
    }
}

