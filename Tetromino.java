import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Tetromino {

    // Direction of rotation
    public static final int CLOCKWISE = 1;
    public static final int COUNTERCLOCKWISE = 2;

    // Direction of tetromino
    public static final int UP = 1;
    public static final int LEFT = 2;
    public static final int DOWN = 3;
    public static final int RIGHT = 4;

    // Change random integer into tetromino type
    private static final Map<Integer, Character> tetrominoMap = new HashMap<Integer, Character>() {
        private static final long serialVersionUID = 1L;
        {
            put(0, 'I');
            put(1, 'L');
            put(2, 'J');
            put(3, 'O');
            put(4, 'S');
            put(5, 'T');
            put(6, 'Z');
        }
    };

    private int direction = UP;
    private char type;
    private Color color;
    private int[][] body;
    private Point position = new Point(0, 0);

    public Tetromino(char tetromino) {
        this(tetromino, UP);
    }

    public Tetromino(char tetromino, int itsDirection) {
        direction = itsDirection;
        type = tetromino;
        switch (tetromino) {
            case 'I':
                color = new Color(139, 0, 0); // Dark Red
                switch (itsDirection) {
                    case UP:
                        body = new int[][] { { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 } };
                        break;
                    case LEFT:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 } };
                        break;
                    case DOWN:
                        body = new int[][] { { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 } };
                        break;
                    case RIGHT:
                        body = new int[][] { { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
                        break;
                }
                break;
            case 'L':
                color = new Color(128, 0, 128); // Purple
                switch (itsDirection) {
                    case UP:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 0, 0, 0 }, { 1, 1, 1, 0 } };
                        break;
                    case LEFT:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 1, 1, 0, 0 } };
                        break;
                    case DOWN:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 1, 0 } };
                        break;
                    case RIGHT:
                        body = new int[][] { { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 0, 0, 0 }, { 1, 0, 0, 0 } };
                        break;
                }
                break;
            case 'J':
                color = new Color(255, 255, 255); // White
                switch (itsDirection) {
                    case UP:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 1, 0 }, { 1, 1, 1, 0 } };
                        break;
                    case LEFT:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 1, 0 }, { 1, 1, 1, 0 } };
                        break;
                    case DOWN:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 1, 0, 0, 0 } };
                        break;
                    case RIGHT:
                        body = new int[][] { { 0, 0, 0, 0 }, { 1, 0, 0, 0 }, { 1, 0, 0, 0 }, { 1, 1, 0, 0 } };
                }
                break;
            case 'O':
                color = new Color(0, 0, 139); // Dark Blue
                // All directions refer to the same shape
                body = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 } };
                break;
            case 'S':
                color = new Color(0, 255, 0); // Green
                switch (itsDirection) {
                    case UP:
                    case DOWN:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 1, 1, 0 }, { 1, 1, 0, 0 } };
                        break;
                    case LEFT:
                    case RIGHT:
                        body = new int[][] { { 0, 0, 0, 0 }, { 1, 0, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 0, 0 } };
                        break;
                }
                break;
            case 'T':
                color = new Color(165, 42, 42); // Brown
                switch (itsDirection) {
                    case UP:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 1, 0, 0 }, { 1, 1, 1, 0 } };
                        break;
                    case LEFT:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 0, 0 } };
                        break;
                    case DOWN:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 0, 0 } };
                        break;
                    case RIGHT:
                        body = new int[][] { { 0, 0, 0, 0 }, { 1, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 0, 0, 0 } };
                        break;
                }
                break;
            case 'Z':
                color = new Color(0, 255, 255); // Cyan
                switch (itsDirection) {
                    case UP:
                    case DOWN:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 1, 0 } };
                        break;
                    case LEFT:
                    case RIGHT:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 1, 0, 0 }, { 1, 1, 0, 0 }, { 1, 0, 0, 0 } };
                }
                break;
        }
        if (itsDirection < UP || itsDirection > RIGHT)
            throw new IllegalArgumentException("The direction of the tetromino does not exists");
        if (color == null || color == null)
            throw new IllegalArgumentException("The tetromino of " + tetromino + " does not exists");
    }

    public static Tetromino getRandomTetronimo() {
        int randomType = (int) (Math.random() * 7);
        int randomDirection = (int) (Math.random() * 4) + 1;
        return new Tetromino(tetrominoMap.get(randomType), randomDirection);
    }

    public boolean isaBlock(int x, int y) {
        if (x < 0 || y < 0 || x > 3 || y > 3)
            return false;
        else
            return body[x][y] == 1;
    }

    public int getDirectionInt() {
        return direction;
    }

    public String getDirectionString() {
        String directionString = "";
        switch (direction) {
            case UP:
                directionString = "up";
                break;
            case LEFT:
                directionString = "left";
                break;
            case DOWN:
                directionString = "down";
                break;
            case RIGHT:
                directionString = "right";
                break;
        }
        return directionString;
    }

    public String getInfo() {
        return type + ", " + getDirectionString();
    }

    public char getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public Point getPosition() {
        return position;
    }

    public void rotate() {
        rotate(CLOCKWISE);
    }

    public void rotate(int directionOfRotation) {
        if (directionOfRotation == CLOCKWISE)
            direction = direction == UP ? RIGHT : direction - 1;
        else
            direction = direction == RIGHT ? UP : direction + 1;
        switch (type) {
            case 'I':
                color = new Color(139, 0, 0); // Dark Red
                switch (direction) {
                    case UP:
                        body = new int[][] { { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 } };
                        break;
                    case LEFT:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 } };
                        break;
                    case DOWN:
                        body = new int[][] { { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 } };
                        break;
                    case RIGHT:
                        body = new int[][] { { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
                        break;
                }
                break;
            case 'L':
                color = new Color(128, 0, 128); // Purple
                switch (direction) {
                    case UP:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 0, 0, 0 }, { 1, 1, 1, 0 } };
                        break;
                    case LEFT:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 1, 1, 0, 0 } };
                        break;
                    case DOWN:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 1, 0 } };
                        break;
                    case RIGHT:
                        body = new int[][] { { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 0, 0, 0 }, { 1, 0, 0, 0 } };
                        break;
                }
                break;
            case 'J':
                color = new Color(255, 255, 255); // White
                switch (direction) {
                    case UP:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 1, 0 }, { 1, 1, 1, 0 } };
                        break;
                    case LEFT:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 1, 1, 0, 0 } };
                        break;
                    case DOWN:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 1, 0 } };
                        break;
                    case RIGHT:
                        body = new int[][] { { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 0, 0, 0 }, { 1, 0, 0, 0 } };
                }
                break;
            case 'O':
                color = new Color(0, 0, 139); // Dark Blue
                // All directions refer to the same shape
                body = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 } };
                break;
            case 'S':
                color = new Color(0, 255, 0); // Green
                switch (direction) {
                    case UP:
                    case DOWN:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 1, 1, 0 }, { 1, 1, 0, 0 } };
                        break;
                    case LEFT:
                    case RIGHT:
                        body = new int[][] { { 0, 0, 0, 0 }, { 1, 0, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 0, 0 } };
                        break;
                }
                break;
            case 'T':
                color = new Color(165, 42, 42); // Brown
                switch (direction) {
                    case UP:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 1, 0, 0 }, { 1, 1, 1, 0 } };
                        break;
                    case LEFT:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 0, 0 } };
                        break;
                    case DOWN:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 0, 0 } };
                        break;
                    case RIGHT:
                        body = new int[][] { { 0, 0, 0, 0 }, { 1, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 0, 0, 0 } };
                        break;
                }
                break;
            case 'Z':
                color = new Color(0, 255, 255); // Cyan
                switch (direction) {
                    case UP:
                    case DOWN:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 1, 0 } };
                        break;
                    case LEFT:
                    case RIGHT:
                        body = new int[][] { { 0, 0, 0, 0 }, { 0, 1, 0, 0 }, { 1, 1, 0, 0 }, { 1, 0, 0, 0 } };
                }
                break;
        }
    }

    public void goUp() {
        --position.y;
    }

    public void goDown() {
        ++position.y;
    }

    public void goLeft() {
        --position.x;
    }

    public void goRight() {
        ++position.x;
    }
}
