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
                    System.out.print("\033[H\033[2J"); // clear screen
                    System.out.flush(); //clear screen
                    currMachine = factory.createVendingMachine(scanner,factory); // create vending machine
                    System.out.print("\033[H\033[2J"); // clear screen
                    System.out.flush(); //clear screen
                }
                case 2 -> {
                    System.out.print("\033[H\033[2J"); // clear screen
                    System.out.flush(); //clear screen
                    if(currMachine == null){
                        System.out.println("There is no Vending Machine created");
                        System.out.println("\tCREATE A MACHINE FIRST!");
                    }
                    else{
                        factory.testVendingMachine(scanner);// test vending mchine
                    }
                    System.out.print("\033[H\033[2J"); // clear screen
                    System.out.flush(); //clear screen
                }
                case 3 -> {
                    System.out.print("\033[H\033[2J"); // clear screen
                    System.out.flush(); //clear screen
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
                VMChoice = new VendingMachine();
                consoleSysCom("cls");
                System.out.println(" ");
                factory.bordersDisplay(1,31);
                System.out.printf("║    %s    ║\n","VENDING MACHINE FACTORY");
                factory.bordersDisplay(3,31);
                System.out.println("[Regular] Vending Machine successfuly created.");
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
    private void testVendingMachine(Scanner scanner){

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
