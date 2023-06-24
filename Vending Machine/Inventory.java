import java.util.ArrayList;
public class Slots {

    private ArrayList <Product> products;

    public void addProductToInventory(Product product){
        if(products.size() < 10){
            products.add(product);
        }
    }



}
