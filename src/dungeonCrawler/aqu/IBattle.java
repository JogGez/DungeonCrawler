package dungeonCrawler.aqu;

import java.io.Serializable;

public interface IBattle extends Serializable
{
    // Getter method for isBattleOver
    boolean getIsBattleOver();

    //TODO Kig på metoden. ifh til retunering af String.
    //Method start, returns a String
    String start();
}
