//import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;
import java.util.ArrayList;

public class VendingMachine{
    private Denomination denomination;
    private final ArrayList<Slot> productSlots;
    private double currSales;
    private double prevSales;
    private final Denomination insertedMoney;

    public VendingMachine(int slotCapacity){
        this.productSlots = new ArrayList<>(); //Create VENDING MACHINE PRODUCT SLOTS
        for(int i=0; i<slotCapacity; i++)
            productSlots.add(new Slot());
        this.denomination = new Denomination(); //Create MONEY
        this.insertedMoney = new Denomination(); // create
        vmPackageSelection();                   //call process prompts in vending machine creation
    }

    // Vending Machine creation method---------------------------------------------------------

    private void vmPackageSelection(){
        Scanner scanner = new Scanner(System.in);
        consoleSysCom("cls");
        System.out.println("\nADD INITIAL ITEMS ON SLOT [OPTIONAL PACKAGE]");
        int selectedSlot;
        do {
            // Display every item slot
            displayProducts(1);
            System.out.print("Select a slot to add an item: ");
            selectedSlot = scanner.nextInt();
            if((selectedSlot-1)!=productSlots.size()){
                setProductOnSlot(selectedSlot-1, true);
            }
            else if((selectedSlot-1)==productSlots.size()){
                int emptySlots=0;
                for(int j=0; j<productSlots.size();j++) {
                    if(isSlotEmpty(j))
                        emptySlots++;
                }
                if(emptySlots==productSlots.size()){
                    System.out.println(" ");
                    for(int x=0; x<67;x++)
                        System.out.print("═");
                    System.out.println("""
                            
                            You did not add a product to any slot; instead, you chose a bare
                            Vending Machine Package with zero products on slot.
                            Remember to add items to your Machine later!""");

                    for(int x=0; x<67;x++)
                        System.out.print("═");
                    System.out.println("\n");
                }
            }
        }while((selectedSlot-1)!= productSlots.size());

        for(int x=0; x<52;x++) System.out.print("═");
        System.out.println("""
                
                You may like to place your initial money in your
                Vending Machine. You may do so by selecting the
                denomination and quantity, or you may exit.""");
        vmSetMoney(denominationFeedInterface());
        double money = this.denomination.getTotalMoney();
        System.out.println("Total Money: " + money);
    }
    public void vmSetMoney(Denomination denomination){
        this.denomination = denomination;
    }

    // start of vending machine methods for overall features------------------------------------------------------------
    public void setProduct(String name, double price, int calories, int quantity, int slotNo){
        productSlots.set(slotNo, new Slot(name, price, calories, quantity));
    }

    public void setProductOnSlot(int slotNumber, boolean ifReplace){
        Scanner scanner = new Scanner(System.in);

        String name;
        if (ifReplace == true) {
            System.out.print("Product name: ");
            name = scanner.nextLine();
        }

        else {
            System.out.printf("Product Slot: %s\n", productSlots.get(slotNumber).getBaseProductName());
            name = productSlots.get(slotNumber).getBaseProductName();
        }

        System.out.print("Product price : ");
        double price = scanner.nextDouble();
        System.out.print("Product calories : ");
        int calories = scanner.nextInt();
        int numofProducts;

        if (ifReplace == true) {
            do {
                System.out.print("Enter product quantity (Max of 15pcs) : ");
                numofProducts = scanner.nextInt();
                scanner.nextLine();
                if (numofProducts < 0 || numofProducts > 15)
                    System.out.println("Enter a valid quantity");
            } while (numofProducts < 0 || numofProducts > 15);
            if (numofProducts > 0)
                productSlots.get(slotNumber).setNumProductsSold(-productSlots.get(slotNumber).getNumProductsSold());    //sets amount sold to 0 upon restocking
        }

        else {
            do {
                System.out.println("Enter quantity to restock (Max of 15pcs): ");
                numofProducts = scanner.nextInt();
                numofProducts += productSlots.get(slotNumber).getProductQuantity();
                if (numofProducts < 0 || numofProducts > 15)
                    System.out.println("Enter a valid quantity");
            } while (numofProducts < 0 || numofProducts > 15);
        }
        setProduct(name,price,calories,numofProducts,slotNumber);
    }

