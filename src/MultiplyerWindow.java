import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.Color.black;

public class MultiplyerWindow extends JPanel implements KeyListener {
    private SinglePlayerWindow player1;
    private SinglePlayerWindow player2;

    private javax.swing.Timer deathChecker;
    public MultiplyerWindow(){
        setPreferredSize(new Dimension(900,700));
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));

        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(10,700));
        spacer.setBackground(black);

        player1=new SinglePlayerWindow(1);
        player2=new SinglePlayerWindow(2);

        player1.restartGameLoop();
        player2.restartGameLoop();
        player1.removeKeyListener(player1);
        player2.removeKeyListener(player2);
        add(player1);
        add(spacer);

        add(player2);

        deathChecker=new javax.swing.Timer(1000,e ->{
            if(player1.isDead && player2.isDead){
                int winner;
                if(player1.getScore()>player2.getScore())
                {
                    winner=1;
                }else if(player2.getScore()>player1.getScore()){
                    winner=2;
                }else{
                    winner=-1;
                }
                FrameManager.switchTo(new GameEnd(true,winner));
                FrameManager.F.setSize(550,700);
                deathChecker.stop();
            }
        });

        deathChecker.start();
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);


    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player1.jumpAction();
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            player2.jumpAction();
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
