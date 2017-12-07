package dungeonCrawler.presentationGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class HighscoreController
{
    @FXML
    private Button btnBack;

    @FXML
    public void handleBack(ActionEvent event) throws IOException
    {
        Game.switchScene("MainMenu.fxml");
    }
}
