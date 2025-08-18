import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopWindow extends JPanel {

    private int
            balance;
    private Image background;
    private Image shopLogo;
    private Image equipped;
    private Image bird1;
    private Image bird2;
    private Image bird3;


    private Skins skins= new Skins();
    private Equipped eq=new Equipped();

    public ShopWindow() {

        Points points = new Points();
        balance = points.getPoints();
        setPreferredSize(new Dimension(550, 700));
        setLayout(null);
        String linkBG = "resources/images/shopBG.png";
        background = new ImageIcon(linkBG).getImage();

        String birdLink = "resources/images/birdFP.png";
        equipped = new ImageIcon(birdLink).getImage();

        String bird1Link = "resources/images/bird1.png";
        bird1 = new ImageIcon(bird1Link).getImage();

        String bird2Link = "resources/images/bird2.png";
        bird2 = new ImageIcon(bird2Link).getImage();

        String bird3Link = "resources/images/bird3.png";
        bird3 = new ImageIcon(bird3Link).getImage();

        shopLogo = new ImageIcon("resources/images/shopLogo.png").getImage();
        ImageIcon buyIcon = new ImageIcon("resources/images/buy.png");
        String backButtonLink = "resources/images/back.png";
        ImageIcon backButtonIcon = new ImageIcon(backButtonLink);

        JButton buyButton = new JButton(buyIcon);
        buyButton.setBorderPainted(false);
        buyButton.setContentAreaFilled(false);
        buyButton.setBounds(240, 280, 140, 60);
        buyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                skins.buyBird1();
                FrameManager.switchTo(new MainMenuWindow());
            }
        });

        JButton buyButton2 = new JButton(buyIcon);
        buyButton2.setContentAreaFilled(false);
        buyButton2.setBorderPainted(false);
        buyButton2.setBounds(240, 410, 140, 60);
        buyButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent h) {
                skins.buyBird2();
                FrameManager.switchTo(new MainMenuWindow());
            }
        });

        JButton buyButton3 = new JButton(buyIcon);
        buyButton3.setContentAreaFilled(false);
        buyButton3.setBorderPainted(false);
        buyButton3.setBounds(240, 540, 140, 60);
        buyButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent d) {
                skins.buyBird3();
                FrameManager.switchTo(new MainMenuWindow());
            }
        });

        ImageIcon equipIcon = new ImageIcon(new String("resources/images/equip.png"));

        JButton equipButton = new JButton(equipIcon);
        equipButton.setBorderPainted(false);
        equipButton.setContentAreaFilled(false);
        equipButton.setBounds(240, 150, 200, 80);
        equipButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent t) {
                eq.equipSkin(0);
                FrameManager.switchTo(new MainMenuWindow());

            }
        });
        JButton equipButton2 = new JButton(equipIcon);
        equipButton2.setContentAreaFilled(false);
        equipButton2.setBorderPainted(false);
        equipButton2.setBounds(240, 280, 200, 80);
        equipButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent t) {
                eq.equipSkin(1);
                FrameManager.switchTo(new MainMenuWindow());

            }
        });


        JButton equipButton3 = new JButton(equipIcon);
        equipButton3.setContentAreaFilled(false);
        equipButton3.setBorderPainted(false);
        equipButton3.setBounds(240, 410, 200, 80);
        equipButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent t) {
                eq.equipSkin(2);
                FrameManager.switchTo(new MainMenuWindow());

            }
        });


        JButton equipButton4 = new JButton(equipIcon);
        equipButton4.setContentAreaFilled(false);
        equipButton4.setBorderPainted(false);
        equipButton4.setBounds(240, 540, 200, 80);
        equipButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent t) {
                eq.equipSkin(3);
                FrameManager.switchTo(new MainMenuWindow());

            }
        });


        JButton backButton = new JButton(backButtonIcon);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setBounds(5,5,100,100);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                FrameManager.switchTo(new MainMenuWindow());
            }
        });
        add(backButton);

        if(skins.bird1Status()==0) {
            add(buyButton);
        }else {
            add(equipButton2);
        }
        if(skins.bird2Status()==0) {
            add(buyButton2);

        }else {
            add(equipButton3);
        }
        if(skins.bird3Status()==0) {
            add(buyButton3);
        }else {
            add(equipButton4);
        }

        add(equipButton);

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background,0,0,550,700,this);
        g.drawImage(equipped,90,140,80,80,this);
        g.drawImage(bird1,90,250,100,100,this);
        g.drawImage(bird2,90,380,100,100,this);
        g.drawImage(bird3,90,510,100,100,this);
        g.drawImage(shopLogo,50,-120,500,400,this);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        int costBird1 = 500;
        g.drawString("COST : " + costBird1, 80, 360);
        int costBird2 = 2000;
        g.drawString("COST : " + costBird2, 80, 490);
        int costBird3 = 10000;
        g.drawString("COST : " + costBird3, 80, 620);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Balance : " +balance, 150, 650);
    }

}
