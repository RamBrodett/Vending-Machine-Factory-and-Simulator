import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class VMInterface extends JPanel{

    private ImageIcon bgIMG_VMInterface = new ImageIcon("./VMResources/VMInterfaceBG.png");
    private ItemPanel itemPanel = new ItemPanel();
    private HumanInterfacePanel huiPanel = new HumanInterfacePanel();
    private JButton InsertMoney = new JButton();
    private CardLayout menu = new CardLayout();
    VMInterface(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //gbc.insets = new Insets(60,65,122,15);
        gbc.weightx = .6000001;
        gbc.weighty = 1;
        gbc.fill = 1;
        add(itemPanel,gbc);
        //gbc.insets = new Insets(75,20,100,53);
        gbc.weightx = .3999999;
        gbc.weighty = 1;
        gbc.fill = 1;
        add(huiPanel,gbc);
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
        ImageIcon vnll = new ImageIcon("./VMResources/vanilla.png");
        ImageIcon chco = new ImageIcon("./VMResources/choco.png");
        ImageIcon mtch = new ImageIcon("./VMResources/matcha.png");
        ImageIcon chcp = new ImageIcon("./VMResources/chocochip.png");
        ImageIcon crel = new ImageIcon("./VMResources/creal.png");
        ImageIcon mxft = new ImageIcon("./VMResources/mixedFruit.png");
        ImageIcon rasp = new ImageIcon("./VMResources/raspberry.png");
        ImageIcon strw = new ImageIcon("./VMResources/strawberry.png");
        ImageIcon mngo = new ImageIcon("./VMResources/mango.png");


        ItemPanel(){
            //setOpaque(false);
            // to do: place the items in each spot and place slot num, price, calories and name pala
            setLayout(new GridLayout(3,3));

        }

    }
    protected class HumanInterfacePanel extends JPanel{

        private JPanel displayPanel = new JPanel();
        private JPanel buttonPanel = new JPanel();
        private JTextArea vmdisplay = new JTextArea(10,40);

        HumanInterfacePanel(){
            //fix here diko ma pwesto yung color blue na text area dapat nasa loob sya nung blue na display part
            setOpaque(false);
            setLayout(new GridLayout(2,1));
            displayPanel.setLayout(new GridLayout(1,1));
            vmdisplay.setLineWrap(true);
            vmdisplay.setBackground(new Color(98,174,239));
            vmdisplay.setSize(new Dimension(500,200));
            displayPanel.add(vmdisplay);
            add(displayPanel);

            buttonPanel.setBackground(Color.GREEN);
            add(buttonPanel);



        }
    }

}
