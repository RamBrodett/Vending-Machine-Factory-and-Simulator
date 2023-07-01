import java.util.Scanner;
public class VMFactory {
    private static VendingMachine currMachine;

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

    private void simulateVMMaintenanceFeatures(Scanner scanner, VMFactory factory){
        int choice;
        do{
            maintenanceVMmenuDisplay(factory);
            do{choice = scanner.nextInt();} while(choice<1||choice>3);
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