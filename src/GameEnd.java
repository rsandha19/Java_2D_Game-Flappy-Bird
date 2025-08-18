import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameEnd extends JPanel {
    private Image backgroundImage;
    private ImageIcon playAgain;
    private ImageIcon mainMainu;
    private ImageIcon exit;

    private boolean multiPlayer;
    private int finalScore;
    private String gameResult="";
    GameEnd(boolean a,int score){
        this.multiPlayer=a;
        this.finalScore=score;
        if(multiPlayer){
            if(finalScore==-1){gameResult="Draw!";}
            else if(finalScore==1){
                gameResult="Player 1 Wins!";
            }else if(finalScore==2){
                gameResult="Player 2 Wins!";
            }
        }

        setPreferredSize(new Dimension(450,700));
        setLayout(null);

        //loading images
        String bgLink = "resources/endpage/finalBG.png";
        backgroundImage=new ImageIcon(bgLink).getImage();

        String playAgainLink="resources/endpage/playAgain.png";
        ImageIcon playAgainIcon=new ImageIcon(playAgainLink);

        String linkMainMenu=new String("resources/endpage/mainMenu.png");
        ImageIcon mainMenuIcon=new ImageIcon(linkMainMenu);

        String linkExit=new String("resources/endpage/exit.png");
        ImageIcon exitIcon=new ImageIcon(linkExit);


        JButton playAgainButton=new JButton(playAgainIcon);
        playAgainButton.setBounds(165,360,240,240);
        playAgainButton.setContentAreaFilled(false);
        playAgainButton.setBorderPainted(false);
        playAgainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent h) {
                FrameManager.switchTo(new SinglePlayerWindow(0));
            }
        });

        JButton mainMenuButton = new JButton(mainMenuIcon);
        mainMenuButton.setBounds(165,160,240,240);
        mainMenuButton.setContentAreaFilled(false);
        mainMenuButton.setBorderPainted(false);
        mainMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent g) {
                FrameManager.switchTo(new MainMenuWindow());
            }
        });

if(!multiPlayer){
    add(playAgainButton);
}
add(mainMenuButton);

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImage,0,0,550,700,this);
        g.setColor(Color.WHITE);

        g.setFont(new Font("Arial",Font.BOLD,42));

        if(!multiPlayer){
            g.drawString("Points Earned : "+finalScore,85,110);
        }else{
            g.drawString(gameResult,150,110);
        }
    }

}
