package components.photo;

import components.drawing.Stroke;
import components.drawing.TypedText;

import java.awt.*;
import java.util.ArrayList;

public class PhotoComponentModel {

    private String storage;
    private boolean isFlipped = false;

    private ArrayList<Stroke> drawnStrokes = new ArrayList<>();
    private Stroke currentStroke = null;

    private ArrayList<TypedText> typedTexts = new ArrayList<>();
    private Point currentInsertPoint = null;
    private TypedText currentTypedText = null;

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

}
