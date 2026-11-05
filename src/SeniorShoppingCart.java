public class SeniorShoppingCart extends ShoppingCart {
    private String seniorID;

    public SeniorShoppingCart() {
        this.seniorID = "TEMP99";
    }

    public SeniorShoppingCart(String name, String date, String ID) {
        setCustomerName(name);
        setDate(date);
        if (ID.equalsIgnoreCase("unknown")) {
            this.seniorID = "TEMP99";
        } else {
            this.seniorID = ID;
        }
    }

    public void setID(String seniorID) {
        this.seniorID = seniorID;
    }

    public String getID() {
        return this.seniorID;
    }

    public double getTax() {                            //tax is half for seniors
        double costWithTax = getCurrCost() * ((getTaxRate()/2)/100);
        return twoDec(costWithTax);
    }


}
