import java.util.Scanner;
import java.util.ArrayList;

/**
 * Vending Machine.
 * <p>
 *    Vending machine that is composed of slot with items and denominations, performs tasks
 *    such as making transaction with user and maintenance mode such as Stocking/Restocking,
 *    replenishing money, and printing transactions.
 * </p>
 * @author Ram Brodett
 * @author Luke Regalado
 * @version 07/1/2023
 */
public class VendingMachine{
    private Denomination denomination;
    private final ArrayList<Slot> productSlots;
    private Denomination insertedMoney;

    /**
     * Constructor for Vending Machine. Makes instances of the subclass it needs.
     * @param slotCapacity for number of capacity it will inherit to be the max slots for products.
     */

    public VendingMachine(int slotCapacity){
        this.productSlots = new ArrayList<>(); //Create VENDING MACHINE PRODUCT SLOTS
        for(int i=0; i<slotCapacity; i++)
            productSlots.add(new Slot());
        this.denomination = new Denomination(); //Create MONEY
        this.insertedMoney = new Denomination(); // create
        vmPackageSelection();                   //call process prompts in vending machine creation
    }

    // Vending Machine creation method---------------------------------------------------------

    /**
     * Selection of initial items and denomination to initialize inside vending machine.
     *
     * <p>
     *     Utilizes different methods to interact with user to recieve and set necessary
     *     informations.
     * </p>
     * @var money is total money provided to the machine.
     */
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


    /**
     * Proving the machine a set of complete values for denomination.
     * @param denomination a set of values of diff denomination.
     */
    public void vmSetMoney(Denomination denomination){
        this.denomination = denomination;
    }

    // start of vending machine methods for overall features------------------------------------------------------------
    public void setProduct(String name, double price, int calories, int quantity, int slotNo){
        productSlots.set(slotNo, new Slot(name, price, calories, quantity));
    }

    /**
     *  allows the authorized personel to set a product on a specific slot on vending machine.
     * @param slotNumber specific location you want to store the product on.
     * @param ifReplace is a boolean value to know if it is replacing an item or restocking.
     */

