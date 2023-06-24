public class Product {
    private final String name;

    private final double price;

    private int quantity;

    private int calories;

    public Product(String name, double price, int calories, int quantity){
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.quantity = quantity;
    }

    public String getName(){return this.name;}
    public double getPrice(){return this.price;}
    public int getCalories(){return this.calories;}

    public int getQuantity(){return this.quantity;}

    public void setCalories(int calories){this.calories = calories;}
    public void setQuantity(int quantity){this.quantity = quantity;}

}
