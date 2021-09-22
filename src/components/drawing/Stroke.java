package components.drawing;

import java.awt.*;
import java.util.ArrayList;

public class Stroke implements Drawing {

    private final ArrayList<Point> drawnPoints;
    private final Color color;

    public Stroke(Color color) {
        this.drawnPoints = new ArrayList<>();
        this.color = color;
    }

    public void addDrawnPoint(Point point) {
        this.drawnPoints.add(point);
    }

    public ArrayList<Point> getDrawnPoints() {
        return drawnPoints;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);

        if (drawnPoints.size() >= 2) {
            for (int i = 0; i < drawnPoints.size() - 1; i ++) {
                Point currentPoint = drawnPoints.get(i);
                Point nextPoint = drawnPoints.get(i + 1);

                RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g.setRenderingHints(rh);
                g.drawLine(currentPoint.x, currentPoint.y, nextPoint.x, nextPoint.y);
            }
        }
    }
}
