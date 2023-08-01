import javax.swing.*;
import java.awt.*;

public class VMGeneratorMenu extends JPanel {

    /**
     * Holds the created separate frame for getting intial money.
     */
    private JFrame moneyInputFrame;

    /**
     * Background image of the GeneratorMenu.
     */
    private final ImageIcon bgIMG_genMenu = new ImageIcon("./VMResources/genBg.png");

    /**
     * Button for creating regular Vending Machine.
     */
    private final JButton regVM = new JButton();
    /**
     * Button for creating special Vending Machine.
     */
    private final JButton specVM = new JButton();

    /**
     * Button for going back to the  previous Menu.
     */
    private final JButton back = new JButton();

    /**
     * Button for exiting the separate frame for money input.
     */
    JButton exit = new JButton("EXIT");

    /**
     * Button for setting the money input.
     */
    JButton set = new JButton("SET DENOMINATION");

    /**
     * Spinning buttons for quantities of different denomination input.
     */
    private JSpinner[] denominationButtons;

    /**
     * Initializations of the GeneratorMenu Components.
     */
    VMGeneratorMenu(){
        setLayout(new GridLayout(3,1));
        JPanel upperPanel = new JPanel();
        JPanel lowerPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        lowerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        upperPanel.setOpaque(false);
        centerPanel.setOpaque(false);
        lowerPanel.setOpaque(false);

        regVM.setIcon(scaleIMG("./VMResources/rvmBTnorm.png",391,91));
        regVM.setRolloverIcon(scaleIMG("./VMResources/rvmBThover.png",391,91));
        regVM.setPressedIcon(scaleIMG("./VMResources/rvmBTclicked.png",391,91));
        regVM.setBorder(null);
        regVM.setContentAreaFilled(false);
        regVM.setOpaque(false);

        specVM.setIcon(scaleIMG("./VMResources/svmBTnorm.png",391,91));
        specVM.setRolloverIcon(scaleIMG("./VMResources/svmBThover.png",391,91));
        specVM.setPressedIcon(scaleIMG("./VMResources/svmBTclicked.png",391,91));
        specVM.setBorder(null);
        specVM.setContentAreaFilled(false);
        specVM.setOpaque(false);

        back.setIcon(scaleIMG("./VMResources/backBTnorm.png",391,91));
        back.setRolloverIcon(scaleIMG("./VMResources/backBThover.png",391,91));
        back.setPressedIcon(scaleIMG("./VMResources/backBTclicked.png",391,91));
        back.setBorder(null);
        back.setContentAreaFilled(false);
        back.setOpaque(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = .54;
        gbc.weighty = 0;
        lowerPanel.add(back,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = .13;
        gbc.weighty = 0;

        lowerPanel.add(regVM,gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = .33;
        gbc.weighty = 0;

        lowerPanel.add(specVM,gbc);


        add(upperPanel);
        add(centerPanel);
        add(lowerPanel);

    }

    /**
     * Initialization of the separate window Money interface
     */
    public void moneyInterface(){
        back.setEnabled(false);
        regVM.setEnabled(false);
        specVM.setEnabled(false);
        moneyInputFrame.setSize(new Dimension(800,600));
        moneyInputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        moneyInputFrame.setLayout(new BorderLayout());
        moneyInputFrame.setLocation(450,50);
        moneyInputFrame.setAlwaysOnTop(true);

        JPanel namingPanel = new JPanel(new FlowLayout());
        JPanel moneyPanel = new JPanel(new GridLayout(5,2));
        JPanel buttonpanel = new JPanel(new GridLayout(1,2));
        JPanel[] moneyTags = new JPanel[10];
        denominationButtons = new JSpinner[10];

        JLabel label = new JLabel("Denomination Setter");
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        namingPanel.add(label);

        for(int i=0; i<10; i++){
            SpinnerNumberModel moneyModel = new SpinnerNumberModel();
            moneyModel.setMinimum(0);
            moneyModel.setValue(0);
            denominationButtons[i] = new JSpinner(moneyModel);
            denominationButtons[i].setPreferredSize(new Dimension(140,70));
            ((JSpinner.DefaultEditor) denominationButtons[i].getEditor()).getTextField().setEditable(false);
            moneyTags[i] = new JPanel();
            JLabel valueHolder = new JLabel();
            valueHolder.setFont(new Font("consolas",Font.BOLD,21));
            moneyTags[i].setLayout(new FlowLayout());
            moneyTags[i].add(denominationButtons[i]);
            switch (i){
                case 0 -> valueHolder.setText("PHP 1 coin");
                case 1 -> valueHolder.setText("PHP 5 coin");
                case 2 -> valueHolder.setText("PHP 10 coin");
                case 3 -> valueHolder.setText("PHP 20 coin");
                case 4 -> valueHolder.setText("PHP 20 bill");
                case 5 -> valueHolder.setText("PHP 50 bill");
                case 6 -> valueHolder.setText("PHP 100 bill");
                case 7 -> valueHolder.setText("PHP 200 bill");
                case 8 -> valueHolder.setText("PHP 500 bill");
                case 9 -> valueHolder.setText("PHP 1000 bill");
            }
            moneyTags[i].add(valueHolder);
            moneyPanel.add(moneyTags[i]);
        }

        buttonpanel.add(exit);
        buttonpanel.add(set);


        moneyInputFrame.add(namingPanel,BorderLayout.NORTH);
        moneyInputFrame.add(moneyPanel,BorderLayout.CENTER);
        moneyInputFrame.add(buttonpanel,BorderLayout.SOUTH);
        moneyInputFrame.setUndecorated(true);
        moneyInputFrame.setVisible(true);

    }

    /**
     * Process for terminating the separate money interfacce window
     * <p>
     *     Resets specific button to its intial state, and resets
     *     the spinners back to zero for next use. Lastly, disposes
     *     the resources used in the creation of that frame.
     * </p>
     */
    public void terminateMONEYINTERFACE(){
        for (JSpinner spinner : denominationButtons) {
            spinner.setValue(0); // Reset each spinner to zero
        }
        moneyInputFrame.setVisible(false);
        moneyInputFrame.dispose();
        back.setEnabled(true);
        regVM.setEnabled(true);
        specVM.setEnabled(true);
    }

    /**
     * The paint method that add graphics for the main panel.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bgIMG_genMenu.getImage(),0,0,getWidth(),getHeight(),null);
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
     * Gets the button for creating regular Vending Machine.
     * @return  button for creating regular Vending Machine.
     */
    public JButton getRegVM(){
        return regVM;
    }

    /**
     * Gets the button for creating special Vending Machine.
     * @return  button for creating special Vending Machine.
     */
    public JButton getSpecVM(){
        return specVM;
    }

    /**
     * Gets the button for going back to previous menu.
     * @return  button for going back to previous menu.
     */
    public JButton getBack(){
        return back;
    }

    /**
     * Gets the value of the indexed spinner button.
     * @return  value of the indexed spinner button.
     */
    public int getValue(int index){
        return (int) denominationButtons[index].getValue();
    }

    /**
     * Gets the button for setting the money input.
     * @return  button for setting the money input.
     */
    public JButton getSet(){
        return set;
    }

    /**
     * Gets the button for exiting the money input.
     * @return  button for exiting the money input.
     */
    public JButton getExit(){
        return exit;
    }

    /**
     * sets the frame for money input.
     */
    public void setMoneyInputFrame(JFrame moneyInputFrame){
        this.moneyInputFrame = moneyInputFrame;
    }
}