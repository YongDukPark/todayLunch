package todaylunch;
import java.util.ArrayList;

public class LifeStarterPack {
    public static void main(String[] args) {
        ArrayList<Integer> fraud = new ArrayList<>();
        int temp = 0;
        boolean numberCheck = true;
        
        while (true) {            
            temp = (int)(Math.random() * 45)+1;
            for(int i = 0 ; i < fraud.size() ; i++){
                if(fraud.get(i) == temp){
                    numberCheck = false;
                }
            }
            if(numberCheck){
                fraud.add(temp);
            }
            if(fraud.size() == 6){
                break;
            }
        }
        for(int i = 0 ; i < fraud.size() ; i++){
            System.out.println(fraud.get(i));
        }
    }
}
