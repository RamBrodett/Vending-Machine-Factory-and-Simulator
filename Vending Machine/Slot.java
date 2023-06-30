import java.util.ArrayList;
public class Slot {

    private ArrayList <Product> products;
    private int numProductsSold;
    private int productQuantity;
    private int oldProductsSold;
    private  int oldProductQuantity;
    private Product baseProduct;
    private boolean isEdited;

    Slot(){
        this.productQuantity = 0;
        this.numProductsSold = 0;
        baseProduct = new Product();
    }

    public Slot(String name, double price, int calories,int qnty){
        this.numProductsSold = 0;
        this.productQuantity = qnty;
        baseProduct = new Product(name,price,calories);
    }

    public String getBaseProductName() {
        return baseProduct.getName();
    }
    public double getBaseProductPrice(){
        return baseProduct.getPrice();
    }
    public double getBaseProductOldPrice(){
        return baseProduct.getOldPrice();
    }
    public double getBaseProductCal(){
        return baseProduct.getCalories();
    }
    public double getBaseProductOldCal(){
        return baseProduct.getOldCalories();
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public int getNumProductsSold(){
        return numProductsSold;
    }

    public int getOldProductsSold() {
        return oldProductsSold;
    }

    public int getOldProductQuantity() {
        return oldProductQuantity;
    }

    public boolean isEdited() {
        return isEdited;
    }

    public void setBaseProductOldPrice(double oldPrice) {
        baseProduct.setOldPrice((int) oldPrice);
    }

    public void setBaseProductOldCalories(double oldCalories){
        baseProduct.setOldCalories((int)oldCalories);
    }

    public void setNumProductsSold(int numProductsSold) {
        this.numProductsSold += numProductsSold;
    }

    public void setOldProductsSold(int oldProductsSold) {
        this.oldProductsSold = oldProductsSold;
    }

    public void setOldProductQuantity(int oldProductQuantity) {
        this.oldProductQuantity = oldProductQuantity;
    }

    public void setEdited (boolean isEdited){
        this.isEdited = isEdited;
    }

    public boolean setProductQuantity(int productQuantity) {
        boolean success = false;
        if(this.productQuantity + productQuantity <= 15) { //setting max capacity of 15;
            this.productQuantity += productQuantity;
            success = true;
        }
        return  success;
    }
}