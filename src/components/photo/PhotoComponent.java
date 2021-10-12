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
        //addMouseListener();
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

    public boolean annotationsAllowed() {
        return this.model.annotationsAllowed();
    }

    public void startTyping(Point point) {
        setFocusable(true);
        requestFocusInWindow();
        model.setCurrentTypedText(
                new TypedText(
                        model.getFontColor(),
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

    public void addCharacter(char chararcter) {
        this.model.addCCharacterToCurrentTypedText(String.valueOf(chararcter));
        repaint();
    }

    private void drawLine(Point currentPoint) {
        if (model.getCurrentStroke() == null) {
            model.setCurrentStroke(
                    new Stroke(
                            model.getPenColor(),
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
                            model.getPenColor(),
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
                            model.getPenColor(),
                            model.getPenSize(),
                            currentPoint,
                            currentPoint)
            );

            model.addDrawing(model.getCurrentRectangle());
        }

        model.getCurrentRectangle().updateEndPoint(currentPoint);
        repaint();
    }

    public void toggleAnnotations() {
        model.toggleAnnotations();

        if (!model.annotationsAllowed()) {
            setFocusable(false);
        }

        repaint();
    }

    public void setImage(String imageDirectory) {
        this.model.setStorage(imageDirectory);
        revalidate();
        repaint();
    }

    public void setPenColor(Color penColor) {
        this.model.setPenColor(penColor);
    }

    public Color getPenColor() {
        return this.model.getPenColor();
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

    public DrawingMode getDrawingMode() {
        return this.model.getDrawingMode();
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

    public int getImageWidth() {
        return this.view.getImageWidth();
    }

    public int getImageHeight() {
        return this.view.getImageHeight();
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
