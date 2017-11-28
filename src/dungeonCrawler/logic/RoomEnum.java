package dungeonCrawler.logic;

import java.io.Serializable;
import java.util.Random;

/**
 * 
 * @author 
 */
enum RoomEnum implements Serializable
{
    CELLAR ("Cellar", "Filled with old dusty skeletons"),
    BUTCHERY ("Buthery", "Bloody gory scenery"),
    TORTURE ("Torture Chamber", "All sorts of random objects"),
    THRONE ("Throne Room", "Big dusty old throne"),
    WEAPONQUARTER ("Weapon Quarter", "Rusty weapon racks")
    ;

    private final String name;
    private final String description;

    RoomEnum(String name, String description)
    {
        this.name = name;
        this.description = description;
    }
     
    public static String[] getRandomString()
    {
        //Values all the values in the enum. 
        int randomNumber = new Random().nextInt(values().length); // gives a random number
        // retruns name and description from values (values is the enums array of contens). 
        return new String[]{values()[randomNumber].name,values()[randomNumber].description};
    }
    
    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }
    
}

