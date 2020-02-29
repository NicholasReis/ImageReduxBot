import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.PixelReader;

public class Smooth implements Tool{
    public WritableImage apply(WritableImage image){
        PixelReader pR = image.getPixelReader();
        WritableImage wImg = new WritableImage((int)image.getWidth(), (int)image.getHeight());
        PixelWriter pW = wImg.getPixelWriter(); //Not sure why this isn't working
        for(int count = 0; count < 3; count++){
            for(int x = 0; x < 1000; x++){
                for(int y = 0; y < 1000; y++){
                    if(x > 0 && x < 999){
                        int curCol = pR.getArgb(x,y);
                        int prevCol = pR.getArgb(x-1,y);
                        int nextCol = pR.getArgb(x+1,y);

                        //Squares the surrounding pixel values and averages them to smooth the image
                        pW.setArgb(((int)(Math.sqrt(curCol)+Math.sqrt(prevCol)+Math.sqrt(nextCol))/3), x, y);

                    }
                }
            }
        }
        // Create a new grid pane
        return wImg;
    }
}
