package worldofzuul.presentation;

import worldofzuul.data.HighScoreHandler;
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
    // Creating a battle class object.
    private Battle battle;

    /**
     * Class constructor.
     *
     * Used to instantiate the Parser.
     */
    public Game()
    {
        // Instantiating the Parser
        parser = new Parser();
    }

    public void start()
    {
        // Call printWelcome() method used to write a welcome message
        printWelcome();

        this.menu();

    }

    /**
     * Method that starts the game and runs till the end of the game.
     *
     */
    public void play() 
    {
        // Instantiating currentMap
        currentMap = new Map(3,4);

        //Prints out the string  beneath
        System.out.println("Enter your name here: ");

        // Instantiating player and initiating name
        player = new Player(parser.getUserInput());

        System.out.println("Well... hello there " + player.getName());
        System.out.println("Type '" + CommandWord.HELP + "' if you ever need help.");
        System.out.println("Please enter a command.");

        // sets the current room as entered
        currentMap.setRoomHasBeenEntered(player.getLocation());


        // Boolean witch hold the value for exiting the game.
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
                // Stops the game if you reach 0 health, and then prints out the line
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
        System.out.println(ANSI.getTitle());
        System.out.println("Welcome to Dungeon Crawler!");
        System.out.println("This is a new, incredibly boring adventure game.");
        System.out.println();

    }

    private void printHighScore()
    {
        HighScoreHandler highScore = new HighScoreHandler("HighScore.txt");

        highScore.readText();

        for (String score : highScore.getHighScoreArray())
        {
            System.out.println(score);
        }

        parser.getUserInput();
        this.menu();
    }

    private void menu()
    {
        System.out.println("Main Menu");
        System.out.println("1. Start New Game");
        System.out.println("2. Load Saved Game");
        System.out.println("3. Show High Score");
        System.out.println("4. Settings");
        System.out.println("5. Exit");
        switch (parser.getUserInput())
        {
            case "1":
                System.out.println("");
                play();
                break;
            case "2":
                System.out.println("");
                break;
            case "3":
                System.out.println("");
                printHighScore();
                break;
            case "4":
                System.out.println("");
                break;
            case "5":
                System.out.println("");
                System.out.println("Thanks for playing");
                System.exit(0);
                break;
        }
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
        else if (commandWord == CommandWord.ATTACK)
        {
            attack(command);
        }
         //Checks if the command is Quit and sets the boolean to true
        else if (commandWord == CommandWord.QUIT)
        {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }
    private void attack(Command command)
    {
        while (!battle.getIsBattleOver())
        {            
            
        }
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
                        System.out.println("Type attack or flee."); // We need to type more informations!
                        randomMonster();
                        
                        boolean acceptedInput = false;
                        while (!acceptedInput)
                        {                            
                            String input = parser.getUserInput();//returns a String
                                                    
                            if (input.contains("attack"))   
                            {
                                acceptedInput = true;
                                battle = new Battle(player, room.getContent(i)); // createas a new battle

                            } 
                            else if (input.contains("flee"))    
                            {
                                acceptedInput = true;

                            }
                            else {System.out.println(" What do you mean?");}
                        }
                       
                        
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
            case 1: System.out.println(ANSI.getSpider()); break;
            case 2: System.out.println(ANSI.getGryphon()); break;
            case 3: System.out.println(ANSI.getMermaid()); break;
            case 4: System.out.println(ANSI.getUnicorn()); break;
            case 5: System.out.println(ANSI.getFairy()); break;
            case 6: System.out.println(ANSI.getHamster()); break;
            case 7: System.out.println(ANSI.getCyclops()); break;
            case 8: System.out.println(ANSI.getSonic()); break;
            case 9: System.out.println(ANSI.getDevil()); break;
            case 10: System.out.println(ANSI.getBabar()); break;
            case 11: System.out.println(ANSI.getBat()); break;
            case 12: System.out.println(ANSI.getBuddha()); break;
            case 13: System.out.println(ANSI.getDevil2()); break;
            case 14: System.out.println(ANSI.getEasterBunny()); break;
            case 15: System.out.println(ANSI.getFrenshMan()); break;
            case 16: System.out.println(ANSI.getGanesha()); break;
            case 17: System.out.println(ANSI.getGhost()); break;
            case 18: System.out.println(ANSI.getGrim()); break;
            case 19: System.out.println(ANSI.getHamster()); break;
            case 20: System.out.println(ANSI.getHarryPotter()); break;
            case 21: System.out.println(ANSI.getJackInABox()); break;
            case 22: System.out.println(ANSI.getJesus()); break;
            case 23: System.out.println(ANSI.getKnight1()); break;
            case 24: System.out.println(ANSI.getKnight2()); break;
            case 25: System.out.println(ANSI.getMickeyMouse()); break;
            case 26: System.out.println(ANSI.getNakedWoman()); break;
            case 27: System.out.println(ANSI.getYourMom()); break;
            case 28: System.out.println(ANSI.getPope()); break;
            case 29: System.out.println(ANSI.getPentacle()); break;
            case 30: System.out.println(ANSI.getPikachu()); break;
            case 31: System.out.println(ANSI.getRabbit()); break;
            case 32: System.out.println(ANSI.getHamster()); break;
            case 33: System.out.println(ANSI.getSanta()); break;
            case 34: System.out.println(ANSI.getSeaHorse()); break;
            case 35: System.out.println(ANSI.getShark()); break;
            case 36: System.out.println(ANSI.getSheep()); break;
            case 37: System.out.println(ANSI.getTeddyBear()); break;
            case 38: System.out.println(ANSI.getWhale()); break;
            case 39: System.out.println(ANSI.getWitch()); break;
            case 40: System.out.println(ANSI.getYourMom()); break;

        }
        System.out.println("-----------------------------------------------");
        
    }

    public void randomChest()
    {
        System.out.println("-----------------------------------------------");
        int random = (int)(Math.random()*3);
        switch (random)
        {
            case 0: System.out.println( ANSI.getChest()); break;
            case 1: System.out.println( ANSI.getChest2()); break;
            case 2: System.out.println( ANSI.getChest3()); break;
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
            case 0: System.out.println(ANSI.getHamster()); break;
            case 1: System.out.println(ANSI.getBuddha()); break;
            case 2: System.out.println(ANSI.getGanesha()); break;
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
