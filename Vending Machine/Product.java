/**
 * Product in a vending machine.
 * <p>
 *     Provides methods to retrieve and modify the product attributes.
 *     Can be used to store and manage product information in a vending machine.
 *     Assumes non-null name and non-negative price, calories, old price,
 *     and old calories values.
 * </p>
 *
 * @author Ram Brodett
 * @author Luke Regalado
 * @version 07/1/2023
 */


public class Product {
    private final String name;

    private double price;

    private double oldPrice;

    private int calories;

    private int oldCalories;

    /**
     * Default constructor for the Product class.
     * <p>
     * Sets the name to "Empty", price to -1, and calories to -1 for slots on initial
     * creation of vending machine.
     * </p>
     */

    public Product(){
        this.name = "Empty";
        this.price = -1;
        this.calories = -1;
    }

    /**
     * constructor for the Product class.
     * <p>
     * Sets the name, price, and calories of the product.
     * </p>
     *
     * @param name     the name of the product
     * @param price    the price of the product
     * @param calories the calories of the product
     */

    public Product(String name, double price, int calories){
        this.name = name;
        this.price = price;
        this.calories = calories;
    }

    /**
     * Returns the name of the product.
     * @return the name of the product
     */

    public String getName(){return this.name;}

    /**
     * Returns the price of the product.
     * @return the price of the product
     */
    public double getPrice(){return this.price;}

    /**
     * Returns the old price of the product.
     * @return the old price of the product
     */
    public double getOldPrice(){return this.oldPrice;}

    /**
     * Returns the calories of the product.
     * @return the calories of the product
     */
    public int getCalories(){return this.calories;}

    /**
     * Returns the old calories of the product.
     * @return the old calories of the product
     */
    public int getOldCalories() {
        return oldCalories;
    }

    /**
     * Sets the calories of the product.
     * @param calories the calories to set for the product
     */
    public void setCalories(int calories){this.calories = calories;}

    /**
     * Sets the old calories of the product.
     * @param oldCalories the old calories to set for the product
     */
    public void setOldCalories(int oldCalories) {
        this.oldCalories = oldCalories;
    }

    /**
     * Sets the price of the product.
     * @param price the price to set for the product
     */
    public void setPrice(int price){
        this.price = price;
    }

    /**
     * Sets the old price of the product.
     * @param oldPrice the old price to set for the product
     */

    public void setOldPrice(int oldPrice){
        this.oldPrice = oldPrice;
    }

}
