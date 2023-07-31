import javax.swing.*;
import java.awt.*;
import java.net.InetSocketAddress;
import java.util.Set;



public class VMInterface extends JPanel{

    private ImageIcon bgIMG_VMInterface = new ImageIcon("./VMResources/VMInterfaceBG.png");
    private ItemPanel itemPanel = new ItemPanel();
    private ButtonPanel buttonPanel = new ButtonPanel();
    private TextPanel textPanel = new TextPanel();
    private JButton InsertMoney = new JButton();
    private CardLayout menu = new CardLayout();



    VMInterface(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

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
        gbc.insets = new Insets(80,76, 127,0);
        gbc.gridheight = 4;         // make the itemPanel span all three rows
        gbc.weightx = .81;          // adjust the horizontal size for the itemPanel (0.6 means 60% of available space)
        gbc.weighty = .9;
        add(itemPanel, gbc);


        gbc.insets = new Insets(0,0,570,0);         // textPanel
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.weightx = .18;
        gbc.weighty = .9;
        add(textPanel, gbc);

        gbc.insets = new Insets(0,0,50,0);          // InsertMoney button
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.weightx = .18;
        gbc.weighty = .9;
        InsertMoney.setPreferredSize(new Dimension(625,100));
        InsertMoney.setIcon(scaleIMG("./VMResources/moneyInsertBT.png",625,100));
        InsertMoney.setBorder(null);
        InsertMoney.setContentAreaFilled(false);
        InsertMoney.setOpaque(false);
        add(InsertMoney, gbc);

        gbc.insets = new Insets(375,0,0,0);         // buttonPanel
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.weightx = .18;
        gbc.weighty = .9;
        add(buttonPanel, gbc);

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

    protected class ItemPanel extends JPanel{
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

        ItemPanel(){
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

            JPanel vnllPanel = createImagePanel(vnll, "Vanilla", 999, 999);
            JPanel chcoPanel = createImagePanel(chco, "Chocolate", 999, 999);
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

    protected class ButtonPanel extends JPanel {

        private JPanel buttonPanel = new JPanel();
        private JPanel moneyPanel = new JPanel();
        private JPanel regbuttonPanel = new JPanel();
        private JPanel specbuttonPanel = new JPanel();

        public ButtonPanel() {
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(625, 300));
            setBackground(Color.GREEN);

            buttonPanel.setLayout(menu);

            buttonPanel.add(moneyPanel);

            buttonPanel.add(regbuttonPanel);

            buttonPanel.add(specbuttonPanel);

            add(buttonPanel,BorderLayout.CENTER);
        }
    }


}
