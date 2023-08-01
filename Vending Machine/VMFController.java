import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.PrintStream;

public class VMFController {

    /**
     * Holder for the current Vending Machine (Model) created.
     */
    private VendingMachine currMachine;
    /**
     * Saves what type of Vending Machine is currently created.
     */

    private String machineType;

    /**
     * Holder for the Active-frame (View) instance created.
     */
    private final MainFrame frame;


    /**
     * This initializes what operations shall take place when a Listener received an event.
     *
     * @param frame is the instance of Jframe passed by a driver.
     */
    VMFController(MainFrame frame){
        //stores the frame as Active Frame
        this.frame = frame;

        //Go back to previous screen event Listener ---------------------------
        ActionListener backToMainListener = e -> this.frame.menu.show(this.frame.cardPanel, "mainMenu");
        ActionListener backToTestMenuListener = e -> this.frame.menu.show(this.frame.cardPanel, "VMTest");
        //Back Buttons -------------------------------------------------
        this.frame.generatorMenu.getBack().addActionListener(backToMainListener);
        this.frame.testMenu.getBackButton().addActionListener(backToMainListener);
        this.frame.maintenanceMenu.getBack().addActionListener(backToTestMenuListener);
        this.frame.vmInterface.getBack().addActionListener(backToTestMenuListener);

        //--------------------------------------------------------------

        // Factory Main Menu Buttons -----------------------------------

        this.frame.mainMenu.getCreateVMButton().addActionListener(e->{
            this.frame.generatorMenu.setMoneyInputFrame(new JFrame());
            if(currMachine != null){
                int choice = JOptionPane.showConfirmDialog(null,"There is a vending machine" +
                                " currently loaded, are you sure you want to overwrite it?", "Select an option",
                        JOptionPane.YES_NO_CANCEL_OPTION);
                if(choice == 0){
                    currMachine = null;
                    this.frame.menu.show(this.frame.cardPanel, "VMGen");
                }
            } else this.frame.menu.show(this.frame.cardPanel, "VMGen"); // for no current machine loaded
        });

        this.frame.mainMenu.getTestVMButton().addActionListener(e ->{
            if(currMachine != null){
                this.frame.menu.show(this.frame.cardPanel,"VMTest");
            } else{
                JOptionPane.showMessageDialog(null,"Please create a Vending" +
                        " Machine First.");
            }
        });

        this.frame.mainMenu.getExitFactoryButton().addActionListener(e->{
            this.frame.mainFrame.setVisible(false);
            System.exit(0);
        });
        //------------------------------------------------------------

        // Generate Menu Buttons -------------------------------------

        this.frame.generatorMenu.getRegVM().addActionListener(e->{
            try{
                currMachine = new VendingMachine();
                updateMachineType("Regular");
                this.frame.testMenu.getVmImage().setIcon(this.frame.testMenu.scaleIMG(
                        "./VMResources/regVM.png",500,800));
                this.frame.generatorMenu.moneyInterface();
                this.frame.vmInterface.getToggleSpecial().setVisible(false);
            }catch (Exception error){
                JOptionPane.showMessageDialog(null,"An error " +
                        "occured: " + error.getMessage()+ " Error");
            }
        });

        this.frame.generatorMenu.getSpecVM().addActionListener(e ->{
            try{
                currMachine = new SpecialVM();
                updateMachineType("Special");
                this.frame.testMenu.getVmImage().setIcon(this.frame.testMenu.scaleIMG(
                        "./VMResources/specVM.png",500,800));
                this.frame.generatorMenu.moneyInterface();
                this.frame.vmInterface.getToggleSpecial().setVisible(true);
            }catch (Exception error){
                JOptionPane.showMessageDialog(null,"An error " +
                        "occured: " + error.getMessage()+ " Error");
            }
        });

        this.frame.generatorMenu.getExit().addActionListener(e ->{
            this.frame.generatorMenu.terminateMONEYINTERFACE();
            JOptionPane.showMessageDialog(null,
                    "Aborted creation of "+ machineType +" Vending Machine");
            currMachine = null;
            this.frame.menu.show(this.frame.cardPanel, "mainMenu");
        });


        this.frame.generatorMenu.getSet().addActionListener(e ->{
            currMachine.vmSetMoney(new Denomination(this.frame.generatorMenu.getValue(0),
                    this.frame.generatorMenu.getValue(1),
                    this.frame.generatorMenu.getValue(2),
                    this.frame.generatorMenu.getValue(3),
                    this.frame.generatorMenu.getValue(4),
                    this.frame.generatorMenu.getValue(5),
                    this.frame.generatorMenu.getValue(6),
                    this.frame.generatorMenu.getValue(7),
                    this.frame.generatorMenu.getValue(8),
                    this.frame.generatorMenu.getValue(9)));
            this.frame.generatorMenu.terminateMONEYINTERFACE();
            JOptionPane.showMessageDialog(null,
                    "Successfully created "+ machineType +" Vending Machine with initial" +
                            " money: "+ currMachine.getmoney());
            this.frame.menu.show(this.frame.cardPanel, "mainMenu");
        });
        //-----------------------------------------------------------

        // Test Menu Buttons ----------------------------------------
        this.frame.testMenu.getSimulateVMButton().addActionListener(e ->{
            this.frame.mainFrame.setResizable(false);
            this.frame.menu.show(this.frame.cardPanel, "vmSIM");
            System.setOut(new PrintStream(new VMInterface.TextPane(this.frame.vmInterface.getTextPanel())));
        });

        this.frame.testMenu.getMaintenanceVMButton().addActionListener(e -> {
            this.frame.menu.show(this.frame.cardPanel, "VMmaintenance");
            System.setOut(new PrintStream(new VMMaintenanceMenu.TextPane(this.frame.maintenanceMenu.getTextPanel())));
        });
        //-----------------------------------------------------------

        // Vending Machine Interface Menu Buttons -------------------
        this.frame.vmInterface.getInsertMoney().addActionListener(e ->{
            // if insert money pressed in selection mode go to payment mode and viceversa
            if(this.frame.vmInterface.getModeButtonPanel().equalsIgnoreCase("selection")) {
                this.frame.vmInterface.updateButtonPanel(2);

            } else if (this.frame.vmInterface.getModeButtonPanel().equalsIgnoreCase("payment")) {
                //to do here create a lock payment and now if locked payment and pressed insertmoney it will receive paymment
                if(this.frame.vmInterface.getMoneyLocked()){
                    currMachine.setInsertedMoney(new Denomination(this.frame.vmInterface.getValue(0),
                            this.frame.vmInterface.getValue(1),
                            this.frame.vmInterface.getValue(2),
                            this.frame.vmInterface.getValue(3),
                            this.frame.vmInterface.getValue(4),
                            this.frame.vmInterface.getValue(5),
                            this.frame.vmInterface.getValue(6),
                            this.frame.vmInterface.getValue(7),
                            this.frame.vmInterface.getValue(8),
                            this.frame.vmInterface.getValue(9)));
                    System.out.println("Money inserted: " + currMachine.getInsertedmoney());
                    this.frame.vmInterface.resetMoneyPanel();
                }
                else this.frame.vmInterface.updateButtonPanel(1);
            }

        });

        this.frame.vmInterface.getToggleSpecial().addActionListener(e -> {
            this.frame.vmInterface.updateItemPanel();
        });

        this.frame.vmInterface.moneyLockToggle().addItemListener(e->{
            if(e.getStateChange()==ItemEvent.SELECTED)this.frame.vmInterface.setLockMoney(true); // true if selected
            else this.frame.vmInterface.setLockMoney(false); // false if deselected
        });
        //-----------------------------------------------------------------

        // Maintenance Menu Buttons ----------------------------------------

        this.frame.maintenanceMenu.getToggleSpecial().addActionListener(e -> {      //
            this.frame.maintenanceMenu.updateItemPanel();
        });

        this.frame.maintenanceMenu.getRestockBtn().addActionListener(e -> {         //RESTOCK ITEMS
                this.frame.maintenanceMenu.updateButtonPanel(1);
        });

        this.frame.maintenanceMenu.moneyLockToggle().addItemListener(e->{
            if(e.getStateChange()==ItemEvent.SELECTED) this.frame.maintenanceMenu.setMoneyLocked(true);
            else this.frame.maintenanceMenu.setMoneyLocked(false);
        });

        this.frame.maintenanceMenu.getReplenishBtn().addActionListener(e -> {
            if (!this.frame.maintenanceMenu.getModeCurrMode().equals("replenish")) {
                this.frame.maintenanceMenu.updateButtonPanel(2);
            }else if(this.frame.maintenanceMenu.getModeCurrMode().equals("replenish")) {
                if (this.frame.maintenanceMenu.getMoneyLocked()) {
                    currMachine.vmSetMoney(new Denomination(this.frame.maintenanceMenu.getValue(0),                 //NEED TO FIX PARA INCREMENT LANG SIYA
                            this.frame.maintenanceMenu.getValue(1),
                            this.frame.maintenanceMenu.getValue(2),
                            this.frame.maintenanceMenu.getValue(3),
                            this.frame.maintenanceMenu.getValue(4),
                            this.frame.maintenanceMenu.getValue(5),
                            this.frame.maintenanceMenu.getValue(6),
                            this.frame.maintenanceMenu.getValue(7),
                            this.frame.maintenanceMenu.getValue(8),
                            this.frame.maintenanceMenu.getValue(9)));
                    System.out.println("Money inserted: " + currMachine.getmoney());
                    this.frame.maintenanceMenu.resetMoneyPanel();
                } else
                    this.frame.maintenanceMenu.updateButtonPanel(2);
            }
        });

        this.frame.maintenanceMenu.getCollectBtn().addActionListener(e -> {
            this.frame.maintenanceMenu.updateButtonPanel(3);
        });

        this.frame.maintenanceMenu.getEditPriceBtn().addActionListener(e -> {
            this.frame.maintenanceMenu.updateButtonPanel(4);
        });

        //------------------------------------------------------------------
    }

    /**
     * Initializer of the start of the program.
     */
    public void initializeProgram(){
        frame.menu.show(frame.cardPanel,"mainMenu");
    }

    /**
     * updates the curr machine type.
     * @param type String relating to what type of machine
     */
    private void updateMachineType(String type){
        machineType = type;
    }

}