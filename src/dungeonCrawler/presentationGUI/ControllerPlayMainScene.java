package dungeonCrawler.presentationGUI;

import dungeonCrawler.aqu.*;
import dungeonCrawler.logic.GameText;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.util.Date;

public class ControllerPlayMainScene implements IGame
{
    public TextArea textareaPlayScene;
    ILogicFacade logic;
    // Stores what room we are currently in
    private IMap map;
    // We are storing the class player's name for player.
    private IPlayer player;
    //Creating print to console object
    private GameText gameText= new GameText();
    //Create timeTracker.
    private ITimeTracker timeTracker;

    public Button btnUp;
    public Button btnDown;
    public Button btnRight;
    public Button btnLeft;

    @FXML
    public void handleMove(ActionEvent event)
    {
        if(((Control)event.getSource()).getId().equals("Up"))
//        if(((Control)event.getSource()).getId().contains("Up"))
        {
            playerMove(new Point(logic.getPlayer().getLocation().x, logic.getPlayer().getLocation().y + 1));
        }
        else if (((Control)event.getSource()).getId().equals("Down"))
        {
            playerMove(new Point(logic.getPlayer().getLocation().x, logic.getPlayer().getLocation().y - 1));
        }
        else if (((Control)event.getSource()).getId().equals("Right"))
        {
            playerMove(new Point(logic.getPlayer().getLocation().x+1, logic.getPlayer().getLocation().y ));
        }
        else if (((Control) event.getSource()).getId().equals("Left"))
        {
            playerMove(new Point(logic.getPlayer().getLocation().x - 1, logic.getPlayer().getLocation().y));
        }
        else
        {
           textareaPlayScene.setText(gameText.getYouRanIntoAWall()); //logic.gameText.getYouRanIntoAWall()//logic.getGameText().getYouRanIntoAWall()
        }

    }




