package dungeonCrawler.presentationGUI;

//import dungeonCrawler.aqu.IGame;
import dungeonCrawler.aqu.ILogicFacade;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Class to handle the main screen in GUI
 * implements Initializable
 */
public class MainMenuController implements Initializable
{
    ILogicFacade logic;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnHighscore;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnLoad;
    @FXML
    private Label lblTitle;
    @FXML
    private ImageView imageBackground;
    @FXML
    private Button btnSettings;


    /**
     * The initialize method used for when the controller is first initialized
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        FadeTransition fadeTransition = new FadeTransition(new Duration(1000),anchorPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(100);
        fadeTransition.setCycleCount(1);
        fadeTransition.setInterpolator(Interpolator.LINEAR);
        fadeTransition.play();

        logic = Game.getLogic();
        lblTitle.setText(logic.getGameText().getAsciiTitle());
    }

    /**
     * Handles btn press play
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void handlePlay(ActionEvent actionEvent) throws IOException
    {
        Game.switchScene("NewGame.fxml");
        AudioClip soundMyNoise = new AudioClip(new File("Resources\\sounds\\click.mp3").toURI().toString());
        soundMyNoise.setVolume(1);
        soundMyNoise.play();
    }

    /**
     * Handles btn press load
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleLoadGame(ActionEvent event) throws IOException
    {
        Game.switchScene("Load.fxml");
        AudioClip soundMyNoise = new AudioClip(new File("Resources\\sounds\\click.mp3").toURI().toString());
        soundMyNoise.setVolume(1);
        soundMyNoise.play();
    }

    /**
     * Handles btn press highscore
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void handleHighscore(ActionEvent actionEvent) throws IOException
    {
        Game.switchScene("HighScore.fxml");
        AudioClip soundMyNoise = new AudioClip(new File("Resources\\sounds\\click.mp3").toURI().toString());
        soundMyNoise.setVolume(1);
        soundMyNoise.play();
    }

    /**
     * Handles btn press settings
     * @param event
     */
    @FXML
    private void handleSettings(ActionEvent event)
    {
        Game.switchScene("Settings.fxml");
        AudioClip soundMyNoise = new AudioClip(new File("Resources\\sounds\\click.mp3").toURI().toString());
        soundMyNoise.setVolume(1);
        soundMyNoise.play();
    }

    /**
     * Handles btn press exit
     * @param event
     */
    @FXML
    private void handleExit(ActionEvent event)
    {
        AudioClip soundMyNoise = new AudioClip(new File("Resources\\sounds\\click.mp3").toURI().toString());
        soundMyNoise.setVolume(1);
        soundMyNoise.play();
        Platform.exit();
    }

}
