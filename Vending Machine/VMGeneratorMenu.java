import javax.swing.*;
import java.awt.*;

public class VMGeneratorMenu extends JPanel {

    private ImageIcon bgIMG_genMenu = new ImageIcon("./VMResources/genBg.png");
    private JFrame moneyInputFrame = new JFrame();
    private JButton regVM = new JButton();
    private JButton specVM = new JButton();
    private JButton back = new JButton();
    private JSpinner[] denominationButtons = new JSpinner[10];
    JButton exit = new JButton("EXIT");
    JButton set = new JButton("SET DENOMINATION");
    public JOptionPane optionPane = new JOptionPane(); // for popup messages


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

        JLabel label = new JLabel("Denomination Setter");
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        namingPanel.add(label);

        for(int i=0; i<10; i++){
            SpinnerNumberModel moneyRoll = new SpinnerNumberModel();
            moneyRoll.setMinimum(0);
            moneyRoll.setValue(0);
            denominationButtons[i] = new JSpinner(moneyRoll);
            denominationButtons[i].setPreferredSize(new Dimension(80,60));
            ((JSpinner.DefaultEditor) denominationButtons[i].getEditor()).getTextField().setEditable(false);
            moneyTags[i] = new JPanel();
            JLabel valueHolder = new JLabel();
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

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bgIMG_genMenu.getImage(),0,0,getWidth(),getHeight(),null);
    }

    private ImageIcon scaleIMG(String strname, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(strname);
        Image raw = imageIcon.getImage();
        Image scaled = raw.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    public JButton getRegVM(){
        return regVM;
    }
    public JButton getSpecVM(){
        return specVM;
    }
    public JButton getBack(){
        return back;
    }
    public int getValue(int index){
        return (int) denominationButtons[index].getValue();
    }
    public JButton getSet(){
        return set;
    }
    public JButton getExit(){
        return exit;
    }
    public JFrame getMoneyInputFrame(){
        return moneyInputFrame;
    }
    public void terminateMONEYINTERFACE(){
        moneyInputFrame.setVisible(false);
        moneyInputFrame.dispose();
    }
    public void re_setButton(){
        back.setEnabled(true);
        regVM.setEnabled(true);
        specVM.setEnabled(true);
    }
}
