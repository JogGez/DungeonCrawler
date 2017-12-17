package dungeonCrawler.presentationGUI;

import dungeonCrawler.aqu.*;
import dungeonCrawler.logic.GameText;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.util.Pair;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The type Play controller.
 * Used to get handle the game itself and what the user is pressing.
 * implements Initializable
 * extends HighscoreController
 */
public class PlayController extends HighscoreController implements Initializable
{
    /**
     * The Logic.
     */
    private ILogicFacade logic;
    /**
     * The Map.
     */
// Stores what room we are currently in
    private IMap map;
    /**
     * The Player.
     */
// We are storing the class player's name for player.
    private IPlayer player;
    /**
     * The Game text.
     */
//Creating print to console object
    private GameText gameText;
    /**
     * The Time tracker.
     */
//Create timeTracker.
    private ITimeTracker timeTracker;

    /**
     * The Battle.
     */
    private IBattle battle;

    /**
     * The Text area main.
     */
    @FXML
    public TextArea textAreaMain;
    /**
     * The Text area map.
     */
    @FXML
    public TextArea textAreaMap;
    /**
     * The Text area inventory.
     */
    @FXML
    public TextArea textAreaInventory;
    /**
     * The Button grid pane.
     */
    @FXML
    private GridPane buttonGridPane;
    /**
     * The Btn up.
     */
    @FXML
    public Button btnUp;
    /**
     * The Btn down.
     */
    @FXML
    public Button btnDown;
    /**
     * The Btn left.
     */
    @FXML
    public Button btnLeft;
    /**
     * The Btn right.
     */
    @FXML
    public Button btnRight;
    /**
     * The Btn attack.
     */
    @FXML
    private Button btnAttack;
    /**
     * The Btn flee.
     */
    @FXML
    private Button btnFlee;
    /**
     * The Btn talk.
     */
    @FXML
    private Button btnTalk;
    /**
     * The Btn open.
     */
    @FXML
    private Button btnOpen;
    /**
     * The Btn enter.
     */
    @FXML
    private Button btnEnter;
    /**
     * The Btn continue.
     */
    @FXML
    private Button btnContinue;
    /**
     * The Btn skip.
     */
    @FXML
    private Button btnSkip;
    /**
     * The Hbox inventory.
     */
    @FXML
    private HBox hboxInventory;
    /**
     * The Label time.
     */
    @FXML
    private Label labelTime;
    /**
     * The Label weapon.
     */
    @FXML
    private Label labelWeapon;
    /**
     * The Label health.
     */
    @FXML
    private Label labelHealth;
    /**
     * The Label name.
     */
    @FXML
    private Label labelName;
    /**
     * The Image place holder.
     */
    @FXML
    private ImageView ImagePlaceHolder;
    /**
     * The Image pane.
     */
    @FXML
    private StackPane ImagePane;

    /**
     * The constant NewGame.
     */
    public static boolean NewGame = true;

    /**
     * The constant mediaPlayer.
     */
    private static MediaPlayer mediaPlayer;

