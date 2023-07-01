import java.util.Scanner;

/**
 * Vending Machine Factory.
 * <p>
 *     This is a simulator of a vending machine factory, where the creator can adjust
 *     the specification such as slots and initial items and denomination of a vending machine.
 *     and where we can test the vending machine features such as the User-Vending Machine
 *     Transaction, restock items and money, and print summary of transaction.
 * </p>
 * @author Ram Brodett
 * @author Luke Regalado
 * @version 07/1/2023
 */

public class VMFactory {
    /**
     *  Declaration of a type of Vending Machine called currMachine,
     *  Vending machines created is stored here.
     */
    private static VendingMachine currMachine;

    /**
     * Main method of the program. Provides the user interface for interacting with the vending machine factory.
     * @param args command-line arguments
     */

    public static void main(String[] args) {

        VMFactory factory = new VMFactory();
        Scanner scanner = new Scanner(System.in);

        boolean terminateProgram = false;

        do{
            factory.mainMenuDisplay(factory);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1 -> {
                    factory.consoleSysCom("cls"); // clear screen helper
                    currMachine = factory.createVendingMachine(scanner,factory); // create vending machine
                    factory.consoleSysCom("cls"); // clear screen helper
                }
                case 2 -> {
                    factory.consoleSysCom("cls"); // clear screen helper
                    if(currMachine == null){
                        System.out.println("There is no Vending Machine created");
                        System.out.println("\tCREATE A MACHINE FIRST!");
                    }
                    else{
                        factory.testVMmenuDisplay(factory);
                        factory.testVendingMachine(scanner, factory);// test vending mchine
                    }
                    factory.consoleSysCom("cls"); // clear screen helper
                }
                case 3 -> {
                    factory.consoleSysCom("cls"); // clear screen helper
                    terminateProgram = true;
                    System.out.print("Terminating program");
                    for(int i=0;i<30;i++) {
                        for (int j = 0; j < 100000005; j++);// loading effect
                        System.out.print(".");
                    }
                }
            }
            factory.consoleSysCom("cls");
        }while(!terminateProgram);
        scanner.close();
        System.out.println("\nProgram termination successful");
    }

    // Vending Machine Production methods

    /**
     * Vening Machine creation.
     * <p>
     *     This method creates the vending machine base on the creator's preference,
     *     such as supplying number of slots.
     * </p>
     *
     * @param scanner used for accepting input.
     * @param factory used for calling the methods within VMFactory class.
     * @return VMChoice, Vending Machine object.
     */
    private VendingMachine createVendingMachine(Scanner scanner,VMFactory factory){
        createVMmenuDisplay(factory);
        int choice = scanner.nextInt();
        VendingMachine VMChoice = null;
        switch (choice){
            case 1 -> {
                int slotCapacity;
                do {
                    System.out.print("Number of slots you want: ");
                    slotCapacity = scanner.nextInt();
                    if(slotCapacity<8)
                        System.out.println("Minimum number of slots is 8");
                }while(slotCapacity < 8);
                VMChoice = new VendingMachine(slotCapacity);
                consoleSysCom("cls");
                System.out.println(" ");
                factory.bordersDisplay(1,31);
                System.out.printf("║    %s    ║\n","VENDING MACHINE FACTORY");
                factory.bordersDisplay(2,31);
                System.out.println("[Regular] Vending Machine successfully created.");
                System.out.println("Press any key to Continue...");
            }
            case 2 -> {
                //VMChoice = new SpecialVM();
                System.out.println("[Special] Vending Machine is not yet available.");
                System.out.println("Press any key to Continue...");
            }
        }
        consoleSysCom("fflush");
        return VMChoice;
    }

    /**
     * VendingMachine Test.
     * <p>
     *     "Initializes the cases for different tests."
     * </p>
     *
     * @param used for accepting input.
     * @param factory used for calling the methods within VMFactory class.
     */
    private void testVendingMachine(Scanner scanner, VMFactory factory) {
        int choice;
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> currMachine.vendingMachineUserTransaction();             //simulate transaction
            case 2 -> simulateVMMaintenanceFeatures(scanner, factory);              //half implemented
        }

        System.out.println("Press any key to Continue...");
        consoleSysCom("fflush");
    }


    // Display methods--------------------------------------------------

    /**
     * Display for the main menu.
     * @param factory used for calling the methods within the VMFactory class.
     */
    private void mainMenuDisplay(VMFactory factory){

        factory.bordersDisplay(1,31);
        System.out.printf("║    %s    ║\n","VENDING MACHINE FACTORY");
        factory.bordersDisplay(3,31);
        factory.bordersDisplay(4,31);
        System.out.printf("║  %-27s  ║\n","1. Create a Vending Machine");
        System.out.printf("║  %-27s  ║\n","2. Test a Vending Machine");
        System.out.printf("║  %-27s  ║\n","3. Exit");
        factory.bordersDisplay(4,31);
        factory.bordersDisplay(2,31);
        System.out.print("Enter your choice: ");
    }

    /**
     * Display the borders for different parts of the menu.
     *
     * @param part    the portion of the border to be used.
     * @param hlength the horizontal length of the border
     */
    private void bordersDisplay(int part, int hlength){

        switch (part) {
            case 1 -> {
                System.out.print("╔");
                for (int i = 0; i < hlength; i++)
                    System.out.print("═");
                System.out.println("╗");
            }
            case 2 -> {
                System.out.print("╚");
                for (int i = 0; i < hlength; i++)
                    System.out.print("═");
                System.out.println("╝");
            }
            case 3 -> {
                System.out.print("╠");
                for (int i = 0; i < hlength; i++)
                    System.out.print("═");
                System.out.println("╣");
            }
            case 4 -> {
                System.out.print("║");
                for (int i = 0; i < hlength; i++)
                    System.out.print(" ");
                System.out.println("║");
            }
        }
    }

    /**
     * Simulates the maintenance features of the vending machine.
     *
     * @param scanner used for accepting input.
     * @param factory used for calling the methods within the VMFactory class.
     */

    private void simulateVMMaintenanceFeatures(Scanner scanner, VMFactory factory){
        int choice;
        do{
            maintenanceVMmenuDisplay(factory);
            do{choice = scanner.nextInt();} while(choice<1||choice>4);
            switch (choice){
                case 1 -> currMachine.editItems();
                case 2 -> {moneyBoxVMmenuDisplay(factory);
                            currMachine.moneyBox();}
                case 3 -> {
                    currMachine.displayTransactions();
                    System.out.print("Press any key to Continue...");
                    consoleSysCom("fflush");
                }
            }
        }while(choice!=4);

    }

    /**
     * Display for creating a vending machine menu.
     * @param factory used for calling the methods within the VMFactory class.
     */

    private void createVMmenuDisplay(VMFactory factory){
        factory.bordersDisplay(1,31);
        System.out.printf("║     %-22s    ║\n","VENDING MACHINE TYPES");
        factory.bordersDisplay(3,31);
        factory.bordersDisplay(4,31);
        System.out.printf("║  %-27s  ║\n","1. Regular Vending Machine");
        System.out.printf("║  %-27s  ║\n","2. Special Vending Machine");
        factory.bordersDisplay(4,31);
        factory.bordersDisplay(2,31);
        System.out.print("Enter your choice: ");
    }

    /**
     * Display for testing a vending machine menu.
     * @param factory used for calling the methods within the VMFactory class.
     */

    private void testVMmenuDisplay(VMFactory factory){
        factory.bordersDisplay(1,35);
        System.out.printf("║       %-24s    ║\n","TEST A VENDING MACHINE");
        factory.bordersDisplay(3,35);
        factory.bordersDisplay(4,35);
        System.out.printf("║  %-31s  ║\n","1. Simulate Vending Transaction");
        System.out.printf("║  %-31s  ║\n","2. Maintenance Features");
        System.out.printf("║  %-31s  ║\n","3. Exit");
        factory.bordersDisplay(4,35);
        factory.bordersDisplay(2,35);
        System.out.print("Enter your choice: ");
    }

    /**
     * Display for maintenance operations on a vending machine menu.
     * @param factory used for calling the methods within the VMFactory class.
     */

    private void maintenanceVMmenuDisplay(VMFactory factory){
        factory.bordersDisplay(1,35);
        System.out.printf("║        %-24s   ║\n","CONDUCT MAINTENANCE");
        factory.bordersDisplay(3,35);
        factory.bordersDisplay(4,35);
        System.out.printf("║  %-31s  ║\n","1. Edit/Add Item");
        System.out.printf("║  %-31s  ║\n","2. Open Money Box");
        System.out.printf("║  %-31s  ║\n","3. Display Transaction Summary");
        System.out.printf("║  %-31s  ║\n","4. Exit");
        factory.bordersDisplay(4,35);
        factory.bordersDisplay(2,35);
        System.out.print("Enter your choice: ");
    }

    /**
     * Display for money box operations on a vending machine menu.
     * @param factory used for calling the methods within the VMFactory class.
     */
    private void moneyBoxVMmenuDisplay(VMFactory factory){
        factory.bordersDisplay(1,35);
        System.out.printf("║        %-24s   ║\n","CONDUCT MAINTENANCE");
        factory.bordersDisplay(3,35);
        factory.bordersDisplay(4,35);
        System.out.printf("║  %-31s  ║\n","1. Collect payments");
        System.out.printf("║  %-31s  ║\n","2. Replenish money");
        System.out.printf("║  %-31s  ║\n","3. Exit");
        factory.bordersDisplay(4,35);
        factory.bordersDisplay(2,35);
    }

    /**
     * Execute console system commands.
     *
     * @param cmd console system command to execute such as cls(clear screen).
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