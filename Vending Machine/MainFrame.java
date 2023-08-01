import javax.swing.*;
import java.awt.*;

public class MainFrame {

    /**
     * Main Frame of the GUI.
     */
    JFrame mainFrame;

    /**
     *  Main Panel that will hold panels of different menu.
     */
    JPanel cardPanel = new JPanel();

    /**
     * Layout for the mainPanel.
     */
    CardLayout menu = new CardLayout();

    /**
     * Instance of MainMenu Panel.
     */
    VMFMainMenu mainMenu = new VMFMainMenu();

    /**
     * Instance of GeneratorMenu Panel.
     */
    VMGeneratorMenu generatorMenu = new VMGeneratorMenu();

    /**
     * Instance of Test Menu Panel.
     */
    VMFTestMenu testMenu = new VMFTestMenu();
    /**
     *  Instance of Maintenance Menu Panel.
     */
    VMMaintenanceMenu maintenanceMenu = new VMMaintenanceMenu();

    /**
     *   Instance of Vending Machine interface Panel.
     */
    VMInterface vmInterface = new VMInterface();

    /**
     * Initialization of MainFrame Components.
     */
    MainFrame(){
        this.mainFrame = new JFrame("YOU-G-ART FACTORY");
        mainFrame.setSize(1920,1080);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setVisible(true);

        cardPanel.setSize(1920,1080);
        cardPanel.setLayout(menu);
        this.mainFrame.add(cardPanel);

        cardPanel.add("mainMenu", mainMenu);
        cardPanel.add("VMGen", generatorMenu);
        cardPanel.add("VMTest",testMenu);
        cardPanel.add("vmSIM", vmInterface);
        cardPanel.add("VMmaintenance", maintenanceMenu);
    }
}
