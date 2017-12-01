package dungeonCrawler.presentationGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HighscoreController
{
    public void handleButtonBack(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene1 = new Scene(root);
        window.setScene(scene1);
        window.show();
    }
}
