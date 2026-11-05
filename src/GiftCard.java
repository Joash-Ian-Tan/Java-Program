import java.util.Scanner;

public class GiftCard extends ItemToPurchase {

    //constructors
    public GiftCard() {
        setName("A gift card");

    }

    public GiftCard(int price) {
        setName("A gift card");
        setPrice(price);
    }

    public GiftCard (String name, int price) { //price = amount of money input by user
        setName(name);
        setPrice(price);
        setQuantity(1);
    }

    //overridden total method
    public int getTotalPoints() {
        return (int)getPrice() * 50;
    }

    public static GiftCard createItem(String name) {
        Scanner sc = new Scanner(System.in);
        System.out.println("One gift card, pls enter the price:");
        int giftCardPrice = (int) sc.nextDouble(); //type casting to floor doubles

        if (giftCardPrice > 0) {
            return new GiftCard(name, giftCardPrice);
        } else {
            return new GiftCard();
        }

    }

    //override toString()
    @Override
    public String toString() {              //setting the output of the object to a String
        return "A gift card" + " @ $"
                + getPrice() + " = $" + getPrice() + " or " + getTotalPoints() + " points";
    }
}
