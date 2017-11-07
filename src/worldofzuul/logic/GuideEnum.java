package worldofzuul.logic;

import java.util.Random;
import worldofzuul.presentation.ASCII;

/**
 *
 * @author Linea Hoffmann
 */
public enum GuideEnum
{
    // TODO tiløje flere guides. 
    BUDDHA ( new Guide ("Buddha","The chubby and kind spiritual guide", ASCII.getBuddha())),
    JESUS (new Guide ("Jesus", "The mighty son of God", ASCII.getJesus() )),
    BABAR (new Guide("Babar", "The real jungle king", ASCII.getBabar()))
    ;
   
    private final Guide guide; 

    GuideEnum(Guide guide)
    {
        this.guide = guide;
    }

    public Guide getGuide()
    {
        return new Guide(guide.getName(),guide.getDescription(),guide.getAscii());
    }

    public static Guide getRandomGuide()
    {
        // Creates a reference to the guide, g.
        // Contains a random guide from the enum. 
        Guide g = values()[new Random().nextInt(values().length)].guide;
        
        // Returns a new instance of guide, where it uses the values from the reference guide g.
        return new Guide(g.getName(),g.getDescription(),g.getAscii());

    }
        
}

