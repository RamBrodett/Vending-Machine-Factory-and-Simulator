/**
 * Driver for the program, creates an instance of the contoller and frame(view)
 */
public class VMFDriver {
    /**
     * main arguments to run program
     * @param args
     */
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        VMFController program = new VMFController(mainFrame);
    }
}
