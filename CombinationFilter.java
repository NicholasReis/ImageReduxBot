import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;

public interface CombinationFilter{
    public WritableImage apply(Image image1, Image image2);
}
