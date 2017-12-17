package dungeonCrawler.presentationGUI;

import dungeonCrawler.aqu.ILogicFacade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Class used to get the game running in GUI
 */
public class Game extends Application
{
    private static ILogicFacade logic;
    public static MediaPlayer mediaPlayer;
    public static MediaPlayer mediaPlayer1;

    // Creating a static root to pass to the controller
    private static BorderPane root = new BorderPane();
    public static MenuBar menuBar;

    /**
     * Is the border pane
     * @return BorderPane
     */
    public static BorderPane getRoot()
    {
        return root;
    }

    static Stage stage;

    /**
     * Start scene
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception
    {
        Game.stage = stage;

        MenuBar menuBar = FXMLLoader.load(getClass().getResource("MenuBar.fxml"));
        Game.menuBar = menuBar;

        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        AnchorPane menu = menuLoader.load();

        root.setTop(menuBar);
        root.setCenter(menu);



        stage.getIcons().add(new Image("file:Swords.png"));
        stage.setTitle("Dungeon Crawler");
        stage.setMinWidth(1280);
        stage.setMinHeight(768);
        stage.setScene(new Scene(root));

        stage.getScene().getStylesheets().clear();
        setUserAgentStylesheet(null);
        stage.getScene().getStylesheets().add(getClass().getResource("ThemeDefault.css").toExternalForm());

        stage.show();

        Media sound = new Media(new File("Resources\\sounds\\Fire.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.7);
        mediaPlayer.play();

        Media sound1 = new Media(new File("Resources\\sounds\\Menu.mp3").toURI().toString());
        mediaPlayer1 = new MediaPlayer(sound1);
        mediaPlayer1.setVolume(0.6);
        mediaPlayer1.play();
    }

//    public static void changeScene(String fxml) throws IOException
//    {
//        FXMLLoader loader = new FXMLLoader(Game.class.getResource(fxml));
//        Parent root = loader.load();
//
//
//        stage.setScene(new Scene(root));
//    }

    /**
     * switches the current viewable scene
     * @param fxml
     */
    public static void switchScene(String fxml) {

        try {

            AnchorPane center = FXMLLoader.load(Game.class.getResource(fxml));

            BorderPane border = Game.getRoot();
            border.setCenter(center);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Getter method for ILogicFacade
     * @return ILogicFacade
     */
    public static ILogicFacade getLogic()
    {
        return logic;
    }

    /**
     * Getter method to get scene
     * @return Scene
     */
    public static Scene getScene()
    {
        return stage.getScene();
    }


    /**
     * Injector method for logic
     * @param logicLayer
     */
    public void injectLogic(ILogicFacade logicLayer)
    {
        logic = logicLayer;
    }

    /**
     * Method to launch the game
     */
    public void begin()
    {
        launch();
    }


}
