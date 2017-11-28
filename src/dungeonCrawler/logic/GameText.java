package dungeonCrawler.logic;

import dungeonCrawler.aqu.*;
import dungeonCrawler.data.HighScore;
import dungeonCrawler.presentationConsole.CommandWord;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameText
{
    Player player;
    Map map;

    public GameText()
    {
    }

    public void injectVariables(Player player, Map map)
    {
        this.player = player;
        this.map = map;
    }

    public String getEmptyLine()
    {
        return "";
    }

    public String getAsciiTitle()
    {
        return GameTextASCII.getTitle();
    }

    public String getWelcomeText()
    {
        return "\nWelcome to Dungeon Crawler!"+
                "\nThis is a new, incredibly boring adventure game.\n";
    }

    public String getMenu()
    {
        return "Main Menu"+
                "\n1. Start New Game"+
                "\n2. Load Saved Game"+
                "\n3. Show High Score"+
                "\n4. Settings"+
                "\n5. Exit";
    }

    public String getSetDifficultyLevel()
    {
        return "Select Difficulty Level"+
                "\n1. Easy"+
                "\n2. Normal"+
                "\n3. Hard";
    }

    public String getEnterPlayerName()
    {
        return "Enter your name here: ";
    }

    public String getEnterToStartGame()
    {
        return "Type \"enter\" to start the game.";
    }

    public String getMessageHello()
    {

        return "Well... hello there "+player.getName()+
                "\nI'am Slave and I'll be your merchant through this adventure."+
                "\nType '"+ CommandWord.HELP + "' if you ever need help."+
                "\nType \"enter\" to enter the DUNGEON...";
    }

    public String getThanksForPLaying()
    {
        return "Thanks for playing "+player.getName()+". Good bye!";
    }

    public String getYouHaveDied()
    {
        return "You have died :(";
    }

    public String getWhatDoYouMean()
    {
        String[] wrongUserInput = new String[]
        {"I don't know what you mean...", "What?", "Invalid Command", "Look at your keyboard when you type, you moron...",
            "Sorry, come again", "Huh?"        
        };
        
        return wrongUserInput[new Random().nextInt(wrongUserInput.length)];
    }

    public String getUseWhat()
    {
        return "Use what?";
    }

    public String getSlotIsOutOfRange()
    {
        return "Slot is out of range";
    }

    public String getShowWhat()
    {
        return "Show what?";
    }

    public String getHelpMenu()
    {
        return "Welcome to the Help menu system..."+
                "\nChoose one of the following options."+
                "\n---------------------------------------"+
                "\n1. Game Commands"+
                "\n2. Game Goals"+
                "\n3. Game Tips & Tricks";
    }

    public String getHelpCommandWords()
    {
        return "Your command words are:";
    }

    public String getHelpGoals()
    {
        return "The goals of the game is to defeat the devil";
    }

    public String getNoTipsAvaiable()
    {
        return "No tips or tricks available :( ";
    }

    public String getInvalidChoice()
    {
        return "Invalid menu choice";
    }


    public String getYouCurrentlyHaveHp()
    {
        return "You currently have: "+player.getHealth()+ " hp";
    }

    public String getYouCurrentlyHavePoints()
    {
        return "You currently have: "+player.getScore()+" points";
    }

    public String getName()
    {
        return "Name: ";
    }

    public String getHuh()
    {
        return "Huh?";
    }

    public String getGoWhere()
    {
        return "Go where?";
    }

    public String getYouEnteredANewRoom(IRoom room)
    {
        return "You entered " + room.getName()
                + "\nYou see " + room.getDescription()
                + "\n";
    }

    public String getYouRanIntoAWall()
    {
        return "You ran into wall :(";
    }

    public String getYouWentBackToPreviousRoom()
    {
        return "You went back to the previous room.";
    }

    public String getNoSuchDirection()
    {
        return "Go where? No such direction found...";
    }

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

    public String getInventory(IInventory inventory)
    {
        String top = " \u256D";
        String mTop = " \u2551";
        String middle = " \u2551";
        String mBottom = " \u2551";
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
            }


            top += "-" + inventoriesItem.replaceAll(".", "-") + "-";
            mTop += " " + inventoriesItem.replaceAll(".", " ") + " ";
            middle += " " + inventoriesItem + " ";
            mBottom += " " + inventoriesItem.replaceAll(".", " ") + " ";
            bottom += "-" + inventoriesItem.replaceAll(".", "-") + "-";
            slot += " " + String.valueOf(i + 1) + inventoriesItem.replaceAll(".", " ") + "";
        }
        top = top + "\u256E";
        mTop = mTop + "\u2551";
        middle = middle + "\u2551" + "  To get information on an item type \"show slot 1\",slot 2 ...";
        mBottom = mBottom + "\u2551";
        bottom = bottom + "\u256F" + "  To use an item type \"use slot 1\",slot 2 ...";

        return top + "\n" + mTop + "\n" + middle + "\n" + mBottom + "\n" + bottom + "\n" + slot;
    }

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

    private String getGuideInfo(Guide guide)
    {
        return  guide.getAscii() +
                "\n" + "Name: " + guide.getName() +
                "\n" + "Description: " + guide.getDescription();
    }

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
        else
        {
            return "";
        }


        return contentInfo;
    }
    
    public String getPlayerInfo()
    {
        return "Your Health: " + player.getHealth() +
        "\n" + "Your Power: " + player.getWeapon().getPower() + "\n" +
        "Your Multiplier: " + player.getWeapon().getMultiplier();
    }

    public String getMonstersInfo(IMonster monster)
    {
        return  monster.getAscii() +
                "\n" + "Name: " + monster.getName() +
                "\n" + "Description: " + monster.getDescription() +
                "\n" + "Health: " + monster.getHealth() +
                "\n" + "Power: " + monster.getPower();
    }

    public String getChestInfo(IChest chest)
    {
        return  chest.getAscii() +
                "\n" + "Name: " + chest.getName() +
                "\n" + "Description: " + chest.getDescription() +
                "\n" +
                "\n" + "There is a chest, type \"open\" to open!" +
                "\n Or you can type \"skip\" to skip it!";
    }

    public String getBattleOrFlee()
    {
        return "Type \"battle\" or \"flee\".";
    }

    public String getAttackOrDrinkPotion()
    {
        return "attack or drink potion";
    }

    public String getBattle(IBattle battle)
    {
        return battle.start();
    }

