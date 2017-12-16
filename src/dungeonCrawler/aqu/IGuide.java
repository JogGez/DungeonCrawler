package dungeonCrawler.aqu;

import java.io.Serializable;

/**
 * IGuide interface
 */
public interface IGuide extends IRoomContent, Serializable
{
    String getName();

    String getDescription();

    String getAscii();
}
