package dungeonCrawler.aqu;

import java.io.Serializable;

public interface IChest extends Serializable
{
    IItem getItem();

    String getAscii();

    String getName();

    String getDescription();
}
