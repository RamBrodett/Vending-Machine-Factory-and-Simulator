public class Product {
    private final String name;

    private final double price;

    private int calories;

    public Product(){
        this.name = "Empty";
        this.price = -1;
        this.calories = -1;
    }

    public Product(String name, double price, int calories){
        this.name = name;
        this.price = price;
        this.calories = calories;
    }

    public String getName(){return this.name;}
    public double getPrice(){return this.price;}
    public int getCalories(){return this.calories;}
    public void setCalories(int calories){this.calories = calories;}

}
