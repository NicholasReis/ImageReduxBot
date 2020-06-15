import java.util.ArrayList;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CombinedImage{
    String name = "";
    String combination = "";
    Image img;
    String imgInfo = "";
    ArrayList<BuiltImage> images = new ArrayList<BuiltImage>();

    public void registerBuildImage(BuiltImage bI){
        images.add(bI);
    }

    public void registerCombination(String combo){
        combination = combo;
    }

    public void setImage(Image tempImg){
        img = tempImg;
    }

    public Image getImage(){
        return img;
    }

    public void saveFile(){
        createName();
        imgInfo = name + "\n";
        imgInfo += "Images: " + images.get(0).getName() + ", " + images.get(1).getName() + "\n";
        imgInfo += "Effects: " + images.get(0).getEffect() + ", " + images.get(1).getEffect() + "\n";
        imgInfo += "Combination: " + combination + "\n";
        
    }

    public String saveImage(ImageView img){
        
        try {
            File outputFolder = new File("output/");
            if(!outputFolder.exists()){
                outputFolder.mkdir();
            }
            File outputImage = new File("output/"+ name+ ".png");
            BufferedImage bImage = SwingFXUtils.fromFXImage(img.getImage(), null);
            ImageIO.write(bImage, "png", outputImage);
            File outputFile = new File("output/"+ name + ".txt");
            FileWriter writer = new FileWriter(outputFile);
            writer.write(imgInfo);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }
    
    public void createName(){
        Date objDate = new Date();
        String strDateFormat = "yyyyMMddHHmmss";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        name += objSDF.format(objDate);
        name+=combination.toLowerCase();
    }
}
