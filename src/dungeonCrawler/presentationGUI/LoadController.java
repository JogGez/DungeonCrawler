package dungeonCrawler.presentationGUI;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class LoadController
{
    @FXML
    private Button btnBack;

    @FXML
    private void handleBack(ActionEvent event) throws IOException 
    {
        Game.switchScene("MainMenu.fxml");
    }

    @FXML
    private void handleLoad(ActionEvent actionEvent)
    {
        PlayController.NewGame = false;
        Game.switchScene("Play.fxml");
    }
}
