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
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.AudioClip;

public class SettingsController implements Initializable {

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

    private double effectVolume = 100;
    private double musicVolume = 100;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {

        lblEffectsVolume.setText("");
        lblMusicVolume.setText("");
        sldrEffectsVolume.setValue(effectVolume);
        sldrEffectsVolume.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val)
            {
                    lblEffectsVolume.setText(String.format("%.0f", new_val) + "%");
                    effectVolume = new_val.doubleValue();

            }
        });

        sldrMusicVolume.setValue(musicVolume);
        sldrMusicVolume.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val)
            {
                lblMusicVolume.setText(String.format("%.0f", new_val) + "%");
                Game.mediaPlayer.setVolume(new_val.doubleValue()/100);
                Game.mediaPlayer1.setVolume(new_val.doubleValue()/100);
//                PlayController.mediaPlayer.setVolume(new_val.doubleValue()/100);
                musicVolume = new_val.doubleValue();
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
}
