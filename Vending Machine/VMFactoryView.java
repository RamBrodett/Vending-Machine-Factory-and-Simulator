import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

public class VMFactoryView{
    private JFrame mainFrame;
    private JPanel mainPanel;

    public VMFactoryView(){
        this.mainFrame = new JFrame("YOU-G-ART FACTORY");
        this.mainPanel = new JPanel(new BorderLayout());
        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.setSize(1920,1080);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setVisible(true);

        this.mainFrame.add(mainPanel,BorderLayout.CENTER);
    }

    public void initializeMenu(){

        JPanel mainMenu = new JPanel(new GridLayout(2,1));
        this.mainPanel.add(mainMenu,BorderLayout.CENTER);

        JPanel panelTop = new JPanel(new GridLayout(2,1));
        JLabel iconYG = new JLabel();
        iconYG.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("mmLogo.png"))));
        iconYG.setHorizontalAlignment(SwingConstants.CENTER);
        panelTop.add(iconYG);

        JLabel mMLabel = new JLabel("YOU-G-ART VENDING MACHINE FACTORY");
        mMLabel.setFont(new Font("Arial",Font.BOLD,36));
        mMLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panelTop.add(mMLabel);
        mainMenu.add(panelTop);

        //EmptyBorder buttonBorder = new EmptyBorder(1,1,1,1);
        JPanel panelDown = new JPanel(new GridLayout(8,5));
        panelDown.setBackground(Color.GRAY);
        JButton createVM = new JButton("Create Vending Machine");
        JButton testVM = new JButton("Test Vending Machine");
        JButton exitFac = new JButton("EXIT");


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                JPanel grayPanel = new JPanel();
                grayPanel.setBackground(Color.GRAY);

                if (i == 1 && j == 2)
                    panelDown.add(createVM);
                else if (i == 3 && j == 2)
                    panelDown.add(testVM);
                else if (i == 5 && j == 2)
                    panelDown.add(exitFac);
                else
                    panelDown.add(grayPanel);
            }
        }

        mainMenu.add(panelDown);
    }


}
