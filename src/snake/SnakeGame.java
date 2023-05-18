package snake;

import javax.swing.JFrame;

/**
 * @author arthu
 */
public class SnakeGame {

    public static void main(String[] args) {

        JFrame f = new JFrame();
        World world = new World();
        GraphicPanel panel = new GraphicPanel(world);
        f.setContentPane(panel);
        f.setVisible(true);
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addKeyListener(new CustomKeyListener(world));
    }

}
