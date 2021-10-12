package components.drawing;

import java.awt.*;
import java.util.ArrayList;

public class Stroke extends Drawing {

    private final ArrayList<Point> drawnPoints;
    private final Color color;
    private final int penSize;

    private final int imageWidth, imageHeight;
    private final int startingPointX, startingPointY;

    public Stroke(Color color, int penSize, int imageWidth, int imageHeight,
                  int startingPointX, int startingPointY) {
        this.drawnPoints = new ArrayList<>();
        this.color = color;
        this.penSize = penSize;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.startingPointX = startingPointX;
        this.startingPointY = startingPointY;
    }

    public void addDrawnPoint(Point point) {
        this.drawnPoints.add(point);
    }

    public ArrayList<Point> getDrawnPoints() {
        return drawnPoints;
    }

    private boolean lineInsideImage(Point currentPoint, Point nextPoint) {
        return pointInsideImage(currentPoint) && pointInsideImage(nextPoint);
    }

    private boolean pointInsideImage(Point point) {
        return pointGreaterThanOrigin(point) && pointSmallerThanEnd(point);
    }

    private boolean pointGreaterThanOrigin(Point point) {
        return point.x > startingPointX
                && point.y > startingPointY;
    }

    private boolean pointSmallerThanEnd(Point point) {
        return point.x < (startingPointX + imageWidth)
                && point.y < (startingPointY + imageHeight);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.setStroke(new BasicStroke(penSize));

        if (drawnPoints.size() >= 2) {
            for (int i = 0; i < drawnPoints.size() - 1; i ++) {
                Point currentPoint = drawnPoints.get(i);
                Point nextPoint = drawnPoints.get(i + 1);

                if (lineInsideImage(currentPoint, nextPoint)) {
                    RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
                    g.setRenderingHints(rh);
                    g.drawLine(currentPoint.x, currentPoint.y, nextPoint.x, nextPoint.y);
                }
            }
        }
    }
}
