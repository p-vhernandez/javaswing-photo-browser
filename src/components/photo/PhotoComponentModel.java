package components.photo;

import components.drawing.DrawingMode;
import components.drawing.Ellipse;
import components.drawing.Rectangle;
import components.drawing.Stroke;
import components.drawing.TypedText;
import utils.Utils;

import java.awt.*;
import java.util.ArrayList;

public class PhotoComponentModel {

    private String storage;
    private boolean isFlipped = false;

    private ArrayList<Stroke> drawnStrokes = new ArrayList<>();
    private Stroke currentStroke = null;

    private final ArrayList<TypedText> typedTexts = new ArrayList<>();
    private Point currentInsertPoint = null;
    private TypedText currentTypedText = null;

    private final ArrayList<Ellipse> drawnEllipses = new ArrayList<>();
    private Ellipse currentEllipse = null;

    private final ArrayList<Rectangle> drawnRectangles = new ArrayList<>();
    private Rectangle currentRectangle = null;

    private Color
            penColor = Color.darkGray,
            fontColor = Color.darkGray;

    private int
            penSize = 1,
            fontSize = 14;

    private DrawingMode drawingMode = DrawingMode.FREE;

    private Font font = Utils.generateFont(this, "../../resources/font/urbanist-medium.ttf");
    private int fontID = 1;

    public void setStorage(String directory) {
        this.storage = directory;
    }

    public String getStorage() {
        return storage;
    }

    public void setFlipped(boolean isFlipped) {
        this.isFlipped = isFlipped;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void flipPicture() {
        isFlipped = !isFlipped;
    }

    public ArrayList<Stroke> getDrawnStrokes() {
        return drawnStrokes;
    }

    public void setDrawnStrokes(ArrayList<Stroke> strokes) {
        drawnStrokes = strokes;
    }

    public Stroke getCurrentStroke() {
        return currentStroke;
    }

    public void setCurrentStroke(Stroke stroke) {
        currentStroke = stroke;
    }

    public Point getCurrentInsertTextPoint() {
        return currentInsertPoint;
    }

    public void setCurrentInsertTextPoint(Point point) {
        currentInsertPoint = point;
    }
    
    public TypedText getCurrentTypedText() {
        return currentTypedText;
    }
    
    public void setCurrentTypedText(TypedText typedText) {
        currentTypedText = typedText;
    }
    
    public void addCCharacterToCurrentTypedText(String letter) {
        currentTypedText.addCharacter(letter);
    }

    public ArrayList<TypedText> getTypedTexts() {
        return typedTexts;
    }

    public void addTypedText(TypedText typedText) {
        typedTexts.add(typedText);
    }

    public Color getPenColor() {
        return penColor;
    }

    public void setPenColor(Color color) {
        this.penColor = color;
    }

    public int getPenSize() {
        return penSize;
    }

    public void setPenSize(int penSize) {
        this.penSize = penSize;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setDrawingMode(DrawingMode drawingMode) {
        this.drawingMode = drawingMode;
    }

    public DrawingMode getDrawingMode() {
        return drawingMode;
    }

    public void setCurrentEllipse(Ellipse ellipse) {
        this.currentEllipse = ellipse;
    }

    public Ellipse getCurrentEllipse() {
        return currentEllipse;
    }

    public void setCurrentRectangle(Rectangle rectangle) {
        this.currentRectangle = rectangle;
    }

    public Rectangle getCurrentRectangle() {
        return currentRectangle;
    }

    public ArrayList<Ellipse> getDrawnEllipses() {
        return drawnEllipses;
    }

    public ArrayList<Rectangle> getDrawnRectangles() {
        return drawnRectangles;
    }

    public void deleteLastTypedCharacter() {
        this.currentTypedText.deleteLastTypedCharacter();
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public int getFontID() {
        return fontID;
    }

    public void setFontID(int fontID) {
        this.fontID = fontID;
    }
}
