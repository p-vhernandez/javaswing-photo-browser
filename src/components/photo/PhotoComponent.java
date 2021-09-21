package components.photo;

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

    public void paintComponent() {
        this.view.paint();
    }

    public void updateUI() {
        revalidate();
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    public Dimension getPreferredSize() {
        return new Dimension(Utils.getPhotoComponentWidth(),
                Utils.getPhotoComponentHeight());
    }

    public Dimension getDefaultSize() {
        return new Dimension(Utils.getPhotoComponentWidth(),
                Utils.getPhotoComponentHeight());
    }

}
