import java.util.Scanner;

import static java.lang.System.out;

public class VIPShoppingCart extends ShoppingCart{

    private int points;

    public VIPShoppingCart() {
        super();
        points = 0;
    }

    public VIPShoppingCart(String name, String date, int points) {
        setCustomerName(name);
        setDate(date);
        this.points = points;
    }

//if user spends over $100, 5% discount (rounded up to nearest dollar e.g. 110.80 = 111)
    public void printTotal() {
        System.out.println(getCustomerName() + " - " + getDate());
        if (getNumItemsInCart() == 0) {                     //if all elements in array are null
            System.out.println("SHOPPING CART IS EMPTY");
        } else {                                            //print receipt
            System.out.println("number of items: " + getNumItemsInCart()); //number of items
            listCartItems();                                               //list of items (name, qty, price, total)
            double total = getCostOfCart();
            if (total < 100) {
                System.out.printf("Total: $%.2f", total);              //total cost of items
                System.out.println("\nNo discount for a total less than $100.");
            } else {
                double discounted = total * 0.95;   //%5 discount
                setCurrCost(discounted);
                System.out.printf("Total: $%.2f", discounted);
                System.out.println(" (after %5 discount)");
            }
            System.out.println("Tax: $" + getTax());
        }

    }




//collect points
    //points collected =
    public void setPoints(int points) {
        this.points = points;
    }

//redeem points
    public  void redeemPoints () {
        Scanner sc = new Scanner(System.in);
        int points;

        System.out.println("Enter points to be redeemed, -1 to quit");
        while (true) {
            points = sc.nextInt();
            if (points == -1) {
                break;
            }
            points -= (points % 50);                                                    //round DOWN to nearest multiple of 50
            if (points < 50) {                                                          //error if entered less than 50
                System.out.println("Less than 50! Please retry. Enter -1 to quit:");
           } else if (points > this.points) {                                          //error if entered more than available (nearest 50)
               System.out.println("Not enough points. Please retry. Enter -1 to quit");
           } else {
               System.out.println("Redeeming " + points + " points.");
                setCurrCost(getCurrCost() - (points / 50));                        // 50 points = $1
               System.out.println("Total to pay: $" + twoDec(getCurrCost()));
               break;
           }
        }

    }

//overridden checkout
    public void checkout() {
        String userInput;
        Scanner scnr = new Scanner(System.in);
        System.out.println("\nRedeem points? (Y/N)");
        userInput = scnr.nextLine();
        if (userInput.equalsIgnoreCase("y")) {
            redeemPoints();
        }
        int pointsAdded = ((int)getCurrCost());
        this.points += pointsAdded;
        System.out.println("\nThank you for shopping with us. " + pointsAdded + " points added!");  //thank you message without redeeming points
        clearCart();    //clear cart



    }
}
