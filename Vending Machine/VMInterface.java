import javax.swing.*;
import java.awt.*;
import java.net.InetSocketAddress;
import java.util.Set;



public class VMInterface extends JPanel{

    private ImageIcon bgIMG_VMInterface = new ImageIcon("./VMResources/VMInterfaceBG.png");
    private RegularItemPanel regItemPanel = new RegularItemPanel();
    private SpecialItemPanel specialItemPanel = new SpecialItemPanel();
    private CardLayout itemPanel;
    private JPanel itemPanelContainer;
    private String currMode = "Regular";            //TRACKER FOR WHICH MODE THE LAYOUT IS CURRENTLY ON
    private ButtonPanel buttonPanel = new ButtonPanel();
    private TextPanel textPanel = new TextPanel();
    private JButton toggleSpecial = new JButton();
    private JButton InsertMoney = new JButton();
    private JButton back = new JButton();

    VMInterface(){
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
        gbc.insets = new Insets(85,76, 127,30);
        gbc.gridheight = 4;         // make the itemPanel span all three rows
        gbc.weightx = .81;          // adjust the horizontal size for the itemPanel (0.6 means 60% of available space)
        gbc.weighty = .9;
        add(itemPanelContainer, gbc);


        gbc.insets = new Insets(0,0,570,0);         // textPanel
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.weightx = .18;
        gbc.weighty = .9;
        add(textPanel, gbc);

        InsertMoney.setPreferredSize(new Dimension(625,100));
        InsertMoney.setIcon(scaleIMG("./VMResources/moneyInsertBT.png",625,100));
        InsertMoney.setPressedIcon(scaleIMG("./VMResources/moneyInsertBTclicked.png",625,100));
        InsertMoney.setBorder(null);
        InsertMoney.setContentAreaFilled(false);
        InsertMoney.setOpaque(false);
        gbc.insets = new Insets(0,0,50,0);          // InsertMoney button
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.weightx = .18;
        gbc.weighty = .9;
        add(InsertMoney, gbc);

        gbc.insets = new Insets(375,0,0,0);         // buttonPanel
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

        toggleSpecial.setIcon(scaleIMG("./VMResources/crtBTnorm.png",391,91));              //EDIT IMAGES!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        toggleSpecial.setRolloverIcon(scaleIMG("./VMResources/crtBThover.png",391,91));
        toggleSpecial.setPressedIcon(scaleIMG("./VMResources/crtBTclicked.png",391,91));
        toggleSpecial.setBorder(null);
        toggleSpecial.setContentAreaFilled(false);
        toggleSpecial.setOpaque(false);
        gbc.insets = new Insets(890,0, 0,0);         // toggle button
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.weightx = .18;
        gbc.weighty = .9;
        add(toggleSpecial, gbc);
    }




    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bgIMG_VMInterface.getImage(),0,0,getWidth(),getHeight(),null);
    }

    private ImageIcon scaleIMG(String strname, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(strname);
        Image raw = imageIcon.getImage();
        Image scaled = raw.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

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

    protected class TextPanel extends JPanel {

        private JTextArea vmdisplay = new JTextArea(10, 40);
        private JScrollPane scrollPane = new JScrollPane(vmdisplay);

        public TextPanel() {
            setLayout(new BorderLayout());

            vmdisplay.setLineWrap(true);
            vmdisplay.setBackground(new Color(98, 174, 239));
            scrollPane.setPreferredSize(new Dimension(625, 300));

            scrollPane.setBackground(new Color(98, 174, 239));
            scrollPane.setBorder(null);

            add(scrollPane);
        }
    }

    private class ButtonPanel extends JPanel {

        private JPanel buttonPanel1 = new JPanel();
        private JPanel moneyPanel = new JPanel();
        private JPanel regbuttonPanel = new JPanel();
        private JPanel specbuttonPanel = new JPanel();
        private JButton specialButton = new JButton();
        private CardLayout menu = new CardLayout();

        public ButtonPanel() {
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(625, 300));
            setBackground(Color.GREEN);

            buttonPanel1.setLayout(menu);

            regbuttonPanel.setBackground(Color.BLUE);
            buttonPanel1.add("buttonsMode",regbuttonPanel);
            moneyPanel.setBackground(Color.GREEN);
            buttonPanel1.add("moneyMode",moneyPanel);
            specbuttonPanel.setBackground(Color.GRAY);
            buttonPanel1.add("SpecialMode",specbuttonPanel);

            add(buttonPanel1,BorderLayout.CENTER);
        }
    }

    public void updateButtonPanel(int choice){
        if(choice == 1){
            buttonPanel.menu.show(buttonPanel.buttonPanel1, "buttonMode");
        } else if (choice == 2 ) {
            buttonPanel.menu.show(buttonPanel.buttonPanel1, "moneyMode");
        } else if (choice == 3) {
            buttonPanel.menu.show(buttonPanel.buttonPanel1, "SpecialMode");
        }
    }

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

    public JButton getInsertMoney(){
        return InsertMoney;
    }

   public JButton getBack(){
        return back;
   }

   public JButton getToggleSpecial(){
        return toggleSpecial;
    }

    public ButtonPanel buttonPanel(){
        return buttonPanel;
    }


}