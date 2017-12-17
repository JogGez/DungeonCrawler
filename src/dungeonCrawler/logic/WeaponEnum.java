package dungeonCrawler.logic;

import java.io.Serializable;
import java.util.Random;

/**
 *  Static weapons for the game
 *  implements Serializable
 * @author Peter Jonathan, Joakim, Linea, Frederik, Simon og Brian
 */
enum WeaponEnum implements Serializable
{
    EXCALIBUR (new Weapon("Excalibur", "The Sword of King Arthur", GameTextASCII.getSword(), 100, 2)),
    DURANDAL ( new Weapon("Durandal", "Sword of Roland, legendary paladin of Charlemagne", GameTextASCII.getSword(), 35, 1)),
    LEGBITER ( new Weapon("Legbiter", "The Sword of Viking King Magnus III", GameTextASCII.getSword(), 40, 1)),
    THE_HOLY_LANCE (new Weapon("The Holy Lance", "Also known as the Spear of Destiny and the Lance of Longinus, was the spear that allegedly pierced the side of Jesus during the crucifixion. ", GameTextASCII.getSword(), 50, 2)),
    GOUJIAN(new Weapon("Goujian", "Emperor Goujian, King of Yue States Sword", GameTextASCII.getSword(), 35, 1)),
    MJÖLNIR (new Weapon("Mjölnir", "The Mighty Hammer of Thor", GameTextASCII.getSword(), 45, 1)),
    FLOPPY_FISH (new Weapon("Floppy Fish", "Floppy Fish from the sea :(", GameTextASCII.getSword(), 1, 1))
    ;
    
    private final Weapon weapon;

    WeaponEnum (Weapon weapon)
    {
        this.weapon = weapon;
    }
    
    /**
     * Getter method for a weapon in enum
     * @return Weapon
     */
    public Weapon getWeapon()
    {
        return new Weapon(weapon.getName(), weapon.getDescription(), weapon.getAscii(), weapon.getPower() + GameSettings.getPlayerPower(), weapon.getMultiplier());
    }
    
    /**
     * Getter method for a random weapon in enum
     * @return Weapon
     */
    public static Weapon getRandomWeapon()
    {
        //Creates a reference to weapon w. 
        // Contains a random merchant from the enum.
        Weapon w = values()[new Random().nextInt(values().length)].weapon;
        
        // Returns a new instances of weapon, where it uses the reference from weaponEnum (w from above)
        // inheriet from Item. 
        return new Weapon(w.getName(), w.getDescription(), w.getAscii(), w.getPower() + GameSettings.getPlayerPower(), w.getMultiplier());
    }
}

