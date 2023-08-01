import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.util.Set;



public class VMMaintenanceMenu extends JPanel{

    /**
     * Background image of the VendingMachineInterface.
     */
    private ImageIcon bgIMG_VMInterface = new ImageIcon("./VMResources/VMInterfaceBG.png");

    /**
     * Panel that contains Regular Items and its descriptions
     */
    private RegularItemPanel regItemPanel = new RegularItemPanel();

    /**
     * Panel that contains Special Items and its descriptions
     */
    private SpecialItemPanel specialItemPanel = new SpecialItemPanel();


    private JPanel btnSelectPanel = new JPanel();

    /**
     * Layout Manager Panel
     */
    private CardLayout itemPanel;

    /**
     * Panel for product contatiner
     */
    private JPanel itemPanelContainer;

    /**
     *  Holder for what vending Machine type is currently loaded
     */
    private String currMode = "Regular";            //TRACKER FOR WHICH MODE THE LAYOUT IS CURRENTLY ON

    /**
     *  Custom Panel for Button
     */
    private ButtonPanel buttonPanel = new ButtonPanel();

    /**
     *  Custom Panel for text area.
     */
    private TextPanel textPanel = new TextPanel();

    /**
     * Button for restocking products.
     */
    private final JButton restockBtn = new JButton(scaleIMG("./VMResources/raspberry.png",100,100));

    /**
     * Button for Replenishin money.
     */
    private final JButton replenishBtn = new JButton(scaleIMG("./VMResources/strawberry.png",100,100));

    /**
     * Button for Collecting Money
     */
    private final JButton collectBtn = new JButton(scaleIMG("./VMResources/mango.png",100,100));

    /**
     * Button for editing price.
     */
    private final JButton editPriceBtn = new JButton(scaleIMG("./VMResources/vanilla.png",100,100));

    /**
     *  Button for toggling special items display
     *  <p>
     *      Toggle button for displaying special
     *      products. Needs to be a Special Vending Machine
     *      to be available.
     *  </p>
     */
    private JButton toggleSpecial = new JButton();

    /**
     * Button for going back to previous menu.
     */
    private final JButton back = new JButton();;

    /**
     * Initializations of the Vending Machine Maintenance Components.
     */
    VMMaintenanceMenu(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        btnSelectPanel.setLayout(new GridLayout(1,4));
        setupSelectionBtn(restockBtn);
        setupSelectionBtn(replenishBtn);
        setupSelectionBtn(collectBtn);
        setupSelectionBtn(editPriceBtn);
        btnSelectPanel.add(restockBtn);
        btnSelectPanel.add(replenishBtn);
        btnSelectPanel.add(collectBtn);
        btnSelectPanel.add(editPriceBtn);
        btnSelectPanel.setOpaque(false);


        itemPanel = new CardLayout();
        itemPanelContainer = new JPanel((itemPanel));
        itemPanelContainer.setOpaque(false);

        itemPanelContainer.add(regItemPanel, "Regular");
        itemPanelContainer.add(specialItemPanel, "Special");
        itemPanel.show(itemPanelContainer, "Toggle");

        JPanel filler1 = new JPanel();
        filler1.setOpaque(false);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = .01;
        gbc.weighty = 1;
        add(filler1, gbc);

        gbc.fill = GridBagConstraints.BOTH;                              // itemPanel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(85,76, 127,30);
        gbc.gridheight = 4;         // make the itemPanel span all three rows
        gbc.weightx = .81;          // adjust the horizontal size for the itemPanel (0.6 means 60% of available space)
        gbc.weighty = .9;
        add(itemPanelContainer, gbc);


        gbc.insets = new Insets(0,0,570,15);         // textPanel
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.weightx = .18;
        gbc.weighty = .9;
        add(textPanel, gbc);

        btnSelectPanel.setPreferredSize(new Dimension(500,100));
        gbc.insets = new Insets(0, 0, 50, 15);          // InsertMoney button
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.weightx = .18;
        gbc.weighty = .9;
        add(btnSelectPanel, gbc);

        gbc.insets = new Insets(375,0,0,15);         // buttonPanel
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.weightx = .18;
        gbc.weighty = .9;
        add(buttonPanel, gbc);

        back.setIcon(scaleIMG("./VMResources/backBTnorm.png",391,91));
        back.setRolloverIcon(scaleIMG("./VMResources/backBThover.png",391,91));
        back.setPressedIcon(scaleIMG("./VMResources/backBTclicked.png",391,91));
        back.setBorder(null);
        back.setContentAreaFilled(false);
        back.setOpaque(false);
        gbc.insets = new Insets(890, 75, 0,0);         // back button
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.weightx = .18;
        gbc.weighty = .9;
        add(back, gbc);

        toggleSpecial.setIcon(scaleIMG("./VMResources/specialsBTnorm.png", 391, 91));
        toggleSpecial.setRolloverIcon(scaleIMG("./VMResources/specialsBThover.png", 391, 91));
        toggleSpecial.setPressedIcon(scaleIMG("./VMResources/specialsBTclicked.png", 391, 91));
        toggleSpecial.setBorder(null);
        toggleSpecial.setContentAreaFilled(false);
        toggleSpecial.setOpaque(false);
        gbc.insets = new Insets(890,0, 0,25);         // toggle button
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.weightx = .18;
        gbc.weighty = .9;
        add(toggleSpecial, gbc);
    }


