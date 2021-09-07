import javax.swing.*;
import java.awt.*;

public class Gallery {

    private final JFrame mainFrame;

    public Gallery() {
        mainFrame = new JFrame("Welcome to your PhotoBrowser!");
        mainFrame.setVisible(true);

        setUpUI();

        mainFrame.pack();
    }

    private void setUpUI() {
        mainFrame.setPreferredSize(new Dimension(600, 500));
    }

}
