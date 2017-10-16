package worldofzuul.presentation;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MatchGenerator;
import sun.invoke.empty.Empty;
import worldofzuul.logic.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;


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

        player = new Player(parser.getUserInput());
        System.out.println("Well... hello there " + player.getName());
        System.out.println("Please enter a command.");

        currentMap.setRoomHasBeenEntered(player.getLocation());
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
        ANSI.printTitle();
        System.out.println("Welcome to Dungeon Crawler!");
        System.out.println("This is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you ever need help.");
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
                    String mapString = " " + i + " \u2551 ";
                    for (Room room : currentMap.getRoomList())
                    {
                        if (room.getLocation().y == i)
                        {
                            if (room.getLocation().x == player.getLocation().x && room.getLocation().y == player.getLocation().y)
                            {
                                mapString = mapString + " P ";
                            }
                            else if (room.getHasBeenEntered())
                            {
                                mapString = mapString + " O ";
                            }
                            else if (!room.getHasBeenEntered())
                            {
                                mapString = mapString + " X ";
                            }
                        }

                    }
                    mapString += " \u2551";
                    printMe.add(0,mapString);
                }


                printMe.set(0 , printMe.get(0) + "   X = Unseen Rooms  ");
                printMe.set(1 , printMe.get(1) + "   O = Seen Rooms ");
                printMe.set(2 , printMe.get(2) + "   P = Player ");
                printMe.set(3 , printMe.get(3) + "   G = Guides ");

                System.out.println("-------------------------------------");
                System.out.println("   \u256D\u2500\u2500\u2500\u2500\u252C\u2500\u2500\u2500\u252C\u2500\u2500\u2500\u2500\u256E");

                for (String s : printMe)
                    System.out.println(s);

                System.out.println("   \u2570\u2500\u2500\u2500\u2500\u2534\u2500\u2500\u2500\u2534\u2500\u2500\u2500\u2500\u256F");
                System.out.println("      0  1  2");
                System.out.println("-------------------------------------");
                break;
            case "exits":
                int counter = 1;
                for(String s : checkExits())
                {
                    System.out.println(counter + ". " + s);
                    counter++;
                }
                System.out.println("");
                break;
            case "health":
                System.out.println("You currently have: " + player.getHealth() + " hp");
                break;
            case "score":
                System.out.println("You currently have: " + player.getScore() + " points");
                break;
            default:
                System.out.println("Huh?");
                break;

        }
    }

    /**
     * Method that prints a help message to the screen.
     */
    private void printHelp() 
    {
        System.out.println("Welcome to the Help menu system...");
        System.out.println("Choose one of the following options.");
        System.out.println("---------------------------------------");
        System.out.println("1. Game Commands");
        System.out.println("2. Game Goals");
        System.out.println("3. Game Tips & Tricks");

        String input = parser.getUserInput();

        if (input.contains("1"))
        {
            System.out.println("Your command words are:");
            // prints all the available commands to screen
            parser.showCommands();
        }
        else if (input.contains("2"))
        {
            System.out.println("The goals of the game is to defeat the devil");
        }
        else if (input.contains("3"))
        {
            System.out.println("No tips or tricks available :( ");
        }
        else
        {
            System.out.println("Invalid menu choice");
        }
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
                    currentMap.setRoomHasBeenEntered(player.getLocation());
                    checkRoom();

                }
                else System.out.println("Player ran into wall :(");
                break;
            case "down":
                if (currentMap.roomExists(new Point(player.getLocation().x, player.getLocation().y - 1)))
                {
                    player.setLocation(new Point(player.getLocation().x, player.getLocation().y - 1));
                    System.out.println("Player enter new room :)");
                    currentMap.setRoomHasBeenEntered(player.getLocation());
                    checkRoom();
                }
                else System.out.println("Player ran into wall :(");
                break;
            case "left":
                if (currentMap.roomExists(new Point(player.getLocation().x - 1, player.getLocation().y)))
                {
                    player.setLocation(new Point(player.getLocation().x - 1, player.getLocation().y));
                    System.out.println("Player enter new room :)");
                    currentMap.setRoomHasBeenEntered(player.getLocation());
                    checkRoom();
                }
                else System.out.println("Player ran into wall :(");
                break;
            case "right":
                if (currentMap.roomExists(new Point(player.getLocation().x + 1, player.getLocation().y )))
                {
                    player.setLocation(new Point(player.getLocation().x + 1, player.getLocation().y));
                    System.out.println("Player enter new room :)");
                    currentMap.setRoomHasBeenEntered(player.getLocation());
                    checkRoom();
                }
                else System.out.println("Player ran into wall :(");
                break;
            default:
                System.out.println("Go where? No such direction found...");
                break;
        }



    }

    public void checkRoom()
    {
        for (int i = 0; i < 2; i++)
        {
            for (Room room : currentMap.getRoomList())
            {
                if (player.getLocation().x == room.getLocation().x && player.getLocation().y == room.getLocation().y)
                {
                    if (room.getContent(i) instanceof Monster)
                    {
                        System.out.println("There is a monster, you can either attack or flee!");
                        randomMonster();
                    }
                    else if (room.getContent(i) instanceof Helper)
                    {
                        System.out.println("There is a helper, you can either ask question or flee!");
                        randomHelper();
                    }
                    else if (room.getContent(i) instanceof Chest)
                    {
                        System.out.println("There is a chest, you can either open or flee!");
                        randomChest();
                    }
                    else if (room.getContent(i) instanceof RoomContent)
                    {
                        System.out.println("Empty space :(");
                    }
                }
            }
        }
    }



    public void randomMonster()
    {
        System.out.println("-----------------------------------------------");
        int random = (int)(Math.random()*40 + 1);
        switch (random)
        {
            case 1: ANSI.printSpider(); break;
            case 2: ANSI.printGryphon(); break;
            case 3: ANSI.printMermaid(); break;
            case 4: ANSI.printUnicorn(); break;
            case 5: ANSI.printFairy(); break;
            case 6: ANSI.printHamster(); break;
            case 7: ANSI.printCyclops(); break;
            case 8: ANSI.printSonic(); break;
            case 9: ANSI.printDevil(); break;
            case 10: ANSI.printBabar(); break;
            case 11: ANSI.printBat(); break;
            case 12: ANSI.printBuddha(); break;
            case 13: ANSI.printDevil2(); break;
            case 14: ANSI.printEasterBunny(); break;
            case 15: ANSI.printFrenshMan(); break;
            case 16: ANSI.printGanesha(); break;
            case 17: ANSI.printGhost(); break;
            case 18: ANSI.printGrim(); break;
            case 19: ANSI.printHamster(); break;
            case 20: ANSI.printHarryPotter(); break;
            case 21: ANSI.printJackInABox(); break;
            case 22: ANSI.printJesus(); break;
            case 23: ANSI.printKnight1(); break;
            case 24: ANSI.printKnight2(); break;
            case 25: ANSI.printMickeyMouse(); break;
            case 26: ANSI.printNakedWoman(); break;
            case 27: ANSI.printNoSmooking(); break;
            case 28: ANSI.printPope(); break;
            case 29: ANSI.printPentacle(); break;
            case 30: ANSI.printPikachu(); break;
            case 31: ANSI.printRabbit(); break;
            case 32: ANSI.printRobot(); break;
            case 33: ANSI.printSanta(); break;
            case 34: ANSI.printSeaHorse(); break;
            case 35: ANSI.printShark(); break;
            case 36: ANSI.printSheep(); break;
            case 37: ANSI.printTeddyBear(); break;
            case 38: ANSI.printWhale(); break;
            case 39: ANSI.printWitch(); break;
            case 40: ANSI.printYourMom(); break;

        }
        System.out.println("-----------------------------------------------");
        Scanner input = new Scanner(System.in);
        input.nextLine();
    }

    public void randomChest()
    {
        System.out.println("-----------------------------------------------");
        int random = (int)(Math.random()*3);
        switch (random)
        {
            case 0: ANSI.printChest(); break;
            case 1: ANSI.printChest2(); break;
            case 2: ANSI.printChest3(); break;
        }
        System.out.println("-----------------------------------------------");
        Scanner input = new Scanner(System.in);
        input.nextLine();
    }

    public void randomHelper()
    {
        System.out.println("-----------------------------------------------");
        int random = (int)(Math.random()*3);
        switch (random)
        {
            case 0: ANSI.printHamster(); break;
            case 1: ANSI.printBuddha(); break;
            case 2: ANSI.printGanesha(); break;
        }
        System.out.println("-----------------------------------------------");
        Scanner input = new Scanner(System.in);
        input.nextLine();
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

        if (currentMap.roomExists(new Point(player.getLocation().x - 1, player.getLocation().y)))
            exitList.add("left");
        if (currentMap.roomExists(new Point(player.getLocation().x + 1, player.getLocation().y)))
            exitList.add("right");
        if (currentMap.roomExists(new Point(player.getLocation().x, player.getLocation().y + 1)))
            exitList.add("up");
        if (currentMap.roomExists(new Point(player.getLocation().x, player.getLocation().y - 1)))
            exitList.add("down");



        return exitList;
    }

    public void slowPrint(String message, long millisPerChar)
    {
        for (int i = 0; i < message.length(); i++)
        {
            System.out.print(message.charAt(i));

            try
            {
                Thread.sleep(millisPerChar);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
