
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
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
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(10);

        pane.add(imgView, 0, 0);

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
