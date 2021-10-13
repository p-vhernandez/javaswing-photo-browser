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

    @Override
    public boolean contains(Point point) {
        int width = endPoint.x - startPoint.x;
        int height = endPoint.y - startPoint.y;


        for (int xError = 0; xError < Utils.getAllowedClickError(); xError++) {
            for (int yError = 0; yError < Utils.getAllowedClickError(); yError++) {
                /*if (point.x + xError >= this.startPoint.x && point.x + xError < this.startPoint.x + width
                        && point.y >= this.startPoint.y && point.y < this.startPoint.y + height) {
                    return true;
                }

                if (point.x >= this.startPoint.x && point.x < this.startPoint.x + width
                        && point.y + yError >= this.startPoint.y && point.y + yError < this.startPoint.y + height) {
                    return true;
                }

                if (point.x + xError >= this.startPoint.x && point.x + xError < this.startPoint.x + width
                        && point.y + yError >= this.startPoint.y && point.y + yError < this.startPoint.y + height) {
                    return true;
                }

                if (point.x + xError >= this.startPoint.x && point.x + xError < this.startPoint.x + width
                        && point.y - yError >= this.startPoint.y && point.y - yError < this.startPoint.y + height) {
                    return true;
                }

                if (point.x - xError >= this.startPoint.x && point.x - xError < this.startPoint.x - width
                        && point.y - yError >= this.startPoint.y && point.y - yError < this.startPoint.y - height) {
                    return true;
                }

                if (point.x - xError >= this.startPoint.x && point.x - xError < this.startPoint.x + width
                        && point.y + yError >= this.startPoint.y && point.y + yError < this.startPoint.y + height) {
                    return true;
                }

                if (point.x - xError >= this.startPoint.x && point.x - xError < this.startPoint.x - width
                        && point.y >= this.startPoint.y && point.y < this.startPoint.y - height) {
                    return true;
                }

                if (point.x >= this.startPoint.x && point.x < this.startPoint.x - width
                        && point.y - yError >= this.startPoint.y && point.y - yError < this.startPoint.y - height) {
                    return true;
                }*/
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
