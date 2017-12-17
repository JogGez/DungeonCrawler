package dungeonCrawler.presentationGUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.AudioClip;
import jdk.nashorn.internal.ir.IfNode;

/**
 * Class to change settings. Sounds in the game etc.
 * extends LoadController
 * implements Initializable
 */
public class SettingsController extends LoadController implements Initializable
{

    @FXML
    private Slider sldrSound;
    @FXML
    private Label lblSoundVolume;
    @FXML
    private Button btnBack;
    @FXML
    private Label lblEffectsVolume;
    @FXML
    private Slider sldrEffectsVolume;
    @FXML
    private Label lblMusicVolume;
    @FXML
    private Slider sldrMusicVolume;

    private static double effectVolume = 1;
    private static double musicVolume = 1;
    private static boolean skipAnimation = false;
    @FXML
    private CheckBox cbxSkipAnimation;

    /**
     * The initialize method used for when the controller is first initialized
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {

        lblEffectsVolume.setText("");
        lblMusicVolume.setText("");
        sldrEffectsVolume.setValue(effectVolume*100);
        sldrEffectsVolume.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val)
            {
                    lblEffectsVolume.setText(String.format("%.0f", new_val) + "%");
                    effectVolume = new_val.doubleValue()/100;

            }
        });

        sldrMusicVolume.setValue(musicVolume*100);
        sldrMusicVolume.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val)
            {
                lblMusicVolume.setText(String.format("%.0f", new_val) + "%");
                Game.mediaPlayer.setVolume(new_val.doubleValue()/100);
                Game.mediaPlayer1.setVolume(new_val.doubleValue()/100);
//                PlayController.mediaPlayer.setVolume(new_val.doubleValue()/100);
                musicVolume = new_val.doubleValue()/100;
            }
        });
    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException 
    {
        Game.switchScene("MainMenu.fxml");
        AudioClip soundMyNoise = new AudioClip(new File("Resources\\sounds\\click.mp3").toURI().toString());
        soundMyNoise.setVolume(1);
        soundMyNoise.play();
    }

    public static double getEffectVolume()
    {
        return effectVolume;
    }

    public static double getMusicVolume()
    {
        return musicVolume;
    }

    public static boolean getSkipAnimation()
    {
        return skipAnimation;
    }

    @FXML
    private void handleSkipAnimation(ActionEvent actionEvent)
    {
        if (cbxSkipAnimation.isSelected())
        {
            skipAnimation = true;
        }
        else
        {
            skipAnimation = false;
        }
    }
}
