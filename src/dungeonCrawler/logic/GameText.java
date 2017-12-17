package dungeonCrawler.logic;


import dungeonCrawler.aqu.*;
import dungeonCrawler.data.HighScore;
import dungeonCrawler.presentationConsole.CommandWord;
import java.awt.*;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Used for all Strings in the game
 * @author Peter Jonathan, Joakim, Linea, Frederik, Simon og Brian
 */
public class GameText implements Serializable
{
    Player player;
    Map map;

    /**
     * Sets type to String
     * @param type set the type to console or gui.
     */
    public void setType(String type)
    {
        this.type = type;
    }

    private String type = "";


    /**
     * Constructor for GameText
     */
    public GameText()
    {
    }

    /**
     * Used to get player and map info
     * @param player Set the player reference.
     * @param map Set the map reference.
     */
    public void injectVariables(Player player, Map map)
    {
        this.player = player;
        this.map = map;
    }

    /**
     * Title of ascii
     * @return String
     */
    public String getAsciiTitle()
    {
        return GameTextASCII.getTitle();
    }

    /**
     * String for welcome start
     * @return String
     */
    public String getWelcomeText()
    {
        return "Welcome to Dungeon Crawler!"+
                "\nThis is a newly created, incredible adventure game.";
    }

    /**
     * String for console game menu
     * @return String
     */
    public String getMenu()
    {
        return "Main Menu"+
                "\n1. Start New Game"+
                "\n2. Load Saved Game"+
                "\n3. Show High Score"+
                "\n4. Settings"+
                "\n5. Exit";
    }

    /**
     * String for difficulty level
     * @return String
     */
    public String getSetDifficultyLevel()
    {
        return "Select Difficulty Level"+
                "\n1. Easy"+
                "\n2. Normal"+
                "\n3. Hard";
    }

    /**
     * String for player name
     * @return String
     */
    public String getEnterPlayerName()
    {
        return "Enter your name here: ";
    }

    /**
     * String for start game
     * @return String
     */
    public String getEnterToStartGame()
    {
        return type + " \"enter\" to start the game.";
    }

    /**
     * String for hello message
     * @return String
     */
    public String getMessageHello()
    {

        return GameTextASCII.getGateKeeper() + "\n\n"+
                "Well... hello there "+player.getName()+"! "+"I'm the gatekeeper."+
                "\nPrepare to enter the dungeon and find that devil!"+
                "\n" + type + " '"+ CommandWord.HELP + "' if you ever need help."+
                "\n" + type + " \'enter\' to enter the DUNGEON...";
    }

    /**
     * String for when game ends
     * @return String
     */
    public String getThanksForPLaying()
    {
        return "Thanks for playing "+player.getName()+". Goodbye!";
    }

    /**
     * String for death
     * @return String
     */
    public String getYouHaveDied()
    {
        return "You have died :(";
    }

    /**
     * String for invalid command
     * @return String
     */
    public String getWhatDoYouMean()
    {
        String[] wrongUserInput = new String[]
        {"I don't know what you mean...", "What?", "Invalid Command", "Look at your keyboard when you type...",
            "Sorry, come again", "Huh?"        
        };
        
        return wrongUserInput[new Random().nextInt(wrongUserInput.length)];
    }

    /**
     * String for use what
     * @return String
     */
    public String getUseWhat()
    {
        return "Use what?";
    }

    /**
     * String for inventory slot of out of bounds
     * @return String
     */
    public String getSlotIsOutOfRange()
    {
        return "Slot is out of range";
    }

    /**
     * String for show what
     * @return String
     */
    public String getShowWhat()
    {
        return "Show what?";
    }

    /**
     * String for help menu
     * @return String
     */
    public String getHelpMenu()
    {
        return "Welcome to the Help menu system..."+
                "\nChoose one of the following options."+
                "\n---------------------------------------"+
                "\n1. Game Commands"+
                "\n2. Game Goals"+
                "\n3. Game Tips & Tricks";
    }

    /**
     * String for valid commands
     * @return String
     */
    public String getHelpCommandWords()
    {
        return "Your command words are:";
    }

    /**
     * String for goals
     * @return String
     */
    public String getHelpGoals()
    {
        return "The goals of the game is to defeat the devil";
    }

    /**
     * String for no available tips
     * @return String
     */
    public String getNoTipsAvaiable()
    {
        return "No tips or tricks available :( ";
    }

    /**
     * String for invalid choice
     * @return String
     */
    public String getInvalidChoice()
    {
        return "Invalid menu choice";
    }

    /**
     * String for players current health
     * @return String
     */
    public String getYouCurrentlyHaveHp()
    {
        return "You currently have: "+player.getHealth()+ " hp";
    }

    /**
     * String for players current score
     * @return String
     */
    public String getYouCurrentlyHavePoints()
    {
        return "You currently have: "+player.getScore()+" points";
    }

    /**
     * String for name
     * @return String
     */
    public String getName()
    {
        return "Name: ";
    }

    /**
     * String for huh
     * @return String
     */
    public String getHuh()
    {
        return "Huh?";
    }

    /**
     * String for go where
     * @return String
     */
    public String getGoWhere()
    {
        return "Go where?";
    }

    /**
     * String for entered new room
     * @param room Set the room you entered.
     * @return String
     */
    public String getYouEnteredANewRoom(IRoom room)
    {
        return "You entered " + room.getName()
                + "\nYou see " + room.getDescription()
                + "\n";
    }

    /**
     * String for walking to a wall
     * @return String
     */
    public String getYouRanIntoAWall()
    {
        return "You ran into wall :(";
    }

    /**
     * String for go back
     * @return String
     */
    public String getYouWentBackToPreviousRoom()
    {
        return "You went back to the previous room.";
    }

    /**
     * String for unavailable direction
     * @return String
     */
    public String getNoSuchDirection()
    {
        return "Go where? No such direction found...";
    }

    /**
     * String for room exits
     * @return String
     */
    public String getExits()
    {
        ArrayList<String> exitList = new ArrayList<>();

        if (map.roomExists(new Point(player.getLocation().x - 1, player.getLocation().y)))
        {
            exitList.add("Left");
        }
        if (map.roomExists(new Point(player.getLocation().x + 1, player.getLocation().y)))
        {
            exitList.add("Right");
        }
        if (map.roomExists(new Point(player.getLocation().x, player.getLocation().y + 1)))
        {
            exitList.add("Up");
        }
        if (map.roomExists(new Point(player.getLocation().x, player.getLocation().y - 1)))
        {
            exitList.add("Down");
        }

        int counter = 1;
        String output = "";
        for (String exit : exitList)
        {
            output += counter + ". " + exit + "\n";
            counter++;
        }

        return output;
    }

