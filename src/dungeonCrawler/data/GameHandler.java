package dungeonCrawler.data;


import java.io.*;

/**
 * GameHandler Class
 *
 * This Class handles saving and loading the game using Serialization
 *
 * Implements Serializable
 * @author Peter Jonathan, Joakim, Linea, Frederik, Simon og Brian
 */
class GameHandler implements Serializable
{
    /**
     * This method
     * @param gameStateDTO
     * @param fileName
     */
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

    /**
     *
     * @param gameStateDTO
     * @param fileName
     * @return
     */
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
