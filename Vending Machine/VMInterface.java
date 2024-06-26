import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Vending Maching Interface for simulation
 */
public class VMInterface extends JPanel {
    /**
     * Background image of the VendingMachineInterface.
     */
    private final ImageIcon bgIMG_VMInterface = new ImageIcon("./VMResources/VMInterfaceBG.png");

    /**
     * Panel that contains Regular Items and its descriptions
     */
    private final RegularItemPanel regItemPanel = new RegularItemPanel();

    /**
     * Panel that contains Special Items and its descriptions
     */
    private final SpecialItemPanel specialItemPanel = new SpecialItemPanel();

    /**
     * Layout Manager Panel
     */
    private final CardLayout itemPanel;

    /**
     * Panel for product contatiner
     */
    private final JPanel itemPanelContainer;


    /**
     *  Holder for what vending Machine type is currently loaded
     */
    private String currMode = "Regular";            //TRACKER FOR WHICH MODE THE LAYOUT IS CURRENTLY ON

    /**
     *  Custom Panel for Button
     */
    private final ButtonPanel buttonPanel = new ButtonPanel();

    /**
     *  Custom Panel for text area
     */
    private final TextPanel textPanel = new TextPanel("Big");

    /**
     * Money display to know the total inserted money.
     */
    private final TextPanel moneyDisplay = new TextPanel("Small");  /////////////////////NEW

    /**
     *  Button for toggling special items display
     *  <p>
     *      Toggle button for displaying special
     *      products. Needs to be a Special Vending Machine
     *      to be available.
     *  </p>
     */
    private final JButton toggleSpecial = new JButton();

    /**
     *
     * Button for Inserting Money
     */
    private final JButton InsertMoney = new JButton();

    /**
     * Button for going back to previous menu.
     */
    private final JButton back = new JButton();

    /**
     * Button for the item selection lock in Custom create yogurts.
     */
    private final JButton selectCustom = new JButton("Select");

    /**
     * Dropdown for selection of base of yogurt for customs
     */
    private JComboBox<String> baseDropdown = new JComboBox<>();
    /**
     * Dropdown for selection of sauce of yogurt for customs
     */
    private JComboBox<String> sauceDropdown = new JComboBox<>();
    /**
     * Dropdown for selection of toppings of yogurt for customs
     */
    private JComboBox<String> toppingDropdown = new JComboBox<>();

    /**
     * For Jdialog frame owning, inheriting the frame of button panel as its
     * parent
     */
    Window window = SwingUtilities.windowForComponent(buttonPanel);

    /**
     * JDialog holder
     */
    private JDialog dialog;


    /**
     * Initializations of the Vending Machine Interface Components.
     */
    VMInterface() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

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
        gbc.insets = new Insets(85, 76, 127, 30);
        gbc.gridheight = 4;         // make the itemPanel span all three rows
        gbc.weightx = .81;          // adjust the horizontal size for the itemPanel (0.6 means 60% of available space)
        gbc.weighty = .9;
        add(itemPanelContainer, gbc);


        gbc.insets = new Insets(0, 0, 570, 19);         // textPanel
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.weightx = .18;
        gbc.weighty = .9;
        add(textPanel, gbc);

        InsertMoney.setPreferredSize(new Dimension(625, 100));
        InsertMoney.setIcon(scaleIMG("./VMResources/moneyInsertBT.png", 625, 100));
        InsertMoney.setPressedIcon(scaleIMG("./VMResources/moneyInsertBTclicked.png", 625, 100));
        InsertMoney.setBorder(null);
        InsertMoney.setContentAreaFilled(false);
        InsertMoney.setOpaque(false);
        gbc.insets = new Insets(0, 0, 50, 15);          // InsertMoney button
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.weightx = .18;
        gbc.weighty = .9;
        add(InsertMoney, gbc);

        gbc.insets = new Insets(375, 0, 0, 15);         // buttonPanel
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.weightx = .18;
        gbc.weighty = .9;
        add(buttonPanel, gbc);

