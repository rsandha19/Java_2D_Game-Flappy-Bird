import java.awt.*;

public class Bird {
     Image birdImage;
     int birdWidth=55;
    int birdHeight=55;
    int birdXLoc=225;
    int birdYLoc=700/3;
    double birdHead;
    double birdFoot;
    Bird(){
EquippedSkin eq=new EquippedSkin();
birdImage=eq.getCurrentSkin();
    }
}
