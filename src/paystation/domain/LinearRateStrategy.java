package paystation.domain;

public class LinearRateStrategy implements RateStrategy {
    
    @Override
    public int calculateTime(int moneyInserted){
        return (moneyInserted * 2) / 5;
    }
}
