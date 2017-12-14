package dungeonCrawler.logic;

import java.io.Serializable;
import java.util.Random;

/**
 * Static merchants
 * @author Peter Jonathan, Joakim, Linea, Frederik, Simon og Brian.
 */
enum MerchantEnum implements Serializable
{
    // TODO tiløje flere merchants.
    BUDDHA ( new Merchant("Buddha", "The chubby and kind spiritual merchant", GameTextASCII.getBuddha())),
    JESUS (new Merchant("Jesus", "The mighty son of God", GameTextASCII.getJesus())),
    BABAR (new Merchant("Babar", "The real jungle king", GameTextASCII.getBabar()))
    ;
   
    private final Merchant merchant;

    MerchantEnum(Merchant merchant)
    {
        this.merchant = merchant;
    }

    /**
     * Getter method for a merchant in the enum
     * @return Merchant
     */
    public Merchant getMerchant()
    {
        return new Merchant(merchant.getName(), merchant.getDescription(), merchant.getAscii());
    }

    /**
     * Getter method to get a random merchant from the enum
     * @return Merchant
     */
    public static Merchant getRandomMerchant()
    {
        // Creates a reference to the merchant, g.
        // Contains a random merchant from the enum.
        Merchant m = values()[new Random().nextInt(values().length)].merchant;
        
        // Returns a new instance of merchant, where it uses the values from the reference merchant g.
        return new Merchant(m.getName(), m.getDescription(), m.getAscii());

    }
        
}

