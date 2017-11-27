package dungeonCrawler.aqu;

import java.awt.*;

public interface IRoom
{
    int getNumberOfContent();

    // Setter of HasBeenEntered
    void setHasBeenEntered(boolean hasBeenEntered);

    // Getter of HasBeenEntered
    boolean getHasBeenEntered();

    String getName();

    String getDescription();

    IRoomContent getContent(int index);

    void removeContent(int index);

    Point getLocation();

    boolean isLocked();

    void setLocked(boolean locked);
}
