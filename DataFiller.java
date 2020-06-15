import java.util.Scanner;
import com.tumblr.jumblr.types.*;
import com.tumblr.jumblr.JumblrClient;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.io.Writer;
import java.io.File;
import java.io.FileWriter;
public class DataFiller{
    JumblrClient client;
    User user;
    Blog blog;
    public DataFiller(){
        client = new JumblrClient("rIWsLAnMBQSMlPmvrfDjxXfhv9f08Qf3VHwlNWnv5YL9I3vy44",
            "xgnuKjCtkRH8uumpeAp763NqA13LDj4RealeBLIXJHLadSBxRS");
        client.setToken("g7E5iEUB7e6ZqXIosdODfj2Yxc9lvwO4YiSdddcr37CQsaKWqp",
            "1k46ysRa0XOcCLKetopLwlYEnovShW1F1SEdX4heg69uVAUvYp");
        user = client.user();
        blog = client.blogInfo(user.getName() + ".tumblr.com");
    }

    public void updateImageInfo(){
        HashMap<String, Boolean> options = new HashMap<String, Boolean>();
        //This is mandatory for access to notes
        options.put("notes_info", true);
        List<Post> submissions = blog.posts(options);
        for(Post post : submissions){
            try{
                //This whole API has been really buggy.
                List<String> tags = post.getTags();
                String name = tags.get(0);
                List<Note> postNotes = post.getNotes();
                int likeSum = 0;
                int replySum = 0;
                int reblogSum = 0;

                /*
                 * WARNING: An illegal reflective access operation has occurred
                 * WARNING: Illegal reflective access by com.google.gson.internal.bind.ReflectiveTypeAdapterFactory (file:/C:/Users/nikok/Desktop/ImageReduxBot/ImageReduxBot%20%5BWIP%5D/) to field java.io.File.path
                 * WARNING: Please consider reporting this to the maintainers of com.google.gson.internal.bind.ReflectiveTypeAdapterFactory
                 * WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
                 * WARNING: All illegal access operations will be denied in a future release
                 *
                 * THIS WAS PREVIOUSLY WORKING.
                 * The issue seems to be related to looping through the notes of
                 * all the previous posts. It does not give this error the first time it's run.
                 * From the googling I have done and the error message it is outside of my control
                 * and is an issue with the creators of the API?
                 * 
                 * Illegal reflective access by com.google.gson.internal.bind.ReflectiveTypeAdapterFactory to field java.io.File.path
                 * This warning as I understand is how their code is interacting with
                 * me calling the path to where it needs to store the obtained data.
                 * It cuts the try/catch at this for loop and therefor does not
                 * assign the values for likes, replies, or reblogs which does
                 * ultimately break the way that I was weighting the decision making
                 * in the DataGrabber class.
                 */

                for(Note postNote : postNotes){
                    if(postNote.getType().equals("like")){
                        likeSum++;
                    }else if(postNote.getType().equals("reply")){
                        replySum++;
                    }else if(postNote.getType().equals("reblog")){
                        reblogSum++;
                    }
                }
                File outputFile = new File("output/"+ name + ".txt");
                String outStream = outputFile.toString();
                
                //Checks the file to see if it's already been ranked
                //If it has it clears it for updated rankings.
                if(outStream.contains("Likes: ")){
                    outStream = outStream.substring(0, outStream.indexOf("Likes: "));
                }
                
                //This adds the rankings to the bottom so data grabber can weight popularity
                FileWriter writer = new FileWriter(outputFile);
                writer.write("Likes: " + likeSum + "\nReplies: " + replySum + "\nReblogs: " + reblogSum);
                writer.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void postToTumblr(String name){
        try{
            //This creates a photo and a post
            Photo picture = new Photo(new File("output/"+name + ".png"));
            PhotoPost post = client.newPost(user.getName(), PhotoPost.class);
            post.setPhoto(picture);
            post.setState("published");
            post.addTag(name);//I tag the name so I can figure out which image to apply the notes
            post.save();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
