package dungeonCrawler.presentationGUI;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dungeonCrawler.aqu.ILogicFacade;
import dungeonCrawler.aqu.IPlayer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MenuBarController implements Initializable
{
    private ILogicFacade logic;
    private IPlayer player;
    @FXML
    public MenuItem menuSaveGame;

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
    private void switchToSaveGame(ActionEvent actionEvent)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Save Game");
        alert.setHeaderText(null);

        ButtonType buttonType1 = new ButtonType("Save 1");
        ButtonType buttonType2 = new ButtonType("Save 2");
        ButtonType buttonType3 = new ButtonType("Save 3");
        alert.getButtonTypes().addAll(buttonType1,buttonType2,buttonType3);

        alert.setContentText("Please choose which slot to save.");
        alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(false);

        Optional<ButtonType> result = alert.showAndWait();


//        player.setTime(timeTracker.calculateRemainingTime());

        if (result.get().getText().contains("Save 1"))
        {
            logic.saveGame(1);
        }
        else if (result.get().getText().contains("Save 2"))
        {
            logic.saveGame(2);
        }
        else if (result.get().getText().contains("Save 3"))
        {
            logic.saveGame(3);
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
}