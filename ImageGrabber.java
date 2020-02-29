import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import java.util.Random;

public class ImageGrabber implements Tool{
    public WritableImage apply(Image image){
        PixelReader pR = image.getPixelReader();
        WritableImage wImg = new WritableImage((int)image.getWidth(), (int)image.getHeight());
        PixelWriter pW = wImg.getPixelWriter(); //Not sure why this isn't working
        Random rand = new Random();
        int height = rand.nextInt((int)image.getHeight());
        int width = rand.nextInt((int)image.getWidth());
        for(int x = 0; x < image.getHeight(); x++){
            for(int y = 0; y < image.getWidth(); y++){
                /*
                 *  These are the ingredients to a colour boost.
                 *  Can also use this new system of storing colour
                 *  to categorize colours way more easily
                Color curColor = pR.getColor(x,y);
                double newRVal = curColor.getRed();
                double newGVal = curColor.getGreen();
                double newBVal = curColor.getBlue();
                double newAVal = curColor.getOpacity();
                if(newBVal +0.3 > 1){
                    newBVal = 1;
                }else{
                    newBVal = newBVal + 0.3;
                }
                Color newColor = new Color(newRVal, newGVal, newBVal, newAVal);
                pW.setColor(x,y,newColor);
                */
                if(x == y){
                    Color curCol = pR.getColor(x,y);
                    double bVal = curCol.getBlue();
                    double gVal = curCol.getGreen();
                    double rVal = curCol.getRed();
                    double aVal = curCol.getOpacity();
                    System.out.println("A: " + aVal);
                    System.out.println("R: " + rVal);
                    System.out.println("G: " + gVal);
                    System.out.println("B: " + bVal);
                    System.out.println();
                    //Needs to take the average for x surounding pixels
                    //Then apply the average to each of the x surrounding pixels
                    //pW.setArgb(x, y, ((int)(curCol+prevCol+nextCol)/3));
                }

            }

        }
        // Create a new grid pane
        return wImg;
    }
}
