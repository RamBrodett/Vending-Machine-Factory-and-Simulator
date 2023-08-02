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

    private void initializeTestMenu(){
        this.frame.testMenu.getSimulateVMButton().addActionListener(e ->{
            this.frame.mainFrame.setResizable(false);
            this.frame.menu.show(this.frame.cardPanel, "vmSIM");
            System.setOut(new PrintStream(new VMInterface.TextPane(this.frame.vmInterface.getTextPanel())));
        });

        this.frame.testMenu.getMaintenanceVMButton().addActionListener(e -> {
            this.frame.menu.show(this.frame.cardPanel, "VMmaintenance");
            System.setOut(new PrintStream(new VMMaintenanceMenu.TextPane(this.frame.maintenanceMenu.getTextPanel())));
        });

    }

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
                    this.frame.maintenanceMenu.getSpecialItemPanel().updateItemPrice(0,newPrice);
                }
                else
                    this.frame.maintenanceMenu.getRegItemPanel().updateItemPrice(0,newPrice);

            }

            else if(src == this.frame.maintenanceMenu.getEditPriceVMButtons(1)){       //2
                newPrice = currMachine.editPriceInput(1);
                if (this.frame.maintenanceMenu.getCurrMode().equals("Special")){
                    this.frame.maintenanceMenu.getSpecialItemPanel().updateItemPrice(1,newPrice);
                }
                else
                    this.frame.maintenanceMenu.getRegItemPanel().updateItemPrice(1,newPrice);
            }

            else if(src == this.frame.maintenanceMenu.getEditPriceVMButtons(2)){       //3
                newPrice = currMachine.editPriceInput(2);
                if (this.frame.maintenanceMenu.getCurrMode().equals("Special")){
                    this.frame.maintenanceMenu.getSpecialItemPanel().updateItemPrice(2,newPrice);
                }
                else
                    this.frame.maintenanceMenu.getRegItemPanel().updateItemPrice(2,newPrice);
            }

            else if (src == this.frame.maintenanceMenu.getEditPriceVMButtons(3)){       //4
                newPrice = currMachine.editPriceInput(3);
                if (this.frame.maintenanceMenu.getCurrMode().equals("Special")){
                    this.frame.maintenanceMenu.getSpecialItemPanel().updateItemPrice(3,newPrice);
                }
                else
                    this.frame.maintenanceMenu.getRegItemPanel().updateItemPrice(3,newPrice);
            }

            else if (src == this.frame.maintenanceMenu.getEditPriceVMButtons(4)){       //5
                newPrice = currMachine.editPriceInput(4);
                if (this.frame.maintenanceMenu.getCurrMode().equals("Special")){
                    this.frame.maintenanceMenu.getSpecialItemPanel().updateItemPrice(4,newPrice);
                }
                else
                    this.frame.maintenanceMenu.getRegItemPanel().updateItemPrice(4,newPrice);
            }

            else if (src == this.frame.maintenanceMenu.getEditPriceVMButtons(5)){       //6
                newPrice = currMachine.editPriceInput(5);
                if (this.frame.maintenanceMenu.getCurrMode().equals("Special")){
                    this.frame.maintenanceMenu.getSpecialItemPanel().updateItemPrice(5,newPrice);
                }
                else
                    this.frame.maintenanceMenu.getRegItemPanel().updateItemPrice(5,newPrice);
            }

            else if (src == this.frame.maintenanceMenu.getEditPriceVMButtons(6)){       //7
                newPrice = currMachine.editPriceInput(6);
                if (this.frame.maintenanceMenu.getCurrMode().equals("Special")){
                    this.frame.maintenanceMenu.getSpecialItemPanel().updateItemPrice(6,newPrice);
                }
                else
                    this.frame.maintenanceMenu.getRegItemPanel().updateItemPrice(6,newPrice);
            }

            else if (src == this.frame.maintenanceMenu.getEditPriceVMButtons(7)){       //8
                newPrice = currMachine.editPriceInput(7);
                if (this.frame.maintenanceMenu.getCurrMode().equals("Special")){
                    this.frame.maintenanceMenu.getSpecialItemPanel().updateItemPrice(7,newPrice);
                }
                else
                    this.frame.maintenanceMenu.getRegItemPanel().updateItemPrice(7,newPrice);
            }

            else if (src == this.frame.maintenanceMenu.getEditPriceVMButtons(8)){      //9
                newPrice = currMachine.editPriceInput(8);
                if (this.frame.maintenanceMenu.getCurrMode().equals("Special")){
                    this.frame.maintenanceMenu.getSpecialItemPanel().updateItemPrice(8,newPrice);
                }
                else
                    this.frame.maintenanceMenu.getRegItemPanel().updateItemPrice(8,newPrice);
            }

        };
        for (int i = 0; i < 9; i++) {
            this.frame.maintenanceMenu.getEditPriceVMButtons(i).addActionListener(a);
        }



        ActionListener b = r -> {                                                                //RESTOCK RVM
            Object src = r.getSource();
            if (src == this.frame.maintenanceMenu.getRestockVMButtons(0)) {                //vanilla
                currMachine.productSlots.get(0).setProductQuantity(currMachine.restockInput(0));

                this.frame.vmInterface.getRegItemPanel().updateImageVis(0,
                        currMachine.productSlots.get(0).getProductQuantity());
                this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(0,
                        currMachine.productSlots.get(0).getProductQuantity());

            } else if (src == this.frame.maintenanceMenu.getRestockVMButtons(1)) {         //choco
                currMachine.productSlots.get(1).setProductQuantity(currMachine.restockInput(1));
                this.frame.vmInterface.getRegItemPanel().updateImageVis(1,
                        currMachine.productSlots.get(1).getProductQuantity());
                this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(1,
                        currMachine.productSlots.get(1).getProductQuantity());

            } else if (src == this.frame.maintenanceMenu.getRestockVMButtons(2)) {         //matcha
                currMachine.productSlots.get(2).setProductQuantity(currMachine.restockInput(2));
                this.frame.vmInterface.getRegItemPanel().updateImageVis(2,
                        currMachine.productSlots.get(2).getProductQuantity());
                this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(2,
                        currMachine.productSlots.get(2).getProductQuantity());

            } else if (src == this.frame.maintenanceMenu.getRestockVMButtons(3)) {       //choco chips
                currMachine.productSlots.get(3).setProductQuantity(currMachine.restockInput(3));
                this.frame.vmInterface.getRegItemPanel().updateImageVis(3,
                        currMachine.productSlots.get(3).getProductQuantity());
                this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(3,
                        currMachine.productSlots.get(3).getProductQuantity());

            } else if (src == this.frame.maintenanceMenu.getRestockVMButtons(4)) {       //cereals
                currMachine.productSlots.get(4).setProductQuantity(currMachine.restockInput(4));
                this.frame.vmInterface.getRegItemPanel().updateImageVis(4,
                        currMachine.productSlots.get(4).getProductQuantity());
                this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(4,
                        currMachine.productSlots.get(4).getProductQuantity());

            } else if (src == this.frame.maintenanceMenu.getRestockVMButtons(5)) {       //mixed fruit bits
                currMachine.productSlots.get(5).setProductQuantity(currMachine.restockInput(5));
                this.frame.vmInterface.getRegItemPanel().updateImageVis(5,
                        currMachine.productSlots.get(5).getProductQuantity());
                this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(5,
                        currMachine.productSlots.get(5).getProductQuantity());

            } else if (src == this.frame.maintenanceMenu.getRestockVMButtons(6)) {       //raspberry
                currMachine.productSlots.get(6).setProductQuantity(currMachine.restockInput(6));
                this.frame.vmInterface.getRegItemPanel().updateImageVis(6,
                        currMachine.productSlots.get(6).getProductQuantity());
                this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(6,
                        currMachine.productSlots.get(6).getProductQuantity());

            } else if (src == this.frame.maintenanceMenu.getRestockVMButtons(7)) {       //strawberry
                currMachine.productSlots.get(7).setProductQuantity(currMachine.restockInput(7));
                this.frame.vmInterface.getRegItemPanel().updateImageVis(7,
                        currMachine.productSlots.get(7).getProductQuantity());
                this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(7,
                        currMachine.productSlots.get(7).getProductQuantity());

            } else if (src == this.frame.maintenanceMenu.getRestockVMButtons(8)) {       //mango
                currMachine.productSlots.get(8).setProductQuantity(currMachine.restockInput(8));
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
        };
        for (int i = 0; i < 9; i++) {
            this.frame.maintenanceMenu.getRestockVMButtons(i).addActionListener(b);
        }

    }

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
        for(int i = 0; i< 9; i++){
            this.frame.vmInterface.getVMButtons(i).addActionListener(a);
        }
    }


    /**
     * updates the curr machine type.
     * @param type String relating to what type of machine
     */
    private void updateMachineType(String type){
        machineType = type;
    }


}