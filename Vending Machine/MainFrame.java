import javax.swing.*;
import java.awt.*;

public class MainFrame {
    public JFrame mainFrame;
    public JPanel cardPanel = new JPanel();
    public CardLayout menu = new CardLayout();
    public VMFMainMenu mainMenu = new VMFMainMenu();
    public VMGeneratorMenu generatorMenu = new VMGeneratorMenu();
    public VMFTestMenu testMenu = new VMFTestMenu();
    public VMInterface vmInterface = new VMInterface();

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
