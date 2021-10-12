package components.drawing;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Drawing {

    private final int penSize;

    private final Point startPoint;
    private Point endPoint;

    public Ellipse(int penSize, Point startPoint, Point endPoint) {
        super(DrawingMode.ELLIPSE);

        this.penSize = penSize;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public void updateEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(getColor());
        g.setStroke(new BasicStroke(penSize));

        Ellipse2D.Double ellipse = new Ellipse2D.Double(
                Math.min(startPoint.x, endPoint.x),
                Math.min(startPoint.y, endPoint.y),
                Math.abs(startPoint.x - endPoint.x),
                Math.abs(startPoint.y - endPoint.y)
        );

        g.draw(ellipse);
    }

    @Override
    public boolean contains(Point point) {
        // TODO
        return false;
    }

    @Override
    public String toString() {
        return "Ellipse{" +
                "color=" + getColor() +
                ", startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                '}';
    }
}
