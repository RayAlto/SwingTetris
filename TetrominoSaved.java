import java.util.ArrayList;
import java.util.List;

import java.awt.Point;
import java.awt.Color;

public class TetrominoSaved {
    public List<Point> body;
    public Point position;
    public Color color;

    TetrominoSaved(Tetromino tetromino) {
        body = new ArrayList<Point>();
        position = tetromino.getPosition();
        color = tetromino.getColor();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tetromino.isaBlock(i, j))
                    body.add(new Point(j, i));
            }
        }
    }

    public List<Point> getRelativeBody() {
        List<Point> relativeBody = new ArrayList<Point>();
        for (int i = 0; i < body.size(); i++)
            relativeBody.add(new Point(position.x + body.get(i).x, position.y - (4 - body.get(i).y)));
        return relativeBody;
    }

    public static final List<Point> getRelativeBody(Tetromino tetromino) {
        List<Point> relativeBody = new ArrayList<Point>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tetromino.isaBlock(i, j))
                    relativeBody.add(new Point(tetromino.getPosition().x + j, tetromino.getPosition().y - (4 - i)));
            }
        }
        return relativeBody;
    }
}