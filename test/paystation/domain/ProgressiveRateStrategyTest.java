package paystation.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


public class ProgressiveRateStrategyTest {
    PayStation ps; 
    
    @Before
    public void setup() {
        ps = new PayStationImpl(new ProgressiveRateStrategy());
    }
    
    //3 Tests to test time displayed when money is less than 150
    
    @Test
    public void shouldDisplay10For25Added() throws IllegalCoinException{
        ps.addPayment(25);
        
        assertEquals(10, ps.readDisplay());
    }
    
    @Test
    public void shouldDisplay40For100Added() throws IllegalCoinException{
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //100
        
        assertEquals(40, ps.readDisplay());
    }
    
    @Test
    public void shouldDisplay58For145Added() throws  IllegalCoinException{
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //100
        ps.addPayment(25); //125
        ps.addPayment(10);
        ps.addPayment(10); //145
         
        assertEquals(58, ps.readDisplay());
    }
    
    //3 Tests to test time displayed when money is greater than or equal to 150
    // or less than 350
    
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
    public void shouldDisplay90For250Added() throws IllegalCoinException{
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
       
        assertEquals(90, ps.readDisplay());
    }
    
    @Test
    public void shouldDisplay117For340Added() throws IllegalCoinException{
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //100
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //200
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //300
        ps.addPayment(25); //325
        ps.addPayment(10);
        ps.addPayment(5); //340
       
        assertEquals(117, ps.readDisplay());
    }
    
    //3 Tests to test time displayed when money is greater than or equal to 350
    
    @Test
    public void shouldDisplay120For350Added() throws IllegalCoinException{
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //100
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //200
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //300
        ps.addPayment(25);
        ps.addPayment(25); //350
       
        assertEquals(120, ps.readDisplay());
    }
    
    @Test
    public void shouldDisplay150For500Added() throws IllegalCoinException{
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //100
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //200
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //300
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //400 
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //500
       
        assertEquals(150, ps.readDisplay());
    }
    
    @Test
    public void shouldDisplay250For1000Added() throws IllegalCoinException{
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //100
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //200
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //300
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //400 
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //500
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //600
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //700
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //800
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //900 
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25); //1000
       
        assertEquals(250, ps.readDisplay());
    }
}