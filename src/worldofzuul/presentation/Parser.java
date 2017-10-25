package worldofzuul.presentation;

import worldofzuul.presentation.Command;
import worldofzuul.presentation.CommandWords;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Class used to handle user input.
 */
public class Parser 
{
    // Declaring objects for later instantiation
    private CommandWords commands;
    private Scanner reader;

    /**
     * Instantiates a new Parser.
     */
    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * Gets the input from the user.
     *
     * @return the command
     */
    public Command getCommand() 
    {
        // Create 3 Strings to contain the input from the user
        String inputLine;
        String word1 = null;
        String word2 = null;
        String word3 = null;

        System.out.print("> ");

        // Sets inputLine String to the value entered on the keyboard
        inputLine = reader.nextLine();
        inputLine = inputLine.toLowerCase();
        // Initialize a new Scanner that reads the String inputLine
        // by doing this we can split the inputLine into seperate words
        Scanner tokenizer = new Scanner(inputLine);
        // Checks if inputLine has a word in it
        if(tokenizer.hasNext()) 
        {
            // Sets word1 to the first word in inputLine
            word1 = tokenizer.next();
            // Checks if inputLine has another word in it
            if(tokenizer.hasNext()) 
            {
                // Sets word2 to the second word in inputLine
                word2 = tokenizer.next();

                if(tokenizer.hasNext())
                {
                    // Sets word2 to the second word in inputLine
                    word3 = tokenizer.next();
                }
            }
        }

        // return the input as a Command object
        return new Command(commands.getCommandWord(word1), word2, word3);
    }

    /**
     * Instantiates a new Parser object with parameterized constructor(parameters) .
     *
     * @param commands the commands
     * @param reader   the reader
     */
    public Parser(CommandWords commands, Scanner reader)
    {
        this.commands = commands;
        this.reader = reader;
    }

    /**
     * Print all available commands to the console through the CommandWords class.
     */
    public void showCommands()
    {
        commands.showAll();
    }

    /**
     * Get the Player name from user input.
     *
     * @return the string
     */
    public String getUserInput()
    {
        System.out.print("> ");
        return reader.nextLine();
    }


}
