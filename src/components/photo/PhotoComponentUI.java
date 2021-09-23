package components.photo;

import components.drawing.Ellipse;
import components.drawing.Rectangle;
import components.drawing.Stroke;
import components.drawing.TypedText;
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

    public void initializeUI() {
        //component.setBorder(new LineBorder(new Color(201, 201, 201),
                //Utils.getPhotoComponentBorder()));
    }

    public void paint(Graphics2D g, boolean isFlipped) {
        getImageData();
        setComponentSize();
        paintImageBorder(g);

        if (!isFlipped) {
            g.drawImage(selectedImage,
                    Utils.getPhotoComponentBorder(),
                    Utils.getPhotoComponentBorder(),
                    imageWidth,
                    imageHeight,
                    component);
        } else {
            g.setColor(Color.white);
            g.fillRect(Utils.getPhotoComponentBorder(),
                    Utils.getPhotoComponentBorder(),
                    imageWidth,
                    imageHeight);

            for (Stroke stroke : component.getDrawnStrokes()) {
                stroke.draw(g);
            }

            for (Ellipse ellipse : component.getDrawnEllipses()) {
                ellipse.draw(g);
            }

            for (Rectangle rectangle : component.getDrawnRectangles()) {
                rectangle.draw(g);
            }

            for (TypedText typedText : component.getTypedTexts()) {
                typedText.draw(g);
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
