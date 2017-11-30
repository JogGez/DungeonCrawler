/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonCrawler.data;


import java.io.*;

/**
 *
 * @author Computer
 */
public class GameHandler implements Serializable
{
    public static void saveGame(GameState gameState, String fileName)
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(gameState);
            fileOut.close();
            out.close();
            //TODO
            System.out.println("Filen er gemt");
        } catch (IOException i)
        {
            i.printStackTrace();
        }
    }

    public static GameState loadGame(GameState gameState, String fileName)
    {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            gameState = (GameState) in.readObject();
            in.close();
            fileIn.close();

        } catch (IOException i)

        {
            i.printStackTrace();

        } catch (ClassNotFoundException c)

        {
            System.out.println("Player not found");
            c.printStackTrace();
        }

        System.out.println(gameState.getMap().checkRoomContent(1));
        System.out.println(gameState.map.checkRoomContent(1));
        return gameState;
    }
    
}
