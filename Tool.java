import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;

public interface Tool{
    public WritableImage apply(Image image);
}