    /**
     * The paint method that add graphics for the main panel.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bgIMG_VMInterface.getImage(),0,0,getWidth(),getHeight(),null);
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
     * Re-modifies Selection buttons
     * @param button button for modification
     */
    private void setupSelectionBtn(JButton button){
        button.setOpaque(false);
        button.setBorder(null);
        button.setContentAreaFilled(false);
    }

    /**
     * SubClass for Regular Item Panel GUI Component
     */
    protected class RegularItemPanel extends JPanel{
        ImageIcon vnll = scaleIMG("./VMResources/vanilla.png",150,150);         //NEED VALUES OF ITEMS HERE!!
        ImageIcon chco = scaleIMG("./VMResources/choco.png",150,150);
        ImageIcon mtch = scaleIMG("./VMResources/matcha.png",150,150);
        ImageIcon chcp = scaleIMG("./VMResources/chocochip.png",150,150);
        ImageIcon crel = scaleIMG("./VMResources/cereal.png",150,150);
        ImageIcon mxft = scaleIMG("./VMResources/mixedFruit.png",150,150);
        ImageIcon rasp = scaleIMG("./VMResources/raspberry.png",150,150);
        ImageIcon strw = scaleIMG("./VMResources/strawberry.png",150,150);
        ImageIcon mngo = scaleIMG("./VMResources/mango.png",150,150);

        JLabel vnllLabel = new JLabel(vnll);
        JLabel chcoLabel = new JLabel(chco);
        JLabel mtchLabel = new JLabel(mtch);
        JLabel chcpLabel = new JLabel(chcp);
        JLabel crelLabel = new JLabel(crel);
        JLabel mxftLabel = new JLabel(mxft);
        JLabel raspLabel = new JLabel(rasp);
        JLabel strwLabel = new JLabel(strw);
        JLabel mngoLabel = new JLabel(mngo);

