package components;

import utils.Utils;

import javax.swing.border.LineBorder;
import java.awt.*;

public class PhotoComponentUI {

    private final PhotoComponent component;

    private int imageWidth;
    private int imageHeight;

    private Image selectedImage;

    public PhotoComponentUI(PhotoComponent component) {
        this.component = component;
    }

    public void initializeUI() {
        component.setBorder(new LineBorder(new Color(201, 201, 201),
                Utils.getPhotoComponentBorder()));
    }

    public void paint(Graphics2D g, boolean isFlipped) {
        getImageData();

        if (!isFlipped) {
            setComponentSize();
            g.drawImage(selectedImage, 0, 0, imageWidth, imageHeight, component);
        } else {
            //generateFlippedSize();
            g.fillRect(0, 0, imageWidth, imageHeight);
        }
    }

    private void getImageData() {
        selectedImage = Utils.generateImage(this, component.getImage());
        imageWidth = selectedImage.getWidth(component);
        imageHeight = selectedImage.getHeight(component);
    }

    private void setComponentSize() {
        if (imageWidth > Utils.getScrollPaneWidth()
                || imageHeight > Utils.getScrollPaneHeight()) {
            component.setComponentSize(imageWidth, imageHeight);
            component.setPreferredSize(component.getSize());
        } else {
            component.setPreferredSize(component.getMinimumSize());
        }
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

}
