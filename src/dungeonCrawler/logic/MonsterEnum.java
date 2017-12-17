package dungeonCrawler.logic;

import java.io.Serializable;
import java.util.Random;

/**
 * Static monsters for the game
 * @author Peter Jonathan, Joakim, Linea, Frederik, Simon og Brian.
 */
enum MonsterEnum implements Serializable
{
    SPIDER  (new Monster("Shelob", "Web crawler that will suck your body dry.", GameTextASCII.getSpider(), 75, ((int)(Math.random()*7+3)))),
    BAT (new Monster("Shikaka", "The white flying bat.", GameTextASCII.getBat(), 25, ((int)(Math.random()*4+1)))),  //calls constructor with value 2
    MERMAID (new Monster("Ariel", "Beautiful creature with a lust for blood.", GameTextASCII.getMermaid(), 100, ((int)(Math.random()*10+5)))),
    UNICORN (new Monster("Unicorn", "Fabled horse with a piercing horn, perfect for killing.", GameTextASCII.getUnicorn(), 100, ((int)(Math.random()*10+5)))),
    HAMSTER (new Monster("Hamster", "Don't be fooled by its furry look, its a rapid killer.", GameTextASCII.getHamster(), 5, 1)),
    LANDSHARK (new Monster("Land Shark", "The infamous land shark with rows upon rows of deadly sharp teeth.", GameTextASCII.getShark(), 125, ((int)(Math.random()*12+5)))),
    CUPID (new Monster("Cupid", "This little devil is the root of all evil.", GameTextASCII.getCupid(), 15, ((int)(Math.random()*2+1)))),
    SANTA (new Monster("Santa Claus", "The biggest lie you tell your children.", GameTextASCII.getSanta(), 150, ((int)(Math.random()*12+7)))),
    WHALE (new Monster("Moby Dick", "The great white whale from the famous Herman Melville book.", GameTextASCII.getWhale(), 300, ((int)(Math.random()*7+5)))),
    MERMAID2 (new Monster("Mermaid nr. 2", "This even more evil sister of Ariel.", GameTextASCII.getMermaid2(), 150, ((int)(Math.random()*10+5)))),
    RABBIT (new Monster("Killer Rabbit of Caerbannog", "Rabbit of Caerbannog from the AWSOME movie \"Monty Python and the Holy Grail.\"", GameTextASCII.getRabbit(), 100, ((int)(Math.random()*10+5)))),
    SHEEP (new Monster("Sheep", "Simple easy to kill sheep.", GameTextASCII.getSheep(), 10, ((int)(Math.random()*5+1)))),
    GRIM (new Monster("The Grim Reaper", "Death is coming to collect you soul.", GameTextASCII.getGrim(), 175, ((int)(Math.random()*10+10)))),
    SEAHORSE (new Monster("Seahorse", "It may look cute & sweet, but don't be fooled.", GameTextASCII.getSeaHorse(), 10, ((int)(Math.random()*3+1)))),
    TEDDY (new Monster("Teddy", "", GameTextASCII.getTeddyBear(), 10, ((int)(Math.random()*3+1)))),
    JACKINABOX (new Monster("Jack In A Box", "", GameTextASCII.getJackInABox(), 10, ((int)(Math.random()*3+1)))),
    DUMBO (new Monster("Dumbo", "", GameTextASCII.getDumbo(), 50, ((int)(Math.random()*4+2)))),
    FAIRY (new Monster("Fairy", "", GameTextASCII.getFairy(), 10, ((int)(Math.random()*3+1)))),
    KNIGHT1 (new Monster("Dark Knight", "", GameTextASCII.getKnight1(), 120, ((int)(Math.random()*10+5)))),
    KNIGHT2 (new Monster("Darker Knight", "", GameTextASCII.getKnight2(), 140, ((int)(Math.random()*15+7)))),
    PIKACHU (new Monster("Pikachu", "", GameTextASCII.getPikachu(), 60, ((int)(Math.random()*8+4)))),
    FATDRAGON (new Monster("Fat Dragon", "", GameTextASCII.getFatDragon(), 200, ((int)(Math.random()*10+5)))),
    CENTUAR (new Monster("Posh Centuar", "", GameTextASCII.getCentuar(), 250, ((int)(Math.random()*10+20)))),
    ALIEN (new Monster("Alien", "", GameTextASCII.getAlien(), 42, ((int)(Math.random()*10+5)))),
    GHOST (new Monster("Slimer", "The Jar Jar Binks of the Ghostbusters universe.", GameTextASCII.getGhost(), 90, ((int)(Math.random()*10+3)))),
    CYCLOPS (new Monster("Cyclops", "", GameTextASCII.getCyclops(), 200, ((int)(Math.random()*10+20))))
    ; // semicolon needed when fields / methods follow

    private final Monster monster;

    MonsterEnum(Monster monster)
    {
        this.monster = monster;
    }

    /**
     * Getter method to get monster from enum
     * @return Monster
     */
    public Monster getMonster()
    {
        return new Monster(monster.getName(), monster.getDescription(), monster.getAscii(), monster.getHealth() + GameSettings.getMonsterHealth(), monster.getPower() + GameSettings.getMonsterPower());
    }

    /**
     * Getter method to get random monster from enum
     * @return Monster
     */
    public static Monster getRandomMonster()
    {
        // Picks a random monster from our Enum.
        Monster monsterValues = values()[new Random().nextInt(values().length)].monster;

        // And here we return a new instance of our merchant from our Enumlist. Where it uses the reference MonsterEnum monsterValues
        return new Monster(monsterValues.getName(), monsterValues.getDescription(), monsterValues.getAscii(), monsterValues.getHealth() + GameSettings.getMonsterHealth(), monsterValues.getPower() + GameSettings.getMonsterPower());
    }

    /**
     * Getter method to get monster Lucifer from enum
     * @return Monster
     */
    public static Monster getLucifer()
    {
        // And here we return a new instance of our merchant from our Enumlist. Where it uses the reference MonsterEnum monsterValues
        return new Monster("Lucifer", "The LORD of the Underworld, The one true evil.", GameTextASCII.getDevil3(), 1000 + GameSettings.getLuciferHealth(), 50 + GameSettings.getLuciferPower());
    }
}
