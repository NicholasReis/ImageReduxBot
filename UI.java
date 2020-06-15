import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class UI extends Application{
    BuiltImage bImage1 = new BuiltImage();
    BuiltImage bImage2 = new BuiltImage();
    CombinedImage cImage = new CombinedImage();
    DataGrabber dGrabber = new DataGrabber();
    public void start(Stage stage){
        //Initializes this first specifically so it can load and update all
        //The file information just to make sure it's set
        DataFiller dF = new DataFiller();
        dF.updateImageInfo();
        
        
        //This creates an arraylist so I could select both files at once
        //Because it was easy that way to avoid duplicates without having
        //to overload one with an argument and one without
        ArrayList<String> files = selectFiles();
        
        //This creates a builtImage which is just a datatype for me to use it
        //more efficiently
        bImage1.setImage(new Image("res/" + files.get(0)));//Potentially resize later?
        bImage2.setImage(new Image("res/" + files.get(1)));//Potentially resize later?
        
        //This puts both of the BuiltImages into the combined image so I can
        //Keep all the data together to output to the files more conveniently
        cImage.registerBuildImage(bImage1);
        cImage.registerBuildImage(bImage2);
        
        //This sends both of the saved images to have a filter applied
        //Then both the new images are combined into one and saved
        cImage.setImage(doubleEdit(singleEdit(bImage1),singleEdit(bImage2)));

        //A lot of the images looked rough so this just smooths it out for quality
        Smooth smooth = new Smooth();
        cImage.setImage(smooth.apply(cImage.getImage()));

        ImageView img = new ImageView(cImage.getImage());
        //Saves the image to local drive and also saves the textfile of the filters
        //and combinations used to create the image so it can compare the filters
        //to the likes, replies and reblogs
        cImage.saveFile();
        
        //saved so I can send the file location to postToTumblr(name)
        String imageName = cImage.saveImage(img);
        
        
        //Posts to tumblr
        //You can confirm it posts at imagereduxbot.tumblr.com
        //The naming format is year,month,day,hour,minute,second so you can verify
        dF.postToTumblr(imageName); 
        
        //Just to verify it's completion despite warnings/errors
        System.out.println("Done");
    }

    public ArrayList<String> selectFiles(){
        ArrayList<String> tempFiles = new ArrayList<String>();
        ArrayList<String> sendFiles = new ArrayList<String>();
        try{
            File folder = new File("res/");
            for(File f : folder.listFiles()){
                tempFiles.add(f.getName());
            }

            //Pulls random choices
            Random rand = new Random();
            int choice = 0;
            int choice2 = 0;

            //Randomizes (while they are the same it will try again)
            while(choice == choice2){
                choice = rand.nextInt(tempFiles.size());
                choice2 = rand.nextInt(tempFiles.size());
            }
            sendFiles.add(tempFiles.get(choice));
            sendFiles.add(tempFiles.get(choice2));
        }catch(Exception e){
            e.printStackTrace();
        }

        //Saves the names to the BuiltImages for data storage
        bImage1.registerImage(sendFiles.get(0));
        bImage2.registerImage(sendFiles.get(1));
        
        return sendFiles;
    }

    
    
    /*
     * Not really proud of these last two methods, they are quick and dirty
     * to allow for a little bit of scalability, but I wasn't sure how to apply
     * classes dynamically without relying on tumblr tags, which it wouldn't have
     * without having used the filters or combos already. Unfortunately I couldn't
     * think of a way to do this stuff dynamically before running out of time
     */
    
    
    public Image singleEdit(BuiltImage img){
        Image tempImage = img.getImage();

        Random rand = new Random();
        HashMap<String, Integer> effectsTable = new HashMap<String, Integer>();
        effectsTable.put("ColorInverter", 0);
        effectsTable.put("GradientTint", 1);
        effectsTable.put("Brighten", 2);
        effectsTable.put("Darken", 3);
        effectsTable.put("None", rand.nextInt(4));

        String choice = dGrabber.chooseFilter();

        switch(effectsTable.get(choice)){
            case 0: ColorInverter cI = new ColorInverter();
            tempImage = cI.apply(img.getImage());
            img.registerEffect("ColorInverter");
            break;

            case 1: GradientTint gT = new GradientTint();
            tempImage = gT.apply(img.getImage());
            img.registerEffect("GradientTint");
            break;

            case 2: Brighten b = new Brighten();
            tempImage = b.apply(img.getImage());
            img.registerEffect("Brighten");
            break;

            case 3: Darken d = new Darken();
            tempImage = d.apply(img.getImage());
            img.registerEffect("Darken");
            break;

            default: tempImage = img.getImage();
            img.registerEffect("None");
            break;
        }
        return tempImage;
    }

    public Image doubleEdit(Image img1, Image img2){
        Image tempImage;
        Random rand = new Random();
        HashMap<String, Integer> comboTable = new HashMap<String, Integer>();
        comboTable.put("RandomPixelCombine", 0);
        comboTable.put("LineByLineCombine", 1);
        comboTable.put("HorizontalBlend", 2);
        comboTable.put("CentralEmergence", 3);
        comboTable.put("HorizontalBlend", 4);
        comboTable.put("VerticalBlend", 5);
        comboTable.put("None", rand.nextInt(6));

        String choice = dGrabber.chooseFilter();

        switch(comboTable.get(choice)){
            case 0: RandomPixelCombine rPC = new RandomPixelCombine();
            tempImage = rPC.apply(img1, img2);
            cImage.registerCombination("RandomPixelCombine");
            break;

            case 1: LineByLineCombine lBLC = new LineByLineCombine();
            tempImage = lBLC.apply(img1, img2);
            cImage.registerCombination("LineByLineCombine");
            break;

            case 2: HorizontalBlend hB = new HorizontalBlend();
            tempImage = hB.apply(img1, img2);
            cImage.registerCombination("HorizontalBlend");
            break;

            case 3: CentralEmergence cE = new CentralEmergence();
            tempImage = cE.apply(img1, img2);
            cImage.registerCombination("CentralEmergence");
            break;

            case 4: HorizontalBleed hBleed = new HorizontalBleed();
            tempImage = hBleed.apply(img1, img2);
            cImage.registerCombination("HorizontalBlend");
            break;

            case 5: VerticalBleed vBleed = new VerticalBleed();
            tempImage = vBleed.apply(img1, img2);
            cImage.registerCombination("VerticalBlend");
            break;

            default: tempImage = img1;
            cImage.registerCombination("None");
            break;
        }

        return tempImage;
    }
}
