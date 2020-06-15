import java.util.ArrayList;
import java.io.File;
import java.util.HashMap;
import java.util.Random;

public class DataGrabber{
    ArrayList<CombinationData> comboStats = new ArrayList<CombinationData>();
    ArrayList<EffectData> effectStats = new ArrayList<EffectData>();
    ArrayList<String> topFilters = new ArrayList<String>();
    ArrayList<String> topCombinations = new ArrayList<String>();
    ArrayList<ImageState> currentImages = new ArrayList<ImageState>();
    HashMap<String, Double> effectRollOdds = new HashMap<String, Double>();
    HashMap<String, Double> comboRollOdds = new HashMap<String, Double>();

    public DataGrabber(){
        //This runs as soon as it's declared to 
        populate();
    }

    public void populate(){
        try{
            File folder = new File("output/");

            for(File f : folder.listFiles()){
                if(f.getName().substring(f.getName().length()-4).equals(".txt")){
                    File file = new File(f.toString());

                    currentImages.add(new ImageState(f));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        loadData();
    }

    public void loadData(){
        //This grabs all the likes, replies and reblogs from the images
        //And assigns them specifically to the filters and combos used
        for(ImageState iState : currentImages){
            if(!comboStats.contains(iState.getCombination())){
                comboStats.add(new CombinationData(iState.getCombination(), iState.getLikes()));
            }else{
                comboStats.get(comboStats.indexOf(iState.getCombination())).add(iState.getLikes());
                comboStats.get(comboStats.indexOf(iState.getCombination())).add(iState.getReplies()*2);
                comboStats.get(comboStats.indexOf(iState.getCombination())).add(iState.getReblogs()*3);
            }

            String[] effects = iState.getEffects();
            if(!effectStats.contains(effects[0])){
                effectStats.add(new EffectData(effects[0], iState.getLikes()));
            }else{
                effectStats.get(effectStats.indexOf(effects[0])).add(iState.getLikes());
                effectStats.get(effectStats.indexOf(effects[0])).add(iState.getReplies()*2);
                effectStats.get(effectStats.indexOf(effects[0])).add(iState.getReblogs()*3);
            }

            if(!effectStats.contains(effects[1])){
                effectStats.add(new EffectData(effects[1], iState.getLikes()));
            }else{
                effectStats.get(effectStats.indexOf(effects[1])).add(iState.getLikes());
                effectStats.get(effectStats.indexOf(effects[1])).add(iState.getReplies()*2);
                effectStats.get(effectStats.indexOf(effects[1])).add(iState.getReblogs()*3);
            }
        }
        calculateWeights();
    }

    public void calculateWeights(){

        //This method takes all the effects into a hashtable with their associated
        //Probabilities making sure to add up to 100% or 1.
        
        double effectTotal;
        effectTotal = 0;
        for(EffectData effect : effectStats){
            effectTotal += effect.getValue();
        }

        for(EffectData effect : effectStats){
            effectRollOdds.put(effect.getName(), effect.getValue()/effectTotal);
        }

        //This does the same for the combinations
        
        double combinationTotal;
        combinationTotal = 0;
        for(CombinationData combo : comboStats){
            combinationTotal += combo.getValue();
        }

        for(CombinationData combo : comboStats){
            comboRollOdds.put(combo.getName(), combo.getValue()/combinationTotal);
        }

    }

    public ArrayList<String> getFilterRank(){
        //explorativePadding()
        return topFilters;
    }

    public ArrayList<String> getCombinationRank(){

        //explorativePadding()
        return topCombinations;
    }

    public String chooseFilter(){
        Random rand = new Random();
        //This makes a random % value between 0 & 1
        double randomChoiceVal = rand.nextDouble();
        double currentWeightBracket = 0;
        
        //This if statement is here so that the first 15 images/files
        //Need to be generated before applying weights so that it has a pool
        //of data to actually draw from. If it doesn't have 15 it returns "none"
        //Which is the key in a dictionary on the other side to just choose randomly
        if(effectStats.size() > 15){
            //This applies the random number backwards
            //You have the number and for each loop you apply the next % value
            //So if the first filter had 0.1 (10%) weight it would check if
            //the random value is less than 10%, if not it will apply the next
            //filter weight which might be 0.3 (30%) bringing the total up to
            //0.4 (40%). As I said in calculateWeights() (just above) it adds
            //up to 1 or (100%) so it's the same as just comparing it to a bunch
            //of if statements or a switch, however because the dataset could neglect
            //some of the filters if they haven't been used this allows a dynamic
            //set where the number of filters can change, but the values always
            //add up to 100%. I couldn't use a switch or if statements with dynamic
            //set sizes which is why I had to do it backwards
            for(EffectData effect : effectStats){
                currentWeightBracket += effectRollOdds.get(effect.getName());
                if(randomChoiceVal < currentWeightBracket){
                    return effect.getName();
                }
            }
            return "None";
        }else{
            return "None";
        }
    }

    public String chooseCombo(){
        Random rand = new Random();
        double randomChoiceVal = rand.nextDouble();
        double currentWeightBracket = 0;
        //All the same stuff as above, but for combinations instead of effects/filters
        if(comboStats.size() > 15){
            for(CombinationData combo : comboStats){
                currentWeightBracket += comboRollOdds.get(combo.getName());
                if(randomChoiceVal < currentWeightBracket){
                    return combo.getName();
                }
            }
            return "None";
        }else{
            return "None";
        }
    }
}