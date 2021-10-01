package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class Utils {

    private static final String APP_NAME = "Photo browser";

    private static final String BASE_STATUS = "This is a dummy text as a status";
    private static final String MODE_VIEWER = ". You are on viewer mode.";
    private static final String MODE_BROWSER = ". You are on browser mode.";
    private static final String SELECTED = ": ON";
    private static final String UNSELECTED = ": OFF";

    private static final String[] CATEGORIES = {
            "People",
            "Places",
            "Pets"
    };

    private static final String[] FONT_OPTIONS = {
            "Regular",
            "Medium",
            "Semi-bold",
            "Bold"
    };

    private static final int WINDOW_WIDTH = 950;
    private static final int WINDOW_HEIGHT = 800;
    private static final int TOOLBAR_WIDTH = 120;
    private static final int PHOTO_COMPONENT_BORDER = 10;
    private static final int PHOTO_COMPONENT_MIN_WIDTH = 600;
    private static final int PHOTO_COMPONENT_MIN_HEIGHT = 550;

    public static String getAppName() {
        return APP_NAME;
    }

    public static String getBaseStatus() {
        return BASE_STATUS;
    }

    public static String getModeViewer() {
        return MODE_VIEWER;
    }

    public static String getModeBrowser() {
        return MODE_BROWSER;
    }

    public static String[] getCategories() {
        return CATEGORIES;
    }

    public static String[] getFontOptions() {
        return FONT_OPTIONS;
    }

    public static String getSelected() {
        return SELECTED;
    }

    public static String getUnselected() {
        return UNSELECTED;
    }

    public static int getWindowWidth() {
        return WINDOW_WIDTH;
    }

    public static int getWindowHeight() {
        return WINDOW_HEIGHT;
    }

    public static int getToolbarWidth() {
        return TOOLBAR_WIDTH;
    }

    public static int getScrollPaneWidth() {
        return WINDOW_WIDTH - TOOLBAR_WIDTH;
    }

    public static int getScrollPaneHeight() {
        return WINDOW_HEIGHT;
    }

    public static int getPhotoComponentBorder() {
        return PHOTO_COMPONENT_BORDER;
    }

    public static int getPhotoComponentMinWidth() {
        return PHOTO_COMPONENT_MIN_WIDTH;
    }

    public static int getPhotoComponentMinHeight() {
        return PHOTO_COMPONENT_MIN_HEIGHT;
    }

    public static Image generateImage(Object object, String directory) {
        return Toolkit.getDefaultToolkit()
                .getImage(object.getClass().getResource(directory));
    }

    public static Image generateImageFromPath(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Font generateFont(Object object, String path) {
        try {
            InputStream is = object.getClass().getResourceAsStream(path);

            if (is != null) {
                return Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(is));
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