        back.setIcon(scaleIMG("./VMResources/backBTnorm.png", 391, 91));
        back.setRolloverIcon(scaleIMG("./VMResources/backBThover.png", 391, 91));
        back.setPressedIcon(scaleIMG("./VMResources/backBTclicked.png", 391, 91));
        back.setBorder(null);
        back.setContentAreaFilled(false);
        back.setOpaque(false);
        gbc.insets = new Insets(890, 75, 0, 0);         // back button
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.weightx = .18;
        gbc.weighty = .9;
        add(back, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(890, 48, 0, 0);         // back button
        add(moneyDisplay, gbc);

        toggleSpecial.setIcon(scaleIMG("./VMResources/specialsBTnorm.png", 391, 91));
        toggleSpecial.setRolloverIcon(scaleIMG("./VMResources/specialsBThover.png", 391, 91));
        toggleSpecial.setPressedIcon(scaleIMG("./VMResources/specialsBTclicked.png", 391, 91));
        toggleSpecial.setBorder(null);
        toggleSpecial.setContentAreaFilled(false);
        toggleSpecial.setOpaque(false);
        gbc.insets = new Insets(890, 0, 0, 25);         // toggle button
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
     * Getter for Select button
     * @return button for select.
     */
    public JButton getSelectCustom() {
        return selectCustom;
    }

    /**
     * Getter for Dialog
     * @return dialog
     */
    public JDialog getDialog() {
        return dialog;
    }

    /**
     * Interface for the custom creation of items.
     * @param slots Arraylist of the slots that contains products.
     */
    public void yougArtFrame(ArrayList<Slot> slots){

        dialog = new JDialog((Frame) window,"Pick your own You-g-Art",true);

        dialog.setSize(new Dimension(400, 300) );
        dialog.setLayout(new GridLayout(4,1));
        JPanel dialogPanel1 = new JPanel(new FlowLayout());
        JPanel dialogPanel2 = new JPanel(new FlowLayout());
        JPanel dialogPanel3 = new JPanel(new FlowLayout());
        JPanel dialogPanel4 = new JPanel(new FlowLayout());


        JLabel yogurtBase = new JLabel("You-g-Art Base: ");
        JLabel yogurtSauce = new JLabel("You-g-Art Sauce: ");
        JLabel yogurtTopps = new JLabel("You-g-Art Toppings: ");

        updateDropdown(baseDropdown,sauceDropdown,toppingDropdown,slots);

        dialogPanel1.add(yogurtBase);
        dialogPanel1.add(baseDropdown);
        dialogPanel2.add(yogurtSauce);
        dialogPanel2.add(sauceDropdown);
        dialogPanel3.add(yogurtTopps);
        dialogPanel3.add(toppingDropdown);
        dialogPanel4.add(selectCustom);

        dialog.add(dialogPanel1);
        dialog.add(dialogPanel2);
        dialog.add(dialogPanel3);
        dialog.add(dialogPanel4);
        dialog.setVisible(true);

    }

    /**
     * Getter for the base dropdown of yogurt.
     * @return the base dropdown.
     */
    public JComboBox<String> getBaseDropdown() {
        return baseDropdown;
    }

    /**
     * Getter for the sauce dropdown of yogurt.
     * @return the sauce dropdown.
     */
    public JComboBox<String> getSauceDropdown() {
        return sauceDropdown;
    }

    /**
     * Getter for the toppings dropdown of yogurt.
     * @return the topping dropdown.
     */
    public JComboBox<String> getToppingDropdown() {
        return toppingDropdown;
    }

    /**
     * Updates the availablity of items on dropdown
     * @param base base of the yogurt dropdown
     * @param sauce sauce of the yogurt dropdown
     * @param topps toppings of the yogurt dropdown
     * @param slots slots that contains the items.
     */
    private void updateDropdown(JComboBox<String> base,JComboBox<String> sauce,JComboBox<String> topps,ArrayList<Slot> slots ){
        base.removeAllItems();
        sauce.removeAllItems();
        topps.removeAllItems();

        for (int i=0; i<13;i++){
            if(!(slots.get(i).getProducts().isEmpty()) && i<3|| (i>5&&i<9)){
                base.addItem(slots.get(i).getBaseProductName());
            }
            else if(!(slots.get(i).getProducts().isEmpty()) && ((i>2 && i<6)||i==12)){
                topps.addItem(slots.get(i).getBaseProductName());
            }
            else sauce.addItem(slots.get(i).getBaseProductName());
        }
    }

    /**
     * The paint method that add graphics for the main panel.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgIMG_VMInterface.getImage(), 0, 0, getWidth(), getHeight(), null);
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

    //    HOW TO CHANGE PRICE:        this.frame.vmInterface.getRegItemPanel().updateItemPrice(index,newPrice);
    //    HOW TO EDIT IMAGE VIS:      this.frame.vmInterface.getRegItemPanel().updateImageVis(index,bool);
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

        JLabel vnllLabelPrice = new JLabel("P100.0");
        JLabel chcoLabelPrice = new JLabel("P100.0");
        JLabel mtchLabelPrice = new JLabel("P100.0");
        JLabel chcpLabelPrice = new JLabel("P100.0");
        JLabel crelLabelPrice = new JLabel("P100.0");
        JLabel mxftLabelPrice = new JLabel("P100.0");
        JLabel raspLabelPrice = new JLabel("P100.0");
        JLabel strwLabelPrice = new JLabel("P100.0");
        JLabel mngoLabelPrice = new JLabel("P100.0");

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

            JPanel vnllPanel = new JPanel(new GridBagLayout());
            JPanel chcoPanel = new JPanel(new GridBagLayout());
            JPanel mtchPanel = new JPanel(new GridBagLayout());
            JPanel chcpPanel = new JPanel(new GridBagLayout());
            JPanel crelPanel = new JPanel(new GridBagLayout());
            JPanel mxftPanel = new JPanel(new GridBagLayout());
            JPanel raspPanel = new JPanel(new GridBagLayout());
            JPanel strwPanel = new JPanel(new GridBagLayout());
            JPanel mngoPanel = new JPanel(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weighty = .8;
            gbc.anchor = GridBagConstraints.CENTER;
            vnllPanel.add(createImagePanel(vnllLabel, "Vanilla", 90),gbc);
            chcoPanel.add(createImagePanel(chcoLabel, "Chocolate", 90),gbc);
            mtchPanel.add(createImagePanel(mtchLabel, "Matcha", 90),gbc);
            chcpPanel.add(createImagePanel(chcpLabel, "Choco Chips", 50),gbc);
            crelPanel.add(createImagePanel(crelLabel, "Cereals", 43),gbc);
            mxftPanel.add(createImagePanel(mxftLabel, "Mixed Fruit Bits", 35),gbc);
            raspPanel.add(createImagePanel(raspLabel, "Raspberry", 90),gbc);
            strwPanel.add(createImagePanel(strwLabel, "Strawberry", 90),gbc);
            mngoPanel.add(createImagePanel(mngoLabel, "Mango", 90),gbc);

            vnllPanel.setOpaque(false);
            chcoPanel.setOpaque(false);
            mtchPanel.setOpaque(false);
            chcpPanel.setOpaque(false);
            crelPanel.setOpaque(false);
            mxftPanel.setOpaque(false);
            raspPanel.setOpaque(false);
            strwPanel.setOpaque(false);
            mngoPanel.setOpaque(false);

            setupCaption(vnllLabelPrice);
            setupCaption(chcoLabelPrice);
            setupCaption(mtchLabelPrice);
            setupCaption(chcpLabelPrice);
            setupCaption(crelLabelPrice);
            setupCaption(mxftLabelPrice);
            setupCaption(raspLabelPrice);
            setupCaption(strwLabelPrice);
            setupCaption(mngoLabelPrice);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weighty = .2;
            gbc.anchor = GridBagConstraints.SOUTH;
            gbc.insets = new Insets(0,0,23,0);
            vnllPanel.add(vnllLabelPrice,gbc);
            chcoPanel.add(chcoLabelPrice,gbc);
            mtchPanel.add(mtchLabelPrice,gbc);
            chcpPanel.add(chcpLabelPrice,gbc);
            crelPanel.add(crelLabelPrice,gbc);
            mxftPanel.add(mxftLabelPrice,gbc);
            raspPanel.add(raspLabelPrice,gbc);
            strwPanel.add(strwLabelPrice,gbc);
            mngoPanel.add(mngoLabelPrice,gbc);

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

        public void updateItemPrice(int index, double newPrice){
            switch (index){
                case 0 -> vnllLabelPrice.setText("P" + newPrice);
                case 1 -> chcoLabelPrice.setText("P" + newPrice);
                case 2 -> mtchLabelPrice.setText("P" + newPrice);
                case 3 -> chcpLabelPrice.setText("P" + newPrice);
                case 4 -> crelLabelPrice.setText("P" + newPrice);
                case 5 -> mxftLabelPrice.setText("P" + newPrice);
                case 6 -> raspLabelPrice.setText("P" + newPrice);
                case 7 -> strwLabelPrice.setText("P" + newPrice);
                case 8 -> mngoLabelPrice.setText("P" + newPrice);
            }
        }

        public void updateImageVis (int index, int productQty){
            boolean bool = productQty > 0;
            switch (index){
                case 0 -> {if (!bool) vnllLabel.setIcon(scaleIMG("./VMResources/vanillaBNW.png",150,150));
                else vnllLabel.setIcon(scaleIMG("./VMResources/vanilla.png",150,150));}
                case 1 -> {if (!bool) chcoLabel.setIcon(scaleIMG("./VMResources/chocoBNW.png",150,150));
                else chcoLabel.setIcon(scaleIMG("./VMResources/choco.png",150,150));}
                case 2 -> {if (!bool) mtchLabel.setIcon(scaleIMG("./VMResources/matchaBNW.png",150,150));
                else mtchLabel.setIcon(scaleIMG("./VMResources/matcha.png",150,150));}
                case 3 -> {if (!bool) chcpLabel.setIcon(scaleIMG("./VMResources/chocochipBNW.png",150,150));
                else chcpLabel.setIcon(scaleIMG("./VMResources/chocochip.png",150,150));}
                case 4 -> {if (!bool) crelLabel.setIcon(scaleIMG("./VMResources/cerealBNW.png",150,150));
                else crelLabel.setIcon(scaleIMG("./VMResources/cereal.png",150,150));}
                case 5 -> {if (!bool) mxftLabel.setIcon(scaleIMG("./VMResources/mixedFruitBNW.png",150,150));
                else mxftLabel.setIcon(scaleIMG("./VMResources/mixedFruit.png",150,150));}
                case 6 -> {if (!bool) raspLabel.setIcon(scaleIMG("./VMResources/raspberryBNW.png",150,150));
                else raspLabel.setIcon(scaleIMG("./VMResources/raspberry.png",150,150));}
                case 7 -> {if (!bool) strwLabel.setIcon(scaleIMG("./VMResources/strawberryBNW.png",150,150));
                else strwLabel.setIcon(scaleIMG("./VMResources/strawberry.png",150,150));}
                case 8 -> {if (!bool) mngoLabel.setIcon(scaleIMG("./VMResources/mangoBNW.png",150,150));
                else mngoLabel.setIcon(scaleIMG("./VMResources/mango.png",150,150));}
            }
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
        JLabel rnbwYgrLabel = new JLabel(rnbwYgr);
        JLabel youGartLabel = new JLabel(youGart);

        JLabel triChcoLabelPrice = new JLabel("P150.0");
        JLabel vitamaxLabelPrice = new JLabel("P150.0");
        JLabel strwDuoLabelPrice = new JLabel("P150.0");
        JLabel chcoDuoLabelPrice = new JLabel("P150.0");
        JLabel chcoMchLabelPrice = new JLabel("P150.0");
        JLabel mngoChcLabelPrice = new JLabel("P150.0");
        JLabel brryCrnLabelPrice = new JLabel("P150.0");
        JLabel rnbwYgrLabelPrice = new JLabel("P150.0");
        JLabel youGartLabelPrice = new JLabel("P150.0");

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
            rnbwYgrLabel.setOpaque(false);
            youGartLabel.setOpaque(false);

            JPanel triChcoPanel = new JPanel(new GridBagLayout());
            JPanel vitamaxPanel = new JPanel(new GridBagLayout());
            JPanel strwDuoPanel = new JPanel(new GridBagLayout());
            JPanel chcoDuoPanel = new JPanel(new GridBagLayout());
            JPanel chcoMchPanel = new JPanel(new GridBagLayout());
            JPanel mngoChcPanel = new JPanel(new GridBagLayout());
            JPanel brryCrnPanel = new JPanel(new GridBagLayout());
            JPanel rnbwYgrPanel = new JPanel(new GridBagLayout());
            JPanel youGartPanel = new JPanel(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weighty = 0.8;
            gbc.anchor = GridBagConstraints.CENTER;
            triChcoPanel.add(createImagePanel(triChcoLabel, "Triple Chocolate", 420),gbc);
            vitamaxPanel.add(createImagePanel(vitamaxLabel, "Vitamax", 110),gbc);
            strwDuoPanel.add(createImagePanel(strwDuoLabel, "Strawberry Duo", 130),gbc);
            chcoDuoPanel.add(createImagePanel(chcoDuoLabel, "Choco Duos", 220),gbc);
            chcoMchPanel.add(createImagePanel(chcoMchLabel, "ChocoMatcha Madness", 170),gbc);
            mngoChcPanel.add(createImagePanel(mngoChcLabel, "Mango Chocolate Glazed", 210),gbc);
            brryCrnPanel.add(createImagePanel(brryCrnLabel, "Berrylicious Crunchies", 160),gbc);
            rnbwYgrPanel.add(createImagePanel(rnbwYgrLabel, "Rainbow Yogurt", 160),gbc);
            youGartPanel.add(createImagePanel(youGartLabel, "You-g-Art", 230),gbc);

            triChcoPanel.setOpaque(false);
            vitamaxPanel.setOpaque(false);
            strwDuoPanel.setOpaque(false);
            chcoDuoPanel.setOpaque(false);
            chcoMchPanel.setOpaque(false);
            mngoChcPanel.setOpaque(false);
            brryCrnPanel.setOpaque(false);
            rnbwYgrPanel.setOpaque(false);
            youGartPanel.setOpaque(false);

            setupCaption(triChcoLabelPrice);
            setupCaption(vitamaxLabelPrice);
            setupCaption(strwDuoLabelPrice);
            setupCaption(chcoDuoLabelPrice);
            setupCaption(chcoMchLabelPrice);
            setupCaption(mngoChcLabelPrice);
            setupCaption(brryCrnLabelPrice);
            setupCaption(rnbwYgrLabelPrice);
            setupCaption(youGartLabelPrice);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weighty = 0.2;
            gbc.anchor = GridBagConstraints.SOUTH;
            gbc.insets = new Insets(0, 0, 23, 0);
            triChcoPanel.add(triChcoLabelPrice,gbc);
            vitamaxPanel.add(vitamaxLabelPrice,gbc);
            strwDuoPanel.add(strwDuoLabelPrice,gbc);
            chcoDuoPanel.add(chcoDuoLabelPrice,gbc);
            chcoMchPanel.add(chcoMchLabelPrice,gbc);
            mngoChcPanel.add(mngoChcLabelPrice,gbc);
            brryCrnPanel.add(brryCrnLabelPrice,gbc);
            rnbwYgrPanel.add(rnbwYgrLabelPrice,gbc);
            youGartPanel.add(youGartLabelPrice,gbc);

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

        public void updateItemPrice(int index, double newPrice){
            switch (index){
                case 0 -> triChcoLabelPrice.setText("P" + newPrice);
                case 1 -> vitamaxLabelPrice.setText("P" + newPrice);
                case 2 -> strwDuoLabelPrice.setText("P" + newPrice);
                case 3 -> chcoDuoLabelPrice.setText("P" + newPrice);
                case 4 -> chcoMchLabelPrice.setText("P" + newPrice);
                case 5 -> mngoChcLabelPrice.setText("P" + newPrice);
                case 6 -> brryCrnLabelPrice.setText("P" + newPrice);
                case 7 -> rnbwYgrLabelPrice.setText("P" + newPrice);
                case 8 -> youGartLabelPrice.setText("P" + newPrice);
            }
        }

        public void updateImageVis (int index, boolean bool){
            switch (index){
                case 0 -> {
                    if (bool) triChcoLabel.setIcon(scaleIMG("./VMResources/trichoco.png", 150, 150));
                    else triChcoLabel.setIcon(scaleIMG("./VMResources/trichocoBNW.png", 150, 150));
                }
                case 1 -> {
                    if (bool) vitamaxLabel.setIcon(scaleIMG("./VMResources/vitamax.png", 150, 150));
                    else vitamaxLabel.setIcon(scaleIMG("./VMResources/vitamaxBNW.png", 150, 150));
                }
                case 2 -> {
                    if (bool) strwDuoLabel.setIcon(scaleIMG("./VMResources/strwbDuo.png", 150, 150));
                    else strwDuoLabel.setIcon(scaleIMG("./VMResources/strwbDuoBNW.png", 150, 150));
                }
                case 3 -> {
                    if (bool) chcoDuoLabel.setIcon(scaleIMG("./VMResources/chcoDuo.png", 150, 150));
                    else chcoDuoLabel.setIcon(scaleIMG("./VMResources/chcoDuoBNW.png", 150, 150));
                }
                case 4 -> {
                    if (bool) chcoMchLabel.setIcon(scaleIMG("./VMResources/chocoMatcha.png", 150, 150));
                    else chcoMchLabel.setIcon(scaleIMG("./VMResources/chocoMatchaBNW.png", 150, 150));
                }
                case 5 -> {
                    if (bool) mngoChcLabel.setIcon(scaleIMG("./VMResources/mangoChoco.png", 150, 150));
                    else mngoChcLabel.setIcon(scaleIMG("./VMResources/mangoChocoBNW.png", 150, 150));
                }
                case 6 -> {
                    if (bool) brryCrnLabel.setIcon(scaleIMG("./VMResources/berrylicious.png", 150, 150));
                    else brryCrnLabel.setIcon(scaleIMG("./VMResources/berryliciousBNW.png", 150, 150));
                }
                case 7 -> {
                    if (bool) rnbwYgrLabel.setIcon(scaleIMG("./VMResources/rainbow.png", 150, 150));
                    else rnbwYgrLabel.setIcon(scaleIMG("./VMResources/rainbowBNW.png", 150, 150));
                }
                case 8 -> {if (bool) youGartLabel.setIcon(scaleIMG("./VMResources/yougart.png",150,150));
                else youGartLabel.setIcon(scaleIMG("./VMResources/yougartBNW.png",150,150));
                }
            }
        }
    }


    /**
     * Setups the styling of texts
     * @param label the text label to be modified.
     */
    private void setupCaption(JLabel label){
        label.setOpaque(false);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("consolas", Font.BOLD, 18));
    }

