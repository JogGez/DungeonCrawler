package worldofzuul.presentation;
import worldofzuul.logic.*;

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
    // Creating a battle class object.
    private Battle battle;
    //Creating print to console object
    private PrintToConsole printToConsole;
    //Creating print to console object
    private TextForPrintToConsole textForPrintToConsole;

    /**
     * Class constructor.
     * Used to instantiate the Parser + Print to console + text for print to console
     */
    public Game()
    {
        // Instantiating the Parser
        parser = new Parser();

        //Instantiating TextForPrintToConsole
        textForPrintToConsole = new TextForPrintToConsole();

        //Instantiating PrintToConsole
        printToConsole = new PrintToConsole();
    }

    /**
     * Start menu
     * Used to call method to start game + Show start logo + Print welcome menu
     */
    public void start()
    {
        //Prints welcome logo and welcome text
        printToConsole.print(textForPrintToConsole.getAsciiTitle());
        printToConsole.print(textForPrintToConsole.getWelcomeText());
        
        // Call menu
        menu();
    }

    /**
     * Method that starts the game and runs till the end of the game.
     */
    public void play() 
    {
        // Instantiating currentMap
        currentMap = new Map(3,4,2 ,2);

        //Prints "Enter your name here: "
        printToConsole.print(textForPrintToConsole.getEnterPlayerName());

        // Instantiating player and initiating name
        player = new Player(parser.getUserInput());

        //Prints Start info (passing object player to be able to print name)
        printToConsole.print(textForPrintToConsole.getMessageHello(player));

        boolean acceptedInput = false;
        while (!acceptedInput)
        {
            String input = parser.getUserInput();//returns a String

            if (input.equals("enter")||input.equals("e"))
            {
                acceptedInput = true;
            }
            else
            {
                //Prints "Type \"enter\" to start the game."
                printToConsole.print(textForPrintToConsole.getEnterToStartGame());
            }
        }

        // sets the current room as entered
        // Compare the players coordinates with the currentMap room coordinates.
        // There is a for-each loop in setRoomHasBeenEntered, that goes through the coordinates.
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
            else if (player.getHealth() <= 0 )
            {
                // Stops the game if you reach 0 health, and then prints out the line 
                finished = player.getHealth() <= 0;
                //Prints "You have died"
                printToConsole.print(textForPrintToConsole.getYouHaveDied());

            }
        }
        // Writes the last output before closing the application, also says goodbye to the username
        printToConsole.print(textForPrintToConsole.getThanksForPLaying(player));


    }

    /**
     * Method that prints Highscore
     */
    private void printHighScore()
    {
        //Prints high score
        printToConsole.printHightScore();
        parser.getUserInput();
        this.menu();
    }

    /**
     * Method that prints menu and and acts on userinput
     */
    private void menu()
    {
        //Prints menu
        printToConsole.print(textForPrintToConsole.getMenu());


        switch (parser.getUserInput())
        {
            case "1":
                printToConsole.print(textForPrintToConsole.getEmptyLine());//Prints empty line
                play();
                break;
            case "2":
                printToConsole.print(textForPrintToConsole.getEmptyLine());//Prints empty line
                break;
            case "3":
                printToConsole.print(textForPrintToConsole.getEmptyLine());//Prints empty line
                printHighScore();
                break;
            case "4":
                printToConsole.print(textForPrintToConsole.getEmptyLine());//Prints empty line
                break;
            case "5":
                printToConsole.print(textForPrintToConsole.getEmptyLine());//Prints empty line
                //Print thank you for playing
                printToConsole.print(textForPrintToConsole.getThanksForPLaying(player));//"Thanks for playing "+player name+". Good bye!"
                System.exit(0);
                break;
        }
    }

    /**
     * This method processes the command recieved from the parser
     * and returns whether or not to quit.
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
            //Prints: "I don't know what you mean..."
            printToConsole.print(textForPrintToConsole.getWhatDoYouMean());
            return false;
        }

        // Checks if the command is Help and runs the printHelp() method
        if (commandWord == CommandWord.HELP) 
        {
            //Invoke help method
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
        else if (commandWord == CommandWord.BATTLE)
        {
            battle(command);
        }
         //Checks if the command is Quit and sets the boolean to true
        else if (commandWord == CommandWord.QUIT || commandWord == CommandWord.EXIT )
        {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    private void battle(Command command)
    {
        if(!command.hasSecondWord())
        {
            //Prints "Battle what?"
            printToConsole.print(textForPrintToConsole.getBattleWhat());
            return;
        }

    }


    private void attack(Command command)
    {
        while (!battle.getIsBattleOver())
        {
        }
    }

    /**
     *
     * @param command
     */
    private void use(Command command)
    {
        if(!command.hasSecondWord())
        {
            //Prints "Use what?"
            printToConsole.print(textForPrintToConsole.getUseWhat());
            return;
        }

        switch (command.getSecondWord())
        {
            case "slot":

                if (0 <= (Integer.parseInt(command.getThirdWord()) - 1) &&
                        (Integer.parseInt(command.getThirdWord()) - 1)
                                <  player.getInventory().getSize())
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
                    //Prints"Slot is out of range"
                    printToConsole.print(textForPrintToConsole.getSlotIsOutOfRange());
                }
                break;
        }
    }

    private void show(Command command)
    {
        if(!command.hasSecondWord())
        {
            //Prints "Show what?"
            printToConsole.print(textForPrintToConsole.getShowWhat());
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
                            if (room.getLocation().x == player.getLocation().x &&
                                    room.getLocation().y == player.getLocation().y)
                            {
                                mapString = mapString + "  P   ";
                            }
                            else if (room.getHasBeenEntered())
                            {
                                mapString = mapString + "  O   ";
                            }
                            else if (!room.getHasBeenEntered())
                            {
                                mapString = mapString + "  X   ";
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

                //Prints Map layout part 1
                printToConsole.print(textForPrintToConsole.getMapLayoutPart1());

                for (String s : mapList)

                //Prints players whereabouts -> Passing  String s as parameter
                printToConsole.print(s);

                //Prints Map layout part 2
                printToConsole.print(textForPrintToConsole.getMapLayoutPart2());

                //Prints Map layout part 3
                printToConsole.print(textForPrintToConsole.getMapLayoutPart3());
                break;
            case "exits":
                int counter = 1;
                for(String s : checkExits())
                {
                //Prints exits from current room
                printToConsole.print(textForPrintToConsole.getExits(counter,s));
                counter++;
                }
                printToConsole.print(textForPrintToConsole.getEmptyLine());//Prints empty line
                break;
            case "health":
                //Prints players current hp
                printToConsole.print(textForPrintToConsole.getYouCurrentlyHaveHp(player));
                break;
            case "score":
                //Prints players current score
                printToConsole.print(textForPrintToConsole.getYouCurrentlyHavePoints(player));
                break;
            case "weapon":

                //Prints players current weapon
                printToConsole.print(textForPrintToConsole.getCurrentWeapon(player));
                break;

            case "inventory":
                showInventory();
                break;
            case "slot":

                if (0 <= (Integer.parseInt(command.getThirdWord()) - 1) &&
                        (Integer.parseInt(command.getThirdWord()) - 1) <
                        player.getInventory().getSize())
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
                    //Prints "Slot is out of range"
                    printToConsole.print(textForPrintToConsole.getSlotIsOutOfRange());
                }
                break;
            default:
                //Prints "Huh?"
                printToConsole.print(textForPrintToConsole.getHuh());
                break;
        }
    }

    public void showInventory()
    {
        String  top = " \u256D";
        String mTop = " \u2551";
        String  middle = " \u2551";
        String  mBottom = " \u2551";
        String  bottom = " \u2570";
        String  slot = "  ";
        for (int i = 0; i < player.getInventory().getSize(); i++)
        {
            String inventoriesItem = "";
            if (player.getInventory().getItem(i)== null)
            {
                inventoriesItem = "empty";
            }
            else
            {
                inventoriesItem = player.getInventory().getItem(i).getName();
            }
            
            
            top += "-" + inventoriesItem.replaceAll(".","-") + "-";
            mTop +=" " + inventoriesItem.replaceAll("."," ") + " ";
            middle +=" " + inventoriesItem + " ";
            mBottom +=" " + inventoriesItem.replaceAll("."," ") + " ";
            bottom += "-" + inventoriesItem.replaceAll(".","-") + "-";
            slot += " " + String.valueOf(i+1) + inventoriesItem.replaceAll(".", " ") + "";
        }
        top = top+ "\u256E";
        mTop = mTop + "\u2551";
        middle =middle+ "\u2551" + "  To get information on an item type \"show slot 1\",slot 2 ...";
        mBottom = mBottom + "\u2551";
        bottom = bottom + "\u256F" + "  To use an item type \"use slot 1\",slot 2 ...";

        String inventory = top +"\n"+ mTop+"\n" + middle+"\n" + mBottom+"\n" + bottom+"\n" + slot;

        //Prints players inventory
        printToConsole.print(textForPrintToConsole.getShowInventory(inventory));
    }

    public void showSlot(int index)
    {
        Item item = player.getInventory().getItem(index);

        if (item instanceof Weapon)
        {
            //Prints players slot if it's a weapon
            printToConsole.print(textForPrintToConsole.getWeapon(item));
        }
        else if (item instanceof Potion)
        {
            //Prints players slot if it's a potion
            printToConsole.print(textForPrintToConsole.getPotion(item));
        }
        else
        {
            //Prints "Slot is empty."
            printToConsole.print(textForPrintToConsole.getSlotIsEmpty());
        }
    }

    public void useSlot(int index)
    {
        Item item = player.getInventory().getItem(index);

        if (item instanceof Weapon)
        {
            player.setCurrentWeapon((Weapon) item);
            //Prints "Your current weapon is now: " + player.getCurrentWeapon().name
            printToConsole.print(textForPrintToConsole.getSetCurrentWeapon(player));
        }
        else if (item instanceof Potion)
        {
            player.setHealth(player.getHealth() + ((Potion) item).getHealthRecovery());
            player.getInventory().removeItem(player.getInventory().getItemIndex(item));
            //Prints "Yom yom ... Your health is now: " + player.getHealth() + "hp"
            printToConsole.print(textForPrintToConsole.getYomYom(player));
        }
        else
        {
            //Prints "Slot is empty."
            printToConsole.print(textForPrintToConsole.getSlotIsEmpty());
        }
    }
    /**
     * Method that prints a help message to the screen.
     */
    private void printHelp() 
    {
        //Prints "Help menu"
        printToConsole.print(textForPrintToConsole.getHelpMenu());
        String input = parser.getUserInput();

        if (parser.getUserInput().contains("1"))
        {
            //Prints "Your command words are:"
            printToConsole.print(textForPrintToConsole.getHelpCommandWords());

            // prints all the available commands to screen
            parser.showCommands();
        }
        else if (parser.getUserInput().contains("2"))
        {
            //Prints "The goals of the game is to defeat the devil"
            printToConsole.print(textForPrintToConsole.getHelpGoals());
     }
        else if (parser.getUserInput().contains("3"))
        {
            //Prints "No tips or tricks available :( "
            printToConsole.print(textForPrintToConsole.getNoTipsAvaiable());
        }
        else
        {
            //Prints "Invalid menu choice"
            printToConsole.print(textForPrintToConsole.getInvalidChoice());
        }
    }

    /**
     * Method for moving around the rooms.
     *
     * @param command go command.
     */
    private void goRoom(Command command) 
    {
        // Checks if the command has a second word and if not prints a message to the screen
        if(!command.hasSecondWord()) 
        {
            //Prints "Go where?"
            printToConsole.print(textForPrintToConsole.getGoWhere());
            return;
        }

        // Sets the String direction to the location of the room you want to go to (east, west, north, south)
        String direction = command.getSecondWord();

        switch (direction){
            case ("up"):
                if (currentMap.roomExists(new Point(player.getLocation().x, player.getLocation().y + 1)))
                {
                    player.setLocation(new Point(player.getLocation().x, player.getLocation().y + 1));

                    //Prints "You entered new room."
                    printToConsole.print(textForPrintToConsole.getYouEnteredANewRoom());

                    currentMap.setRoomHasBeenEntered(player.getLocation());
                    checkRoom();

                }
                else
                    //Prints "You ran into wall :("
                        printToConsole.print(textForPrintToConsole.getYouRanIntoAWall());
                break;
            case "down":
                if (currentMap.roomExists(new Point(player.getLocation().x, player.getLocation().y - 1)))
                {
                    player.setLocation(new Point(player.getLocation().x, player.getLocation().y - 1));

                    //Prints "You entered new room."
                    printToConsole.print(textForPrintToConsole.getYouEnteredANewRoom());

                    currentMap.setRoomHasBeenEntered(player.getLocation());
                    checkRoom();
                }
                else
                    //Prints "You ran into wall :("
                    printToConsole.print(textForPrintToConsole.getYouRanIntoAWall());
                break;
            case "left":
                if (currentMap.roomExists(new Point(player.getLocation().x - 1, player.getLocation().y)))
                {
                    player.setLocation(new Point(player.getLocation().x - 1, player.getLocation().y));

                    //Prints "You entered new room."
                    printToConsole.print(textForPrintToConsole.getYouEnteredANewRoom());

                    currentMap.setRoomHasBeenEntered(player.getLocation());
                    checkRoom();
                }
                else
                    //Prints "You ran into wall :("
                    printToConsole.print(textForPrintToConsole.getYouRanIntoAWall());
                break;
            case "right":
                if (currentMap.roomExists(new Point(player.getLocation().x + 1, player.getLocation().y )))
                {
                    player.setLocation(new Point(player.getLocation().x + 1, player.getLocation().y));

                    //Prints "You entered new room."
                    printToConsole.print(textForPrintToConsole.getYouEnteredANewRoom());

                    currentMap.setRoomHasBeenEntered(player.getLocation());
                    checkRoom();
                }
                else
                    //Prints "You ran into wall :("
                    printToConsole.print(textForPrintToConsole.getYouRanIntoAWall());
                break;
            case "back":
                player.setLocation(new Point(player.getLastLocation().x, player.getLastLocation().y));

                //Prints "You went back to the previous room."
                printToConsole.print(textForPrintToConsole.getYouWentBackToPreviousRoom());
                checkRoom();
                break;
            default:
                //Prints "Go where? No such direction found..."
                printToConsole.print(textForPrintToConsole.getNoSuchDirection());
                break;
        }
    }

    public void checkRoom()
    {

        for (int i = 0; i < currentMap.getNumberOfContent(); i++)
        {
            for (Room room : currentMap.getRoomList())
            {
                if (player.getLocation().x == room.getLocation().x && player.getLocation().y == room.getLocation().y)
                {
                    if (room.getContent(i) instanceof Monster)//Controls if its a monster.
                    {

                        //Prints "There is a monster, you can either do battle or flee!"
                        printToConsole.print(textForPrintToConsole.getThereIsAMonster());

                        //Prints "Your health is currently " + player.getHealth() + "hp"
                        printToConsole.print(textForPrintToConsole.getYouCurrentlyHaveHp(player));

                        //Prints "Monsters health is currently " +((Monster) room.getContent(i)).getHealth() + "hp"
                        printToConsole.print(textForPrintToConsole.getMonstersHealth(room,i));

                        //Prints "Type \"battle\" or \"flee\"." // We need to type more information!
                        printToConsole.print(textForPrintToConsole.getBattleOrFlee());


                        // TODO Eventuelt få delt det ud i metoder.
                        boolean acceptedInput = false;
                        while (!acceptedInput)
                        {                            
                            String input = parser.getUserInput();//returns a String

                            if (input.equals("battle")||input.equals("b"))
                            {
                                acceptedInput = true;
                                battle = new Battle(player, (Monster)room.getContent(i)); // creates a new battle

                                while (!battle.getIsBattleOver())
                                {
                                    //Prints "attack or drink potion"
                                    printToConsole.print(textForPrintToConsole.getAttackOrDrinkPotion());

                                    input = parser.getUserInput();
                                    if (input.toLowerCase().contains("attack") || input.toLowerCase().contains("a"))
                                    {
                                        //Prints status of battle
                                        printToConsole.print(textForPrintToConsole.getBattle(battle));
                                    }
                                    else if (input.toLowerCase().contains("drink"))
                                    {
                                        if (!player.getInventory().potionArrayList().isEmpty())
                                        {
                                            //TODO Change to method
                                            //Prints "Type number to use."
                                            printToConsole.print(textForPrintToConsole.getTypeSlotNumberToUse());

                                            for (int j = 0; j < player.getInventory().potionArrayList().size(); j++)
                                            {
                                                //Prints
                                                printToConsole.print(textForPrintToConsole.getPotionRecovery(j,player));
                                            }
                                            input = parser.getUserInput();
                                            int index = Integer.parseInt(input) -1;
                                            player.setHealth(player.getHealth() +
                                                                     player.getInventory().potionArrayList().get(index).getHealthRecovery());
                                            player.getInventory().removeItem(player.getInventory()
                                                                                     .getItemIndex(player.getInventory().potionArrayList().get(index)));

                                            //Prints "Yom yom ... Your health is now: " + player.getHealth() + "hp"
                                            printToConsole.print(textForPrintToConsole.getYomYom(player));
                                        }
                                        else
                                        {
                                            //Prints "You have no potions :("
                                            printToConsole.print(textForPrintToConsole.getYouHaveNoPotions());
                                        }
                                    }
                                    else
                                    {
                                        //Prints "Type \"attack\" or \"drink\""
                                        printToConsole.print(textForPrintToConsole.getAttackOrDrinkPotion());
                                    }
                                }
                                room.removeContent(i);
                            }
                            else if (input.toLowerCase().contains("flee"))    
                            {
                                acceptedInput = true;
                                player.setLocation(player.getLastLocation());
                                return;
                            }
                            else
                                {
                                    //Prints "Type \"battle\" or \"flee\""
                                    printToConsole.print(textForPrintToConsole.getBattleOrFlee());
                                }
                        }

                    }
                    else if (room.getContent(i) instanceof Guide)
                    {
                        //Prints "There is a helper, you can either \"talk\" , \"flee\" or \"kill\"!"
                        printToConsole.print(textForPrintToConsole.getThereIsAHelper());
                        boolean acceptedInput = false;
                        while (!acceptedInput)
                        {
                            String input = parser.getUserInput();
                            if(input.equals("talk")||input.equals("t"))
                            {
                                acceptedInput = true;
                                //Prints "Hello my name is \"insert name here\" here is a tip ;) ... DON'T DIE!!!"
                                printToConsole.print(textForPrintToConsole.getHelperTalk());
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
                                //Prints "You killed the helper, oh mighty swordsman!"
                                printToConsole.print(textForPrintToConsole.getKilledGuide());
                            }
                            room.removeContent(i);
                        }
                    }
                    else if (room.getContent(i) instanceof Chest)
                    {

                        //Prints "There is a chest, type \"open\" to open!"
                        printToConsole.print(textForPrintToConsole.getThereIsAChest());

                        boolean acceptedInput = false;
                        while (!acceptedInput)
                        {
                            String input = parser.getUserInput();
                            if(input.equals("open")|| input.equals("o"))
                            {
                                boolean chestInput = false;
                                acceptedInput = true;
                                Item item = ((Chest)room.getContent(i)).getItem();

                                if (item instanceof Weapon)
                                {
                                    //Prints content of chest if it's a weapon
                                    printToConsole.print(textForPrintToConsole.getWeapon(item));
                                }
                                else if (item instanceof Potion)
                                {
                                    //Prints content of chest if it's a potion
                                    printToConsole.print(textForPrintToConsole.getPotion(item));
                                }
                                showInventory();

                                //Prints "Do you want to insert this into a slot?"
                                //"Type slot number or \"drop\" to drop."
                                printToConsole.print(textForPrintToConsole.getWhatSlot());

                                input = parser.getUserInput();

                                //A While loop that checks if his input is valid for his inventory size, or if he wants to drop his item.
                                while(!chestInput)
                                {
                                    {
                                        //Checks amount of inventory slots.
                                        for (int j = 0; j < player.getInventory().getSize(); j++)
                                        {

                                            //If input is equal to our inventory size (+1 because array starts at 0), it will stop our loop.
                                            if (Integer.toString(j + 1).equals(input) || input.equals("d") || input.equals("drop"))
                                            {
                                                    chestInput = true;
                                            }
                                        }
                                    }
                                }

                                //Checks through the players inventory
                                for (int j = 0; j < player.getInventory().getSize() ; j++)
                                {

                                    // If the players input is equal to j(+1 because array starts at 0), it will add the item to our designated slot.
                                    if(input.equals(String.valueOf(j+1)))
                                    {
                                        player.getInventory().addItem(item,j);

                                        //Prints "You saved this inventoriesItem in slot: " + (j+1)
                                        printToConsole.print(textForPrintToConsole.getYouSavedItemInThisSlot(j));
                                    }
                                }
                                if (input.equals("drop")||input.equals("d"))
                                {
                                    //Prints "You dropped the inventoriesItem"
                                    printToConsole.print(textForPrintToConsole.getYouDroppedTheItem());
                                }

                                //This removes the chest
                                room.removeContent(i);
                            }
                            else if (input.equals("skip"))
                            {
                                acceptedInput = true;
                            }
                            else
                            {
                                //Prints "Hmm... Wrong command"
                                printToConsole.print(textForPrintToConsole.getWhatDoYouMean());
                            }
                            
                        }
                    }
                    else if (room.getContent(i) instanceof RoomContent)
                    {
                        //Prints "Empty space :("
                        printToConsole.print(textForPrintToConsole.getItsAEmptySpace());
                    }
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
            //Prints "Quit what?"
            printToConsole.print(textForPrintToConsole.getQuitWhat());
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
}
