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
     * Method for game handler to save current Game state
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
     * Method for game handler to load an existing game
     * @param gameStateDTO
     * @param fileName
     * @return GameStateDTO
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
