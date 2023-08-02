import javax.swing.*;
import java.awt.*;

/**
 * Panel for test menu, contains necessary ui initialization for testing.
 */
public class VMFTestMenu extends JPanel {

    /**
     * Background image of the TestMenu.
     */
    private final ImageIcon bgIMG_testMenu = new ImageIcon("./VMResources/testbg.png");

    /**
     *  Holds the image of vending machine currently selected (can be [Regular || Special]).
     */
    private final JLabel vmImage = new JLabel();

    /**
     * Button for simulation of Vending Machine.
     */
    private final JButton simVM = new JButton();

    /**
     * Button for creation of Vending Machine.
     */
    private final JButton maintenanceVM = new JButton();

    /**
     * Button for going back to previous menu.
     */
    private final JButton back = new JButton();

    /**
     * Initializations of the TestMenu Components.
     */
    VMFTestMenu(){
        setLayout(new GridLayout(1,2));
        JPanel lPanel = new JPanel(new GridBagLayout());
        JPanel rPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        ImageIcon vmLogo = scaleIMG("./VMResources/mmLogo.png", 250, 250);
        JLabel vmLabel = new JLabel(vmLogo);

        lPanel.setOpaque(false);
        rPanel.setOpaque(false);

        simVM.setIcon(scaleIMG("./VMResources/simvmBTnorm.png",391,91));
        simVM.setRolloverIcon(scaleIMG("./VMResources/simvmBThover.png",391,91));
        simVM.setPressedIcon(scaleIMG("./VMResources/simvmBTclicked.png.png",391,91));
        simVM.setBorder(null);
        simVM.setContentAreaFilled(false);
        simVM.setOpaque(false);

        maintenanceVM.setIcon(scaleIMG("./VMResources/maintenanceBTnorm.png",391,91));
        maintenanceVM.setRolloverIcon(scaleIMG("./VMResources/maintenanceBThover.png",391,91));
        maintenanceVM.setPressedIcon(scaleIMG("./VMResources/maintenanceBTclicked.png",391,91));
        maintenanceVM.setBorder(null);
        maintenanceVM.setContentAreaFilled(false);
        maintenanceVM.setOpaque(false);

        back.setIcon(scaleIMG("./VMResources/backBTnorm.png",391,91));
        back.setRolloverIcon(scaleIMG("./VMResources/backBThover.png",391,91));
        back.setPressedIcon(scaleIMG("./VMResources/backBTclicked.png",391,91));
        back.setBorder(null);
        back.setContentAreaFilled(false);
        back.setOpaque(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0, 0, 50, 0);
        lPanel.add(vmLabel, gbc);

        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        lPanel.add(simVM, gbc);

        gbc.gridy = 2;
        lPanel.add(maintenanceVM, gbc);

        gbc.insets = new Insets(80,0,0,0);
        gbc.gridy = 3;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        lPanel.add(back, gbc);

        JPanel placeHolder = new JPanel();          //TEMP PALCEHOLDER

        placeHolder.add(vmImage);
        placeHolder.setOpaque(false);
        placeHolder.setPreferredSize(new Dimension(500,800));
        rPanel.add(placeHolder);

        add(lPanel);
        add(rPanel);

    }


    /**
     * The paint method that add graphics for the main panel.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g){
        try{
            super.paintComponent(g);
            g.drawImage(bgIMG_testMenu.getImage(),0,0,getWidth(),getHeight(),null);
        } catch (Exception error){
            JOptionPane.showMessageDialog(null,"Background image directory " +
                    "is invalid: " + error.getMessage()+ " Error");
    }
    }

    /**
     * For creating an Icon with custom dimension
     * @param strname file name of the photo
     * @param width  length from left side to right side
     * @param height length from bottom to top
     * @return ImageIcon
     */
    public ImageIcon scaleIMG(String strname, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(strname);
        Image raw = imageIcon.getImage();
        Image scaled = raw.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    /**
     * Gets the button of simulator.
     * @return button of simulator.
     */
    public JButton getSimulateVMButton() {
        return simVM;
    }

    /**
     * Gets the button of maintenance.
     * @return button of maintenance.
     */
    public JButton getMaintenanceVMButton(){
        return maintenanceVM;
    }

    /**
     * Gets the button for going back.
     * @return button for going back.
     */
    public JButton getBackButton(){
        return back;
    }

    /**
     * Gets the Image iconed in a Jlabel.
     * @return Image iconed in a Jlabel.
     */
    public JLabel getVmImage(){
        return vmImage;
    }


}