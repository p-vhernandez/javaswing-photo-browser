package utils;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import com.sun.tools.javac.Main;

public class Utils {

    /**
     * TODO: these methods are not working. An error occurs.
     * Code retrieved from the internet:
     * https://stackoverflow.com/q/65449685/9772035
     */
    public static Font CustomFont(String path) {
        Font customFont = loadFont(path);
        System.out.println(customFont == null);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(customFont);

        return customFont;

    }

    public static Font loadFont(String path) {
        try {
            Font myFont = Font.createFont(Font.TRUETYPE_FONT,
                    Utils.class.getResourceAsStream(path));

            return myFont.deriveFont(Font.PLAIN);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    public static Font loadFont(String path, float size) {
        try {
            Font myFont = Font.createFont(Font.TRUETYPE_FONT,
                    Utils.class.getResourceAsStream(path));

            return myFont.deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

}
