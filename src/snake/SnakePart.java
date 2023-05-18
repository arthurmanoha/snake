package snake;

import java.awt.Color;
import java.awt.Graphics;

// Single, one-square part of the snake
public class SnakePart {

    public int row, col;
    public boolean isHead;

    public SnakePart(int newRow, int newCol, boolean newIsHead) {
        row = newRow;
        col = newCol;
        isHead = newIsHead;
    }

    boolean isAt(int rowTest, int colTest) {
        return rowTest == row && colTest == col;
    }

    public void setPosition(int newRow, int newCol) {
        row = newRow;
        col = newCol;
    }

    public void setPosition(SnakePart other) {
        row = other.row;
        col = other.col;
    }

    public void paint(Graphics g, int squareSize) {

        g.setColor(Color.red);
        g.fillOval(squareSize * col, squareSize * row, squareSize, squareSize);
    }

}
