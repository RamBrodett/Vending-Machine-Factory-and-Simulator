import java.util.ArrayList;
public class Slot {

    private ArrayList <Product> products;
    private int numProductsSold;
    private Product baseProduct;

    public Slot(){
        this.numProductsSold = 0;
        baseProduct = new Product();
    }

    public void addProductToInventory(Product product){
        if(products.size() < 10){
            products.add(product);
        }
    }
}