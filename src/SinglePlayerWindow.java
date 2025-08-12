import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class SinglePlayerWindow extends JPanel implements ActionListener, KeyListener {

    private final Image background;
    private final Image birdSkin;
    private final Image topPipe;
    private final Image bottomPipe;

    boolean isDead =false;
boolean multiplayer=false;
    private int initialPoints;
    private int score=0;


    private final Font scoreFont=new Font("Arial",Font.BOLD,36);
    private final Color scoreColor=Color.white;

    private final int panelHeight=700;
    private final int panelWidth=550;

    private int center=300;


    private final Timer gameLoop;
    private final Timer pipeTimer ;

 private final ArrayList<Pipe> Pipes = new ArrayList<>();
 Bird bird=new Bird();
Points point=new Points();

    SinglePlayerWindow(){
    initialPoints=point.getPoints();

    setPreferredSize(new Dimension(panelWidth,panelHeight));
    setFocusable(true);
    requestFocusInWindow();
    addKeyListener(this);

    String backgroundLink = "resources/images/finalBG.png";
background=new ImageIcon(backgroundLink).getImage();

birdSkin=bird.birdImage;

String TopPipeLink= "resources/images/Top_pipe.png";
topPipe=new ImageIcon(TopPipeLink).getImage();

String BottomPipeLink= "resources/images/bottom_pipe.png";
bottomPipe=new ImageIcon(BottomPipeLink).getImage();

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
    Pipes.add(pipe);

}

public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.drawImage(background,0,0,panelWidth,panelHeight,this);
    g.drawImage(birdSkin, bird.birdXLoc, bird.birdYLoc- bird.birdHeight/2,bird.birdWidth,bird.birdHeight,this );

    for (Pipe pipe : Pipes) {
        if (pipe.pipeX + pipe.pipeWidth > 0) {
            g.drawImage(pipe.bottomPipe, pipe.pipeX, pipe.pipeBottomY, pipe.pipeWidth, pipe.pipeBottomHeight, this);
            g.drawImage(pipe.topPipe, pipe.pipeX, pipe.pipeTopY, pipe.pipeWidth, pipe.pipeTopHeight, this);

        }
    }
    // score
			g.setFont(scoreFont);
			g.setColor(scoreColor);
			g.drawString("SCORE : "+score,20,50);
}
 public void actionPerformed(ActionEvent e){
    move();
    repaint();
    if(isDead)return;

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
    bird.birdYLoc=bird.birdYLoc+ verticalVelocity;
    int gravity = 7;
    bird.birdYLoc= bird.birdYLoc+ gravity;
    bird.birdYLoc=Math.max(bird.birdYLoc,0);
    bird.birdYLoc=Math.min(bird.birdYLoc,645);
    bird.birdHead=bird.birdYLoc-((double) bird.birdHeight /2);
    bird.birdFoot=bird.birdYLoc+((double) bird.birdHeight /2);
    // changing location of each pipe and removing them from array when not needed
    for(int i=Pipes.size()-1;i>=0;i--) {
        Pipe p =Pipes.get(i);
        int velocityHorizontal = -3;
        p.pipeX+= velocityHorizontal;
        if (!p.passed && bird.birdXLoc > p.pipeX + p.pipeWidth) {
            p.passed = true;
            score++;
        }
        if(p.pipeX+p.pipeWidth<0) {
            Pipes.remove(i);
        }
        // checking for collision here with movement
        int marginX = 6;
        int marginY = 6;
        boolean withinPipeX = (bird.birdXLoc + marginX) + (bird.birdWidth - 2 * marginX) > p.pipeX
                && (bird.birdXLoc + marginX) < (p.pipeX + p.pipeWidth);
        boolean hitTopPipe = (bird.birdHead + marginY) < p.pipeTopHeight;
        boolean hitBottomPipe = (bird.birdFoot - marginY) > p.pipeBottomY;

        if (withinPipeX && (hitTopPipe || hitBottomPipe)) {
            gameOver();
            return;
        }
    }
}

    public void gameOver() {
        disableGame();
        Sound.playCollision();
        isDead=true;
        if(!multiplayer) {
            //FrameManager.switchTo(new GameEnd(0,score));
            initialPoints=initialPoints+score;
            point.setPoints(initialPoints);
            score=0;
            FrameManager.F.setSize(550,700);}
    }
    public void jumpAction() {
        int jump = -50;
        bird.birdYLoc=bird.birdYLoc+ jump;
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






