/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonCrawler.presentationGUI;

import java.io.IOException;
import java.util.Optional;

import dungeonCrawler.aqu.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Slayga
 */
public class DifficultyController
{
    private ILogicFacade logic;

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
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene1 = new Scene(root);
        window.setScene(scene1);
        window.show();
    }


    public void handleBtnEasy(ActionEvent event) throws IOException
    {
        logic.setDifficultyLevel(1);
        showStartScene(event);

    }

    public void handleBtnNormal(ActionEvent event) throws IOException
    {
        logic.setDifficultyLevel(2);
        showStartScene(event);
    }

    public void handleBtnHard(ActionEvent event) throws IOException
    {
        logic.setDifficultyLevel(3);
        showStartScene(event);
    }

    private void showStartScene(ActionEvent event) throws IOException
    {
        TextInputDialog dialog = new TextInputDialog("");

            dialog.setTitle("Player Name:");
            dialog.setHeaderText(null);
            dialog.setContentText("Name:");


            Optional<String> result = dialog.showAndWait();


            if (result.isPresent() && result.get().length() > 0)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Play.fxml"));

                AnchorPane anchorPane = loader.load();
                PlayController controller = loader.getController();
                controller.startGame(logic, result.get());

                Scene scene2 = new Scene(anchorPane);
                //Get Stage information

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.getIcons().add(new Image("file:Swords.png"));
                window.setMinWidth(800);
                window.setMinHeight(600);
                window.setWidth(1024);
                window.setHeight(768);
                window.setScene(scene2);
                window.show();
            }








    }


    public void injectLogic(ILogicFacade logicLayer)
    {
        logic = logicLayer;
    }


}
