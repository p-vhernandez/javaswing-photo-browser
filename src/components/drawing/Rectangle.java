package components.drawing;

import utils.Utils;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Drawing {

    private final int penSize;

    private final Point startPoint;
    private Point endPoint;

    public Rectangle(int penSize, Point startPoint, Point endPoint) {
        super(DrawingMode.RECTANGLE);

        this.penSize = penSize;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public void updateEndPoint(Point endPoint) {
        if (endPoint != null) {
            this.endPoint = endPoint;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        if (isSelected()) {
            g.setColor(Color.red);
            g.setStroke(new BasicStroke(penSize + 1));
        } else {
            g.setColor(getColor());
            g.setStroke(new BasicStroke(penSize));
        }

        Rectangle2D.Double rectangle = new Rectangle2D.Double(
                Math.min(startPoint.x, endPoint.x),
                Math.min(startPoint.y, endPoint.y),
                Math.abs(startPoint.x - endPoint.x),
                Math.abs(startPoint.y - endPoint.y)
        );

        g.draw(rectangle);
    }

    private boolean xPositionCoincides(Point point, int positionToCompare) {
        for (int xError = 0; xError < Utils.getAllowedClickError(); xError++) {
            if ((point.x + xError) == positionToCompare) {
                return true;
            }
        }

        return false;
    }

    protected boolean yPositionCoincides(Point point, int positionToCompare) {
        for (int yError = 0; yError < Utils.getAllowedClickError(); yError++) {
            if ((point.y + yError) == positionToCompare) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean contains(Point point) {
        // FIXME
        int width = endPoint.x - startPoint.x;
        int height = endPoint.y - startPoint.y;

        for (int error = 0; error < Utils.getAllowedClickError(); error++) {
            for (int xAxis = startPoint.x; xAxis < (startPoint.x + width); xAxis++) {
                Point topCheckPoint = new Point(xAxis, startPoint.y);

                if (topCheckPoint.equals(new Point(point.x + error, point.y + error))) {
                    return true;
                }

                if (topCheckPoint.equals(new Point(point.x + error, point.y - error))) {
                    return true;
                }

                if (topCheckPoint.equals(new Point(point.x - error, point.y + error))) {
                    return true;
                }

                if (topCheckPoint.equals(new Point(point.x - error, point.y - error))) {
                    return true;
                }
            }

            for (int xAxis = (endPoint.x - width); xAxis < endPoint.x; xAxis++) {
                Point bottomCheckPoint = new Point(xAxis, endPoint.y);

                if (bottomCheckPoint.equals(new Point(point.x + error, point.y + error))) {
                    return true;
                }

                if (bottomCheckPoint.equals(new Point(point.x + error, point.y - error))) {
                    return true;
                }

                if (bottomCheckPoint.equals(new Point(point.x - error, point.y + error))) {
                    return true;
                }

                if (bottomCheckPoint.equals(new Point(point.x - error, point.y - error))) {
                    return true;
                }
            }

            for (int yAxis = startPoint.y; yAxis < (startPoint.y + height); yAxis++) {
                Point leftCheckPoint = new Point(startPoint.x, yAxis);

                if (leftCheckPoint.equals(new Point(point.x + error, point.y + error))) {
                    return true;
                }

                if (leftCheckPoint.equals(new Point(point.x + error, point.y - error))) {
                    return true;
                }

                if (leftCheckPoint.equals(new Point(point.x - error, point.y + error))) {
                    return true;
                }

                if (leftCheckPoint.equals(new Point(point.x - error, point.y - error))) {
                    return true;
                }
            }

            for (int yAxis = (endPoint.y - height); yAxis < endPoint.y; yAxis++) {
                Point rightCheckPoint = new Point(endPoint.x, yAxis);

                if (rightCheckPoint.equals(new Point(point.x + error, point.y + error))) {
                    return true;
                }

                if (rightCheckPoint.equals(new Point(point.x + error, point.y - error))) {
                    return true;
                }

                if (rightCheckPoint.equals(new Point(point.x - error, point.y + error))) {
                    return true;
                }

                if (rightCheckPoint.equals(new Point(point.x - error, point.y - error))) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void translateBy(double xDistance, double yDistance) {
        startPoint.x += xDistance;
        startPoint.y += yDistance;
        endPoint.x += xDistance;
        endPoint.y += yDistance;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "color=" + getColor() +
                ", startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                '}';
    }
}
