package dungeonCrawler.aqu;

import java.io.Serializable;

/**
 * IHighScore interface
 */
public interface IHighScore extends Serializable
{
    void writeText();

    void readText();

    String[] getHighScoreArray();

    void addHighScore(IPlayer player);
}
