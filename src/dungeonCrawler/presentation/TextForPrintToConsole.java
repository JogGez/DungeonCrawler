package dungeonCrawler.presentation;

import dungeonCrawler.aqu.*;

import java.util.Random;

public class TextForPrintToConsole
{
    //No-arg Constructor
    public TextForPrintToConsole()
    {
    }

    public String getEmptyLine()
    {
        String empty = "";
        return empty;
    }

    public String getAsciiTitle()
    {
        String string = ASCII.getTitle();
        return string;
    }

    public String getWelcomeText()
    {
        ASCII.getTitle();
        String welcome = "\nWelcome to Dungeon Crawler!"+
                "\nThis is a new, incredibly boring adventure game.\n";
        return welcome;
    }

    public String getMenu()
    {
        String menu = "Main Menu"+
                "\n1. Start New Game"+
                "\n2. Load Saved Game"+
                "\n3. Show High Score"+
                "\n4. Settings"+
                "\n5. Exit";
        return menu;
    }

    public String getEnterPlayerName()
    {
        String enterName = "Enter your name here: ";
        return enterName;
    }

    public String getEnterToStartGame()
    {
        String enterNAmeToStartGame = "Type \"enter\" to start the game.";
        return enterNAmeToStartGame;
    }

    public String getMessageHello(IPlayer player)
    {

        String hello = "Well... hello there "+player.getName()+
                "\nI'am Slave and I'll be your guide through this adventure."+
                "\nYou know is me by the slow print speed of the characters..."+
                "\nType '"+CommandWord.HELP + "' if you ever need help."+
                "\nType \"enter\" to enter the DUNGEON...";
        return hello;
    }

    public String getThanksForPLaying(IPlayer player)
    {
        String gameFinsished = "Thanks for playing "+player.getName()+". Good bye!";
        return gameFinsished;
    }

    public String getYouHaveDied()
    {
        String died = "You have died :(";
        return died;
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
        String what = "Use what?";
        return what;
    }

    public String getSlotIsOutOfRange()
    {
        String outOfRange = "Slot is out of range";
        return outOfRange;
    }

    public String getShowWhat()
    {
        String what = "Show what?";
        return what;
    }

    public String getHelpMenu()
    {
        String helpMenu = "Welcome to the Help menu system..."+
                "\nChoose one of the following options."+
                "\n---------------------------------------"+
                "\n1. Game Commands"+
                "\n2. Game Goals"+
                "\n3. Game Tips & Tricks";
        return helpMenu;
    }

    public String getHelpCommandWords()
    {
        String helpCommandWords = "Your command words are:";
        return helpCommandWords;
    }

    public String getHelpGoals()
    {
        String goals = "The goals of the game is to defeat the devil";
        return goals;
    }

    public String getNoTipsAvaiable()
    {
        String noTips = "No tips or tricks available :( ";
        return noTips;
    }

    public String getInvalidChoice()
    {
        String choice = "Invalid menu choice";
        return choice;
    }

    public String getMapLayoutPart1()
    {
        String layout1 = "   \u256D\u2500\u2500\u2500\u2500\u252C\u2500\u2500\u2500" +
                "\u252C\u2500\u2500\u2500\u2500\u256E";
        return layout1;
    }

    public String getMapLayoutPart2()
    {
        String layout2 = "   \u2570\u2500\u2500\u2500\u2500\u2534\u2500\u2500\u2500" +
                "\u2534\u2500\u2500\u2500\u2500\u256F";
        return layout2;
    }

    public String getMapLayoutPart3()
    {
        String layout3 = "       0      1      2";
        return layout3;
    }

    public String getYouCurrentlyHaveHp(IPlayer player)
    {
        String currently = "You currently have: "+player.getHealth()+ " hp";
        return currently;
    }

    public String getYouCurrentlyHavePoints(IPlayer player)
    {
        String currently = "You currently have: "+player.getScore()+" points";
        return currently;
    }

    public String getName()
    {
        String name = "Name: ";
        return name;
    }

    public String getCurrentWeapon(IPlayer player)
    {
        String currentWeapon = "Name: "+
//                player.getWeapon().getName()+ "\nPOWER: "+
                player.getWeapon().getPower()+"\nMULTIPLIER: "+
                player.getWeapon().getMultiplier()+" x"+"\n";
//                player.getWeapon().getDescription();
        return currentWeapon;
    }

    public String getHuh()
    {
        String huh = "Huh?";
        return huh;
    }
    public String getGoWhere()
    {
        String goWhere = "Go where?";
        return goWhere;
    }

    public String getYouEnteredANewRoom(IRoom room)
    {
        String enteredNewRoom = "You entered " + room.getName() 
                + "\nYou see " + room.getDescription() 
                + "\n";
        return enteredNewRoom;
    }

    public String getYouRanIntoAWall()
    {
        String intoWall = "You ran into wall :(";
        return intoWall;
    }
    public String getYouWentBackToPreviousRoom()
    {
        String wentBack = "You went back to the previous room.";
        return wentBack;
    }

    public String getNoSuchDirection()
    {
        String noSuchDirection = "Go where? No such direction found...";
        return noSuchDirection;
    }

    public String getExits( int counter, String s)
    {
        String exits = ""+counter+""+s;
                return exits;
    }

    public String getShowInventory(String string)
    {

        String inventory = string;
        return inventory;
    }

