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
class GameHandler implements Serializable
{
    public static void saveGame(GameStateDTO gameStateDTO, String fileName)
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(gameStateDTO);
            fileOut.close();
            out.close();
        } catch (IOException i)
        {
            i.printStackTrace();
        }
    }

    public static GameStateDTO loadGame(GameStateDTO gameStateDTO, String fileName)
    {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            gameStateDTO = (GameStateDTO) in.readObject();
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

        return gameStateDTO;
    }


    
}
