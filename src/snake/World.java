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
    private int period = 100;
    private boolean isPlaying;

    private ArrayList<Listener> listenersList;

    public World() {
        this.nbCols = 20;
        this.nbLines = 15;
        snek = new Snake();
        generateFood();
        isPlaying = false;
        startTimer();
        listenersList = new ArrayList<>();

    }

    private void evolve() {
        // Move the snake
        snek.move();
        loopAroundBorders(snek);

        // Eat the food
        if (snek.eat(foodRow, foodCol)) {
            // Grow one segment to the snake
            snek.grow();
            // Generate a new target
            generateFood();
        }
        if (snek.selfCollides()) {
            togglePause();
            restartGame();
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

    private void restartGame() {
        snek = new Snake();
    }

    void togglePause() {
        if (isPlaying) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    private void startTimer() {

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                evolve();
            }

        }, delay, period);
        isPlaying = true;
    }

    private void stopTimer() {
        timer.cancel();
        isPlaying = false;
    }

    /**
     * Teleport to the other side of the world when the border is reached.
     *
     * @param snek
     */
    private void loopAroundBorders(Snake snek) {
        SnakePart currentHead = snek.getHeadPosition();
        if (currentHead.col > this.nbCols - 1) {
            snek.teleport(Snake.CardinalPoint.WEST, nbCols, nbLines);
        } else if (currentHead.col < 0) {
            snek.teleport(Snake.CardinalPoint.EAST, nbCols, nbLines);
        } else if (currentHead.row > this.nbLines - 1) {
            snek.teleport(Snake.CardinalPoint.NORTH, nbCols, nbLines);
        } else if (currentHead.row < 0) {
            snek.teleport(Snake.CardinalPoint.SOUTH, nbCols, nbLines);
        }
    }
}
