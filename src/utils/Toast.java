package utils;

import java.awt.Color;
import java.awt.Label;
import java.awt.Point;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.Popup;
import javax.swing.PopupFactory;

/**
 * Full class obtained & adapted from
 * https://stackoverflow.com/a/24716231/9772035
 */

public class Toast {

    private final JComponent component;
    private Point location;
    private final String message;
    private long duration; // in millisecond

    public Toast(JComponent comp, Point toastLocation, String msg, long forDuration) {
        this.component = comp;
        this.location = toastLocation;
        this.message = msg;
        this.duration = forDuration;

        if (this.component != null) {
            if (this.location == null) {
                this.location = component.getLocationOnScreen();
            }

            new Thread(() -> {
                Popup view = null;

                try {
                    Label tip = new Label(message);
                    tip.setForeground(Color.white);
                    tip.setBackground(Color.black);
                    view = PopupFactory.getSharedInstance().getPopup(component, tip, location.x + 250,
                            location.y + component.getHeight() - 200);
                    view.show();
                    Thread.sleep(duration);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Toast.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    Objects.requireNonNull(view).hide();
                }
            }).start();
        }
    }

    public static void showToast(JComponent component, String message) {
        new Toast(component, null, message, 2000 /* Default 2 Sec */);
    }

    public static void showToast(JComponent component, String message, Point location, long forDuration) {
        new Toast(component, location, message, forDuration);
    }
}