import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

import javax.swing.JPanel;

public class TetrominoGamePanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private Color[][] blocks = new Color[10][24];
    private Tetromino currentTetromino;
    private Tetromino nextTetromino;

    private int score = 0;
    private boolean over = false;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 20; y++) {
                if (blocks[x][y + 4] != null) {
                    g.setColor(blocks[x][y + 4]);
                    g.fillRect(x * 25 + 1, y * 25 + 1, 23, 23);
                }
            }
        }
        if (currentTetromino != null) {
            g.setColor(currentTetromino.getColor());
            List<Point> blocksToPaint = TetrominoSaved.getRelativeBody(currentTetromino);
            for (int i = 0; i < blocksToPaint.size(); i++) {
                g.fillRect(blocksToPaint.get(i).x * 25 + 1, (blocksToPaint.get(i).y - 4) * 25 + 1, 23, 23);
            }
        }
    }

    public TetrominoGamePanel() {
        currentTetromino = Tetromino.getRandomTetronimo();
        nextTetromino = Tetromino.getRandomTetronimo();
    }

    public void process() {
        if (!goDown()) {
            refreshTetrominos();
            checkEliminateable();
            over = checkGameOver();
        }
        repaint();
    }

    public synchronized void refreshTetrominos() {
        List<Point> newBlocks = TetrominoSaved.getRelativeBody(currentTetromino);
        Color newBlocksColor = currentTetromino.getColor();
        for (int i = 0; i < newBlocks.size(); i++) {
            blocks[newBlocks.get(i).x][newBlocks.get(i).y] = newBlocksColor;
        }
        currentTetromino = nextTetromino;
        nextTetromino = Tetromino.getRandomTetronimo();
    }

    public synchronized boolean checkGameOver() {
        for (int x = 0; x < 10; x++) {
            if (blocks[x][3] != null)
                return true;
        }
        return false;
    }

    public synchronized void checkEliminateable() {
        boolean eliminateable;
        int lineCount = 0;
        for (int y = 23; y > 4; y--) {
            eliminateable = true;
            for (int x = 0; x < 10; x++) {
                if (blocks[x][y] == null) {
                    eliminateable = false;
                    break;
                }
            }
            if (eliminateable) {
                for (int x = 0; x < 10; x++) {
                    for (int tempY = y; tempY > 0; tempY--) {
                        blocks[x][tempY] = blocks[x][tempY - 1];
                    }
                }
                y++;
                lineCount++;
            }
        }
        if (lineCount > 0) {
            score += Math.pow(2, lineCount);
        }
    }

    public synchronized boolean goDown() {
        currentTetromino.goDown();
        if (conflict(currentTetromino)) {
            currentTetromino.goUp();
            return false;
        }
        return true;
    }

    public synchronized void rotate() {
        currentTetromino.rotate();
        if (conflict(currentTetromino)) {
            currentTetromino.rotate(Tetromino.COUNTERCLOCKWISE);
            return;
        }
        repaint();
    }

    public synchronized void goLeft() {
        currentTetromino.goLeft();
        if (conflict(currentTetromino)) {
            currentTetromino.goRight();
            return;
        }
        repaint();
    }

    public synchronized void goRight() {
        currentTetromino.goRight();
        if (conflict(currentTetromino)) {
            currentTetromino.goLeft();
            return;
        }
        repaint();
    }

    public boolean conflict(Tetromino tetrominoToCheck) {
        List<Point> blocksToCheck = TetrominoSaved.getRelativeBody(tetrominoToCheck);
        for (int i = 0; i < blocksToCheck.size(); i++) {
            if (isBlock(blocksToCheck.get(i).x, blocksToCheck.get(i).y))
                return true;
        }
        return false;
    }

    public boolean isBlock(int x, int y) {
        if (x >= 0 && x < 10 && y >= 0 && y < 24) {
            return blocks[x][y] != null; // 是否为已经落下的方块
        } else {
            if (x < 0 || x > 9 || y >= 24) {
                return true; // 是墙壁
            }
        }
        return false;
    }

    public Tetromino getCurrentTetromino() {
        return currentTetromino;
    }

    public Tetromino getNextTetromino() {
        return nextTetromino;
    }

    public String getCurrentTetrominoInfo() {
        return currentTetromino.getInfo();
    }

    public String getNextTetrominoInfo() {
        return nextTetromino.getInfo();
    }

    public int getScore() {
        return score;
    }

    public boolean isOver() {
        return over;
    }

}