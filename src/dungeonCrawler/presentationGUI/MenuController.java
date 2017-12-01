package dungeonCrawler.presentationGUI;

//import dungeonCrawler.aqu.IGame;
import dungeonCrawler.aqu.ILogicFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController implements Initializable
{
    ILogicFacade logic;

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
    public void handlePlay(ActionEvent actionEvent) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Difficulty.fxml"));

        AnchorPane anchorPane = loader.load();
        DifficultyController controller = loader.getController();
        controller.injectLogic(logic);

        Scene scene2 = new Scene(anchorPane);
        //Get Stage information

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.getIcons().add(new Image("file:Swords.png"));
        window.setScene(scene2);
        window.show();
    }

    @FXML
    public void handleHighscore(ActionEvent actionEvent) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("HighScoreScreen.fxml"));
        AnchorPane anchorPane = loader.load();
        HighscoreController highscoreController = loader.getController();
        Scene scene2 = new Scene(anchorPane);
        //Get Stage information

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.getIcons().add(new Image("file:Swords.png"));
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
        loader.setLocation(getClass().getResource("Load.fxml"));

        AnchorPane anchorPane = loader.load();

        LoadController loadController = loader.getController();
        Scene scene2 = new Scene(anchorPane);
        //Get Stage information

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getIcons().add(new Image("file:Swords.png"));
        window.setScene(scene2);
        window.show();
    }


    public void injectLogic(ILogicFacade logicLayer)
    {
        logic = logicLayer;
        lblTitle.setText(logic.getGameText().getAsciiTitle());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
}
