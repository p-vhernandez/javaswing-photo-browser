package utils;

import java.awt.*;

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

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 700;
    private static final int TOOLBAR_WIDTH = 120;
    private static final int PHOTO_COMPONENT_HEIGHT = 300;

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

    public static int getPhotoComponentWidth() {
        return WINDOW_WIDTH - TOOLBAR_WIDTH;
    }

    public static int getPhotoComponentHeight() {
        return PHOTO_COMPONENT_HEIGHT;
    }

    public static Image generateImage(Object object, String directory) {
        return Toolkit.getDefaultToolkit()
                .getImage(object.getClass().getResource(directory));
    }

}
