package worldofzuul.presentation;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * The type Command words.
 */
// Class that holds a list of all the available words/commands
public class CommandWords
{
    // Declaring objects for later instantiation
    private LinkedHashMap<String, CommandWord> validCommands;

    /**
     * Instantiates a new Command words.
     */
// Constructor that initiates the valid commands HashMap and fills it with all the commands from the CommandWord enum
    public CommandWords()
    {
        validCommands = new LinkedHashMap<String, CommandWord>();
        // Foreach loop that runs through all the words in CommandWord enum

        for(CommandWord command : CommandWord.values()) 
        {
            // Checks if the word is Unknown and if not add the word to the HashMap
            if(command != CommandWord.UNKNOWN) 
            {
                validCommands.put(command.toString(), command);

            }
        }
    }

    /**
     * Gets command word.
     *
     * @param commandWord the command word
     * @return the command word
     */
// Method that checks if a given String command is valid and the returns the actual CommandWord object
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) 
        {
            return command;
        }
        else 
        {
            return CommandWord.UNKNOWN;
        }
    }

    /**
     * Is command boolean.
     *
     * @param aString the a string
     * @return the boolean
     */
// Method that checks if a given String command is valid and then returns a boolean
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    /**
     * Show all.
     */
// Method that prints all the available commands to the screen
    public void showAll() 
    {
        int counter = 1;
        for(String command : validCommands.keySet()) 
        {
            System.out.println(counter + ". " +command + "  ");
            counter++;
        }
        System.out.println();
    }
}
