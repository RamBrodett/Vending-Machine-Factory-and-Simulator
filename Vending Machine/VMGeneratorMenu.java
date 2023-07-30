import javax.swing.*;
import java.awt.*;

public class VMGeneratorMenu extends JPanel {

    ImageIcon bgIMG_genMenu = new ImageIcon("./VMResources/genBg.png");
    private JButton regVM = new JButton();
    private JButton specVM = new JButton();
    private JButton back = new JButton();


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
}