//    public String getWeapon(Item item)
//    {
//        return item.getAscii()+
//                "\nName: " + item.getName()+
//                "\nPOWER: " + ((IWeapon) item).getPower()+
//                "\nMULTIPLIER: " + ((IWeapon) item).getMultiplier() + "x"+
//                "\n"+item.getDescription();
//    }
//
//    public String getPotion(Item item)
//    {
//        return item.getAscii()+
//                "\nName: " + item.getName()+
//                "\nHEALTH RECOVERY: " + ((IPotion) item).getHealthRecovery()+
//                "\nTIME RECOVERY: " + ((IPotion) item).getTimeRecovery()+
//                "\n"+item.getDescription();
//    }

    public String getSlotIsEmpty()
    {
        return "Slot is empty.";
    }

    public String getSetCurrentWeapon()
    {

        return "Your current weapon is now: " + player.getWeapon().getName();
    }

    public String getPlayerHealth()
    {
        return "Your health is: " + player.getHealth() + "hp";
    }
    
    public String getPlayerTime(ITimeTracker timeTracker)
    {
        return "Your time is now: " + timeTracker.calculateRemainingTime()+ "sec";
    }

    public String getTypeSlotNumberToUse()
    {
        return "Type number to use.";
    }

    public String getPotionRecovery(int i)
    {
        return (i+1) +
                ". Potion:" +
                player.getInventory().potionArrayList().get(i).getHealthRecovery();
    }

    public String getYouHaveNoPotions()
    {
        return "You have no potions :(";
    }

    public String getThereIsAGuide()
    {
        return "There is a guide, you can either \"talk\" , \"skip\" or \"kill\"!";
    }

    public String getGuideTalk()
    {
        return "Hello my name is \"insert name here\" here is a tip ;) ... DON'T DIE!!!";
    }

    public String getKilledGuide()
    {
        return "You killed the guide, oh mighty swordsman!";
    }

    public String getThereIsAChest()
    {
        return "There is a chest, type \"open\" to open!" +
                                "\n Or you can type \"skip\" to skip it!";
    }

    public String getWhatSlot()
    {
        return "Do you want to insert this into a slot?"+
                "\nType slot number or \"drop\" to drop.";
    }

    public String getYouSavedItemInThisSlot(int j)
    {
        return "You saved this item in slot: " + (j+1);
    }

    public String getYouDroppedTheItem()
    {
        return "You dropped the item";
    }

    public String getHmmWrongCommand()
    {
        return "Hmm... Wrong command";
    }

    public String getItsAEmptySpace()
    {
        return "Empty space :(";
    }

    public String getManyHyphens()
    {
        return "-----------------------------------------------";
    }

    public String getQuitWhat()
    {
        return "Quit what?";
    }

    public String getBattleWhat()
    {
        return "Battle what?";
    }

    public String getTimeRanOut ()
    {
        return "Your time ran out, and you are now dead";
    }

    public String getAllRoomsEntered ()
    {
        return "The eye of Sauron teleports you to the lair of the demon realm";
    }

    public String getIsLuciferDead ()
    {
        return "You defeated the devil!" +
        "\nBut the princess is in another castle... and the game is over.";
    }

    public String getRoomIsLocked()
    {
        return "Room is locked";

    }

    public String getMap()
    {
        ArrayList<String> mapList = new ArrayList<>();

        for (int i = 0; i < map.getHeight(); i++)
        {
            String mapString = " " + i + " \u2551 ";
            for (IRoom room : map.getRoomList())
            {
                if (room.getLocation().y == i)
                {
                    String insert = "";
                    if (room.getLocation().x == player.getLocation().x &&
                            room.getLocation().y == player.getLocation().y)
                    {
                        insert = "  P   ";
                    }
                    else if (room.isLocked())
                    {
                        insert = "  L   ";
                    }
                    else if (room.getHasBeenEntered())
                    {
                        insert = "  O   ";
                    }
                    else if (!room.getHasBeenEntered())
                    {
                        insert = "  X   ";
                    }
                    for (IMerchant merchant : map.merchantArrayList())
                    {
                        if (room.getLocation().x == merchant.getLocation().x && room.getLocation().y == merchant.getLocation().y)
                        {
                            insert = "  G   ";
                        }
                    }
                    for (IThief thief : map.thiefArrayList())
                    {
                        if (room.getLocation().x == thief.getLocation().x && room.getLocation().y == thief.getLocation().y)
                        {
                            insert = "  T   ";
                        }
                    }

                    mapString += insert;
                }
            }
            mapString += " \u2551";
            mapList.add(0, mapString);
        }

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
        return (i+1) +
                ". Key:" +
                ((Item)player.getInventory().keyArrayList().get(i)).getName();
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

    public String getMerchant()
    {
        return "You found a merchant and he offers you a trade";
    }

    public String getThief()
    {
        return "You found a thief and for his crime you take his life";
    }
    
    public String getWrongPlayerNameLength()
    {
        return  "Name is too long, must be under 10 characters...";
    }
}
