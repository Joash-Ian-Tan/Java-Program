import java.util.Scanner;

public class ShoppingCart {
    private String customerName;
    private String currentDate;
    private ItemToPurchase[] cartItems;
    private static final int CAPACITY = 10;
    private int itemCount;
    private double cartCost;
    private static double taxRate = 5;

    public ShoppingCart() {            //default values if no values are set
        customerName = "Unknown";
        currentDate = "15 August 2026";
        cartItems = new ItemToPurchase[CAPACITY];
        itemCount = 0;
    }

    public ShoppingCart(String name, String date) {
        customerName = name;
        currentDate = date;
        cartItems = new ItemToPurchase[CAPACITY];
        itemCount = 0;
    }

    public String getCustomerName() {           //getter for name
        return customerName;
    }
    public void setCustomerName(String name) {  //setter for name
        customerName = name;
    }

    public String getDate() {                   //getter for date
        return currentDate;
    }
    public void setDate(String date) {          //setter for date
        currentDate = date;
    }


    public boolean checkDuplicate (String name) { //checks for duplicate names
        boolean valid = true;
        try {
            for (int i = 0; i <= (cartItems.length); i++) {
                if (cartItems[i] == null || name == null) { //duplicate name
                    break;
                } else if ((name.equalsIgnoreCase(cartItems[i].getName()))) { //duplicate name
                    valid = false;
                    throw new DuplicateItemException("ITEM ALREADY EXISTS");
                }
            }
        } catch (DuplicateItemException e) {
            System.out.println(e.getMessage());
        }

        return valid;       //determines if item was entered into cart
    }


    public void addItem(ItemToPurchase item) throws ArrayIndexOutOfBoundsException {
        for (int i = 0 ; i <= (cartItems.length) ; i++) {
            if (cartItems[i] == null) {                 //success. Adds item to next available place in array
                cartItems[i] = item;
                break;
            }
        }
    }

    public int getNumItemsInCart() {
        itemCount = 0;
        for (int i = 0 ; i < cartItems.length ; i++) {      //counts elements in array that are NOT null
            if (cartItems[i] != null) {
                itemCount += 1;
            }
        }
        return itemCount;
    }

    public void listCartItems() {   //prints elements of the array that are NOT null
        for (int i = 0 ; i < cartItems.length; i++) {
            if (cartItems[i] != null) {
                System.out.println(cartItems[i]);
            }
        }
    }

    public double getCostOfCart() {        //calculates cost of all items in cart
        cartCost = 0;
        for (int i = 0 ; i < cartItems.length ; i++) {
            if (cartItems[i] != null) {
                cartCost += cartItems[i].getTotalPrice();
            }
        }
        double rounded = twoDec(cartCost);
        setCurrCost(rounded);
        return rounded;
    }

    public double getCurrCost() {
        return cartCost;
    }

    public void setCurrCost(double cost) {
        cartCost = cost;
    }

    public void clearCart() {
        for (int i = 0 ; i < cartItems.length ; i++) {  //remove all elements
            cartItems[i] = null;
        }
        ItemToPurchase.resetObjectCount();
    }

    public void printTotal() {
        System.out.println(customerName + " - " + currentDate);
        if (getNumItemsInCart() == 0) {                     //if all elements in array are null
            System.out.println("SHOPPING CART IS EMPTY");
        } else {                                            //print receipt
            System.out.println("number of items: " + getNumItemsInCart()); //number of items
            listCartItems();                                               //list of items (name, qty, price, total)
            System.out.println("Total: $" + getCostOfCart());              //total cost of items
            System.out.println("Tax: $" + getTax());
        }
    }

    public void modifyItem() {
        Scanner sc = new Scanner(System.in);
        boolean itemFound = false;

        System.out.println("Enter name of the item: ");
        String itemName = sc.nextLine();
        for (ItemToPurchase value : cartItems) {
            if (value != null) {
                if (itemName.equalsIgnoreCase(value.getName())) {   //name found in cart
                    System.out.println("Please enter the new quantity: ");
                    int newQty = sc.nextInt();
                    value.setQuantity(newQty);
                    itemFound = true;
                    break;
                }
            }
        }
        if (itemFound == false) {       //name not found in cart
            System.out.println(itemName + " not found in cart.");
        }
        printTotal();

    }

    public void checkout() {
        boolean empty = true;
        for (int i = 0 ; i < cartItems.length ; i++) {  //check for items in cart
            if (cartItems[i] != null) {
                empty = false;
                break;                                  //break if one item is found
            }
        }
        if (empty == true) {                            //if cart is empty
            System.out.println("SHOPPING CART IS EMPTY");
        } else {                                        //if cart has items
            //printTotal();
            System.out.println("\nThank you for shopping.");
            clearCart();
        }

    }

    public static double getTaxRate() {
        return taxRate;
    }

    public static void setTaxRate(int rate) {
        taxRate = rate;
    }

    public double getTax() {
        double costWithTax = getCurrCost() * (taxRate/100);
        return twoDec(costWithTax);
    }

    public double twoDec(double cost) {     //additional function to round to 2 dec places
        return Math.ceil(cost * 100) / 100;
    }
}
