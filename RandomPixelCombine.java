import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.PixelReader;
import java.util.Random;

public class RandomPixelCombine implements CombinationFilter{
    public WritableImage applyComboFilter(Image image1, Image image2){
        PixelReader pR1 = image1.getPixelReader();
        PixelReader pR2 = image2.getPixelReader();
        WritableImage wImg = new WritableImage((int)image1.getWidth(), (int)image1.getHeight());
        PixelWriter pW = wImg.getPixelWriter();

        for(int x = 0; x < 1000; x++){
            for(int y = 0; y < 1000; y++){
                Random rand = new Random();
                int pullFrom = rand.nextInt(2);
                if(pullFrom == 0){
                    pW.setArgb(x, y, pR1.getArgb(x,y));
                }else{
                    pW.setArgb(x, y, pR2.getArgb(x,y));
                }

            }
        }

        // Create a new grid pane
        return wImg;
    }
}