    /**
     *  Sets the Image and Description of Product per slot
     * @param imageLabel image of the product.
     * @param item name of the product.
     * @param kCal kCal of the product.
     * @return custom-imaged panel
     */
    private JPanel createImagePanel(JLabel imageLabel, String item, int kCal) {  //USED IN ItemPanel
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setOpaque(false);

        // JLabel for item image
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);

        // JLabel for the text
        JLabel itemLabel = new JLabel(item);
        itemLabel.setHorizontalAlignment(SwingConstants.CENTER);
        itemLabel.setVerticalAlignment(SwingConstants.CENTER);
        itemLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel itemCal = new JLabel(kCal + " cal.");
        itemCal.setHorizontalAlignment(SwingConstants.CENTER);
        itemCal.setVerticalAlignment(SwingConstants.CENTER);
        itemCal.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel textPanel = new JPanel(new GridLayout(3,1));
        textPanel.add(itemLabel);
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

        private final JTextArea vmdisplay = new JTextArea(10, 40);
        private TextField moneyDisplay = new TextField();
        private JScrollPane scrollPane = new JScrollPane(vmdisplay);

        /**
         * Initialization of TextArea.
         */
        public TextPanel(String type) {
            setLayout(new BorderLayout());

            vmdisplay.setLineWrap(true);
            vmdisplay.setFont(new Font("consolas", Font.BOLD, 16));
            vmdisplay.setBackground(new Color(98, 174, 239));

            if (type.equals("Big")) {
                this.scrollPane.setPreferredSize(new Dimension(625, 300));
                this.scrollPane.setBackground(new Color(98, 174, 239));
                this.scrollPane.setBorder(null);
                add(scrollPane);
            }
            else if(type.equals("Small")){
                this.moneyDisplay.setPreferredSize(new Dimension(235,75));
                this.moneyDisplay.setEditable(false);
                this.moneyDisplay.setFont(new Font("consolas", Font.BOLD,60));
                updateMoneyDisplay(0);

                add(moneyDisplay, BorderLayout.CENTER);
            }
        }

