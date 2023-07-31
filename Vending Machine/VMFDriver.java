public class VMFDriver {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        VMFController program = new VMFController(mainFrame);
        program.initializeProgram();
    }
}
