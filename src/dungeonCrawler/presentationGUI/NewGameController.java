package dungeonCrawler.presentationGUI;

import dungeonCrawler.aqu.ILogicFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.media.AudioClip;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Screen for selecting game difficulty
 * extends MainMenuController
 * implements Initializable
 */
public class NewGameController extends MainMenuController implements Initializable
{
    private ILogicFacade logic;
    public static String playerName;

    @FXML
    private Button btnEasy;
    @FXML
    private Button btnNormal;
    @FXML
    private Button btnHard;
    @FXML
    private Button btnBack;

    /**
     * The initialize method used for when the controller is first initialized
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        logic = Game.getLogic();
    }

    /**
     * Handles going back to prev scene
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleBack(ActionEvent event) throws IOException
    {
        AudioClip soundMyNoise = new AudioClip(new File("Resources\\sounds\\click.mp3").toURI().toString());
        soundMyNoise.setVolume(1);
        soundMyNoise.play();
        Game.switchScene("MainMenu.fxml");
    }

    /**
     * Handles setting gamesettings to easy
     * @param event
     * @throws IOException
     */
    @FXML
    public void handleBtnEasy(ActionEvent event) throws IOException
    {
        AudioClip soundMyNoise = new AudioClip(new File("Resources\\sounds\\click.mp3").toURI().toString());
        soundMyNoise.setVolume(1);
        soundMyNoise.play();
        logic.setDifficultyLevel(1);
        showStartScene(event);

    }

    /**
     * Handles setting gamesettings to normal
     * @param event
     * @throws IOException
     */
    @FXML
    public void handleBtnNormal(ActionEvent event) throws IOException
    {
        AudioClip soundMyNoise = new AudioClip(new File("Resources\\sounds\\click.mp3").toURI().toString());
        soundMyNoise.setVolume(1);
        soundMyNoise.play();
        logic.setDifficultyLevel(2);
        showStartScene(event);
    }

    /**
     * Handles setting gamesettings to hard
     * @param event
     * @throws IOException
     */
    @FXML
    public void handleBtnHard(ActionEvent event) throws IOException
    {
        AudioClip soundMyNoise = new AudioClip(new File("Resources\\sounds\\click.mp3").toURI().toString());
        soundMyNoise.setVolume(1);
        soundMyNoise.play();
        logic.setDifficultyLevel(3);
        showStartScene(event);
    }

    /**
     * Handles prompt window for player name, and then starts the game.
     * @param event
     * @throws IOException
     */
    @FXML
    private void showStartScene(ActionEvent event) throws IOException
    {
        TextInputDialog dialog = new TextInputDialog("");

        dialog.setTitle("Player Name:");
        dialog.setHeaderText(null);
        dialog.setContentText("Name:");
        dialog.setGraphic(null);
        dialog.getDialogPane().setMinWidth(400);

        Optional<String> result = dialog.showAndWait();


        if (result.isPresent() && result.get().length() > 0)
        {
            playerName = result.get();
            Game.switchScene("Play.fxml");
        }
        playerName = "";
    }


    public void injectLogic(ILogicFacade logicLayer)
    {
        logic = logicLayer;
    }

}
