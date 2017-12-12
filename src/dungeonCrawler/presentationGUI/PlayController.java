package dungeonCrawler.presentationGUI;

import dungeonCrawler.aqu.*;
import dungeonCrawler.logic.GameText;
import dungeonCrawler.logic.LogicFacade;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.*;

public class PlayController implements Initializable
{
    private ILogicFacade logic;
    // Stores what room we are currently in
    private IMap map;
    // We are storing the class player's name for player.
    private IPlayer player;
    //Creating print to console object
    private GameText gameText;
    //Create timeTracker.
    private ITimeTracker timeTracker;

    private IBattle battle;

    @FXML
    public TextArea textAreaMain;
    @FXML
    public TextArea textAreaMap;
    @FXML
    public TextArea textAreaInventory;
    @FXML
    private GridPane buttonGridPane;
    @FXML
    public Button btnUp;
    @FXML
    public Button btnDown;
    @FXML
    public Button btnLeft;
    @FXML
    public Button btnRight;
    @FXML
    private Button btnAttack;
    @FXML
    private Button btnFlee;
    @FXML
    private Button btnTalk;
    @FXML
    private Button btnOpen;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnEnter;
    @FXML
    private Button btnContinue;
    @FXML
    private Button btnSkip;
    @FXML
    private HBox hboxInventory;
    @FXML
    private Label labelTime;
    @FXML
    private Label labelWeapon;
    @FXML
    private Label labelHealth;
    @FXML
    private Label labelName;
    @FXML
    private ImageView ImagePlaceHolder;
    @FXML
    private StackPane ImagePane;

    public static boolean NewGame = true;

    MediaPlayer mediaPlayer;
    AudioClip gatekeepAudio;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        logic = Game.getLogic();

        gameText = logic.getGameText();
        gameText.setType("press");

        if (NewGame)
        {
            // Creats the map instance in logic.facade, and sends the reference back to here.
            player = logic.createPlayerInstance(NewGameController.playerName);

            map = logic.createMapInstance();

            logic.injectGameText();

            map.setRoomHasBeenEntered(player.getLocation());

            textAreaMain.setText(gameText.getMessageHello());

            btnEnter.setText("Enter");

            gatekeepAudio = new AudioClip(new File("Resources\\sounds\\blah-blah-blah.mp3").toURI().toString());
            gatekeepAudio.play();

        }
        else
        {
            player = logic.getPlayer();

            map = logic.getMap();

            logic.injectGameText();

            textAreaMain.setText(gameText.getWelcomeBack());

            btnEnter.setText("Continue");

            NewGame = true;
        }

        Game.mediaPlayer.stop();
        Game.mediaPlayer1.stop();

        Media sound = new Media(new File("Resources\\sounds\\AmbienceCave.mp3").toURI().toString());
        mediaPlayer=new MediaPlayer(sound);
        mediaPlayer.setVolume(0.2);
        mediaPlayer.play();

        //Starting timetracker.
        timeTracker = logic.getTimeTracker(new Date());

        textAreaMap.setText(gameText.getMap());

        labelName.setText("Name: " + player.getName());
        labelHealth.setText("Health: " + String.valueOf(player.getHealth()));
        labelWeapon.setText("Weapon: " + ((IItem) player.getWeapon()).getName());
        labelTime.setText("Time: " + String.valueOf(player.getTime()));

