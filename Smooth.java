import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Smooth implements Tool{
    public WritableImage apply(Image image){
        PixelReader pR = image.getPixelReader();
        WritableImage wImg = new WritableImage((int)image.getWidth(), (int)image.getHeight());
        PixelWriter pW = wImg.getPixelWriter(); //Not sure why this isn't working
        int height = (int)image.getHeight();
        int width = (int)image.getWidth();
        
        for(int count = 0; count < 3; count++){
            for(int x = 0; x < height; x++){
                for(int y = 0; y < width; y++){
                    if(y > 0 && y < image.getWidth()-1){

                        //Needs to take the average for x surounding pixels
                        //Then apply the average to each of the x surrounding pixels

                        pW.setColor(y, x, averageColor(y, x, height, width, pR));
                    }
                }
            }
        }
        // Create a new grid pane
        return wImg;
    }

    public Color averageColor(int y, int x, int height, int width, PixelReader pR){
        ArrayList<Color> colours = new ArrayList<Color>();
        colours.add(pR.getColor(y,x));
        if(x > 0 && y > 0){
            colours.add(pR.getColor(y-1, x-1));
        }

        if(x + 1 < height && y + 1 < width){
            colours.add(pR.getColor(y+1,x+1));
        }

        if(x > 0 && y + 1 < width){
            colours.add(pR.getColor(y+1, x-1));
        }

        if(y > 0 && x + 1 < height){
            colours.add(pR.getColor(y-1, x+1));
        }

        if(x > 0){
            colours.add(pR.getColor(y, x-1));
        }

        if(y > 0){
            colours.add(pR.getColor(y-1,x));
        }

        if(y+1 < width){
            colours.add(pR.getColor(y+1,x));
        }

        if(x+1 < height){
            colours.add(pR.getColor(y,x+1));
        }
        double red = getRed(colours);
        double green = getGreen(colours);
        double blue = getBlue(colours);
        double opacity = getOpacity(colours);
        return new Color(red, green, blue, opacity);
    }
    
    public double getRed(ArrayList<Color> colours){
        double num = 0;
        double colourVals = 0;
        for(Color colour : colours){
            colourVals += colour.getRed();
            num++;
        }
        return colourVals/num;
    }

    public double getGreen(ArrayList<Color> colours){
        double num = 0;
        double colourVals = 0;
        for(Color colour : colours){
            colourVals += colour.getGreen();
            num++;
        }
        return colourVals/num;
    }
    
    public double getBlue(ArrayList<Color> colours){
        double num = 0;
        double colourVals = 0;
        for(Color colour : colours){
            colourVals += colour.getBlue();
            num++;
        }
        return colourVals/num;
    }
    
    public double getOpacity(ArrayList<Color> colours){
        double num = 0;
        double colourVals = 0;
        for(Color colour : colours){
            colourVals += colour.getOpacity();
            num++;
        }
        return colourVals/num;
    }
}
