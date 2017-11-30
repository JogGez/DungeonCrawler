package dungeonCrawler.aqu;

import java.io.Serializable;

public interface IGame extends Serializable
{
    void injectLogic(ILogicFacade logicLayer);

}
