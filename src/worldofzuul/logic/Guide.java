package worldofzuul.logic;

import worldofzuul.presentation.ASCII;

/**
 * The type Guide.
 *
 * @author Jonathan & Linea
 */

// Guide class, the old helper that doesn't move
public class Guide implements RoomContent
{
    private String name;
    private String description;
    
    
    private String ascii;

    /**
     * Instantiates a new Guide.
    */
    
    public Guide()
            {
                
            }
    public Guide(String name, String description, String ascii)
    {
        this.name = name;
        this.ascii = ascii;
        this.description = description;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }


    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * gets ASCII
     * @return String
     */
    public String getAscii()
    {
        return ascii;
    }

}
