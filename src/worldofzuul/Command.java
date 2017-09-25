package worldofzuul;

// Class used to hold the user input
public class Command
{
    // Declaring objects for later instantiation
    private CommandWord commandWord;
    private String secondWord;

    // Constructor that sets the commandWord and the secondWord
    public Command(CommandWord commandWord, String secondWord)
    {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    // Getter for commandWord
    public CommandWord getCommandWord()
    {
        return commandWord;
    }
    // Getter for secondWord
    public String getSecondWord()
    {
        return secondWord;
    }

    // Returns a boolean value whether or not the command is valid
    public boolean isUnknown()
    {
        return (commandWord == CommandWord.UNKNOWN);
    }

    // Returns a boolean value whether or not the command contains a second word
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}

