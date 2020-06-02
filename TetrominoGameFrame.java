import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class TetrominoGameFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private Dimension frameSize = new Dimension(450, 560);
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private JMenuBar menuBar = new JMenuBar();
    private JMenu gameMenu = new JMenu("游戏(G)");
    private JMenuItem startMenuItem = new JMenuItem("开始(S)", KeyEvent.VK_S);
    private JMenuItem pauseMenuItem = new JMenuItem("暂停(P)", KeyEvent.VK_P);
    private JMenuItem restartMenuItem = new JMenuItem("重新开始(R)", KeyEvent.VK_R);
    private JMenuItem exitMenuItem = new JMenuItem("退出(E)", KeyEvent.VK_E);
    private JMenu aboutMenu = new JMenu("关于(A)");
    private JMenuItem testMenuItem = new JMenuItem("测试(T)", KeyEvent.VK_T);
    private JMenuItem howMenuItem = new JMenuItem("玩法(H)", KeyEvent.VK_H);
    private JMenuItem authorMenuItem = new JMenuItem("作者(M)", KeyEvent.VK_M);

    private TetrominoGamePanel gamePanel = new TetrominoGamePanel();
    private TetrominoLabel currentTetrominoLabel = new TetrominoLabel('n', 0);
    private TetrominoLabel nextTetrominoLabel = new TetrominoLabel('n', 0);
    private JLabel startLabel = new JLabel("按下空格键开始/暂停");

    private JLabel currentTetrominoInformationLabel = new JLabel();
    private JLabel nextTetrominoInformationLabel = new JLabel();
    private JLabel scoreLabel = new JLabel();
    private JLabel tetrisLabel = new JLabel();

    private Timer timer;
    private int interval;
    private boolean running = false;
    private boolean over = false;

    public TetrominoGameFrame(String title, int speed) {
        super(title);

        interval = speed;

        setLayout(null);
        setSize(frameSize);
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        // setResizable(false);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_SPACE: // 按下了空格键
                        if (running && !over) {
                            pauseGame();
                        } else {
                            startGame();
                        }
                        break;
                    case KeyEvent.VK_UP:
                        gamePanel.rotate();
                        break;
                    case KeyEvent.VK_DOWN:
                        gamePanel.process();
                        break;
                    case KeyEvent.VK_LEFT:
                        gamePanel.goLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        gamePanel.goRight();
                        break;
                }
            }
        });

        startMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        pauseMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        restartMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));

        startMenuItem.setIcon(Sources.START_ICON);
        pauseMenuItem.setIcon(Sources.PAUSE_ICON);
        restartMenuItem.setIcon(Sources.RESTART_ICON);
        exitMenuItem.setIcon(Sources.EXIT_ICON);

        startMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        pauseMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseGame();
            }
        });
        restartMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitGame();
            }
        });

        testMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        howMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        authorMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));

        testMenuItem.setIcon(Sources.TEST_ICON);
        howMenuItem.setIcon(Sources.HOW_ICON);
        authorMenuItem.setIcon(Sources.AUTHOR_ICON);

        testMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTestFrame();
            }
        });
        howMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHowMessage();
            }
        });
        authorMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAuthorMessage();
            }
        });

        gameMenu.setMnemonic(KeyEvent.VK_G);
        gameMenu.add(startMenuItem);
        gameMenu.add(pauseMenuItem);
        gameMenu.add(restartMenuItem);
        gameMenu.addSeparator();
        gameMenu.add(exitMenuItem);

        aboutMenu.setMnemonic(KeyEvent.VK_A);
        aboutMenu.add(testMenuItem);
        aboutMenu.add(howMenuItem);
        aboutMenu.addSeparator();
        aboutMenu.add(authorMenuItem);

        menuBar.add(gameMenu);
        menuBar.add(aboutMenu);

        tetrisLabel.setIcon(Sources.TETRIS_ICON);

        currentTetrominoInformationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nextTetrominoInformationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        startLabel.setHorizontalAlignment(SwingConstants.CENTER);

        currentTetrominoInformationLabel.setFont(currentTetrominoInformationLabel.getFont().deriveFont(16.9f));
        nextTetrominoInformationLabel.setFont(currentTetrominoInformationLabel.getFont().deriveFont(16.9f));
        scoreLabel.setFont(currentTetrominoInformationLabel.getFont().deriveFont(19.9f));
        startLabel.setFont(startLabel.getFont().deriveFont(24.9f));

        menuBar.setBounds(0, 0, frameSize.width, 20);
        gamePanel.setBounds(1, 20, 250, 500);
        currentTetrominoInformationLabel.setBounds(250, 20, 200, 30);
        currentTetrominoLabel.setBounds(300, 50, 100, 100);
        nextTetrominoInformationLabel.setBounds(250, 150, 200, 30);
        nextTetrominoLabel.setBounds(300, 180, 100, 100);
        tetrisLabel.setBounds(247, 280, 190, 190);
        scoreLabel.setBounds(250, 470, 200, 40);
        startLabel.setBounds(0, 0, frameSize.width, frameSize.height);
        startLabel.setBackground(Color.GRAY);
        startLabel.setVisible(false);

        add(menuBar);
        add(gamePanel);
        add(currentTetrominoInformationLabel);
        add(currentTetrominoLabel);
        add(nextTetrominoInformationLabel);
        add(nextTetrominoLabel);
        add(scoreLabel);
        add(tetrisLabel);
        add(startLabel);

        timer = new Timer(interval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gamePanel.isOver()) {
                    process();
                } else {
                    gameOver();
                }
            }
        });

        currentTetrominoInformationLabel.setText("当前: ");
        nextTetrominoInformationLabel.setText("下一个: ");
        scoreLabel.setText("分数: ");

        pauseGame();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void process() {
        gamePanel.process();
        refreshInfo();
    }

    public void setSpeed(int speed) {
        if (speed > 0 && speed < 5000) {
            interval = speed;
            timer.setDelay(interval);
        }
    }

    public int getSpeed() {
        return interval;
    }

    public void refreshInfo() {
        currentTetrominoInformationLabel.setText("当前: " + gamePanel.getCurrentTetrominoInfo());
        currentTetrominoLabel.changeTo(gamePanel.getCurrentTetromino());
        nextTetrominoInformationLabel.setText("下一个: " + gamePanel.getNextTetrominoInfo());
        nextTetrominoLabel.changeTo(gamePanel.getNextTetromino());
        scoreLabel.setText("分数: " + gamePanel.getScore());
    }

    public void startGame() {
        currentTetrominoLabel.setVisible(true);
        nextTetrominoLabel.setVisible(true);
        currentTetrominoInformationLabel.setVisible(true);
        nextTetrominoInformationLabel.setVisible(true);
        tetrisLabel.setVisible(true);
        startLabel.setVisible(false);
        gamePanel.setVisible(true);
        timer.start();
        running = true;
    }

    public void pauseGame() {
        gamePanel.setVisible(false);
        currentTetrominoLabel.setVisible(false);
        nextTetrominoLabel.setVisible(false);
        currentTetrominoInformationLabel.setVisible(false);
        nextTetrominoInformationLabel.setVisible(false);
        tetrisLabel.setVisible(false);
        startLabel.setVisible(true);
        timer.stop();
        running = false;
    }

    public void restartGame() {
        timer.stop();
        remove(gamePanel);
        gamePanel = new TetrominoGamePanel();
        gamePanel.setBounds(1, 20, 250, 500);
        add(gamePanel);
        repaint();
        over = false;
        timer.start();
    }

    public void gameOver() {
        int answer;
        answer = JOptionPane.showConfirmDialog(this, "你失败了，得分" + gamePanel.getScore() + "，要不要再来一局？", "很遗憾",
                JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            exitGame();
        }
    }

    public void exitGame() {
        System.exit(0);
    }

    public void showTestFrame() {
        new TestTetrominoLabelFrame("测试");
    }

    public void showHowMessage() {
        JOptionPane.showMessageDialog(this, "控制每个 Tetromino 使他们布满一行，这一行就可以被消除，如果 Tetromino 到达了顶端，你就失败了。注: 空格键可以暂停/开始",
                "提示", JOptionPane.INFORMATION_MESSAGE);

    }

    public void showAuthorMessage() {
        JOptionPane.showMessageDialog(this, "Nope", "作者", JOptionPane.INFORMATION_MESSAGE);
    }
}