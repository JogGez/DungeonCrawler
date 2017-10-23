
package worldofzuul;

import worldofzuul.presentation.Game;

/**
 * The type Main.
 */
public class Main 
{
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) 
    {
        // Create a new instance of the Game class
        Game game = new Game();
        // Call the play method of the game instance
        game.start();
    }
}
