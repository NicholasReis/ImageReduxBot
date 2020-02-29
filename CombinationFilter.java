import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;

public interface CombinationFilter{
    public WritableImage applyComboFilter(Image image1, Image image2);
}