        /**
         * Initialization of Regular panel Items
         */
        RegularItemPanel
                (){
            setOpaque(false);
            // to do: place the items in each spot and place slot num, price, calories and name pala
            vnllLabel.setOpaque(false);
            chcoLabel.setOpaque(false);
            mtchLabel.setOpaque(false);
            chcpLabel.setOpaque(false);
            crelLabel.setOpaque(false);
            mxftLabel.setOpaque(false);
            raspLabel.setOpaque(false);
            strwLabel.setOpaque(false);
            mngoLabel.setOpaque(false);

            JPanel vnllPanel = createImagePanel(vnll, "Vanilla", 230, 90);
            JPanel chcoPanel = createImagePanel(chco, "Chocolate", 260, 90);
            JPanel mtchPanel = createImagePanel(mtch, "Matcha", 999, 999);
            JPanel chcpPanel = createImagePanel(chcp, "Choco Chips", 999, 999);
            JPanel crelPanel = createImagePanel(crel, "Cereals", 999, 999);
            JPanel mxftPanel = createImagePanel(mxft, "Mixed Fruit Bits", 999, 999);
            JPanel raspPanel = createImagePanel(rasp, "Raspberry", 999, 999);
            JPanel strwPanel = createImagePanel(strw, "Strawberry", 999, 999);
            JPanel mngoPanel = createImagePanel(mngo, "Mango", 999, 999);

            setLayout(new GridLayout(3,3));
            add(vnllPanel);
            add(chcoPanel);
            add(mtchPanel);
            add(chcpPanel);
            add(crelPanel);
            add(mxftPanel);
            add(raspPanel);
            add(strwPanel);
            add(mngoPanel);
        }
    }

    /**
     * SubClass for Special Item Panel GUI Component
     */
    protected class SpecialItemPanel extends JPanel{
        ImageIcon triChco = scaleIMG("./VMResources/trichoco.png",150,150);         //NEED VALUES OF ITEMS HERE!!
        ImageIcon vitamax = scaleIMG("./VMResources/vitamax.png",150,150);
        ImageIcon strwDuo = scaleIMG("./VMResources/strwbDuo.png",150,150);
        ImageIcon chcoDuo = scaleIMG("./VMResources/chcoDuo.png",150,150);
        ImageIcon chcoMch = scaleIMG("./VMResources/chocoMatcha.png",150,150);
        ImageIcon mngoChc = scaleIMG("./VMResources/mangoChoco.png",150,150);
        ImageIcon brryCrn = scaleIMG("./VMResources/berrylicious.png",150,150);
        ImageIcon rnbwYgr = scaleIMG("./VMResources/rainbow.png",150,150);
        ImageIcon youGart = scaleIMG("./VMResources/yougart.png",150,150);

        JLabel triChcoLabel = new JLabel(triChco);
        JLabel vitamaxLabel = new JLabel(vitamax);
        JLabel strwDuoLabel = new JLabel(strwDuo);
        JLabel chcoDuoLabel = new JLabel(chcoDuo);
        JLabel chcoMchLabel = new JLabel(chcoMch);
        JLabel mngoChcLabel = new JLabel(mngoChc);
        JLabel brryCrnLabel = new JLabel(brryCrn);
        JLabel RnbwYgrLabel = new JLabel(rnbwYgr);
        JLabel youGartLabel = new JLabel(youGart);

        /**
         * Initialization of Special panel Items
         */
        SpecialItemPanel(){
            setOpaque(false);
            // to do: place the items in each spot and place slot num, price, calories and name pala
            triChcoLabel.setOpaque(false);
            vitamaxLabel.setOpaque(false);
            strwDuoLabel.setOpaque(false);
            chcoDuoLabel.setOpaque(false);
            chcoMchLabel.setOpaque(false);
            mngoChcLabel.setOpaque(false);
            brryCrnLabel.setOpaque(false);
            RnbwYgrLabel.setOpaque(false);
            youGartLabel.setOpaque(false);

            JPanel triChcoPanel = createImagePanel(triChco, "Triple Chocolate", 230, 90);
            JPanel vitamaxPanel = createImagePanel(vitamax, "Vitamax", 260, 90);
            JPanel strwDuoPanel = createImagePanel(strwDuo, "Strawberry Duo", 999, 999);
            JPanel chcoDuoPanel = createImagePanel(chcoDuo, "Choco Duo", 999, 999);
            JPanel chcoMchPanel = createImagePanel(chcoMch, "ChocoMatcha Madness", 999, 999);
            JPanel mngoChcPanel = createImagePanel(mngoChc, "Mixed Fruit Bits", 999, 999);
            JPanel brryCrnPanel = createImagePanel(brryCrn, "Berrylicious Crunchies", 999, 999);
            JPanel rnbwYgrPanel = createImagePanel(rnbwYgr, "Rainbow Yogurt", 999, 999);
            JPanel youGartPanel = createImagePanel(youGart, "You-g-Art", 999, 999);

            setLayout(new GridLayout(3,3));
            add(triChcoPanel);
            add(vitamaxPanel);
            add(strwDuoPanel);
            add(chcoDuoPanel);
            add(chcoMchPanel);
            add(mngoChcPanel);
            add(brryCrnPanel);
            add(rnbwYgrPanel);
            add(youGartPanel);
        }
    }

