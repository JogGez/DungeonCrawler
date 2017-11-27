package dungeonCrawler.aqu;


import java.awt.*;
import java.util.ArrayList;

public interface IMap
{
    int getHeight();

    int getWidth();

    int getNumberOfContent();

    boolean roomExists(Point exitPoint);

    void setRoomHasBeenEntered(Point playerLocation);

    boolean getRoomHasBeenEntered(Point p);

//    ArrayList<IRoom> getRoomList();

    int numberOfEnteredRooms();

    boolean isRoomLocked(Point checkPoint);
    
    void guideMove();

    void thiefMove();

    boolean hasAllRoomBeenEntered();

    String checkRoomContent(int index);

    IRoom getCurrentRoom();

    boolean roomContainsThief();

    boolean roomContainsGuide();

    void removeThief();

    void getItemFromGuide(int InventoryIndex);
}
