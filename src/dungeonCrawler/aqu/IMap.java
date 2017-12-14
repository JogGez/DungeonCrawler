package dungeonCrawler.aqu;


import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public interface IMap extends Serializable
{
    int getHeight();

    int getWidth();

    int getNumberOfContent();

    boolean roomExists(Point exitPoint);

    void setRoomHasBeenEntered(Point playerLocation);

    boolean getRoomHasBeenEntered(Point p);

//    ArrayList<IRoom> getRoomList();

    ArrayList<? extends IRoom> getRoomList();

    int numberOfEnteredRooms();

    boolean isRoomLocked(Point checkPoint);
    
    void merchantMove();

    void thiefMove();

    boolean hasAllRoomBeenEntered();

    String checkRoomContent(int index);

    IRoom getCurrentRoom();

    boolean roomContainsThief();

    boolean roomContainsMerchant();

    void removeThief();

    void unlockRoom(Point location);

    IMerchant getMerchant();
}
