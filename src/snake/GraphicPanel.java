package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author arthu
 */
public class GraphicPanel extends JPanel implements Listener {

    private World w;
    private int rowWidth = 30;

    public GraphicPanel(World world) {
        this.w = world;
        w.addListener(this);
        this.setPreferredSize(new Dimension(rowWidth * w.getWidth(), rowWidth * w.getHeight()));
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, rowWidth * w.getWidth(), rowWidth * w.getHeight());

        // Paint the world
        w.paint(g, rowWidth);
    }

    @Override
    public void update() {
        repaint();
    }

}
