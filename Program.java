import javax.swing.UIManager;

public class Program {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new TetrominoGameFrame("俄罗斯方块", 500);
    }
}