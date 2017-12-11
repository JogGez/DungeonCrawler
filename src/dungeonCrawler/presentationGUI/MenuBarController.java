package dungeonCrawler.presentationGUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dungeonCrawler.aqu.ILogicFacade;
import dungeonCrawler.aqu.IPlayer;
import dungeonCrawler.aqu.ITimeTracker;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MenuBarController implements Initializable
{
    private ILogicFacade logic;
    private IPlayer player;
    @FXML
    public MenuItem menuSaveGame;
    private ITimeTracker timeTracker;

    /**
     * Event handler for MenuItem one
     */
    @FXML
    void switchToMainMenu(ActionEvent event)
    {
        try
        {
            URL paneOneUrl = getClass().getResource("MainMenu.fxml");
            AnchorPane paneOne = FXMLLoader.load(paneOneUrl);

            BorderPane border = Game.getRoot();
            border.setCenter(paneOne);

        } catch (IOException e)
        {
            e.printStackTrace();
        }


    }

    @FXML
    void switchToNewGame(ActionEvent event)
    {

        try
        {
            URL paneTwoUrl = getClass().getResource("NewGame.fxml");
            AnchorPane paneTwo = FXMLLoader.load(paneTwoUrl);

            BorderPane border = Game.getRoot();
            border.setCenter(paneTwo);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Event handler for MenuItem two
     */
    @FXML
    void switchToLoadGame(ActionEvent event)
    {
        try
        {
            URL paneTwoUrl = getClass().getResource("Load.fxml");
            AnchorPane paneTwo = FXMLLoader.load(paneTwoUrl);

            BorderPane border = Game.getRoot();
            border.setCenter(paneTwo);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }



    @FXML
    private void exitGame(ActionEvent actionEvent)
    {
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        logic = Game.getLogic();
        player = logic.getPlayer();
    }

    @FXML
    private void switchToSaveGame(ActionEvent actionEvent)
    {
        //TODO playerens tid bliver ikke gemt.
//        player.setTime(timeTracker.calculateRemainingTime());
        logic.saveGame();
    }
}