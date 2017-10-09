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
    private ArrayList<Room> roomList;

    public Map(int width, int height)
    {

        roomList = new ArrayList<>();

        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                roomList.add(new Room(new Point(x,y)));
            }
        }
    }
    
}
