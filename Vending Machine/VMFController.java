import javax.swing.*;
import java.awt.event.ActionListener;


public class VMFController {

    private VendingMachine currMachine;
    private String machineType;
    private final MainFrame frame;

    VMFController(MainFrame frame){
        this.frame = frame;

        //Action Listeners

        ActionListener backToMainListener = e -> this.frame.menu.show(this.frame.cardPanel, "mainMenu");
        //Back Buttons -------------------------------------------------
        this.frame.generatorMenu.getBack().addActionListener(backToMainListener);
        this.frame.testMenu.getBackButton().addActionListener(backToMainListener);

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
            }catch (Exception error){
                JOptionPane.showMessageDialog(null,"An error " +
                        "occured: " + error.getMessage()+ " Error");
            }
        });

        this.frame.generatorMenu.getExit().addActionListener(e ->{
            this.frame.generatorMenu.terminateMONEYINTERFACE();
            this.frame.menu.show(this.frame.cardPanel, "mainMenu");
            JOptionPane.showMessageDialog(null,
                    "Successfully created "+ machineType +" Vending Machine with initial" +
                            " money: "+ currMachine.getmoney());
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
        });

        this.frame.vmInterface.getInsertMoney().addActionListener(e ->
                this.frame.vmInterface.updateButtonPanel(2));
    }

    public void initializeProgram(){
        frame.menu.show(frame.cardPanel,"mainMenu");
    }

    private void updateMachineType(String type){
        machineType = type;
    }

}