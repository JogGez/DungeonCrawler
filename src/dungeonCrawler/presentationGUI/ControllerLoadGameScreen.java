/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonCrawler.presentationGUI;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Slayga
 */
public class ControllerLoadGameScreen {

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
    
}
