package snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author arthu
 */
public class CustomKeyListener implements KeyListener {

    private World w;

    public CustomKeyListener(World newWorld) {
        w = newWorld;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
        case KeyEvent.VK_RIGHT:
        case KeyEvent.VK_UP:
        case KeyEvent.VK_DOWN:
            w.receiveDirection(e);
            break;
        case KeyEvent.VK_P:
            w.togglePause();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
