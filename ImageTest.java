import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javafx.scene.image.ImageView;
/**
 * Write a description of JavaFX class ImageTest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ImageTest extends Application
{
    private Image img = new Image("res/horse.png");

    @Override
    public void start(Stage stage)
    {
        ImageView imgView = new ImageView();
        imgView.setImage(img);
        imgView.setFitHeight(300);
        imgView.setFitWidth(300);

        // Create a new grid pane
        StackPane pane = new StackPane(imgView);
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane, 300,100);
        stage.setTitle("JavaFX Example");
        stage.setScene(scene);
        stage.setWidth(350);
        stage.setHeight(350);
        // Show the Stage (window)
        stage.show();
    }
}
