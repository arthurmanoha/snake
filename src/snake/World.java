package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author arthu
 */
public class World {

    private int nbCols, nbLines;
    private Snake snek;
    private int foodCol, foodRow;

    private Timer timer;
    private int delay = 0;
    private int period = 1000;

    private ArrayList<Listener> listenersList;

    public World() {
        this.nbCols = 15;
        this.nbLines = 10;
        snek = new Snake();
        generateFood();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                evolve();
            }

        }, delay, period);
        listenersList = new ArrayList<>();

    }

    private void evolve() {
        // Move the snake
        snek.move();
        // Eat the food
        if (snek.eat(foodRow, foodCol)) {
            // Grow one segment to the snake
            snek.grow();
        }

        updateListeners();
    }

    public int getWidth() {
        return this.nbCols;
    }

    public int getHeight() {
        return this.nbLines;
    }

    public void paint(Graphics g, int squareSize) {

        // Paint grid
        for (int row = 0; row < nbLines; row++) {
            for (int col = 0; col < nbCols; col++) {
                // Paint square at (row, col)
                g.setColor(Color.black);
                g.drawRect(col * squareSize, row * squareSize, squareSize, squareSize);
            }
        }

        // Paint snake
        snek.paint(g, squareSize);

        // Paint food
        g.setColor(Color.blue);
        g.fillOval(foodCol * squareSize, foodRow * squareSize, squareSize, squareSize);
    }

    /**
     * Find an empty square where we add one food.
     */
    // TODO: find a way to pick randomly among the empty spots.
    private void generateFood() {
        do {
            foodCol = new Random().nextInt(nbCols);
            foodRow = new Random().nextInt(nbLines);
        } while (snek.contains(foodRow, foodCol));
        System.out.println("Food generated at row " + foodRow + ", col " + foodCol);
    }

    public void receiveDirection(KeyEvent e) {
        snek.receiveDirection(e);
//        switch (e.getKeyCode()) {
//        case KeyEvent.VK_LEFT:
//            System.out.println("w LEFT");
//            break;
//        case KeyEvent.VK_RIGHT:
//            System.out.println("w RIGHT");
//            break;
//        case KeyEvent.VK_UP:
//            System.out.println("w UP");
//            break;
//        case KeyEvent.VK_DOWN:
//            System.out.println("w DOWN");
//            break;
//        }
    }

    void addListener(GraphicPanel aThis) {
        this.listenersList.add(aThis);
    }

    private void updateListeners() {
        for (Listener li : listenersList) {
            li.update();
        }
    }
}
