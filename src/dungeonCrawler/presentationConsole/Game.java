package dungeonCrawler.presentationConsole;

import dungeonCrawler.aqu.*;
import dungeonCrawler.logic.GameText;

import java.awt.*;
import java.util.Date;

/**
 * Class used to get the game running.
 *
 * @author JogGez
 * @version 1.0
 * @since 2017-09-22
 */
public class Game implements IGame
{
    private ILogicFacade logic;
    // Parser for handling the user input
    private Parser parser;
    //Creating print to console object
    private PrintToConsole printToConsole;
    // Stores what room we are currently in
    private IMap map;
    // We are storing the class player's name for player.
    private IPlayer player;
    //Creating print to console object
    private GameText gameText;
    //Create timetracker.
    private ITimeTracker timeTracker;
    //Create highscore..


    /**
     * Class constructor.
     * Used to instantiate the Parser + Print to console + text for print to console
     */
    public  Game()
    {
        // Instantiating the Parser
        parser = new Parser();

        //Instantiating PrintToConsole
        printToConsole = new PrintToConsole();




    }

    /**
     * Start menu
     * Used to call method to start game + Show start logo + Print welcome menu
     */
    public void start()
    {
        gameText = logic.getGameText();
        //Prints welcome logo and welcome text
        printToConsole.print(gameText.getAsciiTitle());
        printToConsole.print(gameText.getWelcomeText());

        // Call menu
        menu();
    }

