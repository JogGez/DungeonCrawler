/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.logic;

import java.awt.Point;

/**
 *
 * @author Computer
 */
public class GameConstants
{
    //Static because we will never change those values.
    private static Point mapSize = new Point (3,4);
     //number of contents in a room.
    private static int roomContents = 2;
    private static int playerHealth = 3000;
    private static int inventorySize = 3;
    private static int playerTime = 500;
    private static int playerPower = 50;
    private static int movingGuides = 2;

    
    
     public static void setEasyLevel()
    {
       mapSize = new Point (3,4);
       roomContents = 2;
       playerHealth = 6000;
       inventorySize = 3;
       playerTime = 200;
       playerPower = 100;
       movingGuides = 2;
    }

    public static Point getMapSize()
    {
        return mapSize;
    }

    public static void setMapSize(Point mapSize)
    {
        GameConstants.mapSize = mapSize;
    }

    public static int getRoomContents()
    {
        return roomContents;
    }

    public static void setRoomContents(int roomContents)
    {
        GameConstants.roomContents = roomContents;
    }

    public static int getPlayerHealth()
    {
        return playerHealth;
    }

    public static void setPlayerHealth(int playerHealth)
    {
        GameConstants.playerHealth = playerHealth;
    }

    public static int getInventorySize()
    {
        return inventorySize;
    }

    public static void setInventorySize(int inventorySize)
    {
        GameConstants.inventorySize = inventorySize;
    }

    public static int getPlayerTime()
    {
        return playerTime;
    }

    public static void setPlayerTime(int timeAmount)
    {
        GameConstants.playerTime = timeAmount;
    }

    public static int getPlayerPower()
    {
        return playerPower;
    }

    public static void setPlayerPower(int playerPower)
    {
        GameConstants.playerPower = playerPower;
    }
     
   public static int getMovingGuides()
    {
        return movingGuides;
    }

    public static void setMovingGuides(int movingGuides)
    {
        GameConstants.movingGuides = movingGuides;
    }
            
}
