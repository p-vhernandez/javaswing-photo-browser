package components.drawing;

import utils.Utils;

import java.awt.*;

public class TypedText implements Drawing {

    private final Color color;
    private final int fontSize;
    private Point insertPoint;
    private String typedText = "";

    private final int imageWidth, imageHeight;
    private final int startingPointX, startingPointY;

    private String[] substrings;

    public TypedText(Color color, int fontSize, Point insertPoint, int imageWidth,
                     int imageHeight, int startingPointX, int startingPointY) {
        this.color = color;
        this.fontSize = fontSize;
        this.insertPoint = insertPoint;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.startingPointX = startingPointX;
        this.startingPointY = startingPointY;
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

    private boolean textInsideImage(Graphics2D g, String[] substrings) {
        return g.getFontMetrics().stringWidth(substrings[substrings.length - 1]) <=
                (imageWidth - insertPoint.x);
    }

    private String addNewLine(int position) {
        if (typedText.charAt(position - 1) != '\n') {
            return typedText.substring(0, position) + "\n" + typedText.substring(position);
        } else {
            return typedText;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.setFont(g.getFont().deriveFont(Float.valueOf(fontSize)));

        substrings = typedText.split("\n");
        if (!textInsideImage(g, substrings)) {
            int characterCount = typedText.length();
            typedText = addNewLine(characterCount);
            System.out.println("Splitting");
            System.out.println(typedText);

            for (int i = 0; i < substrings.length; i++) {
                System.out.println("Substring " + i + ": " + substrings[i]);
            }
        }

        for (int i = 0; i < substrings.length; i++) {
            g.drawString(substrings[i], insertPoint.x,
                    insertPoint.y += (g.getFontMetrics().getHeight() * i));
        }
    }
}
