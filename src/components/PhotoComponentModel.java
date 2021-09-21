package components;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PhotoComponentModel {

    private final PhotoComponent component;

    private String storage;

    private boolean isFlipped = false;

    public PhotoComponentModel(PhotoComponent component) {
        this.component = component;
    }

    public void setStorage(String directory) {
        this.storage = directory;
    }

    public String getStorage() {
        return storage;
    }

    public void setFlipped(boolean isFlipped) {
        this.isFlipped = isFlipped;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void addMouseListener() {
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    flipPicture();
                }
            }
        });
    }

    private void flipPicture() {
        isFlipped = !isFlipped;
        component.repaint();
    }

}
