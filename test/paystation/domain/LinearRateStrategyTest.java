package paystation.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


public class LinearRateStrategyTest {
    PayStation ps; 
    
    @Before
    public void setup() {
        ps = new PayStationImpl(new LinearRateStrategy());
    
    }
    
    @Test
    public void shouldDisplay60For150Added() throws IllegalCoinException{
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //100
        ps.addPayment(25);
        ps.addPayment(25); //150
        
        assertEquals(60, ps.readDisplay());
    }
    
    @Test
    public void shouldDisplay100For250Added() throws IllegalCoinException{
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //100
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //200
        ps.addPayment(25);
        ps.addPayment(25); //250
        
        assertEquals(100, ps.readDisplay());
    }
}