import javax.swing.*;
import java.awt.event.ActionListener;

public class VMFController{

    private VendingMachine currMachine;
    private final MainFrame mainView;
    private Denomination initialMoney;

    private boolean moneyActionListenersAdded = false;


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

        mainView.mainMenu.getCreateVMButton().addActionListener(e -> {
            if(currMachine != null){
                int choice = JOptionPane.showConfirmDialog(null,"There is a vending machine" +
                        " currently loaded, are you sure you want to overwrite it?","select an option",
                        JOptionPane.YES_NO_CANCEL_OPTION);
                if(choice==0){
                    currMachine = null; // delete the first mmachine first
                    initialMoney = null;
                    mainView.menu.show(mainView.cardPanel,"VMGen");
                }
            }
            else mainView.menu.show(mainView.cardPanel,"VMGen");
        });

        mainView.mainMenu.getTestVMButton().addActionListener(e -> {
            if(currMachine != null){
                mainView.menu.show(mainView.cardPanel,"VMTest");
            }
            else{
                JOptionPane.showMessageDialog(null,"Please create a Vending" +
                        " Machine First.");
            }
        });

        mainView.mainMenu.getExitFactoryButton().addActionListener(e -> {
            mainView.mainFrame.setVisible(false);
            System.exit(0);
        });

        mainView.generatorMenu.getRegVM().addActionListener(e -> {
            try{
                currMachine = new VendingMachine();
                mainView.generatorMenu.moneyInterface();

               if(!moneyActionListenersAdded){
                   mainView.generatorMenu.getExit().addActionListener(event->{
                       mainView.generatorMenu.terminateMONEYINTERFACE();
                       mainView.generatorMenu.re_setButton();
                       mainView.menu.show(mainView.cardPanel,"mainMenu"); // go back to main menu
                   });

                   mainView.generatorMenu.getSet().addActionListener(event ->{
                       initialMoney = new Denomination(mainView.generatorMenu.getValue(0),
                               mainView.generatorMenu.getValue(1),
                               mainView.generatorMenu.getValue(2),
                               mainView.generatorMenu.getValue(3),
                               mainView.generatorMenu.getValue(4),
                               mainView.generatorMenu.getValue(5),
                               mainView.generatorMenu.getValue(6),
                               mainView.generatorMenu.getValue(7),
                               mainView.generatorMenu.getValue(8),
                               mainView.generatorMenu.getValue(9));
                       currMachine.vmSetMoney(initialMoney);
                       mainView.generatorMenu.terminateMONEYINTERFACE();
                       mainView.generatorMenu.re_setButton();
                       JOptionPane.showMessageDialog(null,
                               "Successfully created Regular Vending Machine with initial" +
                                       " money: "+ currMachine.getmoney());
                       mainView.menu.show(mainView.cardPanel,"mainMenu"); // go back to main menu
                   });
                   moneyActionListenersAdded = true;
               }

            }catch (Exception error){
                JOptionPane.showMessageDialog(null,"An error " +
                        "occured: " + error.getMessage()+ " Error");
            }
        });

        mainView.generatorMenu.getSpecVM().addActionListener(e -> {
            try{
                currMachine = new SpecialVM();
                mainView.generatorMenu.moneyInterface();
                if(!moneyActionListenersAdded){
                    mainView.generatorMenu.getExit().addActionListener(event->{
                        mainView.generatorMenu.terminateMONEYINTERFACE();
                        mainView.generatorMenu.re_setButton();
                        mainView.menu.show(mainView.cardPanel,"mainMenu"); // go back to main menu
                    });
                    mainView.generatorMenu.getSet().addActionListener(event ->{
                        initialMoney = new Denomination(mainView.generatorMenu.getValue(0),
                                mainView.generatorMenu.getValue(1),
                                mainView.generatorMenu.getValue(2),
                                mainView.generatorMenu.getValue(3),
                                mainView.generatorMenu.getValue(4),
                                mainView.generatorMenu.getValue(5),
                                mainView.generatorMenu.getValue(6),
                                mainView.generatorMenu.getValue(7),
                                mainView.generatorMenu.getValue(8),
                                mainView.generatorMenu.getValue(9));
                        currMachine.vmSetMoney(initialMoney);
                        mainView.generatorMenu.terminateMONEYINTERFACE();
                        mainView.generatorMenu.re_setButton();
                        JOptionPane.showMessageDialog(null,
                                "Successfully created Special Vending Machine with initial" +
                                        " money: "+ currMachine.getmoney());
                        mainView.menu.show(mainView.cardPanel,"mainMenu"); // go back to main menu
                    });
                    moneyActionListenersAdded = true;
                }
            }catch (Exception error){
                JOptionPane.showMessageDialog(null,"An error " +
                        "occured: " + error.getMessage()+ " Error");
            }
        });

        mainView.testMenu.getSimulateVMButton().addActionListener(e -> {
            mainView.mainFrame.setResizable(false);
            mainView.menu.show(mainView.cardPanel, "vmSIM");
        });

    }
}