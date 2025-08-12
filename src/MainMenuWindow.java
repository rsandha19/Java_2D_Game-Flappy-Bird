import javax.swing.*;
import java.awt.*;

public class MainMenuWindow extends JPanel {
    private Image background;
    private ImageIcon shopButtonIcon;
    private ImageIcon startButtonIcon;
    private ImageIcon multiplayerButtonIcon;
    private Image welcomeLogo;

    MainMenuWindow(int width,int height){
        setPreferredSize(new Dimension(width,height));
        setLayout(null);

        // load images and resources
        String backgroundLink=new String("resources/Main_menu/finalBG.png");
        background = new ImageIcon(backgroundLink).getImage();

        String shopButtonLink=new String("resources/Main_menu/shop.png");
        shopButtonIcon=new ImageIcon(shopButtonLink);

        String startButtonLink=new String("resources/Main_menu/start.png");
        startButtonIcon=new ImageIcon(startButtonLink);

        String multiplayerButtonLink=new String("resources/Main_menu/multiplayer.png");
        multiplayerButtonIcon=new ImageIcon(multiplayerButtonLink);

        String welcomeLink = new String("resources/Main_menu/welcome.png");
                welcomeLogo=new ImageIcon(welcomeLink).getImage();

        // creating each button

        JButton shopButton = new JButton(shopButtonIcon);
        shopButton.setBounds(185,200,180,180);
        shopButton.setBorderPainted(false);
        shopButton.setContentAreaFilled(false);

        JButton startButton=new JButton(startButtonIcon);
        startButton.setBounds(165,355,240,160);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.addActionListener(actionPerformed(b){
            FrameManager.switchTo(new SinglePlayerWindow());
        });

        JButton multiplayerButton=new JButton(multiplayerButtonIcon);
        multiplayerButton.setBounds(75,530,400,100);
        multiplayerButton.setBorderPainted(false);
        multiplayerButton.setContentAreaFilled(false);


        // add buttons onto the panel
        add(startButton);
        add(shopButton);
        add(multiplayerButton);

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background,0,0,550,700,this);
        g.drawImage(welcomeLogo,125,30,300,200,this);

    }
}
