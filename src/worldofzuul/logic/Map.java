/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.logic;

import java.awt.*;
import java.util.ArrayList;

/**
 * The type Map
 */
public class Map
{
    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    private int height;
    private int width;
    private ArrayList<Room> roomList;

    public Map(int width, int height)
    {
        this.width = width;
        this.height = height;

        roomList = new ArrayList<>();

        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                roomList.add(new Room(new Point(x,y)));
            }
        }
    }

    public boolean roomExists(Point p)
    {

        for (Room room : roomList)
        {

            if (room.getLocation().x == p.x && room.getLocation().y == p.y)
            {
                return true;
            }
        }

        return false;
    }

    public ArrayList<Room> getRoomList()
    {
        return roomList;
    }
    
}
