package gallery;

import utils.Utils;

import javax.swing.*;
import java.awt.*;

public class Gallery extends JFrame{

    private final GalleryUI view;

    public Gallery() {
        this.view = new GalleryUI(this);

        setTitle(Utils.getAppName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUpAppIcon();
        setUpUI();

        pack();
    }

    private void setUpAppIcon() {
        Image appIcon = Utils.generateImage(this, "../resources/img/picture.png");
        setIconImage(appIcon);
    }

    private void setUpUI() {
        this.view.initializeUI();
    }

    public void closeApp() {
        dispose();
    }

}
