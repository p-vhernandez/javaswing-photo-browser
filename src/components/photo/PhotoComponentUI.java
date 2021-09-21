package components.photo;

import javax.swing.border.LineBorder;
import java.awt.*;

public class PhotoComponentUI {

    private final PhotoComponent component;

    public PhotoComponentUI(PhotoComponent component) {
        this.component = component;
    }

    public void initializeUI() {
        component.setPreferredSize(component.getPreferredSize());
        component.setSize(component.getDefaultSize());
        component.setBorder(new LineBorder(new Color(201, 201, 201), 2));
    }

    public void paint() {

    }

}
