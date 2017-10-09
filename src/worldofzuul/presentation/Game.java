package worldofzuul.presentation;

import worldofzuul.logic.Map;
import worldofzuul.logic.Player;
import worldofzuul.logic.Room;

import java.awt.*;
import java.util.ArrayList;


/**
 * Class used to get the game running.
 *
 * @author JogGez
 * @version 1.0
 * @since 2017-09-22
 */
public class Game
{
    // Parser for handling the user input
    private Parser parser;
    // Stores what room we are currently in
    private Map currentMap;
    // We are storing the class player's name for player.
    private Player player;

    /**
     * Class constructor.
     *
     * Used to create the rooms and initialize the Parser.
     */
    public Game()
    {
        // Runs the createRooms() method
        createMap();
        // Initializes the Parser
        parser = new Parser();
    }

    /**
     * Method used to create all the rooms in the game.
     */
    private void createMap()
    {
        currentMap = new Map(3,4);
    }

    /**
     * Method that starts the game and runs till the end of the game.
     *
     */
    public void play() 
    {            
        // Call printWelcome() method used to write a welcome message
        printWelcome();

        //Prints out the string  beneath
        System.out.println("Enter your name here: ");
        System.out.print("> ");
        player = new Player(parser.playerName());
        System.out.println("Well... hello there " + player.getName());
        

        // Gets the name from the parser class, which reads the next input line from the user. Which is going to be the current name for the player.

        // Boolean with 
        boolean finished = false;
        // While loop that runs through the entirety of the game. (until the user types "quit")
        while (! finished)
        {
            // Call the parser object that waits for the user to type a command
            Command command = parser.getCommand();
            // Set the finished boolean to true or false, depending on the command
            // Checks if commandWord quit
            if (processCommand(command)) 
            {
                finished = processCommand(command);    
            } 
            // checks if player has 0 health
            else if (player.isDead())
            {
                // Stops the game if you reach 0 health, and then pritns out the line
                finished = player.isDead();
                System.out.println("You have died :(");
            }
            
           
        }
        // Writes the last output before closing the application, also says goodbye to the username
        System.out.println("Thank you for playing " + player.getName() + ". Good bye!");
    }

