import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javafx.scene.image.WritableImage;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.PixelReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class ImageTest extends Application{
    @Override
    public void start(Stage stage)
    {
        ImageView img = smooth(defaultRedux());
        img.setFitHeight(1000);
        img.setFitWidth(1000);
        try {
            // retrieve image
            File outputfile = new File("saved.png");
            BufferedImage bImage = SwingFXUtils.fromFXImage(img.getImage(), null);
            ImageIO.write(bImage, "png", outputfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StackPane pane = new StackPane(img);
        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane, 300,100);
        stage.setTitle("JavaFX Example");
        stage.setScene(scene);
        stage.setWidth(1000);
        stage.setHeight(1000);
        // Show the Stage (window)
        stage.show();
    }

    public ArrayList<String> selectFiles(){
        ArrayList<String> tempFiles = new ArrayList<String>();
        ArrayList<String> sendFiles = new ArrayList<String>();
        try{
            File folder = new File("res/");
            for(File f : folder.listFiles()){
                tempFiles.add(f.getName());
            }

            Random rand = new Random();
            int choice = 0;
            int choice2 = 0;

            //Randomizes
            while(choice == choice2){
                choice = rand.nextInt(tempFiles.size());
                choice2 = rand.nextInt(tempFiles.size());
            }
            sendFiles.add(tempFiles.get(choice));
            sendFiles.add(tempFiles.get(choice2));
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(sendFiles.get(0));
        System.out.println(sendFiles.get(1));
        return sendFiles;
    }

    public WritableImage defaultRedux(){
        ArrayList<String> files = selectFiles();

        Image img1 = new Image("res/" + files.get(0));//Potentially resize later?
        Image img2 = new Image("res/" + files.get(1));//Potentially resize later?
        WritableImage wImg = new WritableImage((int)img1.getWidth(), (int)img1.getHeight());
        PixelReader pR = img1.getPixelReader();
        PixelReader pR2 = img2.getPixelReader();
        PixelWriter pW = wImg.getPixelWriter();

        for(int x = 0; x < (int)img1.getWidth(); x++){
            for(int y = 0; y < (int)img1.getHeight(); y++){
                if(y % 2 == 0){
                    pW.setArgb(x, y, pR.getArgb(x,y));
                }else{
                    pW.setArgb(x, y, pR2.getArgb(x,y));
                }
            }
        }

        files = selectFiles();

        Image img3 = new Image("res/" + files.get(0));//Potentially resize later?
        Image img4 = new Image("res/" + files.get(1));//Potentially resize later?WritableImage wImg = new WritableImage((int)img1.getWidth(), (int)img1.getHeight());
        WritableImage wImg2 = new WritableImage((int)img3.getWidth(), (int)img3.getHeight());
        PixelReader pR3 = img3.getPixelReader();
        PixelReader pR4 = img4.getPixelReader();
        PixelWriter pW2 = wImg2.getPixelWriter();
        for(int x = 0; x < (int)img3.getWidth(); x++){
            for(int y = 0; y < (int)img3.getHeight(); y++){
                if(y % 2 == 0){
                    pW2.setArgb(x, y, pR3.getArgb(x,y));
                }else{
                    pW2.setArgb(x, y, pR4.getArgb(x,y));
                }
            }
        }

        WritableImage wImg3 = new WritableImage((int)img3.getWidth(), (int)img3.getHeight());
        PixelReader pR5 = wImg.getPixelReader();
        PixelReader pR6 = wImg2.getPixelReader();
        PixelWriter pW3 = wImg3.getPixelWriter();
        for(int y = 0; y < (int)img3.getHeight(); y++){
            for(int x = 0; x < (int)img3.getWidth(); x++){
                if(y % 2 == 0){
                    pW3.setArgb(x, y, pR5.getArgb(x,y));
                }else{
                    pW3.setArgb(x, y, pR6.getArgb(x,y));
                }
            }
        }
        // Create a new grid pane
        return wImg3;
    }

    public ImageView smooth(WritableImage img){

        PixelReader pR = img.getPixelReader();
        PixelWriter pW = img.getPixelWriter();
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
        return new ImageView(img);
    }
    /* Will add later. Will need to import 2DGraphics, but might make
     * images an object so I can do that in the class if I go that route
    public String resize(String f){
    ImageView img = new ImageView(new Image(f));
    img.setFitHeight(1000);
    img.setFitWidth(1000);
    try {
    // retrieve image
    File outputfile = new File(f);
    BufferedImage bImage = (BufferedImage) new Image(;
    ImageIO.write(bImage, "png", outputfile);
    System.out.println(bImage.getWidth());
    System.out.println(bImage.getWidth());
    } catch (Exception e) {
    e.printStackTrace();
    }
    return f;
    }*/
}
