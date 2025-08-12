import java.awt.*;

public class Pipe {
    boolean multi;
    int pipeX=460;
    int pipeTopY;
    int pipeBottomY;
    int pipeTopHeight;
    int pipeBottomHeight;
    int pipeWidth=60;
    Image topPipe;
    Image bottomPipe;
    boolean passed=false;
    // we only take images and the location of the gap in the pipes as an input.
    // then we use the num to calculate other attributes
    public Pipe( Image top,Image bottom,int num) {
        this.bottomPipe=bottom;
        this.topPipe=top;
        pipeTopY=0;
        pipeTopHeight=num-70;
        pipeBottomY=pipeTopHeight+140;
        pipeBottomHeight=700-pipeBottomY;
    }
}
