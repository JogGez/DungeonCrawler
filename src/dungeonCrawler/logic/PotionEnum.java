package dungeonCrawler.logic;

import dungeonCrawler.aqu.IPotion;
import dungeonCrawler.presentation.ASCII;

import java.util.Random;

/**
 *
 * @author Morten Bindslev
 */
public enum PotionEnum
{
    HEALTHPOTION1 (new Potion("Health Potion", "Health Potion from the mighty wizard Merlin.", ASCII.getPotion(), 50, 0)),
    HEALTHPOTION2 (new Potion("Health Potion", "Health Potion from the mighty wizard Gandalf.", ASCII.getPotion(), 75, 0)),
    HEALTHPOTION3 (new Potion("Health Potion", "Health Potion from the mighty wizard Albus Dumbledore.", ASCII.getPotion(), 100, 0)),
    
    TIMEPOTION1 (new Potion("Time Potion", "Time Potion from the mighty wizard Merlin.", ASCII.getPotion(), 0, 30)),
    TIMEPOTION2 (new Potion("Time Potion", "Time Potion from the mighty wizard Gandalf.", ASCII.getPotion(), 0, 45)),
    TIMEPOTION3 (new Potion("Time Potion", "Time Potion from the mighty wizard Albus Dumbledore.", ASCII.getPotion(), 0, 60))
    ;

    private final Potion potion;

    PotionEnum(Potion potion)
    {
        this.potion = potion;
    }

    public IPotion getPotion()
    {
        return new Potion(potion.getName(), potion.getDescription(), potion.getAscii(), potion.getHealthRecovery(), potion.getTimeRecovery());
    }

    public static IPotion getRandomPotion()
    {
        Potion potionValues = values()[new Random().nextInt(values().length)].potion;
        return new Potion(potionValues.getName(), potionValues.getDescription(), potionValues.getAscii(), potionValues.getHealthRecovery(), potionValues.getTimeRecovery());
    }


}
