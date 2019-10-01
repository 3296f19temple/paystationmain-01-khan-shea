package paystation.domain;
import java.util.*;

/**
 * Implementation of the pay station.
 *
 * Responsibilities:
 *
 * 1) Accept payment; 
 * 2) Calculate parking time based on payment; 
 * 3) Know earning, parking time bought; 
 * 4) Issue receipts; 
 * 5) Handle buy and cancel events.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class PayStationImpl implements PayStation {
    
    private int insertedSoFar;
    private int timeBought;
    private Map coinMap = new HashMap();
    private boolean nickleBool = false;
    private boolean dimeBool = false;
    private boolean quarterBool = false;
    
    @Override
    public void addPayment(int coinValue)
            throws IllegalCoinException {

        switch (coinValue) {
            case 5:
                if(!nickleBool)
                {
                    coinMap.put(1, 1);
                    nickleBool = true;
                }
                else
                {
                    coinMap.put(1, (int)coinMap.get(1) + 1);
                    
                }
                break;
            case 10:
                if(!dimeBool)
                {
                    coinMap.put(2, 1);
                    dimeBool = true;
                }
                else
                {
                    coinMap.put(2, (int)coinMap.get(2) + 1);
                }
                break;
            case 25:
                if(!quarterBool)
                {
                    coinMap.put(3, 1);
                    quarterBool = true;
                }
                else
                {
                    coinMap.put(3, (int)coinMap.get(3) + 1);
                }
                break;
            default:
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }
        insertedSoFar += coinValue;
        timeBought = insertedSoFar / 5 * 2;
    }

    @Override
    public int readDisplay() {
        return timeBought;
    }

    @Override
    public Receipt buy() {
        Receipt r = new ReceiptImpl(timeBought);
        reset();
        return r;
    }

    @Override
    public Map<Integer, Integer> cancel() 
    {
        Map tempMap =  new HashMap();
        tempMap.putAll(coinMap);
        reset();
        return tempMap;
    }
    
    private void reset() {
        timeBought = insertedSoFar = 0;
        nickleBool = false;
        dimeBool = false;
        quarterBool = false;
        coinMap.clear();
    }
    
    @Override
    public int empty()
    {
        int total = insertedSoFar;
        insertedSoFar = 0;
        return total;
    }
    
    public static void main(String[] args) throws IllegalCoinException {
    	Scanner sc = new Scanner(System.in);
    	PayStation ps = new PayStationImpl();
    	int choice;
    	while(true) {
    		System.out.println("Welcome to Paystation! Select one of the following options\r\n(1) Deposit Coins\r\n(2) Display\r\n(3) Buy Ticket\r\n(4) Cancel\r\n(5) Change Rate Strategy\r\n(6) Quit");
    		choice = sc.nextInt();
    		
    		switch(choice) {
    			case 1: 
    				System.out.println("Insert as many coins as you would like to, insert 0 when you're finished");
	    			int coin = 1;
	    			while(coin != 0) {
	    				coin = sc.nextInt();
	    				if(coin != 0) {
	    					ps.addPayment(coin);
	    				} else {
	    					break;
	    				}
	    			}
	    			break;
    			case 2: 
    				System.out.println("You have inserted enough money to buy " + ps.readDisplay() + " minutes");
    				break;
    			case 3: 
    				System.out.println("You have just purchased " + ps.buy().value() + " minutes of parking");
    				break;
    			case 4: 
    				System.out.println("Payment canceled. You have been refunded the amount of " + ps.empty() + " cents");
    				break;
    			case 5:
    				System.out.println("Select:\r\n(1) Alphatown rates\r\n(2) Betatown rates\r\n(3) Gammatown rates");
    				break;
    			case 6:
    				sc.close();
    				return;
    		}
    	}
    }
}
