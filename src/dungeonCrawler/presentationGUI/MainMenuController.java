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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.util.Duration;

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


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        FadeTransition fadeTransition = new FadeTransition(new Duration(1000),anchorPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(100);
        fadeTransition.setCycleCount(1);
        fadeTransition.setInterpolator(Interpolator.LINEAR);
        fadeTransition.play();
    }

    @FXML
    public void handlePlay(ActionEvent actionEvent) throws IOException
    {
        Game.switchScene("NewGame.fxml");
    }

    @FXML
    private void handleLoadGame(ActionEvent event) throws IOException
    {
        Game.switchScene("Load.fxml");
    }

    @FXML
    public void handleHighscore(ActionEvent actionEvent) throws IOException
    {
        Game.switchScene("HighScore.fxml");
    }

    @FXML
    private void handleExit(ActionEvent event)
    {
        Platform.exit();
    }


    public void injectLogic(ILogicFacade logicLayer)
    {
        logic = logicLayer;
        lblTitle.setText(logic.getGameText().getAsciiTitle());
    }

}
