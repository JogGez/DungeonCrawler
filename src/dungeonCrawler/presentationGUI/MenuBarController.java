package dungeonCrawler.presentationGUI;

import dungeonCrawler.aqu.ILogicFacade;
import dungeonCrawler.aqu.IPlayer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The top menu handler
 * extends SettingsController
 * implements Initializable
 */
public class MenuBarController extends SettingsController implements Initializable
{
    private ILogicFacade logic;
    private IPlayer player;
    @FXML
    public MenuItem menuSaveGame;

    /**
     * Event handler for MenuItem one
     * @param event Sets the event to use.
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

    /**
     * Event handler for a new game
     * @param event Sets the event to use.
     */
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

    /**
     * Event handler to switch to save game 1 to 3
     * @param actionEvent Sets the actionEvent to use.
     */
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

    /**
     * handler for exiting game
     * @param actionEvent Sets the actionEvent to use.
     */
    @FXML
    private void exitGame(ActionEvent actionEvent)
    {
        Platform.exit();
    }

    /**
     * The initialize method used for when the controller is first initialized
     * @param location Sets the location to use.
     * @param resources Sets the resources to use.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        logic = Game.getLogic();
        player = logic.getPlayer();
    }

    /**
     * Handler for changing theme
     * @param actionEvent Sets the actionEvent to use.
     */
    @FXML
    private void changeTheme(ActionEvent actionEvent)
    {
//        System.out.println(actionEvent.toString());
//        System.out.println(((MenuItem)actionEvent.getSource()).getText());
//        System.out.println(actionEvent.getSource().toString());
        if (((MenuItem)actionEvent.getSource()).getText().contains("Default"))
        {
            Game.getScene().getStylesheets().clear();
            Game.setUserAgentStylesheet(null);
            Game.getScene().getStylesheets().add(getClass().getResource("ThemeDefault.css").toExternalForm());
        }
        else if (((MenuItem)actionEvent.getSource()).getText().contains("White"))
        {
            Game.getScene().getStylesheets().clear();
            Game.setUserAgentStylesheet(null);
            Game.getScene().getStylesheets().add(getClass().getResource("ThemeWhite.css").toExternalForm());
        }
        else if (((MenuItem)actionEvent.getSource()).getText().contains("Dark"))
        {
            Game.getScene().getStylesheets().clear();
            Game.setUserAgentStylesheet(null);
            Game.getScene().getStylesheets().add(getClass().getResource("ThemeDark.css").toExternalForm());
        }

    }

    /**
     * method to open help scene
     * @param actionEvent Sets the actionEvent to use.
     * @throws IOException
     */
    @FXML
    private void openHelp(ActionEvent actionEvent) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Help.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:Swords.png"));
        stage.setTitle("Help");
        stage.setScene(new Scene(root));
        stage.show();
    }
}