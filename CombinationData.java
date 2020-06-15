import java.util.ArrayList;

public class CombinationData{
    String name;
    int value;
    public CombinationData(String tempName, int tempValue){
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
