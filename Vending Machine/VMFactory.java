import java.util.Scanner;
public class VMFactory {
    private static VendingMachine currMachine;

    public static void main(String[] args) {

        VMFactory factory = new VMFactory();
        Scanner scanner = new Scanner(System.in);

        boolean terminateProgram = false;

        do{
            factory.mainMenuDisplay();
            int choice = scanner.nextInt();
            switch (choice){
                case 1 -> {
                    // create vending machine
                }
                case 2 -> {
                    // test vending mchine
                }
                case 3 -> {
                    terminateProgram = true;
                }
            }
            System.out.print("\033[H\033[2J"); // clear screen
            System.out.flush(); //clear screen
        }while(!terminateProgram);


    }

    private void mainMenuDisplay(){
        VMFactory factory = new VMFactory();

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
