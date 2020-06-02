import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class TestTetrominoLabelFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private Dimension frameSize = new Dimension(220, 315);
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    JTextArea messageTextArea = new JTextArea();
    TetrominoLabel tetrominoLabel = new TetrominoLabel();
    JLabel detailLabel = new JLabel();
    JButton changeButton = new JButton("换一个");

    public TestTetrominoLabelFrame(String title) {
        super(title);
        setSize(frameSize);
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        setIconImage(null);
        messageTextArea.setBounds(5, 5, 190, 80);
        tetrominoLabel.setBounds(50, 90, 100, 100);
        detailLabel.setBounds(0, 195, 200, 20);
        changeButton.setBounds(50, 225, 100, 30);
        messageTextArea.setLineWrap(true);
        messageTextArea.setEditable(false);
        messageTextArea.setEnabled(false);
        messageTextArea.setDisabledTextColor(Color.BLACK);
        messageTextArea.setBackground(new Color(240, 240, 240));
        messageTextArea.setText("下面的方框将会随机显示一个 Tetromino ，你可以通过单击“换一个”来刷新显示。方框下面是当前 Tetromino 的信息。");
        messageTextArea.setToolTipText("鼠标左键单击可以顺时针旋转，鼠标右键单击可以逆时针旋转。");
        detailLabel.setHorizontalAlignment(SwingConstants.CENTER);
        refreshDetailLabel();
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tetrominoLabel.showRandomTetromino();
                refreshDetailLabel();
            }
        });
        setLayout(null);
        setResizable(false);
        add(messageTextArea);
        add(tetrominoLabel);
        add(detailLabel);
        add(changeButton);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (e.getButton()) {
                    case MouseEvent.BUTTON1: // Mouse left button clicked
                        tetrominoLabel.rotate(Tetromino.CLOCKWISE);
                        break;
                    case MouseEvent.BUTTON3: // Mouse right button clicked
                        tetrominoLabel.rotate(Tetromino.COUNTERCLOCKWISE);
                        break;
                }
                refreshDetailLabel();
            }
        });
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void refreshDetailLabel() {
        detailLabel.setText(
                "形状: " + tetrominoLabel.getTetrominoType() + ", 方向: " + tetrominoLabel.getTetrominoDirectionString());
    }
}