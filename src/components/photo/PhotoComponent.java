package components.photo;

import javax.swing.*;
import java.awt.*;

public class PhotoComponent extends JComponent {

    private final PhotoComponentModel model;
    private final PhotoComponentUI view;

    public PhotoComponent() {
        this.model = new PhotoComponentModel();
        this.view = new PhotoComponentUI();
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    public Dimension getPreferredSize() {
        return null;
    }

    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

}
