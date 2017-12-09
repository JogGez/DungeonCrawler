package dungeonCrawler.presentationGUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dungeonCrawler.aqu.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;


public class NewGameController implements Initializable
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

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        logic = Game.getLogic();
    }


    @FXML
    private void handleBack(ActionEvent event) throws IOException
    {
        AudioClip soundMyNoise = new AudioClip(new File("click.mp3").toURI().toString());
        soundMyNoise.setVolume(1);
        soundMyNoise.play();
        Game.switchScene("MainMenu.fxml");
    }

    @FXML
    public void handleBtnEasy(ActionEvent event) throws IOException
    {
        AudioClip soundMyNoise = new AudioClip(new File("click.mp3").toURI().toString());
        soundMyNoise.setVolume(1);
        soundMyNoise.play();
        logic.setDifficultyLevel(1);
        showStartScene(event);

    }

    @FXML
    public void handleBtnNormal(ActionEvent event) throws IOException
    {
        AudioClip soundMyNoise = new AudioClip(new File("click.mp3").toURI().toString());
        soundMyNoise.setVolume(1);
        soundMyNoise.play();
        logic.setDifficultyLevel(2);
        showStartScene(event);
    }

    @FXML
    public void handleBtnHard(ActionEvent event) throws IOException
    {
        AudioClip soundMyNoise = new AudioClip(new File("click.mp3").toURI().toString());
        soundMyNoise.setVolume(1);
        soundMyNoise.play();
        logic.setDifficultyLevel(3);
        showStartScene(event);
    }

    @FXML
    private void showStartScene(ActionEvent event) throws IOException
    {
        TextInputDialog dialog = new TextInputDialog("");

        dialog.setTitle("Player Name:");
        dialog.setHeaderText(null);
        dialog.setContentText("Name:");


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
