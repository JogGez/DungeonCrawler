package dungeonCrawler.logic;

import java.io.Serializable;
import java.util.Random;

/**
 * KeyEnum implements Serializable
 * The KeyEnum contains the three keys that are used. 
 * Has a name, description, and ASCII 
 * @author Linea Hoffmann
 */
enum KeyEnum implements Serializable
{
    KEY1 (new Key ("The Normal Key", "The key to unlock the all locked rooms", GameTextASCII.getKey())),
    KEY2 (new Key("The Sweet Key", "The key to unlock all your childhood memories", GameTextASCII.getKey2())),
    KEY3 (new Key ("The Awesome Key", "The key to unlock all the rooms, and whishes", GameTextASCII.getKey3()))
    ;
    
    private final Key key;
    
    /**
     * Get one key from the enum
     * @param key 
     */
    KeyEnum(Key key)
    {
        this.key = key;
    }

    /**
     * getter method for key
     * @return Key
     */
    public Key getKey()
    {
        return new Key(key.getName(), key.getDescription(), key.getAscii());
    }

    /**
     * getRandomkKey 
     * @return 
     */
    public static Key getRandomKey()
    {
        Key keyValues = values()[new Random().nextInt(values().length)].key;
        return new Key(keyValues.getName(), keyValues.getDescription(), keyValues.getAscii());
                
    }
    
    
}
