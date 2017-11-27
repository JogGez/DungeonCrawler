package dungeonCrawler.presentationGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("StartScreen.fxml"));
        primaryStage.setTitle("Dungeon Crawler beta v. 1.0");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void play()
    {
        launch();
    }
}
