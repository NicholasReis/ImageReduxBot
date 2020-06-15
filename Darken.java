import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
public class Darken implements Filter{
    public WritableImage apply(Image image1){
        WritableImage wImg = new WritableImage((int)image1.getWidth(), (int)image1.getHeight());
        PixelWriter pW = wImg.getPixelWriter();
        PixelReader pR = image1.getPixelReader();
        
        for(int x = 0; x < (int)image1.getHeight()-1; x++){
            for(int y = 0; y < (int)image1.getWidth()-1; y++){
                Color c = pR.getColor(y,x);
                pW.setColor(y,x, c.darker());
            }
        }
        
        return wImg;
    }
}
