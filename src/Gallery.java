import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Gallery {

    private final JFrame mainFrame;
    private final JLabel statusBar;

    public Gallery() {
        mainFrame = new JFrame("Welcome to your PhotoBrowser");
        mainFrame.setVisible(true);

        statusBar = new JLabel();

        setUpUI();

        mainFrame.pack();
    }

    private void setUpUI() {
        mainFrame.setPreferredSize(new Dimension(600, 500));

        setUpStatusBar();
    }

    private void setUpStatusBar() {
        statusBar.setText("This is a dummy text as a status");
        statusBar.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainFrame.add(statusBar, BorderLayout.SOUTH);
    }

}
