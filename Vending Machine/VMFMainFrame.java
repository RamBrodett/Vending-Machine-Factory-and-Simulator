import javax.swing.*;
import java.awt.*;

public class VMFMainFrame{
    JFrame mainFrame;
    JPanel cardPanel = new JPanel();
    CardLayout menu = new CardLayout();
    VMFMainMenu mainMenu = new VMFMainMenu();

    VMFMainFrame(){
        this.mainFrame = new JFrame("YOU-G-ART FACTORY");
        mainFrame.setSize(1920,1080);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        cardPanel.setSize(1920,1080);
        cardPanel.setLayout(menu);
        this.mainFrame.add(cardPanel);


        // To switch between displays
        cardPanel.add("mainMenu", mainMenu);


        menu.show(cardPanel,"mainMenu");



    }



}