    /**
     * String for inventory items
     * @param inventory Set the inventory.
     * @return String
     */
    public String getInventory(IInventory inventory)
    {
        String top = " ╔";
        String mTop = " ║";
        String middle = " ║";
        String mBottom = " ║";
        String bottom = " \u2570";
        String slot = "  ";
        for (int i = 0; i < inventory.getSize(); i++)
        {
            String inventoriesItem;
            if (inventory.getItem(i) == null)
            {
                inventoriesItem = "empty";
            }
            else
            {
                inventoriesItem = inventory.getItem(i).getName();
                if (inventory.getItem(i) instanceof Weapon) inventoriesItem += "(Weapon)";
                else if (inventory.getItem(i) instanceof Potion) inventoriesItem += "(Potion)";
                else if (inventory.getItem(i) instanceof Key) inventoriesItem += "(Key)";
                else if (inventory.getItem(i) instanceof Special) inventoriesItem += "(Special)";
            }

            top += "-" + inventoriesItem.replaceAll(".", "-") + "--";
            mTop += " " + inventoriesItem.replaceAll(".", " ") + " |";
            middle += " " + inventoriesItem + " |";
            mBottom += " " + inventoriesItem.replaceAll(".", " ") + " |";
            bottom += "-" + inventoriesItem.replaceAll(".", "-") + "--";

            slot += " " + String.valueOf(i + 1) + inventoriesItem.replaceAll(".", " ") + " ";
        }
        top = top + "╗";
        mTop = mTop + "║";
        middle = middle + "║";
        mBottom = mBottom + "║";
        bottom = bottom + "\u256F";

        return top + "\n" + mTop + "\n" + middle + "\n" + mBottom + "\n" + bottom + "\n" + slot;
    }

    /**
     * String for room content
     * @param index set the index to get content from.
     * @return String
     */
    public String getContentInfo(int index)
    {
        String contentInfo;

        if (map.getCurrentRoom().getContent(index) instanceof Monster)
        {
            Monster monster = (Monster)map.getCurrentRoom().getContent(index);

            contentInfo = getMonstersInfo(monster);
        }
        else if (map.getCurrentRoom().getContent(index) instanceof Chest)
        {
            Chest chest = (Chest)map.getCurrentRoom().getContent(index);

            contentInfo = getChestInfo(chest);
        }
        else if (map.getCurrentRoom().getContent(index) instanceof Guide)
        {
            Guide guide = (Guide)map.getCurrentRoom().getContent(index);

            contentInfo = getGuideInfo(guide);
        }
        else
        {
            return "";
        }

        return contentInfo;
    }

    /**
     * String for guide info
     * @param guide set the guide to get info from.
     * @return String
     */
    private String getGuideInfo(Guide guide)
    {
        return  guide.getAscii() +
                "\n" + "Name: " + guide.getName() +
                "\n" + "Description: " + guide.getDescription();
    }

    /**
     * String for item info
     * @param item set the item to get info from.
     * @return String
     */
    public String getItemInfo(IItem item)
    {
        String contentInfo;

        if (item instanceof Weapon)
        {
            contentInfo = item.getAscii()+
                "\nName: " + item.getName()+
                "\nPOWER: " + ((Weapon) item).getPower() +
                "\nMULTIPLIER: " + ((Weapon) item).getMultiplier() + "x"+
                "\n" + item.getDescription();
        }
        else if (item instanceof Potion)
        {
            contentInfo =  item.getAscii()+
                    "\nName: " +  item.getName()+
                    "\nHEALTH RECOVERY: " + ((Potion) item).getHealthRecovery()+
                    "\nTIME RECOVERY: " + ((Potion) item).getTimeRecovery()+
                    "\n"+ item.getDescription();
        }
        else if (item instanceof Key)
        {
            contentInfo =  item.getAscii()+
                    "\nName: " +  item.getName()+
                    "\n"+ item.getDescription();
        }
        else if (item instanceof Special)
        {
            contentInfo =  item.getAscii()+
                    "\nName: " +  item.getName()+
                    "\n"+ item.getDescription();
        }
        else
        {
            return "Empty Slot.";
        }


        return contentInfo;
    }

    /**
     * String for player info
     * @return String
     */
    public String getPlayerInfo()
    {
        return  "Your Health: " + player.getHealth() +
                "\n" + "Your Power: " + player.getWeapon().getPower() +
                "\n" + "Your Multiplier: " + player.getWeapon().getMultiplier();
    }

    /**
     * String for monster info
     * @param monster set the monster to get info from.
     * @return String
     */
    public String getMonstersInfo(IMonster monster)
    {
        return  monster.getAscii() +
                "\n" + "Name: " + monster.getName() +
                "\n" + "Description: " + monster.getDescription() +
                "\n" + "Health: " + monster.getHealth() +
                "\n" + "Power: " + monster.getPower();
    }

    /**
     * String for chest info
     * @param chest set the chest to get info from.
     * @return String
     */
    private String getChestInfo(IChest chest)
    {
        return  chest.getAscii() +
                "\n" + "Name: " + chest.getName() +
                "\n" + "Description: " + chest.getDescription();
    }

    /**
     * String for battle or run
     * @return String
     */
    public String getBattleOrFlee()
    {
        return type + " \"attack\" or \"flee\".";
    }

    /**
     * String for Lucifer battle
     * @return String
     */
    public String getFinaleBattle()
    {
        return type + " \"attack\" you can't flee from the devil himself!.";
    }

    /**
     * String for attack or drink
     * @return String
     */
    public String getAttackOrDrinkPotion()
    {
        return "Attack or drink potion";
    }

    /**
     * String for battled started
     * @param battle set battle to get info about.
     * @return String
     */
    public String getBattle(IBattle battle)
    {
        return battle.start();
    }

    /**
     * String for empty slot
     * @return String
     */
    public String getSlotIsEmpty()
    {
        return "Slot is empty.";
    }

    /**
     * String for current weapon
     * @return String
     */
    public String getSetCurrentWeapon()
    {

        return "Your current weapon is now: " + ((IItem)player.getWeapon()).getName();
    }

    /**
     * String for players current health
     * @return String
     */
    public String getPlayerHealth()
    {
        return "Your health is: " + player.getHealth() + "hp";
    }

    /**
     * String for players current time
     * @param timeTracker set the time tracker to get time from.
     * @return String
     */
    public String getPlayerTime(ITimeTracker timeTracker)
    {
        return "Your time is now: " + timeTracker.calculateRemainingTime()+ "sec";
    }

    /**
     * String for what key to use
     * @return String
     */
    public String getTypeSlotNumberToUse()
    {
        return type + " which key to use.";
    }

    /**
     * String for health potions healing
     * @param index set the index to get potion from.
     * @return String
     */
    public String getPotionRecovery(int index)
    {
        return (index+1) + ". Potion:" + player.getInventory().potionArrayList().get(index).getHealthRecovery();
    }

    /**
     * String for No potions left
     * @return String
     */
    public String getYouHaveNoPotions()
    {
        return "You have no potions :(";
    }

    /**
     * String for encountered guide
     * @return String
     */
    public String getThereIsAGuide()
    {
        return "There is a guide, you can either " + type + " \"talk\" , \"skip\" , \"flee\" or \"attack\"!";
    }