    /**
     *  Sets the Image and Description of Product per slot
     * @param icon image of the product.
     * @param item name of the product.
     * @param kCal kCal of the product.
     * @param price price of the product.
     * @return custom-imaged panel
     */

    private JPanel createImagePanel(ImageIcon icon, String item, int kCal, double price) {  //USED IN ItemPanel
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setOpaque(false);

        // JLabel for the image
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);

        // JLabel for the text
        JLabel itemLabel = new JLabel(item);
        itemLabel.setHorizontalAlignment(SwingConstants.CENTER);
        itemLabel.setVerticalAlignment(SwingConstants.CENTER);
        itemLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel itemPrice = new JLabel(kCal + " cal.");
        itemPrice.setHorizontalAlignment(SwingConstants.CENTER);
        itemPrice.setVerticalAlignment(SwingConstants.CENTER);
        itemPrice.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel itemCal = new JLabel(("PHP " + price));
        itemCal.setHorizontalAlignment(SwingConstants.CENTER);
        itemCal.setVerticalAlignment(SwingConstants.CENTER);
        itemCal.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel textPanel = new JPanel(new GridLayout(3,1));
        textPanel.add(itemLabel);
        textPanel.add(itemPrice);
        textPanel.add(itemCal);

        textPanel.setOpaque(false);

        // Add the components to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        panel.add(imageLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(10,0,0,0);
        panel.add(textPanel, gbc);

