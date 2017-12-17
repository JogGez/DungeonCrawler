package dungeonCrawler.presentationConsole;

import java.util.Scanner;

/**
 * Class used to handle user input.
 *
 */
public class Parser 
{
    // Declaring objects for later instantiation
    private CommandWords commands;
    private Scanner reader;


    /**
     * Constructor for Parser.
     */
    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * Gets the input from the user.
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
        inputLine = reader.nextLine().toLowerCase();
        // Initialize a new Scanner that reads the String inputLine
        // by doing this we can split the inputLine into seperate words
        // tokenizer is the name of the scanner
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

                // checks if there is a 3. word in the line. 
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
     * Print all available commands to the console through the CommandWords class.
     */
    public void showCommands()
    {
        commands.showAll();
    }

    /**
     * Get the Player name from user input.
     * @return the string
     */
    public String getUserInput()
    {
        System.out.print("> ");
        return reader.nextLine().toLowerCase();
    }
    
    /**
     * Waiting for user input
     */
    public void userPressEnter()
    {
        boolean acceptedInput = false;
        while(!acceptedInput)
        {
            System.out.print("Press Enter to continue...");
            
            if (reader.nextLine().equals(""))
            {
                acceptedInput = true;
                                
            }
            
        }
    }
}