    /**
     * String for guide talking
     * @return String
     */
    public String getGuideTalk()
    {
        String[] guideAdvice = new String[]
                {
                        "Hello my name is \"insert name here\" here is a tip ;) ... DON'T DIE!!!",
                        "You must kill all the monsters before getting to the Devil.",
                        "Remember to check how much time is left and act accordingly.",
                        "Remember to check you heath before getting into battles.",
                        "Remember to equip the weapon you want.",
                        "Killing guides will accomplish nothing. ",
                        "Skipping will help you get a faster time, but you may miss somethings.",
                        "Teleporting to a locked room will make you waste you special.",
                        "BOMBs are useful if you know where the monsters are.",
                        "Live long and prosper!",
                        "This game was made in 87 days by 6 coding novices and 1 not so much. ",
                        "Remember to debug your code before pushing to github.",
                        "Make love not war.",
                        "All your base are belong to us.",
                        "Chances of getting the keys to locked room in chests are very slim. (better find a Merchant)",
                        "Thieves will steal your chests if you leave them alone.",
                        "This array contains over 40 answers.",
                        "If it rains, better wear a raincoat :)",
                        "This game contains a lot of random generations.",
                        "You can't drop items collected, but you can replace them.",
                        "Internet neutrality is a human right",
                        "Doubt kills more dreams, than failure ever will.",
                        "May the Force be with you.",
                        "If you keep talking to me, time will run out.",
                        "Merchants will trade you stuff, one item for an item.",
                        "Drink potions to restore you life or get more time.",
                        "ASCII art was once very popular.",
                        "JAVA and C# are almost identical, with a few exceptions.",
                        "Thieves are weak, and will die instantly when you find them.",
                        "Each weapon in the game has different hit powers, multipliers and chance of missings",
                        "Finding or creating ASCII art takes a lot of time.",
                        "You can't use special powers or switch weapons while investigating a room.",
                        "Difficulty setting in the game will actually change the size of the map and some other things.",
                        "You can save the game at any time.",
                        "If you die at any time in the game you must start over. (or load a saved game.)",
                        "Weapon can be equipped, potions, keys and specials are gone after use.",
                        "If you would like to read all the answers, check the GameText class.",
                        "Your high score is calculated from how much time it takes you to complete the game.",
                        "You shall not pass!!! ( just kiddin i'am a guide not a wizard :( ) ",
                        "Each game you play will be different. I guarantee it.",
                        "This list took about an hour to make... Makes you wonder...",
                        "There are only 2 genders, everything else is a mental disorder.",
                        "The world is truly flat, no doubt about that.",
                        "Leave me alone :("
                };

        return guideAdvice[new Random().nextInt(guideAdvice.length)];
    }

    /**
     * String for killing the guide
     * @return String
     */
    public String getKilledGuide()
    {
        return "You killed the guide, oh mighty swordsman!";
    }

    /**
     * String for encounter of the chest
     * @return String
     */
    public String getThereIsAChest()
    {
        return "There is a chest, " + type + " \"open\" to open!" +
                                "\n Or " + type + " \"skip\" to skip it!";
    }

    /**
     * String for what slot
     * @return String
     */
    public String getWhatSlot()
    {
        return "Do you want to insert this into a slot?"+
                "\n" + type + " slot number or \"skip\" to drop.";
    }

    /**
     * String for item saved to slot
     * @param index set the index to show.
     * @return String
     */
    public String getYouSavedItemInThisSlot(int index)
    {
        return "You saved this item in slot: " + (index+1);
    }

    /**
     * String for item dropped
     * @return String
     */
    public String getYouDroppedTheItem()
    {
        return "You dropped the item";
    }

    /**
     * String for quit what
     * @return String
     */
    public String getQuitWhat()
    {
        return "Quit what?";
    }


    /**
     * String for time has run out
     * @return String
     */
    public String getTimeRanOut ()
    {
        return "You have died, time ran out." +
                "\n" + "Thanks for playing.";
    }

    /**
     * String for all rooms has been entered
     * @return String
     */
    public String getAllRoomsEntered ()
    {
        return "The eye of Sauron teleports you to the lair of the demon realm";
    }

    /**
     * String for Lucifer defeated
     * @return String
     */
    public String getIsLuciferDead ()
    {
        return "You defeated the devil!" +
        "\nBut the princess is in another castle... and the game is over.";
    }

    /**
     * String for no keys
     * @return String
     */
    public String getRoomIsLockedNoKey()
    {
        return "Room is locked and you have no keys :(";

    }

    /**
     * String for have a key
     * @return String
     */
    public String getRoomIsLockedHaveKey()
    {
        return "Room is locked, but you have a key :)";
    }

    /**
     * String for Map
     * @return String
     */
    public String getMap()
    {
        ArrayList<String> mapList = new ArrayList<>();
        String top = "  " + " ╔-------";
        String space = "  " + " ║-------";
        String bottom = "  " + " ╰-------";
        String xCoordinates = "       ";

        int j = 0;
        for (; j < map.getWidth()-1; j++)
        {
            space += "|-------";
            top += "|-------";
            bottom += "|-------";
            xCoordinates += j + "       ";
        }

        space += "║";
        top += "╗";
        bottom += "╯ x";
        xCoordinates += j + "     ";

        for (int i = 0; i < map.getHeight(); i++)
        {
            String over = "   ║";
            String mapString = " " + i + " \u2551";
            String under = "   ║";

            for (IRoom room : map.getRoomList())
            {
                if (room.getLocation().y == i)
                {

                    String insert = "";
                    if (room.getLocation().x == player.getLocation().x &&
                            room.getLocation().y == player.getLocation().y)
                    {
                        insert = "---P---";
                    }
                    else if (room.isLocked())
                    {
                        insert = "   L   ";
                    }
                    else if (room.getHasBeenEntered())
                    {
                        insert = "   O   ";
                    }
                    else if (!room.getHasBeenEntered())
                    {
                        insert = "   X   ";
                    }

                    String t = " ";
                    String b = " ";

                    for (IMerchant merchant : map.merchantArrayList())
                    {
                        if (room.getLocation().x == merchant.getLocation().x && room.getLocation().y == merchant.getLocation().y)
                        {
                            b = "M";
                        }
                    }

                    for (IThief thief : map.thiefArrayList())
                    {
                        if (room.getLocation().x == thief.getLocation().x && room.getLocation().y == thief.getLocation().y)
                        {
                            t = "T";
                        }
                    }

                    if (room.getHasBeenEntered())
                    {
                        over += "  ╭"+ t +"╮  ";
                        under += "  ╰"+ b +"╯  ";
                    }
                    else if (!room.getHasBeenEntered())
                    {
                        over += "  \\"+ t +"/  ";
                        under += "  /"+ b +"\\  ";
                    }

                    mapString += insert;

//                    over += "       ";
//                    under += "       ";


                    if (room.getLocation().x < map.getWidth() - 1)
                    {
                        mapString += "|";
                        over += "|";
                        under += "|";
                    }


                }
            }
            over += "║";
            mapString += "║";
            under += "║";

            mapList.add(0, under);
            mapList.add(0, mapString);
            mapList.add(0, over);

            if (i < map.getHeight()-1)
                mapList.add(0,space);

        }


        mapList.add(0, top);
        mapList.add(0,"   y");
        mapList.add(bottom);
        mapList.add(xCoordinates);

//                mapList.set(0 , mapList.get(0) + "   X = Unseen Rooms  ");
//                mapList.set(1 , mapList.get(1) + "   O = Seen Rooms ");
//                mapList.set(2 , mapList.get(2) + "   P = Player ");
//                mapList.set(3 , mapList.get(3) + "   G = Merchants ");

        String output = "";
        //Prints players whereabouts -> Passing  String s as parameter
        for (String s : mapList)
        {
            output += s + "\n";
        }

        return output;
    }

