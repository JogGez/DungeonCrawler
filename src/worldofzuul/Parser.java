package worldofzuul;

import java.util.Scanner;
import java.util.StringTokenizer;

// Class used to handle user input
public class Parser 
{
    // Declaring objects for later instantiation
    private CommandWords commands;
    private Scanner reader;

    // Constructor that instantiates the CommandWords object and the Scanner
    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    // Method that gets the input from the keyboard
    public Command getCommand() 
    {
        // Create 3 Strings to contain the input from the user
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> ");

        // Sets inputLine String to the value entered on the keyboard
        inputLine = reader.nextLine();

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
            }
        }

        // return the input as a Command object
        return new Command(commands.getCommandWord(word1), word2);
    }

    // Constructor overloading used to pass existing CommandWords object and Scanner
    public Parser(CommandWords commands, Scanner reader)
    {
        this.commands = commands;
        this.reader = reader;
    }

    // Method that calls the CommandWords object showAll() method that prints all the available command to the screen 
    public void showCommands()
    {
        commands.showAll();
    }
}
