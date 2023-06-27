import java.util.Scanner;
import java.util.ArrayList;

public class VendingMachine{
    private Denomination denomination;
    private final ArrayList<Slot> productSlots;
    private double currSales;
    private double prevSales;

    public VendingMachine(int slotCapacity){
        this.productSlots = new ArrayList<>(); //Create VENDING MACHINE PRODUCT SLOTS
        for(int i=0; i<slotCapacity; i++)
            productSlots.add(new Slot());
        this.denomination = new Denomination(); //Create MONEY
        vmPackageSelection();
    }

    private void vmPackageSelection(){
        Scanner scanner = new Scanner(System.in);
        consoleSysCom("cls");
        System.out.println("ADD ITEMS ON SLOT [OPTIONAL PACKAGE]");
        int selectedSlot, i;
        do {
            // Display every item slot
            for(i=0; i<productSlots.size();i++){
                System.out.printf("[%d] %-15s",i+1,productSlots.get(i).getBaseProductName());
                if ((i+1)%2==0)
                    System.out.println(" ");
            }
            System.out.printf("[%d] Exit Selection.\n",i+1);
            System.out.print("Select a slot to add an item:");
            selectedSlot = scanner.nextInt();
            if((selectedSlot-1)!=productSlots.size()){
                setProductOnSlot(selectedSlot-1);
            }
            else if((selectedSlot-1)==productSlots.size()){
                int emptySlots=0;
                for(int j=0; j<productSlots.size();j++) {
                    if(isSlotEmpty(j))
                        emptySlots++;
                }
                if(emptySlots==productSlots.size()){
                    System.out.println("You didn't added a product on any slot.");
                    System.out.println("You opted for a bare Vending Machine Package with initial zero products on slot.");
                    System.out.println("Don't forget to add products on your Machine later!");
                }
            }
        }while((selectedSlot-1)!= productSlots.size());


    }

    public void setProduct(String name, double price, int calories, int quantity, int slotNo){
        productSlots.set(slotNo, new Slot(name, price, calories, quantity));
    }
    public void setProductOnSlot(int slotNumber){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Product name: ");
        String name = scanner.nextLine();
        System.out.print("Product price : ");
        double price = scanner.nextDouble();
        System.out.print("Product calories : ");
        int calories = scanner.nextInt();
        int numofProducts;
        do {
            System.out.print("Enter product quantity (Max of 15pcs) : ");
            numofProducts = scanner.nextInt();
            scanner.nextLine();
            if (numofProducts < 0 || numofProducts > 15)
                System.out.println("Enter a valid quantity");
        }while(numofProducts < 0 || numofProducts > 15);
        setProduct(name,price,calories,numofProducts,slotNumber);
    }
    private boolean isSlotEmpty(int slotNumber){
        return (productSlots.get(slotNumber).getBaseProductName().equals("Empty")&&
                productSlots.get(slotNumber).getBaseProductPrice()==-1&&
                productSlots.get(slotNumber).getBaseProductCal()==-1);
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
