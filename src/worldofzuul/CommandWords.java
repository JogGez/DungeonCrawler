package worldofzuul;

import java.util.HashMap;

// Class that holds a list of all the available words/commands
public class CommandWords
{
    // Declaring objects for later instantiation
    private HashMap<String, CommandWord> validCommands;

    // Constructor that initiates the valid commands HashMap and fills it with all the commands from the CommandWord enum
    public CommandWords()
    {
        validCommands = new HashMap<String, CommandWord>();
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
    
    // Method that checks if a given String command is valid and then returns a boolean
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    // Method that prints all the available commands to the screen
    public void showAll() 
    {
        for(String command : validCommands.keySet()) 
        {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
