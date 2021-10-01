package components.drawing;

import utils.Utils;

import java.awt.*;

public class TypedText implements Drawing {

    private final Color color;
    private final int fontSize;
    private Point insertPoint;
    private String typedText = "";

    private final Font font;

    private final int imageWidth, imageHeight;
    private final int startingPointX, startingPointY;

    private String[] substrings;

    public TypedText(Color color, int fontSize, Point insertPoint, int imageWidth,
                     int imageHeight, int startingPointX, int startingPointY, Font font) {
        this.color = color;
        this.fontSize = fontSize;
        this.insertPoint = insertPoint;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.startingPointX = startingPointX;
        this.startingPointY = startingPointY;
        this.font = font;
    }

    public String getTypedText() {
        return typedText;
    }

    public void addCharacter(String character) {
        this.typedText += character;
    }

    public void deleteLastTypedCharacter() {
        this.typedText = this.typedText.substring(0, typedText.length() - 1);
    }

    public Point getInsertPoint() {
        return insertPoint;
    }

    public void setInsertPoint(Point insertPoint) {
        this.insertPoint = insertPoint;
    }

    private boolean textInsideImageWidth(Graphics2D g) {
        return g.getFontMetrics().stringWidth(substrings[substrings.length - 1]) <=
                (imageWidth - insertPoint.x);
    }

    private boolean textOutsideImageWidth(Graphics2D g) {
        return insertPoint.x + g.getFontMetrics().stringWidth(substrings[substrings.length - 1]) >=
                imageWidth + Utils.getPhotoComponentBorder();
    }

    private boolean textInsideImageHeight(int newLineY) {
        return newLineY < startingPointY + imageHeight;
    }

    private String addNewLine(int blankSpace, int newLine) {
        if (blankSpace != 0 && blankSpace > newLine) {
            return typedText.substring(0, blankSpace) + "\n" + typedText.substring(blankSpace + 1);
        } else {
            return typedText += "\n";
        }
    }

    private int[] searchForLastBlankSpaceAndNewLinePositions() {
        int lastBlankSpace = 0;
        int lastNewLine = 0;

        for (int i = 0; i < typedText.length(); i++) {
            if (typedText.charAt(i) == ' ') {
                lastBlankSpace = i;
            }

            if (typedText.charAt(i) == '\n') {
                lastNewLine = i;
            }
        }

        return new int[]{lastBlankSpace, lastNewLine};
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.setFont(font.deriveFont(Float.valueOf(fontSize)));

        substrings = typedText.split("\n");
        int[] results = searchForLastBlankSpaceAndNewLinePositions();
        int blankSpace = results[0];
        int newLine = results[1];

        if (!textInsideImageWidth(g)) {
            typedText = addNewLine(blankSpace, newLine);
        }

        int newLineY = insertPoint.y;
        for (String substring : substrings) {
            if (textInsideImageHeight(newLineY)) {
                g.drawString(substring, insertPoint.x, newLineY);
            } else {
                Toolkit.getDefaultToolkit().beep();
            }

            newLineY += g.getFontMetrics().getHeight();
        }
    }
}