    public String getMonsterInfo(IMonster monster)
    {
        String monsterInfo = monster.getAscii() +
                "\n" + "Name: " + monster.getName() +
                "\n" + "Description: " + monster.getDescription() +
                "\n" + "Health: " + monster.getHealth() +
                "\n" + "Power: " + monster.getPower() +
                "\n";
        return monsterInfo;
    }
    
    public String getPlayerInfo(IPlayer player)
    {
        String playerInfo =  
                "Your health: " + player.getHealth() +
                "\n" + "Your power: " + player.getWeapon().getPower();
        return playerInfo;
    }

    public String getMonstersHealth(IMonster monster)
    {
        String healthMonster = "Monsters health is currently " +
                monster.getHealth() + "hp";
        return healthMonster;
    }

    public String getBattleOrFlee()
    {
        String battleOrFlee = "Type \"battle\" or \"flee\".";
        return battleOrFlee;
    }

    public String getAttackOrDrinkPotion()
    {
        String attackOrDrinkPotion = "attack or drink potion";
        return attackOrDrinkPotion;
    }

    public String getBattle(IBattle battle)
    {
        String startBattle = battle.start();
        return startBattle;
    }

    public String getWeapon(IItem item)
    {
        String weapon = item.getAscii()+
                "\nName: " + item.getName()+
                "\nPOWER: " + ((IWeapon) item).getPower()+
                "\nMULTIPLIER: " + ((IWeapon) item).getMultiplier() + "x"+
                "\n"+item.getDescription();
        return weapon;
    }

    public String getPotion(IItem item)
    {
        String potion = item.getAscii()+
                "\nName: " + item.getName()+
                "\nHEALTH RECOVERY: " + ((IPotion) item).getHealthRecovery()+
                "\nTIME RECOVERY: " + ((IPotion) item).getTimeRecovery()+
                "\n"+item.getDescription();
        return potion;
    }

    public String getSlotIsEmpty()
    {
        String emptySlot = "Slot is empty.";
        return emptySlot;
    }

    public String getSetCurrentWeapon(IPlayer player)
    {
        String setCurrentWeapon = "Your current weapon is now: " //+ player.getWeapon().getName()
        ;
        return setCurrentWeapon;
    }

    public String getPlayerHealth(IPlayer player)
    {
        String health = "Your health is now: " + player.getHealth() + "hp";
        return health;
    }
    
    public String getPlayerTime(ITimeTracker timeTracker)
    {
        String time = "Your time is now: " + timeTracker.calculateRemainingTime()+ "sec";
        return time;
    }

    public String getTypeSlotNumberToUse()
    {
        String useSlot = "Type number to use.";
        return useSlot;
    }

    public String getPotionRecovery(int i, IPlayer player)
    {
        String recovery = (i+1) +
                ". Potion:" +
                player.getInventory().potionArrayList().get(i).getHealthRecovery();
        return recovery;
    }

    public String getYouHaveNoPotions()
    {
        String noPotions ="You have no potions :(";
        return noPotions;
    }

    public String getThereIsAHelper()
    {
        String thereIsaHelper = "There is a helper, you can either \"talk\" , \"flee\" or \"kill\"!";
        return thereIsaHelper;
    }

    public String getHelperTalk()
    {
        String talk = "Hello my name is \"insert name here\" here is a tip ;) ... DON'T DIE!!!";
        return talk;
    }

    public String getKilledGuide()
    {
        String killedGuide = "You killed the guide, oh mighty swordsman!";
        return killedGuide;
    }

    public String getThereIsAChest()
    {
        String thereIsAChest = "There is a chest, type \"open\" to open!" +
                                "\n Or you can type \"skip\" to skip it!";
        return thereIsAChest;
    }

    String getWhatSlot()
    {
        String slot = "Do you want to insert this into a slot?"+
                "\nType slot number or \"drop\" to drop.";
        return slot;
    }

    String getYouSavedItemInThisSlot(int j)
    {
        String savedInSlotNumber = "You saved this item in slot: " + (j+1);
        return savedInSlotNumber;
    }

    String getYouDroppedTheItem()
    {
        String droppedItem = "You dropped the item";
        return droppedItem;
    }

    String getHmmWrongCommand()
    {
        String wrongCommand = "Hmm... Wrong command";
        return wrongCommand;
    }

    String getItsAEmptySpace()
    {
        String spaceIsEmpty = "Empty space :(";
        return spaceIsEmpty;
    }

    String getManyHyphens()
    {
        String manyHyphens = "-----------------------------------------------";
        return manyHyphens;
    }

    String getQuitWhat()
    {
        String quitWhat = "Quit what?";
        return quitWhat;
    }

    String getBattleWhat()
    {
        String battleWhat = "Battle what?";
        return battleWhat;
    }
    
    String getTimeRanOut ()
    {
        String timeRanOut = "Your time ran out, and you are now dead";
        return timeRanOut;
    }

    String getAllRoomsEntered () 
    {
        String allRoomsEntered = "The eye of Sauron teleports you to the lair of the demon realm";
        return allRoomsEntered;
    }
    
    String getIsLuciferDead ()
    {
        String isLuciferDed = "You defeated the devil!" +
        "\nBut the princess is in another castle... and the game is over.";
                return isLuciferDed;
    }
    
    

}