    /**
     * Method that prints a welcome message to the screen.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Dungeon Crawler!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();

    }

    /**
     * This method processes the command recieved from the parser and returns whether or not to quit.
     *
     * @param command command to process.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        // Gets the last commandWord from the Command object
        CommandWord commandWord = command.getCommandWord();

        // Checks if the commandWord is unknown
        if(commandWord == CommandWord.UNKNOWN) 
        {
            System.out.println("I don't know what you mean...");
            return false;
        }

        // Checks if the command is Help and runs the printHelp() method
        if (commandWord == CommandWord.HELP) 
        {
            printHelp();
        }
        // Checks if the command is Go and runs the goRoom() method
        else if (commandWord == CommandWord.GO) 
        {
            goRoom(command);
        }
        else if (commandWord == CommandWord.SHOW)
        {
            show(command);
        }
        // Shows the players current health
        else if (commandWord == CommandWord.HEALTH)
        {
                System.out.println("your current health is " + player.getHealth());
        }         
//        else if (commandWord == CommandWord.ATTACK)
//        {
//                System.out.println("You deal " + player.power(monster) + " the monster's health is now " + monster.getHealth());
//                if (monster.isDead()) {
//                System.out.println("You have defeated the monster! God bless you.");
//                }
//        }
         //Checks if the command is Quit and sets the boolean to true
        else if (commandWord == CommandWord.QUIT) 
        {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    private void show(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("Show what?");
            return;
        }

        String showWhat = command.getSecondWord();

        switch (showWhat){
            case "map":

                ArrayList<String> printMe = new ArrayList<>();

                for (int i = 0; i < currentMap.getHeight(); i++)
                {
                    String mapString = "";
                    for (Room room : currentMap.getRoomList())
                    {
                        if (room.getLocation().y == i)
                        {
                            if (room.getLocation().x == player.getLocation().x && room.getLocation().y == player.getLocation().y)
                            {
                                mapString = mapString + " P ";
                            }
                            else
                            {
                                mapString = mapString + " X ";
                            }

                        }

                    }
                    printMe.add(0,mapString);
                }

                System.out.println("--------------------------------------------");

                for (String s : printMe)
                    System.out.println(s);

                System.out.println("--------------------------------------------");
                break;
            case "exits":
                for(String s : checkExits())
                    System.out.print(s + "    ");
                System.out.println("");
                break;





        }
    }

    /**
     * Method that prints a help message to the screen.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        // prints all the available commands to screen 
        parser.showCommands();
    }

    /**
     * Method for moving around the rooms.
     *
     * @param command go command.
     */
    private void goRoom(Command command) 
    {
        // Checkes if the command has a second word and if not prints a message to the screen
        if(!command.hasSecondWord()) 
        {
            System.out.println("Go where?");
            return;
        }

        // Sets the String direction to the location of the room you want to go to (east, west, north, south)
        String direction = command.getSecondWord();

        switch (direction){
            case "up":
                if (currentMap.roomExists(new Point(player.getLocation().x, player.getLocation().y + 1)))
                {
                    player.setLocation(new Point(player.getLocation().x, player.getLocation().y + 1));
                    System.out.println("Player enter new room :)");
                }
                else System.out.println("Player ran into wall :(");
                break;
            case "down":
                if (currentMap.roomExists(new Point(player.getLocation().x, player.getLocation().y - 1)))
                {
                    player.setLocation(new Point(player.getLocation().x, player.getLocation().y - 1));
                    System.out.println("Player enter new room :)");
                }
                else System.out.println("Player ran into wall :(");
                break;
            case "left":
                if (currentMap.roomExists(new Point(player.getLocation().x - 1, player.getLocation().y)))
                {
                    player.setLocation(new Point(player.getLocation().x - 1, player.getLocation().y));
                    System.out.println("Player enter new room :)");
                }
                else System.out.println("Player ran into wall :(");
                break;
            case "right":
                if (currentMap.roomExists(new Point(player.getLocation().x + 1, player.getLocation().y )))
                {
                    player.setLocation(new Point(player.getLocation().x + 1, player.getLocation().y));
                    System.out.println("Player enter new room :)");
                }
                else System.out.println("Player ran into wall :(");
                break;

        }

        for (Room room : currentMap.getRoomList())
        {
            if (player.getLocation().x == room.getLocation().x && player.getLocation().y == room.getLocation().y)
            {
                if (room.checkOut() == "Monster")
                {  
                    System.out.println("There is a monster, you can either attack or flee!");
                }
                if (room.checkOut() == "Helper")
                {

                }
                if (room.checkOut() == "Chest")
                {

                }

            }
        }
    }

    /**
     * This is the quit method that return a true or false value.
     * This will return a boolean value of true if there is no other words then quit.
     *
     * @param command quit command.
     * @return boolean
     */

    private boolean quit(Command command) 
    {
        // Checks if command says more that "quit", and cancels the request if so
        if(command.hasSecondWord()) 
        {
            System.out.println("Quit what?");
            return false;
        }
        else 
        {
            return true;
        }
          
    
    }

    public ArrayList<String> checkExits()
    {
        ArrayList<String > exitList = new ArrayList<>();

            if (currentMap.roomExists(new Point(player.getLocation().x, player.getLocation().y - 1)))
                exitList.add("down");
            if (currentMap.roomExists(new Point(player.getLocation().x, player.getLocation().y + 1)))
                exitList.add("up");
            if (currentMap.roomExists(new Point(player.getLocation().x+1, player.getLocation().y)))
                exitList.add("right");
            if (currentMap.roomExists(new Point(player.getLocation().x-1, player.getLocation().y)))
                exitList.add("left");


        return exitList;
    }


}
