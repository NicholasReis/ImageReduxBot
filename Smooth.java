import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.PixelReader;

public class Smooth implements Tool{
    public WritableImage apply(Image image){
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

                        //Needs to take the average for x surounding pixels
                        //Then apply the average to each of the x surrounding pixels
                        //pW.setArgb(x, y, ((int)(curCol+prevCol+nextCol)/3));

                    }
                }
            }
        }
        // Create a new grid pane
        return wImg;
    }
}