    public String getKey(int i)
    {
        return (i+1) + ". " + ((Item)player.getInventory().keyArrayList().get(i)).getName();
    }

    public String getHighScore()
    {
        IHighScore highScore = new HighScore("HighScore.txt");
        highScore.readText();

        String output = "";
        for (String score : highScore.getHighScoreArray())
        {
            output += score + "\n";
        }

        return output;
    }

    /**
     * String for finding merchant
     * @return String
     */
    public String getMerchant()
    {
        return "You found a merchant and he offers you a trade";
    }

    /**
     * String for finding thief
     * @return String
     */
    public String getThief(IThief thief)
    {
        return thief.getAscii() + "\n\n" + "You found a thief and for his crime you take his life";
    }

    /**
     * String for Player name too long
     * @return String
     */
    public String getWrongPlayerNameLength()
    {
        return  "Name is too long, must be under 10 characters...";
    }

    /**
     * String for welcome back
     * @return String
     */
    public String getWelcomeBack()
    {
        return "Welcome back " + player.getName() + "\n" +
                "Nice to have you back.";
    }

    /**
     * String for Separator line
     * @return String
     */
    public String getSeperator()
    {
        return "✪►----⇹-----✫-----⇹-----⇥ ╭∩╮ʕ•ᴥ•ʔ╭∩╮ ⇤-----⇹-----✫-----⇹-----◄✪";
    }

//    public String getTest()
//    {
//        return "●▬▬▬▬▬▬▬▬▬▬▬▬๑۩۩๑▬▬▬▬▬▬▬▬▬▬▬▬▬●\n" +
//                "▂▃▅▇█▓▒░۩۞۩۩۞۩░▒▓█▇▅▃▂\n" +
//                "╭∩╮ʕ•ᴥ•ʔ╭∩╮\n" +
//                "(¯`·._.·(¯`·._.·(¯`·._.· Your Text ·._.·´¯)·._.·´¯)·._.·´¯)\n" +
//                "⋆ ✢ ✣ ✤ ✥ ✦ ✧ ✩ ✪ ✫ ✬ ✭ ✮ ✯ ✰ ✪\n" +
//                "◄███▓▒░░  ░░▒▓███►" +
//                "̲D̲̲u̲̲n̲̲g̲̲e̲̲o̲̲n̲̲ ̲̲C̲̲r̲̲a̲̲w̲̲l̲̲e̲r̲" +
//                "̠D̠̠u̠̠n̠̠g̠̠e̠̠o̠̠n̠̠ ̠̠C̠̠r̠̠a̠̠w̠̠l̠̠e̠r" +
//                "͟D͟͟u͟͟n͟͟g͟͟e͟͟o͟͟n͟͟ ͟͟C͟͟r͟͟a͟͟w͟͟l͟͟e͟r̠͟" +
//                "͞D͞͞u͞͞n͞͞g͞͞e͞͞o͞͞n͞͞ ͞͞C͞͞r͞͞a͞͞w͞͞l͞͞e͞r͞" +
//                "̅D̅̅u̅̅n̅̅g̅̅e̅̅o̅̅n̅̅ ̅̅C̅̅r̅̅a̅̅w̅̅l̅̅e̅r̅" +
//                "↓ ᶠᵘᶜᵏ ᵗʰᶦˢ ᵍᵘʸ↓" +
//                "↑ ᶠᵘᶜᵏ ʸᵒᵘ ᵗᵒᵒ ↑" +
//                "" +
//                "Darude- status\n" +
//                "\n" +
//                "☐ Not Sandstorm\n" +
//                "\n" +
//                "☑ Sandstorm" +
//                "" +
//                "(ಠ_ಠ)┌∩┐" +
//                "╭∩╮(Ο_Ο)╭∩╮" +
//                "¯\\_(ツ)_/¯" +
//                "" +
//                "0%" +
//                "█▒▒▒▒▒▒▒▒▒\n" +
//                "\n" +
//                "10%\n" +
//                "███▒▒▒▒▒▒▒\n" +
//                "\n" +
//                "30%\n" +
//                "█████▒▒▒▒▒\n" +
//                "\n" +
//                "50%\n" +
//                "███████▒▒▒\n" +
//                "\n" +
//                "99% \n" +
//                "██████████]\n" +
//                "\n" +
//                "100%\n" +
//                "██████████ " +
//                "" +
//                "☆.´ `. ☽¸.☆\n" +
//                "(͡๏̯͡๏)(͡๏̯͡๏)\n" +
//                "( ,,)( ,,).\n" +
//                "¯**´¯¯**´¯`" +
//                "" +
//                "✓✔✕✖" +
//                "┬┴┬┴┤･ω･)ﾉ├┬┴┬┴"
//                ;
//    }
//
//    /**
//     * String for a lot of symbols
//     * @return String
//     */
//    public String getSymbols()
//    {
//        return  "㊀ ㊁ ㊂ ㊃ ㊄ ㊅ ㊆ ㊇ ㊈ ㊉ ㊊ ㊋ ㊌ ㊍ ㊎ ㊏ ㊐ ㊑ ㊒ ㊓ ㊔ " +
//                "㊕ ㊖ ㊗ ㊘ ㊙ ㊚ ㊛ ㊜ ㊝ ㊞ ㊟ ㊠ ㊡ ㊢ ㊣ ㊤ ㊥ ㊦ ㊧ ㊨ ㊩ " +
//                "㊪ ㊫ ㊬ ㊭ ㊮ ㊯ ㊰ ➀ ➁ ➂ ➃ ➄ ➅ ➆ ➇ ➈ ➉·¨…¦┅┆➊ ➋ ➌ ➍" +
//                "➎ ➏ ➐ ➑ ➒ ➓ α ɐ β ɔ 卐 ™ © ® ¿ ¡ ½ ⅓ ⅔ ¼ ¾ ⅛ ⅜ ⅝ ⅞ ℅ " +
//                "№ ⇨ ❝ ❞ ℃ ∃ ┈ ℑ ∧ ∠ ∨ ∩ ⊂ ⊃ ∪ ⊥ ∀ Ξ Γ ə ɘ ε ɟ ɥ ɯ и η " +
//                "ℵ ℘ ๏ ɹ ʁ ℜ я ʌ ʍ λ ℓ ч ∞ Σ Π ⌥ ⌘ ¢ € £¥ Ⓐ Ⓑ Ⓒ Ⓓ Ⓔ Ⓕ " +
//                "Ⓖ Ⓗ Ⓘ Ⓙ Ⓚ Ⓛ Ⓜ Ⓝ Ⓞ Ⓟ Ⓠ Ⓡ Ⓢ Ⓣ Ⓤ Ⓥ Ⓦ Ⓧ Ⓨ Ⓩ ╧ ╨ ╤ ╥ ╙ " +
//                "ⓐ ⓑ ⓒ ⓓ ⓔ ⓕ ⓖ ⓗ ⓘ ⓙ ⓚ ⓛ ⓜ ⓝ ⓞ ⓟ ⓠ ⓡ ⓢ ⓣ ⓤ ⓥ ⓦ ⓧ ⓨ ⓩ " +
//                "╒ ╓ ╫ ╪ ┘ ツ ♋ 웃 유 Σ ⊗ ♒ ☠ ☮ ☯ ♠ Ω ♤ ♣ ♧ ♥ ♡ ♦ ♢ ♔ ♕ ♚ ♛ " +
//                "★ ☆ ✮ ✯ ☄ ☾ ☽ ♏ ╘ ┌ ╬ ☼ ☀ ☁ ☂ ☃ ☻ ☺ ۞ ۩ ♬ ✄ ✂ ✆ ✉ ✦ ✧ " +
//                "∞ ♂ ♀ ☿ ❤ ❥ ❦ ❧ ™ ® © ✗ ✘ ⊗ ♒ ▢ ╛ ┐ ─ ┼ ▲ △ ▼ ▽ ◆ ◇ ○ ◎ ● ◯ " +
//                "Δ ◕ ◔ ʊ ϟ ღ 回 ₪ ✓ ✔ ✕ ✖ ☢ ☣ ☤ ☥ ☦ ☧ ☨ ☩ ☪ ☫ ☬ ☭ └ ┴ ┬ ├ ┊╱ ╲ ╳ " +
//                "¯ – — ≡ ჻ ░ ▒ ▓ ▤ ▥ ▦ ▧ ▨ ▩ █ ▌ ▐ ▀ ▄ " +
//                "◠ ◡ ╭ ╮ ╯ ╰ │ ┤ ╡ ╢ ╖ ╕ ╣ ║ ╝ ╜ ╞ ╟ ╚ ╔ ╩ ╦ ╠ ═ " +
//                "{ ｡ ^ ◕ ‿ ◕ ^ ｡ } ( ◕ ^ ^ ◕ ) ✖ ✗ ✘ ♒ ♬ ✄ ✂ ✆ ✉ ✦ ✧ ♱ ♰ ♂ ♀☿ " +
//                "❤ ❥ ❦❧ ™ ® © ♡ ♦ ♢ ♔ ♕ ♚ ♛ ★ ☆ ✮ ✯ ☄ ☾ ☽ ☼ ☀ ☁ ☂ ☃ " +
//                "☻ ☺ ☹ ☮ ۞ ۩ ε ї з ☎ ☏ ¢ ☚ ☛ ☜ ☝ ☞ ☟ ✍ ✌ ☢ ☣ ☠ ☮ ☯ " +
//                "♠ ♤ ♣ ♧ ♥ ♨ ๑ ❀ ✿ ψ ♆ ☪ ☭ ♪ ♩ ♫ ʊ ϟ ღ ツ 回 ₪ 卐 © ® ¿ ¡ " +
//                "½ ⅓ ⅔ ¼ ¾ ⅛ ⅜ ⅝ ⅞ ℅ № ⇨ ❝ ❞ ℃ ◠ ◡ ╭ ╮╯╰ ★ ☆ ⊙ ¤ ㊣ ★ ☆ ♀ ◆ ◇ ™ " +
//                "║ ▼ ╒ ▲ ◣ ◢ ◥ ▼ △ ▽ ⊿ ◤ ◥ △ ▴ ▵ ▶ ▷ ▸ ▹ ► ▻ ▼ ▽ ▾ ▿ ◀ ◁ ◂ ◃ ◄ ◅ " +
//                "▆ ▇ █ █ ■ ▓ 回 □ 〓 ≡☌ ╝╚╔╗╬ ═╓╩ ┠┨┯┷┏ ┓┗┛┳⊥ ﹃﹄┌ ┐└┘∟「 」↑↓→ ←↘↙♀ ♂" +
//                "┇┅﹉﹊ ﹍﹎╭╮╰╯ *^_^* ^*^ ^-^ ^_^ ^︵^∵∴‖ ︱︳︴﹏ ﹋﹌♂♀ ♥♡☜☞☎ ☏" +
//                "⊙◎☺☻ ►◄▧▨ ♨◐◑↔↕ ▪▫☼♦▀ ▄█▌▐ ░▒▬♦◊ ◦ ☼ ♠ ♣ ▣ ▤▥▦▩ ぃ◘◙◈" +
//                "♫ ♬♪♩♭♪ の☆→あ ￡❤｡◕‿ ◕｡✎✟ஐ ≈๑۩ ۩.. ..۩۩๑ ๑۩۞۩๑ ✲❈➹ ~.~◕ ‿-｡☀☂☁ " +
//                "【】┱┲❣ ✚✪✣ ✤✥ ✦❉ ❥❦❧❃ ❂❁❀✄☪ ☣☢☠☭♈ ➸✓✔✕ ✖㊚㊛ *.:｡ ✿*ﾟ‘ﾟ･ ⊙¤㊣" +
//                "★☆ ♀◆◇ ◣◢◥▲△▽⊿◤ ◥▆▇ ██■▓ 回□〓≡╝ ╚╔╗ ╬═╓╩ ┠┨┯┷┏ ┓┗┛ ┳⊥﹃﹄ ┌┐└┘∟ 「」" +
//                "↑↓ → ← ↘ ↙♀♂┇┅﹉ ﹊﹍﹎ ╭╮╰╯ *^_^* ^*^ ^-^ ^_^ ^︵^∵ ∴‖ ︱︳ ︴﹏﹋﹌ ♂♀♥♡☜ ☞" +
//                "☎☏⊙ ◎☺☻►◄ ▧▨♨◐◑ ↔↕▪▫ ☼♦▀▄█ ▌▐░▒▬ ♦◊◦☼ ♠♣▣▤▥ ▦▩ぃ◘◙ ◈♫♬♪ ♩♭♪の☆" +
//                " →あ￡❤｡ ◕‿◕｡ ✎✟ஐ≈ ๑۩۩.. ..۩ ✉ ✍ ✎ ✏ ✐๑✲❈ ➹ ~.~◕‿-｡ ☀☂☁【】 ┱┲" +
//                "❣✚ ✪✣✤✥ ✦❉❥❦ ❧❃❂❁❀ ✄☪☣☢☠ ☭♈➸✓✔✕✖㊚ ㊛♧♤♧♡♬♪*.:｡✿*ﾟ ‘ﾟ･ ◊♥" +
//                "╠═╝▫■๑»«¶ஐ©† εïз♪ღ♣ ♠•± °•ิ.•ஐ இ * × ○ ▫ ✑ ✒ ⌨ ۩ ๑ ๑۩ ۞ ۩ ┭┮┯♂ • ♀ ◊ ©" +
//                " ¤ ▲ ↔ ™ ® ☎ ε ї з ♨ ☏ ☆ ★ ▽ △ ▲ ∵ ∴ ∷ ＃ ♂ ♀ ♥ ♠ ♣ ☹ ☺ ☻┌ " +
//                "┍┎ ┏ ┐ ┑┓ ♭♫♪ﻬஐღ ↔↕↘••● ¤╬﹌▽☜♥☞ ♬✞♥♕☯☭☠☃ ─ ━ │ ┃ ┄ ┅ ┆ ┇ ┈ ┉ ┊ ┋ ≨ ≩╨╩ ╪ ╫ ╬╏" +
//                "═≂ ≃ ≄ ≅ ≆ ≇ ≈ ≉ ≊ ≋ ≌ ≍ ≎ ≏ ≐ ≑ ≒ ≓ ≔ ≕ ≖ ≗ ≘ ≙ ≚ ≛ ≜ ≝ ≞ ≟ ≠ ≡≢ ≣ ≤ ≥ ≦≧" +
//                "␛ ␡ ␚ ␟ ␘ ␠ ␤ ␋ ␌ ␍ ␎ ␏ ␐ ␑ ␒ ␓ ␔ ␕ ␖ ␗ ␙ ␜ ␝ ␞╣ ╤ ╥ ╦ ╧ ╗ ╘ ╙ ╚ ╛╡ ┼ ┽ ┾ ┿ ╀ ╁ ╂ ╃ ╓ ╔ ╕ ╖ " +
//                "♈ ♉ ♊ ♋ ♌ ♍ ♎ ♏ ♐ ♑ ♒ ♓ ╮ ╯ ╰ ╱ ╲ ╳ ‟ †‡•‣" +
//                "▀ ▁ ▂ ▃ ▄ ▅ ▆ ▇ █ ▉ ▊ ▋ ▌ ▍ ▎ ▏ ▐ ░ ▒ ▓ ▔ ▕ ■ □ ▢ ▣ ▤ ▥ ▦ ▧ ▨ ▩ ▪ ▫ ▬ ▭▮▯" +
//                "╭ ◞ ◟ ◠ ◡ ◢ ◣ ♔ ♕ ♖ ♗ ♘ ♙ ♚ ♛ ♝ ♞ ♟ ♠ ♡ ♢ ♣ ☔ ☕ ☖ ☗ ☘ ☙ ☊ ☋ ☌ ☍ ☎ ☏☐" +
//                " ╴ ╵ ╶ ╷ ╸ ╹ ╺ ╻ ╼ ╽ ╾ ╿ ▰ ▱ ◆ ◇ ◈ ◉ ◊ ○ ◌ ◍ ◎ ● ◐ ◑ ◒ ◓ ◔ ◕ ◖ ◗ ◘ ◙ ◚ ◛ ◜ ◝ " +
//                "◤ ◥ ◦ ◧ ◨ ◩ ◪ ◫ ◬ ◭ ◮ ◯◽ ◾ ◿ ☀ ☁ ☂ ☃ ☄ ★ ☆ ☇ ☈ ☉ ☑ ☒ ☓ ♅ ♆ ♇♜ ⇜ ⇝ ⇞ ⇟ ⇠ ⇡⇢⇣☟ " +
//                "☠ ☡ ☢ ☣ ☤ ☥ ☦ ☧ ☨ ☩ ☪ ☫ ☬ ☭ ☮ ☯ ☰ ☱ ☲ ☳ ☴ ☵ ☶ ☷ ☸ ☹ ☺ ☻ ☼ ☽ ☾ ☿ ♀ ♁ ♂ ♃ ♄ℕ" +
//                "♤ ♥ ♦ ♧ ♨ ♩ ♪ ♫ ♬ ♭ ♮ ♯ ♰ ♱ ´ ῾ ‐ ‑ ‒ – — ― ‖ ‗ ‘ ’ ‚ ‛ “ ” „ ℋ ℌ ℍ ℎ ℏ ℐ ℑ ℒ ℓ ℔․ ‥ … ‧ " +
//                "‰ ‱ ′ ″ ‴ ‵ ‶ ‷ ‸ ‹ › ※ ‼ ‽ ‾ ‿ ⁀ ⁁ ⁂ ⁃ ⁄ ⁅ ⁆ ⁑ ⁞ ₠ ₡ ₢ ₣ ₤ ₥ ₦ ₧ ₨ ₩ ₪ ₫ € ₭₮ ₯ ℀ ℁ ℂ ℃ ℄ ℅ ℆ " +
//                "ℇ ℈ ℉ ℊ № ℗ ℘ ℙ ℚ ℛ ℜ ℝ ℞ ℟ ℠ ℡ ™ ℣ ℤ ℥ Ω ℧ ℨ ℩ K Å ℬ ℭ ℮ ℯℰ ℱ Ⅎ ℳ ℴ ℵ ℶ ℷ ℸ " +
//                "⅍ ⅎ ⅓ ⅔ ⅕ ⅖ ⅗ ⅘ ⅙ ⅚ ⅛ ⅜ ⅝ ⅞ ⅟ ⇍ ⇎ ≺ ≻ ≼ ≽ ≾ ≿ ⊀ ∏ ∐ ∑ −∓" +
//                "⁰ ⁱ \u2072 \u2073 ⁴ ⁵ ⁶ ⁷ ⁸ ⁹ ⁺ ⁻ ⁼ ⁽ ⁾ ⁿ ₀ ₁ ₂ ₃ ₄ ₅ ₆ ₇ ₈ ₉ ₊ ₋ ₌ ₍ ₎ " +
//                "⇤ ⇥ ⇦ ⇧ ⇨ ⇩ ⇪ ⇹ ⇺ ⇻ ⇼ ⇽ ∔ ↶ₐ ₑ ₒ ₓ ₔ" +
//                "Ⅰ Ⅱ Ⅲ Ⅳ Ⅴ Ⅵ Ⅶ Ⅷ Ⅸ Ⅹ Ⅺ Ⅻ Ⅼ Ⅽ Ⅾ Ⅿ ⅰ ⅱ ⅲ ⅳ ⅴ ⅵ ⅶ ⅷ ⅸ ⅹ ⅺ ⅻ ⅼ ⅽ ⅾ ⅿ " +
//                "╢ ↵ ↷← ↑ → ↓ ↔ ↕ ↖ ↗ ↘ ↙ ↚ ↛ ↜ ↝ ↞ ↟ ↠ ↡ ↢ ↣ ↤ ↥ ↦ ↧ ↨ ↩ ↪ ↫ ↬ ↭ ↮ ↯ ↰ ↱ ↲ ↳↴ ￡ " +
//                "↸ ↹ ↺ ↻ ↼ ↽ ↾ ↿ ⇀ ⇁ ⇂ ⇃ ⇄ ⇅ ⇆ ⇇ ⇈ ⇉ ⇊ ⇋ ⇌⇏ ⇐ ⇑ ⇒ ⇓ ⇔ ⇕ ⇖ ⇗ ⇘ ⇙ ⇚ ⇛ ∽∾⊣ ⊤" +
//                "⇫ ⇬ ⇭ ⇮ ⇯ ⇰ ⇱ ⇲ ⇳ ⇴ ⇵ ⇶ ⇷ ⇸ ⇾ ⇿ ∀ ∁ ∂ ∃ ∄ ∅ ∆ ∇ ∈ ∉ ∊ ∋ ∌ ∍ ∎ ◙ ▤▥▦▧▨ ▩ ♤ ♧♡" +
//                "∕ ∖ ∗ ∘ ∙ √ ∛ ∜ ∝ ∞ ∟ ∠ ∡ ∢ ∣ ∤ ∥ ∦ ∧ ∨ ∩ ∪ ∫ ∬ ∭ ∮ ∯ ∰ ∱ ∲ ∳ ∴ ∵ ∶ ∷ ∸ ∹ ∺ ∻ ∼ ∿ ≀ ≁ " +
//                "≪ ≫ ≬ ≭ ≮ ≯ ≰ ≱ ≲ ≳ ≴ ≵ ≶ ≷ ≸ ≹ ⁇ ⁈ ⁉ ‼ ‽ ⁇ ⁈ ⁉ ‼ ‽ ™ © ®⍘ ⍙ ♬ ♭ ♮ ♯♰♱⊁ " +
//                "⊂ ⊃ ⊄ ⊅ ⊆ ⊇ ⊈ ⊉ ⊊ ⊋ ⊌ ⊍ ⊎ ⊏ ⊐ ⊑ ⊒ ⊓ ⊔ ⊕ ⊖ ⊗ ⊘ ⊙ ⊚ ⊛ ⊜ ⊝ ⊞ ⊟ ⊠ ⊡ " +
//                "⊢⊥⌕⌖ ⊦ ⊧ ⊨ ⊩ ⊪ ⊫ ⊬ ⊭ ⊮ ⊯ ⊰ ⊱ ⊲ ⊳ ⊴ ⊵ ⊶ ⊷ ⊸ ⊹ ⊺ ⊻ ┌ ┍ ┎ ┏ ┐ ┑ ┒ ┓" +
//                "⋟ ⋠ ⋡ ⋢ ⋣ ⌓⌔⊼ ⊽ ⊾ ⊿ ⋀ ⋁ ⋂ ⋃ ⋄ ⋅ ⋆ ⋇ ⋈ ⋉ ⋊ ⋋ ⋌ ⋍ ⋎ ⋏ ⋐ ⋑ ⋒ ⋓ ⋔ ⋕ ⋖ ⋗ ⋘ ⋙ ⋚ ⋛ ⋜ ⋝ ⋞ " +
//                "♤ ♥♦♧♨ ⋤ ⋥ ⋦ ⋧ ⋨ ⋩ ⋪ ⋫ ⋬ ◐ ◑ ☢ ⊗ ⊙ ◘❃ ❂ ○ ◎ ● ◯ ◕ ◔ ┄ ┅ ┆ ┇ ┈ ┉ ┊ ┋ ♩ ♪ ♫ ♜ ♝⋭ ⋮ ⋯ ⋰ ⋱ " +
//                "⋲ ⋳ ⋴ ⋵ ⋶ ⋷ ⋸ ⋹ ⋺ ⋻ ⋼ ⋽ ⋾ ⋿ ⌀ ⌁ ⌂ ⌃ ⌄ ⌅ ⌆ ⌇ ⌈ ⌉ ⌊ ⌋ ⌌ ⌍– ぱ ⌎ ⌐ ⌑⌒♔ ♕ ⌗ ⌘ ⌙ ⌚ ⌛ ⌜ " +
//                "░ ▒ ▓ ▔ ▕ ª ↀ ↁ ↂ Ↄ ↄ ↅ⍚ ␋ ␢ ␣ ─ ━ │ ┃ ⌾ ⌿ ⍀ ⍁♞ ♟ ♠ ♡ ♢♣⌝ ⌞ ⌟ ⌠ ⌡ ⌢ ⌣ ⌤ ⌥ " +
//                "⌦ ⌧ ⌨ 〈 〉 ⌫ ⌬ ⌭ ⌮ ⌯ ⌰ ⌱ ⌲ ⌳ ⌴ ⌵ ⌶ ⌷ " +
//                "⌸ ⌹ ⌺ ⌻ ⌼ ⌽ ⍂ ⍃ ⍄ ⍅ ⍆ ⍇ ⍈ ⍉ ⍊ ⍋ ⍌ ⍍ ⍎ ⍏ ⍐ ⍑ ⍒ ⍓ ⍔ ⍕ ⍖ ⍗ ♎ ♏ ♐ ♑ ♒ ♓ ♖ ♗ ♘ ♙♚♛" +
//                "頹 – 衙 – 浳 – 浤 – 搰 – 煤 – 洳 – 橱 – 橱 – 煪 – ㍱ – 煱 – 둻 – 睤 – ㌹ – 楤 – ぱ – - " +
//                "椹– 畱 – 煵 – 田 – つ – 煵 – 엌 – 嫠 – 쯦 – 案 – 迎 – 是 – 從 – 事 – 網 – 頁 – 設 – 計 – 簡";
//    }

