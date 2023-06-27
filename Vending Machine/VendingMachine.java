import org.w3c.dom.ls.LSOutput;

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
        vmPackageSelection();                   //call process prompts in vending machine creation
    }

    // Vending Machine creation method---------------------------------------------------------

    private void vmPackageSelection(){
        Scanner scanner = new Scanner(System.in);
        consoleSysCom("cls");
        System.out.println("\nADD INITIAL ITEMS ON SLOT [OPTIONAL PACKAGE]");
        int selectedSlot, i;
        do {
            // Display every item slot
            for(i=0; i<productSlots.size();i++){
                System.out.printf("[%d] %-15s",i+1,productSlots.get(i).getBaseProductName());
                if ((i+1)%2==0)
                    System.out.println(" ");
            }
            System.out.printf("[%d] Exit Selection.\n\n",i+1);
            System.out.print("Select a slot to add an item: ");
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
                    System.out.println("""

                            You did not add a product to any slot; instead, you chose a bare
                            Vending Machine Package with zero products on slot.
                            Remember to add things to your Machine later!""");

                    for(int x=0; x<82;x++)
                        System.out.print("═");
                    System.out.println("\n");
                }
            }
        }while((selectedSlot-1)!= productSlots.size());
        for(int x=0; x<52;x++) System.out.print("═");
        System.out.println("""

                You may like to place your initial money in your Vending Machine.
                You may do so by selecting the denomination and quantity, or you may exit.""");
        vmSetMoney(denominationFeedinterface());
        double money = this.denomination.getTotalMoney();
        System.out.println("Total Money: " + money);
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

    public void testVendingMachine(){
        Scanner scanner = new Scanner(System.in);
        Denomination userMoney = new Denomination();
        int choice;
        int exit = productSlots.size() + 1;

        moneyDisplay();
        do{
            do {
                System.out.print("Select a denomination to insert into the machine: ");
                choice = scanner.nextInt();
            }while(choice<1||(choice>11&&choice!=88));
            switch (choice){
                case 1 -> {
                    System.out.print("1 PHP coin qty: ");
                    userMoney.setOnePesoCoin(scanner.nextInt());
                }
                case 2 -> {
                    System.out.print("5 PHP coin qty: ");
                    userMoney.setFivePesoCoin(scanner.nextInt());
                }
                case 3 -> {
                    System.out.print("10 PHP coin qty: ");
                    userMoney.setTenPesoCoin(scanner.nextInt());
                }
                case 4 -> {
                    System.out.print("20 PHP coin qty: ");
                    userMoney.setTwentyPesoCoin(scanner.nextInt());
                }
                case 5 -> {
                    System.out.print("20 PHP bill qty: ");
                    userMoney.setTwentyPesoBill(scanner.nextInt());

                }
                case 6 -> {
                    System.out.print("50 PHP bill qty: ");
                    userMoney.setFiftyPesoBill(scanner.nextInt());
                }
                case 7 -> {
                    System.out.print("100 PHP bill qty: ");
                    userMoney.setOneHundredPesoBill(scanner.nextInt());

                }
                case 8 -> {
                    System.out.print("200 PHP bill qty: ");
                    userMoney.setTwoHundredPesoBill(scanner.nextInt());

                }
                case 9 -> {
                    System.out.print("500 PHP bill qty: ");
                    userMoney.setFiveHundredPesoBill(scanner.nextInt());

                }
                case 10 -> {
                    System.out.print("1000 PHP bill qty: ");
                    userMoney.setThousandPesoBill(scanner.nextInt());
                }
            }
        }while(choice != 88);



        do {
            System.out.println("Total Money: " + userMoney.getTotalMoney());
            for(int i=0; i<productSlots.size();i++) {                               //display items in VM
                System.out.printf("[%d] %-15s", i + 1, productSlots.get(i).getBaseProductName());
                if ((i + 1) % 2 == 0)
                    System.out.println(" ");
            }
            System.out.printf("[%d] Exit\n", exit);

            System.out.print("Select: ");
            choice = scanner.nextInt();
            if (choice != exit){

                choice--;

                if (isSlotEmpty(choice)){
                    System.out.println("Invalid input! Slot is empty.");
                }

                else if (!(isSlotStocked(choice))) {
                    System.out.println("Sorry! That product is out of stock...\n\n");
                }

                else if(userMoney.getTotalMoney() >= productSlots.get(choice).getBaseProductPrice()){
                    Denomination temp;
                    int confirm;
                    int totChange = (int) (userMoney.getTotalMoney() - productSlots.get(choice).getBaseProductPrice());

                    if (findDenomination(totChange, denomination).getTotalMoney() - totChange == 0){  //if true, then VMable to give enough denoms to supply change
                        System.out.println("\nYou are buying:");
                        System.out.printf("%-10s%.2f\n", productSlots.get(choice).getBaseProductName(),
                                productSlots.get(choice).getBaseProductPrice());
                        System.out.print("""
                                    Confirm purchase? 
                                    [1] Yes 
                                    [2] No\n""");
                        System.out.print("Select: ");
                        confirm = scanner.nextInt();
                        if (confirm == 1){
                            productSlots.get(choice).setProductQuantity(-1);
                            productSlots.get(choice).setNumProductsSold(1);
                            temp = findDenomination(totChange, denomination);       //temp = change in denom
                            addToDenomination(denomination, userMoney);             //transfer userMoney to denomination
                            differenceDenomination(denomination, temp);             //denomination -= temp
                            userMoney = temp;                                       //userMoney = change
                            System.out.printf("\nSuccessfully purchased item! Your change is %.2f:\n", (double)totChange);
                            displayDenominations(temp);
                            System.out.println("\n\n");
                        }
                    }
                    else {
                        System.out.println("Sorry! The vending machine is unable to give change.");
                        System.out.println("Cancelling Transaction...");
                    }
                }

                else{
                    System.out.println("Insufficient balance!");
                }
                choice = 0;
            }

        } while (!(choice >= 1 && choice <= exit));

        System.out.printf("Returning %.2f", userMoney.getTotalMoney());
        displayDenominations(userMoney);

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
        System.out.println();
    }
    private Denomination findDenomination(int money, Denomination inventory) {                  //give money in denominations based on available denoms
        Denomination moneyDenom = new Denomination();                                           //will have denominations of the change

        if (money >= 1000) {                                                                     //makes sure variable change is above 1000
            if (inventory.getThousandPesoBill()*1000 >= money) {                                //if there is enough 1k bills in VM to supply 1k denoms of change
                moneyDenom.setThousandPesoBill(money / 1000);
            }
            else {                                                                              //if there aren't enough 1k bills in VM
                moneyDenom.setThousandPesoBill(inventory.getThousandPesoBill());
            }
            money %= moneyDenom.getThousandPesoBill() * 1000;
        }

        if (money >= 500) {                                       //500
            if (inventory.getFiveHundredPesoBill()*500 >= money) {
                moneyDenom.setFiveHundredPesoBill(money / 500);
            }
            else {
                moneyDenom.setFiveHundredPesoBill(inventory.getFiveHundredPesoBill());
            }
            money %= moneyDenom.getFiveHundredPesoBill() * 500;
        }

        if (money >= 200) {                                       //200
            if (inventory.getTwoHundredPesoBill()*200 >= money) {
                moneyDenom.setTwoHundredPesoBill(money / 200);
            }
            else {
                moneyDenom.setTwoHundredPesoBill(inventory.getTwoHundredPesoBill());
            }
            money %= moneyDenom.getTwoHundredPesoBill() * 200;
        }

        if (money >= 100) {                                       //100
            if (inventory.getOneHundredPesoBill()*100 >= money) {
                moneyDenom.setOneHundredPesoBill(money / 100);
            }
            else {
                moneyDenom.setOneHundredPesoBill(inventory.getOneHundredPesoBill());
            }
            money %= moneyDenom.getOneHundredPesoBill() * 100;
        }

        if (money >= 50) {                                       //50
            if (inventory.getFiftyPesoBill()*50 >= money) {
                moneyDenom.setFiftyPesoBill(money / 50);
            }
            else {
                moneyDenom.setFiftyPesoBill(inventory.getFiftyPesoBill());
            }
            money %= moneyDenom.getFiftyPesoBill() * 50;
        }

        if (money >= 20) {                                       //20bill
            if (inventory.getTwentyPesoBill()*20 >= money) {
                moneyDenom.setTwentyPesoBill(money / 20);
            }
            else {
                moneyDenom.setTwentyPesoBill(inventory.getTwentyPesoBill());
            }
            money %= moneyDenom.getTwentyPesoBill() * 20;
        }

        if (money >= 20) {                                       //20coin
            if (inventory.getTwentyPesoCoin()*20 >= money) {
                moneyDenom.setTwentyPesoCoin(money / 20);
            }
            else {
                moneyDenom.setTwentyPesoCoin(inventory.getTwentyPesoCoin());
            }
            money %= moneyDenom.getTwentyPesoCoin() * 20;
        }

        if (money >= 10) {                                       //10
            if (inventory.getTenPesoCoin()*10 >= money) {
                moneyDenom.setTenPesoCoin(money / 10);
            }
            else {
                moneyDenom.setTenPesoCoin(inventory.getTenPesoCoin());
            }
            money %= moneyDenom.getTenPesoCoin() * 10;
        }

        if (money >= 5) {                                       //5
            if (inventory.getFivePesoCoin()*5 >= money) {
                moneyDenom.setFivePesoCoin(money / 5);
            }
            else {
                moneyDenom.setFivePesoCoin(inventory.getFivePesoCoin());
            }
            money %= moneyDenom.getFivePesoCoin() * 5;
        }

        if (money >= 1) {                                       //1
            if (inventory.getOnePesoCoin()*1 >= money) {
                moneyDenom.setOnePesoCoin(money / 1);
            }
            else {
                moneyDenom.setOnePesoCoin(inventory.getOnePesoCoin());
            }
            money %= moneyDenom.getOnePesoCoin() * 1;
        }

        return moneyDenom;
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



    private void displayDenominations(Denomination money){
        if (money.getThousandPesoBill() > 0){
            System.out.printf("%d | %s\n", money.getThousandPesoBill(), "1000 PHP");
        }
        if (money.getFiveHundredPesoBill() > 0){
            System.out.printf("%d | %s\n", money.getFiveHundredPesoBill(), "500 PHP");
        }
        if (money.getTwoHundredPesoBill() > 0){
            System.out.printf("%d | %s\n", money.getTwoHundredPesoBill(), "200 PHP");
        }
        if (money.getOneHundredPesoBill() > 0){
            System.out.printf("%d | %s\n", money.getOneHundredPesoBill(), "100 PHP");
        }
        if (money.getFiftyPesoBill() > 0){
            System.out.printf("%d | %s\n", money.getFiftyPesoBill(), "50 PHP");
        }
        if (money.getTwentyPesoBill() > 0){
            System.out.printf("%d | %s\n", money.getTwentyPesoBill(), "20 PHP");
        }
        if (money.getTwentyPesoCoin() > 0){
            System.out.printf("%d | %s\n", money.getTwentyPesoCoin(), "20 PHP");
        }
        if (money.getTenPesoCoin() > 0){
            System.out.printf("%d | %s\n", money.getTenPesoCoin(), "10 PHP");
        }
        if (money.getFivePesoCoin() > 0){
            System.out.printf("%d | %s\n", money.getFivePesoCoin(), "5 PHP");
        }
        if (money.getOnePesoCoin() > 0){
            System.out.printf("%d | %s\n", money.getOnePesoCoin(), "1 PHP");
        }

    }
}


