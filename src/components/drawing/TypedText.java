package components.drawing;

import java.awt.*;

public class TypedText implements Drawing {

    private final Color color;
    private Point insertPoint;
    private String typedText = "";

    public TypedText(Color color) {
        this.color = color;
        this.insertPoint = null;
    }

    public TypedText(Color color, Point insertPoint) {
        this.color = color;
        this.insertPoint = insertPoint;
    }

    public String getTypedText() {
        return typedText;
    }

    public void addCharacter(String character) {
        this.typedText += character;
    }

    public Point getInsertPoint() {
        return insertPoint;
    }

    public void setInsertPoint(Point insertPoint) {
        this.insertPoint = insertPoint;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.drawString(typedText, insertPoint.x, insertPoint.y);
    }
}