    /**
     * Method that starts the game and runs till the end of the game.
     */
    public void play()
    {
        //Prints "Enter your name here: "
        printToConsole.print(gameText.getEnterPlayerName());

        // Instantiating player and initiating name

        //player = new Player(parser.getUserInput());
        player = logic.createPlayerInstance(parser.getUserInput());
        map = logic.createMapInstance();

        logic.injectGameText();

        //Prints Start info (passing object player to be able to print name)
        printToConsole.print(gameText.getMessageHello());

        boolean acceptedInput = false;
        while (!acceptedInput)
        {
            String input = parser.getUserInput();//returns a String

            if (input.equals("enter") || input.equals("e"))
            {
                acceptedInput = true;
            }
            else
            {
                //Prints "Type \"enter\" to start the game."
                printToConsole.print(gameText.getEnterToStartGame());
            }

        }
        //Starting timetracker.
        timeTracker = logic.getTimeTracker(new Date());

        // sets the current room as entered
        // Compare the players coordinates with the map room coordinates.
        // There is a for-each loop in setRoomHasBeenEntered, that goes through the coordinates.
        map.setRoomHasBeenEntered(player.getLocation());

        checkRoom();

        // Boolean witch hold the value for exiting the game.
        boolean finished = false;
        // While loop that runs through the entirety of the game. (until the user types "quit")
        while (!finished)
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
            else if (player.getHealth() <= 0)
            {
                // Stops the game if you reach 0 health, and then prints out the line 
                finished = player.getHealth() <= 0;


            }
            else if (timeTracker.calculateRemainingTime() <= 0)
            {
                finished = true;
                //Prints "Your time ran out, and you are now dead"
                printToConsole.print(gameText.getTimeRanOut());
            }

            else if (gameCompleted == true)
            {
                finished = true;
                printToConsole.print(gameText.getIsLuciferDead());
            }

        }
        // Writes the last output before closing the application, also says goodbye to the username
        printToConsole.print(gameText.getThanksForPLaying());
        menu();

    }

    /**
     * Method that prints menu and and acts on userinput
     */
    private void menu()
    {
        //Prints menu
        printToConsole.print(gameText.getMenu());


        switch (parser.getUserInput())
        {
            case "1":
                printToConsole.print(gameText.getEmptyLine());//Prints empty line
                selectDifficulty();
                break;
            case "2":
                printToConsole.print(gameText.getEmptyLine());//Prints empty line
                break;
            case "3":
                printToConsole.print(gameText.getEmptyLine());//Prints empty line
                printHighScore();
                break;
            case "4":
                printToConsole.print(gameText.getEmptyLine());//Prints empty line
                break;
            case "5":
                printToConsole.print(gameText.getEmptyLine());//Prints empty line
                //Print thank you for playing
                printToConsole.print(gameText.getThanksForPLaying());//"Thanks for playing "+player name+". Good bye!"
                System.exit(0);
                break;
        }
    }

    private void selectDifficulty()
    {
        //Prints menu
        printToConsole.print(gameText.getSetDifficultyLevel());


        switch (parser.getUserInput())
        {
        case "1":
            printToConsole.print(gameText.getEmptyLine());//Prints empty line
            logic.setDifficultyLevel(1);
            play();
            break;
        case "2":
            printToConsole.print(gameText.getEmptyLine());//Prints empty line
            logic.setDifficultyLevel(2);
            play();
            break;
        case "3":
            printToConsole.print(gameText.getEmptyLine());//Prints empty line
            logic.setDifficultyLevel(3);
            play();
            break;
        }
    }

    /**
     * Method that prints Highscore
     */
    private void printHighScore()
    {
        //Prints high score
        printToConsole.print(gameText.getHighScore());
        parser.getUserInput();
        this.menu();
    }

    /**
     * Method that prints a help message to the screen.
     */
    private void printHelp()
    {
        //Prints "Help menu"
        printToConsole.print(gameText.getHelpMenu());
        String input = parser.getUserInput();

        if (parser.getUserInput().contains("1"))
        {
            //Prints "Your command words are:"
            printToConsole.print(gameText.getHelpCommandWords());

            // prints all the available commands to screen
            parser.showCommands();
        }
        else if (parser.getUserInput().contains("2"))
        {
            //Prints "The goals of the game is to defeat the devil"
            printToConsole.print(gameText.getHelpGoals());
        }
        else if (parser.getUserInput().contains("3"))
        {
            //Prints "No tips or tricks available :( "
            printToConsole.print(gameText.getNoTipsAvaiable());
        }
        else
        {
            //Prints "Invalid menu choice"
            printToConsole.print(gameText.getInvalidChoice());
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
        boolean quitGame = false;

        // Gets the last commandWord from the Command object
        CommandWord commandWord = command.getCommandWord();

        // Checks if the commandWord is unknown
        if (commandWord == CommandWord.UNKNOWN)
        {
            //Prints: "I don't know what you mean..."
            printToConsole.print(gameText.getWhatDoYouMean());
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
        //Checks if the command is Quit and sets the boolean to true
        else if (commandWord == CommandWord.QUIT || commandWord == CommandWord.EXIT)
        {
            quitGame = quit(command);
        }
        return quitGame;
    }

    /**
     * @param command
     */
    private void use(Command command)
    {
        if (!command.hasSecondWord())
        {
            //Prints "Use what?"
            printToConsole.print(gameText.getUseWhat());
            return;
        }

        switch (command.getSecondWord())
        {
            case "slot":

                if (0 <= (Integer.parseInt(command.getThirdWord()) - 1) &&
                        (Integer.parseInt(command.getThirdWord()) - 1)
                                < player.getInventorySize())
                {
                    for (int i = 0; i < player.getInventorySize(); i++)
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
                    printToConsole.print(gameText.getSlotIsOutOfRange());
                }
                break;
        }
    }

    private void show(Command command)
    {
        if (!command.hasSecondWord())
        {
            //Prints "Show what?"
            printToConsole.print(gameText.getShowWhat());
            return;
        }

        switch (command.getSecondWord())
        {
            case "map":
                printToConsole.print(gameText.getMap());
                break;
            case "exits":
                printToConsole.print(gameText.getExits());
                printToConsole.print(gameText.getEmptyLine());//Prints empty line
                break;
            case "health":
                //Prints players current hp
                printToConsole.print(gameText.getYouCurrentlyHaveHp());
                break;
            case "score":
                //Prints players current score
                printToConsole.print(gameText.getYouCurrentlyHavePoints());
                break;
            case "weapon":

                //Prints players current weapon
                printToConsole.print(gameText.getCurrentWeapon());
                break;

            case "inventory":
                printToConsole.print(gameText.getShowInventory());
                break;
            case "slot":

                if (0 <= (Integer.parseInt(command.getThirdWord()) - 1) &&
                        (Integer.parseInt(command.getThirdWord()) - 1) <
                                player.getInventorySize())
                {
                    for (int i = 0; i < player.getInventorySize(); i++)
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
                    printToConsole.print(gameText.getSlotIsOutOfRange());
                }
                break;
            case "time":
                printToConsole.print("Time left: " + timeTracker.calculateRemainingTime() + " seconds");

                break;

            default:
                //Prints "Huh?"
                printToConsole.print(gameText.getHuh());
                break;
        }
    }

    public void showSlot(int index)
    {
        printToConsole.print(gameText.getItemInfo("inventory",index));
//        IItem item = player.getInventory().getItem(index);
//
//        if (item instanceof IWeapon)
//        {
//            //Prints players slot if it's a weapon
//            printToConsole.print(gameText.getWeapon(item));
//        }
//        else if (item instanceof IPotion)
//        {
//            //Prints players slot if it's a potion
//            printToConsole.print(gameText.getPotion(item));
//        }
//        else
//        {
//            //Prints "Slot is empty."
//            printToConsole.print(gameText.getSlotIsEmpty());
//        }
    }

    public void useSlot(int index)
    {
            IItem item = player.getInventory().getItem(index);

        if (item instanceof IWeapon)
        {
            player.setWeapon((IWeapon) item);
            //Prints "Your current weapon is now: " + player.getCurrentWeapon().name
            printToConsole.print(gameText.getSetCurrentWeapon());
        }
        else if (item instanceof IPotion)
        {
            player.setHealth(player.getHealth() + ((IPotion) item).getHealthRecovery());
            player.getInventory().removeItem(player.getInventory().getItemIndex(item));
            player.setTime(player.getTime() + ((IPotion) item).getTimeRecovery());
            //Prints "Yom yom ... Your health is now: " + player.getHealth() + "hp"
            printToConsole.print(gameText.getPlayerHealth());
            // Prints "Your time is now: " + player.getTime() + "sec"
            printToConsole.print(gameText.getPlayerTime(timeTracker));
        }
        else
        {
            //Prints "Slot is empty."
            printToConsole.print(gameText.getSlotIsEmpty());
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
        if (!command.hasSecondWord())
        {
            //Prints "Go where?"
            printToConsole.print(gameText.getGoWhere());
            return;
        }

        // Sets the String direction to the location of the room you want to go to (east, west, north, south)
        String direction = command.getSecondWord();

        switch (direction)
        {
            case ("up"):
                if (map.roomExists(new Point(player.getLocation().x, player.getLocation().y + 1)))
                {
                    playerMove(new Point(player.getLocation().x, player.getLocation().y + 1));
                }
                else
                    //Prints "You ran into wall :("
                    printToConsole.print(gameText.getYouRanIntoAWall());
                break;
            case "down":
                if (map.roomExists(new Point(player.getLocation().x, player.getLocation().y - 1)))
                {
                    playerMove(new Point(player.getLocation().x, player.getLocation().y - 1));
                }
                else
                    //Prints "You ran into wall :("
                    printToConsole.print(gameText.getYouRanIntoAWall());
                break;
            case "left":
                if (map.roomExists(new Point(player.getLocation().x - 1, player.getLocation().y)))
                {
                    playerMove(new Point(player.getLocation().x - 1, player.getLocation().y));
                }
                else
                    //Prints "You ran into wall :("
                    printToConsole.print(gameText.getYouRanIntoAWall());
                break;
            case "right":
                if (map.roomExists(new Point(player.getLocation().x + 1, player.getLocation().y)))
                {
                    playerMove(new Point(player.getLocation().x + 1, player.getLocation().y));
                }
                else
                    //Prints "You ran into wall :("
                    printToConsole.print(gameText.getYouRanIntoAWall());
                break;
            case "back":
                playerMove(new Point(player.getLastLocation().x, player.getLastLocation().y));
                break;
            default:
                //Prints "Go where? No such direction found..."
                printToConsole.print(gameText.getNoSuchDirection());
                break;
        }
    }

    private void playerMove(Point location)
    {
        if (!map.isRoomLocked(location))
        {
            player.setLocation(location);

            if (location.equals(player.getLastLocation()))
            {
                //Prints "You went back to the previous room."
                printToConsole.print(gameText.getYouWentBackToPreviousRoom());
            }
            else
            {
                //Prints "You entered new room."
                printToConsole.print(gameText.getYouEnteredANewRoom(logic.getCurrentRoom()));
                map.setRoomHasBeenEntered(player.getLocation());
            }

            map.guideMove();
            map.thiefMove();
            checkRoom();
        }
        else if (map.isRoomLocked(location) && logic.getNumberOfAvailableKeys() == 0)
        {
            printToConsole.print("Room is locked and you have no keys :(");
            checkRoom();
        }
        else
        {
            printToConsole.print("Room is locked, but you have a key :)");
            printToConsole.print(gameText.getTypeSlotNumberToUse());

            for (int j = 0; j < logic.getNumberOfAvailableKeys(); j++)
            {
                //Prints
                printToConsole.print(gameText.getKey(j));
            }
            String input = parser.getUserInput();
            int index = Integer.parseInt(input) -1;
            logic.getCurrentRoom().setLocked(false);
            //map.unlockRoom(location);

            logic.useKey(index);

            //Prints "Yom yom ... Your health is now: " + player.getHealth() + "hp"
            printToConsole.print("Room has been unlocked");

            player.setLocation(location);

            //Prints "You entered new room."
            printToConsole.print(gameText.getYouEnteredANewRoom(logic.getCurrentRoom()));

            map.setRoomHasBeenEntered(player.getLocation());
            map.guideMove();
            map.thiefMove();
            checkRoom();
        }

    }

    public void checkRoom()
    {
        if (map.roomContainsGuide())
        {
            printToConsole.print(gameText.getGuide());
            //map.getItemFromGuide();
        }

        if (map.roomContainsThief())
        {
            printToConsole.print(gameText.getThief());
            map.removeThief();
        }





        for (int i = 0; i < map.getNumberOfContent(); i++)
        {
            switch (map.checkRoomContent(i))
            {
            case "Monster":
                //Prints info about monster.
                printToConsole.print(gameText.getContentInfo(i));
                //Prints "Your health is currently " + player.getHealth() + "hp"
                printToConsole.print(gameText.getPlayerInfo());
                //Prints "Type \"battle\" or \"flee\"." // We need to type more information!
                printToConsole.print(gameText.getBattleOrFlee());

                while (true)
                {
                    String input = parser.getUserInput();//returns a String
                    if (input.equals("battle") || input.equals("b"))
                    {
                        startBattle(logic.doBattle(i));

                        logic.getCurrentRoom().removeContent(i);

                        break;
                    }
                    else if (input.equals("flee") || input.equals("f"))
                    {
                        player.setLocation(player.getLastLocation());
                        break;
                    }
                    else
                    {
                        printToConsole.print(gameText.getWhatDoYouMean());
                    }
                }
                if (i < map.getNumberOfContent() - 1)
                {
                    parser.userPressEnter();
                }
                break;
            case "Chest":
                //Prints info about chest.
                printToConsole.print(gameText.getContentInfo(i));

                while (true)
                {
                    String input = parser.getUserInput();
                    if (input.equals("open") || input.equals("o"))
                    {

                        printToConsole.print(gameText.getItemInfo("chest",i));

                        printToConsole.print(gameText.getShowInventory());

                        //Prints "Do you want to insert this into a slot?"
                        //"Type slot number or \"drop\" to drop."
                        printToConsole.print(gameText.getWhatSlot());


                        boolean chestInput = false;
                        //A While loop that checks if his input is valid for his inventory size, or if he wants to drop his item.
                        while (!chestInput)
                        {
                            input = parser.getUserInput();


                            //Checks amount of inventory slots.
                            for (int j = 0; j < player.getInventorySize(); j++)
                            {

                                //If input is equal to our inventory size (+1 because array starts at 0), it will stop our loop.
                                if (Integer.toString(j + 1).equals(input) || input.equals("d") || input.equals("drop"))
                                {
                                    chestInput = true;
                                }
                            }
                        }

                        //Checks through the players inventory
                        for (int j = 0; j < player.getInventorySize(); j++)
                        {

                            // If the players input is equal to j(+1 because array starts at 0), it will add the item to our designated slot.
                            if (input.equals(String.valueOf(j + 1)))
                            {
                                logic.saveItemToInventory(j,i);
//                                player.getInventory().addItem(item, j);

                                //Prints "You saved this inventoriesItem in slot: " + (j+1)
                                printToConsole.print(gameText.getYouSavedItemInThisSlot(j));
                            }
                        }
                        if (input.equals("drop") || input.equals("d"))
                        {
                            //Prints "You dropped the inventoriesItem"
                            printToConsole.print(gameText.getYouDroppedTheItem());
                        }

                        //This removes the chest
                        logic.getCurrentRoom().removeContent(i);
                        break;
                    }
                    else if (input.equals("skip") || input.equals("s"))
                    {
                        break;
                    }
                    else
                    {
                        //Prints "Hmm... Wrong command"
                        printToConsole.print(gameText.getWhatDoYouMean());
                    }
                }
                if (i < map.getNumberOfContent() - 1)
                {
                    parser.userPressEnter();
                }
                break;
            case "Guide":
                //Prints info about guide.
                printToConsole.print(gameText.getContentInfo(i));

                while (true)
                {
                    //Prints "There is a helper, you can either \"talk\" , \"flee\" or \"kill\"!"
                    printToConsole.print(gameText.getThereIsAGuide());

                    String input = parser.getUserInput();
                    if (input.equals("talk") || input.equals("t"))
                    {
                        //Prints "Hello my name is \"insert name here\" here is a tip ;) ... DON'T DIE!!!"
                        printToConsole.print(gameText.getHelperTalk());
                    }
                    else if (input.equals("skip")|| input.equals("s"))
                    {
                        break;
                    }
                    else if (input.equals("kill")|| input.equals("k"))
                    {
                        //Prints "You killed the helper, oh mighty swordsman!"
                        printToConsole.print(gameText.getKilledGuide());
                        logic.getCurrentRoom().removeContent(i);
                        break;
                    }

                }

                if (i < map.getNumberOfContent() - 1)
                {
                    parser.userPressEnter();
                }
                break;
            case "":
            }


//            for (IRoom room : map.getRoomList())
//            {
//                if (player.getLocation().equals(room.getLocation()))
//                {
//                    //Prints "You entered new room."
//                    printToConsole.print(gameText.getYouEnteredANewRoom(room));
//                    if (room.getContent(i) instanceof IMonster)//Controls if its a monster.
//                    {
//
//                        //Prints info about monster.
//                        printToConsole.print(gameText.getContentInfo((IMonster) (room.getContent(i))));
//
//                        //Prints "Your health is currently " + player.getHealth() + "hp"
//                        printToConsole.print(gameText.getPlayerInfo(player));
//
//                        //Prints "Type \"battle\" or \"flee\"." // We need to type more information!
//                        printToConsole.print(gameText.getBattleOrFlee());
//
//                        boolean acceptedInput = false;
//                        while (!acceptedInput)
//                        {
//                            String input = parser.getUserInput();//returns a String
//
//                            if (input.equals("battle") || input.equals("b"))
//                            {
//                                acceptedInput = true;
//                                battle = logic.doBattle((IMonster) room.getContent(i));
//                                // creates a new battle
//
//                                // calls the battle sequence method.
//                                battleSequence();
//
//                                room.removeContent(i);
//
//                                if (i < map.getNumberOfContent() - 1)
//                                {
//                                    parser.userPressEnter();
//                                }
//
//
//                            }
//                            else if (input.toLowerCase().contains("flee"))
//                            {
//                                acceptedInput = true;
//                                player.setLocation(player.getLastLocation());
//                                return;
//                            }
//                            else
//                            {
//                                //Prints "Type \"battle\" or \"flee\""
//                                printToConsole.print(gameText.getBattleOrFlee());
//                            }
//                        }
//                        room.removeContent(i);
//
//                    }
//                    else if (room.getContent(i) instanceof IGuide)
//                    {
//
//                        boolean acceptedInput = false;
//                        while (!acceptedInput)
//                        {
//                            //Prints "There is a helper, you can either \"talk\" , \"flee\" or \"kill\"!"
//                            printToConsole.print(gameText.getThereIsAGuide());
//
//                            String input = parser.getUserInput();
//                            if (input.equals("talk") || input.equals("t"))
//                            {
//                                //Prints "Hello my name is \"insert name here\" here is a tip ;) ... DON'T DIE!!!"
//                                printToConsole.print(gameText.getHelperTalk());
//                            }
//                            else if (input.equals("skip"))
//                            {
//                                acceptedInput = true;
//
//                            }
//                            else if (input.equals("kill"))
//                            {
//                                acceptedInput = true;
//                                //Prints "You killed the helper, oh mighty swordsman!"
//                                printToConsole.print(gameText.getKilledGuide());
//                                room.removeContent(i);
//                            }
//
//                        }
//
//                        if (i < map.getNumberOfContent() - 1)
//                        {
//                            parser.userPressEnter();
//                        }
//
//                    }
//                    else if (room.getContent(i) instanceof IChest)
//                    {
//
//                        //Prints "There is a chest, type \"open\" to open!"
//                        printToConsole.print(gameText.getThereIsAChest());
//
//                        boolean acceptedInput = false;
//                        while (!acceptedInput)
//                        {
//                            String input = parser.getUserInput();
//                            if (input.equals("open") || input.equals("o"))
//                            {
//                                boolean chestInput = false;
//                                acceptedInput = true;
//                                IItem item = ((IChest) room.getContent(i)).getItem();
//
//                                if (item instanceof IWeapon)
//                                {
//                                    //Prints content of chest if it's a weapon
//                                    printToConsole.print(gameText.getWeapon(item));
//                                }
//                                else if (item instanceof IPotion)
//                                {
//                                    //Prints content of chest if it's a potion
//                                    printToConsole.print(gameText.getPotion(item));
//                                }
//                                showInventory();
//
//                                //Prints "Do you want to insert this into a slot?"
//                                //"Type slot number or \"drop\" to drop."
//                                printToConsole.print(gameText.getWhatSlot());
//
//
//                                //A While loop that checks if his input is valid for his inventory size, or if he wants to drop his item.
//                                while (!chestInput)
//                                {
//                                    input = parser.getUserInput();
//
//
//                                    //Checks amount of inventory slots.
//                                    for (int j = 0; j < player.getInventory().getSize(); j++)
//                                    {
//
//                                        //If input is equal to our inventory size (+1 because array starts at 0), it will stop our loop.
//                                        if (Integer.toString(j + 1).equals(input) || input.equals("d") || input.equals("drop"))
//                                        {
//                                            chestInput = true;
//                                        }
//                                    }
//
//                                }
//
//                                //Checks through the players inventory
//                                for (int j = 0; j < player.getInventory().getSize(); j++)
//                                {
//
//                                    // If the players input is equal to j(+1 because array starts at 0), it will add the item to our designated slot.
//                                    if (input.equals(String.valueOf(j + 1)))
//                                    {
//                                        player.getInventory().addItem(item, j);
//
//                                        //Prints "You saved this inventoriesItem in slot: " + (j+1)
//                                        printToConsole.print(gameText.getYouSavedItemInThisSlot(j));
//                                    }
//                                }
//                                if (input.equals("drop") || input.equals("d"))
//                                {
//                                    //Prints "You dropped the inventoriesItem"
//                                    printToConsole.print(gameText.getYouDroppedTheItem());
//                                }
//
//                                //This removes the chest
//                                room.removeContent(i);
//                            }
//                            else if (input.equals("skip") || input.equals("s"))
//                            {
//                                acceptedInput = true;
//                            }
//                            else
//                            {
//                                //Prints "Hmm... Wrong command"
//                                printToConsole.print(gameText.getWhatDoYouMean());
//                            }
//                        }
//                        if (i < map.getNumberOfContent() - 1)
//                        {
//                            parser.userPressEnter();
//                        }
//                    }
//                    else if (room.getContent(i) == null)
//                    {
//                        //Prints "Empty space :("
//                        printToConsole.print(gameText.getItsAEmptySpace());
//                    }
//                }
//            }

        }

        if (map.hasAllRoomBeenEntered())
        {
            parser.userPressEnter();
            lastBossBattle();

        }
    }

    public void startBattle(IBattle battle)
    {
        while (!battle.getIsBattleOver())
        {
            //Prints "attack or drink potion"
            printToConsole.print(gameText.getAttackOrDrinkPotion());

            String input = parser.getUserInput();
            if (input.toLowerCase().contains("attack") || input.toLowerCase().contains("a"))
            {
                //Prints status of battle
                printToConsole.print(gameText.getBattle(battle));
            }
            else if (input.toLowerCase().contains("drink"))
            {
                drinkPotion();
            }
        }
    }

    private void drinkPotion()
    {
        if (logic.getNumberOfAvailablePotions() > 0)
        {

            //Prints "Type number to use."
            printToConsole.print(gameText.getTypeSlotNumberToUse());

            for (int j = 0; j < logic.getNumberOfAvailablePotions(); j++)
            {
                //Prints
                printToConsole.print(gameText.getPotionRecovery(j));
            }
            String input = parser.getUserInput();
            int index = Integer.parseInt(input) - 1;

            logic.usePotion(index);

            //Prints "Your health is now: " + player.getHealth() + "hp"
            printToConsole.print(gameText.getPlayerHealth());
        }
        else
        {
            //Prints "You have no potions :("
            printToConsole.print(gameText.getYouHaveNoPotions());
        }
    }

    boolean gameCompleted = false;

    public void lastBossBattle()
    {
        // "The eye of Sauron teleports you to the lair of the demon realm"
        printToConsole.print(gameText.getAllRoomsEntered());

        printToConsole.print(gameText.getMonstersInfo(logic.getLucifer()));

        printToConsole.print(gameText.getPlayerInfo());


        startBattle(logic.doBattle(logic.getLucifer()));

        if (logic.getLucifer().getHealth() <= 0)
        {
            gameCompleted = true;
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
        if (command.hasSecondWord())
        {
            //Prints "Quit what?"
            printToConsole.print(gameText.getQuitWhat());
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void injectLogic(ILogicFacade logicLayer)
    {
        logic = logicLayer;
    }

}