    /**
     * String for printing file
     * @param files set array to get files from.
     * @return String
     */
    public String getFilesInFolder(File[] files)
    {
        String output = "";
        for ( int i=0; i<files.length; i++ )
        {
            output += (i+1) + ". " + files[i].getName() + "\n";
        }
        return output;
    }

    /**
     * String for invalid use of key
     * @return String
     */
    public String getUseKey()
    {
        return "You can't use a key here.";
    }

    /**
     * String for players name
     * @return String
     */
    public String getPlayerName()
    {
        return "Name: " + player.getName();
    }

    /**
     * String for X coordinate
     * @return String
     */
    public String getTypeXCoordinate()
    {
        return type + " X Coordinate";
    }

    /**
     * String for Y coordinate
     * @return String
     */
    public String getTypeYCoordinate()
    {
        return type + " Y Coordinate";
    }

    /**
     * String for wrong coordinate inuput
     * @return String
     */
    public String getWrongInputCoordinate()
    {
        return "Must only contain digits and be inside the range of the coordinate system.";
    }

    /**
     * String for teleporting to locked room
     * @return String
     */
    public String getTeleportToLockedRoomNoKey()
    {
        return "You teleported to a locked room and have no keys ;/ ... Teleport Wasted...";
    }

    /**
     * String for bomb use
     * @return String
     */
    public String getUseSpecialBomb()
    {
        return "BOOM ... Monsters health reduced by 50%";
    }

