package dungeonCrawler.aqu;

import java.awt.*;
import java.io.Serializable;

/**
 * IThief interface
 */
public interface IThief extends Serializable
{
    String getAscii();

    String getName();

    String getDescription();

    Point getLocation();
}
