import java.util.ArrayList;

public class EffectData{
    String name;
    int value = 0;
    public EffectData(String tempName, int tempValue){
        name = tempName;
        value = tempValue;
    }
    
    public String getName(){
        return name;
    }
    
    public int getValue(){
        return value;
    }

    public void add(int increaseBy){
        value += increaseBy;
    }
}
