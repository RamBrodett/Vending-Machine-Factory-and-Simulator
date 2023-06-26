import java.util.Scanner;

public class VendingMachine{
    private Denomination denomination;
    private Slot[] productSlots;
    private double currSales;
    private double prevSales;

    public VendingMachine(){
        this.productSlots = new Slot[10]; //Create VENDING MACHINE PRODUCT SLOTS
        this.denomination = new Denomination(); //Create MONEY
        addProductsToSlot();
    }

    private void addProductsToSlot(){
        Scanner scanner = new Scanner(System.in);
        int numSlot=0;
        while(numSlot < 10){
            //fill every slot with items, thus  vending machine cannot be empty.
            if(isSlotEmpty(numSlot)){
                String name = scanner.nextLine();
                double price = scanner.nextDouble();
                int calories = scanner.nextInt();
                int numofProducts;
                do {
                    System.out.println("Max of 15pcs per product");
                    numofProducts = scanner.nextInt();
                    if (numofProducts < 0 || numofProducts > 15)
                        System.out.println("Enter a valid quantity");
                }while(numofProducts < 0 || numofProducts > 15);

            }
            numSlot++;
        }
    }

    public void setProductOnSlot(String name, double price, int calories, int quantity, int slotNo){
        productSlots[slotNo] = new Slot(name,price,calories,quantity);

    }
    public boolean isSlotEmpty(int slotNumber){
        return (productSlots[slotNumber] == null);
    }

}
