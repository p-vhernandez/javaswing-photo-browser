import utils.Toast;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Gallery {

    private final JFrame mainFrame;
    private final JPanel mainPanel;
    private final JLabel statusBar;

    private final JMenuBar menuBar;
    private final JToolBar toolBar;

    private final JMenu fileMenu;
    private final JMenu viewMenu;

    private final JMenuItem iDelete, iImport, iQuit;
    private final JRadioButtonMenuItem iViewer, iBrowser;
    private final ButtonGroup viewButtons;
    private final JToggleButton btnCategoryPeople;
    private final JToggleButton btnCategoryPlaces;
    private final JToggleButton btnCategoryPets;

    private static final String APP_TITLE = "Welcome to your PhotoBrowser";
    private static final String BASE_STATUS = "This is a dummy text as a status";
    private static final String MODE_VIEWER = ". You are on viewer mode.";
    private static final String MODE_BROWSER = ". You are on browser mode.";
    private static final String CATEGORY_PEOPLE = "People";
    private static final String CATEGORY_PLACES = "Places";
    private static final String CATEGORY_PETS = "Pets";
    private static final String SELECTED = ": ON";
    private static final String UNSELECTED = ": OFF";

    public Gallery() {
        mainFrame = new JFrame(APP_TITLE);
        mainFrame.setVisible(true);

        menuBar = new JMenuBar();
        toolBar = new JToolBar();

        fileMenu = new JMenu("File");
        viewMenu = new JMenu("View");

        mainPanel = new JPanel();
        statusBar = new JLabel();

        iImport = new JMenuItem("Import");
        iDelete = new JMenuItem("Delete");
        iQuit = new JMenuItem("Quit");
        iViewer = new JRadioButtonMenuItem("Viewer", true);
        iBrowser = new JRadioButtonMenuItem("Browser", false);
        viewButtons = new ButtonGroup();
        btnCategoryPeople = new JToggleButton();
        btnCategoryPlaces = new JToggleButton();
        btnCategoryPets = new JToggleButton();

        setUpUI();

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
    }

    private void setUpUI() {
        Image appIcon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/img/picture.png"));
        mainFrame.setIconImage(appIcon);
        mainFrame.setPreferredSize(new Dimension(600, 500));
        // mainFrame.setMinimumSize(new Dimension(100, 100));

        setUpAppMenu();
        setUpToolBar();
        setUpMainSpace();
        setUpStatusBar();
    }

    private void setUpAppMenu() {
        setFileMenu();
        setViewMenu();

        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        mainFrame.setJMenuBar(menuBar);
    }

    private void setFileMenu() {
        fileMenu.add(iImport);
        fileMenu.add(iDelete);
        fileMenu.add(iQuit);
        fileMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
        setUpMenuItemsListeners();
    }

    /**
     * ButtonGroup way of working gotten from
     * https://www.javascan.com/993/java-jradiobuttonmenuitem-example
     */
    private void setViewMenu() {
        viewMenu.add(iViewer);
        viewMenu.add(iBrowser);
        viewMenu.setBorder(new EmptyBorder(5, 5, 5, 5));

        viewButtons.add(iViewer);
        viewButtons.add(iBrowser);
        setUpRadioButtonsListeners();
    }

    private void setUpMenuItemsListeners() {
        iImport.addActionListener(listener -> {
            // TODO: Open FileChooser
            Toast.showToast(mainPanel, "iImport");
        });

        iDelete.addActionListener(listener -> {
            // TODO: Delete a picture
            Toast.showToast(mainPanel, "iDelete");
        });

        iQuit.addActionListener(listener -> {
            // Close the app
            mainFrame.dispose();
        });
    }

    private void setUpRadioButtonsListeners() {
        // In this function will be implemented the way
        // the app changes from one mode to another
        iViewer.addActionListener(listener -> {
            if (iViewer.isSelected()) {
                statusBar.setText(BASE_STATUS + MODE_VIEWER);
            }
        });

        iBrowser.addActionListener(listener -> statusBar.setText(BASE_STATUS + MODE_BROWSER));
    }

    private void setUpToolBar() {
        setToggleButtonsListeners();

        toolBar.setFloatable(true);
        toolBar.setOrientation(SwingConstants.VERTICAL);
        toolBar.add(btnCategoryPeople);
        toolBar.add(btnCategoryPlaces);
        toolBar.add(btnCategoryPets);
        mainFrame.add(toolBar, BorderLayout.WEST);
    }

    private void setToggleButtonsListeners() {
        setToggleButton(btnCategoryPeople, CATEGORY_PEOPLE);
        setToggleButton(btnCategoryPlaces, CATEGORY_PLACES);
        setToggleButton(btnCategoryPets, CATEGORY_PETS);
    }

    private void setToggleButton(JToggleButton btnCategory, String category) {
        btnCategory.setText(category + UNSELECTED);
        btnCategory.setBorder(new EmptyBorder(8, 8, 8, 8));
        // btnCategory.setMinimumSize(new Dimension(300, 0));
        btnCategory.addActionListener(listener -> {
            if (btnCategory.isSelected()) {
                btnCategory.setText(category + SELECTED);
            } else {
                btnCategory.setText(category + UNSELECTED);
            }
        });
    }

    private void setUpMainSpace() {
        // Background just to check if it's visible
        // Keep it empty as assignment states
        mainPanel.setBackground(new Color(148, 148, 148));
        mainFrame.add(mainPanel, BorderLayout.CENTER);
    }

    private void setUpStatusBar() {
        statusBar.setText(BASE_STATUS + MODE_VIEWER);
        statusBar.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainFrame.add(statusBar, BorderLayout.SOUTH);
    }

}
