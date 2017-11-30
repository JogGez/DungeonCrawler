package dungeonCrawler.presentationGUI;

import dungeonCrawler.aqu.IGame;
import dungeonCrawler.aqu.ILogicFacade;
import dungeonCrawler.logic.LogicFacade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class Game extends Application implements IGame
{

    ILogicFacade logic;



    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StartScreen.fxml"));
        Parent root = loader.load();

        ILogicFacade logic = new LogicFacade();
        IGame controller =loader.getController();
        controller.injectLogic(logic);


        stage.setTitle("Dungeon Crawler");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void injectLogic(ILogicFacade logicLayer)
    {
        logic = logicLayer;
    }



    // Skal ændres, skal laves om til det samme som vores start i consoleGame.
    //Må ikke være begin eller start. 
    public void begin()
    {
        launch();
    }


}
