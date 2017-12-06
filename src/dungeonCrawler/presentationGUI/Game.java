package dungeonCrawler.presentationGUI;

import dungeonCrawler.aqu.ILogicFacade;
import dungeonCrawler.logic.GameText;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


public class Game extends Application
{
    private static ILogicFacade logic;
    private static Stage stage;

    // Creating a static root to pass to the controller
    private static BorderPane root = new BorderPane();
    public static MenuBar menuBar;

    public static BorderPane getRoot()
    {
        return root;
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        this.stage = stage;

        MenuBar menuBar = FXMLLoader.load(getClass().getResource("MenuBar.fxml"));
        this.menuBar = menuBar;

        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        AnchorPane menu = menuLoader.load();

        root.setTop(menuBar);
        root.setCenter(menu);

        MainMenuController controller = menuLoader.getController();
        controller.injectLogic(logic);

        stage.getIcons().add(new Image("file:Swords.png"));
        stage.setTitle("Dungeon Crawler");
        stage.setMinWidth(1280);
        stage.setMinHeight(768);
        stage.setScene(new Scene(root));
        stage.show();
    }

//    public static void changeScene(String fxml) throws IOException
//    {
//        FXMLLoader loader = new FXMLLoader(Game.class.getResource(fxml));
//        Parent root = loader.load();
//
//
//        stage.setScene(new Scene(root));
//    }

    public static void switchScene(String fxml) {

        try {

            AnchorPane center = FXMLLoader.load(Game.class.getResource(fxml));

            BorderPane border = Game.getRoot();
            border.setCenter(center);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static ILogicFacade getLogic()
    {
        return logic;
    }


    public void injectLogic(ILogicFacade logicLayer)
    {
        logic = logicLayer;
    }

    public void begin()
    {
        launch();
    }


}