        //checkRoom();
    }

    public void startGame()
    {
        for (int i = 0; i < player.getInventory().getSize(); i++)
        {
            TextArea textArea = new TextArea(gameText.getInventorySlot(i, player.getInventory()));
            textArea.setWrapText(true);

            Button btnAdd = new Button(String.valueOf(i + 1));
            btnAdd.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            btnAdd.setStyle("-fx-font: 14px Monospaced;");
            int finalI = i;
            btnAdd.setOnAction((ActionEvent event) -> addItem(finalI));

            Button btnUse = new Button("Use");
            btnUse.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            btnUse.setStyle("-fx-font: 14px Monospaced;");
            btnUse.setOnAction((ActionEvent event) -> useItem(finalI));

            Button btnShow = new Button("Show");
            btnShow.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            btnShow.setStyle("-fx-font: 14px Monospaced;");
            btnShow.setOnAction((ActionEvent event) -> showItem(finalI));

            VBox vBox = new VBox();
            vBox.setId(String.valueOf(i));
            vBox.getChildren().addAll(btnAdd, textArea, btnUse, btnShow);
            hboxInventory.getChildren().add(vBox);
            textArea.setEditable(false);
        }

        for (javafx.scene.control.Menu menu : Game.menuBar.getMenus())
        {
            for (javafx.scene.control.MenuItem item : menu.getItems())
            {
                item.setDisable(false);
            }
        }

        if (gatekeepAudio != null) gatekeepAudio.stop();

        btnEnter.setVisible(false);

        showImage("Resources\\images\\Gate2.gif", 2.5);

        playAudio("Resources\\sounds\\GateOpen.mp3",1, 1);


        checkRoom();
    }

    private void gameOver()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("GAME OVER");
        alert.setHeaderText(null);

        player.setScore(timeTracker.calculateRemainingTime() * logic.getDifficultyLevel() + (player.getHealth() / 2));

        if (player.getHealth() <= 0) alert.setContentText(gameText.getYouHaveDied() + "\nAnd your score was " + player.getScore());
        else if (timeTracker.calculateRemainingTime() <= 0) alert.setContentText(gameText.getTimeRanOut() + "\nAnd your score was " + player.getScore());
        else if (lastBattle == true) alert.setContentText(gameText.getIsLuciferDead() + "\nAnd your score was " + player.getScore());

        alert.showAndWait();

        Game.switchScene("MainMenu.fxml");
    }

    @FXML
    public void handleMove(ActionEvent event)
    {
        switch (((Control) event.getSource()).getId())
        {
            case ("btnUp"):
                if (map.roomExists(new Point(player.getLocation().x, player.getLocation().y + 1)))
                {
                    playerMove(new Point(player.getLocation().x, player.getLocation().y + 1));
                } else
                {
                    textAreaMain.setText(gameText.getYouRanIntoAWall());
                }
                break;
            case "btnDown":
                if (map.roomExists(new Point(player.getLocation().x, player.getLocation().y - 1)))
                {
                    playerMove(new Point(player.getLocation().x, player.getLocation().y - 1));
                } else
                {
                    textAreaMain.setText(gameText.getYouRanIntoAWall());
                }
                break;
            case "btnLeft":
                if (map.roomExists(new Point(player.getLocation().x - 1, player.getLocation().y)))
                {
                    playerMove(new Point(player.getLocation().x - 1, player.getLocation().y));
                } else
                {
                    textAreaMain.setText(gameText.getYouRanIntoAWall());
                }
                break;
            case "btnRight":
                if (map.roomExists(new Point(player.getLocation().x + 1, player.getLocation().y)))
                {
                    playerMove(new Point(player.getLocation().x + 1, player.getLocation().y));
                } else
                {
                    textAreaMain.setText(gameText.getYouRanIntoAWall());
                }
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
            } else
            {
                //Prints "You entered new room."
                textAreaMain.setText(gameText.getYouEnteredANewRoom(logic.getCurrentRoom()));
                map.setRoomHasBeenEntered(player.getLocation());
            }

            map.merchantMove();
            map.thiefMove();

            showImage("Resources\\images\\G4.gif", 2.5);

            playAudio("Resources\\sounds\\Door.wav",1,0.2);

            textAreaMap.setText(gameText.getMap());
            checkRoom();


        } else if (map.isRoomLocked(location) && logic.getNumberOfAvailableKeys() == 0)
        {
            textAreaMain.setText(gameText.getRoomIsLockedNoKey());
//            checkRoom();
        } else
        {
            textAreaMain.setText(gameText.getRoomIsLockedHaveKey());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(gameText.getTypeSlotNumberToUse());
            alert.setHeaderText(null);


            String availableKeys = "";
            for (int j = 0; j < logic.getNumberOfAvailableKeys(); j++)
            {
                availableKeys += gameText.getKey(j) + "\n";

                ButtonType buttonType = new ButtonType("" + (j + 1));
                alert.getButtonTypes().add(buttonType);
            }
            alert.setContentText(availableKeys);
            alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(false);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get().getText().matches("[0-9]+"))
            {
                logic.useKey(Integer.parseInt(result.get().getText()) - 1);
                logic.getCurrentRoom().setLocked(false);
                map.unlockRoom(location);
                player.setLocation(location);

                textAreaMain.setText(gameText.getRoomHasBeenUnlocked());

                textAreaMain.setText(gameText.getYouEnteredANewRoom(logic.getCurrentRoom()));

                map.setRoomHasBeenEntered(player.getLocation());
                map.merchantMove();
                map.thiefMove();

                textAreaMap.setText(gameText.getMap());

                updateInventory();

                showImage("Resources\\images\\Key4.gif", 2);

                checkRoom();
            } else
            {
                textAreaMain.setText("You canceled??? Why???");
            }
        }
    }

    private int ContentIndex;
    private int MerchantIndex;

    public void checkRoom()
    {
        labelTime.setText("Time: " + timeTracker.calculateRemainingTime());

        if (timeTracker.calculateRemainingTime() <= 0)
        {
            gameOver();
            return;
        }
        if (map.roomContainsMerchant())
        {
            itemFrom = "merchant";
            textAreaMain.setText(gameText.getMerchant());
            //textAreaMain.appendText("\n" + gameText.getInventory(map.getMerchant().getInventory()));

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Select which item you want?");
            alert.setHeaderText(null);


            String availableItems = "";
            for (int j = 0; j < map.getMerchant().getInventory().getSize(); j++)
            {
                availableItems += (j + 1) + ". " + map.getMerchant().getInventory().getItem(j).getName() + "\n";

                ButtonType buttonType = new ButtonType("" + (j + 1));
                alert.getButtonTypes().add(buttonType);
            }
            alert.setContentText(availableItems);
            alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(false);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get().getText().matches("[0-9]+"))
            {
                textAreaMain.appendText("\n\n" + "Please select a slot to exchange with the Merchant");
                MerchantIndex = Integer.parseInt(result.get().getText()) - 1;
                disableButtons(new Button[]{btnSkip}, false, true);
                return;
            } else
            {
                map.getMerchant().setRandomLocation(new Point(map.getWidth(), map.getHeight()));
                textAreaMap.setText(gameText.getMap());
            }

            disableButtons();
            btnContinue.setVisible(true);
            return;
        }

        if (map.roomContainsThief())
        {
            textAreaMain.setText(gameText.getThief());
            map.removeThief();
            textAreaMap.setText(gameText.getMap());

            disableButtons();
            btnContinue.setVisible(true);
            return;
        }



        for (; ContentIndex < map.getNumberOfContent(); ContentIndex++)
        {
            switch (map.checkRoomContent(ContentIndex))
            {
                case "Monster":
                    //Prints info about monster.
                    textAreaMain.setText(gameText.getContentInfo(ContentIndex));
                    //Prints "Your health is currently " + player.getHealth() + "hp"
                    textAreaMain.appendText("\n\n" + gameText.getPlayerInfo());
                    //Prints "Type \"battle\" or \"flee\"." // We need to type more information!
                    textAreaMain.appendText("\n\n" + gameText.getBattleOrFlee());

                    disableButtons(new Button[]{btnAttack, btnFlee}, false, false);

                    return;

                case "Chest":

                    itemFrom = "chest";
                    //Prints info about chest.
                    textAreaMain.setText(gameText.getContentInfo(ContentIndex));
                    textAreaMain.appendText("\n\n" + gameText.getThereIsAChest());

                    disableButtons(new Button[]{btnOpen, btnSkip}, false, false);

                    return;
                case "Guide":
                    //Prints info about Guide.
                    textAreaMain.setText(gameText.getContentInfo(ContentIndex));
                    textAreaMain.appendText("\n\n" + gameText.getThereIsAGuide());

                    guideTalkCount = 0;

                    disableButtons(new Button[]{btnAttack, btnFlee, btnSkip, btnTalk}, false, false);

                    return;
                case "":
            }

        }

        ContentIndex = 0;
        textAreaMain.setText(gameText.getRoomIsEmpty());
        disableButtons(new Button[]{btnUp, btnDown, btnLeft, btnRight}, true, false);

        if (map.hasAllRoomBeenEntered())
        {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Boss Battle");
            alert.setHeaderText(null);
            alert.setContentText(gameText.getAllRoomsEntered());
            alert.showAndWait();




            showImage("Resources\\images\\DevilTrans.gif", 6);

            playAudio("Resources\\sounds\\DevilTheme.mp3",0.7, 0.7);

            playAudio("Resources\\sounds\\DevilGrowl.mp3",3, 0.7);

            playAudio("Resources\\sounds\\Monster_04.mp3",5, 0.7);

            playAudio("Resources\\sounds\\AmbienceHell.mp3",0, 0.7);

            mediaPlayer.stop();

            //Prints "Your health is currently " + player.getHealth() + "hp"
            textAreaMain.appendText("\n" + gameText.getMonstersInfo(logic.getLucifer()));
            //Prints "Type \"battle\" or \"flee\"." // We need to type more information!
            textAreaMain.appendText("\n" + gameText.getFinaleBattle());

            lastBattle = true;

            disableButtons(new Button[]{btnAttack}, false, false);

            battle = logic.doBattle(logic.getLucifer());

        }

    }


    public void updateInventory()
    {
        for (int i = 0; i < player.getInventory().getSize(); i++)
        {
            for (Node nodeIn : hboxInventory.getChildren())
            {
                if (nodeIn instanceof VBox)
                {
                    VBox vBox = (VBox) nodeIn;
                    for (Node nodeIn2 : vBox.getChildren())
                    {
                        if (nodeIn2 instanceof TextArea && vBox.getId().contains(String.valueOf(i)))
                        {
                            ((TextArea) nodeIn2).setText(gameText.getInventorySlot(i, player.getInventory()));
                        }
                    }
                }
            }
        }
    }


    private String itemFrom;
    private void addItem(int index)
    {
        if (itemFrom.equals("chest"))
        {
            player.getInventory().addItem(((IChest)map.getCurrentRoom().getContent(ContentIndex)).getItem(), index);
            updateInventory();
            logic.getCurrentRoom().removeContent(ContentIndex);
            disableButtons();
            btnContinue.setVisible(true);
        }
        else if (itemFrom.equals("merchant"))
        {
            if (player.getInventory().getItem(index) == null)
            {
                textAreaMain.appendText("\n\n" + "Merchant is not happy :( ... You need to trade an item.");
            }
            else
            {
                player.getInventory().addItem(map.getMerchant().getInventory().getItem(MerchantIndex),index);

                updateInventory();
                disableButtons();
                btnContinue.setVisible(true);
                map.getMerchant().setRandomLocation(new Point(map.getWidth(), map.getHeight()));
                textAreaMain.appendText("\n\n" + "Merchant is happy :) ... Thanks you for the trade.");
                textAreaMap.setText(gameText.getMap());
            }
        }
    }

    public void useItem(int index)
    {
        IItem item = player.getInventory().getItem(index);

        if (item instanceof IWeapon)
        {
            player.setWeapon((IWeapon) item);
            textAreaMain.setText(gameText.getSetCurrentWeapon());
            labelWeapon.setText("Weapon: " + ((IItem)player.getWeapon()).getName());

            showImage("Resources\\images\\Sword.gif", 2);
        }
        else if (item instanceof IPotion)
        {
            player.setHealth(player.getHealth() + ((IPotion) item).getHealthRecovery());
            player.setTime(player.getTime() + ((IPotion) item).getTimeRecovery());
            player.getInventory().removeItem(player.getInventory().getItemIndex(item));
            //Prints "Yom yom ... Your health is now: " + player.getHealth() + "hp"
            textAreaMain.setText(gameText.getPlayerHealth());
            // Prints "Your time is now: " + player.getTime() + "sec"
            textAreaMain.appendText("\n" + gameText.getPlayerTime(timeTracker));
            labelHealth.setText("Health: " + String.valueOf(player.getHealth()));
            labelTime.setText("Time: " + String.valueOf(timeTracker.calculateRemainingTime()));


            showImage("Resources\\images\\Heart.gif", 2);

        }
        else if (item instanceof IKey)
        {
            textAreaMain.setText(gameText.getUseKey());
        }
        else if (item instanceof ISpecial)
        {
            if (((ISpecial) item).getTypeString().equals("teleport"))
            {
//                printToConsole.print(gameText.getMap());
//                while (true)
//                {
//                    printToConsole.print(gameText.getTypeXCoordinate());
//                    String inputX;
//                    while (true)
//                    {
//                        inputX = parser.getUserInput();
//                        if (inputX.matches("\\d+"))
//                        {
//                            if (Integer.parseInt(inputX) >= 0 && Integer.parseInt(inputX) <= map.getWidth()-1)
//                                break;
//                            else printToConsole.print(gameText.getWrongInputCoordinate());
//                        }
//                        else
//                        {
//                            printToConsole.print(gameText.getWrongInputCoordinate());
//                        }
//                    }
//
//                    printToConsole.print(gameText.getTypeYCoordinate());
//                    String inputY;
//                    while (true)
//                    {
//                        inputY = parser.getUserInput();
//                        if (inputY.matches("\\d+"))
//                        {
//                            if (Integer.parseInt(inputY) >= 0 && Integer.parseInt(inputY) <= map.getHeight()-1)
//                                break;
//                            else printToConsole.print(gameText.getWrongInputCoordinate());
//                        }
//                        else
//                        {
//                            printToConsole.print(gameText.getWrongInputCoordinate());
//                        }
//                    }
//
//                    Point point = new Point(Integer.parseInt(inputX), Integer.parseInt(inputY));
//
//                    if (map.isRoomLocked(point) && player.getInventory().keyArrayList().size() == 0)
//                    {
//                        player.getInventory().removeItem(player.getInventory().getItemIndex(item));
//                        printToConsole.print(gameText.getTeleportToLockedRoomNoKey());
//                    }
//                    else
//                    {
//                        player.getInventory().removeItem(player.getInventory().getItemIndex(item));
//                        //((ISpecial) item).use(player,map,point);
//                        playerMove(point);
//                    }
//
//                    break;
//                }
            }
            else if (((ISpecial) item).getTypeString().equals("bomb"))
            {
                textAreaMain.setText(gameText.getUseSpecialBomb());
                ((ISpecial) item).use(player,map);
                player.getInventory().removeItem(player.getInventory().getItemIndex(item));
            }
            else if (((ISpecial) item).getTypeString().equals("awesome_name"))
            {
                ((ISpecial) item).use(player);
                textAreaMain.setText(gameText.getPlayerName());
                player.getInventory().removeItem(player.getInventory().getItemIndex(item));
            }
            else if (((ISpecial) item).getTypeString().equals("vision"))
            {
                //((ISpecial) item).use(player);
                textAreaMain.setText(gameText.getVisionMap());
                player.getInventory().removeItem(player.getInventory().getItemIndex(item));
            }
            else if (((ISpecial) item).getTypeString().equals("extra_slot"))
            {
                ((ISpecial) item).use(player);
                textAreaMain.setText(gameText.getUseSpecialExtraSlot());

                int i = player.getInventory().getSize()-1;
                TextArea textArea = new TextArea(gameText.getInventorySlot(i, player.getInventory()));
                textArea.setWrapText(true);

                Button btnAdd = new Button(String.valueOf(i + 1));
                btnAdd.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                btnAdd.setStyle("-fx-font: 14px Monospaced;");
                int finalI = i;
                btnAdd.setOnAction((ActionEvent event) -> addItem(finalI));

                Button btnUse = new Button("Use");
                btnUse.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                btnUse.setStyle("-fx-font: 14px Monospaced;");
                btnUse.setOnAction((ActionEvent event) -> useItem(finalI));

                Button btnShow = new Button("Show");
                btnShow.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                btnShow.setStyle("-fx-font: 14px Monospaced;");
                btnShow.setOnAction((ActionEvent event) -> showItem(finalI));

                VBox vBox = new VBox();
                vBox.setId(String.valueOf(i));
                vBox.getChildren().addAll(btnAdd, textArea, btnUse, btnShow);
                hboxInventory.getChildren().add(vBox);

                updateInventory();
                player.getInventory().removeItem(player.getInventory().getItemIndex(item));
                disableButtons(new Button[]{btnUp, btnDown, btnLeft, btnRight}, true, false);
            }
        }
        else
        {
            //Prints "Slot is empty."
            textAreaMain.setText(gameText.getSlotIsEmpty());
        }

        updateInventory();
    }

    Timeline timeline;

    private void showImage(String image, double time)
    {
        ImagePane.setOpacity(1);
        ImagePane.setVisible(true);
        ImagePlaceHolder.setImage(new Image(new File(image).toURI().toString()));
        timeline = new Timeline();
        timeline.setCycleCount(2);
        timeline.setAutoReverse(true);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(time), new KeyValue(ImagePlaceHolder.opacityProperty(), 1.0));
        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(arg0 ->
                               {
                                   ImagePane.setVisible(false);
                                   ImagePlaceHolder.setOpacity(0);
                               });
        timeline.play();
    }

    private void playAudio(String audio, double delay, double volume)
    {
        AudioClip gateAudio = new AudioClip(new File(audio).toURI().toString());
        gateAudio.setVolume(volume);

        timeline = new Timeline();
        timeline.setCycleCount(2);
        timeline.setAutoReverse(true);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(delay));
