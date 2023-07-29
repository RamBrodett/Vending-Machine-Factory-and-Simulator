import javax.swing.*;
import java.awt.*;

public class VMFTestMenu extends JPanel {

    ImageIcon bgIMG_testMenu = new ImageIcon("./VMResources/mainMenuBg.png");
    private final JButton simVM = new JButton();
    private final JButton maintenanceVM = new JButton();
    private final JButton back = new JButton();

    VMFTestMenu(){
        setLayout(new GridLayout(1,2));
        JPanel lPanel = new JPanel(new GridBagLayout());
        JPanel rPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        ImageIcon vmLogo = scaleIMG("./VMResources/mmLogo.png", 250, 250);
        JLabel vmLabel = new JLabel(vmLogo);

        lPanel.setOpaque(false);
        rPanel.setOpaque(false);

        simVM.setIcon(scaleIMG("./VMResources/rvmBTnorm.png",391,91));
        simVM.setRolloverIcon(scaleIMG("./VMResources/rvmBThover.png",391,91));
        simVM.setPressedIcon(scaleIMG("./VMResources/rvmBTclicked.png",391,91));
        simVM.setBorder(null);
        simVM.setContentAreaFilled(false);
        simVM.setOpaque(false);

        maintenanceVM.setIcon(scaleIMG("./VMResources/svmBTnorm.png",391,91));
        maintenanceVM.setRolloverIcon(scaleIMG("./VMResources/svmBThover.png",391,91));
        maintenanceVM.setPressedIcon(scaleIMG("./VMResources/svmBTclicked.png",391,91));
        maintenanceVM.setBorder(null);
        maintenanceVM.setContentAreaFilled(false);
        maintenanceVM.setOpaque(false);

        back.setIcon(scaleIMG("./VMResources/backBTnorm.png",391,91));
        back.setRolloverIcon(scaleIMG("./VMResources/backBThover.png",391,91));
        back.setPressedIcon(scaleIMG("./VMResources/backBTclicked.png",391,91));
        back.setBorder(null);
        back.setContentAreaFilled(false);
        back.setOpaque(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0, 0, 50, 0);
        lPanel.add(vmLabel, gbc);

        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        lPanel.add(simVM, gbc);

        gbc.gridy = 2;
        lPanel.add(maintenanceVM, gbc);

        gbc.insets = new Insets(80,0,0,0);
        gbc.gridy = 3;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        lPanel.add(back, gbc);


        JPanel placeHolder = new JPanel();          //TEMP PALCEHOLDER
        placeHolder.setBackground(Color.darkGray);
        placeHolder.setPreferredSize(new Dimension(500,800));
        rPanel.add(placeHolder);

        add(lPanel);
        add(rPanel);

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bgIMG_testMenu.getImage(),0,0,getWidth(),getHeight(),null);
    }

    private ImageIcon scaleIMG(String strname, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(strname);
        Image raw = imageIcon.getImage();
        Image scaled = raw.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    public JButton getSimulateVMButton() {
        return simVM;
    }
    public JButton getMaintenanceVMButton(){
        return maintenanceVM;
    }
    public JButton getBackButton(){
        return back;
    }
}