    /**
     * The initialize method used for when the controller is first initialized
     * @param location set the URL location.
     * @param resources set the ResourceBundle.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        logic = Game.getLogic();

//        textAreaMain.setOnMouseClicked((ActionEvent event) -> {
//            Game.getRoot().requestFocus();
//        });

        textAreaMain.setOnKeyReleased(key -> handleKeyPress(key));
        textAreaMap.setOnKeyReleased(key -> handleKeyPress(key));
        Game.getScene().addEventHandler(KeyEvent.KEY_RELEASED, (key) -> handleKeyPress(key));

        this.gameText = logic.getGameText();
        gameText.setType("press");

        if (NewGame)
        {
            // Creats the map instance in logic.facade, and sends the reference back to here.
            player = logic.createPlayerInstance(NewGameController.playerName);

            map = logic.createMapInstance();

            logic.injectGameText();

            map.setRoomHasBeenEntered(player.getLocation());

            textAreaMain.setText(gameText.getMessageHello());

            btnEnter.setVisible(true);
            btnEnter.setText("Enter (e)");

            playAudio("Resources\\sounds\\blah-blah-blah.mp3", 0, 1, true);

        }
        else
        {
            player = logic.getPlayer();

            map = logic.getMap();

            logic.injectGameText();

            textAreaMain.setText(gameText.getWelcomeBack());

            btnEnter.setVisible(true);
            btnEnter.setText("Continue (e)");

            NewGame = true;
        }

        Game.mediaPlayer.stop();
        Game.mediaPlayer1.stop();

        Media sound = new Media(new File("Resources\\sounds\\AmbienceCave.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.2);
        mediaPlayer.play();

        //Starting timetracker.
        timeTracker = logic.getTimeTracker(new Date());

        textAreaMap.setText(gameText.getMap());

        labelName.setText("Name: " + player.getName());
        labelHealth.setText("Health: " + String.valueOf(player.getHealth()));
        labelWeapon.setText("Weapon: " + ((IItem) player.getWeapon()).getName());
        labelTime.setText("Time is under: " + String.valueOf(player.getTime()) + " seconds");

        //checkRoom();
    }


    private final KeyCombination keyShiftOne = new KeyCodeCombination(KeyCode.DIGIT1, KeyCombination.SHIFT_DOWN);
    private final KeyCombination keyShiftTwo = new KeyCodeCombination(KeyCode.DIGIT2, KeyCombination.SHIFT_DOWN);
    private final KeyCombination keyShiftTree = new KeyCodeCombination(KeyCode.DIGIT3, KeyCombination.SHIFT_DOWN);
    private final KeyCombination keyShiftFour = new KeyCodeCombination(KeyCode.DIGIT4, KeyCombination.SHIFT_DOWN);
    private final KeyCombination keyShiftFive = new KeyCodeCombination(KeyCode.DIGIT5, KeyCombination.SHIFT_DOWN);
    private final KeyCombination keyShiftSix = new KeyCodeCombination(KeyCode.DIGIT6, KeyCombination.SHIFT_DOWN);
    private final KeyCombination keyShiftSeven = new KeyCodeCombination(KeyCode.DIGIT7, KeyCombination.SHIFT_DOWN);
    private final KeyCombination keyShiftEight = new KeyCodeCombination(KeyCode.DIGIT8, KeyCombination.SHIFT_DOWN);
    private final KeyCombination keyShiftNine = new KeyCodeCombination(KeyCode.DIGIT9, KeyCombination.SHIFT_DOWN);


    private void handleKeyPress(KeyEvent key)
    {
        if (key.getCode() == KeyCode.C && btnContinue.isVisible())
        {
            btnContinue.fire();
        }
        else if (key.getCode() == KeyCode.E && btnEnter.isVisible())
        {
            btnEnter.fire();
        }

        if (!btnEnter.isVisible() && !btnContinue.isVisible())
        {


            if (key.getCode() == KeyCode.SPACE)
            {
                handleImageClick();
            }
            else if (key.getCode() == KeyCode.A)
            {
                btnAttack.fire();
            }
            else if (key.getCode() == KeyCode.O)
            {
                btnOpen.fire();
            }
            else if (key.getCode() == KeyCode.F)
            {
                btnFlee.fire();
            }
            else if (key.getCode() == KeyCode.T)
            {
                btnTalk.fire();
            }
            else if (key.getCode() == KeyCode.S)
            {
                btnSkip.fire();
            }
            else if (key.getCode() == KeyCode.UP)
            {
                btnUp.fire();
            }
            else if (key.getCode() == KeyCode.DOWN)
            {
                btnDown.fire();
            }
            else if (key.getCode() == KeyCode.LEFT)
            {
                btnLeft.fire();
            }
            else if (key.getCode() == KeyCode.RIGHT)
            {
                btnRight.fire();
            }

            else if (key.getCode() == KeyCode.DIGIT1 && player.getInventory().getSize() >= 1 && itemAddAvailable)
            {
                addItem(0);
            }
            else if (key.getCode() == KeyCode.DIGIT2 && player.getInventory().getSize() >= 2 && itemAddAvailable)
            {
                addItem(1);
            }
            else if (key.getCode() == KeyCode.DIGIT3 && player.getInventory().getSize() >= 3 && itemAddAvailable)
            {
                addItem(2);
            }
            else if (key.getCode() == KeyCode.DIGIT4 && player.getInventory().getSize() >= 4 && itemAddAvailable)
            {
                addItem(3);
            }
            else if (key.getCode() == KeyCode.DIGIT5 && player.getInventory().getSize() >= 5 && itemAddAvailable)
            {
                addItem(4);
            }
            else if (key.getCode() == KeyCode.DIGIT6 && player.getInventory().getSize() >= 6 && itemAddAvailable)
            {
                addItem(5);
            }
            else if (key.getCode() == KeyCode.DIGIT7 && player.getInventory().getSize() >= 7 && itemAddAvailable)
            {
                addItem(6);
            }
            else if (key.getCode() == KeyCode.DIGIT8 && player.getInventory().getSize() >= 8 && itemAddAvailable)
            {
                addItem(7);
            }
            else if (key.getCode() == KeyCode.DIGIT9 && player.getInventory().getSize() >= 9 && itemAddAvailable)
            {
                addItem(8);
            }


            else if (keyShiftOne.match(key) && player.getInventory().getSize() >= 1 && itemUseAvailable)
            {
                showItem(0);
            }
            else if (keyShiftTwo.match(key) && player.getInventory().getSize() >= 2 && itemUseAvailable)
            {
                showItem(1);
            }
            else if (keyShiftTree.match(key) && player.getInventory().getSize() >= 3 && itemUseAvailable)
            {
                showItem(2);
            }
            else if (keyShiftFour.match(key) && player.getInventory().getSize() >= 4 && itemUseAvailable)
            {
                showItem(3);
            }
            else if (keyShiftFive.match(key) && player.getInventory().getSize() >= 5 && itemUseAvailable)
            {
                showItem(4);
            }
            else if (keyShiftSix.match(key) && player.getInventory().getSize() >= 6 && itemUseAvailable)
            {
                showItem(5);
            }
            else if (keyShiftSeven.match(key) && player.getInventory().getSize() >= 7 && itemUseAvailable)
            {
                showItem(6);
            }
            else if (keyShiftEight.match(key) && player.getInventory().getSize() >= 8 && itemUseAvailable)
            {
                showItem(7);
            }
            else if (keyShiftNine.match(key) && player.getInventory().getSize() >= 9 && itemUseAvailable)
            {
                showItem(8);
            }



            else if (key.getCode() == KeyCode.DIGIT1 && player.getInventory().getSize() >= 1 && itemUseAvailable)
            {
                useItem(0);
            }
            else if (key.getCode() == KeyCode.DIGIT2 && player.getInventory().getSize() >= 2 && itemUseAvailable)
            {
                useItem(1);
            }
            else if (key.getCode() == KeyCode.DIGIT3 && player.getInventory().getSize() >= 3 && itemUseAvailable)
            {
                useItem(2);
            }
            else if (key.getCode() == KeyCode.DIGIT4 && player.getInventory().getSize() >= 4 && itemUseAvailable)
            {
                useItem(3);
            }
            else if (key.getCode() == KeyCode.DIGIT5 && player.getInventory().getSize() >= 5 && itemUseAvailable)
            {
                useItem(4);
            }
            else if (key.getCode() == KeyCode.DIGIT6 && player.getInventory().getSize() >= 6 && itemUseAvailable)
            {
                useItem(5);
            }
            else if (key.getCode() == KeyCode.DIGIT7 && player.getInventory().getSize() >= 7 && itemUseAvailable)
            {
                useItem(6);
            }
            else if (key.getCode() == KeyCode.DIGIT8 && player.getInventory().getSize() >= 8 && itemUseAvailable)
            {
                useItem(7);
            }
            else if (key.getCode() == KeyCode.DIGIT9 && player.getInventory().getSize() >= 9 && itemUseAvailable)
            {
                useItem(8);
            }
            else if (key.getCode() == KeyCode.DIGIT9 && player.getInventory().getSize() >= 9 && itemUseAvailable)
            {
                useItem(8);
            }


        }


    }

    /**
     * Start game method used when the play screen is first loaded.
     */
    public void startGame()
    {
        for (int i = 0; i < player.getInventory().getSize(); i++)
        {
            TextArea textArea = new TextArea(gameText.getInventorySlot(i, player.getInventory()));
            textArea.setFocusTraversable(false);
            textArea.setWrapText(true);

            Button btnAdd = new Button(String.valueOf(i + 1));
            btnAdd.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            btnAdd.setStyle("-fx-font: 14px Monospaced;");
            btnAdd.setFocusTraversable(false);
            int finalI = i;
            btnAdd.setOnAction((ActionEvent event) -> addItem(finalI));

            Button btnUse = new Button("Use");
            btnUse.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            btnUse.setStyle("-fx-font: 14px Monospaced;");
            btnUse.setFocusTraversable(false);
            btnUse.setOnAction((ActionEvent event) -> useItem(finalI));

            Button btnShow = new Button("Show");
            btnShow.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            btnShow.setStyle("-fx-font: 14px Monospaced;");
            btnShow.setFocusTraversable(false);
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

        btnEnter.setVisible(false);

        if (audioS != null) audioS.stop();

        playAnimation("Resources\\images\\Gate.gif", 2.5);

        playAudio("Resources\\sounds\\Gate.mp3", 1, 1, true);


        checkRoom();
    }

    /**
     * This method is used when the game is over.
     */
    private void gameOver()
    {
        btnContinue.setVisible(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("GAME OVER");
        alert.setHeaderText(null);


        player.setScore(timeTracker.calculateRemainingTime() * logic.getDifficultyLevel() + (player.getHealth() / 2));
        logic.getHighScore().addHighScore(player);

        if (player.getHealth() <= 0)
        {
            alert.setContentText(gameText.getYouHaveDied() + "\nAnd your score was " + player.getScore());
        }
        else if (timeTracker.calculateRemainingTime() <= 0)
        {
            alert.setContentText(gameText.getTimeRanOut() + "\nAnd your score was " + player.getScore());
        }
        else if (lastBattle)
        {
            alert.setContentText(gameText.getIsLuciferDead() + "\nAnd your score was " + player.getScore());
        }

        alert.showAndWait();

        lastBattle = false;

        Game.switchScene("MainMenu.fxml");


    }

    /**
     * Method used to when the player tries to move via. the control buttons.
     *
     * @param event action event from the button press
     */
    @FXML
    public void handleMove(ActionEvent event)
    {
        switch (((Control) event.getSource()).getId())
        {
        case ("btnUp"):
            if (map.roomExists(new Point(player.getLocation().x, player.getLocation().y + 1)))
            {
                playerMove(new Point(player.getLocation().x, player.getLocation().y + 1));
            }
            else
            {
                textAreaMain.setText(gameText.getYouRanIntoAWall());
            }
            break;
        case "btnDown":
            if (map.roomExists(new Point(player.getLocation().x, player.getLocation().y - 1)))
            {
                playerMove(new Point(player.getLocation().x, player.getLocation().y - 1));
            }
            else
            {
                textAreaMain.setText(gameText.getYouRanIntoAWall());
            }
            break;
        case "btnLeft":
            if (map.roomExists(new Point(player.getLocation().x - 1, player.getLocation().y)))
            {
                playerMove(new Point(player.getLocation().x - 1, player.getLocation().y));
            }
            else
            {
                textAreaMain.setText(gameText.getYouRanIntoAWall());
            }
            break;
        case "btnRight":
            if (map.roomExists(new Point(player.getLocation().x + 1, player.getLocation().y)))
            {
                playerMove(new Point(player.getLocation().x + 1, player.getLocation().y));
            }
            else
            {
                textAreaMain.setText(gameText.getYouRanIntoAWall());
            }
            break;
        case "btnBack":
            playerMove(new Point(player.getLastLocation().x, player.getLastLocation().y));
            break;
        }
    }

    /**
     * This method is called when its decided if the player can move to a new location and it handles the actual move.
     *
     * @param location the location
     */
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
                textAreaMain.setText(gameText.getYouEnteredANewRoom(map.getCurrentRoom()));
                map.setRoomHasBeenEntered(player.getLocation());
            }

            map.merchantMove();
            map.thiefMove();

            playAnimation("Resources\\images\\Door.gif", 2.5);

            playAudio("Resources\\sounds\\Door.wav", 2, 0.2, true);

            textAreaMap.setText(gameText.getMap());
            checkRoom();


        }
        else if (map.isRoomLocked(location) && logic.getNumberOfAvailableKeys() == 0)
        {
            textAreaMain.setText(gameText.getRoomIsLockedNoKey());
//            checkRoom();
        }
        else
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
                map.getCurrentRoom().setLocked(false);
                map.unlockRoom(location);
                player.setLocation(location);

