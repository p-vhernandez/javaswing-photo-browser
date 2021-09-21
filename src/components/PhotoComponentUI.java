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
            paintImageBorder(g);
            g.drawImage(selectedImage,
                    Utils.getPhotoComponentBorder() * 2,
                    Utils.getPhotoComponentBorder() * 2,
                    imageWidth,
                    imageHeight,
                    component);
        } else {
            paintImageBorder(g);
            g.setColor(Color.white);
            g.fillRect(Utils.getPhotoComponentBorder() * 2,
                    Utils.getPhotoComponentBorder() * 2,
                    imageWidth,
                    imageHeight);

            g.setColor(Color.black);

            int i = 0;
            while (i < component.getDrawnPoints().size() -1) {
                Point currentPoint = component.getDrawnPoints().get(i);
                Point nextPoint = component.getDrawnPoints().get(i + 1);

                if (nextPoint.x != -1 && nextPoint.y != -1) {
                    RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
                    g.setRenderingHints(rh);
                    g.drawLine(currentPoint.x, currentPoint.y, nextPoint.x, nextPoint.y);
                    i++;
                } else {
                    i+=2;
                }
            }
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
            component.setComponentSize(imageWidth + Utils.getPhotoComponentBorder() * 2,
                    imageHeight + Utils.getPhotoComponentBorder() * 2);
            component.setPreferredSize(component.getSize());
        } else {
            component.setPreferredSize(component.getMinimumSize());
        }
    }

    private void paintImageBorder(Graphics2D g) {
        g.fillRect(Utils.getPhotoComponentBorder(), Utils.getPhotoComponentBorder(),
                imageWidth + Utils.getPhotoComponentBorder() * 2,
                imageHeight + Utils.getPhotoComponentBorder() * 2);
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

}
