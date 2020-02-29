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

public class UI extends Application{
    public void start(Stage stage)
    {
             
        ArrayList<String> files = selectFiles();

        Image img1 = new Image("res/" + files.get(0));//Potentially resize later?
        Image img2 = new Image("res/" + files.get(1));//Potentially resize later?
        LineByLineCombine lBLC = new LineByLineCombine();
        Smooth smooth = new Smooth();
        Image tempImage = smooth.apply(lBLC.applyComboFilter(img1, img2));
        ImageView img = new ImageView(tempImage);
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


}
