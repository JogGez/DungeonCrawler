package dungeonCrawler.aqu;

import java.io.Serializable;

public interface IPotion extends Serializable
{
    int getHealthRecovery();

    int getTimeRecovery();
}
