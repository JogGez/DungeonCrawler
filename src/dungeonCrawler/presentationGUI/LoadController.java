package dungeonCrawler.presentationGUI;

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class LoadController
{
    @FXML
    private Button btnBack;

    @FXML
    private void handleBack(ActionEvent event) throws IOException 
    {
        Game.switchScene("MainMenu.fxml");
        AudioClip soundMyNoise = new AudioClip(new File("click.mp3").toURI().toString());
        soundMyNoise.setVolume(1);
        soundMyNoise.play();
    }

    @FXML
    private void handleLoad(ActionEvent actionEvent)
    {
        PlayController.NewGame = false;
        Game.switchScene("Play.fxml");
    }
}
