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
     */

    public VendingMachine(){
        this.productSlots = new ArrayList<>(); //Create VENDING MACHINE PRODUCT SLOTS
        this.denomination = new Denomination(); //Create MONEY
        this.insertedMoney = new Denomination(); // create
        vmCreation();                   //call process prompts in vending machine creation
    }

    // Vending Machine creation method---------------------------------------------------------

    /**
     * Selection of initial items and denomination to initialize inside vending machine.
     *
     * <p>
     *     Utilizes different methods to interact with user to recieve and set necessary
     *     informations.
     * </p>
     */
    //!!!! USED!!!!!
    private void vmCreation(){
        String[] products = {"Vanilla", "Chocolate", "Matcha", "Choco Chips", "Cereals",
                "Mixed Fruit Bits", "Raspberry", "Starberry","Mango"};

        for (int i = 0; i < products.length; i++){

            if(i<3||i>5)productSlots.add(i,new Slot(products[i],100,90,15));
            else productSlots.add(i,new Slot(products[i],45,40,15));
        }
    }

    /**
     * Providingng the machine a set of complete values for denomination.
     * @param denomination a set of values of diff denomination.
     */

    //!!!!USED!!!!!
    public void vmSetMoney(Denomination denomination){
        this.denomination = denomination;
    }

    //to do dapat lumalabas to sa text area yung blue na gilid ng vm

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

    public void setInsertedMoney(Denomination denomination){
        this.insertedMoney = denomination;
    }


    public void editItemPrice() {
        /*
        To DO :

        Change item's Price
         */

    }

    public boolean dispenseProduct(int index){
        if(!(isSlotEmpty(index))){
            if(productSlots.get(index).getBaseProductPrice()<= getTotalInsertedMoney()){
                float change = (float) (getTotalInsertedMoney() - productSlots.get(index).getBaseProductPrice()); // needed change
                addToDenomination(denomination,insertedMoney);
                Denomination changeDenom = findDenomination(change,denomination);
                if(!(changeDenom.getTotalMoney() - change != 0)){
                    System.out.println("Transaction successful.");
                    System.out.println("Dispensing " + productSlots.get(index).getBaseProductName());
                    dispenser(index);
                    differenceDenomination(denomination,changeDenom);
                    insertedMoney = changeDenom;
                    productSlots.get(index).setNumProductsSold(1);
                    System.out.printf("Your change is %.2f\n",change);
                    displayDenominations(changeDenom);
                    return true;
                }
                else System.out.println("Unable to give change. Unsuccessful transaction.\n");

            }else System.out.println("Insufficient balance. Unsuccessful transaction.\n");

        }else System.out.println("Sorry! Item is out of stock.\n");
        return false;
    }

    public float getTotalInsertedMoney(){
        return this.insertedMoney.getTotalMoney();
    }

    private void dispenser(int index){
        productSlots.get(index).getProducts().remove(0);
    }


    /**
     * <p>
     * Adds the values of each denomination in the 'from' object to the corresponding values in the 'to' object.
     * The result is stored in the 'to' object.
     * </p>
     * @param to   The denomination object where the values will be added.
     * @param from The denomination object containing the values to be added.
     */
    public void addToDenomination(Denomination to, Denomination from){                         //add all denoms; to = from + to
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

    // boolean methods for vmstatus-------------------------------------------------------------

    /**
     * Method that checks the availability of a slot.
     * @param slotNumber specific location you want to check.
     * @return boolean value, returned true relates that slot is empty, otherwise, it is not empty.
     */
    private boolean isSlotEmpty(int slotNumber){
        return (productSlots.get(slotNumber).getProductQuantity()==0);
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


    // display methods---------------------------------------------------------------------------------

    public Denomination getDenomination() { return this.denomination;}      /////////////NEWWW
    public float getTotalMoney(){
        return this.denomination.getTotalMoney();
    }
    public Denomination getInsertedMoney(){
        return this.insertedMoney;
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






}