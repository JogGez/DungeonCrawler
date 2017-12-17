package dungeonCrawler.presentationGUI;

import dungeonCrawler.aqu.ILogicFacade;
import dungeonCrawler.logic.GameText;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.media.AudioClip;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class to present highscore in gui from data.
 * implements Initializable
 */
public class HighscoreController implements Initializable
{
    private ILogicFacade logic;
    private GameText gameText;
    @FXML
    public TextArea textAreaHighScore;
    @FXML
    private Button btnBack;

    /**
     * Handles the back btn press
     * @param event the event to use
     * @throws IOException
     */
    @FXML
    public void handleBack(ActionEvent event) throws IOException
    {
        Game.switchScene("MainMenu.fxml");
        AudioClip soundMyNoise = new AudioClip(new File("Resources\\sounds\\click.mp3").toURI().toString());
        soundMyNoise.setVolume(1);
        soundMyNoise.play();
    }

    /**
     * The initialize method used for when the controller is first initialized
     * @param location the URL location to use
     * @param resources the ResourceBundle to use
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.logic = Game.getLogic();
        this.gameText = logic.getGameText();
        textAreaHighScore.setText(gameText.getHighScore());
    }
}
