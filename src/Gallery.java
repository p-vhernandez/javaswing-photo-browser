import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Gallery {

    private final JFrame mainFrame;
    private final JPanel mainPanel;
    private final JLabel statusBar;

    public Gallery() {
        mainFrame = new JFrame("Welcome to your PhotoBrowser");
        mainFrame.setVisible(true);

        mainPanel = new JPanel();
        statusBar = new JLabel();

        setUpUI();

        mainFrame.pack();
    }

    private void setUpUI() {
        mainFrame.setPreferredSize(new Dimension(600, 500));

        setUpMainSpace();
        setUpStatusBar();
    }

    private void setUpMainSpace() {
        // Background just to check if it's visible
        // Keep it empty as assignment states
        mainPanel.setBackground(new Color(168, 237, 206));
        mainFrame.add(mainPanel, BorderLayout.CENTER);
    }

    private void setUpStatusBar() {
        statusBar.setText("This is a dummy text as a status");
        statusBar.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainFrame.add(statusBar, BorderLayout.SOUTH);
    }

}
