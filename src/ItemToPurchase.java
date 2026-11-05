import java.util.Scanner;

public class ItemToPurchase {
    private String itemName;
    private double itemPrice;
    private double itemQuantity;
    private static int ObjectCount;

    ItemToPurchase() {      //default values if no values are set
        itemName = "none";
        itemPrice = 0;
        itemQuantity = 0;
        ObjectCount++;
    }

    ItemToPurchase(String name, double price, double quantity) {
        itemName = name;
        itemPrice = price;
        itemQuantity = quantity;
        ObjectCount++;
    }

    public void setName(String name) {      //setter for name
        itemName = name;
    }
    public String getName() {               //getter for name
        return itemName;
    }

    public void setPrice(double price) {       //setter for price
        itemPrice = price;
    }
    public double getPrice() {                 //getter for price
        return itemPrice;
    }

    public void setQuantity(double quantity) { //setter for qty
        itemQuantity = quantity;
    }
    public double getQuantity() {              //getter for qty
        return itemQuantity;
    }

    public double getTotalPrice() {            //calculate total within class
        double total = itemPrice * itemQuantity;
        double floored = Math.ceil(total * 100) / 100;     //round to 2 decimal places
        return floored;
    }

    public static int getObjectCount() {
        return ObjectCount;
    }

    public static void resetObjectCount() {
        ObjectCount = 0;
    }

    public static ItemToPurchase enterItem(Scanner sc) {
        ItemToPurchase item;

        System.out.println("Enter the name of the item: ");
        String name = sc.nextLine();
        //gift card
        if (name.equalsIgnoreCase("giftcard") || name.equalsIgnoreCase("gift card")) {
            item = GiftCard.createItem(name);
        }
        //donation
        else if (name.equalsIgnoreCase("donation")) {
            item = Donation.createItem(name);
        }
        //normal item
        else {
            item = ItemToPurchase.createItem(name);

        }
        return item;
    }




    public static ItemToPurchase createItem(String name) {

            double price = 0;
            double quantity = 0;
            Scanner sc;
//todo: check for and throw invalidvariableexception after each input

            System.out.println("Enter the price of " + name + ": ");
            while (true) {
                try {
                    sc = new Scanner(System.in);
                    price = sc.nextDouble();
                    if (price < 0) {
                        throw new InvalidVariableException("Price cannot be negative, try again:");
                    } else {
                        break;
                    }
                } catch (InvalidVariableException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Not a valid number, try again:");
                }
            }


            System.out.println("Enter quantity: ");
            while (true) {
                try {
                    sc = new Scanner(System.in);
                    quantity = sc.nextDouble();
                    if (quantity <= 0) {
                        throw new InvalidVariableException("Quantity cannot be 0 or lower, try again:");
                    } else {
                        break;
                    }
                } catch (InvalidVariableException e) {
                    System.out.println(e.getMessage());
                }
                catch (Exception e) {
                    System.out.println("Not a valid number, try again:");
                }
            }
            return new ItemToPurchase(name, price, quantity);

    }

    public static ItemToPurchase enterItem(Scanner sc, ShoppingCart cart) {    //method overloading for stage 2 & 4
        ItemToPurchase item;
        String name = "";

        System.out.println("Enter the name of the item: ");

        while (true) {
            try {
                name = sc.nextLine();
                if (name.isEmpty()) {
                    throw new InvalidVariableException("Name cannot be empty, try again:");
                } else {
                    break;
                }
            } catch (InvalidVariableException e) {
                System.out.println(e.getMessage());
            }
        }

        if (cart.checkDuplicate(name)) {
            //gift card
            if (name.equalsIgnoreCase("giftcard") || name.equalsIgnoreCase("gift card")) {
                item = GiftCard.createItem(name);
            }
            //donation
            else if (name.equalsIgnoreCase("donation")) {
                item = Donation.createItem(name);
            }
            //normal item
            else {
                item = ItemToPurchase.createItem(name);

            }
            return item;
        } else {
            return null;
        }


    }

    @Override
    public String toString() {              //setting the output of the object to a String
        return itemName + " " + itemQuantity + " @ $"
                + itemPrice + " = $" + getTotalPrice();
    }

}
