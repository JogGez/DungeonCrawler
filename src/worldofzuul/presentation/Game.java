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

        slowPrint("Well... hello there " + player.getName(), 10);
        slowPrint("I'am Slave and I'll be your guide through this adventure.",10);
        slowPrint("You know is me by the slow print speed of the characters...",10);

        slowPrint("Type '" + CommandWord.HELP + "' if you ever need help.", 10);

        slowPrint("Type \"enter\" to enter the DUNGEON...", 10);


        boolean acceptedInput = false;
        while (!acceptedInput)
        {
            String input = parser.getUserInput();//returns a String

            if (input.contains("enter"))
            {
                acceptedInput = true;
            }
            else
            {
                System.out.println("Type \"enter\" to start the game.");
            }
        }



        // sets the current room as entered
        currentMap.setRoomHasBeenEntered(player.getLocation());
        checkRoom();

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
        System.out.println(ASCII.getTitle());
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
        else if (commandWord == CommandWord.USE)
        {
            use(command);
        }
        else if (commandWord == CommandWord.ATTACK)
        {
            attack(command);
        }
         //Checks if the command is Quit and sets the boolean to true
        else if (commandWord == CommandWord.QUIT || commandWord == CommandWord.EXIT )
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

    private void use(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("Use what?");
            return;
        }

        switch (command.getSecondWord())
        {
            case "slot":

                if (0 <= (Integer.parseInt(command.getThirdWord()) - 1) && (Integer.parseInt(command.getThirdWord()) - 1) <  player.getInventory().getSize())
                {
                    for (int i = 0; i < player.getInventory().getSize(); i++)
                    {
                        if (i == Integer.parseInt(command.getThirdWord()) - 1)
                        {
                            useSlot(i);
                            break;
                        }
                    }
                }
                else
                {
                    System.out.println("Slot is out of range");
                }
                break;
        }
    }

    private void show(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("Show what?");
            return;
        }

        switch (command.getSecondWord()){
            case "map":

                ArrayList<String> mapList = new ArrayList<>();

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
                    mapList.add(0,mapString);
                }


                mapList.set(0 , mapList.get(0) + "   X = Unseen Rooms  ");
                mapList.set(1 , mapList.get(1) + "   O = Seen Rooms ");
                mapList.set(2 , mapList.get(2) + "   P = Player ");
                mapList.set(3 , mapList.get(3) + "   G = Guides ");

//                System.out.println("-------------------------------------");
                System.out.println("   \u256D\u2500\u2500\u2500\u2500\u252C\u2500\u2500\u2500\u252C\u2500\u2500\u2500\u2500\u256E");

                for (String s : mapList)
                    System.out.println(s);

                System.out.println("   \u2570\u2500\u2500\u2500\u2500\u2534\u2500\u2500\u2500\u2534\u2500\u2500\u2500\u2500\u256F");
                System.out.println("      0  1  2");
