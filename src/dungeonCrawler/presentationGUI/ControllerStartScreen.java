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
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControllerStartScreen
{

    @FXML
    private Button btnPlay;
    @FXML
    private Button btnHighscore;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnLoad;

    @FXML
    public void handlePlay(ActionEvent actionEvent)
    {
    }

    @FXML
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

    @FXML
    private void handleExit(ActionEvent event) 
    {
        Platform.exit();
    }

    @FXML
    private void handleLoadGame(ActionEvent event) throws IOException 
    {
              FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("LoadGameScreen.fxml"));


        AnchorPane anchorPane = loader.load();

        ControllerLoadGameScreen controllerLoadGameScreen = loader.getController();
        Scene scene2 = new Scene(anchorPane);
        //Get Stage information

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();

    }
}
