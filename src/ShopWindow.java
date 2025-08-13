import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopWindow extends JPanel {

    private int balance;
    private Image background;
    private Image shopLogo;
    private Image equipped;
    private Image bird1;
    private Image bird2;
    private Image bird3;
    private ImageIcon backButtonIcon;
    private ImageIcon buyIcon;
    private ImageIcon equipIcon;


    private boolean ownBird1;
    private boolean ownBird2;
    private boolean ownBird3;
    private int costBird1=500;
    private int costBird2=2000;
    private int costBird3=10000;

    private Skins skins= new Skins();
    private Points points=new Points();
    private EquippedSkin eq=new EquippedSkin();
    private int EquippedIndex;
    public ShopWindow(){

        balance=points.getPoints();
        setPreferredSize(new Dimension(550,700));
        setLayout(null);
        String linkBG="resources/images/shopBG.png";
        background=new ImageIcon(linkBG).getImage();

        String birdLink ="resources/images/birdFP.png";
        equipped = new ImageIcon(birdLink).getImage();

        String bird1Link=new String("resources/images/bird1.png");
        bird1=new ImageIcon(bird1Link).getImage();

        String bird2Link=new String("resources/images/bird2.png");
        bird2=new ImageIcon(bird2Link).getImage();

        String bird3Link=new String("resources/images/bird3.png");
        bird3=new ImageIcon(bird3Link).getImage();

        shopLogo=new ImageIcon(new String("resources/images/shopLogo.png")).getImage();
        buyIcon=new ImageIcon(new String("resources/images/buy.png"));
        String backButtonLink=new String("resources/images/back.png");
        backButtonIcon=new ImageIcon(backButtonLink);

        JButton buyButton=new JButton(buyIcon);
        buyButton.setBorderPainted(false);
        buyButton.setContentAreaFilled(false);
        buyButton.setBounds(240,280,140,60);
        buyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                skins.buyBird1();
                FrameManager.switchTo(new MainMenuWindow());
            }
        });

        JButton buyButton2 = new JButton(buyIcon);
        buyButton2.setContentAreaFilled(false);
        buyButton2.setBorderPainted(false);
        buyButton2.setBounds(240,410,140,60);
        buyButton2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent h) {
                skins.buyBird2();
                FrameManager.switchTo(new MainMenuWindow());
            }
        });




    }



}
