
import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.PixelReader;
import java.util.Random;

public class HorizontalBleed implements CombinationFilter{
    public WritableImage apply(Image image1, Image image2){
        WritableImage wImg = new WritableImage((int)image1.getWidth(), (int)image1.getHeight());
        PixelReader pR = image1.getPixelReader();
        PixelReader pR2 = image2.getPixelReader();
        PixelWriter pW = wImg.getPixelWriter();

        Random rand = new Random();

        int height = limitingHeight(image1, image2);
        int width = limitingWidth(image1, image2);
        for(int x = 0; x < height; x++){
            double widthP = -6*(((((-x)+((double)width/2))*((-x)+(double)width/2)))/(double)height) + (double)height/2;
            double randVal = rand.nextDouble();
            for(int y = 0; y < width; y++){
                if(y < width/2){
                    if(randVal< widthP/width){
                        pW.setArgb(y, x, pR.getArgb(y,x));
                    }else{
                        pW.setArgb(y, x, pR2.getArgb(y,x));
                    }
                }else{
                    if(randVal< widthP/width){
                        pW.setArgb(y, x, pR2.getArgb(y,x));
                    }else{
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
