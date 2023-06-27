import java.util.Scanner;
import java.util.ArrayList;

public class VendingMachine{
    private Denomination denomination;
    private ArrayList<Slot> productSlots;
    private double currSales;
    private double prevSales;

    public VendingMachine(int slotCapacity){
        this.productSlots = new ArrayList<>(); //Create VENDING MACHINE PRODUCT SLOTS
        for(int i=0; i<slotCapacity; i++)
            productSlots.add(new Slot());
        this.denomination = new Denomination(); //Create MONEY
        addProductsToSlot();
    }

    private void addProductsToSlot(){
        Scanner scanner = new Scanner(System.in);
        consoleSysCom("cls");
        // Display every item slot
        for(int i=0; i<productSlots.size();i++){
            System.out.printf("[%d] %-15s",i+1,productSlots.get(i).getBaseProductName());
            if ((i+1)%2==0)
                System.out.println(" ");
            /*
            if(!isSlotEmpty(i))
                System.out.printf("[%d] %-15s",i,productSlots.get(i).getBaseProductName());
            else
                System.out.printf("[%d] %-15s",i,productSlots.get(i).getBaseProductName());

             */
        }
    }

    public void setProductOnSlot(String name, double price, int calories, int quantity, int slotNo){
        productSlots.set(slotNo, new Slot(name, price, calories, quantity));
    }
    public boolean isSlotEmpty(int slotNumber){
        return (productSlots.get(slotNumber) == null);
    }


    private void consoleSysCom(String command){
        Scanner scanner = new Scanner(System.in);
        switch(command){
            case "cls" -> {
                System.out.print("\033[H\033[2J"); // clear screen
                System.out.flush(); //clear screen
            }
            case "fflush" -> scanner.nextLine();
        }
    }

}

/*
int numSlot=1;
        while(numSlot<=8){
            //display all slot
                //...implement here
            //fill every slot with items, thus  vending machine cannot be empty
            if(isSlotEmpty(numSlot-1)){
                System.out.printf("║    %s    ║\n","VENDING MACHINE FACTORY");
                System.out.println("╚═══════════════════════════════╝");
                System.out.println("SLOT No."+numSlot);
                System.out.println("═════════════════════════════════");
                System.out.print("Enter product name : ");
                String name = scanner.nextLine();
                System.out.print("Enter product price : ");
                double price = scanner.nextDouble();
                System.out.print("Enter product calories : ");
                int calories = scanner.nextInt();
                int numofProducts;
                do {
                    System.out.print("Enter product quantity (Max of 15pcs) : ");
                    numofProducts = scanner.nextInt();
                    scanner.nextLine();
                    if (numofProducts < 0 || numofProducts > 15)
                        System.out.println("Enter a valid quantity");
                }while(numofProducts < 0 || numofProducts > 15);
                System.out.println("═════════════════════════════════");
            }
            consoleSysCom("cls");
            numSlot++;
        }
 */
