import javax.swing.*;

public class FrameManager {
    public static JFrame F;
    public static void switchTo(JPanel panel){
        F.setContentPane(panel);
        F.revalidate();
        F.repaint();
        SwingUtilities.invokeLater(() -> panel.requestFocusInWindow());


    }
}