//                System.out.println("-------------------------------------");
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
            case "weapon":
                System.out.println(player.getCurrentWeapon().ascii);
                System.out.println("Name: " + player.getCurrentWeapon().name);
                System.out.println("POWER: " + (player.getCurrentWeapon()).getPower());
                System.out.println("MULTIPLIER: " + (player.getCurrentWeapon()).getMultiplier() + "x");
                System.out.println(player.getCurrentWeapon().description);
                break;
            case "inventory":

                showInventory();
                break;
            case "slot":

                if (0 <= (Integer.parseInt(command.getThirdWord()) - 1) && (Integer.parseInt(command.getThirdWord()) - 1) <  player.getInventory().getSize())
                {
                    for (int i = 0; i < player.getInventory().getSize(); i++)
                    {
                        if (i == Integer.parseInt(command.getThirdWord()) - 1)
                        {
                            showSlot(i);
                            break;
                        }
                    }
                }
                else
                {
                    System.out.println("Slot is out of range");
                }
                break;

            default:
                System.out.println("Huh?");
                break;

        }
    }

    public void showInventory()
    {
        String Top = " \u256D";
        String MTop = " \u2551";
        String Middle = " \u2551";
        String MBottom = " \u2551";
        String Bottom = " \u2570";
        String Slot = "  ";

        for (int i = 0; i < player.getInventory().getSize(); i++)
        {
            Top += "-" + player.getInventory().getItem(i).name.replaceAll(".","-") + "-";
            MTop +=" " + player.getInventory().getItem(i).name.replaceAll("."," ") + " ";
            Middle +=" " + player.getInventory().getItem(i).name + " ";
            MBottom +=" " + player.getInventory().getItem(i).name.replaceAll("."," ") + " ";
            Bottom += "-" + player.getInventory().getItem(i).name.replaceAll(".","-") + "-";
            Slot += " " + String.valueOf(i+1) + player.getInventory().getItem(i).name.replaceAll(".", " ") + "";
        }


        System.out.println(Top + "\u256E");
//        System.out.println(MTop + "\u2551");
        System.out.println(Middle + "\u2551" + "  To get information on an item type \"show slot 1\",slot 2 ...");
//        System.out.println(MBottom + "\u2551");
        System.out.println(Bottom + "\u256F" + "  To use an item type \"use slot 1\",slot 2 ...");
        System.out.println(Slot);
    }

    public void showSlot(int index)
    {
        Item item = player.getInventory().getItem(index);

        if (item instanceof Weapon)
        {
            System.out.println(item.ascii);
            System.out.println("Name: " + item.name);
            System.out.println("POWER: " + ((Weapon) item).getPower());
            System.out.println("MULTIPLIER: " + ((Weapon) item).getMultiplier() + "x");
            System.out.println(item.description);
        }
        else if (item instanceof Potion)
        {
            System.out.println(item.ascii);
            System.out.println("Name: " + item.name);
            System.out.println("RECOVERY: " + ((Potion) item).getHealthRecovery());
            System.out.println(item.description);
        }
        else
        {
            System.out.println("Slot is empty.");
        }

    }

    public void useSlot(int index)
    {
        Item item = player.getInventory().getItem(index);

        if (item instanceof Weapon)
        {
            player.setCurrentWeapon((Weapon) item);
            System.out.println("Your current weapon is now: " + player.getCurrentWeapon().name);
        }
        else if (item instanceof Potion)
        {
            player.setHealth(player.getHealth() + ((Potion) item).getHealthRecovery());
            player.getInventory().removeItem(player.getInventory().getItemIndex(item));
            System.out.println("Yom yom ... Your health is now: " + player.getHealth() + "hp");
        }
        else
        {
            System.out.println("Slot is empty.");
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
                    System.out.println("You enter new room.");
                    currentMap.setRoomHasBeenEntered(player.getLocation());
                    checkRoom();

                }
                else System.out.println("You ran into wall :(");
                break;
            case "down":
                if (currentMap.roomExists(new Point(player.getLocation().x, player.getLocation().y - 1)))
                {
                    player.setLocation(new Point(player.getLocation().x, player.getLocation().y - 1));
                    System.out.println("You enter new room.");
                    currentMap.setRoomHasBeenEntered(player.getLocation());
                    checkRoom();
                }
                else System.out.println("You ran into wall :(");
                break;
            case "left":
                if (currentMap.roomExists(new Point(player.getLocation().x - 1, player.getLocation().y)))
                {
                    player.setLocation(new Point(player.getLocation().x - 1, player.getLocation().y));
                    System.out.println("You enter new room.");
                    currentMap.setRoomHasBeenEntered(player.getLocation());
                    checkRoom();
                }
                else System.out.println("You ran into wall :(");
                break;
            case "right":
                if (currentMap.roomExists(new Point(player.getLocation().x + 1, player.getLocation().y )))
                {
                    player.setLocation(new Point(player.getLocation().x + 1, player.getLocation().y));
                    System.out.println("You enter new room.");
                    currentMap.setRoomHasBeenEntered(player.getLocation());
                    checkRoom();
                }
                else System.out.println("You ran into wall :(");
                break;
            case "back":

                player.setLocation(new Point(player.getLastLocation().x, player.getLastLocation().y));
                System.out.println("You went back to the previous room.");
                checkRoom();
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
                        randomMonster();
                        System.out.println("There is a monster, you can either do battle or flee!");
                        System.out.println("Your health is currently " + player.getHealth() + "hp");
                        System.out.println("Monsters health is currently " + ((Monster) room.getContent(i)).getHealth() + "hp");
                        System.out.println("Type \"battle\" or \"flee\"."); // We need to type more informations!

                        boolean acceptedInput = false;
                        while (!acceptedInput)
                        {                            
                            String input = parser.getUserInput();//returns a String

                            if (input.contains("battle"))
                            {
                                acceptedInput = true;
                                battle = new Battle(player, (Monster)room.getContent(i)); // createas a new battle


                                while (!battle.getIsBattleOver())
                                {
                                    System.out.println("attack & drink potion");

                                    input = parser.getUserInput();
                                    if (input.contains("attack") || input.contains("a"))
                                    {
                                        System.out.println(battle.fight());
                                    }
                                    else if (input.contains("drink"))
                                    {
                                        if (!player.getInventory().potionArrayList().isEmpty())
                                        {
                                            System.out.println("Type number to use.");
                                            for (int j = 0; j < player.getInventory().potionArrayList().size(); j++)
                                            {
                                                System.out.println((j+1) + ". Potion:" + player.getInventory().potionArrayList().get(j).getHealthRecovery());
                                            }

                                            input = parser.getUserInput();
                                            int index = Integer.parseInt(input) -1;

                                            player.setHealth(player.getHealth() + player.getInventory().potionArrayList().get(index).getHealthRecovery());
                                            player.getInventory().removeItem(player.getInventory().getItemIndex(player.getInventory().potionArrayList().get(index)));
                                            System.out.println("Yom yom ... Your health is now " + player.getHealth() + "hp");
                                        }
                                        else
                                        {
                                            System.out.println("You have no potions :(");
                                        }

                                    }
                                    else {System.out.println("Type \"attack\" or \"drink\"");}

                                }
                                parser.getUserInput();

                            } 
                            else if (input.contains("flee"))    
                            {
                                acceptedInput = true;
                                player.setLocation(player.getLastLocation());
                                return;

                            }
                            else {System.out.println("Type \"battle\" or \"flee\"");}
                        }
                       
                        
                    }
                    else if (room.getContent(i) instanceof Helper)
                    {
                        randomHelper();
                        System.out.println("There is a helper, you can either \"talk\" , \"flee\" or \"kill\"!");
                        boolean acceptedInput = false;
                        while (!acceptedInput)
                        {
                            String input = parser.getUserInput();
                            if(input.equals("talk"))
                            {
                                acceptedInput = true;
                                System.out.println("Hello name is \"insert name here\" here is a tip ;) ... DON'T DIE!!!");
                            }
                            else if(input.equals("flee"))
                            {
                                acceptedInput = true;
                                player.setLocation(player.getLastLocation());
                                return;
                            }
                            else if(input.equals("kill"))
                            {
                                acceptedInput = true;
                                //room.removeContent(i);
                                System.out.println("You killed the helper, oh might swordsman!");
                            }
                        }

                    }
                    else if (room.getContent(i) instanceof Chest)
                    {
                        randomChest();
                        System.out.println("There is a chest, type \"open\" to open!");
                        boolean acceptedInput = false;
                        while (!acceptedInput)
                        {
                            String input = parser.getUserInput();
                            if(input.equals("open"))
                            {
                                acceptedInput = true;
                                Item item = ((Chest)room.getContent(i)).getItem();

                                if (item instanceof Weapon)
                                {
                                    System.out.println(item.ascii);
                                    System.out.println("Your found: " + item.name);
                                    System.out.println("POWER: " + ((Weapon) item).getPower());
                                    System.out.println("MULTIPLIER: " + ((Weapon) item).getMultiplier() + "x");
                                    System.out.println(item.description);
                                }
                                else if (item instanceof Potion)
                                {
                                    System.out.println(item.ascii);
                                    System.out.println("Your found: " + item.name);
                                    System.out.println("RECOVERY: " + ((Potion) item).getHealthRecovery());
                                    System.out.println(item.description);
                                }
                                showInventory();
                                System.out.println("Do you want to insert this into a slot?");
                                System.out.println("Type slot number or \"drop\" to drop.");

                                input = parser.getUserInput();

                                for (int j = 0; j < player.getInventory().getSize() ; j++)
                                {
                                    if(input.equals(String.valueOf(j+1)))
                                    {
                                        player.getInventory().addItem(item,j);
                                        System.out.println("You saved this item in slot: " + (j+1));
                                    }
                                }
                                if (input.equals("drop"))
                                {
                                    System.out.println("You dropped the item");
                                }

                                //room.removeContent(i);


                            }
                            else
                            {
                                System.out.println("Hmm... Wrong command");
                            }
                        }
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
            case 1: System.out.println(ASCII.getSpider()); break;
            case 2: System.out.println(ASCII.getGryphon()); break;
            case 3: System.out.println(ASCII.getMermaid()); break;
            case 4: System.out.println(ASCII.getUnicorn()); break;
            case 5: System.out.println(ASCII.getFairy()); break;
            case 6: System.out.println(ASCII.getHamster()); break;
            case 7: System.out.println(ASCII.getCyclops()); break;
            case 8: System.out.println(ASCII.getSonic()); break;
            case 9: System.out.println(ASCII.getDevil()); break;
            case 10: System.out.println(ASCII.getBabar()); break;
            case 11: System.out.println(ASCII.getBat()); break;
            case 12: System.out.println(ASCII.getBuddha()); break;
            case 13: System.out.println(ASCII.getDevil2()); break;
            case 14: System.out.println(ASCII.getEasterBunny()); break;
            case 15: System.out.println(ASCII.getFrenshMan()); break;
            case 16: System.out.println(ASCII.getGanesha()); break;
            case 17: System.out.println(ASCII.getGhost()); break;
            case 18: System.out.println(ASCII.getGrim()); break;
            case 19: System.out.println(ASCII.getHamster()); break;
            case 20: System.out.println(ASCII.getHarryPotter()); break;
            case 21: System.out.println(ASCII.getJackInABox()); break;
            case 22: System.out.println(ASCII.getJesus()); break;
            case 23: System.out.println(ASCII.getKnight1()); break;
            case 24: System.out.println(ASCII.getKnight2()); break;
            case 25: System.out.println(ASCII.getMickeyMouse()); break;
            case 26: System.out.println(ASCII.getNakedWoman()); break;
            case 27: System.out.println(ASCII.getYourMom()); break;
            case 28: System.out.println(ASCII.getPope()); break;
            case 29: System.out.println(ASCII.getPentacle()); break;
            case 30: System.out.println(ASCII.getPikachu()); break;
            case 31: System.out.println(ASCII.getRabbit()); break;
            case 32: System.out.println(ASCII.getHamster()); break;
            case 33: System.out.println(ASCII.getSanta()); break;
            case 34: System.out.println(ASCII.getSeaHorse()); break;
            case 35: System.out.println(ASCII.getShark()); break;
            case 36: System.out.println(ASCII.getSheep()); break;
            case 37: System.out.println(ASCII.getTeddyBear()); break;
            case 38: System.out.println(ASCII.getWhale()); break;
            case 39: System.out.println(ASCII.getWitch()); break;
            case 40: System.out.println(ASCII.getYourMom()); break;

        }
        System.out.println("-----------------------------------------------");
        
    }

    public void randomChest()
    {
        System.out.println("-----------------------------------------------");
        int random = (int)(Math.random()*2);
        switch (random)
        {
            case 0: System.out.println( ASCII.getChest()); break;
            case 1: System.out.println( ASCII.getChest3()); break;
        }
        System.out.println("-----------------------------------------------");
    }

    public void randomHelper()
    {
        System.out.println("-----------------------------------------------");
        int random = (int)(Math.random()*3);
        switch (random)
        {
            case 0: System.out.println(ASCII.getHamster()); break;
            case 1: System.out.println(ASCII.getBuddha()); break;
            case 2: System.out.println(ASCII.getGanesha()); break;
        }
        System.out.println("-----------------------------------------------");

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
        System.out.println();
    }
}
