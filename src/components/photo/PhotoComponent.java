package components.photo;

import components.drawing.*;
import components.drawing.Rectangle;
import components.drawing.Stroke;
import fr.lri.swingstates.debug.StateMachineVisualization;
import components.states.StateMachine;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PhotoComponent extends JComponent {

    private final PhotoComponentModel model;
    private final PhotoComponentUI view;

    private StateMachine stateMachine;

    public PhotoComponent() {
        this.model = new PhotoComponentModel();
        this.view = new PhotoComponentUI(this);

        setUpComponent();
    }

    private void setUpComponent() {
        stateMachine = new StateMachine(this);
        stateMachine.attachTo(this);

        visualizeStateMachine();
    }

    public void visualizeStateMachine() {
        JFrame visualizationFrame = new JFrame();
        StateMachineVisualization stateMachineVisualization = new StateMachineVisualization(stateMachine);
        visualizationFrame.add(stateMachineVisualization);

        visualizationFrame.pack();
        visualizationFrame.setVisible(true);
    }

    public void toggleAnnotations() {
        model.toggleAnnotations();
        setFocusable(true);
        requestFocusInWindow();

        if (!model.annotationsAllowed()) {
            setFocusable(false);
        }

        repaint();
    }

    public void startTyping(Point point) {
        model.setCurrentTypedText(
                new TypedText(
                        model.getFontSize(),
                        point,
                        view.getImageWidth(),
                        view.getImageHeight(),
                        Utils.getPhotoComponentBorder(),
                        Utils.getPhotoComponentBorder(),
                        model.getFont())
        );

        model.addDrawing(model.getCurrentTypedText());
    }

    public void resetCurrentTypedText() {
        this.model.setCurrentTypedText(null);
    }

    public void draw(Point point) {
        switch (model.getDrawingMode()) {
            case FREE -> drawLine(point);
            case ELLIPSE -> drawEllipse(point);
            case RECTANGLE -> drawRectangle(point);
        }
    }

    public void stopDrawing() {
        switch (model.getDrawingMode()) {
            case FREE -> model.setCurrentStroke(null);
            case ELLIPSE -> model.setCurrentEllipse(null);
            case RECTANGLE -> model.setCurrentRectangle(null);
        }
    }

    public void deleteCharacter() {
        this.model.deleteLastTypedCharacter();
        repaint();
    }

    public void addCharacter(char character) {
        this.model.addCCharacterToCurrentTypedText(character);
        repaint();
    }

    private void drawLine(Point currentPoint) {
        if (model.getCurrentStroke() == null) {
            model.setCurrentStroke(
                    new Stroke(
                            model.getPenSize(),
                            view.getImageWidth(),
                            view.getImageHeight(),
                            Utils.getPhotoComponentBorder(),
                            Utils.getPhotoComponentBorder())
            );

            model.addDrawing(model.getCurrentStroke());
        }

        model.getCurrentStroke().addDrawnPoint(currentPoint);
        repaint();
    }

    private void drawEllipse(Point currentPoint) {
        if (model.getCurrentEllipse() == null) {
            model.setCurrentEllipse(
                    new Ellipse(
                            model.getPenSize(),
                            currentPoint,
                            currentPoint)
            );

            model.addDrawing(model.getCurrentEllipse());
        }

        model.getCurrentEllipse().updateEndPoint(currentPoint);
        repaint();
    }

    private void drawRectangle(Point currentPoint) {
        if (model.getCurrentRectangle() == null) {
            model.setCurrentRectangle(
                    new Rectangle(
                            model.getPenSize(),
                            currentPoint,
                            currentPoint)
            );

            model.addDrawing(model.getCurrentRectangle());
        }

        model.getCurrentRectangle().updateEndPoint(currentPoint);
        repaint();
    }

    public Drawing getDrawingAt(Point point) {
        for (int i = model.getDrawings().size() - 1; i >= 0; i--) {
            Drawing drawing = model.getDrawings().get(i);

            if (drawing.contains(point)) {
                System.out.println("SHAPE TOUCHED!!!!");
                return drawing;
            }
        }

        return null;
    }

    public void selectDrawing(Drawing drawing) {
        if (!model.getDrawings().contains(drawing)) {
            return;
        }

        this.model.selectDrawing(drawing);
        drawing.setSelected(true);
        repaint();
    }

    public void deselectAllDrawings() {
        List<Drawing> shapesToDeselect = model.getSelectedDrawings().stream().toList();
        for (Drawing drawing : shapesToDeselect) {
            deselect(drawing);
        }
    }

    // No effect if the shape has not been added to the canvas first.
    public void deselect(Drawing drawing) {
        if (!model.getDrawings().contains(drawing)) {
            return;
        }

        model.removeSelectedDrawing(drawing);
        drawing.setSelected(false);
        repaint();
    }

    public void startDraggingShapes() {
        this.model.addDraggedDrawings(model.getSelectedDrawings());
    }

    public void dragDrawings(Point currentMousePosition, Point lastMousePosition) {
        double xDistance = currentMousePosition.getX() - lastMousePosition.getX();
        double yDistance = currentMousePosition.getY() - lastMousePosition.getY();

        for (Drawing drawing : model.getSelectedDrawings()) {
            drawing.translateBy(xDistance, yDistance);
        }

        repaint();
    }

    public void stopDraggingShapes() {
        this.model.clearDraggedDrawings();
    }

    public void setImage(String imageDirectory) {
        this.model.setStorage(imageDirectory);
        revalidate();
        repaint();
    }

    public boolean hasDrawingsSelected() {
        return this.model.getSelectedDrawings().size() > 0;
    }

    public void deleteSelectedDrawings() {
        ArrayList<Drawing> toDelete = new ArrayList<>(this.model.getSelectedDrawings());
        this.model.removeSelectedDrawings();

        for (Drawing drawing : toDelete) {
            this.model.getDrawings().remove(drawing);
        }

        repaint();
    }

    public void changeSelectedDrawingsColor(Color newColor) {
        model.changeSelectedDrawingsColor(newColor);
        repaint();
    }

    public void changeSelectedTypedTextColor(Color newColor) {
        model.changeSelectedTypedTextColor(newColor);
        repaint();
    }

    public void setPenColor(Color penColor) {
        this.model.setPenColor(penColor);
    }

    public void setPenSize(int penSize) {
        this.model.setPenSize(penSize);
    }

    public int getPenSize() {
        return this.model.getPenSize();
    }

    public void setFontColor(Color fontColor) {
        this.model.setFontColor(fontColor);
    }

    public void setFontSize(int fontSize) {
        this.model.setFontSize(fontSize);
    }

    public int getFontSize() {
        return this.model.getFontSize();
    }

    public void setDrawingMode(DrawingMode drawingMode) {
        this.model.setDrawingMode(drawingMode);
    }

    public void setFontID(int id) {
        this.model.setFontID(id);
        setFont(id);
    }

    public void setFont(int newFont) {
        switch (newFont) {
            case 0 -> this.model.setFont(Utils.generateFont(this, "../../resources/font/urbanist-regular.ttf"));
            case 1 -> this.model.setFont(Utils.generateFont(this, "../../resources/font/urbanist-medium.ttf"));
            case 2 -> this.model.setFont(Utils.generateFont(this, "../../resources/font/urbanist-semibold.ttf"));
            case 3 -> this.model.setFont(Utils.generateFont(this, "../../resources/font/urbanist-bold.ttf"));
        }
    }

    public int getFontID() {
        return this.model.getFontID();
    }

    public Font getFont() {
        return this.model.getFont();
    }

    public String getImage() {
        return this.model.getStorage();
    }

    public List<Drawing> getDrawings() {
        return this.model.getDrawings();
    }

    public void paintComponent(Graphics g) {
        this.view.paint((Graphics2D) g, this.model.annotationsAllowed());
    }

    public void updateUI() {
        revalidate();
    }

    public void setComponentSize(int width, int height) {
        setSize(new Dimension(width, height));
    }

    public Dimension getMinimumSize() {
        return new Dimension(Utils.getPhotoComponentMinWidth(),
                Utils.getPhotoComponentMinHeight());
    }

}
