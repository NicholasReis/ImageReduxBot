import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;

public interface Filter{
    public WritableImage applyFilter(Image image1);
}
