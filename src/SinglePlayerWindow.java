import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class SinglePlayerWindow extends JPanel implements ActionListener, KeyListener {
    private Image background;
    private Image birdSkin;
    private Image topPipe;
    private Image bottomPipe;

    boolean isDead =false;

    private int initialPoints;
    private int score=0;


    private Font scoreFont=new Font("Arial",Font.BOLD,36);
    private Color scoreColor=Color.white;

    private int panelHeight=700;
    private int panelWidth=550;

    private int verticalVelocity=-5;
    private int gravity=7;
    private int jump=-50;
    private int center=300;
    private int velocityHorizontal=-4;



    private Timer gameLoop;
    private Timer pipeTimer;

 ArrayList<Pipe> Pipes = new ArrayList<>();
Bird bird=new Bird();
Points point=new Points();
Sound sound=new Sound();
SinglePlayerWindow(){
    initialPoints=point.getPoints();

    setPreferredSize(new Dimension(panelWidth,panelHeight));
    setFocusable(true);
    requestFocusInWindow();
    addKeyListener(this);

    String backgroundLink =new String("resources/images/finalBG.png");
background=new ImageIcon(backgroundLink).getImage();

birdSkin=bird.birdImage;

String TopPipeLink=new String("resources/images/Top_pipe.png");
topPipe=new ImageIcon(TopPipeLink).getImage();

String BottomPipeLink=new String("resources/images/bottom_pipe.png");
bottomPipe=new ImageIcon(BottomPipeLink).getImage();

gameLoop=new Timer(1000/60,this);
gameLoop.start();

    pipeTimer=new Timer(1500,new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            placePipes();
    pipeTimer.setInitialDelay(2000);
pipeTimer.start();


}
public int getScore(){
    return score;
}
public void placePipes(){
    Random rand=new Random();
    int range=100;
    int minLimit=140;
    int maxLimit=560;

    int min = Math.max(center-range, minLimit);
    int max=Math.min(center+range,maxLimit);

    int gapLoc = rand.nextInt(max-min+1)+min;
    center=gapLoc;
    Pipe pipe=new Pipe(topPipe,bottomPipe,gapLoc);
    Pipes.add(pipe);

}

public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.drawImage(background,0,0,panelWidth,panelHeight,this);
    g.drawImage(birdSkin, bird.birdXLoc, bird.birdYLoc- bird.birdHeight/2,bird.birdWidth,bird.birdHeight,this );

for(int i=0;i<Pipes.size();i++) { if(Pipes.get(i).pipeX+Pipes.get(i).pipeWidth>0){
        g.drawImage(Pipes.get(i).bottomPipe,Pipes.get(i).pipeX,Pipes.get(i).pipeBottomY,Pipes.get(i).pipeWidth,Pipes.get(i).pipeBottomHeight,this);
        g.drawImage(Pipes.get(i).topPipe,Pipes.get(i).pipeX,Pipes.get(i).pipeTopY,Pipes.get(i).pipeWidth,Pipes.get(i).pipeTopHeight,this);

    }}
    // score
			g.setFont(scoreFont);
			g.setColor(scoreColor);
			g.drawString("SCORE : "+score,20,50);
}
public void actionPerformed(ActionEvent e){
    move();
    repaint();

}

}
