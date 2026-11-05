import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class JPAssignment2{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("*************** Stage 1 ***************");
        stage1(sc);
        
        System.out.println("*************** Stage 2***************");
        stage2(sc);
        
        System.out.println("*************** Stage 3***************");
        stage3();
        
        System.out.println("*************** Stage 4***************");
        stage4(sc);
    }



//---------------------------------------------------------------------------------------------------------------------




    public static void stage1(Scanner sc){
        ItemToPurchase item = ItemToPurchase.enterItem(sc);
        System.out.println("Total: " + item);
    }


    public static void stage2(Scanner sc){

        Scanner scnr = new Scanner(System.in);

        out.println("Enter the name of the customer:");
        String custName = sc.nextLine();
        if (custName == ""){
            custName = null;                        //setting default value fom no argument constructor
        }

        out.println("Enter the current date:");
        String currDate = sc.nextLine();
        if (currDate == "") {
            currDate = null;                        //setting default value fom no argument constructor
        }

        ShoppingCart cart;

        System.out.println("Select type of cart");
        System.out.println("1. normal");
        System.out.println("2. VIP (stage 2)");
        System.out.println("3. Senior (stage 4)");
        int cartType = sc.nextInt();

        if (cartType == 1) {                                            //normal cart
            cart = new ShoppingCart(custName, currDate);
        } else if (cartType == 2) {                                     //VIP cart
            out.println("Enter available points:");
            int points = sc.nextInt();
            cart = new VIPShoppingCart(custName, currDate, points);
        }
        else if (cartType == 3) {
            //senior cart
            out.println("Enter senior ID:");
            sc.nextLine();
            while (true) {
                try {
                    String seniorID = sc.nextLine();
                    if (seniorID.matches("[a-zA-Z0-9]{6}")) {
                        cart = new SeniorShoppingCart(custName, currDate, seniorID);
                        break;
                    } else {
                        throw new InvalidVariableException("ID must 6 digits long, try again");
                    }
                } catch (InvalidVariableException e) {
                    out.println(e.getMessage());
                }
            }
        } else {
            cart = new ShoppingCart(custName, currDate);
        }

        //cart.printTotal();                      //first printotal() call

        String userInput = "";
        do {                                    //loop if user selects "Y"
            try {
                cart.addItem(ItemToPurchase.enterItem(scnr, cart));
            } catch (ArrayIndexOutOfBoundsException e) {                //cart full
                System.out.println("SHOPPING CART IS FULL");
            } catch (Exception e) {
                System.out.println("Something went wrong when adding to cart");
            }
            out.println("Add more? (y/n)");
            while (true) {
                try {
                    userInput = scnr.nextLine();
                    if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("n")) {
                        break;
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("Not Y/N, try again (Y/N)");
                }
            }
        } while (userInput.equalsIgnoreCase("y"));

        cart.printTotal();                      //second printtotal() call

        cart.checkout();                        //redeem points (if VIP)

    }

    public static void stage3(){
        Scanner sc = new Scanner(System.in);
        out.println("Press enter to start stage 3");
        sc.nextLine();

        ShoppingCart[] arrayOfCarts =  {new ShoppingCart("normal cart 1", "11 October 2026"),
                                        new ShoppingCart("normal cart 2", "12 October 2026"),
                                        new VIPShoppingCart("VIP cart", "13 October 2026", 121),
                                        new SeniorShoppingCart("Senior cart", "14 Oct 2026", "VC2503")};

        double totalTax = 0;

        for (ShoppingCart cart : arrayOfCarts) {
            for (int i = 0 ; i <= 4 ; i++) {    //adding 4 items
                switch (i) {
                    case 1:
                        ItemToPurchase itemOne = new ItemToPurchase("apple", 4.52, 3.5);
                        cart.addItem(itemOne);
                        break;
                    case 2:
                        ItemToPurchase itemTwo = new ItemToPurchase("wagyu", 89.99, 2);
                        cart.addItem(itemTwo);
                        break;
                    case 3:
                        ItemToPurchase donation = new Donation(3);
                        cart.addItem(donation);
                        break;
                    case 4:
                        ItemToPurchase giftcard = new GiftCard(25);
                        cart.addItem(giftcard);
                        break;
                }
            }

            cart.printTotal();
            totalTax += cart.getTax();
            System.out.println();

        }

        System.out.println("Total items: " + ItemToPurchase.getObjectCount());
        System.out.printf("Total tax: $%.2f\n", totalTax);
    }

    //You may call other stages there but with exception handling enabled
    public static void stage4(Scanner sc){
        sc.nextLine();
        stage2(sc);
    }
}