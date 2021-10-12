package components.photo;

import components.drawing.*;
import utils.Utils;

import java.awt.*;

public class PhotoComponentUI {

    private final PhotoComponent component;

    private int imageWidth = 0;
    private int imageHeight = 0;

    private Image selectedImage;

    public PhotoComponentUI(PhotoComponent component) {
        this.component = component;
    }

    public void paint(Graphics2D g, boolean isFlipped) {
        getImageData();
        setComponentSize();
        paintImageBorder(g);

        g.drawImage(selectedImage,
                Utils.getPhotoComponentBorder(),
                Utils.getPhotoComponentBorder(),
                imageWidth,
                imageHeight,
                component);

        if (isFlipped) {
            for (Drawing drawing : component.getDrawings()) {
                drawing.draw(g);
            }
        }
    }

    private void getImageData() {
        selectedImage = Utils.generateImageFromPath(component.getImage());
        if (selectedImage != null) {
            imageWidth = selectedImage.getWidth(component);
            imageHeight = selectedImage.getHeight(component);
        }
    }

    private void setComponentSize() {
        component.setMinimumSize(component.getMinimumSize());

        if (imageWidth > Utils.getScrollPaneWidth()
                || imageHeight > Utils.getScrollPaneHeight()) {
            component.setComponentSize(imageWidth + Utils.getPhotoComponentBorder() * 2,
                    imageHeight + Utils.getPhotoComponentBorder() * 2);
            component.setPreferredSize(component.getSize());
        } else {
            component.setPreferredSize(component.getMinimumSize());
        }
    }

    private void paintImageBorder(Graphics2D g) {
        g.fillRect(0, 0, imageWidth + Utils.getPhotoComponentBorder() * 2,
                imageHeight + Utils.getPhotoComponentBorder() * 2);
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

}
