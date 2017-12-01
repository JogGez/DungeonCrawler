package dungeonCrawler.logic;

import com.sun.org.apache.bcel.internal.generic.NEW;
import dungeonCrawler.aqu.*;
import dungeonCrawler.data.HighScore;
import dungeonCrawler.presentationConsole.CommandWord;
import sun.awt.geom.AreaOp;

import javax.lang.model.element.NestingKind;
import java.awt.*;
import java.io.File;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class GameText implements Serializable
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
        return "Welcome to Dungeon Crawler!"+
                "\nThis is a new, incredibly boring adventure game.";
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
        {"I don't know what you mean...", "What?", "Invalid Command", "Look at your keyboard when you type...",
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
        else if (item instanceof Special)
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
        return  "Your Health: " + player.getHealth() +
                "\n" + "Your Power: " + player.getWeapon().getPower() +
                "\n" + "Your Multiplier: " + player.getWeapon().getMultiplier();
    }

    public String getMonstersInfo(IMonster monster)
    {
        return  monster.getAscii() +
                "\n" + "Name: " + monster.getName() +
                "\n" + "Description: " + monster.getDescription() +
                "\n" + "Health: " + monster.getHealth() +
                "\n" + "Power: " + monster.getPower() +
                "\n";
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

    public String getSlotIsEmpty()
    {
        return "Slot is empty.";
    }

    public String getSetCurrentWeapon()
    {

        return "Your current weapon is now: " + ((IItem)player.getWeapon()).getName();
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
        String top = "  " + " ╔";
        String space = "  " + " \u2551";
        String bottom = "  " + " \u2570";
        String xCordinates = "      ";

        for (int i = 0; i < map.getWidth(); i++)
        {
            space += "     ";
            top += "-----";
            bottom += "-----";
            xCordinates += i + "    ";
        }

        space += "\u2551";
        top += "╗";
        bottom += "\u256F X";

        for (int i = 0; i < map.getHeight(); i++)
        {
            String mapString = " " + i + " \u2551";

            for (IRoom room : map.getRoomList())
            {
                if (room.getLocation().y == i)
                {
                    String insert = "";
                    if (room.getLocation().x == player.getLocation().x &&
                            room.getLocation().y == player.getLocation().y)
                    {
                        insert = "  P  ";
                    }
                    else if (room.isLocked())
                    {
                        insert = "  L  ";
                    }
                    else if (room.getHasBeenEntered())
                    {
                        insert = "  O  ";
                    }
                    else if (!room.getHasBeenEntered())
                    {
                        insert = "  X  ";
                    }
                    for (IMerchant merchant : map.merchantArrayList())
                    {
                        if (room.getLocation().x == merchant.getLocation().x && room.getLocation().y == merchant.getLocation().y)
                        {
                            insert = "  G  ";
                        }
                    }
                    for (IThief thief : map.thiefArrayList())
                    {
                        if (room.getLocation().x == thief.getLocation().x && room.getLocation().y == thief.getLocation().y)
                        {
                            insert = "  T  ";
                        }
                    }

                    mapString += insert;
                }
            }
            mapString += "\u2551";
            mapList.add(0, mapString);
            if (i < map.getHeight()-1)
                mapList.add(0,space);

        }


        mapList.add(0, top);
        mapList.add(0,"   Y");
        mapList.add(bottom);
        mapList.add(xCordinates);

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

    public String getWelcomeBack()
    {
        return "Welcome back " + player.getName();
    }

    public String getSeperator()
    {
        return "✪►----⇹-----✫-----⇹-----⇥ ╭∩╮ʕ•ᴥ•ʔ╭∩╮ ⇤-----⇹-----✫-----⇹-----◄✪";
    }

    public String getTest()
    {
        return "●▬▬▬▬▬▬▬▬▬▬▬▬๑۩۩๑▬▬▬▬▬▬▬▬▬▬▬▬▬●\n" +
                "▂▃▅▇█▓▒░۩۞۩۩۞۩░▒▓█▇▅▃▂\n" +
                "╭∩╮ʕ•ᴥ•ʔ╭∩╮\n" +
                "(¯`·._.·(¯`·._.·(¯`·._.· Your Text ·._.·´¯)·._.·´¯)·._.·´¯)\n" +
                "⋆ ✢ ✣ ✤ ✥ ✦ ✧ ✩ ✪ ✫ ✬ ✭ ✮ ✯ ✰ ✪\n" +
                "◄███▓▒░░  ░░▒▓███►" +
                "̲D̲̲u̲̲n̲̲g̲̲e̲̲o̲̲n̲̲ ̲̲C̲̲r̲̲a̲̲w̲̲l̲̲e̲r̲" +
                "̠D̠̠u̠̠n̠̠g̠̠e̠̠o̠̠n̠̠ ̠̠C̠̠r̠̠a̠̠w̠̠l̠̠e̠r" +
                "͟D͟͟u͟͟n͟͟g͟͟e͟͟o͟͟n͟͟ ͟͟C͟͟r͟͟a͟͟w͟͟l͟͟e͟r̠͟" +
                "͞D͞͞u͞͞n͞͞g͞͞e͞͞o͞͞n͞͞ ͞͞C͞͞r͞͞a͞͞w͞͞l͞͞e͞r͞" +
                "̅D̅̅u̅̅n̅̅g̅̅e̅̅o̅̅n̅̅ ̅̅C̅̅r̅̅a̅̅w̅̅l̅̅e̅r̅" +
                "↓ ᶠᵘᶜᵏ ᵗʰᶦˢ ᵍᵘʸ↓" +
                "↑ ᶠᵘᶜᵏ ʸᵒᵘ ᵗᵒᵒ ↑" +
                "" +
                "Darude- status\n" +
                "\n" +
                "☐ Not Sandstorm\n" +
                "\n" +
                "☑ Sandstorm" +
                "" +
                "(ಠ_ಠ)┌∩┐" +
                "╭∩╮(Ο_Ο)╭∩╮" +
                "¯\\_(ツ)_/¯" +
                "" +
                "0%" +
                "█▒▒▒▒▒▒▒▒▒\n" +
                "\n" +
                "10%\n" +
                "███▒▒▒▒▒▒▒\n" +
                "\n" +
                "30%\n" +
                "█████▒▒▒▒▒\n" +
                "\n" +
                "50%\n" +
                "███████▒▒▒\n" +
                "\n" +
                "99% \n" +
                "██████████]\n" +
                "\n" +
                "100%\n" +
                "██████████ " +
                "" +
                "☆.´ `. ☽¸.☆\n" +
                "(͡๏̯͡๏)(͡๏̯͡๏)\n" +
                "( ,,)( ,,).\n" +
                "¯**´¯¯**´¯`" +
                "" +
                "✓✔✕✖" +
                "┬┴┬┴┤･ω･)ﾉ├┬┴┬┴"
                ;
    }

    public String getSymbols()
    {
        return  "㊀ ㊁ ㊂ ㊃ ㊄ ㊅ ㊆ ㊇ ㊈ ㊉ ㊊ ㊋ ㊌ ㊍ ㊎ ㊏ ㊐ ㊑ ㊒ ㊓ ㊔ " +
                "㊕ ㊖ ㊗ ㊘ ㊙ ㊚ ㊛ ㊜ ㊝ ㊞ ㊟ ㊠ ㊡ ㊢ ㊣ ㊤ ㊥ ㊦ ㊧ ㊨ ㊩ " +
                "㊪ ㊫ ㊬ ㊭ ㊮ ㊯ ㊰ ➀ ➁ ➂ ➃ ➄ ➅ ➆ ➇ ➈ ➉·¨…¦┅┆➊ ➋ ➌ ➍" +
                "➎ ➏ ➐ ➑ ➒ ➓ α ɐ β ɔ 卐 ™ © ® ¿ ¡ ½ ⅓ ⅔ ¼ ¾ ⅛ ⅜ ⅝ ⅞ ℅ " +
                "№ ⇨ ❝ ❞ ℃ ∃ ┈ ℑ ∧ ∠ ∨ ∩ ⊂ ⊃ ∪ ⊥ ∀ Ξ Γ ə ɘ ε ɟ ɥ ɯ и η " +
                "ℵ ℘ ๏ ɹ ʁ ℜ я ʌ ʍ λ ℓ ч ∞ Σ Π ⌥ ⌘ ¢ € £¥ Ⓐ Ⓑ Ⓒ Ⓓ Ⓔ Ⓕ " +
                "Ⓖ Ⓗ Ⓘ Ⓙ Ⓚ Ⓛ Ⓜ Ⓝ Ⓞ Ⓟ Ⓠ Ⓡ Ⓢ Ⓣ Ⓤ Ⓥ Ⓦ Ⓧ Ⓨ Ⓩ ╧ ╨ ╤ ╥ ╙ " +
                "ⓐ ⓑ ⓒ ⓓ ⓔ ⓕ ⓖ ⓗ ⓘ ⓙ ⓚ ⓛ ⓜ ⓝ ⓞ ⓟ ⓠ ⓡ ⓢ ⓣ ⓤ ⓥ ⓦ ⓧ ⓨ ⓩ " +
                "╒ ╓ ╫ ╪ ┘ ツ ♋ 웃 유 Σ ⊗ ♒ ☠ ☮ ☯ ♠ Ω ♤ ♣ ♧ ♥ ♡ ♦ ♢ ♔ ♕ ♚ ♛ " +
                "★ ☆ ✮ ✯ ☄ ☾ ☽ ♏ ╘ ┌ ╬ ☼ ☀ ☁ ☂ ☃ ☻ ☺ ۞ ۩ ♬ ✄ ✂ ✆ ✉ ✦ ✧ " +
                "∞ ♂ ♀ ☿ ❤ ❥ ❦ ❧ ™ ® © ✗ ✘ ⊗ ♒ ▢ ╛ ┐ ─ ┼ ▲ △ ▼ ▽ ◆ ◇ ○ ◎ ● ◯ " +
                "Δ ◕ ◔ ʊ ϟ ღ 回 ₪ ✓ ✔ ✕ ✖ ☢ ☣ ☤ ☥ ☦ ☧ ☨ ☩ ☪ ☫ ☬ ☭ └ ┴ ┬ ├ ┊╱ ╲ ╳ " +
                "¯ – — ≡ ჻ ░ ▒ ▓ ▤ ▥ ▦ ▧ ▨ ▩ █ ▌ ▐ ▀ ▄ " +
                "◠ ◡ ╭ ╮ ╯ ╰ │ ┤ ╡ ╢ ╖ ╕ ╣ ║ ╝ ╜ ╞ ╟ ╚ ╔ ╩ ╦ ╠ ═ " +
                "{ ｡ ^ ◕ ‿ ◕ ^ ｡ } ( ◕ ^ ^ ◕ ) ✖ ✗ ✘ ♒ ♬ ✄ ✂ ✆ ✉ ✦ ✧ ♱ ♰ ♂ ♀☿ " +
                "❤ ❥ ❦❧ ™ ® © ♡ ♦ ♢ ♔ ♕ ♚ ♛ ★ ☆ ✮ ✯ ☄ ☾ ☽ ☼ ☀ ☁ ☂ ☃ " +
                "☻ ☺ ☹ ☮ ۞ ۩ ε ї з ☎ ☏ ¢ ☚ ☛ ☜ ☝ ☞ ☟ ✍ ✌ ☢ ☣ ☠ ☮ ☯ " +
                "♠ ♤ ♣ ♧ ♥ ♨ ๑ ❀ ✿ ψ ♆ ☪ ☭ ♪ ♩ ♫ ʊ ϟ ღ ツ 回 ₪ 卐 © ® ¿ ¡ " +
                "½ ⅓ ⅔ ¼ ¾ ⅛ ⅜ ⅝ ⅞ ℅ № ⇨ ❝ ❞ ℃ ◠ ◡ ╭ ╮╯╰ ★ ☆ ⊙ ¤ ㊣ ★ ☆ ♀ ◆ ◇ ™ " +
                "║ ▼ ╒ ▲ ◣ ◢ ◥ ▼ △ ▽ ⊿ ◤ ◥ △ ▴ ▵ ▶ ▷ ▸ ▹ ► ▻ ▼ ▽ ▾ ▿ ◀ ◁ ◂ ◃ ◄ ◅ " +
                "▆ ▇ █ █ ■ ▓ 回 □ 〓 ≡☌ ╝╚╔╗╬ ═╓╩ ┠┨┯┷┏ ┓┗┛┳⊥ ﹃﹄┌ ┐└┘∟「 」↑↓→ ←↘↙♀ ♂" +
                "┇┅﹉﹊ ﹍﹎╭╮╰╯ *^_^* ^*^ ^-^ ^_^ ^︵^∵∴‖ ︱︳︴﹏ ﹋﹌♂♀ ♥♡☜☞☎ ☏" +
                "⊙◎☺☻ ►◄▧▨ ♨◐◑↔↕ ▪▫☼♦▀ ▄█▌▐ ░▒▬♦◊ ◦ ☼ ♠ ♣ ▣ ▤▥▦▩ ぃ◘◙◈" +
                "♫ ♬♪♩♭♪ の☆→あ ￡❤｡◕‿ ◕｡✎✟ஐ ≈๑۩ ۩.. ..۩۩๑ ๑۩۞۩๑ ✲❈➹ ~.~◕ ‿-｡☀☂☁ " +
                "【】┱┲❣ ✚✪✣ ✤✥ ✦❉ ❥❦❧❃ ❂❁❀✄☪ ☣☢☠☭♈ ➸✓✔✕ ✖㊚㊛ *.:｡ ✿*ﾟ‘ﾟ･ ⊙¤㊣" +
                "★☆ ♀◆◇ ◣◢◥▲△▽⊿◤ ◥▆▇ ██■▓ 回□〓≡╝ ╚╔╗ ╬═╓╩ ┠┨┯┷┏ ┓┗┛ ┳⊥﹃﹄ ┌┐└┘∟ 「」" +
                "↑↓ → ← ↘ ↙♀♂┇┅﹉ ﹊﹍﹎ ╭╮╰╯ *^_^* ^*^ ^-^ ^_^ ^︵^∵ ∴‖ ︱︳ ︴﹏﹋﹌ ♂♀♥♡☜ ☞" +
                "☎☏⊙ ◎☺☻►◄ ▧▨♨◐◑ ↔↕▪▫ ☼♦▀▄█ ▌▐░▒▬ ♦◊◦☼ ♠♣▣▤▥ ▦▩ぃ◘◙ ◈♫♬♪ ♩♭♪の☆" +
                " →あ￡❤｡ ◕‿◕｡ ✎✟ஐ≈ ๑۩۩.. ..۩ ✉ ✍ ✎ ✏ ✐๑✲❈ ➹ ~.~◕‿-｡ ☀☂☁【】 ┱┲" +
                "❣✚ ✪✣✤✥ ✦❉❥❦ ❧❃❂❁❀ ✄☪☣☢☠ ☭♈➸✓✔✕✖㊚ ㊛♧♤♧♡♬♪*.:｡✿*ﾟ ‘ﾟ･ ◊♥" +
                "╠═╝▫■๑»«¶ஐ©† εïз♪ღ♣ ♠•± °•ิ.•ஐ இ * × ○ ▫ ✑ ✒ ⌨ ۩ ๑ ๑۩ ۞ ۩ ┭┮┯♂ • ♀ ◊ ©" +
                " ¤ ▲ ↔ ™ ® ☎ ε ї з ♨ ☏ ☆ ★ ▽ △ ▲ ∵ ∴ ∷ ＃ ♂ ♀ ♥ ♠ ♣ ☹ ☺ ☻┌ " +
                "┍┎ ┏ ┐ ┑┓ ♭♫♪ﻬஐღ ↔↕↘••● ¤╬﹌▽☜♥☞ ♬✞♥♕☯☭☠☃ ─ ━ │ ┃ ┄ ┅ ┆ ┇ ┈ ┉ ┊ ┋ ≨ ≩╨╩ ╪ ╫ ╬╏" +
                "═≂ ≃ ≄ ≅ ≆ ≇ ≈ ≉ ≊ ≋ ≌ ≍ ≎ ≏ ≐ ≑ ≒ ≓ ≔ ≕ ≖ ≗ ≘ ≙ ≚ ≛ ≜ ≝ ≞ ≟ ≠ ≡≢ ≣ ≤ ≥ ≦≧" +
                "␛ ␡ ␚ ␟ ␘ ␠ ␤ ␋ ␌ ␍ ␎ ␏ ␐ ␑ ␒ ␓ ␔ ␕ ␖ ␗ ␙ ␜ ␝ ␞╣ ╤ ╥ ╦ ╧ ╗ ╘ ╙ ╚ ╛╡ ┼ ┽ ┾ ┿ ╀ ╁ ╂ ╃ ╓ ╔ ╕ ╖ " +
                "♈ ♉ ♊ ♋ ♌ ♍ ♎ ♏ ♐ ♑ ♒ ♓ ╮ ╯ ╰ ╱ ╲ ╳ ‟ †‡•‣" +
                "▀ ▁ ▂ ▃ ▄ ▅ ▆ ▇ █ ▉ ▊ ▋ ▌ ▍ ▎ ▏ ▐ ░ ▒ ▓ ▔ ▕ ■ □ ▢ ▣ ▤ ▥ ▦ ▧ ▨ ▩ ▪ ▫ ▬ ▭▮▯" +
                "╭ ◞ ◟ ◠ ◡ ◢ ◣ ♔ ♕ ♖ ♗ ♘ ♙ ♚ ♛ ♝ ♞ ♟ ♠ ♡ ♢ ♣ ☔ ☕ ☖ ☗ ☘ ☙ ☊ ☋ ☌ ☍ ☎ ☏☐" +
                " ╴ ╵ ╶ ╷ ╸ ╹ ╺ ╻ ╼ ╽ ╾ ╿ ▰ ▱ ◆ ◇ ◈ ◉ ◊ ○ ◌ ◍ ◎ ● ◐ ◑ ◒ ◓ ◔ ◕ ◖ ◗ ◘ ◙ ◚ ◛ ◜ ◝ " +
                "◤ ◥ ◦ ◧ ◨ ◩ ◪ ◫ ◬ ◭ ◮ ◯◽ ◾ ◿ ☀ ☁ ☂ ☃ ☄ ★ ☆ ☇ ☈ ☉ ☑ ☒ ☓ ♅ ♆ ♇♜ ⇜ ⇝ ⇞ ⇟ ⇠ ⇡⇢⇣☟ " +
                "☠ ☡ ☢ ☣ ☤ ☥ ☦ ☧ ☨ ☩ ☪ ☫ ☬ ☭ ☮ ☯ ☰ ☱ ☲ ☳ ☴ ☵ ☶ ☷ ☸ ☹ ☺ ☻ ☼ ☽ ☾ ☿ ♀ ♁ ♂ ♃ ♄ℕ" +
                "♤ ♥ ♦ ♧ ♨ ♩ ♪ ♫ ♬ ♭ ♮ ♯ ♰ ♱ ´ ῾ ‐ ‑ ‒ – — ― ‖ ‗ ‘ ’ ‚ ‛ “ ” „ ℋ ℌ ℍ ℎ ℏ ℐ ℑ ℒ ℓ ℔․ ‥ … ‧ " +
                "‰ ‱ ′ ″ ‴ ‵ ‶ ‷ ‸ ‹ › ※ ‼ ‽ ‾ ‿ ⁀ ⁁ ⁂ ⁃ ⁄ ⁅ ⁆ ⁑ ⁞ ₠ ₡ ₢ ₣ ₤ ₥ ₦ ₧ ₨ ₩ ₪ ₫ € ₭₮ ₯ ℀ ℁ ℂ ℃ ℄ ℅ ℆ " +
                "ℇ ℈ ℉ ℊ № ℗ ℘ ℙ ℚ ℛ ℜ ℝ ℞ ℟ ℠ ℡ ™ ℣ ℤ ℥ Ω ℧ ℨ ℩ K Å ℬ ℭ ℮ ℯℰ ℱ Ⅎ ℳ ℴ ℵ ℶ ℷ ℸ " +
                "⅍ ⅎ ⅓ ⅔ ⅕ ⅖ ⅗ ⅘ ⅙ ⅚ ⅛ ⅜ ⅝ ⅞ ⅟ ⇍ ⇎ ≺ ≻ ≼ ≽ ≾ ≿ ⊀ ∏ ∐ ∑ −∓" +
                "⁰ ⁱ \u2072 \u2073 ⁴ ⁵ ⁶ ⁷ ⁸ ⁹ ⁺ ⁻ ⁼ ⁽ ⁾ ⁿ ₀ ₁ ₂ ₃ ₄ ₅ ₆ ₇ ₈ ₉ ₊ ₋ ₌ ₍ ₎ " +
                "⇤ ⇥ ⇦ ⇧ ⇨ ⇩ ⇪ ⇹ ⇺ ⇻ ⇼ ⇽ ∔ ↶ₐ ₑ ₒ ₓ ₔ" +
                "Ⅰ Ⅱ Ⅲ Ⅳ Ⅴ Ⅵ Ⅶ Ⅷ Ⅸ Ⅹ Ⅺ Ⅻ Ⅼ Ⅽ Ⅾ Ⅿ ⅰ ⅱ ⅲ ⅳ ⅴ ⅵ ⅶ ⅷ ⅸ ⅹ ⅺ ⅻ ⅼ ⅽ ⅾ ⅿ " +
                "╢ ↵ ↷← ↑ → ↓ ↔ ↕ ↖ ↗ ↘ ↙ ↚ ↛ ↜ ↝ ↞ ↟ ↠ ↡ ↢ ↣ ↤ ↥ ↦ ↧ ↨ ↩ ↪ ↫ ↬ ↭ ↮ ↯ ↰ ↱ ↲ ↳↴ ￡ " +
                "↸ ↹ ↺ ↻ ↼ ↽ ↾ ↿ ⇀ ⇁ ⇂ ⇃ ⇄ ⇅ ⇆ ⇇ ⇈ ⇉ ⇊ ⇋ ⇌⇏ ⇐ ⇑ ⇒ ⇓ ⇔ ⇕ ⇖ ⇗ ⇘ ⇙ ⇚ ⇛ ∽∾⊣ ⊤" +
                "⇫ ⇬ ⇭ ⇮ ⇯ ⇰ ⇱ ⇲ ⇳ ⇴ ⇵ ⇶ ⇷ ⇸ ⇾ ⇿ ∀ ∁ ∂ ∃ ∄ ∅ ∆ ∇ ∈ ∉ ∊ ∋ ∌ ∍ ∎ ◙ ▤▥▦▧▨ ▩ ♤ ♧♡" +
                "∕ ∖ ∗ ∘ ∙ √ ∛ ∜ ∝ ∞ ∟ ∠ ∡ ∢ ∣ ∤ ∥ ∦ ∧ ∨ ∩ ∪ ∫ ∬ ∭ ∮ ∯ ∰ ∱ ∲ ∳ ∴ ∵ ∶ ∷ ∸ ∹ ∺ ∻ ∼ ∿ ≀ ≁ " +
                "≪ ≫ ≬ ≭ ≮ ≯ ≰ ≱ ≲ ≳ ≴ ≵ ≶ ≷ ≸ ≹ ⁇ ⁈ ⁉ ‼ ‽ ⁇ ⁈ ⁉ ‼ ‽ ™ © ®⍘ ⍙ ♬ ♭ ♮ ♯♰♱⊁ " +
                "⊂ ⊃ ⊄ ⊅ ⊆ ⊇ ⊈ ⊉ ⊊ ⊋ ⊌ ⊍ ⊎ ⊏ ⊐ ⊑ ⊒ ⊓ ⊔ ⊕ ⊖ ⊗ ⊘ ⊙ ⊚ ⊛ ⊜ ⊝ ⊞ ⊟ ⊠ ⊡ " +
                "⊢⊥⌕⌖ ⊦ ⊧ ⊨ ⊩ ⊪ ⊫ ⊬ ⊭ ⊮ ⊯ ⊰ ⊱ ⊲ ⊳ ⊴ ⊵ ⊶ ⊷ ⊸ ⊹ ⊺ ⊻ ┌ ┍ ┎ ┏ ┐ ┑ ┒ ┓" +
                "⋟ ⋠ ⋡ ⋢ ⋣ ⌓⌔⊼ ⊽ ⊾ ⊿ ⋀ ⋁ ⋂ ⋃ ⋄ ⋅ ⋆ ⋇ ⋈ ⋉ ⋊ ⋋ ⋌ ⋍ ⋎ ⋏ ⋐ ⋑ ⋒ ⋓ ⋔ ⋕ ⋖ ⋗ ⋘ ⋙ ⋚ ⋛ ⋜ ⋝ ⋞ " +
                "♤ ♥♦♧♨ ⋤ ⋥ ⋦ ⋧ ⋨ ⋩ ⋪ ⋫ ⋬ ◐ ◑ ☢ ⊗ ⊙ ◘❃ ❂ ○ ◎ ● ◯ ◕ ◔ ┄ ┅ ┆ ┇ ┈ ┉ ┊ ┋ ♩ ♪ ♫ ♜ ♝⋭ ⋮ ⋯ ⋰ ⋱ " +
                "⋲ ⋳ ⋴ ⋵ ⋶ ⋷ ⋸ ⋹ ⋺ ⋻ ⋼ ⋽ ⋾ ⋿ ⌀ ⌁ ⌂ ⌃ ⌄ ⌅ ⌆ ⌇ ⌈ ⌉ ⌊ ⌋ ⌌ ⌍– ぱ ⌎ ⌐ ⌑⌒♔ ♕ ⌗ ⌘ ⌙ ⌚ ⌛ ⌜ " +
                "░ ▒ ▓ ▔ ▕ ª ↀ ↁ ↂ Ↄ ↄ ↅ⍚ ␋ ␢ ␣ ─ ━ │ ┃ ⌾ ⌿ ⍀ ⍁♞ ♟ ♠ ♡ ♢♣⌝ ⌞ ⌟ ⌠ ⌡ ⌢ ⌣ ⌤ ⌥ " +
                "⌦ ⌧ ⌨ 〈 〉 ⌫ ⌬ ⌭ ⌮ ⌯ ⌰ ⌱ ⌲ ⌳ ⌴ ⌵ ⌶ ⌷ " +
                "⌸ ⌹ ⌺ ⌻ ⌼ ⌽ ⍂ ⍃ ⍄ ⍅ ⍆ ⍇ ⍈ ⍉ ⍊ ⍋ ⍌ ⍍ ⍎ ⍏ ⍐ ⍑ ⍒ ⍓ ⍔ ⍕ ⍖ ⍗ ♎ ♏ ♐ ♑ ♒ ♓ ♖ ♗ ♘ ♙♚♛" +
                "頹 – 衙 – 浳 – 浤 – 搰 – 煤 – 洳 – 橱 – 橱 – 煪 – ㍱ – 煱 – 둻 – 睤 – ㌹ – 楤 – ぱ – - " +
                "椹– 畱 – 煵 – 田 – つ – 煵 – 엌 – 嫠 – 쯦 – 案 – 迎 – 是 – 從 – 事 – 網 – 頁 – 設 – 計 – 簡";
    }

    public String getFilesInFolder(File[] files)
    {
        String output = "";
        for ( int i=0; i<files.length; i++ )
        {
            output += (i+1) + ". " + files[i].getName() + "\n";
        }
        return output;
    }

    public String getUseKey()
    {
        return "You can't use a key here.";
    }

    public String getPlayerName()
    {
        return "Name: " + player.getName();
    }

    public String getTypeXCoordinate()
    {
        return "Type X Coordinate";
    }

    public String getTypeYCoordinate()
    {
        return "Type Y Coordinate";
    }

    public String getWrongInputCoordinate()
    {
        return "Must only contain digits and be inside the range of the coordinate system.";
    }

    public String getTeleportToLockedRoomNoKey()
    {
        return "You teleported to a locked room and have no keys ;/ ... Teleport Wasted...";
    }

    public String getUseSpecialBomb()
    {
        return "BOOM ... Monsters health reduced by 50%";
    }

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
            System.out.println((room.getLocation().y + 1) == (player.getLocation().y + 1) && (room.getLocation().x - 1) == (player.getLocation().x - 1));
            if ((room.getLocation().y + 1) == (player.getLocation().y + 1) && (room.getLocation().x - 1) == (player.getLocation().x - 1))
            {
                System.out.println(room.getLocation());
                topLeft = getContentFromRoom(room);
            }
            else if (room.getLocation().y + 1 == player.getLocation().y + 1)
            {
                topCenter = getContentFromRoom(room);
            }
            else if (room.getLocation().y + 1 == player.getLocation().y + 1 && room.getLocation().x + 1 == player.getLocation().x + 1)
            {
                topRight = getContentFromRoom(room);
            }
            else if (room.getLocation().x - 1 == player.getLocation().x - 1)
            {
                left = getContentFromRoom(room);
            }
            else if (room.getLocation().x + 1 == player.getLocation().x + 1)
            {
                right = getContentFromRoom(room);
            }
            else if (room.getLocation().y - 1 == player.getLocation().y - 1 && room.getLocation().x - 1 == player.getLocation().x - 1)
            {
                bottomLeft = getContentFromRoom(room);
            }
            else if (room.getLocation().y - 1 == player.getLocation().y - 1)
            {
                bottomCenter = getContentFromRoom(room);
            }
            else if (room.getLocation().y - 1 == player.getLocation().y - 1 && room.getLocation().x + 1 == player.getLocation().x + 1)
            {
                bottomRight = getContentFromRoom(room);
            }
        }

        ArrayList<String> top = new ArrayList<>();
        ArrayList<String> middle = new ArrayList<>();
        ArrayList<String> bottom = new ArrayList<>();

        for (int i = 0; i < GameSettings.getRoomContents()-1; i++)
        {
            //top.add(topLeft.get(i) + top.get(i)+ topRight.get(i));
            //System.out.println(topLeft.get(i) + top.get(i)+ topRight.get(i));
        }






        return null;
    }

    private ArrayList<String> getContentFromRoom(Room room)
    {
        ArrayList<String> list = new ArrayList<>();
        for (RoomContent content : room.getContentArray())
        {
            if (content instanceof Monster) list.add("Monster");
            else if (content instanceof Chest) list.add("Chest");
            else if (content instanceof Guide) list.add("Guide");
            else if (content == null) list.add("");
            else list.add("NO ROOM");
        }

        return list;
    }
}
