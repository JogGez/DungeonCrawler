package dungeonCrawler.aqu;

public interface IGame
{
    void injectLogic(ILogicFacade logicLayer);
    void begin();
}
