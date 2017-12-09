package dungeonCrawler.presentationGUI;

        import dungeonCrawler.aqu.ILogicFacade;
        import dungeonCrawler.logic.GameText;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.TextArea;
        import javafx.stage.Stage;
        import java.io.IOException;
        import java.net.URL;
        import java.util.ResourceBundle;

public class HighscoreController implements Initializable
{
    private ILogicFacade logic;
    private GameText gameText;
    @FXML
    public TextArea textAreaHighScore;
    @FXML
    private Button btnBack;

    @FXML
    public void handleBack(ActionEvent event) throws IOException
    {
        Game.switchScene("MainMenu.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.logic = Game.getLogic();
        this.gameText = logic.getGameText();
        textAreaHighScore.setText(gameText.getHighScore());
    }
}
