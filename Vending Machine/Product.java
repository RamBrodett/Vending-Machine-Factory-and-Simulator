public class Product {
    private final String name;

    private double price;

    private double oldPrice;

    private int calories;

    private int oldCalories;

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
    public double getOldPrice(){return this.oldPrice;}
    public int getCalories(){return this.calories;}
    public int getOldCalories() {
        return oldCalories;
    }

    public void setCalories(int calories){this.calories = calories;}

    public void setOldCalories(int oldCalories) {
        this.oldCalories = oldCalories;
    }

    public void setPrice(int price){
        this.price = price;
    }
    public void setOldPrice(int oldPrice){
        this.oldPrice = oldPrice;
    }

}