        return panel;
    }

    /**
     * SubClass for TextArea GUI Panel Components.
     */
    protected class TextPanel extends JPanel {

        private JTextArea vmdisplay = new JTextArea(10, 40);
        private JScrollPane scrollPane = new JScrollPane(vmdisplay);

        /**
         * Initialization of TextArea.
         */
        public TextPanel() {
            setLayout(new BorderLayout());

            vmdisplay.setLineWrap(true);
            vmdisplay.setFont(new Font("consolas", Font.BOLD, 16));
            vmdisplay.setBackground(new Color(98, 174, 239));
            scrollPane.setPreferredSize(new Dimension(625, 300));

            scrollPane.setBackground(new Color(98, 174, 239));
            scrollPane.setBorder(null);

            add(scrollPane);
        }
    }

    /**
     * SubClass for Buttons' Panel GUI Components
     */
    private class ButtonPanel extends JPanel {

        private String currMode; // restock | replenish | collect | edit price
        private boolean moneyLocked = false;
        public JSpinner[] denominationButtons;
        private final JPanel[] moneyTags = new JPanel[10];
        private final JCheckBox setter;
        private JPanel buttonPanel1 = new JPanel();
        private JPanel blankPanel = new JPanel();
        private JPanel replenishPanel = new JPanel(new BorderLayout());
        private JPanel moneySubPanel = new JPanel(new GridLayout(5, 2));
        private JPanel restockPanel = new JPanel();
        private JPanel collectPanel = new JPanel();
        private JPanel editPricePanel = new JPanel();
        private CardLayout menu = new CardLayout();

        /**
         * Initialization of button gui components.
         */
        public ButtonPanel() {
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(625, 300));
            setBackground(Color.GREEN);

            buttonPanel1.setLayout(menu);
            blankPanel.setOpaque(false);

            denominationButtons = new JSpinner[10];
            for (int i = 0; i < 10; i++) {
                SpinnerNumberModel moneyModel = new SpinnerNumberModel();
                moneyModel.setMinimum(0);
                moneyModel.setValue(0);
                denominationButtons[i] = new JSpinner(moneyModel);
                denominationButtons[i].setOpaque(false);
                denominationButtons[i].setPreferredSize(new Dimension(90, 40));
                ((JSpinner.DefaultEditor) denominationButtons[i].getEditor()).getTextField().setEditable(false);
                moneyTags[i] = new JPanel();
                moneyTags[i].setOpaque(false);
                JLabel vlHolder = new JLabel();
                vlHolder.setOpaque(false);
                moneyTags[i].setLayout(new FlowLayout());
                switch (i) {
                    case 0 -> vlHolder.setText("PHP 1 coin");
                    case 1 -> vlHolder.setText("PHP 5 coin");
                    case 2 -> vlHolder.setText("PHP 10 coin");
                    case 3 -> vlHolder.setText("PHP 20 coin");
                    case 4 -> vlHolder.setText("PHP 20 bill");
                    case 5 -> vlHolder.setText("PHP 50 bill");
                    case 6 -> vlHolder.setText("PHP 100 bill");
                    case 7 -> vlHolder.setText("PHP 200 bill");
                    case 8 -> vlHolder.setText("PHP 500 bill");
                    case 9 -> vlHolder.setText("PHP 1000 bill");
                }
                moneyTags[i].add(denominationButtons[i]);
                moneyTags[i].add(vlHolder);
                moneySubPanel.add(moneyTags[i]);
            }
            setter = new JCheckBox();
            setter.setOpaque(false);
            JLabel denominationLabel = new JLabel();
            denominationLabel.setText("DENOMINATION FEEDER");
            denominationLabel.setFont(new Font("consolas", Font.BOLD, 14));
            denominationLabel.setHorizontalAlignment(SwingConstants.CENTER);
            setter.setText("Lock in Money");
            setter.setHorizontalAlignment(SwingConstants.CENTER);
            replenishPanel.add(denominationLabel, BorderLayout.NORTH);
            moneySubPanel.setOpaque(false);
            replenishPanel.add(moneySubPanel, BorderLayout.CENTER);
            replenishPanel.add(setter, BorderLayout.SOUTH);
            replenishPanel.setBackground(Color.LIGHT_GRAY);
            buttonPanel1.add("replenishPanel",replenishPanel);

            restockPanel.setBackground(Color.GREEN);
            buttonPanel1.add("restockPanel",restockPanel);

            collectPanel.setBackground(Color.MAGENTA);
            buttonPanel1.add("collectPanel",collectPanel);

            editPricePanel.setBackground(Color.YELLOW);
            buttonPanel1.add("pricePanel",editPricePanel);

            buttonPanel1.add("blank", blankPanel);
            menu.show(buttonPanel1, "blank");
            currMode = "blank";
            add(buttonPanel1,BorderLayout.CENTER);
        }
    }

    /**
     * Updates the mode of button panel
     * <p>
     *     Updates the type of button panel shown,
     *     can be button mode for selecting items,
     *     or can be money interface.
     * </p>
     * @param choice type of modes.
     */
    public void updateButtonPanel(int choice){

        // 1    restock
        // 2    replenish money
        // 3    collect money
        // 4    edit item price

        if(choice == 1){
            if(buttonPanel.currMode.equals("restock")){
                buttonPanel.menu.show(buttonPanel.buttonPanel1, "blank");
                buttonPanel.currMode = "blank";
            } else {
                buttonPanel.menu.show(buttonPanel.buttonPanel1, "restockPanel");
                buttonPanel.currMode = "restock";
            }

        } else if (choice == 2 ) {
            if(buttonPanel.currMode.equals("replenish")){
                buttonPanel.menu.show(buttonPanel.buttonPanel1, "blank");
                buttonPanel.currMode = "blank";
            } else {
                buttonPanel.menu.show(buttonPanel.buttonPanel1, "replenishPanel");
                buttonPanel.currMode = "replenish";
            }

        } else if (choice == 3) {
            if(buttonPanel.currMode.equals("collect")) {
                buttonPanel.menu.show(buttonPanel.buttonPanel1, "blank");
                buttonPanel.currMode = "blank";
            } else {
                buttonPanel.menu.show(buttonPanel.buttonPanel1, "collectPanel");
                buttonPanel.currMode = "collect";
            }

        } else if (choice == 4) {
            if(buttonPanel.currMode.equals("edit price")) {
                buttonPanel.menu.show(buttonPanel.buttonPanel1, "blank");
                buttonPanel.currMode = "blank";
            } else {
                buttonPanel.menu.show(buttonPanel.buttonPanel1, "pricePanel");
                buttonPanel.currMode = "edit price";
            }

        } else if (choice == 0) {
            buttonPanel.menu.show(buttonPanel.buttonPanel1, "blank");
            buttonPanel.currMode = "blank";
        }
    }

    /**
     * Updates the mode of item panel
     * <p>
     *     Updates the type of button panel shown,
     *     can be Regular mode of items,
     *     or can be Special items.
     * </p>
     */
    public void updateItemPanel(){
        if(currMode == "Regular"){
            itemPanel.show(itemPanelContainer, "Special");
            this.currMode = "Special";
        }
        else{
            itemPanel.show(itemPanelContainer, "Regular");
            this.currMode = "Regular";
        }
    }

    /**
     * Resetter for money input panel once done setting.
     */

    public void resetMoneyPanel(){
        for (int i = 0; i < 10; i++){
            buttonPanel.denominationButtons[i].setValue(0);
        }
        buttonPanel.setter.setSelected(false);
        updateButtonPanel(2);
    }

    /**
     *Sets the boolean value of whether the money is locked or not.
     */
    public void setMoneyLocked(boolean bool){
        buttonPanel.moneyLocked = bool;
    }

    /**
     *Gets the Checkbox for Setting Money.
     * @return Checkbox for Setting Money.
     */
    public JCheckBox moneyLockToggle(){
        return buttonPanel.setter;
    }

    /**
     * Gets the state of the checkbox.
     * @return boolean value whether to set or not the values of spinner.
     */
    public boolean getMoneyLocked(){
        return buttonPanel.moneyLocked;
    }

    /**
     *Gets the button for going Back.
     * @return button for going Back.
     */
    public JButton getBack(){
        return back;
    }

    /**
     * Gets the value of the indexed spinner button.
     * @return  value of the indexed spinner button.
     */
    public int getValue(int index) {
        return (int) buttonPanel.denominationButtons[index].getValue();
    }

    /**
     *Gets the button for toggling special products.
     * @return button for toggling special products.
     */
    public JButton getToggleSpecial(){
        return toggleSpecial;
    }

    /**
     * Gets the button for restocking products.
     * @return button for restocking products.
     */
    public JButton getRestockBtn() {
        return restockBtn;
    }

    /**
     * Gets the button for Replenishing money.
     * @return button for Replenishing money.
     */
    public JButton getReplenishBtn() {
        return replenishBtn;
    }

    /**
     * Gets the collect money button.
     * @return collect money button.
     */
    public JButton getCollectBtn() {
        return collectBtn;
    }

    /**
     * Gets the edit price button.
     * @return edit price button.
     */
    public JButton getEditPriceBtn() {
        return editPriceBtn;
    }

    /**
     *Gets the current mode of the button panel
     * @return String in relation to the mode.
     */
    public String getModeCurrMode() {
        return buttonPanel.currMode;
    }

    /**
     *Gets the textArea for display.
     * @return textArea for display.
     */
    public JTextArea getTextPanel(){
        return this.textPanel.vmdisplay;
    }

    /**
     * SubClass for Textpane OutputStream redirection
     */
    protected static class TextPane extends OutputStream {
        private JTextArea textArea;

        public TextPane(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) throws IOException {
            // redirects data to the text area
            textArea.append(String.valueOf((char)b));
        }
        @Override
        public void write( byte[] b, int off, int len) throws IOException{
            String text = new String(b,off,len);
            textArea.append(text);
        }
    }

}