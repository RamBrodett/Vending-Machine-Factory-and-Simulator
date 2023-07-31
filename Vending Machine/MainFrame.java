import javax.swing.*;
import java.awt.*;

public class MainFrame {
    JFrame mainFrame;
    JFrame moneyInputFrame;
    JPanel cardPanel = new JPanel();
    CardLayout menu = new CardLayout();
    VMFMainMenu mainMenu = new VMFMainMenu();
    VMGeneratorMenu generatorMenu = new VMGeneratorMenu();
    VMFTestMenu testMenu = new VMFTestMenu();
    VMInterface vmInterface = new VMInterface();

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
    }
}
