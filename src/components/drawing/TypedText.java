package components.drawing;

import utils.Utils;

import java.awt.*;

public class TypedText extends Drawing {

    private final int fontSize;
    private Point insertPoint;
    private String typedText = "";

    private final Font font;

    private final int imageWidth, imageHeight;
    private double startingPointX, startingPointY;

    private String[] substrings;

    public TypedText(int fontSize, Point insertPoint, int imageWidth,
                     int imageHeight, int startingPointX, int startingPointY, Font font) {
        super(DrawingMode.TYPED_TEXT);

        this.fontSize = fontSize;
        this.insertPoint = insertPoint;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.startingPointX = startingPointX;
        this.startingPointY = startingPointY;
        this.font = font;
    }

    public void addCharacter(String character) {
        this.typedText += character;
    }

    public void deleteLastTypedCharacter() {
        this.typedText = this.typedText.substring(0, typedText.length() - 1);
    }

    private boolean textInsideImageWidth(Graphics2D g) {
        return g.getFontMetrics().stringWidth(substrings[substrings.length - 1]) <=
                (imageWidth - insertPoint.x);
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
        g.setFont(font.deriveFont(Float.valueOf(fontSize)));
        if (isSelected()) {
            g.setColor(Color.red);
        } else {
            g.setColor(getColor());
        }

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

    @Override
    public boolean contains(Point point) {
        int width = imageWidth - insertPoint.x;
        int height = imageHeight - insertPoint.y;

        for (int xError = 0; xError < Utils.getAllowedClickError(); xError++) {
            for (int yError = 0; yError < Utils.getAllowedClickError(); yError++) {
                if (point.x + xError >= this.insertPoint.x && point.x + xError < this.insertPoint.x + width
                        && point.y >= this.insertPoint.y && point.y < this.insertPoint.y + height) {
                    return true;
                }

                if (point.x >= this.insertPoint.x && point.x < this.insertPoint.x + width
                        && point.y + yError >= this.insertPoint.y && point.y + yError < this.insertPoint.y + height) {
                    return true;
                }

                if (point.x + xError >= this.insertPoint.x && point.x + xError < this.insertPoint.x + width
                        && point.y + yError >= this.insertPoint.y && point.y + yError < this.insertPoint.y + height) {
                    return true;
                }

                if (point.x + xError >= this.insertPoint.x && point.x + xError < this.insertPoint.x + width
                        && point.y - yError >= this.insertPoint.y && point.y - yError < this.insertPoint.y + height) {
                    return true;
                }

                if (point.x - xError >= this.insertPoint.x && point.x - xError < this.insertPoint.x - width
                        && point.y - yError >= this.insertPoint.y && point.y - yError < this.insertPoint.y - height) {
                    return true;
                }

                if (point.x - xError >= this.insertPoint.x && point.x - xError < this.insertPoint.x + width
                        && point.y + yError >= this.insertPoint.y && point.y + yError < this.insertPoint.y + height) {
                    return true;
                }

                if (point.x - xError >= this.insertPoint.x && point.x - xError < this.insertPoint.x - width
                        && point.y >= this.insertPoint.y && point.y < this.insertPoint.y - height) {
                    return true;
                }

                if (point.x >= this.insertPoint.x && point.x < this.insertPoint.x - width
                        && point.y - yError >= this.insertPoint.y && point.y - yError < this.insertPoint.y - height) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void translateBy(double xDistance, double yDistance) {
        insertPoint.x += xDistance;
        insertPoint.y += yDistance;
    }
}
