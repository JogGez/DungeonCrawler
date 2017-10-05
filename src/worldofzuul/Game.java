package worldofzuul;

// Class used to run the game
public class Game
{
    // Parser for handling the user input
    private Parser parser;
    // Stores what room we are currently in
    private Room currentRoom;
    // We are storing the class player's name for player.
    private Player player;
    // Stores the monster/monsters we can meet
    private Monster monster;
        
    // Constructor that launches the class when instantiated through our Main Method
    public Game() 
    {
        // Runs the createRooms() method
        createRooms();
        // Initializes the Parser
        parser = new Parser();
    }

    // Method that creates all the room in the game
    private void createRooms()
    {
        // Create 8 instances of the class Room
        Room outside, chapel, dormitory, lab, bath, library, kitchen, forge, cave;
      
        // Initializes the 5 rooms and sets a description for each
        outside = new Room("outside the cave entrance");
        cave = new Room("Inside the cave");
        chapel = new Room("in the old chapel");
        dormitory = new Room("in the old forgotten dormitory");
        lab = new Room("in the abboned lab");
        bath = new Room("in the bathroom");
        library = new Room("in the dusty library");
        kitchen = new Room("in the smelly kitchen");
        forge = new Room("in the cold forge");
        
        // Adds 3 exits to the room "outside"
        outside.setExit("east", chapel);
        outside.setExit("south", lab);
        outside.setExit("west", dormitory);

        // Adds 1 exits to the room "theatre"
        chapel.setExit("west", outside);

        // Adds 1 exits to the room "pub"
        dormitory.setExit("east", outside);

        // Adds 2 exits to the room "lab"
        lab.setExit("north", outside);
        lab.setExit("east", kitchen);

        // Adds 1 exits to the room "office"
        kitchen.setExit("west", lab);

        // Sets the current room to "outside"
        currentRoom = outside;
    }

    // Method that starts the game and runs till the end of the game
    public void play() 
    {            
        // Call printWelcome() method used to write a welcome message
        printWelcome();
        // Calls for the monster constructor, and creates the object monster
        monster = new Monster();
        // Sets its health to 15
        monster.setHealth(15);
        // Calls on the constructor player, and creates the object player
        player = new Player();
        //Prints out the string  beneath
        System.out.print("Enter your name here: ");
        // Gets the name from the parser class, which reads the next input line from the user. Which is going to be the current name for the player.
        player.setName(parser.playerName());
        // Sets the players health to 100, default
        player.setHealth(100);
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

     // Method that prints a welcome message to the screen
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Dungeon Crawler!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    // This method processes the command recieved from the parser and returns whether or not to quit
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
        // Shows the players current health
        else if (commandWord == CommandWord.HEALTH)
        {
                System.out.println("your current health is " + player.getHealth());
        } 
        else if (commandWord == CommandWord.ATTACK && !monster.isDead())
        {
                System.out.println("You deal " + player.power(monster) + " the monster's health is now " + monster.getHealth());
                if (monster.isDead()) {
                System.out.println("You have defeated the monster! God bless you.");
                }
        }
        // Checks if the command is Quit and sets the boolean to true
        else if (commandWord == CommandWord.QUIT) 
        {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

     // Method that prints a help message to the screen
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        // prints all the available commands to screen 
        parser.showCommands();
    }

    // Method for moving around the rooms
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

        // Create a placeholder for the room you want to go to
        Room nextRoom = currentRoom.getExit(direction);

        // Checks if the nextRoom exist
        if (nextRoom == null) 
        {
            System.out.println("There is no door!");
        }
        // 
        else
        {
            // Sets the currentRoom to the placeholder and prints out a message
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    // This is the quit method that return a true or false value. 
    // This will return a boolean value of true if there is no other words then quit
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
}
