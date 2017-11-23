/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonCrawler.logic;

import dungeonCrawler.aqu.IKey;
import java.util.Random;

/**
 *
 * @author Linea Hoffmann
 */
public enum KeyEnum
{
    KEY1 (new Key ("The Normal Key", "The key to unlock the all locked rooms", GameTextASCII.getKey())),
    KEY2 (new Key("The Sweet Key", "The key to unlock all your childhood memories", GameTextASCII.getKey2())),
    KEY3 (new Key ("The Awsome Key", "The key to unlock all the rooms, and whishes", GameTextASCII.getKey3()))
    ;
    
    private final Key key;
    
    KeyEnum(Key key)
    {
        this.key = key;
    }

    public Key getKey()
    {
        return new Key(key.getName(), key.getDescription(), key.getAscii());
    }

    public static Key getRandomKey()
    {
        Key keyValues = values()[new Random().nextInt(values().length)].key;
        return new Key(keyValues.getName(), keyValues.getDescription(), keyValues.getAscii());
                
    }
    
    
}
