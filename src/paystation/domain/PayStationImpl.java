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
    private int totalMoney;
    private int numNickels = 0;
    private int numDimes = 0;
    private int numQuarters = 0;
    private Map<Integer, Integer> coinCount = new HashMap<>();
    
    private RateStrategy rateStrategy;
    
    public PayStationImpl(RateStrategy rateStrategy){
        this.rateStrategy = rateStrategy;
    }

    PayStationImpl() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addPayment(int coinValue)
            throws IllegalCoinException {
        switch (coinValue) {
            case 5: 
                coinCount.put(5, ++numNickels);
                break;
            case 10: 
                coinCount.put(10, ++numDimes);
                break;
            case 25: 
                coinCount.put(25, ++numQuarters);
                break;
            default:
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }
        insertedSoFar += coinValue;
        timeBought = rateStrategy.calculateTime(insertedSoFar);
    }

    @Override
    public int readDisplay() {
        return timeBought;
    }

    @Override
    public Receipt buy() {
        Receipt r = new ReceiptImpl(timeBought);
        totalMoney += insertedSoFar;
        reset();
        return r;
    }

    @Override
    public Map<Integer, Integer> cancel() {
        HashMap<Integer, Integer> tempMap = new HashMap<>();
        tempMap.putAll(coinCount);
        reset();
        return tempMap;
    }
    
    private void reset() {
        timeBought = insertedSoFar = 0;
        coinCount.clear();
    }
    
    @Override
    public int empty(){
        int temp = totalMoney;
        totalMoney = 0;
        return temp;
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
