import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VMFController{

    private VendingMachine currMachine;
    private final MainFrame mainView;


    VMFController(MainFrame mainFrame){
        this.mainView = mainFrame;

        //initialize Main Menu first
        mainView.menu.show(this.mainView.cardPanel,"mainMenu");

        // action to go back to main menu
        ActionListener backtoMmListener = e-> mainView.menu.show(mainView.cardPanel, "mainMenu");
        //back buttons ------------------------------------------------------
        mainView.generatorMenu.getBack().addActionListener(backtoMmListener);
        mainView.testMenu.getBackButton().addActionListener(backtoMmListener);
        //--------------------------------------------------------------------

        mainView.mainMenu.getCreateVMButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.menu.show(mainView.cardPanel,"VMGen");
            }
        });

        mainView.mainMenu.getTestVMButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.menu.show(mainView.cardPanel,"VMTest");
            }
        });

        mainView.mainMenu.getExitFactoryButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.mainFrame.setVisible(false);
                System.exit(0);
            }
        });

        mainView.generatorMenu.getRegVM().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currMachine = new VendingMachine();
                // to do here:
                //   set money pane for vending machine;
                //  then when done go back to main menu
                mainView.menu.show(mainView.cardPanel,"mainMenu"); // go back to main menu
            }
        });

        mainView.generatorMenu.getSpecVM().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currMachine = new SpecialVM();
                // to do here:
                //   set money pane for vending machine;
                //  then when done go back to main menu
                mainView.menu.show(mainView.cardPanel, "mainMenu");
            }
        });

    }

}