import java.util.ArrayList;
public class Slot {

    private ArrayList <Product> products;
    private int numProductsSold;
    private int productQuantity;
    private Product baseProduct;

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

    public int getProductQuantity() {
        return productQuantity;
    }

    public int getNumProductsSold(){
        return numProductsSold;
    }

    public boolean setProductQuantity(int productQuantity) {
        boolean success = false;
        if(this.productQuantity + productQuantity <= 15) {
            this.productQuantity += productQuantity;
            success = true;
        }
        return  success;
    }
}