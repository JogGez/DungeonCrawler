package dungeonCrawler.presentationGUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dungeonCrawler.aqu.ILogicFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

/**
 * Class to handle drop down section LoadGame
 * implements Initializable
 */
public class LoadController implements Initializable
{
    @FXML
    private Button btnBack;

    private ILogicFacade logic;

    /**
     * Handles the back btn
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleBack(ActionEvent event) throws IOException
    {
        Game.switchScene("MainMenu.fxml");
        AudioClip soundMyNoise = new AudioClip(new File("Resources\\sounds\\click.mp3").toURI().toString());
        soundMyNoise.setVolume(1);
        soundMyNoise.play();
    }

    /**
     * Handles load game 1
     * @param actionEvent
     */
    @FXML
    private void handleLoad1(ActionEvent actionEvent)
    {
        logic.loadGame(1);
        PlayController.NewGame = false;
        Game.switchScene("Play.fxml");
    }

    /**
     * Handles load game 2
     * @param actionEvent
     */
    @FXML
    private void handleLoad2(ActionEvent actionEvent)
    {
        logic.loadGame(2);
        PlayController.NewGame = false;
        Game.switchScene("Play.fxml");
    }

    /**
     * Handles load game 3
     * @param actionEvent
     */
    @FXML
    private void handleLoad3(ActionEvent actionEvent)
    {
        logic.loadGame(3);
        PlayController.NewGame = false;
        Game.switchScene("Play.fxml");
    }

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
}
