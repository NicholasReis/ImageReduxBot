import javafx.scene.image.Image;

public class BuiltImage{
    String name = "";
    Image img;
    String imgEffect = "";
    
    public void registerImage(String str){
        name = str;
    }
    
    public String getName(){
        return name;
    }
    
    public void registerEffect(String str){
        imgEffect = str;
    }
    
    public String getEffect(){
        return imgEffect;
    }
    
    public void setImage(Image tempImg){
        img = tempImg;
    }
    
    public Image getImage(){
        return img;
    }

}