    private void playerMove(Point location)
    {
        if (!logic.getMap().isRoomLocked(location))
        {
            logic.getPlayer().setLocation(location);

            if (location.equals(logic.getPlayer().getLastLocation()))
            {
                //Prints "You went back to the previous room."
//                printToConsole(gameText.getYouWentBackToPreviousRoom());
                textareaPlayScene.setText(gameText.getYouWentBackToPreviousRoom());
            }
            else
            {
                //Prints "You entered new room."
//                printToConsole(gameText.getYouEnteredANewRoom(logic.getCurrentRoom()));
                textareaPlayScene.setText(gameText.getYouEnteredANewRoom(logic.getCurrentRoom()));
                map.setRoomHasBeenEntered(player.getLocation());
            }

            logic.getMap().merchantMove();
            logic.getMap().thiefMove();
//            checkRoom();
        }
        else if (logic.getMap().isRoomLocked(location) && logic.getNumberOfAvailableKeys() == 0)
        {
//            printToConsole("Room is locked and you have no keys :(");
//            checkRoom();
        }
        else
        {
//            printToConsole("Room is locked, but you have a key :)");
//            printToConsole(gameText.getTypeSlotNumberToUse());

            for (int j = 0; j < logic.getNumberOfAvailableKeys(); j++)
            {
                //Prints
//                printToConsole(gameText.getKey(j));
            }
//            String input = parser.getUserInput();
//            int index = Integer.parseInt(input) -1;
            logic.getCurrentRoom().setLocked(false);
            logic.getMap().unlockRoom(location);

//            logic.useKey(index);

            //Prints "Yom yom ... Your health is now: " + player.getHealth() + "hp"
//            printToConsole("Room has been unlocked");

            player.setLocation(location);

            //Prints "You entered new room."
//            printToConsole(gameText.getYouEnteredANewRoom(logic.getCurrentRoom()));

            map.setRoomHasBeenEntered(player.getLocation());
            map.merchantMove();
            map.thiefMove();
//            checkRoom();
        }
    }

//    public void checkRoom()
//    {
//        if (map.roomContainsMerchant())
//        {
//            textareaPlayScene.setText(gameText.getMerchant());
//            textareaPlayScene.setText(gameText.getInventory(map.getMerchant().getInventory()));
//
//
//            int merchantIndex = 0;
//            outerLoop:
////            while (true)
////            {
////                String input = parser.getUserInput();
//
//                //Checks amount of inventory slots.
//                for (int j = 0; j < map.getMerchant().getInventory().getSize(); j++)
//                {
//                    //If input is equal to our inventory size (+1 because array starts at 0), it will stop our loop.
//                    if (Integer.toString(j + 1).equals(input) || input.equals("s") || input.equals("skip"))
//                    {
//                        for (int i = 0; i < map.getMerchant().getInventory().getSize(); i++)
//                        {
//                            // If the players input is equal to i(+1 because array starts at 0), it will add the item to our designated slot.
//                            if (input.equals(String.valueOf(i + 1)))
//                            {
//                                merchantIndex = Integer.parseInt(input) - 1;
//                            }
//                        }
//                        // Drops the item
//                        if (input.equals("skip") || input.equals("s"))
//                        {
//                            //Prints "You dropped the inventoriesItem"
//                            textareaPlayScene.setText(gameText.getYouDroppedTheItem());
//                        }
//
//                        //Jumps all the way out of the while-loop, because of the outerLoop.
//                        break outerLoop;
//                    }
//                }
//                //Prints "Hmm... Wrong command"
//                textareaPlayScene.setText(gameText.getWhatDoYouMean());
////            }
//
//            changeInventory(map.getMerchant().getInventory().getItem(merchantIndex));
//            textareaPlayScene.setText(gameText.getInventory(player.getInventory()));
//
//
////            changeInventory(map.getItemFromMerchant());
//        }
//
//        if (map.roomContainsThief())
//        {
//            textareaPlayScene.setText(gameText.getThief());
//            map.removeThief();
//        }
//
//        for (int i = 0; i < map.getNumberOfContent(); i++)
//        {
//            switch (map.checkRoomContent(i))
//            {
//                case "Monster":
//                    //Prints info about monster.
//                    textareaPlayScene.setText(gameText.getContentInfo(i));
//                    //Prints "Your health is currently " + player.getHealth() + "hp"
//                    textareaPlayScene.setText(gameText.getPlayerInfo());
//                    //Prints "Type \"battle\" or \"flee\"." // We need to type more information!
//                    textareaPlayScene.setText(gameText.getBattleOrFlee());
//
//                    while (true)
//                    {
//                        String input = parser.getUserInput();//returns a String
//                        if (input.equals("battle") || input.equals("b"))
//                        {
//                            startBattle(logic.doBattle(i));
//
//                            logic.getCurrentRoom().removeContent(i);
//
//                            break;
//                        }
//                        else if (input.equals("flee") || input.equals("f"))
//                        {
//                            player.setLocation(player.getLastLocation());
//                            break;
//                        }
//                        else
//                        {
//                            textareaPlayScene.setText(gameText.getWhatDoYouMean());
//                        }
//                    }
//                    if (i < map.getNumberOfContent() - 1)
//                    {
//                        parser.userPressEnter();
//                    }
//                    break;
//                case "Chest":
//                    //Prints info about chest.
//                    textareaPlayScene.setText(gameText.getContentInfo(i));
//
//                    while (true)
//                    {
//                        String input = parser.getUserInput();
//                        if (input.equals("open") || input.equals("o"))
//                        {
//                            textareaPlayScene.setText(gameText.getSeperator());
//                            changeInventory(((IChest)map.getCurrentRoom().getContent(i)).getItem());
//
//                            //This removes the chest
//                            logic.getCurrentRoom().removeContent(i);
//
//                            break;
//                        }
//                        else if (input.equals("skip") || input.equals("s"))
//                        {
//                            textareaPlayScene.setText(gameText.getSeperator());
//                            break;
//                        }
//                        else
//                        {
//                            //Prints "Hmm... Wrong command"
//                            textareaPlayScene.setText(gameText.getWhatDoYouMean());
//                        }
//                    }
//                    if (i < map.getNumberOfContent() - 1)
//                    {
//                        parser.userPressEnter();
//                    }
//                    break;
//                case "Guide":
//                    //Prints info about Guide.
//                    textareaPlayScene.setText(gameText.getContentInfo(i));
//
//                    while (true)
//                    {
//                        //Prints "There is a Guide, you can either \"talk\" , \"flee\" or \"kill\"!"
//                        textareaPlayScene.setText(gameText.getThereIsAGuide());
//
//                        String input = parser.getUserInput();
//                        if (input.equals("talk") || input.equals("t"))
//                        {
//                            //Prints "Hello my name is \"insert name here\" here is a tip ;) ... DON'T DIE!!!"
//                            textareaPlayScene.setText(gameText.getGuideTalk());
//                        }
//                        else if (input.equals("skip")|| input.equals("s"))
//                        {
//                            break;
//                        }
//                        else if (input.equals("kill")|| input.equals("k"))
//                        {
//                            //Prints "You killed the helper, oh mighty swordsman!"
//                            textareaPlayScene.setText(gameText.getKilledGuide());
//                            map.getCurrentRoom().removeContent(i);
//                            break;
//                        }
//                    }
//
//                    if (i < map.getNumberOfContent() - 1)
//                    {
//                        parser.userPressEnter();
//                    }
//                    break;
//                case "":
//            }
//
//        }
//
//        if (map.hasAllRoomBeenEntered())
//        {
//            parser.userPressEnter();
//            lastBossBattle();
//        }
//    }
//
//    public void changeInventory (IItem item)
//    {
//        printToConsole.print(gameText.getItemInfo(item));
//        printToConsole.print(gameText.getInventory(player.getInventory()));
//        printToConsole.print(gameText.getWhatSlot());
//
//        //A While loop that checks if his input is valid for his inventory size, or if he wants to drop his item.
//        outerLoop:
//        while (true)
//        {
//            String input = parser.getUserInput();
//
//            //Checks amount of inventory slots.
//            for (int j = 0; j < player.getInventorySize(); j++)
//            {
//
//                //If input is equal to our inventory size (+1 because array starts at 0), it will stop our loop.
//                if (Integer.toString(j + 1).equals(input) || input.equals("d") || input.equals("drop"))
//                {
//                    for (int i = 0; i < player.getInventorySize(); i++)
//                    {
//                        // If the players input is equal to i(+1 because array starts at 0), it will add the item to our designated slot.
//                        if (input.equals(String.valueOf(i + 1)))
//                        {
//                            player.getInventory().addItem(item, i);
//
//                            //Prints "You saved this inventoriesItem in slot: " + (i+1)
//                            printToConsole.print(gameText.getYouSavedItemInThisSlot(i));
//                        }
//                    }
//                    // Drops the item
//                    if (input.equals("drop") || input.equals("d"))
//                    {
//                        //Prints "You dropped the inventoriesItem"
//                        printToConsole.print(gameText.getYouDroppedTheItem());
//                    }
//
//                    //Jumps all the way out of the while-loop, because of the outerLoop.
//                    break outerLoop;
//                }
//            }
//            //Prints "Hmm... Wrong command"
//            printToConsole.print(gameText.getWhatDoYouMean());
//        }
//    }

    @Override
    public void injectLogic(ILogicFacade logicLayer)
    {
        logic = logicLayer;

    }



    public void handleAttack(ActionEvent event)
    {
    }

    public void handleFlee(ActionEvent event)
    {
    }

    public void handleInventory(ActionEvent event)
    {
    }


}
