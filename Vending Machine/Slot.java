import java.util.ArrayList;

/**
 * Slot of Vending Machine.
 * <p>
 *     A slot represents a specific location in vending machine where a product can be placed.
 *     It keep tracks of the product quantity, number of products sold, old Product Quantity.
 *     The base product represents the default product in the slot.
 * </p>
 *
 * @author Ram Brodett
 * @author Luke Regalado
 * @version 07/1/2023
 */
public class Slot {
    private int numProductsSold;
    private int specProductsSold;
    private int oldProductQuantity;
    private Product baseProduct;
    private ArrayList<Product> products = new ArrayList<>();
    private boolean isEdited;

    /**
     * Constructs a new Slot object with default values.
     */

    Slot(){
        this.numProductsSold = 0;
        baseProduct = new Product();
    }

    /**
     * Constructs a new Slot object with the specified parameters.
     * @param name name supplied for the product.
     * @param price price supplied for the product.
     * @param calories calories supplied for the product.
     * @param qnty quantity supplied for the product.
     */

    public Slot(String name, double price, int calories,int qnty){
        this.numProductsSold = 0;
        baseProduct = new Product(name,price,calories);
        for(int i = 0; i< qnty; i++){
            products.add(i,baseProduct);
        }
    }
    public Slot(String name, int calories,int qnty){
        this.numProductsSold = 0;
        baseProduct = new Product(name,0,calories);
        for(int i = 0; i< qnty; i++){
            products.add(i,baseProduct);
        }
    }

    public void editItemPrice(float itemNewPrice) {
        baseProduct.setPrice(itemNewPrice);

    }

    public ArrayList<Product> getProducts(){
        return products;
    }

    /**
     * Gets the name of the base product.
     * @return the name of the base product.
     */
    public String getBaseProductName() {
        return baseProduct.getName();
    }

    /**
     * Gets the price of the base product.
     *
     * @return the price of the base product
     */
    public double getBaseProductPrice(){
        return baseProduct.getPrice();
    }

    /**
     * Gets the old price of the base product.
     * @return the old price of the base product.
     */
    public double getBaseProductOldPrice(){
        return baseProduct.getOldPrice();
    }

    /**
     * Gets the calories of the base product.
     * @return the calories of the base product
     */
    public double getBaseProductCal(){
        return baseProduct.getCalories();
    }

    /**
     * Gets the old calories of the base product.
     * @return the old calories of the base product
     */
    public double getBaseProductOldCal(){
        return baseProduct.getOldCalories();
    }

    /**
     * Gets the quantity of products in the slot.
     * @return the quantity of products in the slot
     */
    public int getProductQuantity() {
        return products.size();
    }

    /**
     * Gets the number of products sold from the slot.
     * @return the number of products sold from the slot
     */

    public int getNumProductsSold(){
        return numProductsSold;
    }


    /**
     * Gets the number of old products sold from the slot.
     * @return the number of old products sold from the slot
     */

    public int getSpecProductsSold() {
        return specProductsSold;
    }


    /**
     * Gets the old product quantity in the slot.
     * @return the old product quantity in the slot
     */

    public int getOldProductQuantity() {
        return oldProductQuantity;
    }


    /**
     * Checks if the slot has been edited.
     * @return true if the slot has been edited, false otherwise
     */
    public boolean isEdited() {
        return isEdited;
    }

    /**
     * Sets the old price of the base product.
     * @param oldPrice the old price to set
     */

    public void setBaseProductOldPrice(double oldPrice) {
        baseProduct.setOldPrice((int) oldPrice);
    }

    /**
     * Sets the old calories of the base product.
     * @param oldCalories the old calories to set
     */

    public void setBaseProductOldCalories(double oldCalories){
        baseProduct.setOldCalories((int)oldCalories);
    }

    /**
     * Sets the number of products sold from the slot.
     * @param numProductsSold the number of products sold to set
     */

    public void setNumProductsSold(int numProductsSold) {
        this.numProductsSold += numProductsSold;
    }

    /**
     * Sets the number of old products sold from the slot.
     * @param oldProductsSold the number of old products sold to set
     */
    public void setSpecProductsSold(int specProductsSold) {
        this.specProductsSold += specProductsSold;
    }

    /**
     * Sets the old product quantity in the slot.
     * @param oldProductQuantity the old product quantity to set
     */

    public void setOldProductQuantity(int oldProductQuantity) {
        this.oldProductQuantity = oldProductQuantity;
    }

    /**
     * Sets whether the slot has been edited or not.
     * @param isEdited true if the slot has been edited, false otherwise
     */

    public void setEdited (boolean isEdited){
        this.isEdited = isEdited;
    }

    /**
     * Sets the quantity of products in the slot.
     * @param productQuantity the quantity of products to set
     * @return true if the product quantity was successfully set, false otherwise
     */

    public boolean setProductQuantity(int productQuantity) {
        boolean success = false;
        if (products.size() + productQuantity <= 15) { //setting max capacity of 15;
            for (int i = 0; i < productQuantity; i++) {
                products.add(baseProduct);
            }
            success = true;
        }

        return  success;
    }
}