    // user - vending transaction [simulation of using the vending machine]
    public void vendingMachineUserTransaction(){
        Denomination insertedMoney = new Denomination();
        Scanner scanner = new Scanner(System.in);
        String decision;
        boolean ongoingTransaction = true;

        vmSimulationDisplay(1);

        do{
            while(ongoingTransaction){
                displayProducts(0); // 0 value since it is for display only not selection.
                insertedMoney = denominationFeedInterface();
                System.out.println("Total inserted money: " + insertedMoney.getTotalMoney());
                //START OF IMPLEMENTATION BASED ON COMMENTS-----------------------------------------------------------------------------
                displayProducts(1); // 1 value since it is selection of item already.
                int selected;
                do {
                    System.out.print("Selected item: ");
                    selected = scanner.nextInt();
                    scanner.nextLine();
                    if ((selected < 1 || selected > productSlots.size())) System.out.println("Select a valid item");
                } while ((selected < 1 || selected > productSlots.size()) && (selected - 1) != productSlots.size());

                if ((selected-1) == productSlots.size()) {
                    clearDenomination(insertedMoney);
                    ongoingTransaction = false;
                } else if (!isSlotEmpty(selected - 1)) {

                    int change = insertedMoney.getTotalMoney() - (int) productSlots.get(selected - 1).getBaseProductPrice(); //money of user - price
                    Denomination changeDenom = findDenomination(change, denomination);

                    if (changeDenom.getTotalMoney() - change != 0) {  //if true, then change isn't possible
                        System.out.println("Unable to give change. Unsuccessful transaction.");
                    }
                    else if (insertedMoney.getTotalMoney() < productSlots.get(selected - 1).getBaseProductPrice()) {
                        System.out.println("Insufficient balance. Unsuccessful transaction.");
                    }
                    else {
                        addToDenomination(denomination, insertedMoney);     //transfer money from inserted to VM
                        differenceDenomination(denomination, changeDenom);   //remove change from VM
                        insertedMoney = changeDenom;                        //money of user = change
                        productSlots.get(selected-1).setProductQuantity(-1);
                        productSlots.get(selected-1).setNumProductsSold(1);
                        System.out.printf("Purchase successful! Your change is %3.2f\n", (double) change);
                        displayDenominations(changeDenom);
                    }
                }
                ongoingTransaction = false;
            }

             //END OF IMPLEMENTATION BASED ON COMMENTS-------------------------------------------------------------------------------
            /*
             to do here is once naka select ng product
                assess if kaya mag change if hindi unsuccessful transaction
                pag pwede proceed to the change calculation, dispensing change and product.
             */

            System.out.println("Do you want to make transactions again?");
            do {
                System.out.print("Input: ");
                decision = scanner.nextLine();

                if(!(decision.equalsIgnoreCase("yes"))&& // to continue asking while answer is
                   !(decision.equalsIgnoreCase("no")))   // neither yes nor no.
                      System.out.println("type yes or no only.");
                else if(decision.equalsIgnoreCase("yes"))
                    ongoingTransaction = true;

            }while(!(decision.equalsIgnoreCase("yes"))&& !(decision.equalsIgnoreCase("no")));
        }while(decision.equalsIgnoreCase("yes"));

        vmSimulationDisplay(2);
    }

    //maintenance methods-----------------------------------------------------------------------
    public void editItems() {
        Scanner scanner = new Scanner(System.in);
        int selected;
        int choice;


        do {
            displayProducts(3);
            System.out.print("Select a slot to edit: ");
            selected = scanner.nextInt();

            if ((selected -1) != productSlots.size()) {
                if (isSlotEmpty(selected - 1) || productSlots.get(selected - 1).getProductQuantity() == 0) {
                    setProductOnSlot(selected-1,true);
                }
                else if (!(isSlotEmpty(selected - 1))) {
                    setProductOnSlot(selected - 1, false);
                }
            }
        } while (selected != productSlots.size()+1);


    }

    // boolean methods for vmstatus-------------------------------------------------------------

    private boolean isSlotEmpty(int slotNumber){
        return (productSlots.get(slotNumber).getBaseProductName().equals("Empty")&&
                productSlots.get(slotNumber).getBaseProductPrice()==-1&&
                productSlots.get(slotNumber).getBaseProductCal()==-1);
    }

