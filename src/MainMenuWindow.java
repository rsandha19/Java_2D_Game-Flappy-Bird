import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuWindow extends JPanel {
    private Image background;
    private ImageIcon shopButtonIcon;
    private ImageIcon startButtonIcon;
    private ImageIcon multiplayerButtonIcon;
    private Image welcomeLogo;

    private int OPTION_CHOSEN=0;
    MainMenuWindow(){
        setPreferredSize(new Dimension(550,700));
        setLayout(null);

        // load images and resources
        String backgroundLink= "resourc" +
                "es/Main_menu/finalBG.png";
        background = new ImageIcon(backgroundLink).getImage();

        String shopButtonLink= "resources/Main_menu/shop.png";
        shopButtonIcon=new ImageIcon(shopButtonLink);

        String startButtonLink= "resources/Main_menu/start.png";
        startButtonIcon=new ImageIcon(startButtonLink);

        String multiplayerButtonLink= "resources/Main_menu/multiplayer.png";
        multiplayerButtonIcon=new ImageIcon(multiplayerButtonLink);

        String welcomeLink = "resources/Main_menu/welcome.png";
                welcomeLogo=new ImageIcon(welcomeLink).getImage();

        // creating each button

        JButton shopButton = new JButton(shopButtonIcon);
        shopButton.setBounds(185,200,180,180);
        shopButton.setBorderPainted(false);
        shopButton.setContentAreaFilled(false);
        shopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                FrameManager.switchTo(new ShopWindow());
            }
        });

        JButton startButton=new JButton(startButtonIcon);
        startButton.setBounds(165,355,240,160);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.addActionListener(e -> {

            FrameManager.switchTo(new SinglePlayerWindow(0));
        });


        JButton multiplayerButton=new JButton(multiplayerButtonIcon);
        multiplayerButton.setBounds(75,530,400,100);
        multiplayerButton.setBorderPainted(false);
        multiplayerButton.setContentAreaFilled(false);
multiplayerButton.addActionListener( new ActionListener(){
    public void actionPerformed(ActionEvent g)
{
    FrameManager.switchTo(new MultiplyerWindow());
FrameManager.F.pack();
FrameManager.F.setLocationRelativeTo(null);}
});

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
    public int getOption(){
        return OPTION_CHOSEN;
    }
}
