package components.drawing;

import utils.Utils;

import java.awt.*;

public abstract class Drawing {

    private boolean selected = false;
    private Color color = Color.darkGray;
    private DrawingMode mode = DrawingMode.FREE;

    public Drawing(DrawingMode mode) {
        this.mode = mode;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Color getColor() {
        return color;
    }

    public DrawingMode getMode() {
        return mode;
    }

    public void setColor(Color newColor) {
        this.color = newColor;
    }

    public abstract void draw(Graphics2D g);

    public abstract boolean contains(Point point);

    public abstract void translateBy(double xDistance, double yDistance);

}
