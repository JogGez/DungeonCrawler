package dungeonCrawler.aqu;

import java.io.IOException;
import java.io.Serializable;

/**
 * IGame interface
 */
public interface IGame extends Serializable
{
    void injectLogic(ILogicFacade logicLayer) throws IOException;

}