//        KeyFrame keyFrame = new KeyFrame(Duration.seconds(delay), new KeyValue(ImagePlaceHolder.opacityProperty(), 1.0));
        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(arg0 ->
                               {
                                   gateAudio.play();
                               });
        timeline.play();
    }

    public void showItem(int index)
    {
        textAreaMain.setText(gameText.getItemInfo(player.getInventory().getItem(index)));
    }

    boolean lastBattle = false;

    AudioClip attackSound;
    public void handleAttack(ActionEvent event)
    {
        ArrayList<String> sounds = new ArrayList<>();
        sounds.add("Resources\\sounds\\Hit1.wav");
        sounds.add("Resources\\sounds\\Hit2.wav");
        sounds.add("Resources\\sounds\\Hit3.wav");
        sounds.add("Resources\\sounds\\Hit4.wav");
        sounds.add("Resources\\sounds\\Hit32.mp3");
        sounds.add("Resources\\sounds\\Hit33.mp3");
        sounds.add("Resources\\sounds\\Hit34.mp3");
        sounds.add("Resources\\sounds\\Hit35.mp3");
        sounds.add("Resources\\sounds\\Hit36.mp3");

        if (lastBattle)
        {
            textAreaMain.appendText("\n" + gameText.getBattle(battle));

            int random = new Random().nextInt(sounds.size());
            attackSound = new AudioClip(new File(sounds.get(random)).toURI().toString());
            attackSound.play();


            if (battle.getIsBattleOver())
            {
                disableButtons();
                btnContinue.setVisible(true);
                //if (logic.getLucifer().getHealth() <= 0)
            }
        }
        else
        {
            switch (map.checkRoomContent(ContentIndex))
            {
            case "Monster":
                if (battle == null)
                {
                    battle = logic.doBattle(ContentIndex);
                }

                if (attackSound != null) attackSound.stop();


                textAreaMain.appendText("\n" + gameText.getBattle(battle));
                labelHealth.setText("Health: " + String.valueOf(player.getHealth()));
                labelTime.setText("Time: " + timeTracker.calculateRemainingTime());




                if (battle.getIsBattleOver())
                {
                    int random = new Random().nextInt(sounds.size());
                    attackSound = new AudioClip(new File("Resources\\sounds\\TheWilhelmScream.mp3").toURI().toString());
                    attackSound.setVolume(1);
                    attackSound.play();
                    battle = null;
                    logic.getCurrentRoom().removeContent(ContentIndex);
                    disableButtons();
                    btnContinue.setVisible(true);
                }
                else
                {
                    int random = new Random().nextInt(sounds.size());
                    attackSound = new AudioClip(new File(sounds.get(random)).toURI().toString());
                    attackSound.play();
                }
                break;
            case "Guide":
                if (guideAudio != null) guideAudio.stop();

                int random = new Random().nextInt(sounds.size());
                attackSound = new AudioClip(new File(sounds.get(random)).toURI().toString());
                attackSound.play();
                textAreaMain.appendText("\n" + gameText.getKilledGuide());
                map.getCurrentRoom().removeContent(ContentIndex);
                disableButtons();
                btnContinue.setVisible(true);

                break;
            }
        }


    }

    public void handleFlee(ActionEvent event)
    {
        player.setLocation(player.getLastLocation());

        textAreaMap.setText(gameText.getMap());

        if (guideAudio != null) guideAudio.stop();

        checkRoom();
    }


    @FXML
    private void handleOpen(ActionEvent actionEvent)
    {
        textAreaMain.setText(gameText.getItemInfo(((IChest) map.getCurrentRoom().getContent(ContentIndex)).getItem()));
        textAreaMain.appendText("\n" + gameText.getWhatSlot());

        disableButtons(new Button[]{btnSkip}, false, true);

        showImage("Resources\\images\\ChestTrans3.gif", 2.5);

        playAudio("Resources\\sounds\\ChestOpen.mp3",0.7, 0.7);
    }

    @FXML
    private void handleContinue(ActionEvent actionEvent)
    {
        if (lastBattle == true || player.getHealth() <= 0)
        {
            if (player.getHealth() <= 0)
            {
                gameOver();
                return;
            }
            else textAreaMain.setText(gameText.getIsLuciferDead());
            gameOver();
            return;
        }
        else


        btnContinue.setVisible(false);

        checkRoom();

    }

    int guideTalkCount = 0;

    AudioClip guideAudio;
    @FXML
    private void handleTalk(ActionEvent actionEvent)
    {
        if (guideTalkCount < 10)
        {
            if (guideAudio != null) guideAudio.stop();

            guideAudio = new AudioClip(new File("Resources\\sounds\\wa-wa-effect.mp3").toURI().toString());
            guideAudio.play();

            textAreaMain.appendText("\n\n" + gameText.getGuideTalk());
            guideTalkCount++;
        }
        else
        {
            textAreaMain.appendText("\n\n" + gameText.getGuideLimit());
        }

    }

    @FXML
    private void handleSkip(ActionEvent actionEvent)
    {
        if (map.roomContainsMerchant())
        {
            map.getMerchant().setRandomLocation(new Point(map.getWidth(), map.getHeight()));
            textAreaMap.setText(gameText.getMap());
        }
        else
        {
            ContentIndex++;
        }

        if (guideAudio != null) guideAudio.stop();
        checkRoom();

    }

    public void disableButtons(Button[] buttons, boolean showInventory, boolean showInventoryNumbers)
    {
        for (Node nodeIn : buttonGridPane.getChildren())
        {
            if (nodeIn instanceof Button)
            {
                nodeIn.setDisable(true);
            }
        }

        for (Node nodeIn : hboxInventory.getChildren())
        {
            if (nodeIn instanceof VBox)
            {
                VBox vBox = (VBox) nodeIn;
                for (Node nodeIn2 : vBox.getChildren())
                {
                    if (nodeIn2 instanceof Button)
                    {
                        if (showInventory)
                        {
                            nodeIn2.setDisable(false);
                        }
                        else
                        {
                            nodeIn2.setDisable(true);
                        }

                        if (showInventoryNumbers && ((Button) nodeIn2).getText().matches("[0-9]+"))
                        {
                            nodeIn2.setDisable(false);
                        }
                        else if (!showInventoryNumbers && ((Button) nodeIn2).getText().matches("[0-9]+"))
                        {
                            nodeIn2.setDisable(true);
                        }
                    }
                }
            }
        }

        for (Button button : buttons)
        {
            button.setDisable(false);
        }
    }

    public void disableButtons()
    {
        for (Node nodeIn : buttonGridPane.getChildren())
        {
            if (nodeIn instanceof Button)
            {
                nodeIn.setDisable(true);
            }
        }

        for (Node nodeIn : hboxInventory.getChildren())
        {
            if (nodeIn instanceof VBox)
            {
                VBox vBox = (VBox) nodeIn;
                for (Node nodeIn2 : vBox.getChildren())
                {
                    if (nodeIn2 instanceof Button)
                    {
                        nodeIn2.setDisable(true);
                    }
                }
            }
        }
    }

    public void enableAllButtons()
    {
        for (Node nodeIn : buttonGridPane.getChildren())
        {
            if (nodeIn instanceof Button)
            {
                nodeIn.setDisable(false);
            }
        }
    }

    @FXML
    private void handleImageClick(MouseEvent mouseEvent)
    {
        timeline.stop();
        ImagePane.setVisible(false);
        ImagePlaceHolder.setOpacity(0);
    }
}
