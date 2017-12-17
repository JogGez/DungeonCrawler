package dungeonCrawler.aqu;

import java.io.Serializable;

/**
 * IChest interface
 */
public interface IChest extends Serializable
{
    IItem getItem();

    String getAscii();

    String getName();

    String getDescription();
}
