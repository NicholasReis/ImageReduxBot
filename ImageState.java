import java.util.Scanner;
import java.io.File;

public class ImageState{
    String[] filters;
    String combination = "";
    int likes = 0;
    int comments = 0;
    int retweets = 0;
    
    public ImageState(File dataFile){
        try{
            Scanner scan = new Scanner(dataFile);
            String line = "";
            while(scan.hasNextLine()){
                line = scan.nextLine();
                if(line.contains("Effects: ")){
                    filters = line.substring(line.indexOf("Effects: ")+9).split(", ");
                }
                if(line.contains("Combination: ")){
                    combination = line.substring(line.indexOf("Combination: ")+13);
                }
                if(line.contains("Likes: ")){
                    likes = Integer.parseInt(line.substring(line.indexOf("Likes: ")+7));
                }
                if(line.contains("Replies: ")){
                    comments = Integer.parseInt(line.substring(line.indexOf("Replies: ")+9));
                }
                if(line.contains("Reblogs: ")){
                    retweets = Integer.parseInt(line.substring(line.indexOf("Reblogs: ")+9));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public String[] getEffects(){
        return filters;
    }
    
    public String getCombination(){
        return combination;
    }
    
    public int getLikes(){
        return likes;
    }
    
    public int getReplies(){
        return comments;
    }
    
    public int getReblogs(){
        return retweets;
    }
}
