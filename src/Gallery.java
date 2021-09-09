import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Gallery {

    private final JFrame mainFrame;
    private final JPanel mainPanel;
    private final JLabel statusBar;

    private final JMenuBar menuBar;

    private final JMenu mainMenu;
    private final JMenu fileMenu;
    private final JMenu viewMenu;

    private final JMenuItem iDelete, iImport, iQuit;
    private final JCheckBoxMenuItem iViewer, iBrowser;

    public Gallery() {
        mainFrame = new JFrame("Welcome to your PhotoBrowser");
        mainFrame.setVisible(true);

        menuBar = new JMenuBar();

        mainMenu = new JMenu("Menu");
        fileMenu = new JMenu("File");
        viewMenu = new JMenu("View");

        mainPanel = new JPanel();
        statusBar = new JLabel();

        iImport = new JMenuItem("Import");
        iDelete = new JMenuItem("Delete");
        iQuit = new JMenuItem("Quit");
        iViewer = new JCheckBoxMenuItem("Viewer", true);
        iBrowser = new JCheckBoxMenuItem("Browser", false);

        setUpUI();

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
    }

    private void setUpUI() {
        mainFrame.setPreferredSize(new Dimension(600, 500));
        // mainFrame.setMinimumSize(new Dimension(100, 100));

        setUpAppMenu();
        setUpMainSpace();
        setUpStatusBar();
    }

    private void setUpAppMenu() {
        fileMenu.add(iImport);
        fileMenu.add(iDelete);
        fileMenu.add(iQuit);
        fileMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
        //fileMenu.setMinimumSize(new Dimension(150, 0));

        viewMenu.add(iViewer);
        viewMenu.add(iBrowser);
        viewMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
        //viewMenu.setMinimumSize(new Dimension(150, 0));
        iViewer.setState(true);
        iBrowser.setState(false);
        setUpCheckMenuItemsListeners();

        mainMenu.add(fileMenu);
        mainMenu.add(viewMenu);

        menuBar.add(mainMenu);
        mainFrame.setJMenuBar(menuBar);
    }

    private void setUpCheckMenuItemsListeners() {
        // In this function will be implemented the way
        // the app changes from one mode to another
        iViewer.addActionListener(listener -> {
            if (!iViewer.getState()) {
                iBrowser.setState(false);
                iViewer.setState(true);
            }
        });

        iBrowser.addActionListener(listener -> {
            if (!iBrowser.getState()) {
                iViewer.setState(false);
                iBrowser.setState(true);
            }
        });
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
