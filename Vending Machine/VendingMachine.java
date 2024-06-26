import javax.swing.*;
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
    /**
     * Denomination of money the machine has.
     */
    protected Denomination denomination;
    /**
     * Slots of the product
     */
    protected final ArrayList<Slot> productSlots;
    /**
     * Inserted money for transaction
     */
    protected Denomination insertedMoney;

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
                "Mixed Fruit Bits", "Raspberry", "Strawberry","Mango"};

        for (int i = 0; i < products.length; i++){

            if(i<3||i>5) {
                productSlots.add(i, new Slot(products[i], 100, 90, 15));
                //productsSold.add(i, new Slot(products[i], 100, 90, 0));
            }
            else {
                productSlots.add(i, new Slot(products[i], 45, 40, 15));
                //productsSold.add(i, new Slot(products[i], 45, 40, 0));
            }
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
        int specSold = 0;

        System.out.printf("\n%-20s%s    %s      %s\n", "Item", "Price", "Sold", "Profit");
        for (int i = 0; i < 57; i++){
            System.out.print("=");
        }
        System.out.println();
        for (Slot productSlot : productSlots) {
            if (productSlot.getNumProductsSold() > 0) {
                System.out.printf("%-20s%-7.2f  %-4d        %-7.2f\n", productSlot.getBaseProductName(),
                        productSlot.getBaseProductPrice(), productSlot.getNumProductsSold(),
                        productSlot.getBaseProductPrice() * productSlot.getNumProductsSold());

                specSold +=productSlot.getSpecProductsSold();
                totalProfit += productSlot.getBaseProductPrice() * productSlot.getNumProductsSold();
            }
        }

        if (specSold > 0)
            System.out.printf("%-20s%-7.2f  %-4d        %-7.2f\n", "Pre-set Yogurt Fee",
                50.0, specSold, specSold * 50.0);

        totalProfit += specSold * 50;

        for (int i = 0; i < 57; i++){
            System.out.print("=");
        }

        System.out.print("\nTotal: ");
        System.out.printf("%-33.2f\n", totalProfit);
        for (int i = 0; i < 57; i++){
            System.out.print("=");
        }
        System.out.println(" ");
    }

    /**
     * Sets a money intro to Inserted money holder.
     * @param denomination denominations of money.
     */
    public void setInsertedMoney(Denomination denomination){
        this.insertedMoney = denomination;
    }


    /**
     * Product dispenser
     * @param index index is the index of the item.
     * @param c c is case type (ignore c in Regular, it only matters in Special)
     * @return boolean value, will set to true if dispense take place.
     */
    public boolean dispenseProduct(int index, int c){
        if(!(isSlotEmpty(index))){
            if(productSlots.get(index).getBaseProductPrice()<= getTotalInsertedMoney()){                          // if insert money is more than or equal to price
                float change = (float) (getTotalInsertedMoney() - productSlots.get(index).getBaseProductPrice()); // needed change
                addToDenomination(denomination,insertedMoney);                                                    // add inserted to vm money
                Denomination changeDenom = findDenomination(change,denomination);
                if(!(changeDenom.getTotalMoney() - change != 0)){
                    System.out.println("Transaction successful.");
                    System.out.println("Dispensing " + productSlots.get(index).getBaseProductName());
                    dispenser(index);
                    differenceDenomination(denomination,changeDenom);
                    insertedMoney = changeDenom;
                    System.out.printf("Your change is %.2f\n",change);
                    displayDenominations(changeDenom);
                    return true;
                }
                else System.out.println("Unable to give change. Unsuccessful transaction.\n");

            }else System.out.println("Insufficient balance. Unsuccessful transaction.\n");

        }else System.out.println("Sorry! Item is out of stock.\n");
        return false;
    }

    /**
     * Gets the total Inserted value.
     * @return total inserted money.
     */
    public float getTotalInsertedMoney(){
        return this.insertedMoney.getTotalMoney();
    }


    /**
     * Dispense helper
     * <p>
     *    it will emulate the dispense of the item, destroy products dispensed
     * </p>
     * @param index index of the product.
     */
    protected void dispenser(int index){
        productSlots.get(index).setNumProductsSold(1);
        productSlots.get(index).getProducts().remove(0);
    }

    /**
     * Edits the price on an item display.
     * @param index index of the item.
     * @return returns the user input price.
     */
    public double editPriceInput(int index){
        double userInput;
        int itemNum = index + 1;
        do {
            userInput = Double.parseDouble(JOptionPane.showInputDialog(null, "Current Price: " +
                            productSlots.get(index).getBaseProductPrice() + "\nNew Price: ",
                    "Editing price of Item " + itemNum + "...", JOptionPane.QUESTION_MESSAGE));

            if (userInput < 0) {
                JOptionPane.showMessageDialog(null, "Please input a positive integer.");
            }
        } while (userInput < 0);

        if (userInput > 0)
            System.out.println("Edited price of  \"" + productSlots.get(index).getBaseProductName() +
                    "\" to P" + userInput + "...");
        return userInput;
    }

    /**
     * Restocking of items.
     * @param index index of the item.
     * @return desired qty to add to the item .
     */

    public int restockInput(int index){
        int userInput;
        do {
            userInput = Integer.parseInt(JOptionPane.showInputDialog(null, "Current Quantity: " +
                            productSlots.get(index).getProductQuantity() + "\nAmount to Restock: ",
                    "Restocking \"" + productSlots.get(index).getBaseProductName() + "\""
                    , JOptionPane.QUESTION_MESSAGE));

            if (userInput + productSlots.get(index).getProductQuantity() > 15){
                JOptionPane.showMessageDialog(null, "Items can only have a maximum stock of 15!");
            } else if (userInput< 0) {
                JOptionPane.showMessageDialog(null, "Please input a positive integer.");
            }
        } while (userInput + productSlots.get(index).getProductQuantity() > 15 ||
                userInput < 0);

        if (userInput > 0)
            System.out.println("Stocked " + userInput + " piece[s] of \"" + productSlots.get(index).getBaseProductName() + "\"...");
        return userInput;
    }

    /**
     * Restocking of items special items.
     * @param results ArrayList for passing the results of restocking.
     */
    public void restockInput(ArrayList<Integer> results) {
        JPanel dropdownPanel = new JPanel();
        JPanel amtPanel = new JPanel();
        JPanel promptPanel = new JPanel();
        promptPanel.setLayout(new BoxLayout(promptPanel, BoxLayout.Y_AXIS));

        String[] unsellables = {
                "Chocolate Sauce   | " + productSlots.get(9).getProductQuantity() + "x",
                "Strawberry Sauce | " + productSlots.get(10).getProductQuantity() + "x",
                "Caramel Sauce      | " + productSlots.get(11).getProductQuantity() + "x",
                "Rainbow Sprinkle  | " + productSlots.get(12).getProductQuantity() + "x"};
        JComboBox<String> itemDropdown = new JComboBox<>(unsellables);

        JLabel itemLabel = new JLabel("Item: ");
        JLabel amtLabel = new JLabel("Restock: ");
        dropdownPanel.add(itemLabel);
        dropdownPanel.add(itemDropdown);

        JTextField amtRestock = new JTextField(5);
        amtPanel.add(amtLabel);
        amtPanel.add(amtRestock);

        promptPanel.add(dropdownPanel);
        promptPanel.add(amtPanel);

        String selectedItem = "";
        int qty = 0;
        int itemIndex = 9;
        int exit = 0;
        do {
            int choice = JOptionPane.showConfirmDialog(null, promptPanel, "Restocking unsellable items...",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (choice == JOptionPane.OK_OPTION){
                selectedItem = (String) itemDropdown.getSelectedItem();
                qty = Integer.parseInt(amtRestock.getText());

                if (selectedItem.equals(unsellables[0])){
                    itemIndex = 9;
                }
                if (selectedItem.equals(unsellables[1])){
                    itemIndex = 10;
                }
                if (selectedItem.equals(unsellables[2])){
                    itemIndex = 11;
                }
                if (selectedItem.equals(unsellables[3])){
                    itemIndex = 12;
                }

                if (qty + productSlots.get(itemIndex).getProductQuantity() > 15 || qty < 0){
                    if (qty + productSlots.get(itemIndex).getProductQuantity() > 15) {
                        JOptionPane.showMessageDialog(null, "Items can only have a maximum stock of 15!");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Please input a positive integer.");
                    }
                    qty = 0;
                }
                else {
                    exit = 1;
                }
            }

            else if (choice == JOptionPane.CANCEL_OPTION || choice == JOptionPane.CLOSED_OPTION)
                exit = 1;

        } while (exit != 1);

        results.add(itemIndex);
        results.add(qty);
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


    // boolean methods for vmstatus-------------------------------------------------------------

    /**
     * Method that checks the availability of a slot.
     * @param slotNumber specific location you want to check.
     * @return boolean value, returned true relates that slot is empty, otherwise, it is not empty.
     */
    protected boolean isSlotEmpty(int slotNumber){
        return (productSlots.get(slotNumber).getProductQuantity()==0);
    }

    // methods for money related calculations and processes and transaction subprocesses--------------------------------

    //interface for feeding the machine with money

    /**
     * This method looks for the appropriate denomination from the inventory.
     * @param money is the reference of inserted money.
     * @param inventory is the location of sets of money in machine
     * @return a Denomination object representing the appropriate set of money the machine can produce for change.
     */

    protected Denomination findDenomination(float money, Denomination inventory) {                  //give money in denominations based on available denoms
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
     * <p>
     * Subtracts the values of each denomination in the 'subtrahend' from the corresponding values in the 'minuend'.
     * The result is stored in the 'minuend'.
     * </p>
     *
     * @param minuend    The denomination object from which the subtrahend values will be subtracted.
     * @param subtrahend The denomination object containing the values to be subtracted from the 'minuend'.
     */

    protected void differenceDenomination(Denomination minuend, Denomination subtrahend){
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

    /**
     * for getting the denomination in the machine.
     * @return denomination of money.
     */
    public Denomination getDenomination() { return this.denomination;}      /////////////NEWWW

    /**
     * Gets the total value of money  in float form
     * @return total value of money float.
     */
    public float getTotalMoney(){
        return this.denomination.getTotalMoney();
    }

    /**
     * Getter for inserted money.
     * @return denomination type of money.
     */
    public Denomination getInsertedMoney(){
        return this.insertedMoney;
    }

    /**
     * Displays the denominations and amounts of money in the 'money' object .
     * @param money The Denomination object representing the money to be displayed.
     */

    protected void displayDenominations(Denomination money){
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
     * Checks the custom product ingredients
     * @param i for indexing
     * @return return boolean if product is available
     */

    public boolean customProductChecker(int i) {
        return false;
    }
}