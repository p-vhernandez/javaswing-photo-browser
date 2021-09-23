package gallery;

import components.photo.PhotoComponent;
import utils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;

public class GalleryUI {

    private final Gallery gallery;

    private final JPanel mainPanel = new JPanel();
    private final JLabel statusBar = new JLabel();
    private final JMenuBar menuBar = new JMenuBar();
    private final JToolBar toolBar = new JToolBar();

    private final JMenu
            fileMenu = new JMenu("File"),
            viewMenu = new JMenu("View"),
            penMenu = new JMenu("Pen options"),
            textMenu = new JMenu("Text options");

    private final JMenuItem
            iDelete = new JMenuItem("Delete"),
            iImport = new JMenuItem("Import"),
            iQuit = new JMenuItem("Quit"),
            iPenColorChooser = new JMenuItem("Color chooser"),
            iPenSizeChooser = new JMenuItem("Size chooser"),
            iFontColorChooser = new JMenuItem("Color chooser"),
            iFontSizeChooser = new JMenuItem("Size chooser"),
            iFontStyleChooser = new JMenuItem("Style chooser");

    private final JRadioButtonMenuItem
            iViewer = new JRadioButtonMenuItem("Viewer", true),
            iBrowser = new JRadioButtonMenuItem("Browser", false);

    private final JToggleButton
            btnCategoryPeople = new JToggleButton(),
            btnCategoryPlaces = new JToggleButton(),
            btnCategoryPets = new JToggleButton();

    private JScrollPane scrollPane;
    private PhotoComponent photoComponent = null;

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
        setOptionalItems();

        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        menuBar.add(penMenu);
        menuBar.add(textMenu);

        if (photoComponent == null) {
            showOptionalMenuItems(false);
        }

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
        iImport.addActionListener(listener -> chooseFileFromDevice());
        iDelete.addActionListener(listener -> deletePicture());
        iQuit.addActionListener(listener -> gallery.dispose());
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

    // TODO: select colors, sizes, fonts
    private void setOptionalItems() {
        setUpPenOptionsListeners();
        setUpFontOptionsListeners();

        penMenu.add(iPenColorChooser);
        penMenu.add(iPenSizeChooser);

        textMenu.add(iFontColorChooser);
        textMenu.add(iFontSizeChooser);
        textMenu.add(iFontStyleChooser);
    }

    private void setUpToolBar() {
        setToggleButtonsListeners();

        toolBar.setFloatable(true);
        toolBar.setOrientation(SwingConstants.VERTICAL);
        toolBar.setPreferredSize(new Dimension(Utils.getToolbarWidth(),
                Utils.getWindowHeight()));

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
    }

    private void setUpPhotoComponent(String absolutePath) {
        photoComponent = new PhotoComponent();
        photoComponent.setImage(absolutePath);
        showOptionalMenuItems(true);

        scrollPane = new JScrollPane(photoComponent);
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

    private void setUpPenOptionsListeners() {
        iPenColorChooser.addActionListener(listener -> {
            Color newColor = JColorChooser.showDialog(null, "Choose a pen color", Color.RED);
            photoComponent.setPenColor(newColor);
        });

        iPenSizeChooser.addActionListener(e -> {
            String result = (String)JOptionPane.showInputDialog(
                    null,
                    "Type the desired size",
                    "Choose a pen size",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    photoComponent.getPenSize());

            if (result != null && result.length() > 0) {
                photoComponent.setPenSize(Integer.parseInt(result));
            }
        });
    }

    private void setUpFontOptionsListeners() {
        iFontColorChooser.addActionListener(listener -> {
            Color newColor = JColorChooser.showDialog(null, "Choose a pen color", Color.RED);
            photoComponent.setFontColor(newColor);
        });

        iFontSizeChooser.addActionListener(e -> {
            String result = (String)JOptionPane.showInputDialog(
                    null,
                    "Type the desired size",
                    "Choose a font size",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    photoComponent.getFontSize());

            if (result != null && result.length() > 0) {
                photoComponent.setFontSize(Integer.parseInt(result));
            }
        });
    }

    private void chooseFileFromDevice() {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Select your image");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setMultiSelectionEnabled(false);

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                ".png, .jpg, .jpeg",
                "png", "jpg", "jpeg");
        fileChooser.addChoosableFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            setUpPhotoComponent(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void deletePicture() {
        mainPanel.remove(scrollPane);
        mainPanel.revalidate();
        mainPanel.repaint();

        photoComponent = null;
        scrollPane = null;

        showOptionalMenuItems(false);
    }

    private void showOptionalMenuItems(boolean show) {
        penMenu.setVisible(show);
        textMenu.setVisible(show);
    }

}
