/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonCrawler.presentationGUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dungeonCrawler.aqu.*;
import dungeonCrawler.logic.GameText;
import dungeonCrawler.presentationConsole.Parser;
import dungeonCrawler.presentationConsole.PrintToConsole;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Slayga
 */
public class ControllerDifficultyScreen implements Initializable
{

    private ILogicFacade logic;
    // Parser for handling the user input
    private Parser parser;
    //Creating print to console object
    private PrintToConsole printToConsole;
    // Stores what room we are currently in
    private IMap map;
    // We are storing the class player's name for player.
    private IPlayer player;
    //Creating print to console object
    private GameText gameText;
    //Create timeTracker.
    private ITimeTracker timeTracker;
    @FXML
    private Button btnEasy;
    @FXML
    private Button btnNormal;
    @FXML
    private Button btnHard;
    @FXML
    private Button btnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void handleSetEasy(ActionEvent event) throws  IOException
    {
        logic.setDifficultyLevel(1);
        //TODO Gå videre til play
    }

    @FXML
    private void handleSetNormal(ActionEvent event) throws  IOException
    {
        logic.setDifficultyLevel(2);
        //TODO Gå videre til play
    }

    @FXML
    private void handleSetHard(ActionEvent event) throws  IOException
    {
        logic.setDifficultyLevel(3);
        //TODO Gå videre til play
    }



    @FXML
    private void handleBack(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("StartScreen.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene1 = new Scene(root);
        window.setScene(scene1);
        window.show();
    }

}
