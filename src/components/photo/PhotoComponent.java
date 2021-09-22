package components.photo;

import components.drawing.Stroke;
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
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (model.isFlipped()) {
                    // TODO
                    System.out.println("Mouse pressed!");
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (model.isFlipped()) {
                    if (model.getCurrentStroke() == null) {
                        model.setCurrentStroke(new Stroke(Color.darkGray));
                        model.getDrawnStrokes().add(model.getCurrentStroke());
                    }

                    model.getCurrentStroke().addDrawnPoint(e.getPoint());
                    repaint();
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (model.isFlipped()) {
                    model.setCurrentStroke(null);
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (model.isFlipped()) {
                    System.out.println("KEY: " + e.getKeyChar());
                }
            }
        });
    }

    private void flipPicture() {
        model.flipPicture();

        if (model.isFlipped()) {
            setFocusable(true);
        }

        repaint();
    }

    public void setImage(String imageDirectory) {
        this.model.setStorage(imageDirectory);
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
