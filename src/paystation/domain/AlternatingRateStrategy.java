package paystation.domain;


import java.util.Date;
import java.util.Calendar;

public class AlternatingRateStrategy implements RateStrategy {
    
    @Override
    public int calculateTime(int moneyInserted){
        
        int time = 0;
        
        if(isWeekend()){
            LinearRateStrategy linearRate = new LinearRateStrategy();
            time = linearRate.calculateTime(moneyInserted);
        }
        else{
            ProgressiveRateStrategy progressiveRate = new ProgressiveRateStrategy();
            time = progressiveRate.calculateTime(moneyInserted);
        }
        return time;
    }
    
    private boolean isWeekend(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return (calendar.get(Calendar.DAY_OF_WEEK) == 1 ||
                calendar.get(Calendar.DAY_OF_WEEK) == 7);
    }
}
