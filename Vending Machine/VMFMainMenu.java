import javax.swing.*;
import java.awt.*;

public class VMFMainMenu extends JPanel {
    ImageIcon bgIMG= new ImageIcon("Vending Machine\\VMResources\\mainbg.png");
    private final JButton createVM = new JButton();
    private final JButton testVM = new JButton();
    private final JButton exitFactory = new JButton();

    VMFMainMenu(){
        setLayout(new GridLayout(2,1));

        JPanel upperPanel =  new JPanel();
        JPanel lowerPanel = new JPanel();

        upperPanel.setOpaque(false);
        lowerPanel.setOpaque(false);
        lowerPanel.setLayout(new GridLayout(4,1));
        JPanel panelFiller = new JPanel();
        JPanel panelFiller2 = new JPanel();
        JPanel panelFiller3 = new JPanel();
        JPanel panelFiller4 = new JPanel();
        JPanel panelFiller5 = new JPanel();
        JPanel panelFiller6 = new JPanel();
        JPanel panelFiller7 = new JPanel();

        panelFiller.setOpaque(false);
        panelFiller2.setOpaque(false);
        panelFiller3.setOpaque(false);
        panelFiller4.setOpaque(false);
        panelFiller5.setOpaque(false);
        panelFiller6.setOpaque(false);
        panelFiller7.setOpaque(false);

        createVM.setIcon(scaleIMG("Vending Machine\\VMResources\\crtBTnorm.png",275,99));
        createVM.setRolloverIcon(scaleIMG("Vending Machine\\VMResources\\crtBThover.png",275,99));
        createVM.setPressedIcon(scaleIMG("Vending Machine\\VMResources\\crtBTclicked.png",275,99));
        createVM.setBorder(null);
        createVM.setContentAreaFilled(false);
        createVM.setOpaque(false);

        testVM.setIcon(scaleIMG("Vending Machine\\VMResources\\testBTnorm.png",275,99));
        testVM.setRolloverIcon(scaleIMG("Vending Machine\\VMResources\\testBThover.png",275,99));
        testVM.setPressedIcon(scaleIMG("Vending Machine\\VMResources\\testBTclicked.png",275,99));
        testVM.setBorder(null);
        testVM.setContentAreaFilled(false);
        testVM.setOpaque(false);

        exitFactory.setIcon(scaleIMG("Vending Machine\\VMResources\\exitBTnorm.png",275,99));
        exitFactory.setRolloverIcon(scaleIMG("Vending Machine\\VMResources\\exitBThover.png",275,99));
        exitFactory.setPressedIcon(scaleIMG("Vending Machine\\VMResources\\exitBTclicked.png",275,99));
        exitFactory.setBorder(null);
        exitFactory.setContentAreaFilled(false);
        exitFactory.setOpaque(false);

        panelFiller4.add(createVM);
        panelFiller5.add(testVM);
        panelFiller6.add(exitFactory);

        lowerPanel.add(panelFiller);
        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new GridLayout(1,5));
        buttonPanel.add(panelFiller3);
        buttonPanel.add(panelFiller4);
        buttonPanel.add(panelFiller5);
        buttonPanel.add(panelFiller6);
        buttonPanel.add(panelFiller7);
        buttonPanel.setOpaque(false);

        lowerPanel.add(buttonPanel);

        lowerPanel.add(panelFiller2);

        add(upperPanel);
        add(lowerPanel);

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bgIMG.getImage(),0,0,getWidth(),getHeight(),null);
    }

    private ImageIcon scaleIMG(String strname, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(strname);
        Image raw = imageIcon.getImage();
        Image scaled = raw.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    public JButton getCreateVMButton() {
        return createVM;
    }
    public JButton getTestVMButton(){
        return testVM;
    }
    public JButton getExitFactoryButton(){
        return exitFactory;
    }

}
