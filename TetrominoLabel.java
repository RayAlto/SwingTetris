import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class TetrominoLabel extends JLabel {
    private static final long serialVersionUID = 1L;

    public Tetromino tetromino;

    public TetrominoLabel() {
        // Use random tetromino
        this('r', 0);
    }

    public TetrominoLabel(char type, int itsDirection) {
        setBackground(Color.DARK_GRAY);
        setSize(100, 100);
        if (itsDirection == 0) {
            if (type == 'r') {
                tetromino = Tetromino.getRandomTetronimo();
            } else if (type == 'n') {
                tetromino = null;
            }
        } else {
            tetromino = new Tetromino(type, itsDirection);
        }
    }

    public void showRandomTetromino() {
        tetromino = Tetromino.getRandomTetronimo();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        if (tetromino != null) {
            g.setColor(tetromino.getColor());
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (tetromino.isaBlock(i, j)) {
                        g.fillRect(j * 25 + 1, i * 25 + 1, 23, 23);
                    }
                }
            }
        }
    }

    public void changeTo(Tetromino nextTetromino) {
        tetromino = nextTetromino;
        repaint();
    }

    public char getTetrominoType() {
        return tetromino.getType();
    }

    public String getTetrominoDirectionString() {
        return tetromino.getDirectionString();
    }

    public String getTetrominoInfo() {
        return tetromino.getInfo();
    }

    public void rotate(int directionOfRotation) {
        tetromino.rotate(directionOfRotation);
        repaint();
    }
}