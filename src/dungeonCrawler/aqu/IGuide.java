package dungeonCrawler.aqu;

import java.io.Serializable;

public interface IGuide extends IRoomContent, Serializable
{
    String getName();

    String getDescription();

    String getAscii();
}
