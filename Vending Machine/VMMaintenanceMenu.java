import javax.swing.*;
import java.awt.*;
import java.awt.desktop.AppForegroundListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
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
    private TextPanel textPanel = new TextPanel("Big");

    private final TextPanel moneyDisplay = new TextPanel("Small"); //////////NEWWWWWWWWWWW

    /**
     * Button for restocking products.
     */
    private final JButton restockBtn = new JButton(scaleIMG("./VMResources/restockItem.png",100,100));

    /**
     * Button for Replenishing money.
     */
    private final JButton replenishBtn = new JButton(scaleIMG("./VMResources/replenishMoney.png",100,100));

    /**
     * Button for Collecting Money
     */
    private final JButton collectBtn = new JButton(scaleIMG("./VMResources/collectMoney.png",100,100));

    /**
     * Button for editing price.
     */
    private final JButton editPriceBtn = new JButton(scaleIMG("./VMResources/editPrice.png",100,100));

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

        buttonPanel.setBackground(new Color(87,99,104));



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

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(890, 48, 0, 0);         // back button
        add(moneyDisplay, gbc);

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
     * The paint method that adds graphics for the main panel.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g){
        try{
            super.paintComponent(g);
            g.drawImage(bgIMG_VMInterface.getImage(),0,0,getWidth(),getHeight(),null);
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

    //    HOW TO CHANGE PRICE:        this.frame.maintenanceMenu.getRegItemPanel().updateItemPrice(index,newPrice);
    //    HOW TO EDIT IMAGE VIS:      this.frame.maintenanceMenu.getRegItemPanel().updateImageVis(index,bool);
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
            vnllPanel.add(createImagePanel(vnllLabel, "Vanilla", 230),gbc);
            chcoPanel.add(createImagePanel(chcoLabel, "Chocolate", 260),gbc);
            mtchPanel.add(createImagePanel(mtchLabel, "Matcha", 999),gbc);
            chcpPanel.add(createImagePanel(chcpLabel, "Choco Chips", 999),gbc);
            crelPanel.add(createImagePanel(crelLabel, "Cereals", 999),gbc);
            mxftPanel.add(createImagePanel(mxftLabel, "Mixed Fruit Bits", 999),gbc);
            raspPanel.add(createImagePanel(raspLabel, "Raspberry", 999),gbc);
            strwPanel.add(createImagePanel(strwLabel, "Strawberry", 999),gbc);
            mngoPanel.add(createImagePanel(mngoLabel, "Mango", 999),gbc);

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
            triChcoPanel.add(createImagePanel(triChcoLabel, "Triple Chocolate", 230),gbc);
            vitamaxPanel.add(createImagePanel(vitamaxLabel, "Vitamax", 260),gbc);
            strwDuoPanel.add(createImagePanel(strwDuoLabel, "Strawberry Duo", 999),gbc);
            chcoDuoPanel.add(createImagePanel(chcoDuoLabel, "Choco Duos", 999),gbc);
            chcoMchPanel.add(createImagePanel(chcoMchLabel, "ChocoMatcha Madness", 999),gbc);
            mngoChcPanel.add(createImagePanel(mngoChcLabel, "Mango Chocolate Glazed", 999),gbc);
            brryCrnPanel.add(createImagePanel(brryCrnLabel, "Berrylicious Crunchies", 999),gbc);
            rnbwYgrPanel.add(createImagePanel(rnbwYgrLabel, "Rainbow Yogurt", 999),gbc);
            youGartPanel.add(createImagePanel(youGartLabel, "You-g-Art", 999),gbc);

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

            vmdisplay.setEditable(false);
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

        public void updateVMdisplay(ArrayList<Slot> productsSold){
            String transaction = "";
            double profit = 0;

            for (int i = 0; i < productsSold.size(); i++){
                if (productsSold.get(i).getProductQuantity() > 0)
                    transaction = transaction + productsSold.get(i).getBaseProductName() +
                        " | P" + productsSold.get(i).getBaseProductPrice() + "\n";

                profit += productsSold.get(i).getBaseProductPrice() * productsSold.get(i).
                        getNumProductsSold();
            }

            transaction = transaction + "\n\nPROFIT: P" + profit;


            vmdisplay.setText(transaction);
        }

        public void updateMoneyDisplay(double money){
            this.moneyDisplay.setText("P" + money);
        }
    }


    /**
     * SubClass for Buttons' Panel GUI Components
     */
    protected class ButtonPanel extends JPanel {

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
        private JPanel collectPanel = new JPanel(new GridBagLayout());
        private final JButton collectBtn = new JButton();
        private final JButton[] restockSlotButtons = new JButton[9];
        private final JButton[] editPriceSlotButtons = new JButton[9];
        private JPanel editPricePanel = new JPanel();
        private CardLayout menu = new CardLayout();

        private TextPanel transacHistory = new TextPanel("Big");

        /**
         * Initialization of button gui components.
         */
        public ButtonPanel() {
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(625, 300));

            buttonPanel1.setLayout(menu);
            blankPanel.setBackground(new Color(87,99,104));
            blankPanel.setOpaque(true);

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

            restockPanel.setLayout(new GridLayout(3, 3));
            restockPanel.setOpaque(true);
            restockPanel.setBackground(new Color(87, 99, 104));

            for (int i = 0; i < 9; i++) {
                restockSlotButtons[i] = new JButton();
            }
            restockSlotButtons[0].setIcon(scaleIMG("./VMResources/slot1.png", 212, 108));
            restockSlotButtons[0].setBorder(null);
            restockSlotButtons[0].setOpaque(false);
            restockSlotButtons[0].setContentAreaFilled(false);
            restockPanel.add(restockSlotButtons[0]);

            restockSlotButtons[1].setIcon(scaleIMG("./VMResources/slot2.png", 212, 108));
            restockSlotButtons[1].setBorder(null);
            restockSlotButtons[1].setOpaque(false);
            restockSlotButtons[1].setContentAreaFilled(false);
            restockPanel.add(restockSlotButtons[1]);

            restockSlotButtons[2].setIcon(scaleIMG("./VMResources/slot3.png", 212, 108));
            restockSlotButtons[2].setOpaque(false);
            restockSlotButtons[2].setContentAreaFilled(false);
            restockSlotButtons[2].setBorder(null);
            restockPanel.add(restockSlotButtons[2]);

            restockSlotButtons[3].setIcon(scaleIMG("./VMResources/slot4.png", 212, 108));
            restockSlotButtons[3].setOpaque(false);
            restockSlotButtons[3].setContentAreaFilled(false);
            restockSlotButtons[3].setBorder(null);
            restockPanel.add(restockSlotButtons[3]);

            restockSlotButtons[4].setIcon(scaleIMG("./VMResources/slot5.png", 212, 108));
            restockSlotButtons[4].setOpaque(false);
            restockSlotButtons[4].setContentAreaFilled(false);
            restockSlotButtons[4].setBorder(null);
            restockPanel.add(restockSlotButtons[4]);


            restockSlotButtons[5].setIcon(scaleIMG("./VMResources/slot6.png", 212, 108));
            restockSlotButtons[5].setOpaque(false);
            restockSlotButtons[5].setContentAreaFilled(false);
            restockSlotButtons[5].setBorder(null);
            restockPanel.add(restockSlotButtons[5]);

            restockSlotButtons[6].setIcon(scaleIMG("./VMResources/slot7.png", 212, 108));
            restockSlotButtons[6].setOpaque(false);
            restockSlotButtons[6].setContentAreaFilled(false);
            restockSlotButtons[6].setBorder(null);
            restockPanel.add(restockSlotButtons[6]);

            restockSlotButtons[7].setIcon(scaleIMG("./VMResources/slot8.png", 212, 108));
            restockSlotButtons[7].setOpaque(false);
            restockSlotButtons[7].setContentAreaFilled(false);
            restockSlotButtons[7].setBorder(null);
            restockPanel.add(restockSlotButtons[7]);

            restockSlotButtons[8].setIcon(scaleIMG("./VMResources/slot9.png", 212, 108));
            restockSlotButtons[8].setOpaque(false);
            restockSlotButtons[8].setContentAreaFilled(false);
            restockSlotButtons[8].setBorder(null);
            restockPanel.add(restockSlotButtons[8]);
            buttonPanel1.add("restockPanel",restockPanel);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.CENTER;

            collectBtn.setIcon(scaleIMG("./VMResources/collectBTnormal.png", 391, 91));
            collectBtn.setRolloverIcon(scaleIMG("./VMResources/collectBThover.png", 391, 91));
            collectBtn.setPressedIcon(scaleIMG("./VMResources/collectBTclicked.png", 391, 91));
            collectBtn.setBorder(null);
            collectBtn.setContentAreaFilled(false);
            collectBtn.setOpaque(false);
            collectBtn.setPreferredSize(new Dimension(391,91));
            collectPanel.setBackground(new Color(87,99,104));
            collectPanel.add(collectBtn, gbc);
            buttonPanel1.add("collectPanel",collectPanel);

            editPricePanel.setLayout(new GridLayout(3, 3));
            editPricePanel.setOpaque(true);
            editPricePanel.setBackground(new Color(87, 99, 104));

            for (int i = 0; i < 9; i++) {
                editPriceSlotButtons[i] = new JButton();
            }
            editPriceSlotButtons[0].setIcon(scaleIMG("./VMResources/slot1.png", 212, 108));
            editPriceSlotButtons[0].setBorder(null);
            editPriceSlotButtons[0].setOpaque(false);
            editPriceSlotButtons[0].setContentAreaFilled(false);
            editPricePanel.add(editPriceSlotButtons[0]);

            editPriceSlotButtons[1].setIcon(scaleIMG("./VMResources/slot2.png", 212, 108));
            editPriceSlotButtons[1].setBorder(null);
            editPriceSlotButtons[1].setOpaque(false);
            editPriceSlotButtons[1].setContentAreaFilled(false);
            editPricePanel.add(editPriceSlotButtons[1]);

            editPriceSlotButtons[2].setIcon(scaleIMG("./VMResources/slot3.png", 212, 108));
            editPriceSlotButtons[2].setOpaque(false);
            editPriceSlotButtons[2].setContentAreaFilled(false);
            editPriceSlotButtons[2].setBorder(null);
            editPricePanel.add(editPriceSlotButtons[2]);

            editPriceSlotButtons[3].setIcon(scaleIMG("./VMResources/slot4.png", 212, 108));
            editPriceSlotButtons[3].setOpaque(false);
            editPriceSlotButtons[3].setContentAreaFilled(false);
            editPriceSlotButtons[3].setBorder(null);
            editPricePanel.add(editPriceSlotButtons[3]);

            editPriceSlotButtons[4].setIcon(scaleIMG("./VMResources/slot5.png", 212, 108));
            editPriceSlotButtons[4].setOpaque(false);
            editPriceSlotButtons[4].setContentAreaFilled(false);
            editPriceSlotButtons[4].setBorder(null);
            editPricePanel.add(editPriceSlotButtons[4]);


            editPriceSlotButtons[5].setIcon(scaleIMG("./VMResources/slot6.png", 212, 108));
            editPriceSlotButtons[5].setOpaque(false);
            editPriceSlotButtons[5].setContentAreaFilled(false);
            editPriceSlotButtons[5].setBorder(null);
            editPricePanel.add(editPriceSlotButtons[5]);

            editPriceSlotButtons[6].setIcon(scaleIMG("./VMResources/slot7.png", 212, 108));
            editPriceSlotButtons[6].setOpaque(false);
            editPriceSlotButtons[6].setContentAreaFilled(false);
            editPriceSlotButtons[6].setBorder(null);
            editPricePanel.add(editPriceSlotButtons[6]);

            editPriceSlotButtons[7].setIcon(scaleIMG("./VMResources/slot8.png", 212, 108));
            editPriceSlotButtons[7].setOpaque(false);
            editPriceSlotButtons[7].setContentAreaFilled(false);
            editPriceSlotButtons[7].setBorder(null);
            editPricePanel.add(editPriceSlotButtons[7]);

            editPriceSlotButtons[8].setIcon(scaleIMG("./VMResources/slot9.png", 212, 108));
            editPriceSlotButtons[8].setOpaque(false);
            editPriceSlotButtons[8].setContentAreaFilled(false);
            editPriceSlotButtons[8].setBorder(null);
            editPricePanel.add(editPriceSlotButtons[8]);
            buttonPanel1.add("pricePanel",editPricePanel);

            buttonPanel1.add("blank", transacHistory);
            menu.show(buttonPanel1, "blank");
            currMode = "blank";
            add(buttonPanel1,BorderLayout.CENTER);
        }

        public void updateTransacHistory(ArrayList<Slot> productsSold){
            this.transacHistory.updateVMdisplay(productsSold);
        }


        public JButton getCollectBtn(){
            return collectBtn;
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
        // 2    replenish
        // 3    collect
        // 4    edit price

        if(choice == 1){
            if(buttonPanel.currMode.equals("restock")){
                buttonPanel.menu.show(buttonPanel.buttonPanel1, "blank");
                buttonPanel.currMode = "blank";
            } else {
                restockBtn.setIcon(scaleIMG("./VMResources/restockItemSelected.png",100,100));
                buttonPanel.menu.show(buttonPanel.buttonPanel1, "restockPanel");
                buttonPanel.currMode = "restock";
            }

        } else if (choice == 2 ) {
            if(buttonPanel.currMode.equals("replenish")){
                buttonPanel.menu.show(buttonPanel.buttonPanel1, "blank");
                buttonPanel.currMode = "blank";
            } else {
                replenishBtn.setIcon(scaleIMG("./VMResources/replenishMoneySelected.png",100,100));
                buttonPanel.menu.show(buttonPanel.buttonPanel1, "replenishPanel");
                buttonPanel.currMode = "replenish";
            }

        } else if (choice == 3) {
            if(buttonPanel.currMode.equals("collect")) {
                buttonPanel.menu.show(buttonPanel.buttonPanel1, "blank");
                buttonPanel.currMode = "blank";
            } else {
                collectBtn.setIcon(scaleIMG("./VMResources/collectMoneySelected.png",100,100));
                buttonPanel.menu.show(buttonPanel.buttonPanel1, "collectPanel");
                buttonPanel.currMode = "collect";
            }

        } else if (choice == 4) {
            if(buttonPanel.currMode.equals("edit price")) {
                buttonPanel.menu.show(buttonPanel.buttonPanel1, "blank");
                buttonPanel.currMode = "blank";
            } else {
                editPriceBtn.setIcon(scaleIMG("./VMResources/editPriceSelected.png",100,100));
                buttonPanel.menu.show(buttonPanel.buttonPanel1, "pricePanel");
                buttonPanel.currMode = "edit price";
            }

        } else if (choice == 0) {
            buttonPanel.menu.show(buttonPanel.buttonPanel1, "blank");
            buttonPanel.currMode = "blank";
        }
    }

    public void resetButtonPanelBtns(){
        restockBtn.setIcon(scaleIMG("./VMResources/restockItem.png",100,100));
        replenishBtn.setIcon(scaleIMG("./VMResources/replenishMoney.png",100,100));
        collectBtn.setIcon(scaleIMG("./VMResources/collectMoney.png",100,100));
        editPriceBtn.setIcon(scaleIMG("./VMResources/editPrice.png",100,100));
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

    public ButtonPanel getButtonPanel(){
        return this.buttonPanel;
    }

    public TextPanel getMoneyDisplay(){                 ////NNEWWWWWWWWWW
        return moneyDisplay;
    }

    public RegularItemPanel getRegItemPanel(){
        return regItemPanel;
    }

    public SpecialItemPanel getSpecialItemPanel(){
        return specialItemPanel;
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

    public JButton getRestockVMButtons(int index){
        return buttonPanel.restockSlotButtons[index];
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

    public JButton getEditPriceVMButtons(int index){
        return buttonPanel.editPriceSlotButtons[index];
    }

    /**
     *Gets the current mode of the button panel
     * @return String in relation to the mode.
     */
    public String getModeCurrMode() {
        return buttonPanel.currMode;
    }

    public String getCurrMode() {
        return this.currMode;
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