    /**
     * String for vision use
     * @return String
     */
    public String getVisionMap()
    {
        ArrayList<String> topLeft = new ArrayList<>();
        ArrayList<String> topCenter = new ArrayList<>();
        ArrayList<String> topRight = new ArrayList<>();
        ArrayList<String> left = new ArrayList<>();
        ArrayList<String> right = new ArrayList<>();
        ArrayList<String> bottomLeft = new ArrayList<>();
        ArrayList<String> bottomCenter = new ArrayList<>();
        ArrayList<String> bottomRight = new ArrayList<>();

        for (Room room : ((ArrayList<Room>) map.getRoomList()))
        {
            if ((room.getLocation().y) == (player.getLocation().y + 1) && (room.getLocation().x) == (player.getLocation().x - 1))
            {
                topLeft = getContentFromRoom(room);
            }
            else if (topLeft.size() == 0) topLeft = getEmptyArray();

            if ((room.getLocation().y) == (player.getLocation().y + 1) && (room.getLocation().x) == (player.getLocation().x))
            {
                topCenter = getContentFromRoom(room);
            }
            else if (topCenter.size() == 0) topCenter = getEmptyArray();

            if ((room.getLocation().y) == (player.getLocation().y + 1) && (room.getLocation().x) == (player.getLocation().x + 1))
            {
                topRight = getContentFromRoom(room);
            }
            else if (topRight.size() == 0) topRight = getEmptyArray();

            if ((room.getLocation().y) == (player.getLocation().y) && (room.getLocation().x) == (player.getLocation().x - 1))
            {
                left = getContentFromRoom(room);
            }
            else if (left.size() == 0) left = getEmptyArray();

            if ((room.getLocation().y) == (player.getLocation().y) && (room.getLocation().x) == (player.getLocation().x + 1))
            {
                right = getContentFromRoom(room);
            }
            else if (right.size() == 0) right = getEmptyArray();

            if ((room.getLocation().y) == (player.getLocation().y - 1) && (room.getLocation().x) == (player.getLocation().x - 1))
            {
                bottomLeft = getContentFromRoom(room);
            }
            else if (bottomLeft.size() == 0) bottomLeft = getEmptyArray();

            if ((room.getLocation().y) == (player.getLocation().y - 1) && (room.getLocation().x) == (player.getLocation().x))
            {
                bottomCenter = getContentFromRoom(room);
            }
            else if (bottomCenter.size() == 0) bottomCenter = getEmptyArray();

            if ((room.getLocation().y) == (player.getLocation().y - 1) && (room.getLocation().x) == (player.getLocation().x + 1))
            {
                bottomRight = getContentFromRoom(room);
            }
            else if (bottomRight.size() == 0) bottomRight = getEmptyArray();
        }

        String top = "";
        String middle = "";
        String bottom = "";

        for (int i = 0; i < GameSettings.getRoomContents(); i++)
        {
            top += " ║" + topLeft.get(i) + "|" + topCenter.get(i)+ "|" + topRight.get(i) + "║\n";
            if (i == 0) middle += " ║" + left.get(i) + "|" + "  Player   " + "|" + right.get(i) + "║\n";
            else middle += " ║" + left.get(i) + "|" + "           " + "|" + right.get(i) + "║\n";
            bottom += " ║" + bottomLeft.get(i) + "|" + bottomCenter.get(i)+ "|" + bottomRight.get(i) + "║\n";
        }

        String topDecor = " ╔-----------|-----------|-----------╗" + "\n";
        String seperator =  " ║-----------|-----------|-----------║" + "\n";
        String empty =  " ║           |           |           ║" + "\n";
        String bottomDecor = " ╰-----------|-----------|-----------╯" + "\n" ;

        return topDecor + empty + top + empty + seperator + empty + middle + empty + seperator + empty + bottom + empty + bottomDecor;
    }

