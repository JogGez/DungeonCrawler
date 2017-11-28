/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonCrawler.data;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author Computer
 */
public class GameHandler
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
    }
        
    catch (IOException i)
    {
            i.printStackTrace();
    }
}
public static GameState loadGame(String fileName)
{
    return null;
}
    
}
