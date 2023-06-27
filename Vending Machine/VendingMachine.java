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

    // Vending Machine creation method---------------------------------------------------------

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
                    System.out.println(" ");
                    for(int x=0; x<82;x++)
                        System.out.print("═");
                    System.out.println("\nYou didn't added a product on any slot.");
                    System.out.println("You opted for a bare Vending Machine Package with " +
                            "initial zero products on slot.");
                    System.out.println("Don't forget to add products on your Machine later!");
                    for(int x=0; x<82;x++)
                        System.out.print("═");
                    System.out.println("\n");
                }
            }
        }while((selectedSlot-1)!= productSlots.size());
        vmSetMoney(denominationFeedinterface());
        double money = this.denomination.getTotalMoney();

        System.out.println("Total Money: "+money);

    }
    public void vmSetMoney(Denomination denomination){
        this.denomination = denomination;
    }

    // vending machine for overall features---------------------------------------------------
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
    public Denomination denominationFeedinterface(){
        Scanner scanner = new Scanner(System.in);
        Denomination money = new Denomination();
        int choice;
        moneyDisplay();
        do{
            do {
                System.out.print("Select a denomination to supply: ");
                choice = scanner.nextInt();
            }while(choice<1||(choice>11&&choice!=88));
            switch (choice){
                case 1 -> {
                    System.out.print("1 PHP coin qty: ");
                    money.setOnePesoCoin(scanner.nextInt());
                }
                case 2 -> {
                    System.out.print("5 PHP coin qty: ");
                    money.setFivePesoCoin(scanner.nextInt());
                }
                case 3 -> {
                    System.out.print("10 PHP coin qty: ");
                    money.setTenPesoCoin(scanner.nextInt());
                }
                case 4 -> {
                    System.out.print("20 PHP coin qty: ");
                    money.setTwentyPesoCoin(scanner.nextInt());
                }
                case 5 -> {
                    System.out.print("20 PHP bill qty: ");
                    money.setTwentyPesoBill(scanner.nextInt());

                }
                case 6 -> {
                    System.out.print("50 PHP bill qty: ");
                    money.setFiftyPesoBill(scanner.nextInt());
                }
                case 7 -> {
                    System.out.print("100 PHP bill qty: ");
                    money.setOneHundredPesoBill(scanner.nextInt());

                }
                case 8 -> {
                    System.out.print("200 PHP bill qty: ");
                    money.setTwoHundredPesoBill(scanner.nextInt());

                }
                case 9 -> {
                    System.out.print("500 PHP bill qty: ");
                    money.setFiveHundredPesoBill(scanner.nextInt());

                }
                case 10 -> {
                    System.out.print("1000 PHP bill qty: ");
                    money.setThousandPesoBill(scanner.nextInt());
                }
            }
        }while(choice != 88);
        return money;
    }
    // boolean methods for vmstatus-------------------------------------------------------------

    private boolean isSlotEmpty(int slotNumber){
        return (productSlots.get(slotNumber).getBaseProductName().equals("Empty")&&
                productSlots.get(slotNumber).getBaseProductPrice()==-1&&
                productSlots.get(slotNumber).getBaseProductCal()==-1);
    }


    //helper methods-----------------------------------------------------------------------------
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
    private void moneyDisplay(){
        for(int i=0; i<52;i++)
            System.out.print("═");
        System.out.printf("\n%-20s  %-20s [88] Exit\n", "Coins","Bills");
        System.out.printf("[1] %-16s | [5] %-20s\n","1 PHP", "20 PHP");
        System.out.printf("[2] %-16s | [6] %-20s\n","5 PHP", "50 PHP");
        System.out.printf("[3] %-16s | [7] %-20s\n","10 PHP", "100 PHP");
        System.out.printf("[4] %-16s | [8] %-20s\n","20 PHP", "200 PHP");
        System.out.printf("%-20s | [9] %-20s\n"," ", "500 PHP");
        System.out.printf("%-20s | [10] %-20s\n"," ", "1000 PHP");
        for(int i=0; i<52;i++)
            System.out.print("═");
        System.out.println("\n");


    }

}


