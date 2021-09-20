package components.toolbar;

import utils.Utils;

import javax.swing.*;
import java.awt.*;

public class GalleryToolbar extends JToolBar {

    private final GalleryToolbarUI view;

    public GalleryToolbar() {
        this.view = new GalleryToolbarUI(this);

        setUpUI();
    }

    private void setUpUI() {
        this.view.initializeUI();
    }

    public Dimension getPreferredSize() {
        return new Dimension(Utils.getToolbarWidth(),
                Utils.getWindowHeight());
    }

}
