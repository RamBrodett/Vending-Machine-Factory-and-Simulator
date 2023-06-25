import java.util.Scanner;
public class VMFactory {
    private VendingMachine currMachine;

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
                    factory.createVendingMachine(); // create vending machine
                }
                case 2 -> {
                    // test vending mchine
                }
                case 3 -> {
                    terminateProgram = true;
                    System.out.print("Terminating program");
                    for(int i=0;i<30;i++) {
                        for (int j = 0; j < 100000005; j++) {// loading effect
                        }
                        System.out.print(".");
                    }
                }
            }
            System.out.print("\033[H\033[2J"); // clear screen
            System.out.flush(); //clear screen
        }while(!terminateProgram);
        scanner.close();
        System.out.println("\nProgram termination successful");
    }

    // Vending Machine Production methods

    private void createVendingMachine(){
        this.currMachine = new VendingMachine();
        System.out.println("[Regular] Vending Machine successfuly created.");
    }
    private void testVendingMachine(){

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

}
