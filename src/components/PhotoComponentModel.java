package components;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PhotoComponentModel {

    private final PhotoComponent component;

    private String storage;
    private boolean isFlipped = false;

    private ArrayList<Point> drawnPoints = new ArrayList<>();

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

    public ArrayList<Point> getDrawnPoints() {
        return drawnPoints;
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

        component.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isFlipped) {
                    System.out.println("I'm drawing!!");
                    drawnPoints.add(e.getPoint());
                    component.repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (isFlipped) {
                    drawnPoints.add(e.getPoint());
                    drawnPoints.add(new Point(-1, -1));
                }
            }
        });
    }

    private void flipPicture() {
        isFlipped = !isFlipped;
        component.repaint();
    }
}
