package components.photo;

import components.drawing.Stroke;

import java.util.ArrayList;

public class PhotoComponentModel {

    private String storage;
    private boolean isFlipped = false;

    private ArrayList<Stroke> drawnStrokes = new ArrayList<>();
    private Stroke currentStroke = null;

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

}