                textAreaMain.setText(gameText.getRoomHasBeenUnlocked());

                textAreaMain.setText(gameText.getYouEnteredANewRoom(map.getCurrentRoom()));

                map.setRoomHasBeenEntered(player.getLocation());
                map.merchantMove();
                map.thiefMove();

                textAreaMap.setText(gameText.getMap());

                updateInventory();

                playAnimation("Resources\\images\\Lock.gif", 2);

                checkRoom();
            }
            else
            {
                textAreaMain.setText("You canceled??? Why???");
            }
        }
    }

    /**
     * The Content index.
     */
    private int ContentIndex;
    /**
     * The Merchant index.
     */
    private int MerchantIndex;

    /**
     * Method that checks a room for its content and acts accordingly.
     */
    private void checkRoom()
    {
        labelTime.setText("Time is under: " + timeTracker.calculateRemainingTime() + " seconds");

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
            }
            else
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
            textAreaMain.setText(gameText.getThief(map.getThief()));
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

            playAnimation("Resources\\images\\Devil.gif", 6);

            playAudio("Resources\\sounds\\DevilTheme.mp3", 0.7, 0.7, false);

            playAudio("Resources\\sounds\\DevilGrowl.mp3", 3, 0.7, false);

            playAudio("Resources\\sounds\\Monster_04.mp3", 5, 0.7, false);

            playAudio("Resources\\sounds\\AmbienceHell.mp3", 0, 0.7, false);

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


    /**
     * Method that updates the inventory view.
     */
    private void updateInventory()
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


    /**
     * String that hold the information to where an item is coming from.
     */
    private String itemFrom;

    /**
     * Add item.
     *
     * @param index the index of which to add the item.
     */
    private void addItem(int index)
    {
        if (player.getInventory().getItem(index) == player.getWeapon())
        {
            player.setWeaponDefault();
            labelWeapon.setText("Weapon: " + ((IItem) player.getWeapon()).getName());
        }

        if (itemFrom.equals("chest"))
        {
            player.getInventory().addItem(((IChest) map.getCurrentRoom().getContent(ContentIndex)).getItem(), index);
            updateInventory();
            map.getCurrentRoom().removeContent(ContentIndex);

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
                player.getInventory().addItem(map.getMerchant().getInventory().getItem(MerchantIndex), index);

                updateInventory();
                disableButtons();
                btnContinue.setVisible(true);
                map.getMerchant().setRandomLocation(new Point(map.getWidth(), map.getHeight()));
                textAreaMain.appendText("\n\n" + "Merchant is happy :) ... Thanks you for the trade.");
                textAreaMap.setText(gameText.getMap());
            }
        }
    }

    /**
     * Method that handles the "use" button under each inventory slot.
     *
     * @param index the index
     */
    private void useItem(int index)
    {
        IItem item = player.getInventory().getItem(index);

        if (item instanceof IWeapon)
        {
            player.setWeapon((IWeapon) item);
            textAreaMain.setText(gameText.getSetCurrentWeapon());
            labelWeapon.setText("Weapon: " + ((IItem) player.getWeapon()).getName());

            playAnimation("Resources\\images\\Sword.gif", 2);
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


            playAnimation("Resources\\images\\Heart.gif", 2);

        }
        else if (item instanceof IKey)
        {
            textAreaMain.setText(gameText.getUseKey());
        }
        else if (item instanceof ISpecial)
        {
            switch (((ISpecial) item).getTypeString())
            {
            case "teleport":
                playAnimation("Resources\\images\\Warp.gif", 3);

                Dialog<Pair<String, String>> dialog = new Dialog<>();
                dialog.setTitle("Teleport");
                dialog.setHeaderText("Type in an X and Y cordinate to teleport to.");
                dialog.setGraphic(null);

                ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 150, 10, 10));

                TextField xCordinate = new TextField();
                xCordinate.setPromptText("X");
                TextField yCordinate = new TextField();
                yCordinate.setPromptText("Y");

                grid.add(new Label("X:"), 0, 0);
                grid.add(xCordinate, 1, 0);
                grid.add(new Label("Y:"), 0, 1);
                grid.add(yCordinate, 1, 1);

                Node loginButton = dialog.getDialogPane().lookupButton(okButtonType);
                loginButton.setDisable(true);

                AtomicBoolean fieldOne = new AtomicBoolean(false);
                AtomicBoolean fieldTwo = new AtomicBoolean(false);

                xCordinate.textProperty().addListener((observable, oldValue, newValue) ->
                                                      {
                                                          if (newValue.matches("\\d+") && Integer.parseInt(newValue) >= 0 && Integer.parseInt(newValue) <= map.getWidth() - 1)
                                                          {
                                                              fieldOne.set(true);
                                                          }
                                                          else
                                                          {
                                                              fieldOne.set(false);
                                                          }

                                                          if (fieldOne.get() && fieldTwo.get())
                                                          {
                                                              loginButton.setDisable(false);
                                                          }
                                                          else
                                                          {
                                                              loginButton.setDisable(true);
                                                          }

                                                      });

                yCordinate.textProperty().addListener((observable, oldValue, newValue) ->
                                                      {
                                                          if (newValue.matches("\\d+") && Integer.parseInt(newValue) >= 0 && Integer.parseInt(newValue) <= map.getHeight() - 1)
                                                          {
                                                              fieldTwo.set(true);
                                                          }
                                                          else
                                                          {
                                                              fieldTwo.set(false);
                                                          }

                                                          if (fieldOne.get() && fieldTwo.get())
                                                          {
                                                              loginButton.setDisable(false);
                                                          }
                                                          else
                                                          {
                                                              loginButton.setDisable(true);
                                                          }
                                                      });

                dialog.getDialogPane().setContent(grid);

                Platform.runLater(() -> xCordinate.requestFocus());

                dialog.setResultConverter(dialogButton ->
                                          {
                                              if (dialogButton == okButtonType)
                                              {
                                                  return new Pair<>(xCordinate.getText(), yCordinate.getText());
                                              }
                                              return null;
                                          });

                Optional<Pair<String, String>> result = dialog.showAndWait();

                result.ifPresent(cordinates ->
                                 {

                                     Point point = new Point(Integer.parseInt(cordinates.getKey()), Integer.parseInt(cordinates.getValue()));

                                     if (map.isRoomLocked(point) && player.getInventory().keyArrayList().size() == 0)
                                     {
                                         player.getInventory().removeItem(player.getInventory().getItemIndex(item));
                                         textAreaMain.setText(gameText.getTeleportToLockedRoomNoKey());
                                     }
                                     else
                                     {

                                         player.getInventory().removeItem(player.getInventory().getItemIndex(item));
                                         playerMove(point);
                                     }
                                 });
                break;
            case "bomb":
                playAnimation("Resources\\images\\Bomb.gif", 2.3);
                playAudio("Resources\\sounds\\Explosion.mp3", 2, 1, true);
                textAreaMain.setText(gameText.getUseSpecialBomb());
                ((ISpecial) item).use(player, map);
                player.getInventory().removeItem(player.getInventory().getItemIndex(item));
                break;
            case "awesome_name":
                ((ISpecial) item).use(player);
                textAreaMain.setText(gameText.getPlayerName());
                player.getInventory().removeItem(player.getInventory().getItemIndex(item));
                break;
            case "vision":
                playAnimation("Resources\\images\\Glasses.gif", 2.5);
                //((ISpecial) item).use(player);
                textAreaMain.setText("\n" + gameText.getVisionMap());
                player.getInventory().removeItem(player.getInventory().getItemIndex(item));
                break;
            case "extra_slot":
                ((ISpecial) item).use(player);
                textAreaMain.setText(gameText.getUseSpecialExtraSlot());

                int i = player.getInventory().getSize() - 1;
                TextArea textArea = new TextArea(gameText.getInventorySlot(i, player.getInventory()));
                textArea.setWrapText(true);

                Button btnAdd = new Button(String.valueOf(i + 1));
                btnAdd.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                btnAdd.setStyle("-fx-font: 14px Monospaced;");
                btnAdd.setOnAction((ActionEvent event) -> addItem(i));

                Button btnUse = new Button("Use");
                btnUse.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                btnUse.setStyle("-fx-font: 14px Monospaced;");
                btnUse.setOnAction((ActionEvent event) -> useItem(i));

                Button btnShow = new Button("Show");
                btnShow.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                btnShow.setStyle("-fx-font: 14px Monospaced;");
                btnShow.setOnAction((ActionEvent event) -> showItem(i));

                VBox vBox = new VBox();
                vBox.setId(String.valueOf(i));
                vBox.getChildren().addAll(btnAdd, textArea, btnUse, btnShow);
                hboxInventory.getChildren().add(vBox);

                updateInventory();
                player.getInventory().removeItem(player.getInventory().getItemIndex(item));
                disableButtons(new Button[]{btnUp, btnDown, btnLeft, btnRight}, true, false);
                break;
            }
        }
        else
        {
            //Prints "Slot is empty."
            textAreaMain.setText(gameText.getSlotIsEmpty());
        }

        updateInventory();
    }

    /**
     * Timeline for all image animations.
     */
    private Timeline animationTimeline = new Timeline();

    /**
     * Show image.
     *
     * @param imagePath the image path to use.
     * @param time      the duration of the animation.
     */
    private void playAnimation(String imagePath, double time)
    {
        if (!SettingsController.getSkipAnimation())
        {
            animationTimeline.stop();
            ImagePane.setOpacity(1);
            ImagePane.setVisible(true);
            ImagePlaceHolder.setImage(new Image(new File(imagePath).toURI().toString()));
            animationTimeline.setCycleCount(2);
            animationTimeline.setAutoReverse(true);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(time), new KeyValue(ImagePlaceHolder.opacityProperty(), 1.0));
            animationTimeline.getKeyFrames().add(keyFrame);
            animationTimeline.setOnFinished(arg0 ->
                                            {
                                                ImagePane.setVisible(false);
                                                ImagePlaceHolder.setOpacity(0);
                                            });
            animationTimeline.play();
        }
    }

    /**
     * Timeline for all audio effects.
     */
    private Timeline audioTimeline;
    private AudioClip audioS;

    /**
     * Method for playing audio.
     *
     * @param audioPath the audio path to where the audio is stored
     * @param delay     the delay time in seconds
     * @param volume    the volume at which the audio is player
     */
    private void playAudio(String audioPath, double delay, double volume, boolean stoppable)
    {
        if (stoppable)
        {
            if (audioTimeline != null) audioTimeline.stop();
            if (audioS != null) audioS.stop();
            audioS = new AudioClip(new File(audioPath).toURI().toString());
            audioS.setVolume(volume * SettingsController.getEffectVolume());

            audioTimeline = new Timeline();
            audioTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(delay)));
            audioTimeline.setOnFinished(arg0 ->
                                        {
                                            audioS.play();
                                        });
            audioTimeline.play();
        }
        else
        {
            AudioClip audio = new AudioClip(new File(audioPath).toURI().toString());
            audio.setVolume(volume * SettingsController.getEffectVolume());

            Timeline audioTimeline = new Timeline();
            audioTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(delay)));
            audioTimeline.setOnFinished(arg0 ->
                                        {
                                            audio.play();
                                        });
            audioTimeline.play();
        }


    }

    /**
     * Method that displays the item from the inventory.
     *
     * @param index the index
     */
    private void showItem(int index)
    {
        textAreaMain.setText(gameText.getItemInfo(player.getInventory().getItem(index)));
    }

    /**
     * Boolean that holds whether or not the final boss battle is on.
     */
    private boolean lastBattle = false;

    /**
     * The Attack sounds.
     */
    private final String[] attackSounds = new String[]{
            "Resources\\sounds\\DuckToy.aiff",
            "Resources\\sounds\\Hit1.wav",
            "Resources\\sounds\\Hit2.wav",
            "Resources\\sounds\\Hit3.wav",
            "Resources\\sounds\\Hit4.wav",
            "Resources\\sounds\\Hit32.mp3",
            "Resources\\sounds\\Hit33.mp3",
            "Resources\\sounds\\Hit34.mp3",
            "Resources\\sounds\\Hit35.mp3",
            "Resources\\sounds\\Hit36.mp3"
    };

    /**
     * Handle attack.
     *
     */
    public void handleAttack()
    {
        if (lastBattle)
        {
            textAreaMain.appendText("\n" + gameText.getBattle(battle));

            int random = new Random().nextInt(attackSounds.length);
            playAudio(attackSounds[random], 0, 1, false);

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

                textAreaMain.appendText("\n" + gameText.getBattle(battle));
                labelHealth.setText("Health: " + String.valueOf(player.getHealth()));
                labelTime.setText("Time is under: " + timeTracker.calculateRemainingTime() + " seconds");

                if (battle.getIsBattleOver())
                {
                    playAudio("Resources\\sounds\\TheWilhelmScream.mp3", 0, 1, false);

                    battle = null;
                    map.getCurrentRoom().removeContent(ContentIndex);
                    disableButtons();
                    btnContinue.setVisible(true);
                }
                else
                {
                    int random = new Random().nextInt(attackSounds.length);
                    playAudio(attackSounds[random], 0, 1, false);

                }
                break;
            case "Guide":
                if (audioS != null) audioS.stop();

                int random = new Random().nextInt(attackSounds.length);
                playAudio(attackSounds[random], 0, 1, false);

                textAreaMain.appendText("\n" + gameText.getKilledGuide());
                map.getCurrentRoom().removeContent(ContentIndex);
                disableButtons();
                btnContinue.setVisible(true);

                break;
            }
        }


    }

    /**
     * Handle flee.
     *
     */
    public void handleFlee()
    {
        player.setLocation(player.getLastLocation());

        textAreaMap.setText(gameText.getMap());

        if (audioS != null) audioS.stop();

        checkRoom();
    }


    /**
     * Handle open.
     *
     */
    @FXML
    private void handleOpen()
    {
        textAreaMain.setText(gameText.getItemInfo(((IChest) map.getCurrentRoom().getContent(ContentIndex)).getItem()));
        textAreaMain.appendText("\n" + gameText.getWhatSlot());

        disableButtons(new Button[]{btnSkip}, false, true);

        playAnimation("Resources\\images\\Chest.gif", 2.5);

        playAudio("Resources\\sounds\\ChestOpen.mp3", 0.7, 0.7, true);
    }

    /**
     * Handle continue.
     *
     */
    @FXML
    private void handleContinue()
    {
        if (lastBattle || player.getHealth() <= 0)
        {
            if (player.getHealth() <= 0)
            {
                gameOver();
                return;
            }
            else
            {
                textAreaMain.setText(gameText.getIsLuciferDead());
            }
            gameOver();
            return;
        }
        else
        {
            btnContinue.setVisible(false);
        }

        checkRoom();

    }

    /**
     * The Guide talk count.
     */
    private int guideTalkCount = 0;

    /**
     * Handle talk.
     *
     */
    @FXML
    private void handleTalk()
    {
        if (guideTalkCount < 10)
        {
            if (audioS != null) audioS.stop();

            playAudio("Resources\\sounds\\wa-wa-effect.mp3", 0, 1, true);

            textAreaMain.appendText("\n\n" + gameText.getGuideTalk());
            guideTalkCount++;
        }
        else
        {
            textAreaMain.appendText("\n\n" + gameText.getGuideLimit());
        }

    }

    /**
     * Handle skip.
     *
     */
    @FXML
    private void handleSkip()
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

        if (audioS != null) audioS.stop();
        checkRoom();

    }

    private boolean itemAddAvailable = false;
    private boolean itemUseAvailable = false;

    /**
     * Disable buttons.
     *
     * @param buttons              the buttons
     * @param showInventory        the show inventory
     * @param showInventoryNumbers the show inventory numbers
     */
    private void disableButtons(Button[] buttons, boolean showInventory, boolean showInventoryNumbers)
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
                            itemUseAvailable = true;
                            nodeIn2.setDisable(false);
                        }
                        else
                        {
                            itemUseAvailable = false;
                            nodeIn2.setDisable(true);
                        }

                        if (showInventoryNumbers && ((Button) nodeIn2).getText().matches("[0-9]+"))
                        {
                            itemAddAvailable = true;
                            nodeIn2.setDisable(false);
                        }
                        else if (!showInventoryNumbers && ((Button) nodeIn2).getText().matches("[0-9]+"))
                        {
                            itemAddAvailable = false;
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

    /**
     * Disable buttons.
     */
    private void disableButtons()
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

    /**
     * Enable all buttons.
     */
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

    /**
     * Handle image click.
     */
    @FXML
    private void handleImageClick()
    {
        if (animationTimeline != null) animationTimeline.stop();
        if (audioTimeline != null) audioTimeline.stop();
        if (audioS != null) audioS.stop();
        ImagePane.setVisible(false);
        ImagePlaceHolder.setOpacity(0);
    }

}