    private boolean isSlotStocked(int slotNumber){
        return(productSlots.get(slotNumber).getProductQuantity() > 0);
    }


    // methods for money related calculations and processes and transaction subprocesses--------------------------------

    //interface for feeding the machine with money
    public Denomination denominationFeedInterface(){
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

    private Denomination findDenomination(float money, Denomination inventory) {                  //give money in denominations based on available denoms
        Denomination moneyDenom = new Denomination();                                           //will have denominations of the change

        if (money >= 1000) {                                                                     //makes sure variable change is above 1000
            if (inventory.getThousandPesoBill()*1000 >= money) {                                //if there is enough 1k bills in VM to supply 1k denoms of change
                moneyDenom.setThousandPesoBill((int)money / 1000);
            }
            else {                                                                              //if there aren't enough 1k bills in VM
                moneyDenom.setThousandPesoBill(inventory.getThousandPesoBill());
            }
            if (moneyDenom.getThousandPesoBill() != 0)
                money %= moneyDenom.getThousandPesoBill() * 1000;
        }

        if (money >= 500) {                                       //500
            if (inventory.getFiveHundredPesoBill()*500 >= money) {
                moneyDenom.setFiveHundredPesoBill((int)money / 500);
            }
            else {
                moneyDenom.setFiveHundredPesoBill(inventory.getFiveHundredPesoBill());
            }
            if (moneyDenom.getFiveHundredPesoBill() != 0)
                money %= moneyDenom.getFiveHundredPesoBill() * 500;
        }

        if (money >= 200) {                                       //200
            if (inventory.getTwoHundredPesoBill()*200 >= money) {
                moneyDenom.setTwoHundredPesoBill((int)money / 200);
            }
            else {
                moneyDenom.setTwoHundredPesoBill(inventory.getTwoHundredPesoBill());
            }
            if (moneyDenom.getTwoHundredPesoBill() != 0)
                money %= moneyDenom.getTwoHundredPesoBill() * 200;
        }

        if (money >= 100) {                                       //100
            if (inventory.getOneHundredPesoBill()*100 >= money) {
                moneyDenom.setOneHundredPesoBill((int)money / 100);
            }
            else {
                moneyDenom.setOneHundredPesoBill(inventory.getOneHundredPesoBill());
            }
            if (moneyDenom.getOneHundredPesoBill() != 0)
                money %= moneyDenom.getOneHundredPesoBill() * 100;
        }

        if (money >= 50) {                                       //50
            if (inventory.getFiftyPesoBill()*50 >= money) {
                moneyDenom.setFiftyPesoBill((int)money / 50);
            }
            else {
                moneyDenom.setFiftyPesoBill(inventory.getFiftyPesoBill());
            }
            if (moneyDenom.getFiftyPesoBill() != 0)
                money %= moneyDenom.getFiftyPesoBill() * 50;
        }

        if (money >= 20) {                                       //20bill
            if (inventory.getTwentyPesoBill()*20 >= money) {
                moneyDenom.setTwentyPesoBill((int)money / 20);
            }
            else {
                moneyDenom.setTwentyPesoBill(inventory.getTwentyPesoBill());
            }
            if (moneyDenom.getTwentyPesoBill() != 0)
                money %= moneyDenom.getTwentyPesoBill() * 20;
        }

        if (money >= 20) {                                       //20coin
            if (inventory.getTwentyPesoCoin()*20 >= money) {
                moneyDenom.setTwentyPesoCoin((int)money / 20);
            }
            else {
                moneyDenom.setTwentyPesoCoin(inventory.getTwentyPesoCoin());
            }
            if (moneyDenom.getTwentyPesoCoin() != 0)
                money %= moneyDenom.getTwentyPesoCoin() * 20;
        }

        if (money >= 10) {                                       //10
            if (inventory.getTenPesoCoin()*10 >= money) {
                moneyDenom.setTenPesoCoin((int)money / 10);
            }
            else {
                moneyDenom.setTenPesoCoin(inventory.getTenPesoCoin());
            }
            if (moneyDenom.getTenPesoCoin() != 0)
                money %= moneyDenom.getTenPesoCoin() * 10;
        }

        if (money >= 5) {                                       //5
            if (inventory.getFivePesoCoin()*5 >= money) {
                moneyDenom.setFivePesoCoin((int)money / 5);
            }
            else {
                moneyDenom.setFivePesoCoin(inventory.getFivePesoCoin());
            }
            if (moneyDenom.getFivePesoCoin() != 0)
                money %= moneyDenom.getFivePesoCoin() * 5;
        }

        if (money >= 1) {                                       //1
            if (inventory.getOnePesoCoin()*1 >= money) {
                moneyDenom.setOnePesoCoin((int)money/1);
            }
            else {
                moneyDenom.setOnePesoCoin(inventory.getOnePesoCoin());
            }
            money %= moneyDenom.getOnePesoCoin() * 1;
        }

        return moneyDenom;
    }

    public void displayProducts(int typeOfDisplay){
        int i;
        int totalSales = 0;
//        typeOfDisplay (equivalent)
//        1       display to customer
//        2       selection
//        3       maintenance

        if (typeOfDisplay == 0) { // for product display to customer
            for(int x=0; x<50;x++)
                System.out.print("═");
            System.out.println();
            System.out.printf("%-4s %s%4s%s\n",
                    " ","P R O D U C T"," ","D I S P L A Y");
        }

        else if (typeOfDisplay == 3){
            for(int x=0; x<50;x++)
                System.out.print("═");
            System.out.println();
            System.out.printf("%12s %s %s\n",
                    " ","Conducting","Maintenance...");
        }
        for(int x=0; x<50;x++)
            System.out.print("═");
        System.out.println();

        if (typeOfDisplay == 3){
            System.out.printf("%4s%-14s%6s %6s  %s  %s\n", "", "Item", "Stock", "Price", "Sold", "Calories");
        }
        for(i=0; i<productSlots.size();i++){
            System.out.printf("[%d] %-14s",i+1,productSlots.get(i).getBaseProductName());

            if (typeOfDisplay == 0 && productSlots.get(i).getBaseProductPrice() != -1){
                double price = productSlots.get(i).getBaseProductPrice();
                System.out.printf("%6.2f ", price);
            }

            else if (typeOfDisplay == 3){

                if (i+1 < 10)
                    System.out.print("  ");
                else
                    System.out.print(" ");

                if (!isSlotEmpty(i)) {
                    System.out.printf("%-5d %-6.2f  %-7d %-8d\n", productSlots.get(i).getProductQuantity(),
                            productSlots.get(i).getBaseProductPrice(), productSlots.get(i).getNumProductsSold(),
                            (int)productSlots.get(i).getBaseProductCal());
                }
                else {
                    System.out.printf(" %s%6s%s%7s\n", "-", "", "-", "--");
                }

            }

            else {
                System.out.printf("%6s ", "");
            }



            if ((i+1)%2==0 && typeOfDisplay != 3)
                System.out.println(" ");
        }

        if(typeOfDisplay==1 || typeOfDisplay == 3) // for display selection
            System.out.printf("[%d] Exit Selection.",i+1);

        if (typeOfDisplay == 3){
            System.out.println("");
            for (int x = 0; x < 50; x++){
                System.out.print("_");
            }

            for (int x = 0; x < productSlots.size(); x++){
                if (!(isSlotEmpty(x)));
                totalSales += productSlots.get(x).getBaseProductPrice() * productSlots.get(x).getNumProductsSold();
            }

            System.out.printf("\nTotal Sales: %6.2f", (double) totalSales);
        }

        System.out.println(" ");
        for(int x=0; x<50;x++)
            System.out.print("═");
        System.out.println(" ");
    }


    private void differenceDenomination(Denomination minuend, Denomination subtrahend){     //subtracts all values of subtrahend from minuend = minuend
        minuend.setThousandPesoBill     (-subtrahend.getThousandPesoBill());
        minuend.setFiveHundredPesoBill  (-subtrahend.getFiveHundredPesoBill());
        minuend.setTwoHundredPesoBill   (-subtrahend.getTwoHundredPesoBill());
        minuend.setOneHundredPesoBill   (-subtrahend.getOneHundredPesoBill());
        minuend.setFiftyPesoBill        (-subtrahend.getFiftyPesoBill());
        minuend.setTwentyPesoBill       (-subtrahend.getTwentyPesoBill());
        minuend.setTwentyPesoCoin       (-subtrahend.getTwentyPesoCoin());
        minuend.setTenPesoCoin          (-subtrahend.getTenPesoCoin());
        minuend.setFivePesoCoin         (-subtrahend.getFivePesoCoin());
        minuend.setOnePesoCoin          (-subtrahend.getOnePesoCoin());
    }

    private void addToDenomination(Denomination to, Denomination from){                         //add all denoms; to = from + to
        to.setThousandPesoBill     (from.getThousandPesoBill());
        to.setFiveHundredPesoBill  (from.getFiveHundredPesoBill());
        to.setTwoHundredPesoBill   (from.getTwoHundredPesoBill());
        to.setOneHundredPesoBill   (from.getOneHundredPesoBill());
        to.setFiftyPesoBill        (from.getFiftyPesoBill());
        to.setTwentyPesoBill       (from.getTwentyPesoBill());
        to.setTwentyPesoCoin       (from.getTwentyPesoCoin());
        to.setTenPesoCoin          (from.getTenPesoCoin());
        to.setFivePesoCoin         (from.getFivePesoCoin());
        to.setOnePesoCoin          (from.getOnePesoCoin());
    }

    private void clearDenomination(Denomination x){
        x.setOnePesoCoin(-x.getOnePesoCoin());
        x.setFivePesoCoin(-x.getFivePesoCoin());
        x.setTenPesoCoin(-x.getTenPesoCoin());
        x.setTwentyPesoCoin(-x.getTwentyPesoCoin());
        x.setTwentyPesoBill(-x.getTwentyPesoBill());
        x.setFiftyPesoBill(-x.getFiftyPesoBill());
        x.setOneHundredPesoBill(-x.getOneHundredPesoBill());
        x.setTwoHundredPesoBill(-x.getTwoHundredPesoBill());
        x.setFiveHundredPesoBill(-x.getFiveHundredPesoBill());
        x.setThousandPesoBill(-x.getThousandPesoBill());
    }


    // display methods---------------------------------------------------------------------------------
    public void moneyDisplay(){
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
        System.out.println();
    }

    private void vmSimulationDisplay(int type){
        if(type==1)
        {
            consoleSysCom("cls");
            System.out.println("Starting emulation.....");
            System.out.println("Vending Machine ready for testing.\n");
        }
        else if (type==2)
        {
            System.out.println("Terminating emulation.....");
            System.out.println("Vending Machine terminated.\n");
        }
    }

    private void displayDenominations(Denomination money){
        System.out.printf("%-5s| %s\n", "Amt.", "Denomination");
        if (money.getThousandPesoBill() > 0){
            System.out.printf("%4d | %s\n", money.getThousandPesoBill(), "1000 PHP");
        }
        if (money.getFiveHundredPesoBill() > 0){
            System.out.printf("%4d | %s\n", money.getFiveHundredPesoBill(), "500 PHP");
        }
        if (money.getTwoHundredPesoBill() > 0){
            System.out.printf("%4d | %s\n", money.getTwoHundredPesoBill(), "200 PHP");
        }
        if (money.getOneHundredPesoBill() > 0){
            System.out.printf("%4d | %s\n", money.getOneHundredPesoBill(), "100 PHP");
        }
        if (money.getFiftyPesoBill() > 0){
            System.out.printf("%4d | %s\n", money.getFiftyPesoBill(), "50 PHP");
        }
        if (money.getTwentyPesoBill() > 0){
            System.out.printf("%4d | %s\n", money.getTwentyPesoBill(), "20 PHP");
        }
        if (money.getTwentyPesoCoin() > 0){
            System.out.printf("%4d | %s\n", money.getTwentyPesoCoin(), "20 PHP");
        }
        if (money.getTenPesoCoin() > 0){
            System.out.printf("%4d | %s\n", money.getTenPesoCoin(), "10 PHP");
        }
        if (money.getFivePesoCoin() > 0){
            System.out.printf("%4d | %s\n", money.getFivePesoCoin(), "5 PHP");
        }
        if (money.getOnePesoCoin() > 0){
            System.out.printf("%4d | %s\n", money.getOnePesoCoin(), "1 PHP");
        }
        System.out.println("");
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
}

