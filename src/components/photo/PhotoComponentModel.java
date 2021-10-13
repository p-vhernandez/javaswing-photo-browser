package components.photo;

import components.drawing.*;
import components.drawing.Rectangle;
import components.drawing.Stroke;
import utils.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class PhotoComponentModel {

    private String storage;
    private boolean isFlipped = false;

    private final List<Drawing> drawings = new ArrayList<>();
    private Set<Drawing> selectedDrawings = new LinkedHashSet<>();
    private Set<Drawing> draggedDrawings = new LinkedHashSet<>();

    private Point currentInsertPoint = null;
    private Stroke currentStroke = null;
    private TypedText currentTypedText = null;
    private Ellipse currentEllipse = null;
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

    public boolean annotationsAllowed() {
        return isFlipped;
    }

    public void toggleAnnotations() {
        isFlipped = !isFlipped;
    }

    public List<Drawing> getDrawings() {
        return drawings;
    }

    public void addDrawing(Drawing drawing) {
        this.drawings.add(drawing);
    }

    public void removeDrawing(Drawing drawing) {
        this.drawings.remove(drawing);
    }

    public Set<Drawing> getSelectedDrawings() {
        return this.selectedDrawings;
    }

    public void removeSelectedDrawing(Drawing drawing) {
        this.selectedDrawings.remove(drawing);
    }

    public void addDraggedDrawings(Set<Drawing> drawingsToDrag) {
        this.draggedDrawings.addAll(drawingsToDrag);
    }

    public void clearDraggedDrawings() {
        this.draggedDrawings.clear();
    }

    public void changeSelectedDrawingsColor(Color newColor) {
        for (Drawing drawing : selectedDrawings) {
            if (drawing.getMode() != DrawingMode.TYPED_TEXT) {
                drawing.setColor(newColor);
            }
        }
    }

    public void changeSelectedTypedTextColor(Color newColor) {
        for (Drawing drawing : selectedDrawings) {
            if (drawing.getMode() == DrawingMode.TYPED_TEXT) {
                drawing.setColor(newColor);
            }
        }
    }

    public void selectDrawing(Drawing drawing) {
        this.selectedDrawings.add(drawing);
    }

    public Set<Drawing> getDraggedDrawings() {
        return this.draggedDrawings;
    }

    public void dragDrawing(Drawing drawing) {
        this.draggedDrawings.add(drawing);
    }

    public Stroke getCurrentStroke() {
        return currentStroke;
    }

    public void setCurrentStroke(Stroke stroke) {
        currentStroke = stroke;
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
