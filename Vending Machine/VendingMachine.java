import javax.swing.plaf.synth.SynthStyleFactory;
import java.util.Scanner;

public class VendingMachine{
    private Denomination denomination;
    private Slot[] productSlots;
    private double currSales;
    private double prevSales;

    public VendingMachine(){
        this.productSlots = new Slot[8]; //Create VENDING MACHINE PRODUCT SLOTS
        this.denomination = new Denomination(); //Create MONEY
        addProductsToSlot();
    }

    private void addProductsToSlot(){
        Scanner scanner = new Scanner(System.in);
        consoleSysCom("cls");
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
    }

    public void setProductOnSlot(String name, double price, int calories, int quantity, int slotNo){
        productSlots[slotNo] = new Slot(name,price,calories,quantity);
    }
    public boolean isSlotEmpty(int slotNumber){
        return (productSlots[slotNumber] == null);
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
