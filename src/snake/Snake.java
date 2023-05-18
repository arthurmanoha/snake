package snake;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * This object represents the snake that moves on the grid.
 *
 * @author arthu
 */
public class Snake {

    private enum CardinalPoint {
        NORTH, SOUTH, EAST, WEST
    }

    // First element is the head of the snake.
    private ArrayList<SnakePart> parts;

    private CardinalPoint currentHeading;

    private boolean mustGrowNextMove;

    public Snake() {
        parts = new ArrayList<>();
        parts.add(new SnakePart(0, 0, true));
        currentHeading = CardinalPoint.EAST;
        mustGrowNextMove = false;
    }

    public void paint(Graphics g, int squareSize) {
        for (SnakePart p : parts) {
            p.paint(g, squareSize);
        }
    }

    /**
     * Test if the requested position is occupied by the snake.
     *
     * @param line
     * @param col
     * @return true when one part of the snake exists at these coordinates.
     */
    public boolean contains(int rowTest, int colTest) {
        for (SnakePart p : parts) {
            if (p.isAt(rowTest, colTest)) {
                return true;
            }
        }
        return false;
    }

    public void receiveDirection(KeyEvent e) {

        switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
            currentHeading = CardinalPoint.WEST;
            break;
        case KeyEvent.VK_RIGHT:
            currentHeading = CardinalPoint.EAST;
            break;
        case KeyEvent.VK_UP:
            currentHeading = CardinalPoint.NORTH;
            break;
        case KeyEvent.VK_DOWN:
            currentHeading = CardinalPoint.SOUTH;
            break;
        }
    }

    void move() {

        if (mustGrowNextMove) {
            // Create a new snakePart where the last one currently is.
            SnakePart newPart = new SnakePart(0, 0, false);
            parts.add(newPart);
        }
        mustGrowNextMove = false;

        for (int rank = parts.size() - 1; rank >= 1; rank--) {
            // Each part will follow and replace another part.
            parts.get(rank).setPosition(parts.get(rank - 1));
        }
        // The head moves with currentHeading
        int dRow, dCol;
        switch (currentHeading) {
        case NORTH:
            dRow = -1;
            dCol = 0;
            break;
        case SOUTH:
            dRow = +1;
            dCol = 0;
            break;
        case EAST:
            dRow = 0;
            dCol = +1;
            break;
        case WEST:
            dRow = 0;
            dCol = -1;
            break;
        default:
            dRow = 0;
            dCol = 0;
            break;
        }
        SnakePart head = parts.get(0);
        head.setPosition(head.row + dRow, head.col + dCol);
    }

    public boolean eat(int foodRow, int foodCol) {
        SnakePart head = parts.get(0);
        return (head.row == foodRow && head.col == foodCol);
    }

    public void grow() {
        mustGrowNextMove = true;
        System.out.println("Must grow");
    }

}
