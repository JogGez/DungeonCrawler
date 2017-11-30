/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonCrawler.presentationGUI;

import java.io.IOException;
import java.util.Date;

import dungeonCrawler.aqu.*;
import dungeonCrawler.logic.GameText;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Slayga
 */
public class ControllerDifficultyScreen implements IGame
{

    ILogicFacade logic;

    @FXML
    private Button btnEasy;
    @FXML
    private Button btnNormal;
    @FXML
    private Button btnHard;
    @FXML
    private Button btnBack;


    @FXML
    private void handleBack(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("StartScreen.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene1 = new Scene(root);
        window.setScene(scene1);
        window.show();
    }


    public void handleBtnEasy(ActionEvent event) throws IOException
    {
        showStartScene(event);
        logic.setDifficultyLevel(1);
    }

    public void handleBtnNormal(ActionEvent event) throws IOException
    {
        showStartScene(event);
        logic.setDifficultyLevel(2);
    }

    public void handleBtnHard(ActionEvent event) throws IOException
    {
        showStartScene(event);
        logic.setDifficultyLevel(3);
    }

    private void showStartScene(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PlayMainScene.fxml"));

        GridPane gridPane = loader.load();
        IGame controller = loader.getController();
        controller.injectLogic(logic);

        Scene scene2 = new Scene(gridPane);
        //Get Stage information

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }

    @Override
    public void injectLogic(ILogicFacade logicLayer)
    {
        logic = logicLayer;
    }


}
