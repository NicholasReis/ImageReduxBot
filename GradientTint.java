import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import java.util.Random;

public class GradientTint implements Filter{
    public WritableImage apply(Image image1){
        Random rand = new Random();
        WritableImage wImg = new WritableImage((int)image1.getWidth(), (int)image1.getHeight());
        switch(rand.nextInt(3)){
            case 0:
             wImg= redTint(image1);
            break;

            case 1:
            wImg = greenTint(image1);
            break;

            case 2:
            wImg = blueTint(image1);
            break;
        }

        return wImg;
    }

    public WritableImage redTint(Image image1){
        WritableImage wImg = new WritableImage((int)image1.getWidth(), (int)image1.getHeight());
        PixelWriter pW = wImg.getPixelWriter();
        PixelReader pR = image1.getPixelReader();

        for(int x = 0; x < (int)image1.getHeight()-1; x++){
            for(int y = 0; y < (int)image1.getWidth()-1; y++){
                Color c = pR.getColor(y,x);
                double red = c.getRed();
                if(red + 0.3 <= 1){
                    red+=0.3;
                }
                pW.setColor(y,x, new Color(red, c.getGreen(), c.getBlue(), c.getOpacity()));
            }
        }

        return wImg;
    }
    
    
    public WritableImage greenTint(Image image1){
        WritableImage wImg = new WritableImage((int)image1.getWidth(), (int)image1.getHeight());
        PixelWriter pW = wImg.getPixelWriter();
        PixelReader pR = image1.getPixelReader();

        for(int x = 0; x < (int)image1.getHeight()-1; x++){
            for(int y = 0; y < (int)image1.getWidth()-1; y++){
                Color c = pR.getColor(y,x);
                double green = c.getGreen();
                if(green + 0.3 <= 1){
                    green+=0.3;
                }
                pW.setColor(y,x, new Color(c.getRed(), green, c.getBlue(), c.getOpacity()));
            }
        }

        return wImg;
    }
    
    
    public WritableImage blueTint(Image image1){
        WritableImage wImg = new WritableImage((int)image1.getWidth(), (int)image1.getHeight());
        PixelWriter pW = wImg.getPixelWriter();
        PixelReader pR = image1.getPixelReader();

        for(int x = 0; x < (int)image1.getHeight()-1; x++){
            for(int y = 0; y < (int)image1.getWidth()-1; y++){
                Color c = pR.getColor(y,x);
                double blue = c.getBlue();
                if(blue + 0.3 <= 1){
                    blue+=0.3;
                }
                pW.setColor(y,x, new Color(c.getRed(), c.getGreen(), blue, c.getOpacity()));
            }
        }

        return wImg;
    }
}
