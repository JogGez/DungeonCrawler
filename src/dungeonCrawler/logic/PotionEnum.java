package dungeonCrawler.logic;

import dungeonCrawler.aqu.IPotion;

import java.io.Serializable;
import java.util.Random;

/**
 * Static potions for the game
 * implements Serializable
 * @author Morten Bindslev
 */
enum PotionEnum implements Serializable
{
    HEALTHPOTION1 (new Potion("Health Potion", "Health Potion from the mighty wizard Merlin.", GameTextASCII.getPotion(), 50, 0)),
    HEALTHPOTION2 (new Potion("Health Potion", "Health Potion from the mighty wizard Gandalf.", GameTextASCII.getPotion(), 75, 0)),
    HEALTHPOTION3 (new Potion("Health Potion", "Health Potion from the mighty wizard Albus Dumbledore.", GameTextASCII.getCoke(), 100, 0)),
    
    TIMEPOTION1 (new Potion("Time Potion", "Time Potion from the mighty wizard Merlin.", GameTextASCII.getPotion(), 0, 30)),
    TIMEPOTION2 (new Potion("Time Potion", "Time Potion from the mighty wizard Gandalf.", GameTextASCII.getPotion(), 0, 45)),
    TIMEPOTION3 (new Potion("Time Potion", "Time Potion from the mighty wizard Albus Dumbledore.", GameTextASCII.getPotion(), 0, 60))
    ;

    private final Potion potion;


    PotionEnum(Potion potion)
    {
        this.potion = potion;
    }

    /**
     * Getter method to get a potion from enum
     * @return  Potion
     */
    public Potion getPotion()
    {
        return new Potion(potion.getName(), potion.getDescription(), potion.getAscii(), potion.getHealthRecovery(), potion.getTimeRecovery());
    }

    /**
     * Getter method to get random potion from enum
     * @return Potion
     */
    public static Potion getRandomPotion()
    {
        Potion potionValues = values()[new Random().nextInt(values().length)].potion;
        return new Potion(potionValues.getName(), potionValues.getDescription(), potionValues.getAscii(), potionValues.getHealthRecovery(), potionValues.getTimeRecovery());
    }

    /**
     * Getter method to get random health potion from enum
     * @return Potion
     */
    public static Potion getRandomHealthPotion()
    {
        Potion potionValues = values()[new Random().nextInt(values().length-3)].potion;
        return new Potion(potionValues.getName(), potionValues.getDescription(), potionValues.getAscii(), potionValues.getHealthRecovery(), potionValues.getTimeRecovery());
    }

    /**
     * Getter method to get random time potion from enum
     * @return Potion
     */
    public static Potion getRandomTimePotion()
    {
        Potion potionValues = values()[new Random().nextInt(values().length-3)+3].potion;
        return new Potion(potionValues.getName(), potionValues.getDescription(), potionValues.getAscii(), potionValues.getHealthRecovery(), potionValues.getTimeRecovery());
    }
}