        public void updateMoneyDisplay(double money){
            this.moneyDisplay.setText("P" + money);
        }
    }

    /**
     * SubClass for Buttons' Panel GUI Components
     */
    private class ButtonPanel extends JPanel {
        private CardLayout menu = new CardLayout();
        private boolean moneyLocked;
        private String currMode; // either payment || selection
        public JSpinner[] denominationButtons;
        private final JCheckBox setter;
        private final JButton[] slotButtons = new JButton[9];
        private final JPanel[] moneyTags = new JPanel[10];
        private final JPanel buttonPanel1 = new JPanel();
        private JPanel moneyPanel = new JPanel(new BorderLayout());
        private JPanel moneySubPanel = new JPanel(new GridLayout(5, 2));
        private JPanel regbuttonPanel = new JPanel();
        private JPanel specbuttonPanel = new JPanel();
        private JButton specialButton = new JButton();

        /**
         * Initialization of button gui components.
         */
        public ButtonPanel() {
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(625, 300));
            moneyLocked = false;

            currMode = "SELECTION";

            buttonPanel1.setLayout(menu);
            buttonPanel1.setOpaque(false);

            regbuttonPanel.setLayout(new GridLayout(3, 3));
            regbuttonPanel.setOpaque(true);
            regbuttonPanel.setBackground(new Color(87, 99, 104));


            for (int i = 0; i < 9; i++) {
                slotButtons[i] = new JButton();
            }

            slotButtons[0].setIcon(scaleIMG("./VMResources/slot1.png", 212, 108));
            slotButtons[0].setBorder(null);
            slotButtons[0].setOpaque(false);
            slotButtons[0].setContentAreaFilled(false);
            regbuttonPanel.add(slotButtons[0]);

            slotButtons[1].setIcon(scaleIMG("./VMResources/slot2.png", 212, 108));
            slotButtons[1].setBorder(null);
            slotButtons[1].setOpaque(false);
            slotButtons[1].setContentAreaFilled(false);
            regbuttonPanel.add(slotButtons[1]);

            slotButtons[2].setIcon(scaleIMG("./VMResources/slot3.png", 212, 108));
            slotButtons[2].setOpaque(false);
            slotButtons[2].setContentAreaFilled(false);
            slotButtons[2].setBorder(null);
            regbuttonPanel.add(slotButtons[2]);

            slotButtons[3].setIcon(scaleIMG("./VMResources/slot4.png", 212, 108));
            slotButtons[3].setOpaque(false);
            slotButtons[3].setContentAreaFilled(false);
            slotButtons[3].setBorder(null);
            regbuttonPanel.add(slotButtons[3]);

            slotButtons[4].setIcon(scaleIMG("./VMResources/slot5.png", 212, 108));
            slotButtons[4].setOpaque(false);
            slotButtons[4].setContentAreaFilled(false);
            slotButtons[4].setBorder(null);
            regbuttonPanel.add(slotButtons[4]);


            slotButtons[5].setIcon(scaleIMG("./VMResources/slot6.png", 212, 108));
            slotButtons[5].setOpaque(false);
            slotButtons[5].setContentAreaFilled(false);
            slotButtons[5].setBorder(null);
            regbuttonPanel.add(slotButtons[5]);

            slotButtons[6].setIcon(scaleIMG("./VMResources/slot7.png", 212, 108));
            slotButtons[6].setOpaque(false);
            slotButtons[6].setContentAreaFilled(false);
            slotButtons[6].setBorder(null);
            regbuttonPanel.add(slotButtons[6]);

            slotButtons[7].setIcon(scaleIMG("./VMResources/slot8.png", 212, 108));
            slotButtons[7].setOpaque(false);
            slotButtons[7].setContentAreaFilled(false);
            slotButtons[7].setBorder(null);
            regbuttonPanel.add(slotButtons[7]);

            slotButtons[8].setIcon(scaleIMG("./VMResources/slot9.png", 212, 108));
            slotButtons[8].setOpaque(false);
            slotButtons[8].setContentAreaFilled(false);
            slotButtons[8].setBorder(null);
            regbuttonPanel.add(slotButtons[8]);
            buttonPanel1.add("buttonsMode", regbuttonPanel);

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
            moneyPanel.add(denominationLabel, BorderLayout.NORTH);
            moneySubPanel.setOpaque(false);
            moneyPanel.add(moneySubPanel, BorderLayout.CENTER);
            moneyPanel.add(setter, BorderLayout.SOUTH);
            moneyPanel.setBackground(Color.LIGHT_GRAY);
            //moneyPanel.setOpaque(false);
            buttonPanel1.add("moneyMode", moneyPanel);


            //specbuttonPanel.setBackground(Color.GRAY);
            buttonPanel1.add("SpecialMode", specbuttonPanel);

            add(buttonPanel1, BorderLayout.CENTER);
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
    public void updateButtonPanel(int choice) {
        if (choice == 1) {
            buttonPanel.currMode = "selection";
            buttonPanel.menu.show(buttonPanel.buttonPanel1, "buttonsMode");
        } else if (choice == 2) {
            buttonPanel.currMode = "payment";
            buttonPanel.menu.show(buttonPanel.buttonPanel1, "moneyMode");

        } else if (choice == 3) {
            buttonPanel.menu.show(buttonPanel.buttonPanel1, "SpecialMode");
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
    public void updateItemPanel() {

        if (currMode.equalsIgnoreCase("Regular")) {
            itemPanel.show(itemPanelContainer, "Special");
            this.currMode = "Special";
        } else {
            itemPanel.show(itemPanelContainer, "Regular");
            this.currMode = "Regular";
        }
    }

    /**
     * Gets the money display text panel.
     * @return the text panel
     */

    public TextPanel getMoneyDisplay(){
        return moneyDisplay;
    }

    /**
     * Panel for regular item display.
     * @return regular item panel display
     */
    public RegularItemPanel getRegItemPanel(){
        return regItemPanel;
    }

    /**
     * Panel for special item display.
     * @return special item panel display
     */
    public SpecialItemPanel getSpecialItemPanel(){
        return specialItemPanel;
    }

    /**
     *Gets the button for Inserting Money.
     * @return button for Inserting Money.
     */
    public JButton getInsertMoney() {
        return InsertMoney;
    }

    /**
     *Gets the button for going Back.
     * @return button for going Back.
     */
    public JButton getBack() {
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
    public JButton getToggleSpecial() {
        return toggleSpecial;
    }

    /**
     * Gets the state of the checkbox.
     * @return boolean value whether to set or not the values of spinner.
     */
    public boolean getMoneyLocked(){
        return buttonPanel.moneyLocked;
    }

    /**
     *Gets the Checkbox for Setting Money.
     * @return Checkbox for Setting Money.
     */
    public JCheckBox moneyLockToggle(){
        return buttonPanel.setter;
    }

    /**
     *Sets the boolean value of whether the money is locked or not.
     */
    public void setLockMoney (boolean bool){
        buttonPanel.moneyLocked = bool;
    }

    /**
     *Gets the current mode of the button panel
     * @return String in relation to the mode.
     */
    public String getModeButtonPanel(){
        return buttonPanel.currMode;
    }

    /**
     * Gets the buttons for slots.
     * @param index index of the item.
     * @return buttons for slots.
     */
    public JButton getVMButtons(int index){
        return buttonPanel.slotButtons[index];
    }

    /**
     *Gets the textArea for display.
     * @return textArea for display.
     */
    public JTextArea getTextPanel(){
        return this.textPanel.vmdisplay;
    }

    /**
     * Resetter for money input panel once done setting.
     */
    public void resetMoneyPanel(){
        for (int i = 0; i < 10; i++){
            buttonPanel.denominationButtons[i].setValue(0);
        }
        buttonPanel.setter.setSelected(false);
        updateButtonPanel(1);
    }

    /**
     * Gets the string text of current mode.
     * @return
     */
    public String getCurrMode() {
        return currMode;
    }


    /**
     * SubClass for Textpane OutputStream redirection
     */
    protected static class TextPane extends OutputStream {
        private final JTextArea textArea;

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