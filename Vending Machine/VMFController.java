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

        mainView.mainMenu.getTestVMButton().addActionListener(e -> mainView.menu.show(mainView.cardPanel,"VMTest"));

        mainView.mainMenu.getExitFactoryButton().addActionListener(e -> {
            mainView.mainFrame.setVisible(false);
            System.exit(0);
        });

        mainView.generatorMenu.getRegVM().addActionListener(e -> {
            try{
                currMachine = new VendingMachine();
                mainView.generatorMenu.optionPane.showMessageDialog(null, "Successfully " +
                        "created Regular Vending Machine");

            }catch (Exception error){
                mainView.generatorMenu.optionPane.showMessageDialog(null,"An error " +
                        "occured: " + error.getMessage()+ " Error");
            }
            // to do here:
            //   set money pane for vending machine;
            //  then when done go back to main menu

            mainView.menu.show(mainView.cardPanel,"mainMenu"); // go back to main menu
        });

        mainView.generatorMenu.getSpecVM().addActionListener(e -> {
            currMachine = new SpecialVM();
            // to do here:
            //   set money pane for vending machine;
            //  then when done go back to main menu
            mainView.menu.show(mainView.cardPanel, "mainMenu");
        });

    }

}