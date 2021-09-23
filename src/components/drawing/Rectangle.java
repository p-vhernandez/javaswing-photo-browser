package components.drawing;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle implements Drawing {

    private final Color color;
    private final int penSize;

    private final Point startPoint;
    private Point endPoint;

    public Rectangle(Color color, int penSize,
                     Point startPoint, Point endPoint) {
        this.color = color;
        this.penSize = penSize;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public void updateEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.setStroke(new BasicStroke(penSize));

        Rectangle2D.Double rectangle = new Rectangle2D.Double(
                Math.min(startPoint.x, endPoint.x),
                Math.min(startPoint.y, endPoint.y),
                Math.abs(startPoint.x - endPoint.x),
                Math.abs(startPoint.y - endPoint.y)
        );

        g.draw(rectangle);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "color=" + color +
                ", startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                '}';
    }
}
