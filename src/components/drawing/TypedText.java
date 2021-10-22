package components.drawing;

import utils.Utils;

import java.awt.*;
import java.util.ArrayList;

public class TypedText extends Drawing {

    private final int fontSize;
    private final Point insertPoint;
    private final StringBuffer text = new StringBuffer();

    private final Font font;

    private final int imageWidth, imageHeight;
    private double startingPointX, startingPointY;

    private boolean lineBreaksValid = false;
    private ArrayList<String> lines;
    private ArrayList<Integer> lineBreaks;

    private int lineHeight, numberOfLines = 0;

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

    public void addCharacter(char character) {
        this.text.append(character);
        invalidateLineBreaks();
    }

    public void deleteLastTypedCharacter() {
        this.text.deleteCharAt(text.length() - 1);
    }

    private boolean textInsideImageWidth(String string, int lineStart, int i, Graphics2D g) {
        return g.getFontMetrics().stringWidth(string.substring(lineStart, i + 1)) <=
                (imageWidth - insertPoint.x);
    }

    private void invalidateLineBreaks() {
        this.lineBreaksValid = false;
    }

    private void wrapLines(Graphics2D g) {
        numberOfLines = 0;
        lines = new ArrayList<>();
        String string = text.toString();
        lineBreaks = calculateLineBreaks(g);

        int lineStart = 0;
        for (int breakPoint : lineBreaks) {
            lines.add(string.substring(lineStart, breakPoint));
            numberOfLines++;
            lineStart = breakPoint;
        }

        if (lineStart < string.length()) {
            lines.add(string.substring(lineStart));
            numberOfLines++;
        }

        //numberOfLines = linesAux;
    }

    private ArrayList<Integer> calculateLineBreaks(Graphics2D g) {
        String string = text.toString();
        ArrayList<Integer> breaks = new ArrayList<>();

        int lineStart = 0;
        int lastSpace = 0;

        for (int i = 0; i < string.length(); ++i) {
            char character = string.charAt(i);

            if (Character.isWhitespace(character)) {
                lastSpace = i;
                continue;
            }

            if (!textInsideImageWidth(string, lineStart, i, g)) {
                int breakPoint = lastSpace > lineStart ? lastSpace + 1 : i;
                breaks.add(breakPoint);
                lineStart = breakPoint;
                lastSpace = breakPoint;
            }
        }

        return lineBreaks = breaks;
    }

    @Override
    public void draw(Graphics2D g) {
        lineHeight = g.getFontMetrics().getHeight();
        Point lineStart = new Point(insertPoint);
        g.setFont(font.deriveFont(Float.valueOf(fontSize)));

        if (isSelected()) {
            g.setColor(Color.red);
        } else {
            g.setColor(getColor());
        }

        if (!lineBreaksValid) {
            wrapLines(g);
        }

        for (String line : lines) {
            g.drawString(line, lineStart.x, lineStart.y);
            lineStart.y += g.getFontMetrics().getHeight();
        }
    }

    @Override
    public boolean contains(Point point) {
        // FIXME
        int width = imageWidth - insertPoint.x;
        int height = numberOfLines * lineHeight;

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
        if (insertPoint.x + xDistance < imageWidth
                || insertPoint.x + xDistance > 0) {
            insertPoint.x += xDistance;
            insertPoint.y += yDistance;
        }
    }
}
