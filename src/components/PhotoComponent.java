package components;

import utils.Utils;

import javax.swing.*;
import java.awt.*;

public class PhotoComponent extends JComponent {

    private final PhotoComponentModel model;
    private final PhotoComponentUI view;

    public PhotoComponent() {
        this.model = new PhotoComponentModel(this);
        this.view = new PhotoComponentUI(this);

        setUpComponent();
    }

    private void setUpComponent() {
        this.view.initializeUI();
        this.model.addMouseListener();
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
