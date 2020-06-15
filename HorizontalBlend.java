
import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.PixelReader;
import java.util.Random;

public class HorizontalBlend implements CombinationFilter{
    public WritableImage apply(Image image1, Image image2){
        WritableImage wImg = new WritableImage((int)image1.getWidth(), (int)image1.getHeight());
        PixelReader pR = image1.getPixelReader();
        PixelReader pR2 = image2.getPixelReader();
        PixelWriter pW = wImg.getPixelWriter();

        Random rand = new Random();

        int height = limitingHeight(image1, image2);
        int width = limitingWidth(image1, image2);
        for(int x = 0; x < height; x++){
            double heightP = -6*(((((-x)+((double)height/2))*((-x)+(double)height/2)))/(double)height) + (double)height/2;
            if(x < height/2){
                if(rand.nextDouble()< heightP/height){
                    for(int y = 0; y < width; y++){
                        pW.setArgb(y, x, pR.getArgb(y,x));
                    }
                }else{
                    for(int y = 0; y < width; y++){
                        pW.setArgb(y, x, pR2.getArgb(y,x));
                    }
                }
            }else{
                if(rand.nextDouble()< heightP/height){
                    for(int y = 0; y < width; y++){
                        pW.setArgb(y, x, pR2.getArgb(y,x));
                    }
                }else{
                    for(int y = 0; y < width; y++){
                        pW.setArgb(y, x, pR.getArgb(y,x));
                    }
                }
            }
        }
        // Create a new grid pane
        return wImg;
    }

    public int limitingHeight(Image image1, Image image2){
        if((int)image1.getHeight() > (int)image2.getHeight()){
            return (int)image2.getHeight();
        }else{
            return (int)image1.getHeight();
        }
    }

    public int limitingWidth(Image image1, Image image2){
        if((int)image1.getWidth() > (int)image2.getWidth()){
            return (int)image2.getWidth();
        }else{
            return (int)image1.getWidth();
        }
    }
}
