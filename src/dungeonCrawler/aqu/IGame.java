package dungeonCrawler.aqu;

import java.io.IOException;
import java.io.Serializable;

public interface IGame extends Serializable
{
    void injectLogic(ILogicFacade logicLayer) throws IOException;

}
