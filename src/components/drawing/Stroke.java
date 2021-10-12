package components.drawing;

import utils.Utils;

import java.awt.*;
import java.util.ArrayList;

public class Stroke extends Drawing {

    private final ArrayList<Point> drawnPoints;
    private final int penSize;

    private final int imageWidth, imageHeight;
    private final int startingPointX, startingPointY;

    public Stroke(int penSize, int imageWidth, int imageHeight,
                  int startingPointX, int startingPointY) {
        super(DrawingMode.FREE);

        this.drawnPoints = new ArrayList<>();
        this.penSize = penSize;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.startingPointX = startingPointX;
        this.startingPointY = startingPointY;
    }

    public void addDrawnPoint(Point point) {
        this.drawnPoints.add(point);
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
        // Distinguish if stroke is selected
        if (isSelected()) {
            g.setColor(Color.red);
            g.setStroke(new BasicStroke(penSize + 1));
        } else {
            System.out.println(getColor());
            g.setColor(getColor());
            g.setStroke(new BasicStroke(penSize));
        }

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

    @Override
    public boolean contains(Point point) {
        // Adding an error, so it can be easier to
        // exactly select the stroke
        for (int xError = 0; xError < Utils.getAllowedClickError(); xError++) {
            for (int yError = 0; yError < Utils.getAllowedClickError(); yError++) {
                for (Point strokePoint : drawnPoints) {
                    if (strokePoint.equals(new Point(point.x + xError, point.y + yError))) {
                        return true;
                    }

                    if (strokePoint.equals(new Point(point.x + xError, point.y - yError))) {
                        return true;
                    }

                    if (strokePoint.equals(new Point(point.x - xError, point.y - yError))) {
                        return true;
                    }

                    if (strokePoint.equals(new Point(point.x - xError, point.y + yError))) {
                        return true;
                    }

                    if (strokePoint.equals(new Point(point.x - xError, point.y))) {
                        return true;
                    }

                    if (strokePoint.equals(new Point(point.x + xError, point.y))) {
                        return true;
                    }

                    if (strokePoint.equals(new Point(point.x, point.y - yError))) {
                        return true;
                    }

                    if (strokePoint.equals(new Point(point.x, point.y + yError))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
