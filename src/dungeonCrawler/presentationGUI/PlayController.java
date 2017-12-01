package dungeonCrawler.presentationGUI;

import dungeonCrawler.aqu.*;
import dungeonCrawler.logic.GameText;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;

import java.awt.*;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class PlayController implements IGame, Initializable
{
    @FXML
    public TextArea textAreaMain;
    @FXML
    public TextArea textAreaMap;
    @FXML
    public TextArea textAreaInventory;


    private ILogicFacade logic;
    // Stores what room we are currently in
    private IMap map;
    // We are storing the class player's name for player.
    private IPlayer player;
    //Creating print to console object
    private GameText gameText;
    //Create timeTracker.
    private ITimeTracker timeTracker;

    @FXML
    public Button btnUp;
    @FXML
    public Button btnDown;
    @FXML
    public Button btnRight;
    @FXML
    public Button btnLeft;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    @FXML
    public void handleMove(ActionEvent event)
    {

        switch (((Control)event.getSource()).getId())
        {
            case ("btnUp"):
                if (map.roomExists(new Point(player.getLocation().x, player.getLocation().y + 1)))
                {
                    playerMove(new Point(player.getLocation().x, player.getLocation().y + 1));
                }
                else textAreaMain.setText(gameText.getYouRanIntoAWall());
                break;
            case "btnDown":
                if (map.roomExists(new Point(player.getLocation().x, player.getLocation().y - 1)))
                {
                    playerMove(new Point(player.getLocation().x, player.getLocation().y - 1));
                }
                else textAreaMain.setText(gameText.getYouRanIntoAWall());
                break;
            case "btnLeft":
                if (map.roomExists(new Point(player.getLocation().x - 1, player.getLocation().y)))
                {
                    playerMove(new Point(player.getLocation().x - 1, player.getLocation().y));
                }
                else textAreaMain.setText(gameText.getYouRanIntoAWall());
                break;
            case "btnRight":
                if (map.roomExists(new Point(player.getLocation().x + 1, player.getLocation().y)))
                {
                    playerMove(new Point(player.getLocation().x + 1, player.getLocation().y));
                }
                else textAreaMain.setText(gameText.getYouRanIntoAWall());
                break;
            case "btnBack":
                playerMove(new Point(player.getLastLocation().x, player.getLastLocation().y));
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
                textAreaMain.setText(gameText.getYouWentBackToPreviousRoom());
            }
            else
            {
                //Prints "You entered new room."
                textAreaMain.setText(gameText.getYouEnteredANewRoom(logic.getCurrentRoom()));
                map.setRoomHasBeenEntered(player.getLocation());
            }

            map.merchantMove();
            map.thiefMove();
            checkRoom();

            textAreaMap.setText(gameText.getMap());
        }
        else if (map.isRoomLocked(location) && logic.getNumberOfAvailableKeys() == 0)
        {
            textAreaMain.setText("Room is locked and you have no keys :(");
//            checkRoom();
        }
        else
        {
            textAreaMain.setText("Room is locked, but you have a key :)");
            textAreaMain.appendText(gameText.getTypeSlotNumberToUse());

            for (int j = 0; j < logic.getNumberOfAvailableKeys(); j++)
            {
                //Prints
                textAreaMain.appendText(gameText.getKey(j));
            }
//            String input = parser.getUserInput();
//            int index = Integer.parseInt(input) -1;
//            logic.getCurrentRoom().setLocked(false);
//            map.unlockRoom(location);
//
//            logic.useKey(index);
//
//            //Prints "Yom yom ... Your health is now: " + player.getHealth() + "hp"
//            textAreaMain.setText("Room has been unlocked");
//
//            player.setLocation(location);
//
//            //Prints "You entered new room."
//            textAreaMain.setText(gameText.getYouEnteredANewRoom(logic.getCurrentRoom()));
//
//            map.setRoomHasBeenEntered(player.getLocation());
//            map.merchantMove();
//            map.thiefMove();
//            checkRoom();
        }
    }

    public void checkRoom()
    {
        if (map.roomContainsMerchant())
        {
            textAreaMain.setText(gameText.getMerchant());
            textAreaMain.appendText(gameText.getInventory(map.getMerchant().getInventory()));

//
//            int merchantIndex = 0;
//            outerLoop:
//            while (true)
//            {
//                String input = parser.getUserInput();
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
//                            printToConsole.print(gameText.getYouDroppedTheItem());
//                        }
//
//                        //Jumps all the way out of the while-loop, because of the outerLoop.
//                        break outerLoop;
//                    }
//                }
//                //Prints "Hmm... Wrong command"
//                printToConsole.print(gameText.getWhatDoYouMean());
//            }
//
//            changeInventory(map.getMerchant().getInventory().getItem(merchantIndex));
//            printToConsole.print(gameText.getInventory(player.getInventory()));


//            changeInventory(map.getItemFromMerchant());
        }

        if (map.roomContainsThief())
        {
            textAreaMain.setText(gameText.getThief());
            map.removeThief();
        }

        for (int i = 0; i < map.getNumberOfContent(); i++)
        {
            switch (map.checkRoomContent(i))
            {
            case "Monster":
                //Prints info about monster.
                textAreaMain.setText(gameText.getContentInfo(i));
                //Prints "Your health is currently " + player.getHealth() + "hp"
                textAreaMain.appendText(gameText.getPlayerInfo());
                //Prints "Type \"battle\" or \"flee\"." // We need to type more information!
                textAreaMain.appendText(gameText.getBattleOrFlee());

//                while (true)
//                {
//                    String input = parser.getUserInput();//returns a String
//                    if (input.equals("battle") || input.equals("b"))
//                    {
//                        startBattle(logic.doBattle(i));
//
//                        logic.getCurrentRoom().removeContent(i);
//
//                        break;
//                    }
//                    else if (input.equals("flee") || input.equals("f"))
//                    {
//                        player.setLocation(player.getLastLocation());
//                        break;
//                    }
//                    else
//                    {
//                        printToConsole.print(gameText.getWhatDoYouMean());
//                    }
//                }
//                if (i < map.getNumberOfContent() - 1)
//                {
//                    parser.userPressEnter();
//                }
                break;
            case "Chest":
                //Prints info about chest.
                textAreaMain.setText(gameText.getContentInfo(i));

//                while (true)
//                {
//                    String input = parser.getUserInput();
//                    if (input.equals("open") || input.equals("o"))
//                    {
//                        printToConsole.print(gameText.getSeperator());
//                        changeInventory(((IChest)map.getCurrentRoom().getContent(i)).getItem());
//
//                        //This removes the chest
//                        logic.getCurrentRoom().removeContent(i);
//
//                        break;
//                    }
//                    else if (input.equals("skip") || input.equals("s"))
//                    {
//                        printToConsole.print(gameText.getSeperator());
//                        break;
//                    }
//                    else
//                    {
//                        //Prints "Hmm... Wrong command"
//                        printToConsole.print(gameText.getWhatDoYouMean());
//                    }
//                }
//                if (i < map.getNumberOfContent() - 1)
//                {
//                    parser.userPressEnter();
//                }
                break;
            case "Guide":
                //Prints info about Guide.
                textAreaMain.setText(gameText.getContentInfo(i));

//                while (true)
//                {
//                    //Prints "There is a Guide, you can either \"talk\" , \"flee\" or \"kill\"!"
//                    printToConsole.print(gameText.getThereIsAGuide());
//
//                    String input = parser.getUserInput();
//                    if (input.equals("talk") || input.equals("t"))
//                    {
//                        //Prints "Hello my name is \"insert name here\" here is a tip ;) ... DON'T DIE!!!"
//                        printToConsole.print(gameText.getGuideTalk());
//                    }
//                    else if (input.equals("skip")|| input.equals("s"))
//                    {
//                        break;
//                    }
//                    else if (input.equals("kill")|| input.equals("k"))
//                    {
//                        //Prints "You killed the helper, oh mighty swordsman!"
//                        printToConsole.print(gameText.getKilledGuide());
//                        map.getCurrentRoom().removeContent(i);
//                        break;
//                    }
//                }
//
//                if (i < map.getNumberOfContent() - 1)
//                {
//                    parser.userPressEnter();
//                }
                break;
            case "":
            }

        }

        if (map.hasAllRoomBeenEntered())
        {
//            parser.userPressEnter();
//            lastBossBattle();
        }
    }


    @Override
    public void injectLogic(ILogicFacade logicLayer)
    {
        logic = logicLayer;

        gameText = logic.getGameText();

        if (true)
        {
            TextInputDialog dialog = new TextInputDialog("");

            dialog.setTitle("Player Name:");
            dialog.setHeaderText(null);
            dialog.setContentText("Name:");


            Optional<String> result = dialog.showAndWait();

            result.ifPresent(name -> player = logic.createPlayerInstance(name));

            // Creats the map instance in logic.facade, and sends the reference back to here.

            map = logic.createMapInstance();

            logic.injectGameText();

            map.setRoomHasBeenEntered(player.getLocation());
        }

        //Starting timetracker.
        timeTracker = logic.getTimeTracker(new Date());

        textAreaMain.setText(gameText.getMessageHello());
        textAreaMap.setText(gameText.getMap());
        textAreaInventory.setText(gameText.getInventory(player.getInventory()));
        //checkRoom();
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
