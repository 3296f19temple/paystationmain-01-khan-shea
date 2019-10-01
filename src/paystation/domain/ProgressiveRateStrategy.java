package paystation.domain;

public class ProgressiveRateStrategy implements RateStrategy {
    
    @Override
    public int calculateTime(int moneyInserted){
        
        int time = 0;
        
        if(moneyInserted < 150){
            time = (moneyInserted * 2) / 5;
        }
        else if(moneyInserted >= 150 && moneyInserted < 350){
            time = (moneyInserted - 150) * (3/10) + 60;
        }
        else if(moneyInserted >= 350){
            time = (moneyInserted - 350) / 5 + 120;
        }
        return time;
    }
    
}
