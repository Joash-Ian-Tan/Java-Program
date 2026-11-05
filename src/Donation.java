import java.util.Scanner;

public class Donation extends ItemToPurchase {

    //constructors
    public Donation() {
        setName("Donation");
    }

    public Donation(int quantity) { //quantity = the number of units input by user
        setName("Donation");
        setPrice(10);
        setQuantity(quantity);
    }

    public static Donation createItem(String name) {
        Scanner sc = new Scanner(System.in);
        System.out.println("A donation, enter the quantity of units ($10 per unit):");
        int donationQuantity = sc.nextInt();

        if (donationQuantity > 0) {
            return new Donation(donationQuantity);
        } else {
            return new Donation();
        }
    }

    //override toString()
    @Override
    public String toString() {              //setting the output of the object to a String
        return getName() + " " + getQuantity() + " @ $"
                + getPrice() + " = $" + getTotalPrice();
    }
}
