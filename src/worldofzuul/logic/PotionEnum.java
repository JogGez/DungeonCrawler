package worldofzuul.logic;

import worldofzuul.presentation.ASCII;
import java.util.Random;
/**
 *
 * @author Morten Bindslev
 */
public enum PotionEnum 
{
    POTION1 (new Potion("Potion","Health Potion from the mighty wizard Merlin.", ASCII.getPotion(),50)),
    POTION2 (new Potion("Potion","Health Potion from the mighty wizard Gandalf.", ASCII.getPotion(),75)),
    POTION3 (new Potion("Potion","Health Potion from the mighty wizard Albus Dumbledore.", ASCII.getPotion(),100))
    ;

    private final Potion potion;

    PotionEnum(Potion potion)
    {
        this.potion = potion;
    }

    public Potion getPotion()
    {
        return new Potion(potion.getName(),potion.getDescription(),potion.getAscii(),potion.getHealthRecovery());
    }

    public static Potion getRandomPotion()
    {
        Potion potionValues = values()[new Random().nextInt(values().length)].potion;
        return new Potion(potionValues.getName(),potionValues.getDescription(),potionValues.getAscii(),potionValues.getHealthRecovery());
    }


}
