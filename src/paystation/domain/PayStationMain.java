package paystation.domain;

import java.util.*;

public class PayStationMain {
    public static void main(String[] args) throws IllegalCoinException {

        PayStationImpl payStation = new PayStationImpl(new LinearRateStrategy());
        Scanner sc = new Scanner(System.in);
        boolean simulation = true;

        while (simulation) {
            System.out.println("---------------------");
            System.out.println("Welcome to PayStation");
            System.out.println("---------------------");
            System.out.println("Press 1 to Deposit Coins");
            System.out.println("Press 2 to Display");
            System.out.println("Press 3 to Buy Ticket");
            System.out.println("Press 4 to Cancel");
            System.out.println("Press 5 to Change Rate Strategy");
            System.out.println();
            System.out.print("Select an option: ");

            int input = sc.nextInt();
            
            System.out.println();

            switch (input) {

                case 1: //deposit coins
                    while(simulation) {
                        System.out.print("Please enter 5, 10, or 25 to deposit a nickel, a dime, or a quarter. Press 0 to stop depositing coins: ");
                        int coin = sc.nextInt();
                        if (coin == 0) {
                            break;
                        }
                        try {
                            payStation.addPayment(coin);
                        } catch (IllegalCoinException e) {
                            System.out.println("Error. Please enter a valid coin value. Only nickels, dimes, and quarters are accepted.");
                        }
                    }
                    System.out.println();
                    break;

                case 2: //display 
                    System.out.println("You have bought " + payStation.readDisplay() + " minutes so far");
                    System.out.println();
                    break;

                case 3: //buy
                    Receipt r = payStation.buy();

                    System.out.println("Time bought = " + r.value() + " minutes");
                    System.out.println("Thank you for using PayStation");

                    simulation = false;
                    break;

                case 4: //cancel
                    Map<Integer, Integer> numCoins = payStation.cancel();
                    int nickels;
                    int dimes;
                    int quarters;
                    
                    try{
                        nickels = numCoins.get(5);
                    } catch (NullPointerException e){
                        nickels = 0; 
                    }
                    try{
                        dimes = numCoins.get(10);
                    } catch(NullPointerException e){
                        dimes = 0; 
                    }
                    try{
                        quarters = numCoins.get(25);
                    } catch(NullPointerException e){
                        quarters = 0; 
                    }

                    System.out.println("Number of nickels: " + nickels);
                    System.out.println("Number of dimes: " + dimes);
                    System.out.println("Number of quarters: " + quarters);
                    
                    int total = (nickels * 5) + (dimes * 10) + (quarters * 25);
                    
                    System.out.println("Total value of coins returned : $" + total / 100.0);
                    
                    System.out.println();
                    simulation = false;
                    break;

                case 5: //change rate strategy 
                    System.out.println("Select the rate strategy which you wish to use");
                    System.out.println("1) Alphatown (Linear Rate Strategy)");
                    System.out.println("2) Betatown (Progressive Rate Strategy)");
                    System.out.println("3) Gammatown (Alternating Rate Strategy)");
                    System.out.println();
                    System.out.print("Enter the rate strategy you wish to change to or press 0 to exit: ");

                    int rs = sc.nextInt();
                    switch (rs) {
                        case 1:
                            payStation = new PayStationImpl(new LinearRateStrategy());
                            System.out.println("Rate Strategy changed to Linear");
                            break;
                        case 2:
                            payStation = new PayStationImpl(new ProgressiveRateStrategy());
                            System.out.println("Rate Strategy changed to Progressive");
                            break;
                        case 3:
                            payStation = new PayStationImpl(new AlternatingRateStrategy());
                            System.out.println("Rate Strategy changed to Alternating");
                            break;
                    }
                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid PayStation option. Please pick a valid option.");
                    System.out.println();
                    break;
            }
        }
    }
}