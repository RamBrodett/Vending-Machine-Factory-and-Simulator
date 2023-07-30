import javax.swing.*;
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

        mainView.mainMenu.getCreateVMButton().addActionListener(e -> mainView.menu.show(mainView.cardPanel,"VMGen"));

        mainView.mainMenu.getTestVMButton().addActionListener(e -> {
            if(currMachine != null){
                mainView.menu.show(mainView.cardPanel,"VMTest");
            }
            else{
                mainView.optionPane.showMessageDialog(null,"Please create a Vending" +
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
                mainView.generatorMenu.getExit().addActionListener(event->{
                    Denomination initialMoney = new Denomination();
                    mainView.generatorMenu.terminateMONEYINTERFACE();
                    mainView.generatorMenu.re_setButton();
                    mainView.menu.show(mainView.cardPanel,"mainMenu"); // go back to main menu
                });
                mainView.generatorMenu.getSet().addActionListener(event ->{
                    Denomination initialMoney = new Denomination(mainView.generatorMenu.getValue(0),
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
                    mainView.generatorMenu.optionPane.showMessageDialog(null, "Successfully " +
                            "created Reular Vending Machine with initial money: "+ currMachine.getmoney());
                    mainView.menu.show(mainView.cardPanel,"mainMenu"); // go back to main menu
                });

            }catch (Exception error){
                mainView.generatorMenu.optionPane.showMessageDialog(null,"An error " +
                        "occured: " + error.getMessage()+ " Error");
            }
        });

        mainView.generatorMenu.getSpecVM().addActionListener(e -> {
            try{
                currMachine = new SpecialVM();
                mainView.generatorMenu.moneyInterface();
                mainView.generatorMenu.getExit().addActionListener(event->{
                    Denomination initialMoney = new Denomination();
                    mainView.generatorMenu.terminateMONEYINTERFACE();
                    mainView.generatorMenu.re_setButton();
                    mainView.menu.show(mainView.cardPanel,"mainMenu"); // go back to main menu
                });
                mainView.generatorMenu.getSet().addActionListener(event ->{
                    Denomination initialMoney = new Denomination(mainView.generatorMenu.getValue(0),
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
                    mainView.generatorMenu.optionPane.showMessageDialog(null, "Successfully " +
                            "created Special Vending Machine with initial money: "+ currMachine.getmoney());
                    mainView.menu.show(mainView.cardPanel,"mainMenu"); // go back to main menu
                });
            }catch (Exception error){
                mainView.generatorMenu.optionPane.showMessageDialog(null,"An error " +
                        "occured: " + error.getMessage()+ " Error");
            }
            // to do here:
            //   set money pane for vending machine;
            //  then when done go back to main menu
            mainView.menu.show(mainView.cardPanel, "mainMenu");
        });

        mainView.testMenu.getSimulateVMButton().addActionListener(e -> {
            mainView.mainFrame.setResizable(false);
            mainView.menu.show(mainView.cardPanel, "vmSIM");
        });

    }
}