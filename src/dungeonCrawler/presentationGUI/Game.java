package dungeonCrawler.presentationGUI;

import dungeonCrawler.aqu.ILogicFacade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Game extends Application
{
    private static ILogicFacade logic;

    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();

        MenuController controller =loader.getController();
        controller.injectLogic(logic);
        stage.getIcons().add(new Image("file:Swords.png"));
        stage.setTitle("Dungeon Crawler");
        stage.setScene(new Scene(root));
        stage.show();
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
