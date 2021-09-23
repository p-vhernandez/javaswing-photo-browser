package components.photo;

import components.drawing.DrawingMode;
import components.drawing.Ellipse;
import components.drawing.Rectangle;
import components.drawing.Stroke;
import components.drawing.TypedText;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PhotoComponent extends JComponent {

    private final PhotoComponentModel model;
    private final PhotoComponentUI view;

    public PhotoComponent() {
        this.model = new PhotoComponentModel();
        this.view = new PhotoComponentUI(this);

        setUpComponent();
    }

    private void setUpComponent() {
        this.view.initializeUI();
        addMouseListener();
    }

    private void addMouseListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    flipPicture();
                } else if (event.getClickCount() == 1 && model.isFlipped()) {
                    setFocusable(true);
                    requestFocusInWindow();
                    model.setCurrentTypedText(
                            new TypedText(
                                    model.getFontColor(),
                                    model.getFontSize(),
                                    event.getPoint(),
                                    view.getImageWidth(),
                                    view.getImageHeight(),
                                    Utils.getPhotoComponentBorder(),
                                    Utils.getPhotoComponentBorder())
                    );

                    model.addTypedText(model.getCurrentTypedText());
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (model.isFlipped()) {
                    switch (model.getDrawingMode()) {
                        case FREE -> drawLine(e.getPoint());
                        case ELLIPSE -> drawEllipse(e.getPoint());
                        case RECTANGLE -> drawRectangle(e.getPoint());
                    }
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (model.isFlipped()) {
                    switch (model.getDrawingMode()) {
                        case FREE -> model.setCurrentStroke(null);
                        case ELLIPSE -> model.setCurrentEllipse(null);
                        case RECTANGLE -> model.setCurrentRectangle(null);
                    }
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (model.isFlipped()) {
                    model.addCCharacterToCurrentTypedText(String.valueOf(e.getKeyChar()));
                    repaint();
                }
            }
        });
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

            model.getDrawnStrokes().add(model.getCurrentStroke());
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

            model.getDrawnEllipses().add(model.getCurrentEllipse());
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

            model.getDrawnRectangles().add(model.getCurrentRectangle());
        }

        model.getCurrentRectangle().updateEndPoint(currentPoint);
        repaint();
    }

    private void flipPicture() {
        model.flipPicture();

        if (!model.isFlipped()) {
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

    public String getImage() {
        return this.model.getStorage();
    }

    public int getImageWidth() {
        return this.view.getImageWidth();
    }

    public int getImageHeight() {
        return this.view.getImageHeight();
    }

    public ArrayList<Stroke> getDrawnStrokes() {
        return this.model.getDrawnStrokes();
    }

    public ArrayList<TypedText> getTypedTexts() {
        return this.model.getTypedTexts();
    }

    public ArrayList<Ellipse> getDrawnEllipses() {
        return this.model.getDrawnEllipses();
    }

    public ArrayList<Rectangle> getDrawnRectangles() {
        return this.model.getDrawnRectangles();
    }

    public void paintComponent(Graphics g) {
        this.view.paint((Graphics2D) g, this.model.isFlipped());
    }

    public void updateUI() {
        revalidate();
    }

    public void setComponentSize(int width, int height) {
        setSize(new Dimension(width, height));
    }

    public Dimension getMinimumSize() {
        return new Dimension(Utils.getScrollPaneWidth(),
                Utils.getScrollPaneHeight());
    }

}
