package dungeonCrawler.aqu;

import java.io.Serializable;

/**
 * IPotion interface
 */
public interface IPotion extends Serializable
{
    int getHealthRecovery();

    int getTimeRecovery();
}
