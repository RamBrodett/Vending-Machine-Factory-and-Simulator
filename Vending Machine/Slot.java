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
    /**
     * number of products sold
     */
    private int numProductsSold;
    /**
     * number of special products sold
     */
    private int specProductsSold;

    /**
     * base product is for assignment of what type of item this slot will hold.
     */
    private Product baseProduct;

    /**
     * quantity of the products in this slot.
     */
    private ArrayList<Product> products = new ArrayList<>();


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

    /**
     * Constructor for new slot
     * @param name name to be used by the item
     * @param calories calories of the item
     * @param qnty number of the products in slot.
     */
    public Slot(String name, int calories,int qnty){
        this.numProductsSold = 0;
        baseProduct = new Product(name,0,calories);
        for(int i = 0; i< qnty; i++){
            products.add(i,baseProduct);
        }
    }

    /**
     * for editing item price
     * @param itemNewPrice price to be used.
     */

    public void editItemPrice(float itemNewPrice) {
        baseProduct.setPrice(itemNewPrice);

    }

    /**
     * Getter for the products
     * @return products
     */
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
     * Sets the number of products sold from the slot.
     * @param numProductsSold the number of products sold to set
     */

    public void setNumProductsSold(int numProductsSold) {
        this.numProductsSold += numProductsSold;
    }


    /**
     * Sets the number of special item product sold.
     * @param specProductsSold number of special products sold
     */
    public void setSpecProductsSold(int specProductsSold) {
        this.specProductsSold += specProductsSold;
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