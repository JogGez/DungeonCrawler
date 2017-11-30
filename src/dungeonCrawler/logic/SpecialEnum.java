package dungeonCrawler.logic;

import java.io.Serializable;
import java.util.Random;

enum SpecialEnum implements Serializable
{
    TELEPORT (new Special("Teleport","Teleport to anywhere on the map",GameTextASCII.getJesus(),Special.Type.TELEPORT)),
    BOMB (new Special("BOMB","Lower the health of all enemies around you by 50% (Up, Down, Left, Right)",GameTextASCII.getJesus(),Special.Type.BOMB)),
    AWESOME_NAME (new Special("Awesome Name","Change to name to something AWESOME",GameTextASCII.getJesus(),Special.Type.AWESOME_NAME));

    private final Special special;

    SpecialEnum (Special special)
    {
        this.special = special;
    }

    /**
     *
     * @return object
     */
    public Special getSpecial()
    {
        return new Special(special.getName(), special.getDescription(), special.getAscii(), special.getType());
    }

    /**
     *
     * @return random weapon
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