    private ArrayList<String> getEmptyArray()
    {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < GameSettings.getRoomContents(); i++)
        {
            if (i == 0) list.add("  #######  ");
            else if (i == 1) list.add("  #######  ");
            else list.add("  #######  ");
        }

        return list;
    }

    /**
     * String for room content
     * @param room set the room from which to return content.
     * @return String
     */
    private ArrayList<String> getContentFromRoom(Room room)
    {
        ArrayList<String> list = new ArrayList<>();
        for (RoomContent content : room.getContentArray())
        {
            if (content instanceof Monster) list.add("  Monster  ");
            else if (content instanceof Chest) list.add("  Chest    ");
            else if (content instanceof Guide) list.add("  Guide    ");
            else if (content == null) list.add("           ");
            else list.add("NO ROOM");
        }

        return list;
    }

    /**
     * String for empty room
     * @return String
     */
    public String getRoomIsEmpty()
    {
        return "Room is empty...";
    }

    /**
     * String for unlocking room
     * @return String
     */
    public String getRoomHasBeenUnlocked()
    {
        return "Room has been unlocked.";
    }

    /**
     * String for inventory slot
     * @param index set the index the get item from.
     * @param inventory set which inventory to use.
     * @return String
     */
    public String getInventorySlot(int index, IInventory inventory)
    {
        String inventoriesItem;
        if (inventory.getItem(index) == null)
        {
            inventoriesItem = "empty";
        }
        else
        {
            inventoriesItem = inventory.getItem(index).getName();
            if (inventory.getItem(index) instanceof Weapon) inventoriesItem += " (Weapon)";
            else if (inventory.getItem(index) instanceof Potion) inventoriesItem += " (Potion)";
            else if (inventory.getItem(index) instanceof Key) inventoriesItem += " (Key)";
            else if (inventory.getItem(index) instanceof Special) inventoriesItem += " (Special)";
        }

//        top += "-" + inventoriesItem.replaceAll(".", "-") + "--";
//        mTop += " " + inventoriesItem.replaceAll(".", " ") + " |";
//        middle += " " + inventoriesItem + " |";
//        mBottom += " " + inventoriesItem.replaceAll(".", " ") + " |";
//        bottom += "-" + inventoriesItem.replaceAll(".", "-") + "--";


        return inventoriesItem;
    }

    /**
     * String for talking too much
     * @return String
     */
    public String getGuideLimit()
    {
        return "My daily limit of 10 answers has been reached. Please find another guide to keep talking...";
    }

    /**
     * String for using extra slot item
     * @return String
     */
    public String getUseSpecialExtraSlot()
    {
        return "You just go an extra inventory slot. Good on ya mate :)";
    }
}
