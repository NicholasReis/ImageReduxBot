import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.PixelReader;

public class LineByLineCombine implements CombinationFilter{
    public WritableImage applyComboFilter(Image image1, Image image2){
        WritableImage wImg = new WritableImage((int)image1.getWidth(), (int)image1.getHeight());
        PixelReader pR = image1.getPixelReader();
        PixelReader pR2 = image2.getPixelReader();
        PixelWriter pW = wImg.getPixelWriter();

        for(int x = 0; x < (int)image1.getWidth(); x++){
            for(int y = 0; y < (int)image1.getHeight(); y++){
                if(y % 2 == 0){
                    pW.setArgb(x, y, pR.getArgb(x,y));
                }else{
                    pW.setArgb(x, y, pR2.getArgb(x,y));
                }
            }
        }
        // Create a new grid pane
        return wImg;
    }
}
