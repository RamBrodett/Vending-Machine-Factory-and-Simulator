import javax.swing.*;
import java.awt.*;

public class VMFMainMenu extends JPanel {
    /**
     * Background image of the MainMenu.
     */
    private ImageIcon bgIMG= new ImageIcon("./VMResources/mainbg.png");

    /**
     * Button for creation of Vending Machine menu.
     */
    private final JButton createVM = new JButton();

    /**
     * Button for testing of Vending Machine menu.
     */
    private final JButton testVM = new JButton();

    /**
     * Button for terminating the program.
     */
    private final JButton exitFactory = new JButton();


    /**
     * Initializations of the MainMenu Components.
     */

    VMFMainMenu(){
        setLayout(new GridLayout(2,1));

        JPanel upperPanel =  new JPanel();
        JPanel lowerPanel = new JPanel();

        upperPanel.setOpaque(false);
        lowerPanel.setOpaque(false);
        lowerPanel.setLayout(new GridLayout(4,1));
        JPanel panelFiller = new JPanel();
        JPanel panelFiller2 = new JPanel();
        JPanel panelFiller3 = new JPanel();
        JPanel panelFiller4 = new JPanel();
        JPanel panelFiller5 = new JPanel();
        JPanel panelFiller6 = new JPanel();
        JPanel panelFiller7 = new JPanel();

        panelFiller.setOpaque(false);
        panelFiller2.setOpaque(false);
        panelFiller3.setOpaque(false);
        panelFiller4.setOpaque(false);
        panelFiller5.setOpaque(false);
        panelFiller6.setOpaque(false);
        panelFiller7.setOpaque(false);

        createVM.setIcon(scaleIMG("./VMResources/crtBTnorm.png",275,99));
        createVM.setRolloverIcon(scaleIMG("./VMResources/crtBThover.png",275,99));
        createVM.setPressedIcon(scaleIMG("./VMResources/crtBTclicked.png",275,99));
        createVM.setBorder(null);
        createVM.setContentAreaFilled(false);
        createVM.setOpaque(false);

        testVM.setIcon(scaleIMG("./VMResources/testBTnorm.png",275,99));
        testVM.setRolloverIcon(scaleIMG("./VMResources/testBThover.png",275,99));
        testVM.setPressedIcon(scaleIMG("./VMResources/testBTclicked.png",275,99));
        testVM.setBorder(null);
        testVM.setContentAreaFilled(false);
        testVM.setOpaque(false);

        exitFactory.setIcon(scaleIMG("./VMResources/exitBTnorm.png",275,99));
        exitFactory.setRolloverIcon(scaleIMG("./VMResources/exitBThover.png",275,99));
        exitFactory.setPressedIcon(scaleIMG("./VMResources/exitBTclicked.png",275,99));
        exitFactory.setBorder(null);
        exitFactory.setContentAreaFilled(false);
        exitFactory.setOpaque(false);

        panelFiller4.add(createVM);
        panelFiller5.add(testVM);
        panelFiller6.add(exitFactory);

        lowerPanel.add(panelFiller);
        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new GridLayout(1,5));
        buttonPanel.add(panelFiller3);
        buttonPanel.add(panelFiller4);
        buttonPanel.add(panelFiller5);
        buttonPanel.add(panelFiller6);
        buttonPanel.add(panelFiller7);
        buttonPanel.setOpaque(false);

        lowerPanel.add(buttonPanel);

        lowerPanel.add(panelFiller2);

        add(upperPanel);
        add(lowerPanel);
    }

    /**
     * The paint method that add graphics for the main panel.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bgIMG.getImage(),0,0,getWidth(),getHeight(),null);
    }

    /**
     * For creating an Icon with custom dimension
     * @param strname file name of the photo
     * @param width  length from left side to right side
     * @param height length from bottom to top
     * @return ImageIcon
     */
    private ImageIcon scaleIMG(String strname, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(strname);
        Image raw = imageIcon.getImage();
        Image scaled = raw.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    /**
     * Gets the button of create VM menu
     * @return button for create VM menu
     */
    public JButton getCreateVMButton() {
        return createVM;
    }

    /**
     * Gets the button of test VM menu
     * @return button for test VM menu
     */
    public JButton getTestVMButton(){
        return testVM;
    }

    /**
     * Gets the button for exit factory
     * @return button for exiting
     */
    public JButton getExitFactoryButton(){
        return exitFactory;
    }

}
