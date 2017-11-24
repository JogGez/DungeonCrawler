package dungeonCrawler.presentationGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerStartScreen
{

    public void handlePlay(ActionEvent actionEvent)
    {
    }

    public void handleHighscore(ActionEvent actionEvent) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("HighScoreScreen.fxml"));


        AnchorPane anchorPane = loader.load();

        ControllerHighscoreScreen controllerHighscoreScreen = loader.getController();
        Scene scene2 = new Scene(anchorPane);
        //Get Stage information

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();

    }
}
