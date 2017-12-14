package dungeonCrawler.logic;

import java.io.Serializable;
import java.util.Random;

/**
 * Static special items
 * @author Peter Jonathan, Joakim, Linea, Frederik, Simon og Brian
 */
enum SpecialEnum implements Serializable
{
    TELEPORT (new Special("Teleport","Teleport to anywhere on the map",GameTextASCII.getTeleporter(),Special.Type.TELEPORT)),
    BOMB (new Special("BOMB","Lower the health of all enemies around you by 50% (Up, Down, Left, Right)",GameTextASCII.getBombSmall(),Special.Type.BOMB)),
    VISION (new Special("Vision","Show the content of all rooms around you",GameTextASCII.getGlasses(),Special.Type.VISION)),
    AWESOME_NAME (new Special("Awesome Name","Change your name to something AWESOME",GameTextASCII.getAwesomeName(),Special.Type.AWESOME_NAME)),
    EXTRA_SLOT (new Special("Extra Inventory Slot","Add another slot to your inventory",GameTextASCII.getStar(),Special.Type.EXTRA_SLOT));



    private final Special special;

    SpecialEnum (Special special)
    {
        this.special = special;
    }

    /**
     * Getter method for a special in enum
     * @return Special
     */
    public Special getSpecial()
    {
        return new Special(special.getName(), special.getDescription(), special.getAscii(), special.getType());
    }

    /**
     * Getter method for a random special in enum
     * @return Special
     */
    public static Special getRandomSpecial()
    {
        //Creates a reference to special s.
        // Contains a random merchant from the enum.
        Special s = values()[new Random().nextInt(values().length)].special;

        // Returns a new instances of special, where it uses the reference from specialEnum (s from above)
        // inheriet from Item.
        return new Special(s.getName(), s.getDescription(), s.getAscii(), s.getType());
    }
}
