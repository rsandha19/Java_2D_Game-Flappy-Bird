import javax.swing.*;

public class StartClass {
    public static void main(String[] args){

        // main window dimensions
        int  height =700;
        int width=550;

        // title to pass into window
        String title="Flappy Bird : By Rohitpreet Singh";

        // creating frame
        JFrame baseFrame = new JFrame(title);

        // setting up a cose operation to create the upper buttons
        baseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // creating base with given dimensions
        baseFrame.setSize(width,height);

        // other layout settings
        baseFrame.setResizable(false);
        baseFrame.setLocationRelativeTo(null);

        //
        FrameManager.F= baseFrame;

        // now move to the main menu window;
        FrameManager.switchTo(new MainMenuWindow(width,height));

        baseFrame.pack();
        baseFrame.setVisible(true);



    }
}
