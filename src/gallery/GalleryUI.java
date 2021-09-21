package gallery;

import components.PhotoComponent;
import utils.Toast;
import utils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GalleryUI {

    private final Gallery gallery;

    private final JPanel mainPanel = new JPanel();
    private final JLabel statusBar = new JLabel();
    private final JMenuBar menuBar = new JMenuBar();
    private final JToolBar toolBar = new JToolBar();

    private final JMenu
            fileMenu = new JMenu("File"),
            viewMenu = new JMenu("View");

    private final JMenuItem
            iDelete = new JMenuItem("Delete"),
            iImport = new JMenuItem("Import"),
            iQuit = new JMenuItem("Quit");

    private final JRadioButtonMenuItem
            iViewer = new JRadioButtonMenuItem("Viewer", true),
            iBrowser = new JRadioButtonMenuItem("Browser", false);

    private final JToggleButton
            btnCategoryPeople = new JToggleButton(),
            btnCategoryPlaces = new JToggleButton(),
            btnCategoryPets = new JToggleButton();

    public GalleryUI(Gallery gallery) {
        this.gallery = gallery;
    }

    public void initializeUI() {
        gallery.setPreferredSize(new Dimension(Utils.getWindowWidth(),
                Utils.getWindowHeight()));

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

        gallery.setJMenuBar(menuBar);
    }

    private void setFileMenu() {
        fileMenu.add(iImport);
        fileMenu.add(iDelete);
        fileMenu.add(iQuit);
        fileMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
        setUpMenuItemsListeners();
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
            gallery.dispose();
        });
    }

    /**
     * ButtonGroup way of working gotten from
     * https://www.javascan.com/993/java-jradiobuttonmenuitem-example
     */
    private void setViewMenu() {
        ButtonGroup viewButtons = new ButtonGroup();

        viewMenu.add(iViewer);
        viewMenu.add(iBrowser);
        viewMenu.setBorder(new EmptyBorder(5, 5, 5, 5));

        viewButtons.add(iViewer);
        viewButtons.add(iBrowser);
        setUpRadioButtonsListeners();
    }

    // In this function will be implemented the way
    // the app changes from one mode to another
    private void setUpRadioButtonsListeners() {
        iViewer.addActionListener(listener -> {
            if (iViewer.isSelected()) {
                statusBar.setText(Utils.getBaseStatus() + Utils.getModeViewer());
            }
        });

        iBrowser.addActionListener(listener -> {
            if (iBrowser.isSelected()) {
                statusBar.setText(Utils.getBaseStatus() + Utils.getModeBrowser());
            }
        });
    }

    private void setUpToolBar() {
        setToggleButtonsListeners();

        toolBar.setFloatable(true);
        toolBar.setOrientation(SwingConstants.VERTICAL);
        toolBar.add(btnCategoryPeople);
        toolBar.add(btnCategoryPlaces);
        toolBar.add(btnCategoryPets);

        gallery.add(toolBar, BorderLayout.WEST);
    }

    private void setToggleButtonsListeners() {
        setToggleButton(btnCategoryPeople, Utils.getCategories()[0]);
        setToggleButton(btnCategoryPlaces, Utils.getCategories()[1]);
        setToggleButton(btnCategoryPets, Utils.getCategories()[2]);
    }

    private void setToggleButton(JToggleButton btnCategory, String category) {
        btnCategory.setText(category + Utils.getUnselected());
        btnCategory.setBorder(new EmptyBorder(8, 8, 8, 8));

        btnCategory.addActionListener(listener -> {
            if (btnCategory.isSelected()) {
                btnCategory.setText(category + Utils.getSelected());
            } else {
                btnCategory.setText(category + Utils.getUnselected());
            }
        });
    }

    // Background just to check if it's visible
    // Keep it empty as assignment states
    private void setUpMainSpace() {
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(148, 148, 148));
        gallery.add(mainPanel, BorderLayout.CENTER);

        setUpPhotoComponent();
    }

    private void setUpPhotoComponent() {
        PhotoComponent photoComponent = new PhotoComponent();
        photoComponent.setImage("../resources/img/test-image.jpg");

        JScrollPane scrollPane = new JScrollPane(photoComponent);
        scrollPane.setPreferredSize(new Dimension(Utils.getScrollPaneWidth(),
                Utils.getScrollPaneHeight()));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void setUpStatusBar() {
        statusBar.setText(Utils.getBaseStatus() + Utils.getModeViewer());
        statusBar.setBorder(new EmptyBorder(5, 5, 5, 5));
        gallery.add(statusBar, BorderLayout.SOUTH);
    }

}
