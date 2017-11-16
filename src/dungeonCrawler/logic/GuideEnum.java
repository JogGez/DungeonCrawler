package dungeonCrawler.logic;

import dungeonCrawler.aqu.IGuide;
import dungeonCrawler.presentation.ASCII;

import java.util.Random;

/**
 *
 * @author Linea Hoffmann
 */
enum GuideEnum
{
    // TODO tiløje flere guides. 
    BUDDHA ( new Guide("Buddha", "The chubby and kind spiritual guide", ASCII.getBuddha())),
    JESUS (new Guide("Jesus", "The mighty son of God", ASCII.getJesus() )),
    BABAR (new Guide("Babar", "The real jungle king", ASCII.getBabar()))
    ;
   
    private final IGuide guide;

    GuideEnum(IGuide guide)
    {
        this.guide = guide;
    }

    public IGuide getGuide()
    {
        return new Guide(guide.getName(), guide.getDescription(), guide.getAscii());
    }

    public static IGuide getRandomGuide()
    {
        // Creates a reference to the guide, g.
        // Contains a random guide from the enum. 
        IGuide g = values()[new Random().nextInt(values().length)].guide;
        
        // Returns a new instance of guide, where it uses the values from the reference guide g.
        return new Guide(g.getName(), g.getDescription(), g.getAscii());

    }
        
}

