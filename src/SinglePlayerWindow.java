import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class SinglePlayerWindow extends JPanel implements ActionListener, KeyListener {

    private  Image background;
    private Image birdSkin;
    private  Image topPipe;
    private Image bottomPipe;

    private Image bird1;
    private Image bird2;
    private Image bird3;

    boolean isDead =false;
boolean isMultiplayerPlayer1=false;
boolean isMultiplayer=false;


    private int initialPoints;
    private int score=0;


    private final Font scoreFont=new Font("Arial",Font.BOLD,36);
    private final Color scoreColor=Color.white;

    private final int panelHeight=700;
    private final int panelWidth=550;

    private int birdWidth=55;
    private int birdHeight=55;
    private int birdX=panelWidth/5;
    private int birdY=panelHeight/3;

    private double birdHead;
    private double birdFoot;

    private int verticalVelocity;
    private int gravity=7;
    private int jump=-50;
    private int center=300;
    private int velocityX=-4;
    {
        verticalVelocity = -5;
    }


    private Timer gameLoop;
    private Timer pipeTimer ;

    private Equipped eq=new Equipped();
    private Sound sounds=new Sound();
    private Points points=new Points();

 private final ArrayList<Pipe> pipes = new ArrayList<>();

    SinglePlayerWindow(int i){if(i==1){
        isMultiplayer=true;
    }
    initialPoints=points.getPoints();

    setPreferredSize(new Dimension(panelWidth,panelHeight));
    setFocusable(true);
    requestFocusInWindow();
    addKeyListener(this);

        // creating links and importing image objects.
        String backGroundLink= "resources/images/finalBG.png";
        String birdLink= "resources/images/birdFP.png";
        String topPipeLink= "resources/images/Top_pipe.png";
        String bottomPipeLink = "resources/images/bottom_pipe.png";

        // loading images

        String bird1Link= "resources/images/bird1.png";
        bird1=new ImageIcon(bird1Link).getImage();
        String bird2Link= "resources/images/bird2.png";
        bird2=new ImageIcon(bird2Link).getImage();
        String bird3Link= "resources/images/bird3.png";
        bird3=new ImageIcon(bird3Link).getImage();
        topPipe=new ImageIcon(topPipeLink).getImage();
        bottomPipe=new ImageIcon(bottomPipeLink).getImage();
        background=new ImageIcon(backGroundLink).getImage();


        if(eq.getEquippedIndex()==0){
            birdSkin=new ImageIcon(birdLink).getImage();
        }else if(eq.getEquippedIndex()==1){
            birdSkin=bird1;
        }else if(eq.getEquippedIndex()==2){
            birdSkin=bird2;
        }else{
            birdSkin=bird3;
        }
        gameLoop=new Timer(1000/60,this);
gameLoop.start();

pipeTimer=new javax.swing.Timer(1500, _ ->placePipes()) ;
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
    pipes.add(pipe);

}

public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.drawImage(background,0,0,panelWidth,panelHeight,this);
    g.drawImage(birdSkin, birdX, birdY-birdHeight/2,birdWidth,birdHeight,this );

    for(int i=0;i<pipes.size();i++) { if(pipes.get(i).pipeX+pipes.get(i).pipeWidth>0){
        g.drawImage(pipes.get(i).bottomPipe,pipes.get(i).pipeX,pipes.get(i).pipeBottomY,pipes.get(i).pipeWidth,pipes.get(i).pipeBottomHeight,this);
        g.drawImage(pipes.get(i).topPipe,pipes.get(i).pipeX,pipes.get(i).pipeTopY,pipes.get(i).pipeWidth,pipes.get(i).pipeTopHeight,this);

    }}
    // score
			g.setFont(scoreFont);
			g.setColor(scoreColor);
			g.drawString("SCORE : "+score,20,50);
}
 public void actionPerformed(ActionEvent e){
    move();
    repaint();
    if(isDead) {
        return;
    }
}
public void restartGameLoop(){
    if(gameLoop!=null) gameLoop.start();
    if(pipeTimer!=null) pipeTimer.start();
}
public void disableGame(){
    gameLoop.stop();
    pipeTimer.stop();

}
public void move(){
    int verticalVelocity = -5;
    birdY=birdY+ verticalVelocity;
    int gravity = 7;
    birdY=birdY+ gravity;
    birdY=Math.max(birdY,0);
    birdY=Math.min(birdY,645);
    birdHead=birdY-((double)birdHeight /2);
    birdFoot=birdY+((double)birdHeight /2);
    // changing location of each pipe and removing them from array when not needed
    for(int i=pipes.size()-1;i>=0;i--) {
        Pipe p =pipes.get(i);
        int velocityHorizontal = -3;
        p.pipeX+= velocityHorizontal;
        if (!p.passed && birdX> p.pipeX + p.pipeWidth) {
            p.passed = true;
            score++;
        }
        if(p.pipeX+p.pipeWidth<0) {
            pipes.remove(i);
        }
        // checking for collision here with movement
        int marginX = 6;
        int marginY = 6;
        boolean withinPipeX = (birdX + marginX) + (birdWidth - 2 * marginX) > p.pipeX
                && (birdX + marginX) < (p.pipeX + p.pipeWidth);
        boolean hitTopPipe = (birdHead + marginY) < p.pipeTopHeight;
        boolean hitBottomPipe = (birdFoot - marginY) > p.pipeBottomY;

        if (withinPipeX && (hitTopPipe || hitBottomPipe)) {
            gameOver();
            return;
        }
    }
}

    public void gameOver() {
        disableGame();
        sounds.playCollision();
        isDead=true;
        if(!isMultiplayer) {
            FrameManager.switchTo(new GameEnd(false,score));
            initialPoints=initialPoints+score;
            points.setPoints(initialPoints);
            score=0;
            FrameManager.F.setSize(550,700);}
    }
    public void jumpAction() {
        int jump = -50;
        birdY=birdY+ jump;
        Sound.playJumpSound();
    }
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE) {
            jumpAction();
        }
    }
    public void keyTyped(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {};

}






