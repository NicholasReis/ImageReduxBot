import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javafx.scene.image.WritableImage;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import java.io.File;
/**
 * Write a description of JavaFX class ImageTest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ImageTest extends Application
{
    private Image img1 = new Image("res/horse.png");
    private Image img2 = new Image("res/horse2.png");
    @Override
    public void start(Stage stage)
    {
        double imgH = 300;
        double imgW = 300;
        WritableImage wImg = new WritableImage((int)img1.getWidth(), (int)img1.getHeight());
        PixelReader pR = img1.getPixelReader();
        PixelReader pR2 = img2.getPixelReader();
        PixelWriter pW = wImg.getPixelWriter();

        for(int x = 0; x < (int)img1.getWidth(); x++){
            for(int y = 0; y < (int)img1.getHeight(); y++){
                if(y % 2 == 0){
                    pW.setArgb(x, y, pR.getArgb(x,y));
                }
            }
        }

        // Create a new grid pane
        ImageView img = new ImageView(wImg);
        img.setFitHeight(300);
        img.setFitWidth(300);
        try {
            // retrieve image
            File outputfile = new File("saved.png");
            BufferedImage bImage = SwingFXUtils.fromFXImage(img.getImage(), null);
            ImageIO.write(bImage, "png", outputfile);
        } catch (Exception e) {
        }
        StackPane pane = new StackPane(img);
        pane.setPadding(new Insets(10, 10, 10, 10));

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane, 300,100);
        stage.setTitle("JavaFX Example");
        stage.setScene(scene);
        stage.setWidth(imgW);
        stage.setHeight(imgH);
        // Show the Stage (window)
        stage.show();
    }
}
