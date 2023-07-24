import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

public class VMFMainMenu extends JPanel {
    private JLabel nameBranding = new JLabel();
    private JLabel iconBranding = new JLabel();
    private JPanel upperPanel = new JPanel(); //Logo and Name Panel
    private JPanel lowerPanel = new JPanel(); // Buttons Panel
    private JButton createVM = new JButton("Create Vending Machine");
    private JButton testVM = new JButton("Test Vending Machine");
    private JButton exitFactory = new JButton("Exit");

    VMFMainMenu(){
        JPanel borderPanel = new JPanel(new GridLayout(2,1));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        //setLayout(new GridLayout(2,1));

        upperPanel.setLayout(new GridLayout(2,1));
        nameBranding.setText("YOU - G - ART VENDING MACHINE FACTORY");
        nameBranding.setFont(new Font("Arial",Font.BOLD,36));
        nameBranding.setHorizontalTextPosition(JLabel.CENTER);
        nameBranding.setHorizontalAlignment(JLabel.CENTER);
        nameBranding.setVerticalTextPosition(JLabel.CENTER);
        iconBranding.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("mmLogo.png"))));
        iconBranding.setHorizontalAlignment(SwingConstants.CENTER);
        iconBranding.setVerticalAlignment(JLabel.BOTTOM);
        upperPanel.setBackground(Color.LIGHT_GRAY);
        upperPanel.add(iconBranding);
        upperPanel.add(nameBranding);
        borderPanel.add(upperPanel);


        lowerPanel.setLayout(new GridLayout(8,5));
        lowerPanel.setBackground(new Color(44,69,85));
        createVM.setHorizontalTextPosition(JLabel.CENTER);
        createVM.setPreferredSize(new Dimension(300,25));
        testVM.setHorizontalTextPosition(JLabel.CENTER);
        createVM.setPreferredSize(new Dimension(300,25));
        exitFactory.setHorizontalTextPosition(JLabel.CENTER);
        exitFactory.setPreferredSize(new Dimension(300,25));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                JPanel Panel = new JPanel();
                Panel.setBackground(new Color(44,69,85));

                if (i == 1 && j == 2)
                    lowerPanel.add(createVM);
                else if (i == 3 && j == 2)
                    lowerPanel.add(testVM);
                else if (i == 5 && j == 2)
                    lowerPanel.add(exitFactory);
                else
                    lowerPanel.add(Panel);
            }
        }

        borderPanel.add(lowerPanel);
        add(borderPanel);
        setBackground(new Color(66,70,73));

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
