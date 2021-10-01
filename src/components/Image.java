package components;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;

/**
 * Potential use: save and display saved data in an image
 */
public class Image implements Serializable {

    private final String imgPath;

    public Image(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImgPath() {
        return imgPath;
    }

    @Serial
    private void readObject(ObjectInputStream inputStream) {
        try {
            inputStream.defaultReadObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
