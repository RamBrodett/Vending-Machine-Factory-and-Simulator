import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VMFMainFrame{
    JFrame mainFrame;
    JPanel cardPanel = new JPanel();
    CardLayout menu = new CardLayout();
    VMFMainMenu mainMenu = new VMFMainMenu();
    VMGeneratorMenu generatorMenu = new VMGeneratorMenu();
    VMFTestMenu testMenu = new VMFTestMenu();

    VMFMainFrame(){
        this.mainFrame = new JFrame("YOU-G-ART FACTORY");
        mainFrame.setSize(1920,1080);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setVisible(true);

        cardPanel.setSize(1920,1080);
        cardPanel.setLayout(menu);
        this.mainFrame.add(cardPanel);


        // To switch between displays
        cardPanel.add("mainMenu", mainMenu);
        cardPanel.add("VMGen", generatorMenu);
        cardPanel.add("VMTest",testMenu);


        menu.show(cardPanel,"mainMenu");

        ActionListener createVMListener = e -> menu.show(cardPanel,"VMGen");
        mainMenu.getCreateVMButton().addActionListener(createVMListener);

        ActionListener testVMListener = e -> menu.show(cardPanel,"VMTest");
        mainMenu.getTestVMButton().addActionListener(testVMListener);

        ActionListener exitFacLister = e ->{
            mainFrame.setVisible(false);
            System.exit(0);
        };
        mainMenu.getExitFactoryButton().addActionListener(exitFacLister);
    }

}