    public void setProductOnSlot(int slotNumber, boolean ifReplace){
        Scanner scanner = new Scanner(System.in);

        String name;
        if (ifReplace) {
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

        if (ifReplace) {
            do {
                System.out.print("Enter product quantity (Max of 15pcs) : ");
                numofProducts = scanner.nextInt();
                scanner.nextLine();
                if (numofProducts < 1 || numofProducts > 15)
                    System.out.println("Enter a valid quantity");
            } while (numofProducts < 1 || numofProducts > 15);
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

    /**
     *<p>
     *   Simulates Transaction method of the vending machine, utilizes method to receive money from user,
     *  provide choice for the buyer along with the nutrition fact calories, calculate needed
     *  denomination for change, dispensing items and change.
     *</p>
     */
    public void vendingMachineUserTransaction(){
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
                String confirm;
                do {
                    System.out.print("Selected item: ");
                    selected = scanner.nextInt();
                    scanner.nextLine();
                    if ((selected < 1 || selected > productSlots.size())) System.out.println("Select a valid item");
                } while ((selected < 1 || selected > productSlots.size()) && (selected - 1) != productSlots.size());

                if ((selected-1) == productSlots.size()) {
                    clearDenomination(insertedMoney);
                    ongoingTransaction = false;
                }

                else if (!isSlotEmpty(selected - 1)) {

                    int change = insertedMoney.getTotalMoney() - (int) productSlots.get(selected - 1).getBaseProductPrice(); //money of user - price
                    Denomination changeDenom = findDenomination(change, denomination);

                    if ((!isSlotStocked(selected - 1))) {
                        System.out.println("Sorry! Item is out of stock.\n");
                    }

                    else if(changeDenom.getTotalMoney() - change != 0) {  //if true, then change isn't possible
                        System.out.println("Unable to give change. Unsuccessful transaction.\n");
                    }
                    else if (insertedMoney.getTotalMoney() < productSlots.get(selected - 1).getBaseProductPrice()) {
                        System.out.println("Insufficient balance. Unsuccessful transaction.\n");
                    }
                    else {
                        do {
                            System.out.println(productSlots.get(selected-1).getBaseProductName() + " has " +
                                    productSlots.get(selected-1).getBaseProductCal() + "Calories");
                            System.out.println("\nBuying \"" + productSlots.get(selected-1).getBaseProductName() +
                                    "\" for " + productSlots.get(selected-1).getBaseProductPrice() + "...");
                            System.out.print("Confirm purchase: ");
                            confirm = scanner.nextLine();

                            if (!(confirm.equalsIgnoreCase("yes")) && // to continue asking while answer is
                                    !(confirm.equalsIgnoreCase("no")))   // neither yes nor no.
                                System.out.println("type yes or no only.");
                        } while (!(confirm.equalsIgnoreCase("yes"))&& !(confirm.equalsIgnoreCase("no")));

                        if (confirm.equalsIgnoreCase("yes")){
                            addToDenomination(denomination, insertedMoney);     //transfer money from inserted to VM
                            differenceDenomination(denomination, changeDenom);   //remove change from VM
                            insertedMoney = changeDenom;                        //money of user = change
                            productSlots.get(selected-1).setProductQuantity(-1);
                            productSlots.get(selected-1).setNumProductsSold(1);
                            System.out.printf("Purchase successful! Your change is %3.2f\n", (double) change);
                            displayDenominations(changeDenom);
                        }

                        else
                            System.out.println("Cancelling transaction...");
                    }
                }

                else if (isSlotEmpty(selected - 1)){
                    System.out.print("Invalid input! Slot is empty.\n\n");
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

    /**
     * <p>
     *  This method allows the user to edit the items in the vending machine.
     *  It prompts the user to select a slot and either add new items or restock existing items.
     * </p>
     */
    public void editItems() {
        Scanner scanner = new Scanner(System.in);
        int selected;
        int temp1, temp2;
        double temp3, temp4;
        do {
            displayProducts(3);
            System.out.print("Select a slot to edit: ");
            selected = scanner.nextInt();

            if ((selected -1) != productSlots.size()) {
                if (isSlotEmpty(selected - 1) || productSlots.get(selected - 1).getProductQuantity() == 0) {
                    setProductOnSlot(selected-1,true);
                }
                else if (!(isSlotEmpty(selected - 1))) {
                    temp1 = productSlots.get(selected-1).getProductQuantity();          //set temp vars for before restocking/editing
                    temp2 = productSlots.get(selected-1).getNumProductsSold();
                    temp3 = productSlots.get(selected-1).getBaseProductPrice();
                    temp4 = productSlots.get(selected-1).getBaseProductCal();

                    setProductOnSlot(selected - 1, false);

                    productSlots.get(selected - 1).setOldProductQuantity(temp1);        //assign temp vars into old attributes for display
                    productSlots.get(selected - 1).setOldProductsSold(temp2);
                    productSlots.get(selected - 1).setBaseProductOldPrice(temp3);
                    productSlots.get(selected - 1).setBaseProductOldCalories(temp4);
                    productSlots.get(selected - 1).setEdited(true);
                }
            }
        } while (selected != productSlots.size()+1);
    }

    // boolean methods for vmstatus-------------------------------------------------------------

    /**
     * Method that checks the availability of a slot.
     * @param slotNumber specific location you want to check.
     * @return boolean value, returned true relates that slot is empty, otherwise, it is not empty.
     */
    private boolean isSlotEmpty(int slotNumber){
        return (productSlots.get(slotNumber).getBaseProductName().equals("Empty")&&
                productSlots.get(slotNumber).getBaseProductPrice()==-1&&
                productSlots.get(slotNumber).getBaseProductCal()==-1);
    }

    /**
     * Method that checks the slot if it is stocked with products.
     * @param slotNumber specific location you want to check on.
     * @return boolean value, returned true relates that slot is empty, otherwise, it is not empty.
     */
    private boolean isSlotStocked(int slotNumber){
        return(productSlots.get(slotNumber).getProductQuantity() > 0);
    }


    // methods for money related calculations and processes and transaction subprocesses--------------------------------

    //interface for feeding the machine with money

    /**
     * <p>
     *     This method prompts the user to supply money denominations.
     * </p>
     * @return a Denomination object representing the money fed into the vending machine.
     */

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

    /**
     * Method to edit/collect the supply of money mounted on the machine.
     */

    public void moneyBox(){
        Scanner scanner = new Scanner(System.in);
        int choice;
        do{
            System.out.print("Input: ");
            choice=scanner.nextInt();
        }while(choice<1||choice>3);
        switch (choice){
            case 1 ->{
                System.out.println("Collecting payment...");
                System.out.println("Payment collected");
                System.out.println("Total Money Collected: " + denomination.getTotalMoney());
                clearDenomination(denomination);
            }
            case 2 ->{
                addToDenomination(denomination,denominationFeedInterface());
                System.out.println("Money replenished");
            }
            case 3 ->{
                // exit case
            }
        }
    }

    /**
     * This method looks for the appropriate denomination from the inventory.
     * @param money is the reference of inserted money.
     * @param inventory is the location of sets of money in machine
     * @return a Denomination object representing the appropriate set of money the machine can produce for change.
     */

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

    /**
     *  Product Displays
     * @param typeOfDisplay is the kind it should be used for, either Selection, Menu, Maintenance.
     */

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
            System.out.printf("%4s%-14s%6s %9s%5s%s  %s\n", "", "Item", "Stock", "Price", "", "Sold", "Cal.");
        }
        for(i=0; i<productSlots.size();i++){
            System.out.printf("[%d] %-14s",i+1,productSlots.get(i).getBaseProductName());

            if (typeOfDisplay == 0 && productSlots.get(i).getBaseProductPrice() != -1){
                double price = productSlots.get(i).getBaseProductPrice();
                System.out.printf("%6.2f ", price);
            }

            else if (typeOfDisplay == 3){


                System.out.print(" ");

                if (!isSlotEmpty(i)) {
                    if(productSlots.get(i).isEdited()) {
                        System.out.printf("%2d>%-2d %6.2f>%-6.2f%2d>%-2d %3d>%-3d\n", productSlots.get(i).getOldProductQuantity(),
                                productSlots.get(i).getProductQuantity(), productSlots.get(i).getBaseProductOldPrice(),
                                productSlots.get(i).getBaseProductPrice(), productSlots.get(i).getOldProductsSold(),
                                productSlots.get(i).getNumProductsSold(), (int)productSlots.get(i).getBaseProductOldCal(),
                                (int)productSlots.get(i).getBaseProductCal());
                    }

                    else {
                        System.out.printf("%-9d %-9.2f %-5d %-4d\n", productSlots.get(i).getProductQuantity(),
                                productSlots.get(i).getBaseProductPrice(), productSlots.get(i).getNumProductsSold(),
                                (int) productSlots.get(i).getBaseProductCal());
                    }
                }
                else {
                    if (i+1 < 10)
                        System.out.print("  ");
                    else
                        System.out.print(" ");
                    System.out.printf("-%9s-%8s--%4s-\n", "", "", "");
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
                if (!(isSlotEmpty(x))) {
                    if (productSlots.get(x).isEdited())
                        totalSales += productSlots.get(x).getBaseProductOldPrice() * productSlots.get(x).getOldProductsSold();
                    else
                        totalSales += productSlots.get(x).getBaseProductPrice() * productSlots.get(x).getNumProductsSold();
                }
            }

            System.out.printf("\nTotal Sales: %6.2f", (double) totalSales);
        }

        System.out.println(" ");
        for(int x=0; x<50;x++)
            System.out.print("═");
        System.out.println(" ");
    }

    /**
     * <p>
     * Subtracts the values of each denomination in the 'subtrahend' from the corresponding values in the 'minuend'.
     * The result is stored in the 'minuend'.
     * </p>
     *
     * @param minuend    The denomination object from which the subtrahend values will be subtracted.
     * @param subtrahend The denomination object containing the values to be subtracted from the 'minuend'.
     */

    private void differenceDenomination(Denomination minuend, Denomination subtrahend){
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


    /**
     * <p>
     * Adds the values of each denomination in the 'from' object to the corresponding values in the 'to' object.
     * The result is stored in the 'to' object.
     * </p>
     * @param to   The denomination object where the values will be added.
     * @param from The denomination object containing the values to be added.
     */

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

    /**
     * <p>
     * Clears the values of each denomination in the 'x' object by setting them to zero.
     * The result is stored in the same 'x' object.</p>
     * @param x The denomination object to be cleared.
     */
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
    /**
     * <p>
     * Displays the representation of money in the console.
     * Shows a table with coin and bill denominations, along with their corresponding codes.</p>
     */
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

    /**
     * Displays the simulation status of the Vending Machine.
     * @param type is the type of what you want to display.
     */
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

    /**
     * Displays the denominations and amounts of money in the 'money' object .
     * @param money The Denomination object representing the money to be displayed.
     */

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


    /**
     * <p>
     * Displays the transactions made in the Vending Machine.
     * Shows the items sold, their prices, the quantity sold, and the corresponding profit.
     * </p>
     */
    public void displayTransactions(){
        double totalProfit = 0;

        System.out.printf("\n%-15s%s  %s %s\n", "Item", "Price", "Sold", "Profit");
        for (int i = 0; i < 37; i++){
            System.out.print("=");
        }
        System.out.println();
        for (Slot productSlot : productSlots) {
            if (productSlot.getNumProductsSold() > 0) {
                System.out.printf("%-15s%-7.2f %-4d %-7.2f\n", productSlot.getBaseProductName(),
                        productSlot.getBaseProductPrice(), productSlot.getNumProductsSold(),
                        productSlot.getBaseProductPrice() * productSlot.getNumProductsSold());
                totalProfit = productSlot.getBaseProductPrice() * productSlot.getNumProductsSold();
            }
        }
        for (int i = 0; i < 37; i++){
            System.out.print("=");
        }

        System.out.print("\nTotal: ");
        System.out.printf("%-33.2f\n", totalProfit);
        for (int i = 0; i < 37; i++){
            System.out.print("=");
        }
        System.out.println(" ");
    }

    //helper methods-----------------------------------------------------------------------------

    /**
     * Executes a system command related to console control.
     * @param command The command to be executed:
     *                - "cls": Clears the console screen.
     *                - "fflush": Flushes the console input buffer.
     */
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
