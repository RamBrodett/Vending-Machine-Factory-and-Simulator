import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VMFController{
    private static VendingMachine currMachine;
    JFrame mainFrame;
    JPanel cardPanel = new JPanel();
    CardLayout menu = new CardLayout();
    VMFMainMenu mainMenu = new VMFMainMenu();
    VMGeneratorMenu generatorMenu = new VMGeneratorMenu();
    VMFTestMenu testMenu = new VMFTestMenu();

    VMFController(){
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

        //ACTION LISTENERS-----------------------

            //mainmenu

        ActionListener createVMListener = e -> menu.show(cardPanel,"VMGen");
        mainMenu.getCreateVMButton().addActionListener(createVMListener);

        ActionListener testVMListener = e -> menu.show(cardPanel,"VMTest");
        mainMenu.getTestVMButton().addActionListener(testVMListener);

        ActionListener exitFacLister = e ->{
            mainFrame.setVisible(false);
            System.exit(0);
        };
        mainMenu.getExitFactoryButton().addActionListener(exitFacLister);

           //create VM (not yet right it will hang cuz it's looking for money input) **only back is working right**
        ActionListener createRVMListener = e ->{
            currMachine = new VendingMachine();
            menu.show(cardPanel,"mainMenu");
            
        };
        generatorMenu.getRegVM().addActionListener(createRVMListener);

        ActionListener createSVMListener = e ->{
            currMachine = new SpecialVM();
            menu.show(cardPanel,"mainMenu");
        };
        generatorMenu.getSpecVM().addActionListener(createSVMListener);

        ActionListener backBTListener = e -> menu.show(cardPanel,"mainMenu");
        generatorMenu.getBack().addActionListener(backBTListener);

    }

}
