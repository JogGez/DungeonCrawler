package dungeonCrawler.logic;

import dungeonCrawler.aqu.IRoomContent;

/**
 * The type Guide.
 *
 * @author Jonathan & Linea
 */

// Guide class, the old helper that doesn't move
class Guide implements IRoomContent, dungeonCrawler.aqu.IGuide
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
    @Override
    public String getName() {
        return name;
    }


    /**
     * Gets description.
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return description;
    }
    
    /**
     * gets ASCII
     * @return String
     */
    @Override
    public String getAscii()
    {
        return ascii;
    }

}
