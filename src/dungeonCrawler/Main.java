
package dungeonCrawler;

import dungeonCrawler.aqu.IDataFacade;
import dungeonCrawler.aqu.ILogicFacade;
import dungeonCrawler.data.DataFacade;
import dungeonCrawler.logic.LogicFacade;
//import dungeonCrawler.presentationConsole.Game;
import dungeonCrawler.presentationGUI.Game;

/**
 * The type Main.
 */
public class Main
{
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args)
    {
        ILogicFacade logic = new LogicFacade();
        IDataFacade data = new DataFacade();
        logic.injectData(data);
//comment1
        // Create a new instance of the Game class
        Game game = new Game();

        game.injectLogic(logic);
        // Call the begin method of the game instance
        game.begin();
    }
}
