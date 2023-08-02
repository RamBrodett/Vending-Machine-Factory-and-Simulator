import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.PrintStream;
import java.util.ArrayList;

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
     * Holder for the found index.
     */
    private int foundIndex;


    /**
     * This initializes what operations shall take place when a Listener received an event.
     *
     * @param frame is the instance of Jframe passed by a driver.
     */
    VMFController(MainFrame frame){
        //stores the frame as Active Frame
        this.frame = frame;

        initializeProgram();

    }

    /**
     * Initializer of the start of the program.
     */
    private void initializeProgram(){
        frame.menu.show(frame.cardPanel,"mainMenu");

        //initialize Listeners
        initializeMainMenu();
        initializeGenMenu();
        initializeBackMethod();
        initializeTestMenu();
        initializeVMInterface();
        initializeVMmaintenance();
        initializeSimulation();

    }

    /**
     * Initializes Main Menu Components and controlls interaction of user to manipulate display.
     */
    private void initializeMainMenu(){
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
    }

    /**
     * Initializes Generating Menu Components and controlls interaction of user to manipulate display.
     */
    private void initializeGenMenu(){

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
                            " money: "+ currMachine.getTotalMoney());

            currMachine.setInsertedMoney(new Denomination());                //RESET ALL MONEY

            this.frame.vmInterface.getTextPanel().setText(null);             //RESET ALL TRANSACTION PROCESS TEXT
            this.frame.maintenanceMenu.getTextPanel().setText(null);

            this.frame.vmInterface.getMoneyDisplay().updateMoneyDisplay(0);  //RESET ALL MONEY DISPLAYS
            this.frame.maintenanceMenu.getMoneyDisplay().updateMoneyDisplay(currMachine.getTotalMoney());

            this.frame.menu.show(this.frame.cardPanel, "mainMenu");
        });

    }

    /**
     * Initializes back controlls interaction of user to manipulate display.
     */
    private void initializeBackMethod(){
        //Go back to previous screen event Listener ---------------------------
        ActionListener backToMainListener = e -> this.frame.menu.show(this.frame.cardPanel, "mainMenu");
        ActionListener backToTestMenuListener = e -> {
            if(e.getSource() ==this.frame.vmInterface.getBack()){
                float tempmoneyHolder = currMachine.getTotalInsertedMoney();
                JOptionPane.showMessageDialog(null,"Change Ejected: PHP "
                        + tempmoneyHolder);
                currMachine.setInsertedMoney(new Denomination());
                this.frame.vmInterface.getMoneyDisplay()
                        .updateMoneyDisplay((float)currMachine.getTotalInsertedMoney());
            }
            this.frame.menu.show(this.frame.cardPanel, "VMTest");
        };
        //Back Buttons -------------------------------------------------
        this.frame.generatorMenu.getBack().addActionListener(backToMainListener);
        this.frame.testMenu.getBackButton().addActionListener(backToMainListener);
        this.frame.maintenanceMenu.getBack().addActionListener(backToTestMenuListener);
        this.frame.vmInterface.getBack().addActionListener(backToTestMenuListener);
    }

    /**
     * Initializes Test Menu Components and controlls interaction of user to manipulate display.
     */
    private void initializeTestMenu(){
        this.frame.testMenu.getSimulateVMButton().addActionListener(e ->{
            this.frame.mainFrame.setResizable(false);
            this.frame.menu.show(this.frame.cardPanel, "vmSIM");
            System.setOut(new PrintStream(new VMInterface.TextPane(this.frame.vmInterface.getTextPanel())));
        });

        this.frame.testMenu.getMaintenanceVMButton().addActionListener(e -> {
//            this.frame.maintenanceMenu.getButtonPanel().updateTransacHistory(currMachine.productsSold);
            this.frame.menu.show(this.frame.cardPanel, "VMmaintenance");
            System.setOut(new PrintStream(new VMMaintenanceMenu.TextPane(this.frame.maintenanceMenu.getTransacHistoryPanel())));
            this.frame.maintenanceMenu.getTransacHistoryPanel().selectAll();
            this.frame.maintenanceMenu.getTransacHistoryPanel().replaceSelection("");
            currMachine.displayTransactions();
            System.setOut(new PrintStream(new VMMaintenanceMenu.TextPane(this.frame.maintenanceMenu.getTextPanel())));
        });

    }

    /**
     * Initializes Vending Machine Interface Components and controlls interaction of user to manipulate display.
     */
    private void initializeVMInterface(){
        this.frame.vmInterface.getInsertMoney().addActionListener(e ->{
            // if insert money pressed in selection mode go to payment mode and viceversa
            if(this.frame.vmInterface.getModeButtonPanel().equalsIgnoreCase("selection")) {
                this.frame.vmInterface.updateButtonPanel(2);

            } else if (this.frame.vmInterface.getModeButtonPanel().equalsIgnoreCase("payment")) {
                //to do here create a lock payment and now if locked payment and pressed insertmoney it will receive paymment
                if(this.frame.vmInterface.getMoneyLocked()){
                    Denomination moneyToAdd = new Denomination(
                            this.frame.vmInterface.getValue(0),
                            this.frame.vmInterface.getValue(1),
                            this.frame.vmInterface.getValue(2),
                            this.frame.vmInterface.getValue(3),
                            this.frame.vmInterface.getValue(4),
                            this.frame.vmInterface.getValue(5),
                            this.frame.vmInterface.getValue(6),
                            this.frame.vmInterface.getValue(7),
                            this.frame.vmInterface.getValue(8),
                            this.frame.vmInterface.getValue(9));

                    currMachine.addToDenomination(currMachine.getInsertedMoney(), moneyToAdd);
                    System.out.println("Money inserted: " + moneyToAdd.getTotalMoney());
                    this.frame.vmInterface.getMoneyDisplay().updateMoneyDisplay(currMachine.getTotalInsertedMoney());
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
    }

    /**
     * Initializes Vending Machine Maintenance Interface Components and controlls interaction of user to manipulate display.
     */
    private void initializeVMmaintenance(){

        this.frame.maintenanceMenu.getToggleSpecial().addActionListener(e -> {      //TOGGLE ITEM PANEL
            if (this.frame.maintenanceMenu.getModeCurrMode().equals("restock")) {   //FOR SPECIAL RESTOCK
                ArrayList<Integer> results = new ArrayList<>(); // index 0 - index || index 1 - qty
                currMachine.restockInput(results);
                currMachine.productSlots.get(results.get(0)).setProductQuantity(results.get(1));
            }

            else
                this.frame.maintenanceMenu.updateItemPanel();
        });

        this.frame.maintenanceMenu.getRestockBtn().addActionListener(e -> {         //RESTOCK ITEMS
            this.frame.maintenanceMenu.resetButtonPanelBtns();
            this.frame.maintenanceMenu.updateButtonPanel(1);
        });

        this.frame.maintenanceMenu.moneyLockToggle().addItemListener(e->{
            if(e.getStateChange()==ItemEvent.SELECTED) this.frame.maintenanceMenu.setMoneyLocked(true);
            else this.frame.maintenanceMenu.setMoneyLocked(false);
        });

        this.frame.maintenanceMenu.getReplenishBtn().addActionListener(e -> {
            if (!this.frame.maintenanceMenu.getModeCurrMode().equals("replenish")) {
                this.frame.maintenanceMenu.resetButtonPanelBtns();
                this.frame.maintenanceMenu.updateButtonPanel(2);
            }else if(this.frame.maintenanceMenu.getModeCurrMode().equals("replenish")) {
                if (this.frame.maintenanceMenu.getMoneyLocked()) {
                    Denomination denomToAdd = new Denomination(
                            this.frame.maintenanceMenu.getValue(0),
                            this.frame.maintenanceMenu.getValue(1),
                            this.frame.maintenanceMenu.getValue(2),
                            this.frame.maintenanceMenu.getValue(3),
                            this.frame.maintenanceMenu.getValue(4),
                            this.frame.maintenanceMenu.getValue(5),
                            this.frame.maintenanceMenu.getValue(6),
                            this.frame.maintenanceMenu.getValue(7),
                            this.frame.maintenanceMenu.getValue(8),
                            this.frame.maintenanceMenu.getValue(9));

                    currMachine.addToDenomination(currMachine.getDenomination(), denomToAdd);
                    System.out.println("Money inserted: " + denomToAdd.getTotalMoney());
                    this.frame.maintenanceMenu.getMoneyDisplay().updateMoneyDisplay(currMachine.getTotalMoney());
                    this.frame.maintenanceMenu.resetButtonPanelBtns();
                    this.frame.maintenanceMenu.resetMoneyPanel();
                } else {
                    this.frame.maintenanceMenu.resetButtonPanelBtns();
                    this.frame.maintenanceMenu.updateButtonPanel(2);
                }
            }
        });

        this.frame.maintenanceMenu.getCollectBtn().addActionListener(e -> {
            this.frame.maintenanceMenu.resetButtonPanelBtns();
            this.frame.maintenanceMenu.updateButtonPanel(3);
        });

        this.frame.maintenanceMenu.getButtonPanel().getCollectBtn().addActionListener(e -> {
            if (currMachine.getTotalMoney() != 0){
                int choice = JOptionPane.showConfirmDialog(null,"Collecting P" +
                                currMachine.getTotalMoney(), "Select an option",
                        JOptionPane.YES_NO_OPTION);
                if (choice == 0){
                    System.out.println("Collecting P" + currMachine.denomination.getTotalMoney() +
                            " from the machine...");
                    currMachine.vmSetMoney(new Denomination());
                    this.frame.maintenanceMenu.getMoneyDisplay().updateMoneyDisplay(0);
                }
            }
            else JOptionPane.showMessageDialog(null,"Unable to collect money." +
                    " Vending machine balance is 0.");
        });

        this.frame.maintenanceMenu.getEditPriceBtn().addActionListener(e -> {
            this.frame.maintenanceMenu.resetButtonPanelBtns();
            this.frame.maintenanceMenu.updateButtonPanel(4);
        });

        ActionListener a = e -> {
            Object src = e.getSource();

            double newPrice;

            if(src == this.frame.maintenanceMenu.getEditPriceVMButtons(0)){       //1
                newPrice = currMachine.editPriceInput(0);
                if (this.frame.maintenanceMenu.getCurrMode().equals("Special")){
                    SpecialVM specialVM = (SpecialVM) currMachine;
                     specialVM.setNewSpecialPrice(0, (float) newPrice);
                    this.frame.maintenanceMenu.getSpecialItemPanel().updateItemPrice(0,newPrice);
                    this.frame.vmInterface.getSpecialItemPanel().updateItemPrice(0,newPrice);
                }
                else {
                    this.frame.maintenanceMenu.getRegItemPanel().updateItemPrice(0, newPrice);
                    this.frame.vmInterface.getRegItemPanel().updateItemPrice(0,newPrice);
                    currMachine.productSlots.get(0).editItemPrice((float) newPrice);
                }

            }

            else if(src == this.frame.maintenanceMenu.getEditPriceVMButtons(1)){       //2
                newPrice = currMachine.editPriceInput(1);
                if (this.frame.maintenanceMenu.getCurrMode().equals("Special")){
                    SpecialVM specialVM = (SpecialVM) currMachine;
                    specialVM.setNewSpecialPrice(1, (float) newPrice);
                    this.frame.maintenanceMenu.getSpecialItemPanel().updateItemPrice(1,newPrice);
                    this.frame.vmInterface.getSpecialItemPanel().updateItemPrice(1,newPrice);
                }
                else {
                    this.frame.maintenanceMenu.getRegItemPanel().updateItemPrice(1, newPrice);
                    this.frame.vmInterface.getRegItemPanel().updateItemPrice(1,newPrice);
                    currMachine.productSlots.get(1).editItemPrice((float) newPrice);
                }
            }

            else if(src == this.frame.maintenanceMenu.getEditPriceVMButtons(2)){       //3
                newPrice = currMachine.editPriceInput(2);
                if (this.frame.maintenanceMenu.getCurrMode().equals("Special")){
                    SpecialVM specialVM = (SpecialVM) currMachine;
                    specialVM.setNewSpecialPrice(2, (float) newPrice);
                    this.frame.maintenanceMenu.getSpecialItemPanel().updateItemPrice(2,newPrice);
                    this.frame.vmInterface.getSpecialItemPanel().updateItemPrice(2,newPrice);
                }
                else {
                    this.frame.maintenanceMenu.getRegItemPanel().updateItemPrice(2, newPrice);
                    this.frame.vmInterface.getRegItemPanel().updateItemPrice(2,newPrice);
                    currMachine.productSlots.get(2).editItemPrice((float) newPrice);
                }
            }

            else if (src == this.frame.maintenanceMenu.getEditPriceVMButtons(3)){       //4
                newPrice = currMachine.editPriceInput(3);
                if (this.frame.maintenanceMenu.getCurrMode().equals("Special")){
                    SpecialVM specialVM = (SpecialVM) currMachine;
                    specialVM.setNewSpecialPrice(3, (float) newPrice);
                    this.frame.maintenanceMenu.getSpecialItemPanel().updateItemPrice(3,newPrice);
                    this.frame.vmInterface.getSpecialItemPanel().updateItemPrice(3,newPrice);
                }
                else {
                    this.frame.maintenanceMenu.getRegItemPanel().updateItemPrice(3, newPrice);
                    this.frame.vmInterface.getRegItemPanel().updateItemPrice(3,newPrice);
                    currMachine.productSlots.get(3).editItemPrice((float) newPrice);
                }
            }

            else if (src == this.frame.maintenanceMenu.getEditPriceVMButtons(4)){       //5
                newPrice = currMachine.editPriceInput(4);
                if (this.frame.maintenanceMenu.getCurrMode().equals("Special")){
                    SpecialVM specialVM = (SpecialVM) currMachine;
                    specialVM.setNewSpecialPrice(4, (float) newPrice);
                    this.frame.maintenanceMenu.getSpecialItemPanel().updateItemPrice(4,newPrice);
                    this.frame.vmInterface.getSpecialItemPanel().updateItemPrice(4,newPrice);
                }
                else {
                    this.frame.maintenanceMenu.getRegItemPanel().updateItemPrice(4, newPrice);
                    this.frame.vmInterface.getRegItemPanel().updateItemPrice(4,newPrice);
                    currMachine.productSlots.get(4).editItemPrice((float) newPrice);
                }
            }

            else if (src == this.frame.maintenanceMenu.getEditPriceVMButtons(5)){       //6
                newPrice = currMachine.editPriceInput(5);
                if (this.frame.maintenanceMenu.getCurrMode().equals("Special")){
                    SpecialVM specialVM = (SpecialVM) currMachine;
                    specialVM.setNewSpecialPrice(5, (float) newPrice);
                    this.frame.maintenanceMenu.getSpecialItemPanel().updateItemPrice(5,newPrice);
                    this.frame.vmInterface.getSpecialItemPanel().updateItemPrice(5,newPrice);
                }
                else {
                    this.frame.maintenanceMenu.getRegItemPanel().updateItemPrice(5, newPrice);
                    this.frame.vmInterface.getRegItemPanel().updateItemPrice(5,newPrice);
                    currMachine.productSlots.get(5).editItemPrice((float) newPrice);
                }
            }

            else if (src == this.frame.maintenanceMenu.getEditPriceVMButtons(6)){       //7
                newPrice = currMachine.editPriceInput(6);
                if (this.frame.maintenanceMenu.getCurrMode().equals("Special")){
                    SpecialVM specialVM = (SpecialVM) currMachine;
                    specialVM.setNewSpecialPrice(6, (float) newPrice);
                    this.frame.maintenanceMenu.getSpecialItemPanel().updateItemPrice(6,newPrice);
                    this.frame.vmInterface.getSpecialItemPanel().updateItemPrice(6,newPrice);
                }
                else {
                    this.frame.maintenanceMenu.getRegItemPanel().updateItemPrice(6, newPrice);
                    this.frame.vmInterface.getRegItemPanel().updateItemPrice(6,newPrice);
                    currMachine.productSlots.get(6).editItemPrice((float) newPrice);
                }
            }

            else if (src == this.frame.maintenanceMenu.getEditPriceVMButtons(7)){       //8
                newPrice = currMachine.editPriceInput(7);
                if (this.frame.maintenanceMenu.getCurrMode().equals("Special")){
                    SpecialVM specialVM = (SpecialVM) currMachine;
                    specialVM.setNewSpecialPrice(7, (float) newPrice);
                    this.frame.maintenanceMenu.getSpecialItemPanel().updateItemPrice(7,newPrice);
                    this.frame.vmInterface.getSpecialItemPanel().updateItemPrice(7,newPrice);
                }
                else {
                    this.frame.maintenanceMenu.getRegItemPanel().updateItemPrice(7, newPrice);
                    this.frame.vmInterface.getRegItemPanel().updateItemPrice(7,newPrice);
                    currMachine.productSlots.get(7).editItemPrice((float) newPrice);
                }
            }

            else if (src == this.frame.maintenanceMenu.getEditPriceVMButtons(8)){      //9
                newPrice = currMachine.editPriceInput(8);
                if (this.frame.maintenanceMenu.getCurrMode().equals("Special")){
                    SpecialVM specialVM = (SpecialVM) currMachine;
                    specialVM.setNewSpecialPrice(8, (float) newPrice);
                    this.frame.maintenanceMenu.getSpecialItemPanel().updateItemPrice(8,newPrice);
                    this.frame.vmInterface.getSpecialItemPanel().updateItemPrice(8,newPrice);
                }
                else {
                    this.frame.maintenanceMenu.getRegItemPanel().updateItemPrice(8, newPrice);
                    this.frame.vmInterface.getRegItemPanel().updateItemPrice(8,newPrice);
                    currMachine.productSlots.get(8).editItemPrice((float) newPrice);
                }
            }

        };
        for (int i = 0; i < 9; i++) {
            this.frame.maintenanceMenu.getEditPriceVMButtons(i).addActionListener(a);
        }



        ActionListener b = r -> {                                                                //RESTOCK RVM
            Object src = r.getSource();
            if (src == this.frame.maintenanceMenu.getRestockVMButtons(0)) {                //vanilla
                currMachine.productSlots.get(0).setProductQuantity(currMachine.restockInput(0));
                currMachine.productSlots.get(0).setProductQuantity(1);

                this.frame.vmInterface.getRegItemPanel().updateImageVis(0,
                        currMachine.productSlots.get(0).getProductQuantity());
                this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(0,
                        currMachine.productSlots.get(0).getProductQuantity());

            } else if (src == this.frame.maintenanceMenu.getRestockVMButtons(1)) {         //choco
                currMachine.productSlots.get(1).setProductQuantity(currMachine.restockInput(1));
                currMachine.productSlots.get(1).setProductQuantity(1);

                this.frame.vmInterface.getRegItemPanel().updateImageVis(1,
                        currMachine.productSlots.get(1).getProductQuantity());
                this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(1,
                        currMachine.productSlots.get(1).getProductQuantity());

            } else if (src == this.frame.maintenanceMenu.getRestockVMButtons(2)) {         //matcha
                currMachine.productSlots.get(2).setProductQuantity(currMachine.restockInput(2));
                currMachine.productSlots.get(2).setProductQuantity(1);

                this.frame.vmInterface.getRegItemPanel().updateImageVis(2,
                        currMachine.productSlots.get(2).getProductQuantity());
                this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(2,
                        currMachine.productSlots.get(2).getProductQuantity());

            } else if (src == this.frame.maintenanceMenu.getRestockVMButtons(3)) {       //choco chips
                currMachine.productSlots.get(3).setProductQuantity(currMachine.restockInput(3));
                currMachine.productSlots.get(3).setProductQuantity(1);

                this.frame.vmInterface.getRegItemPanel().updateImageVis(3,
                        currMachine.productSlots.get(3).getProductQuantity());
                this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(3,
                        currMachine.productSlots.get(3).getProductQuantity());

            } else if (src == this.frame.maintenanceMenu.getRestockVMButtons(4)) {       //cereals
                currMachine.productSlots.get(4).setProductQuantity(currMachine.restockInput(4));
                currMachine.productSlots.get(4).setProductQuantity(1);

                this.frame.vmInterface.getRegItemPanel().updateImageVis(4,
                        currMachine.productSlots.get(4).getProductQuantity());
                this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(4,
                        currMachine.productSlots.get(4).getProductQuantity());

            } else if (src == this.frame.maintenanceMenu.getRestockVMButtons(5)) {       //mixed fruit bits
                currMachine.productSlots.get(5).setProductQuantity(currMachine.restockInput(5));
                currMachine.productSlots.get(5).setProductQuantity(1);

                this.frame.vmInterface.getRegItemPanel().updateImageVis(5,
                        currMachine.productSlots.get(5).getProductQuantity());
                this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(5,
                        currMachine.productSlots.get(5).getProductQuantity());

            } else if (src == this.frame.maintenanceMenu.getRestockVMButtons(6)) {       //raspberry
                currMachine.productSlots.get(6).setProductQuantity(currMachine.restockInput(6));
                currMachine.productSlots.get(6).setProductQuantity(1);

                this.frame.vmInterface.getRegItemPanel().updateImageVis(6,
                        currMachine.productSlots.get(6).getProductQuantity());
                this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(6,
                        currMachine.productSlots.get(6).getProductQuantity());

            } else if (src == this.frame.maintenanceMenu.getRestockVMButtons(7)) {       //strawberry
                currMachine.productSlots.get(7).setProductQuantity(currMachine.restockInput(7));
                currMachine.productSlots.get(7).setProductQuantity(1);


                this.frame.vmInterface.getRegItemPanel().updateImageVis(7,
                        currMachine.productSlots.get(7).getProductQuantity());
                this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(7,
                        currMachine.productSlots.get(7).getProductQuantity());

            } else if (src == this.frame.maintenanceMenu.getRestockVMButtons(8)) {       //mango
                currMachine.productSlots.get(8).setProductQuantity(currMachine.restockInput(8));
                currMachine.productSlots.get(8).setProductQuantity(1);

                this.frame.vmInterface.getRegItemPanel().updateImageVis(8,
                        currMachine.productSlots.get(8).getProductQuantity());
                this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(8,
                        currMachine.productSlots.get(8).getProductQuantity());

            }
            this.frame.maintenanceMenu.getMoneyDisplay().
                    updateMoneyDisplay(currMachine.getTotalMoney());
            SpecialVM specialVM = (SpecialVM) currMachine;

            this.frame.vmInterface.getSpecialItemPanel().updateImageVis(0,
                    specialVM.customProductChecker(0));
            this.frame.maintenanceMenu.getSpecialItemPanel().updateImageVis(0,
                    specialVM.customProductChecker(0));

            this.frame.vmInterface.getSpecialItemPanel().updateImageVis(1,
                    specialVM.customProductChecker(1));
            this.frame.maintenanceMenu.getSpecialItemPanel().updateImageVis(1,
                    specialVM.customProductChecker(1));

            this.frame.vmInterface.getSpecialItemPanel().updateImageVis(2,
                    specialVM.customProductChecker(2));
            this.frame.maintenanceMenu.getSpecialItemPanel().updateImageVis(2,
                    specialVM.customProductChecker(2));

            this.frame.vmInterface.getSpecialItemPanel().updateImageVis(3,
                    specialVM.customProductChecker(3));
            this.frame.maintenanceMenu.getSpecialItemPanel().updateImageVis(3,
                    specialVM.customProductChecker(3));

            this.frame.vmInterface.getSpecialItemPanel().updateImageVis(4,
                    specialVM.customProductChecker(4));
            this.frame.maintenanceMenu.getSpecialItemPanel().updateImageVis(4,
                    specialVM.customProductChecker(4));

            this.frame.vmInterface.getSpecialItemPanel().updateImageVis(5,
                    specialVM.customProductChecker(5));
            this.frame.maintenanceMenu.getSpecialItemPanel().updateImageVis(5,
                    specialVM.customProductChecker(5));

            this.frame.vmInterface.getSpecialItemPanel().updateImageVis(6,
                    specialVM.customProductChecker(6));

            this.frame.maintenanceMenu.getSpecialItemPanel().updateImageVis(6,
                    specialVM.customProductChecker(6));

            this.frame.vmInterface.getSpecialItemPanel().updateImageVis(7,
                    specialVM.customProductChecker(7));
            this.frame.maintenanceMenu.getSpecialItemPanel().updateImageVis(7,
                    specialVM.customProductChecker(7));
        };
        for (int i = 0; i < 9; i++) {
            this.frame.maintenanceMenu.getRestockVMButtons(i).addActionListener(b);
        }

    }


    /**
     * Structure for the listeners of buttons for vending machine simulation
     */
    private void initializeSimulation(){

        ActionListener a = e -> {
            Object src = e.getSource();

            if(currMachine.getTotalInsertedMoney()>0){
                if(src ==  this.frame.vmInterface.getVMButtons(0)){             //trichoco
                    if (this.frame.vmInterface.getCurrMode().equals("Special")){
                        currMachine.dispenseProduct(0,2);
                        this.frame.vmInterface.getMoneyDisplay().
                                updateMoneyDisplay(currMachine.getTotalInsertedMoney());
                    }
                    else if(this.currMachine.dispenseProduct(0,1)) {
                        this.frame.vmInterface.getMoneyDisplay().
                                updateMoneyDisplay(currMachine.getTotalInsertedMoney());
                    }

                    this.frame.vmInterface.getRegItemPanel().updateImageVis(0,
                            currMachine.productSlots.get(0).getProductQuantity());
                    this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(0,
                            currMachine.productSlots.get(0).getProductQuantity());
                }
                else if(src == this.frame.vmInterface.getVMButtons(1)){         //vitamax
                    if (this.frame.vmInterface.getCurrMode().equals("Special")){


                        currMachine.dispenseProduct(1,2);
                        this.frame.vmInterface.getMoneyDisplay().
                                updateMoneyDisplay(currMachine.getTotalInsertedMoney());
                    }
                    else if(this.currMachine.dispenseProduct(1,1)){
                        this.frame.vmInterface.getMoneyDisplay().
                                updateMoneyDisplay(currMachine.getTotalInsertedMoney());
                    }

                    this.frame.vmInterface.getRegItemPanel().updateImageVis(1,
                            currMachine.productSlots.get(1).getProductQuantity());
                    this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(1,
                            currMachine.productSlots.get(1).getProductQuantity());
                }
                else if(src == this.frame.vmInterface.getVMButtons(2)){         //strawb duo
                    if (this.frame.vmInterface.getCurrMode().equals("Special")){


                        currMachine.dispenseProduct(2,2);
                        this.frame.vmInterface.getMoneyDisplay().
                                updateMoneyDisplay(currMachine.getTotalInsertedMoney());
                    }
                    else if(this.currMachine.dispenseProduct(2,1)) {
                        this.frame.vmInterface.getMoneyDisplay().
                                updateMoneyDisplay(currMachine.getTotalInsertedMoney());
                    }

                    this.frame.vmInterface.getRegItemPanel().updateImageVis(2,
                            currMachine.productSlots.get(2).getProductQuantity());
                    this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(2,
                            currMachine.productSlots.get(2).getProductQuantity());
                }
                else if (src == this.frame.vmInterface.getVMButtons(3)) {       //choco duo
                    if (this.frame.vmInterface.getCurrMode().equals("Special")){


                        currMachine.dispenseProduct(3,2);
                        this.frame.vmInterface.getMoneyDisplay().
                                updateMoneyDisplay(currMachine.getTotalInsertedMoney());
                    }
                    else if(this.currMachine.dispenseProduct(3,1)) {
                        this.frame.vmInterface.getMoneyDisplay().
                                updateMoneyDisplay(currMachine.getTotalInsertedMoney());
                    }

                    this.frame.vmInterface.getRegItemPanel().updateImageVis(3,
                            currMachine.productSlots.get(3).getProductQuantity());
                    this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(3,
                            currMachine.productSlots.get(3).getProductQuantity());
                }
                else if (src == this.frame.vmInterface.getVMButtons(4)) {       //chocomatcha
                    if (this.frame.vmInterface.getCurrMode().equals("Special")){


                        currMachine.dispenseProduct(4,2);
                        this.frame.vmInterface.getMoneyDisplay().
                                updateMoneyDisplay(currMachine.getTotalInsertedMoney());
                    }
                    else if(this.currMachine.dispenseProduct(4,1)) {
                        this.frame.vmInterface.getMoneyDisplay().
                                updateMoneyDisplay(currMachine.getTotalInsertedMoney());
                    }

                    this.frame.vmInterface.getRegItemPanel().updateImageVis(4,
                            currMachine.productSlots.get(4).getProductQuantity());
                    this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(4,
                            currMachine.productSlots.get(4).getProductQuantity());

                }
                else if (src == this.frame.vmInterface.getVMButtons(5)) {       //mango choco
                    if (this.frame.vmInterface.getCurrMode().equals("Special")){


                        currMachine.dispenseProduct(5,2);
                        this.frame.vmInterface.getMoneyDisplay().
                                updateMoneyDisplay(currMachine.getTotalInsertedMoney());
                    }
                    else if(this.currMachine.dispenseProduct(5,1)) {
                        this.frame.vmInterface.getMoneyDisplay().
                                updateMoneyDisplay(currMachine.getTotalInsertedMoney());
                    }

                    this.frame.vmInterface.getRegItemPanel().updateImageVis(5,
                            currMachine.productSlots.get(5).getProductQuantity());
                    this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(5,
                            currMachine.productSlots.get(5).getProductQuantity());
                }
                else if (src == this.frame.vmInterface.getVMButtons(6)) {       //berrylicious
                    if (this.frame.vmInterface.getCurrMode().equals("Special")){


                        currMachine.dispenseProduct(6,2);
                        this.frame.vmInterface.getMoneyDisplay().
                                updateMoneyDisplay(currMachine.getTotalInsertedMoney());
                    }
                    else if(this.currMachine.dispenseProduct(6,1)) {
                        this.frame.vmInterface.getMoneyDisplay().
                                updateMoneyDisplay(currMachine.getTotalInsertedMoney());
                    }

                    this.frame.vmInterface.getRegItemPanel().updateImageVis(6,
                            currMachine.productSlots.get(6).getProductQuantity());
                    this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(6,
                            currMachine.productSlots.get(6).getProductQuantity());
                }
                else if (src == this.frame.vmInterface.getVMButtons(7)) {       //rainbow
                    if (this.frame.vmInterface.getCurrMode().equals("Special")){
                        currMachine.dispenseProduct(7,2);
                        this.frame.vmInterface.getMoneyDisplay().
                                updateMoneyDisplay(currMachine.getTotalInsertedMoney());
                    }
                    else  if(this.currMachine.dispenseProduct(7,1)) {
                        this.frame.vmInterface.getMoneyDisplay().
                                updateMoneyDisplay(currMachine.getTotalInsertedMoney());
                    }

                    this.frame.vmInterface.getRegItemPanel().updateImageVis(7,
                            currMachine.productSlots.get(7).getProductQuantity());
                    this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(7,
                            currMachine.productSlots.get(7).getProductQuantity());
                }
                else if (src == this.frame.vmInterface.getVMButtons(8)) {
                    if (this.frame.vmInterface.getCurrMode().equals("Special")){
                        if(isthereCombination()) {
                            this.frame.vmInterface.yougArtFrame(currMachine.productSlots);
                        }

                        this.frame.vmInterface.getMoneyDisplay().
                                updateMoneyDisplay(currMachine.getTotalInsertedMoney());
                    }
                    else if(this.currMachine.dispenseProduct(8,1))
                        this.frame.vmInterface.getMoneyDisplay().
                                updateMoneyDisplay(currMachine.getTotalInsertedMoney());

                    this.frame.vmInterface.getRegItemPanel().updateImageVis(8,
                            currMachine.productSlots.get(8).getProductQuantity());
                    this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(8,
                            currMachine.productSlots.get(8).getProductQuantity());
                }

                this.frame.maintenanceMenu.getMoneyDisplay().
                        updateMoneyDisplay(currMachine.getTotalMoney());


                this.frame.vmInterface.getSpecialItemPanel().updateImageVis(0,
                        currMachine.customProductChecker(0));
                this.frame.maintenanceMenu.getSpecialItemPanel().updateImageVis(0,
                        currMachine.customProductChecker(0));

                this.frame.vmInterface.getSpecialItemPanel().updateImageVis(1,
                        currMachine.customProductChecker(1));
                this.frame.maintenanceMenu.getSpecialItemPanel().updateImageVis(1,
                        currMachine.customProductChecker(1));

                this.frame.vmInterface.getSpecialItemPanel().updateImageVis(2,
                        currMachine.customProductChecker(2));
                this.frame.maintenanceMenu.getSpecialItemPanel().updateImageVis(2,
                        currMachine.customProductChecker(2));

                this.frame.vmInterface.getSpecialItemPanel().updateImageVis(3,
                        currMachine.customProductChecker(3));
                this.frame.maintenanceMenu.getSpecialItemPanel().updateImageVis(3,
                        currMachine.customProductChecker(3));

                this.frame.vmInterface.getSpecialItemPanel().updateImageVis(4,
                        currMachine.customProductChecker(4));
                this.frame.maintenanceMenu.getSpecialItemPanel().updateImageVis(4,
                        currMachine.customProductChecker(4));

                this.frame.vmInterface.getSpecialItemPanel().updateImageVis(5,
                        currMachine.customProductChecker(5));
                this.frame.maintenanceMenu.getSpecialItemPanel().updateImageVis(5,
                        currMachine.customProductChecker(5));

                this.frame.vmInterface.getSpecialItemPanel().updateImageVis(6,
                        currMachine.customProductChecker(6));

                this.frame.maintenanceMenu.getSpecialItemPanel().updateImageVis(6,
                        currMachine.customProductChecker(6));

                this.frame.vmInterface.getSpecialItemPanel().updateImageVis(7,
                        currMachine.customProductChecker(7));
                this.frame.maintenanceMenu.getSpecialItemPanel().updateImageVis(7,
                        currMachine.customProductChecker(7));

            }
        };

        ActionListener b = select -> {
            Object subSrc = select.getSource();
            if(this.frame.vmInterface.getSelectCustom()==subSrc){
                String[] selectedProducts ={(String)this.frame.vmInterface.
                        getBaseDropdown().getSelectedItem(),
                        (String)this.frame.vmInterface.getSauceDropdown().getSelectedItem(),
                        (String) this.frame.vmInterface.getToppingDropdown().getSelectedItem()
                };
                // look for the indices
                int[] indices = new int[3];
                if(indexFinder(selectedProducts[0])){
                    indices[0] = foundIndex;
                    if (indexFinder(selectedProducts[1])){
                        indices[1] = foundIndex;
                        if (indexFinder(selectedProducts[2]))
                            indices[2] = foundIndex;
                    }
                }
                SpecialVM specialVM = (SpecialVM) currMachine;

                specialVM.youGartTransaction(this.currMachine.productSlots.get(indices[0]),
                        this.currMachine.productSlots.get(indices[1]),
                        this.currMachine.productSlots.get(indices[2]));

                this.currMachine.productSlots.get(indices[0]).getProducts().remove(0);
                this.currMachine.productSlots.get(indices[1]).getProducts().remove(0);
                this.currMachine.productSlots.get(indices[2]).getProducts().remove(0);
                this.frame.vmInterface.getDialog().dispose();
            }

        };
        for(int i = 0; i< 9; i++){
            this.frame.vmInterface.getVMButtons(i).addActionListener(a);
        }
        this.frame.vmInterface.getSelectCustom().addActionListener(b);
    }


    /**
     * updates the curr machine type.
     * @param type String relating to what type of machine
     */
    private void updateMachineType(String type){
        machineType = type;
    }

    /**
     * Finds index of an item given the name.
     * @param name name of an item/
     * @return returns true if found.
     */
    private boolean indexFinder(String name){
        int index = 0;

        for (Slot slot : this.currMachine.productSlots){
            if(slot.getBaseProductName().equalsIgnoreCase(name)){
                this.foundIndex=index;
                return true;
            }
            index++;
        }
        return false;
    }

    /**
     * Checks if there atleast one combination of items for custom
     * @return boolean value to know if the process should take place.
     */
    private boolean isthereCombination(){
        boolean base =false;
        boolean sauce = false;
        boolean toppings = false;

        for (int i=0; i<13;i++){
            //check bases
            if(!(currMachine.productSlots.get(i).getProducts().isEmpty()) && i<3|| (i>5&&i<9)){
                base = true;
            }
            //check toppings
            else if(!(currMachine.productSlots.get(i).getProducts().isEmpty()) && ((i>2 && i<6)||i==12)){
                sauce = true;
            }
            //check sauces
            else if (!(currMachine.productSlots.get(i).getProducts().isEmpty())){
                toppings = true;
            }
        }
        if(base && sauce && toppings) return true;

        return false;
    }



}