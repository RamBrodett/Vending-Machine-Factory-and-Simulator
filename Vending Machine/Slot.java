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

    public Slot(String name, double price, int calories,int capacity){
        this.numProductsSold = 0;
        baseProduct = new Product(name,price,calories);
    }

    public String getBaseProductName() {
        return baseProduct.getName();
    }
}